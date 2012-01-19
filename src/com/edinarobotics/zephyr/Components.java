/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.zephyr;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 */
public class Components {
    //PORT NUMBERS HERE!
    private static int LEFT_JAGUAR_PORT = 1;
    private static int RIGHT_JAGUAR_PORT = 2;
    private static int SHOOTER_JAGUAR_PORT = 3;
    
    private static Components instance;
    public Jaguar leftJaguar;
    public Jaguar rightJaguar;
    public Jaguar shooterJaguar;
    public RobotDrive driveControl;
    
    private Components(){
        leftJaguar = new Jaguar(LEFT_JAGUAR_PORT);
        rightJaguar = new Jaguar(RIGHT_JAGUAR_PORT);
        shooterJaguar = new Jaguar(SHOOTER_JAGUAR_PORT);
        driveControl = new RobotDrive(leftJaguar,rightJaguar);
    }
    
    public static Components getInstance(){
        if(instance == null){
            instance = new Components();
        }
        return instance;
    }
}
