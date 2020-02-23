/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {

  TalonSRX lift;
  TalonSRX winch;

  public Climber() {
    lift = new TalonSRX(RobotMap.climberLift);
    winch = new TalonSRX(RobotMap.climberWinch);
  }

  public void setLiftPower(double power) {
    lift.set(ControlMode.PercentOutput, power);
  }

  public double getLiftPosition() {
    return lift.getSelectedSensorPosition();
  }

  public void setWinchPower(double power) {
    winch.set(ControlMode.PercentOutput, power);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}