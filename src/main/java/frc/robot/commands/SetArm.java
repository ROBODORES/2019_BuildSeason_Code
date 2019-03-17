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

  int mode;
  boolean hatchMode;
  boolean isOOTW = false; //is out of the way

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

    isOOTW = false;

    if (Robot.m_intakeArm.getPosition() < 0.0) {
      Robot.m_intakeArm.setSetpoint(-90.0);
      Robot.m_intakeArm.enable();
      if (Robot.m_intakeArm.getPosition() < -84.0) {
        isOOTW = true;
      }
    } else {
      isOOTW = true;
    }

    if (isOOTW) {
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
  }

  void intakeHeight() {
    if (hatchMode) { 
      Robot.m_arm.setSetpoint(5.0);
      Robot.m_wrist.setSetpoint(90.0);
    } else {
      Robot.m_arm.setSetpoint(-2.0);
      Robot.m_wrist.setSetpoint(160.0);
    }
    Robot.m_arm.enable();
    Robot.m_wrist.enable();
  }

  void levelOne() {
    if (hatchMode) { 
      Robot.m_arm.setSetpoint(5.0);
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
      Robot.m_arm.setSetpoint(70.0);
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
      Robot.m_wrist.setSetpoint(175.0);
    }
    Robot.m_arm.enable();
    Robot.m_wrist.enable();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double tolerance = 15;
    double error = Math.abs(Robot.m_arm.getSetpoint()-Robot.m_arm.getPosition());

    return error <= tolerance && isOOTW;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
