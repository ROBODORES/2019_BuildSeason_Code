/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetArm extends Command {
  public static int intakeLevelHeight = 0;
  public static int LevelOneHeight = 1;
  public static int LevelTwoHeight = 2;
  public static int LevelThreeHeight = 3;

  public static double stowedPosition = 10.0;
  public static double stowedThreshold = 6.0;
  public static double outPosition = -70;
  public static double outThreshold = -60;
  public static double outOfTheWayPosition = -80;
  public static double outOfTheWayThreshold = -75;

  public static double clearThreshold = 66.0;
  public static double clearPosition = 70.0;

  int mode;
  boolean hatchMode;

  public SetArm(int mode) {
    // Use requires() here to declare subsystem dependencies
    this.mode = mode;
    hatchMode = false;

    requires(Robot.m_arm);
    requires(Robot.m_wrist);
    requires(Robot.m_intakeArm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    hatchMode = Robot.m_oi.toggleSwitch.getRawButton(1);

    switch (mode) {
      case 0:
      intakeHeight();
      break;
      case 1:
      levelOne();
      break;
      case 2:
      levelTwo();
      break;
      case 3:
      levelThree();
      break;
    }
  }

  void intakeHeight() {
    if (hatchMode) { 
      if (Robot.m_intakeArm.getPosition() >= stowedThreshold) {
        Robot.m_arm.setSetpoint(5.0);
        Robot.m_wrist.setSetpoint(90.0);
      } else {
        if (Robot.m_intakeArm.getPosition() <= outOfTheWayThreshold) {
          Robot.m_arm.setSetpoint(clearPosition);
          Robot.m_wrist.setSetpoint(90.0);
          if (Robot.m_arm.getPosition() >= clearThreshold) {
            Robot.m_intakeArm.setSetpoint(stowedPosition);
          }
        } else {
          Robot.m_intakeArm.setSetpoint(outOfTheWayPosition);
        }
      }
    } else {
      if (Robot.m_intakeArm.getPosition() <= outOfTheWayThreshold) {
        Robot.m_arm.setSetpoint(-5.0);
        System.out.println(Robot.m_arm.getPosition());
        if (Robot.m_arm.getPosition() <= 12.0) {
          Robot.m_wrist.setSetpoint(180.0);
          Robot.m_intakeArm.setSetpoint(outPosition);
        }
      } else if (Robot.m_intakeArm.getPosition() <= outThreshold) {
        Robot.m_arm.setSetpoint(0.0);
      } else {
        Robot.m_arm.setSetpoint(clearPosition);
        Robot.m_wrist.setSetpoint(270.0);
        if (Robot.m_arm.getPosition() >= clearThreshold) {
          Robot.m_intakeArm.setSetpoint(outOfTheWayPosition);
        }
      }
    }
    Robot.m_arm.enable();
    Robot.m_wrist.enable();
    Robot.m_intakeArm.enable();
  }

  void levelOne() {
    if (hatchMode) { 
      Robot.m_arm.setSetpoint(10.0);
      Robot.m_wrist.setSetpoint(90.0);
    } else {
      Robot.m_arm.setSetpoint(20.0);
      Robot.m_wrist.setSetpoint(200.0);
    }
    Robot.m_arm.enable();
    Robot.m_wrist.enable();
  }

  void levelTwo() {
    if (hatchMode) { 
      Robot.m_arm.setSetpoint(60.0);
      Robot.m_wrist.setSetpoint(70.0);
    } else {
      Robot.m_arm.setSetpoint(75.0);
      Robot.m_wrist.setSetpoint(185.0);
    }
    Robot.m_arm.enable();
    Robot.m_wrist.enable();
  }

  void levelThree() {
    if (hatchMode) { 
      Robot.m_arm.setSetpoint(95.0);
      Robot.m_wrist.setSetpoint(60.0);
    } else {
      Robot.m_arm.setSetpoint(108.0);
      Robot.m_wrist.setSetpoint(195.0);
    }
    Robot.m_arm.enable();
    Robot.m_wrist.enable();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_arm.disable();
    Robot.m_wrist.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
