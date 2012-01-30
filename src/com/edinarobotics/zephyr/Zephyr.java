/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.edinarobotics.zephyr;


import com.edinarobotics.utils.gamepad.FilterSet;
import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.utils.gamepad.GamepadResult;
import com.edinarobotics.utils.gamepad.filters.DeadzoneFilter;
import com.edinarobotics.utils.gamepad.filters.ScalingFilter;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SimpleRobot;
import com.edinarobotics.utils.sensors.FIRFilter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Zephyr extends SimpleRobot {
    //Driving Variables
    private double leftDrive = 0;
    private double rightDrive = 0;
   
    //Shooter Variables
    private double shooterSpeed = 0;
    private boolean ballLoaderUp = false; 
    private final double SHOOTER_SPEED_STEP = 0.0005;
    private double lastManualSpeed = 0;
    
    //Sensor Variables
     private double filteringWeights[] = {.67, 17, .16};
     private FIRFilter firFiltering = new FIRFilter(filteringWeights);
     
     //Camera Variables
     double cameraSetX;
     double cameraSetY;
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {}

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() 
    {
        // Filters for Gamepads
        FilterSet filters = new FilterSet();
        filters.addFilter(new DeadzoneFilter(.05));
        filters.addFilter(new ScalingFilter());
        
        // Gamepads
        Gamepad driveGamepad = new Gamepad(1);
        Gamepad gamepad2 = new Gamepad(2);
        
        // Initiate components
        Components components = Components.getInstance();
        
        while(this.isOperatorControl()&&this.isEnabled())
        {
            // Filter the joysticks on the gamepads according to the filters
            // initialized
           GamepadResult joystick = filters.filter(driveGamepad.getJoysticks());
           
           // Set values for the drive speeds of the robot
           leftDrive = joystick.getLeftY();
           rightDrive = joystick.getRightY()*-1;
           
           // If the right bumper on the driveGamepad is pushed, speed up the
           // shooter
           if(driveGamepad.getRawButton(Gamepad.RIGHT_BUMPER))
           {
               //Step speed of shooter up.
               shooterSpeed -= SHOOTER_SPEED_STEP;
               
               // Limit the speed of the shooter to not exceed -1
               if(shooterSpeed <= -1)
               {
                   shooterSpeed = -1;
               }
               
               // Store the speed of the shooter to a second variable
               lastManualSpeed = shooterSpeed;
           }
           
           // If the left bumper on the driveGamepad is pushed, slow down the
           // shooter
           else if(driveGamepad.getRawButton(Gamepad.LEFT_BUMPER))
           {
               //Step speed of shooter down.
               shooterSpeed += SHOOTER_SPEED_STEP;
               
               // Limit the speed of the shooter to not go past 0
               if(shooterSpeed>=0)
               {
                   shooterSpeed = 0;
               }
               
               // Store the speed of the shooter to a second variable
               lastManualSpeed = shooterSpeed;
           }
           
           // Jump shooter speed to max if button 1 on the driveGamepad is
           // pushed
           if(driveGamepad.getRawButton(Gamepad.BUTTON_1))
           {
               // Max is -1
               shooterSpeed = -1;
           }
           
           // Jump shooter speed to the min if button 2 on the driveGamepad is
           // pushed
           else if(driveGamepad.getRawButton(Gamepad.BUTTON_2))
           {
               // Min is 0
               shooterSpeed = 0;
           }
           
           // Jump shooter speed to the last manually set speed if button 3 on 
           // the driveGamepad is pushed
           else if(driveGamepad.getRawButton(Gamepad.BUTTON_3))
           {
               shooterSpeed = lastManualSpeed;
           }
           
           // Set the camera servo positions
           cameraSetX = components.cameraServoHorizontal.get() + driveGamepad.getD_PadX() * .1;
           cameraSetY = components.cameraServoHorizontal.get() + driveGamepad.getD_PadY() * .1;
           ballLoaderUp = driveGamepad.getRawButton(Gamepad.RIGHT_TRIGGER);
           mechanismSet();
        }
    }
    
    /**
     * Updates all parts of the robot to avoid safety timeouts
     */
    private void mechanismSet(){
        Components robotParts = Components.getInstance();
        robotParts.driveControl.tankDrive(leftDrive, rightDrive);
        robotParts.shooterJaguar.set(shooterSpeed);
        robotParts.ballLoadPiston.set((ballLoaderUp ? Relay.Value.kReverse :
                                                      Relay.Value.kForward));
        robotParts.cameraServoHorizontal.set(cameraSetX);
        robotParts.cameraServoVertical.set(cameraSetY);
        SmartDashboard.putDouble("Shooter:",shooterSpeed);
        SmartDashboard.putInt("Sonar:", (int)firFiltering.filter(robotParts.sonar.getValue()));
        SmartDashboard.putDouble("X-Axis Camera Servo:", robotParts.cameraServoHorizontal.get());
        SmartDashboard.putDouble("Y-Axis Camera Servo:", robotParts.cameraServoVertical.get());
    }
    
    /**
     * Stop the robot from moving
     */
    private void stop(){
        leftDrive = 0;
        rightDrive = 0;
        shooterSpeed = 0;
        ballLoaderUp = false;
        mechanismSet();
    }
}
