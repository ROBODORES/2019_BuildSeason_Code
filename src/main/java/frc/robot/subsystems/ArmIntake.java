/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.commands.SetArmIntake;;

/**
 * Add your docs here.
 */
public class ArmIntake extends Subsystem {
  VictorSPX armIntakeMotor = null;

  public ArmIntake() {
    armIntakeMotor = new VictorSPX(RobotMap.armIntakeMotor);
  }

  public void set(double speed) {
    armIntakeMotor.set(ControlMode.PercentOutput, speed);
  }

  public void intake() {
    double intakeSpeed = 0.8;
    set(-intakeSpeed);
  }

  public void expel() {
    double expelSpeed = 0.8;
    set(expelSpeed);
  }

  public void stop() {
    set(0.0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new SetArmIntake());
  }
}
