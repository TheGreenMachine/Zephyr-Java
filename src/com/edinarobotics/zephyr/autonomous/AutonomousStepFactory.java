package com.edinarobotics.zephyr.autonomous;

import com.edinarobotics.utils.autonomous.*;
import com.edinarobotics.zephyr.Zephyr;

/**
 *
 */
public class AutonomousStepFactory {
    private Zephyr robot;
    
    public AutonomousStepFactory(Zephyr robot){
        this.robot = robot;
    }
    
    public AutonomousStep getShooterFireStep(double shooterSpeed, int numShots){
        final double SHOOTER_WARMUP_DELAY = 3;
        final double PISTON_SET_DELAY = 1;
        final double BALL_LOAD_DELAY = 2.5;
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
}
