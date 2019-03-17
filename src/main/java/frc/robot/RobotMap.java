/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  // Joysticks
  public static final int leftStickPort = 0;
  public static final int rightStickPort = 1;
  public static final int sideStickPort = 2;
  public static final int toggleSwitchPort = 3;

  public static final int gearSwitcherButton = 2;

  //SparkMaxs
  public static final int leftMotor = 1;
  public static final int leftMotorFollower = 3;
  public static final int rightMotor = 0;
  public static final int rightMotorFollower = 2;

  //VictorSPXs
  public static final int armMotor = 10;
  public static final int wristMotor = 9;
  public static final int armIntakeMotor = 11;
  public static final int liftMotor = 6;
  public static final int intakeArmMotor = 8;
  public static final int intakeRollerMotor = 7;

  //Encoder Ports
  public static final int armSourceA = 0;
  public static final int armSourceB = 1;
  public static final int intakeSourceA = 3;
  public static final int intakeSourceB = 4;

  //Limit Switches
  public static final int intakeHallEffect = 8;
  public static final int liftHallEffect = 9;

  //Solenoids
  public static final int hatchSolenoid = 0;
  public static final int gearSwitcher = 3;

  public static final int pcm_id = 4;

  //LEDs
  public static final int blinkinPort = 0;
}
