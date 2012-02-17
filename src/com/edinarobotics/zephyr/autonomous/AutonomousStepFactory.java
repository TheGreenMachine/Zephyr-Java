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
        //Steps to bring the piston up and down
        AutonomousStep[] fireSteps = new AutonomousStep[4];
        fireSteps[0] = new ShooterPistonControlStep(true, robot);
        fireSteps[1] = new IdleWaitStep(0.5, robot);
        fireSteps[2] = new ShooterPistonControlStep(false, robot);
        fireSteps[3] = new IdleWaitStep(0.5, robot);
        
        AutonomousStep[] steps = new AutonomousStep[3];
        //Start the shooter
        steps[0] = new SetShooterSpeedStep(shooterSpeed, robot);
        //Fire the piston several times
        steps[1] = new ForLoopStep(new SequentialAutonomousStepGroup(fireSteps), numShots);
        //Stop the shooter
        steps[2] = new SetShooterSpeedStep(0, robot);
        
        return new SequentialAutonomousStepGroup(steps);
    }
}
