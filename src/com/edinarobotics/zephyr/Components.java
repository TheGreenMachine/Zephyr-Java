/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.Zephyr;

import com.edinarobotics.Zephyr.subsystems.Collector;
import com.edinarobotics.Zephyr.subsystems.Drivetrain;
import com.edinarobotics.Zephyr.subsystems.Shooter;
import edu.wpi.first.wpilibj.Compressor;

/**
 *
 * @author GreenMachine
 */
public class Components {
    
    public Drivetrain drivetrain;
    public Shooter shooter;
    public Collector collector;
    public Compressor compressor;
    
    private static Components instance;
    
    //Drivetrain Values
        private static final int RIGHT_MOTOR = 2;
        private static final int LEFT_MOTOR = 1;
        private static final int BACK_RIGHT_MOTOR = 7;
        private static final int BACK_LEFT_MOTOR = 8;
    
    //Talon Values 
        //Shooter
            private static final int SHOOTER_RIGHT_TALON = 3;
            private static final int SHOOTER_LEFT_TALON = 4;
            private static final int SHOOTER_RELAY_CHANNEL = 1;
        //Collector
            private static final int COLLECTOR_TALON = 6;
        //Conveyor
            private static final int CONVEYOR_TALON = 5;
        
    //Solenoid Values
        private static final int SHOOTER_FOWARD_SOLENOID = 1;
        private static final int SHOOTER_REVERSE_SOLENOID = 2;
        
    //Encoder Ports
        private static final int SHOOTER_ENC_A = 1;
        private static final int SHOOTER_ENC_B = 2;
                
    //Compressor Values
        private static final int COMPRESSOR_SPIKE = 2;
        private static final int PRESSURE_SWITCH = 3;
        
        
    public Components(){
        //Instantiate
        shooter = new Shooter(SHOOTER_LEFT_TALON, SHOOTER_RIGHT_TALON, SHOOTER_RELAY_CHANNEL, SHOOTER_FOWARD_SOLENOID, SHOOTER_REVERSE_SOLENOID, SHOOTER_ENC_A, SHOOTER_ENC_B);
        drivetrain = new Drivetrain(LEFT_MOTOR, RIGHT_MOTOR, BACK_LEFT_MOTOR, BACK_RIGHT_MOTOR);
        collector = new Collector(CONVEYOR_TALON, COLLECTOR_TALON);
        compressor = new Compressor(PRESSURE_SWITCH,COMPRESSOR_SPIKE);
        compressor.start();
    }
    
    public static Components getInstance(){
        if (instance == null){
            instance = new Components();
        }
        return instance;
    }
    
}
