/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.Zephyr.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author GreenMachine
 */
public class Collector extends Subsystem1816{

    private Talon collectorTalon;
    private Talon conveyorTalon;
    
    private boolean collectorOn;
    private boolean collectorReverse;

    private static final double COLLECTOR_SPEED = -1.0; 
    private static final double CONVEYOR_SPEED = -1.0; 
    
    public Collector(int collectorPort, int conveyorPort){
        this.collectorTalon = new Talon(collectorPort);
        this.conveyorTalon = new Talon(conveyorPort);
    }
    
    public void setCollectorOn(boolean on, boolean rev){
        this.collectorOn = on;
        this.collectorReverse = rev;
        update();
    }
    
    public void update() {
        if (collectorOn){
            collectorTalon.set(COLLECTOR_SPEED);
            conveyorTalon.set(CONVEYOR_SPEED);
        } 
        else if (collectorReverse) {
            collectorTalon.set(-COLLECTOR_SPEED);
            conveyorTalon.set(-CONVEYOR_SPEED);
        }
        else {
            collectorTalon.set(0.0);
            conveyorTalon.set(0.0);
        }
        
    }
    
}
