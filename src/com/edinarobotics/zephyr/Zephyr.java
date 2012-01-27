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
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        FilterSet filters = new FilterSet();
        filters.addFilter(new DeadzoneFilter(.05));
        filters.addFilter(new ScalingFilter());
        Gamepad gamepad1 = new Gamepad(1);
        Gamepad gamepad2 = new Gamepad(2);
        Components components = Components.getInstance();
        while(this.isOperatorControl()&&this.isEnabled()){
           GamepadResult joystick = filters.filter(gamepad1.getJoysticks());
           leftDrive = joystick.getLeftY();
           rightDrive = joystick.getRightY()*-1;
           if(gamepad1.getRawButton(Gamepad.RIGHT_BUMPER)){
               //Step speed of shooter up.
               shooterSpeed -= SHOOTER_SPEED_STEP;
               if(shooterSpeed<=-1){
                   shooterSpeed = -1;//Max speed is reverse 1 (-1).
               }
               lastManualSpeed = shooterSpeed;
           }
           else if(gamepad1.getRawButton(Gamepad.LEFT_BUMPER)){
               //Step speed of shooter down.
               shooterSpeed += SHOOTER_SPEED_STEP;
               if(shooterSpeed>=0){
                   shooterSpeed = 0;
               }
               lastManualSpeed = shooterSpeed;
           }
           if(gamepad1.getRawButton(Gamepad.BUTTON_1)){
               //Jump shooter speed to max.
               shooterSpeed = -1; //Max is -1
           }
           else if(gamepad1.getRawButton(Gamepad.BUTTON_2)){
               //Jump shooter speed to min.
               shooterSpeed = 0;
           }
           else if(gamepad1.getRawButton(Gamepad.BUTTON_3)){
               shooterSpeed = lastManualSpeed;
           }
           ballLoaderUp = gamepad1.getRawButton(Gamepad.RIGHT_TRIGGER);
           mechanismSet();
        }
        //stop();
    }
    
    private void mechanismSet(){
        Components robotParts = Components.getInstance();
        robotParts.driveControl.tankDrive(leftDrive, rightDrive);
        robotParts.shooterJaguar.set(shooterSpeed);
        robotParts.ballLoadPiston.set((ballLoaderUp ? Relay.Value.kReverse :
                                                      Relay.Value.kForward));
        String shooterPowerString = "Shooter: "+shooterSpeed;
        String sonarValue = "Sonar reads: " + firFiltering.filter(robotParts.sonar.getValue());
        robotParts.textOutput.println(DriverStationLCD.Line.kUser2, 1, shooterPowerString);
        robotParts.textOutput.println(DriverStationLCD.Line.kUser3, 1, sonarValue);
        robotParts.textOutput.updateLCD();
    }
    
    private void stop(){
        leftDrive = 0;
        rightDrive = 0;
        shooterSpeed = 0;
        ballLoaderUp = false;
        mechanismSet();
    }
}
