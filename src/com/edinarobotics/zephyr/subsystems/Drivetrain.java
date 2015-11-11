/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.Zephyr.subsystems;

import com.edinarobotics.Zephyr.commands.SetDrivetrainCommand;
import com.edinarobotics.utils.subsystems.Subsystem1816;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author GreenMachine
 */
public class Drivetrain extends Subsystem1816{

    public RobotDrive robotDrive;

    private double leftMotor, rightMotor, backLeftMotor, backRightMotor;
    
    public Drivetrain(int leftMotor, int rightMotor, int backLeftMotor, int backRightMotor){
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.backLeftMotor = backLeftMotor;
        this.backRightMotor = backRightMotor;
        this.robotDrive = new RobotDrive(leftMotor, rightMotor, backLeftMotor, backRightMotor);
    }
    
    public void setValues(double leftMotor, double rightMotor){
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        update();
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new SetDrivetrainCommand(leftMotor, rightMotor));
    }
    
    public void update(){
        robotDrive.tankDrive(leftMotor, rightMotor);
    }
    
    public void setDefaultCommand(Command command){
        if(getDefaultCommand() != null){
            super.getDefaultCommand().cancel();
        }
        super.setDefaultCommand(command);
    }
    
}
