/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*
   __ __   ___     ______    ______
  / // /  |__ \   / ____/   / ____/
 / // /_  __/ /  /___ \    /___ \  
/__  __/ / __/  ____/ /   ____/ /  
  /_/   /____/ /_____/   /_____/   
  _____   _                ____            _                   _                             
 |_   _| | |__     ___    |  _ \    ___   | |__     ___     __| |   ___    _ __    ___   ___ 
   | |   | '_ \   / _ \   | |_) |  / _ \  | '_ \   / _ \   / _` |  / _ \  | '__|  / _ \ / __|
   | |   | | | | |  __/   |  _ <  | (_) | | |_) | | (_) | | (_| | | (_) | | |    |  __/ \__ \
   |_|   |_| |_|  \___|   |_| \_\  \___/  |_.__/   \___/   \__,_|  \___/  |_|     \___| |___/
                                                                                             
*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.cameraserver.CameraServer;

//Subsystems
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ArmPID;
import frc.robot.subsystems.WristPID;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.HatchGrabber;
import frc.robot.subsystems.LEDs;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.IntakeArmPID;
//import frc.robot.subsystems.JeVois;

//Commands
import frc.robot.commands.ExampleCommand;
//import frc.robot.commands.Drive;

/**
 * The VM is configured to automasbfkjsdhlifdalfdhsftically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static DriveTrain m_driveTrain = null;
  public static ArmPID m_arm = null;
  public static WristPID m_wrist = null;
  public static Intake m_intake = null;
  public static HatchGrabber m_hatchGrabber = null;
  public static LEDs m_LEDs = null;
  public static Lift m_lift = null;
  public static IntakeArmPID m_intakeArm = null;
  //public static JeVois m_jeVois = null;

  public static OI m_oi = null;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_driveTrain = new DriveTrain();
    m_arm = new ArmPID();
    m_wrist = new WristPID();
    m_intake = new Intake();
    m_hatchGrabber = new HatchGrabber();
    m_LEDs = new LEDs();
    m_lift = new Lift();
    m_intakeArm = new IntakeArmPID();
    //m_jeVois = new JeVois();

    m_oi = new OI();

    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);

    CameraServer.getInstance().startAutomaticCapture();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
