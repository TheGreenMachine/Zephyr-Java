package com.edinarobotics.zephyr;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;

/**
 *
 */
public class Components {
    // Port Numbers
    //Jaguars
    private static final int RIGHT_JAGUAR_PORT = 1;
    private static final int LEFT_JAGUAR_PORT = 2;
    private static final int SHOOTER_LEFT_JAGUAR_PORT = 3;
    private static final int SHOOTER_RIGHT_JAGUAR_PORT = 4;
    private static final int SHOOTER_ROTATOR_JAGUAR_PORT = 5;
    //Relays
    private static final int COMPRESSOR_SPIKE = 1;
    private static final int BALL_LOAD_PISTON_SPIKE = 2;
    private static final int SUPER_SHIFTERS_SPIKE = 3;
    private static final int BALL_COLL_ROTATE_SPIKE = 4;
    private static final int BALL_COLL_LIFT_SPIKE = 5;
    private static final int CONV_MOVE_SPIKE = 6;
    //Servos
    private static final int CAMERA_SERVO_VERTICAL = 7;
    private static final int CAMERA_SERVO_HORIZONTAL = 8;
    //Analog Input
    private static final int SONAR = 1;
    //Digital Input
    private static final int COMPRESSOR_PRESSURE_SENSOR = 1;
    
/******************************************************************************/
    
    // Robot Items
    private static Components instance;
    //RobotDrive
    public RobotDrive driveControl;
    //Jaguars
    public Jaguar leftJaguar;
    public Jaguar rightJaguar;
    public Jaguar shooterLeftJaguar;
    public Jaguar shooterRightJaguar;
    public Jaguar shooterRotator;
    //Relays
    public Relay ballLoadPiston;
    public Relay superShifters;
    public Relay collectorRotate;
    public Relay liftCollector;
    public Relay conveyorMove;
    //Servos
    public Servo cameraServoHorizontal;
    public Servo cameraServoVertical;
    //Analog Input
    public AnalogChannel sonar;      
    //Misc
    public Compressor compressor;
    public Jaguar shooterRotateJaguar;
    public DriverStationLCD textOutput;
    
    /**
     * Instantiates all components of the robot
     */
    private Components()
    {
        //Robot Drive
        driveControl = new RobotDrive(leftJaguar,rightJaguar);
        //Jaguars
        leftJaguar = new Jaguar(LEFT_JAGUAR_PORT);
        rightJaguar = new Jaguar(RIGHT_JAGUAR_PORT);
        shooterLeftJaguar = new Jaguar(SHOOTER_LEFT_JAGUAR_PORT);
        shooterRightJaguar = new Jaguar(SHOOTER_RIGHT_JAGUAR_PORT);
        shooterRotator = new Jaguar(SHOOTER_ROTATOR_JAGUAR_PORT);
        //Relays
        ballLoadPiston = new Relay(BALL_LOAD_PISTON_SPIKE);
        superShifters = new Relay(SUPER_SHIFTERS_SPIKE);
	collectorRotate = new Relay(BALL_COLL_ROTATE_SPIKE);
	liftCollector = new Relay(BALL_COLL_LIFT_SPIKE);
	conveyorMove = new Relay(CONV_MOVE_SPIKE);
        //Servos
        cameraServoHorizontal = new Servo(CAMERA_SERVO_HORIZONTAL);
        cameraServoVertical = new Servo(CAMERA_SERVO_VERTICAL);
        //Analog Inputs
        sonar = new AnalogChannel(SONAR);
        //Misc
        compressor  = new Compressor(COMPRESSOR_PRESSURE_SENSOR,COMPRESSOR_SPIKE);
        compressor.start();
        textOutput = DriverStationLCD.getInstance();
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
