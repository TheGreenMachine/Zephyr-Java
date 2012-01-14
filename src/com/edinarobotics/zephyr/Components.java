/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.zephyr;

import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 */
public class Components {
    //PORT NUMBERS HERE!
    private static int LEFT_JAGUAR_PORT = 1;
    private static int RIGHT_JAGUAR_PORT = 2;
    
    private static Components instance;
    public Jaguar leftJaguar;
    public Jaguar rightJaguar;
    
    private Components(){
        leftJaguar = new Jaguar(LEFT_JAGUAR_PORT);
        rightJaguar = new Jaguar(RIGHT_JAGUAR_PORT);
    }
    
    public static Components getInstance(){
        if(instance == null){
            instance = new Components();
        }
        return instance;
    }
}
