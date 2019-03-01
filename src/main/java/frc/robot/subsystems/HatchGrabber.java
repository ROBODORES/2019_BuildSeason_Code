/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.SetHatchGrabber;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Add your docs here.
 */
public class HatchGrabber extends Subsystem {
  Solenoid hatchGrabberSolenoid = null;

  public HatchGrabber() {
    hatchGrabberSolenoid = new Solenoid(RobotMap.pcm_id, RobotMap.hatchSolenoid);
  }

  public void set(boolean state) {
    hatchGrabberSolenoid.set(state);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new SetHatchGrabber());
  }
}
