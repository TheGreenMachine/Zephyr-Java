package com.edinarobotics.utils.autonomous.conditions;

import com.edinarobotics.utils.autonomous.BooleanCondition;
import edu.wpi.first.wpilibj.RobotBase;

/**
 * A {@link BooleanCondition} that returns whether or not a given robot
 * is enabled and is in operator control mode.
 */
public class OperatorControlCondition implements BooleanCondition{
    private RobotBase robot;
    
    /**
     * Constructs a new OperatorControlCondition that can be used to test
     * whether or not a given robot is enabled and is in operator
     * control mode.
     * @param robot the robot to test for operator control mode.
     */
    public OperatorControlCondition(RobotBase robot){
        this.robot = robot;
    }
    
    /**
     * Returns whether or not the robot is in operator control mode and is
     * enabled.
     * This is the result of: 
     * "{@code robot.isOperatorControl() && robot.isEnabled()}."
     * @return {@code true} if the robot is enabled and is in operator control
     * mode, {@code false} otherwise.
     */
    public boolean get(){
        return robot.isOperatorControl() && robot.isEnabled();
    }
}
