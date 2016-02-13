/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.Zephyr.subsystems;

import com.edinarobotics.Zephyr.commands.SetDrivetrainCommand;
import com.edinarobotics.utils.math.Math1816;
import com.edinarobotics.utils.subsystems.Subsystem1816;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author GreenMachine
 */
public class Drivetrain extends Subsystem1816{

    public RobotDrive robotDrive;
    public double verticalStrafe, rotation;
    private double leftMotor, rightMotor, backLeftMotor, backRightMotor;
    
    public Drivetrain(int leftMotor, int rightMotor, int backLeftMotor, int backRightMotor){
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.backLeftMotor = backLeftMotor;
        this.backRightMotor = backRightMotor;
        this.robotDrive = new RobotDrive(leftMotor, backLeftMotor, rightMotor, backRightMotor);
    }
    
    public void setValues(double verticalStrafe, double rotation){
        this.verticalStrafe = verticalStrafe;
        this.rotation = rotation;
        update();
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new SetDrivetrainCommand(verticalStrafe, rotation));
    }
    
    public void update(){
        double vertical = Math1816.coerceValue(1, -1, verticalStrafe);
        double rotate = Math1816.coerceValue(1, -1, rotation);
        robotDrive.arcadeDrive(-vertical, rotation);
    }
    
    public void setDefaultCommand(Command command){
        if(getDefaultCommand() != null){
            super.getDefaultCommand().cancel();
        }
        super.setDefaultCommand(command);
    }
    
}
