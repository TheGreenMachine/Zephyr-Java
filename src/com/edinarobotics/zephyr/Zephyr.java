/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.edinarobotics.zephyr;


import com.edinarobotics.utils.gamepad.Gamepad;
import edu.wpi.first.wpilibj.SimpleRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Zephyr extends SimpleRobot {
    private double leftDrive;
    private double rightDrive;
    
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        Gamepad gamepad1 = new Gamepad(1);
        Gamepad gamepad2 = new Gamepad(2);
        Components components = Components.getInstance();
        while(this.isOperatorControl()&&this.isEnabled()){
           leftDrive = gamepad1.getLeftY();
           rightDrive = gamepad1.getRightY();
           mechanismSet();
        }
        stop();
    }
    
    private void mechanismSet(){
        Components.getInstance().driveControl.tankDrive(leftDrive, rightDrive);
    }
    
    private void stop(){
        leftDrive = 0;
        rightDrive = 0;
        mechanismSet();
    }
}
