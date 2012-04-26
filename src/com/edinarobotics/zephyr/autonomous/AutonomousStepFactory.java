package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.*;
import com.edinarobotics.zephyr.Components;
import com.edinarobotics.zephyr.Zephyr;

/**
 *
 */
public class AutonomousStepFactory {
    private Zephyr robot;
    private final double DEFAULT_GYRO_TURN_SPEED = 80;
    
    public AutonomousStepFactory(Zephyr robot){
        this.robot = robot;
    }
    
    public AutonomousStep getShooterFireStep(double shooterSpeed, int numShots){
        final double PISTON_SET_DELAY = 1;
        final double BALL_LOAD_DELAY = 2.5;
        final double SHOOTER_WARMUP_DELAY = 3;
        //Steps to bring the piston up and down
        AutonomousStep[] fireSteps = new AutonomousStep[7];
        fireSteps[0] = new ShooterPistonControlStep(true, robot);
        fireSteps[1] = new IdleWaitStep(PISTON_SET_DELAY, robot);
        fireSteps[2] = new ShooterPistonControlStep(false, robot);
        fireSteps[3] = new IdleWaitStep(PISTON_SET_DELAY, robot);
        fireSteps[4] = new SetConveyorStep(true, robot);
        fireSteps[5] = new IdleWaitStep(BALL_LOAD_DELAY, robot);
        fireSteps[6] = new SetConveyorStep(false, robot);
        
        AutonomousStep[] steps = new AutonomousStep[4];
        //Start the shooter
        steps[0] = new SetShooterSpeedStep(shooterSpeed, robot);
        //Wait for the shooter to warm up
        steps[1] = new IdleWaitStep(SHOOTER_WARMUP_DELAY, robot);
        //Fire the piston several times
        steps[2] = new ForLoopStep(new SequentialAutonomousStepGroup(fireSteps), numShots);
        //Stop the shooter
        steps[3] = new SetShooterSpeedStep(0, robot);
        
        return new SequentialAutonomousStepGroup(steps);
    }
    
    public AutonomousStep shooterVoltageEstimateStep(double targetVelocity, double estimate1Delay, double estimate2Delay){
        final double ITER_1_FRACTION = 0.75;
        AutonomousStep[] warmupSteps  = new AutonomousStep[4];
        warmupSteps[0] = new ShooterVoltagePWMStep(targetVelocity, robot);
        warmupSteps[1] = new IdleWaitStep(estimate1Delay, robot);
        warmupSteps[2] = new ShooterVoltagePWMStep(targetVelocity, robot);
        warmupSteps[3] = new IdleWaitStep(estimate2Delay,robot);
        return new SequentialAutonomousStepGroup(warmupSteps);
    }
    
    /**
     * Creates a step that performs a turn of a certain number of degrees
     * using the gyro.
     * @param degreesLeft The number of degrees that the robot should
     * turn left.
     * @return An {@link AutonomousStep} that performs a left turn
     * of a certain number of degrees using the gyro.
     */
    public AutonomousStep turnLeftStep(double degreesLeft){
        return getGyroTurnSteps(-degreesLeft, DEFAULT_GYRO_TURN_SPEED);
    }
    
    /**
     * Creates a step that performs a turn of a certain number of degrees
     * using the gyro.
     * @param degreesLeft The number of degrees that the robot should
     * turn left.
     * @param power The power to be given to both motors when performing the
     * turn.
     * @return An {@link AutonomousStep} that performs a left turn
     * of a certain number of degrees using the gyro and the given power.
     */
    public AutonomousStep turnLeftStep(double degreesLeft, double power){
        return getGyroTurnSteps(degreesLeft, power);
    }
    
    /**
     * Creates a step that performs a turn of a certain number of degrees
     * using the gyro.
     * @param degreesLeft The number of degrees that the robot should
     * turn left.
     * @return An {@link AutonomousStep} that performs a left turn
     * of a certain number of degrees using the gyro.
     */
    public AutonomousStep turnRightStep(double degreesRight){
        return getGyroTurnSteps(degreesRight, DEFAULT_GYRO_TURN_SPEED);
    }
    
    public AutonomousStep turnRightStep(double degreesRight, double power){
        return getGyroTurnSteps(degreesRight, power);
    }
    
    /**
     * Returns an autonomous step that performs a turn using a gyro.
     * @param angle The angle to turn. Negative values represent counterclockwise
     * turns.
     * @param power The power for the chassis to use to turn.
     */
    private AutonomousStep getGyroTurnSteps(double angle, double power){
        double workingPower = Math.abs(power);
        double leftPower = 0;
        double rightPower = 0;
        if(angle<=0){
            leftPower = -workingPower;
            rightPower = workingPower;
        }
        else{
            leftPower = workingPower;
            rightPower = -workingPower;
        }
        AutonomousStep[] turnSteps = new AutonomousStep[3];
        turnSteps[0] = new SetDriveSpeedStep(leftPower, rightPower, robot);
        turnSteps[1] = new GyroWaitStep(angle, Components.getInstance().gyro, robot);
        turnSteps[2] = new SetDriveSpeedStep(0, 0, robot);
        return new SequentialAutonomousStepGroup(turnSteps);
    }
}
