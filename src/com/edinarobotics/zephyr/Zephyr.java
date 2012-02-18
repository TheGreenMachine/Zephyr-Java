/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.edinarobotics.zephyr;


import com.edinarobotics.utils.autonomous.AutonomousManager;
import com.edinarobotics.utils.autonomous.AutonomousStep;
import com.edinarobotics.utils.gamepad.FilterSet;
import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.utils.gamepad.GamepadResult;
import com.edinarobotics.utils.gamepad.ToggleHelper;
import com.edinarobotics.utils.gamepad.filters.DeadzoneFilter;
import com.edinarobotics.utils.gamepad.filters.ScalingFilter;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SimpleRobot;
import com.edinarobotics.utils.sensors.FIRFilter;
import com.edinarobotics.zephyr.autonomous.AutonomousStepFactory;
import com.edinarobotics.zephyr.autonomous.IdleStopStep;
import com.edinarobotics.zephyr.autonomous.IdleWaitStep;
import com.edinarobotics.zephyr.parts.CypressComponents;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO.EnhancedIOException;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Zephyr extends SimpleRobot {
    //Driving Variables
    public double leftDrive = 0;
    public double rightDrive = 0;
    private final double ONE_STICK_MULTIPLIER = 0.5;
   
    //Shooter Variables
    public double shooterSpeed = 0;
    public boolean ballLoaderUp = false;
    public double shooterRotateSpeed = 0;
    private final double SHOOTER_SPEED_STEP = 0.0005;
    private double lastManualSpeed = 0;
    
    //Sensor Variables
     private FIRFilter firFiltering = FIRFilter.autoWeightedFilter(20);
     //Camera Variables
     public double cameraSetX;
     public double cameraSetY;
     private double CAMERA_STEP = .005;
     //Collector Variables
     public int collectorLift = 0;
     public boolean collectorSpin = false;
     public boolean convMove = false;

     public boolean shifters = false;
     
     /**
      * This function initializes the robot by constructing objects for each
      * of its components and starting the compressor.
      */
     protected void robotInit(){
         Components.getInstance();
     }
     
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        //Cypress switch constants
        final int POSITION_LEFT_SWITCH = 5;
        final int POSITION_RIGHT_SWITCH = 3;
        final int COLLECT_SWITCH = 1;
        final int SHOOTING_DELAY_1 = 4;
        final int SHOOTING_DELAY_2 = 2;
        
        //Autonomous constants
        final int NO_AUTONOMOUS = 0;
        final int KEY_LEFT = 1;
        final int KEY_RIGHT = 2;
        final int KEY_MIDDLE = 3;
        final int DELAY_MULTIPLIER = 3;
        Components parts = Components.getInstance();
        CypressComponents cypress = parts.cypress;
        
        //Autonomous program constants
        final double LEFT_KEY_SHOOTER_SPEED = 1;
        final double RIGHT_KEY_SHOOTER_SPEED = 1;
        final double MIDDLE_KEY_SHOOTER_SPEED = 1;
        
        //Autonomous config values
        int shootingDelayValue = 1;
        boolean driveToCollect = false;
        int keyPosition = KEY_MIDDLE;
        
        //Determine shooting delay value
        shootingDelayValue = (((cypress.getDigital(SHOOTING_DELAY_2)?1:0)<<1)+
                             (cypress.getDigital(SHOOTING_DELAY_1)?1:0))*
                             DELAY_MULTIPLIER;
        
        //Determine if we should collect
        driveToCollect = cypress.getDigital(COLLECT_SWITCH);
        
        //Determine position on the key
        keyPosition = ((cypress.getDigital(POSITION_RIGHT_SWITCH)?1:0)<<1)+
                      (cypress.getDigital(POSITION_LEFT_SWITCH)?1:0);
        
        //Create autonomous program
        AutonomousStepFactory stepFactory = new AutonomousStepFactory(this);
        //Create our pre-shooting delay step
        AutonomousStep shootDelayStep = new IdleWaitStep(shootingDelayValue, this);
        
        //Create out shooting step
        AutonomousStep shootStep;
        switch(keyPosition){
            case KEY_LEFT: shootStep = stepFactory.getShooterFireStep(LEFT_KEY_SHOOTER_SPEED, 2); break;
            case KEY_RIGHT: shootStep = stepFactory.getShooterFireStep(RIGHT_KEY_SHOOTER_SPEED, 2); break;
            case KEY_MIDDLE: shootStep = stepFactory.getShooterFireStep(MIDDLE_KEY_SHOOTER_SPEED, 2); break;
            default: shootStep = new IdleWaitStep(0, this);
        }
        AutonomousStep[] steps = new AutonomousStep[3];
        steps[0] = shootDelayStep;
        steps[1] = shootStep;
        steps[2] = new IdleStopStep(this);
        AutonomousManager manager = new AutonomousManager(steps, this);
        manager.start();
    }

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
        Gamepad shootGamepad = new Gamepad(2);
        
        // Initiate components
        Components components = Components.getInstance();
        ToggleHelper button3 = new ToggleHelper();
        while(this.isOperatorControl()&&this.isEnabled())
        {
           //************GAMEPAD 1****************************************//
           // Set values for the drive speeds of the robot
           //Using 0.9 as comparison value to avoid floating point problems
           //Shouldn't ever be an issue but just in case
           if(Math.abs(driveGamepad.getDPadY())<=0.9){
               //Normal joystick drive. D-pad is not 1 or -1
               // Filter the joysticks on the gamepads according to the filters
               // initialized
               GamepadResult joystick = filters.filter(driveGamepad.getJoysticks());
               leftDrive = joystick.getLeftY();
               rightDrive = joystick.getRightY();
           }
           else{
               //Single direction, forward/backward drive with the d-pad.
               double oneStickDriveValue = driveGamepad.getDPadY() * ONE_STICK_MULTIPLIER;
               leftDrive = oneStickDriveValue;
               rightDrive = oneStickDriveValue;
           }
           
           if(driveGamepad.getRawButton(Gamepad.RIGHT_BUMPER))
           {
               collectorLift = 1;
           }
           else if(driveGamepad.getRawButton(Gamepad.RIGHT_TRIGGER))
           {
               collectorLift = -1;
           }
           else{
               collectorLift = 0;
           }
           if(driveGamepad.getRawButton(Gamepad.LEFT_TRIGGER))
           {
               collectorSpin = true;
           }
           else{
               collectorSpin = false;
           }
            if(driveGamepad.getRawButton(Gamepad.LEFT_BUMPER))
           {
               convMove = true;
           }
           else{
               convMove = false;
           }
           if(button3.isToggled(driveGamepad.getRawButton(Gamepad.BUTTON_3)))
           {
               shifters = !shifters;
           }
           //******************GAMEPAD 2*********************************//
           // If the right bumper on the shootGamepad is pushed, speed up the
           // shooter
           if(shootGamepad.getRawButton(Gamepad.RIGHT_BUMPER))
           {
               //Step speed of shooter up.
               shooterSpeed += SHOOTER_SPEED_STEP;
               
               // Limit the speed of the shooter to not exceed -1
               if(shooterSpeed >= 1)
               {
                   shooterSpeed = 1;
               }
               
               // Store the speed of the shooter to a second variable
               lastManualSpeed = shooterSpeed;
           }
           
           // If the left bumper on the shootGamepad is pushed, slow down the
           // shooter
           else if(shootGamepad.getRawButton(Gamepad.RIGHT_TRIGGER))
           {
               //Step speed of shooter down.
               shooterSpeed -= SHOOTER_SPEED_STEP;
               
               // Limit the speed of the shooter to not go past 0
               if(shooterSpeed<=0)
               {
                   shooterSpeed = 0;
               }
               
               // Store the speed of the shooter to a second variable
               lastManualSpeed = shooterSpeed;
           }
           
           // Jump shooter speed to max if button 1 on the shootGamepad is
           // pushed
           if(shootGamepad.getRawButton(Gamepad.BUTTON_1))
           {
               // Max is 1
               shooterSpeed = 1;
           }
           // Jump shooter speed to 50% if button 3 on the shootGamepad is
           // pushed
           else if(shootGamepad.getRawButton(Gamepad.BUTTON_3)){
               shooterSpeed = 0.5;
           }
           // Jump shooter speed to the min if button 3 on the shootGamepad is
           // pushed
           else if(shootGamepad.getRawButton(Gamepad.BUTTON_2))
           {
               // Min is 0
               shooterSpeed = 0;
           }
           
           shooterRotateSpeed = filters.filter(shootGamepad.getJoysticks()).getRightX();
           
           // Set the camera servo positions
           cameraSetY = components.cameraServoVertical.get() + shootGamepad.getDPadY() * CAMERA_STEP;
           ballLoaderUp = shootGamepad.getRawButton(Gamepad.LEFT_TRIGGER) || 
                          shootGamepad.getRawButton(Gamepad.LEFT_BUMPER);
           mechanismSet();
           Timer.delay(0.005);
        }
    }
    
    /**
     * Updates all parts of the robot to avoid safety timeouts
     */
    public void mechanismSet(){
        //Driving Assignments
        Components robotParts = Components.getInstance();
        robotParts.drive.setDrivingSpeed(leftDrive, rightDrive);
        robotParts.drive.shift(shifters);
        //Shooter Assignments
        robotParts.shooter.setSpeed(shooterSpeed);
        robotParts.shooter.firePiston(ballLoaderUp);
        robotParts.shooter.rotate(shooterRotateSpeed);
        //Collector Assignments
        robotParts.collector.conveyorMove(convMove);
        robotParts.collector.collect(collectorSpin);
        robotParts.collector.lift(collectorLift);
        //Servo Assignments
        robotParts.cameraServoVertical.set(cameraSetY);
        //Sonar Processing
        String shooterPowerString = "Shooter: "+shooterSpeed;
        int sonarVal = (int) robotParts.sonar.getFilteredValue();
        String sonarValue = "Sonar reads: " + String.valueOf((sonarVal/2)+5);
        String servoPositions = "Y-Axis Servo: "+robotParts.cameraServoVertical.get();
        robotParts.textOutput.println(DriverStationLCD.Line.kUser3,1, "                                                              ");
        robotParts.textOutput.println(DriverStationLCD.Line.kUser2, 1, shooterPowerString);
        robotParts.textOutput.println(DriverStationLCD.Line.kUser3, 1, sonarValue);
        robotParts.textOutput.println(DriverStationLCD.Line.kUser4, 1, servoPositions);
        robotParts.textOutput.updateLCD();
        
    }
    
    /**
     * Stop the robot from moving
     */
    public void stop(){
        leftDrive = 0;
        rightDrive = 0;
        shooterSpeed = 0;
        ballLoaderUp = false;
        mechanismSet();
    }
}
