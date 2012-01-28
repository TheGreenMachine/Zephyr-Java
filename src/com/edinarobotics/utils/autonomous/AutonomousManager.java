package com.edinarobotics.utils.autonomous;

import edu.wpi.first.wpilibj.RobotBase;
import java.util.Vector;

/**
 * This class manages the executions of the several {@link AutonomousStep}
 * objects that are used in an autonomous program.
 */
public class AutonomousManager {
    private AutonomousStep[] autonomousSteps;
    private RobotBase robot;
    
    /**
     * Constructs an AutonomousManager using the given steps.
     * @param steps The steps that this AutonomousManager will run.
     * @param robot The robot that will be running these steps. This is 
     * used to check whether or not this robot is in autonomous.
     */
    public AutonomousManager(AutonomousStep[] steps, RobotBase robot){
        this.robot = robot;
        autonomousSteps = steps;
    }
    
    /**
     * Constructs an AutonomousManager using the given steps.
     * @param steps The steps that this AutonomousManager will run.
     * @param robot The robot that will be running these steps. This is 
     * used to check whether or not this robot is in autonomous.
     */
    public AutonomousManager(Vector steps, RobotBase robot){
        this(convertVector(steps), robot);
    }
    
    /**
     * Used internally to convert a Vector to an array of
     * AutonomousStep objects.
     * @param toConvert The Vector to convert to an array
     * @return an array of AutonomousStep objects.
     */
    private static AutonomousStep[] convertVector(Vector toConvert){
        AutonomousStep[] tempSteps = new AutonomousStep[toConvert.size()];
        for(int i=0;i<toConvert.size();i++){
            tempSteps[i] = (AutonomousStep)toConvert.elementAt(i);
        }
        return tempSteps;
    }
    
    /**
     * Starts this AutonomousManager. This method should only be called
     * once and will handle all of the steps.
     * This method will return when the autonomous phase ends or when all the
     * steps have finished.
     */
    public void start(){
        int currentStep = 0;
        autonomousSteps[currentStep].start();
        while(robot.isEnabled() && robot.isAutonomous()){
            //Autonomous loop
            if(!autonomousSteps[currentStep].isFinished()){
                //Step not finished, call run().
                autonomousSteps[currentStep].run();
            }
            else{
                //Step finished, call stop().
                autonomousSteps[currentStep].stop();
                currentStep++;
                if(currentStep>=autonomousSteps.length){
                    //All steps have run, we're done.
                    return;
                }
                autonomousSteps[currentStep].start();
            }
            //End of autonomous loop
        }
        autonomousSteps[currentStep].stop();
    }
}
