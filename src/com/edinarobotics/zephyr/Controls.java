/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edinarobotics.Zephyr;

import com.edinarobotics.Zephyr.commands.CollectorCommand;
import com.edinarobotics.Zephyr.commands.SetShooterLoaderCommand;
import com.edinarobotics.Zephyr.commands.SetShooterStateCommand;
import com.edinarobotics.Zephyr.commands.SetShooterRotateCommand;
import com.edinarobotics.Zephyr.commands.ChangeShooterSpeedCommand;
import com.edinarobotics.utils.gamepad.FilteredGamepad;
import com.edinarobotics.utils.gamepad.Gamepad;
import com.edinarobotics.utils.gamepad.gamepadfilters.DeadzoneFilter;
import com.edinarobotics.utils.gamepad.gamepadfilters.GamepadFilterSet;
import com.edinarobotics.utils.gamepad.gamepadfilters.PowerFilter;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import java.util.Vector;

/**
 *
 * @author GreenMachine
 */
public class Controls {
    private static Controls instance;

    public final Gamepad gamepad;
    
    private Controls(){
        Vector driveGamepadFilters = new Vector();
        driveGamepadFilters.addElement(new DeadzoneFilter(0.1));
        driveGamepadFilters.addElement(new PowerFilter(2));
        GamepadFilterSet driveGamepadFilterSet = new GamepadFilterSet(driveGamepadFilters);
        
        gamepad = new FilteredGamepad(1, driveGamepadFilterSet);
        
        gamepad.leftBumper().whileHeld(new CollectorCommand(true, false));
        gamepad.leftBumper().whenReleased(new CollectorCommand(false, false));
        
        gamepad.leftTrigger().whileHeld(new CollectorCommand(false, true)); //Turning right
        gamepad.leftTrigger().whenReleased(new CollectorCommand(false, false)); //Turning right
        
        gamepad.dPadLeft().whileHeld(new SetShooterRotateCommand(Relay.Value.kForward));
        gamepad.dPadRight().whileHeld(new SetShooterRotateCommand(Relay.Value.kReverse));
        gamepad.dPadLeft().whenReleased(new SetShooterRotateCommand(Relay.Value.kOff));
        gamepad.dPadRight().whenReleased(new SetShooterRotateCommand(Relay.Value.kOff));
        
    
        gamepad.diamondUp().whileHeld(new ChangeShooterSpeedCommand(50));
        gamepad.diamondDown().whileHeld(new ChangeShooterSpeedCommand(-50));
        
        gamepad.diamondLeft().whenPressed(new SetShooterStateCommand(false));
        gamepad.diamondRight().whenPressed(new SetShooterStateCommand(true));
 
        gamepad.rightBumper().whileHeld(new SetShooterLoaderCommand(DoubleSolenoid.Value.kReverse));
        gamepad.rightBumper().whenReleased(new SetShooterLoaderCommand(DoubleSolenoid.Value.kForward));
        
    }
    
    public static Controls getInstance() {
        if (instance == null) {
            instance = new Controls();
        }
        return instance;
    }
}
