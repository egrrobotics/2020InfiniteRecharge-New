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
public class Wof extends Subsystem {

  TalonSRX lift;
  TalonSRX spinner;

  public Wof() {
    lift = new TalonSRX(RobotMap.wofLift);
    spinner = new TalonSRX(RobotMap.wofSpinner);
  }

  public void setLiftPower(double power) {
    lift.set(ControlMode.PercentOutput, power);
  }

  public void setSpinnerPower(double power) {
    spinner.set(ControlMode.PercentOutput, power);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}