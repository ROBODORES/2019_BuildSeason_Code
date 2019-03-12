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
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Add your docs here.
 */
public class Lift extends Subsystem {
  VictorSPX liftMotor = null;
  DigitalInput liftHallEffect = null;
  boolean isUp = true;

  public Lift() {
    liftMotor = new VictorSPX(RobotMap.liftMotor);
    liftHallEffect = new DigitalInput(RobotMap.liftHallEffect);
  }

  public void down() {
    double speed = 0.5;
    if (isUp) {
      liftMotor.set(ControlMode.PercentOutput, -speed);
    }
  }

  public void up() {
    double speed = 0.5;
    if (!isUp) {
      liftMotor.set(ControlMode.PercentOutput, speed);
    }
  }

  public void stop() {
    liftMotor.set(ControlMode.PercentOutput, 0.0);
  }

  public boolean limitTriggered() { 
    return !liftHallEffect.get();
  }

  public boolean topLimitTriggered() {
    return !liftHallEffect.get();
  }

  public boolean bottomLimitTriggered() {
    return !liftHallEffect.get();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
