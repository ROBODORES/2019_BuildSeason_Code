/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmInit extends Command {
  int step = 0;
  public ArmInit() {
    requires(Robot.m_arm);
    requires(Robot.m_wrist);
    requires(Robot.m_intakeArm);
    step = 0;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    step = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    switch (step) {
      case 0:
      Robot.m_intakeArm.down();
      if (Robot.m_intakeArm.hallEffectTriggered()) {
        Robot.m_intakeArm.stop();
        step = 1;
      }
      break;
      case 1:
      Robot.m_intakeArm.setSetpoint(-90.0);
      Robot.m_intakeArm.enable();
      if (Robot.m_intakeArm.getPosition() <= -86.0) {
        step = 2;
      }
      break;
      case 2:
      Robot.m_arm.setSetpoint(70.0);
      Robot.m_arm.enable();
      Robot.m_wrist.setSetpoint(90);
      Robot.m_wrist.enable();
      if (Robot.m_arm.getPosition() >= 66.0) {
        step = 3;
      }
      break;
      case 3:
      Robot.m_intakeArm.setSetpoint(10.0);
      Robot.m_intakeArm.enable();
      break;
    }
    /*if (Robot.m_arm.onTarget()) {
      Robot.m_wrist.setSetpoint(180);
      Robot.m_wrist.enable();
    }*/
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
