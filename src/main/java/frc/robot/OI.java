/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.awt.Button;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commands.SetArm;
import frc.robot.commands.SetHatchGrabber;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  public static Joystick leftStick = new Joystick(RobotMap.leftStickPort);
  public static Joystick rightStick = new Joystick(RobotMap.rightStickPort);
  public static Joystick sideStick = new Joystick(RobotMap.sideStickPort);
  public static Joystick toggleSwitch = new Joystick(RobotMap.toggleSwitchPort);

  JoystickButton intakeHeight = new JoystickButton(rightStick, 7);
  JoystickButton intakeBall = new JoystickButton(rightStick, 1);

  JoystickButton level1 = new JoystickButton(sideStick, 2);
  JoystickButton level2 = new JoystickButton(sideStick, 6);
  JoystickButton level3 = new JoystickButton(sideStick, 4);

  JoystickButton grabHatch = new JoystickButton(sideStick, 5);
  JoystickButton releaseHatch = new JoystickButton(sideStick, 3);

  public OI() {
    intakeHeight.whenPressed(new SetArm(0));
    level1.whenPressed(new SetArm(1));
    level2.whenPressed(new SetArm(2));
    level3.whenPressed(new SetArm(3));
    grabHatch.whenPressed(new SetHatchGrabber(true));
    releaseHatch.whenPressed(new SetHatchGrabber(false));
  }
}
