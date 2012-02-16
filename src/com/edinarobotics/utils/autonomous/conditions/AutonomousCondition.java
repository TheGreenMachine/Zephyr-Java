package com.edinarobotics.utils.autonomous.conditions;

import com.edinarobotics.utils.autonomous.BooleanCondition;
import edu.wpi.first.wpilibj.RobotBase;

/**
 * A {@link BooleanCondition} that returns whether or not a given robot
 * is enabled and is in autonomous mode.
 */
public class AutonomousCondition implements BooleanCondition{
    private RobotBase robot;
    
    /**
     * Constructs a new AutonomousCondition that can be used to test whether
     * or not a given robot is enabled and is in autonomous mode.
     * @param robot the robot to test for autonomous mode.
     */
    public AutonomousCondition(RobotBase robot){
        this.robot = robot;
    }
    
    /**
     * Returns whether or not the robot is in autonomous mode and is enabled.
     * This is the result of: 
     * "{@code robot.isAutonomous() && robot.isEnabled()}."
     * @return {@code true} if the robot is enabled and is in autonomous mode,
     * {@code false} otherwise.
     */
    public boolean get(){
        return robot.isAutonomous() && robot.isEnabled();
    }
}
