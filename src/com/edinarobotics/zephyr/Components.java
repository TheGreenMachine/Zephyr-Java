package com.edinarobotics.zephyr;

import com.edinarobotics.utils.sensors.FIRFilter;
import com.edinarobotics.zephyr.parts.CollectorComponents;
import com.edinarobotics.zephyr.parts.CypressComponents;
import com.edinarobotics.zephyr.parts.DrivingComponents;
import com.edinarobotics.zephyr.parts.ShooterComponents;
import com.edinarobotics.zephyr.parts.SonarComponents;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Gyro;

/**
 *
 */
public class Components {
    // Port Numbers
    //Jaguars
    private static final int RIGHT_JAGUAR_PORT = 1;
    private static final int LEFT_JAGUAR_PORT = 2;
    private static final int SHOOTER_LEFT_JAGUAR_NUMBER = 10;
    private static final int SHOOTER_RIGHT_JAGUAR_NUMBER = 11;
    private static final int SHOOTER_ROTATOR_JAGUAR_PORT = 5;
    private static final int BALL_COLL_LIFT_JAGUAR = 6;
    private static final int SHOOTER_TAP_SIZE = 10;
    //Relays
    private static final int COMPRESSOR_SPIKE = 1;
    private static final int BALL_LOAD_PISTON_SPIKE = 2;
    private static final int SUPER_SHIFTERS_SPIKE = 3;
    private static final int BALL_COLL_ROTATE_SPIKE = 4;
    private static final int CONV_MOVE_SPIKE = 6;
    //Servos
    private static final int CAMERA_SERVO_VERTICAL = 7;
    private static final int CAMERA_SERVO_HORIZONTAL = 8;
    //Analog Input
    private static final int SONAR = 1;
    private static final int GYRO = 2;
    //Digital Input
    private static final int COMPRESSOR_PRESSURE_SENSOR = 1;
    private static final int SHOOTER_LEFT_LIMIT_SWITCH = 2;
    private static final int SHOOTER_RIGHT_LIMIT_SWITCH = 3;
    //Cypress
    public static final int CYPRESS_AUTO_SWITCH_ONE_IN = 1;
    public static final int CYPRESS_AUTO_SWITCH_TWO_IN = 2;
    
/******************************************************************************/
    
    // Robot 
    private static Components instance;
    public ShooterComponents shooter;
    public DrivingComponents drive;
    public CollectorComponents collector;
    public SonarComponents sonar;
    public Servo cameraServoVertical;
    public Gyro gyro;
     
    //Misc
    public Compressor compressor;
    public DriverStationLCD textOutput;
    public CypressComponents cypress;
    public Timer timer;
    
    /**
     * Instantiates all components of the robot
     */
    private Components()
    {
        //Relays
        shooter = new ShooterComponents(SHOOTER_LEFT_JAGUAR_NUMBER, SHOOTER_RIGHT_JAGUAR_NUMBER,
                                            SHOOTER_ROTATOR_JAGUAR_PORT, BALL_LOAD_PISTON_SPIKE,
                                            SHOOTER_LEFT_LIMIT_SWITCH, SHOOTER_RIGHT_LIMIT_SWITCH,
                                            SHOOTER_TAP_SIZE);
        drive = new DrivingComponents(LEFT_JAGUAR_PORT, RIGHT_JAGUAR_PORT, SUPER_SHIFTERS_SPIKE);
	collector = new CollectorComponents(BALL_COLL_LIFT_JAGUAR, BALL_COLL_ROTATE_SPIKE,
                                                CONV_MOVE_SPIKE);
        //Servos
        cameraServoVertical = new Servo(CAMERA_SERVO_VERTICAL);
        //Analog Inputs
        sonar = new SonarComponents(SONAR, FIRFilter.autoWeightedFilter(20));
        gyro = new Gyro(GYRO);
        //Misc
        compressor = new Compressor(COMPRESSOR_PRESSURE_SENSOR,COMPRESSOR_SPIKE);
        compressor.start();
        textOutput = DriverStationLCD.getInstance();
        cypress = new CypressComponents();
        timer = new Timer();
    }

    /**
     * Creates an instance of the Components class
     */
    public static Components getInstance(){
        if(instance == null){
            instance = new Components();
        }
        return instance;
    }
}
