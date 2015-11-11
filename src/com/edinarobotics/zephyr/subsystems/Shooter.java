/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.Zephyr.subsystems;

import com.edinarobotics.utils.subsystems.Subsystem1816;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 * @author GreenMachine
 */
public class Shooter extends Subsystem1816{
    private Talon leftTalon, rightTalon;
    private Encoder encoder;
    private boolean on;
    private Relay.Value value;
    private Relay relay;
    private DoubleSolenoid loader;
    private DoubleSolenoid.Value loaderValue;
    private double target = 0.0;
    
    public Shooter(int leftTalon, int rightTalon, int relayChannel, int soleniodFowardChannel, int soleniodReverseChannel, int encoderChannelA, int encoderChannelB){
        this.leftTalon = new Talon(leftTalon);
        this.rightTalon = new Talon(rightTalon);
        this.relay = new Relay(relayChannel);
        this.loader = new DoubleSolenoid(soleniodFowardChannel, soleniodReverseChannel);
        this.value = Relay.Value.kOff;
        this.loaderValue = DoubleSolenoid.Value.kForward;
        this.encoder = new Encoder(encoderChannelA, encoderChannelB);
        this.encoder.start();
    }
    
    public void setOn(boolean on){
        this.on = on;
        update();
    }
    
    public void setRotation(Relay.Value value) {
        this.value = value;
        update();
    }
    
    public boolean isOn(){
        return on;
    }
    
    public void setLoader(DoubleSolenoid.Value value){
        this.loaderValue = value;
        update();
    }
    
    protected void setShooter(double setspeed)
    {
        leftTalon.set(setspeed);
        rightTalon.set(-setspeed);
    }
    
    protected void setSpeed(double setpoint){
        if (encoder.getRate() > setpoint){
            setShooter(0.0);
        }
        else 
        {
            setShooter(1.0);
        }
    }
    
    public double getSetpoint()
    {
        return target;
    }
    
    public void setSetpoint(double set)
    {
        target = set;
    }
    
    public double getEncRate()
    {
        return encoder.getRate();
    }
    
    public void changeSetpoint(double change)
    {
        target = target + change;
    }

    public void update() {
        if (on){
            setSpeed(target);
        }
        else {
            leftTalon.set(0.0);
            rightTalon.set(0.0);
        }
        
        relay.set(value);
        loader.set(loaderValue);
    }
}
