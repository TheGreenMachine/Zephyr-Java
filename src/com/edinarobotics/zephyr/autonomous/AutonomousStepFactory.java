package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.PrintStep;
import com.edinarobotics.utils.autonomous.*;
import com.edinarobotics.zephyr.Zephyr;
import com.edinarobotics.zephyr.autonomous.stringsources.FormattedShooterSpeedSource;

/**
 *
 */
public class AutonomousStepFactory {
    private Zephyr robot;
    
    public AutonomousStepFactory(Zephyr robot){
        this.robot = robot;
    }
    
    public AutonomousStep getShooterFireStep(double shooterSpeed, int numShots){
        final double PISTON_SET_DELAY = 1;
        final double BALL_LOAD_DELAY = 2.5;
        final double SHOOTER_WARMUP_DELAY = 4.5;
        //Steps to bring the piston up and down
        AutonomousStep[] fireSteps = new AutonomousStep[9];
        fireSteps[0] = new ShooterPistonControlStep(true, robot);
        fireSteps[1] = new PrintStep("Firing!");
        fireSteps[2] = new PrintStep(new FormattedShooterSpeedSource());
        fireSteps[3] = new IdleWaitStep(PISTON_SET_DELAY, robot);
        fireSteps[4] = new ShooterPistonControlStep(false, robot);
        fireSteps[5] = new IdleWaitStep(PISTON_SET_DELAY, robot);
        fireSteps[6] = new SetConveyorStep(true, robot);
        fireSteps[7] = new IdleWaitStep(BALL_LOAD_DELAY, robot);
        fireSteps[8] = new SetConveyorStep(false, robot);
        
        AutonomousStep[] steps = new AutonomousStep[5];
        steps[0] = new PrintStep("Warming the shooter up!");
        //Start the shooter
        steps[1] = new SetShooterSpeedStep(shooterSpeed, robot);
        //Wait for the shooter to warm up
        steps[2] = new IdleWaitStep(SHOOTER_WARMUP_DELAY, robot);
        //Fire the piston several times
        steps[3] = new ForLoopStep(new SequentialAutonomousStepGroup(fireSteps), numShots);
        //Stop the shooter
        steps[4] = new SetShooterSpeedStep(0, robot);
        
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
}
