package com.edinarobotics.zephyr.parts;

import com.edinarobotics.utils.sensors.FilterDouble;
import com.edinarobotics.utils.sensors.SimpleAverageFilter;
import edu.wpi.first.wpilibj.*;

/**
 *The wrapper for the shooter components, contains the 2 jaguars driving the shooter
 * along with the rotating jaguar and the piston.
 */
public class ShooterComponents implements PIDSource, PIDOutput{
    public static final int ROTATE_RIGHT_SIGN = 1;
    public static final int ROTATE_LEFT_SIGN = -1;
    
    private Jaguar shooterLeftJaguar;
    private Jaguar shooterRightJaguar;
    private Jaguar shooterRotator;
    private Relay ballLoadPiston;
    private DigitalInput leftLimitSwitch;
    private DigitalInput rightLimitSwitch;
    private Encoder encoder;
    private FilterDouble filter;
    private PIDController pid;
    
    /*
     * Constructs shooterLeftJaguar, shooterRightJaguar, shooterRotator and ballLoadPiston
     * with leftJaguar, rightJaguar, rotator and piston respectively.
     */
    public ShooterComponents(int leftJaguar, int rightJaguar, int rotator, int piston,
                             int leftLimitSwitch, int rightLimitSwitch, int encoderA, int encoderB){
        shooterLeftJaguar = new Jaguar(leftJaguar);
        shooterRightJaguar = new Jaguar(rightJaguar);
        shooterRotator = new Jaguar(rotator);
        ballLoadPiston = new Relay(piston);
        this.leftLimitSwitch = new DigitalInput(leftLimitSwitch);
        this.rightLimitSwitch = new DigitalInput(rightLimitSwitch);
        this.encoder = new Encoder(encoderA, encoderB);
        encoder.setReverseDirection(true);
        encoder.setDistancePerPulse(1.0/180.0);
        encoder.start();
        filter = new SimpleAverageFilter(200);
        pid = new PIDController(0.3,0.8,0.5,this,this);
        pid.setTolerance(5);
        pid.enable();
    }
    /*
     * sets the shooterLeftJaguar to speed and shooterRightJaguar to -speed
     */
    public void setSpeed(double speed){
        pid.setSetpoint(speed);
    }
    
    public void pidWrite(double speed){
        shooterLeftJaguar.set(-speed);
        shooterRightJaguar.set(speed);
    }
    
    /*
     * Sets the rotator to speed
     */
    public void rotate(double speed){
        if((sgn(speed) == ROTATE_LEFT_SIGN && !(leftLimitSwitch.get())) || 
           (sgn(speed) == ROTATE_RIGHT_SIGN) && !(rightLimitSwitch.get())){
            //Limit switches are pressed, stop rotating.
            shooterRotator.set(0);
            return;
        }
        shooterRotator.set(speed);
    }
    /*
     * Sets the piston up if position is true, else it lowers it.
     */
    public void firePiston(boolean position){
        ballLoadPiston.set((position ? Relay.Value.kForward :Relay.Value.kReverse));
    }
    
    /**
     * Returns the encoder attached to the shooter.
     * @return The {@link Encoder} object that can be used to access the encoder
     * attached to the shooter.
     */
    public Encoder getEncoder(){
        return encoder;
    }
    
    public double getEncoderValue(){
        return filter.filter(encoder.getRate());
    }
    
    public double pidGet(){
        return getEncoderValue();
    }
    
    /**
     * A very simple implementation of the sgn function. Returns
     * {@code 1},{@code -1} or {@code 0} representing the sign of the number.
     * @param num The number whose sign will be returned.
     * @return {@code -1} if the number is negative,
     * {@code 0} if the number is 0, or {@code 1} if the number is positive.
     */
    private int sgn(double num){
        if(num>0){
            return 1;
        }
        else if(num<0){
            return -1;
        }
        return 0;
    }
}
