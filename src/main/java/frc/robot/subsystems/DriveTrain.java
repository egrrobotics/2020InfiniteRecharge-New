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
import frc.robot.commands.ArcadeDrive;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  TalonSRX leftA;
  TalonSRX leftB;
  TalonSRX rightA;
  TalonSRX rightB;
 
  public DriveTrain() {
    leftA = new TalonSRX(RobotMap.driveLeftA);
    leftB = new TalonSRX(RobotMap.driveLeftB);
    rightA = new TalonSRX(RobotMap.driveRightA);
    rightB = new TalonSRX(RobotMap.driveRightB); 
    rightA.setInverted(true);
    rightB.setInverted(true);
  }

  private double deadBand(double power) {
    if(Math.abs(power) < 0.1) {
      return 0;
    }
    return power;
  }

  private void setLeftPower(double leftPower) {
    leftPower = deadBand(leftPower);
    leftA.set(ControlMode.PercentOutput, leftPower);
    leftB.set(ControlMode.PercentOutput, leftPower);
  }

  private void setRightPower(double rightPower) {
    rightPower = deadBand(rightPower);
    rightA.set(ControlMode.PercentOutput, rightPower);
    rightB.set(ControlMode.PercentOutput, rightPower);
  }

  public void setPower(double power) {
    setLeftPower(power);
    setRightPower(power);
  }
  
  public void setPower(double leftPower, double rightPower) {
    setLeftPower(leftPower);
    setRightPower(rightPower);
  }

  public int getLeftEncoder() {
    return leftA.getSelectedSensorPosition();
  }

  public int getRightEncoder() {
    return rightA.getSelectedSensorPosition();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    this.setDefaultCommand(new ArcadeDrive());
  }
}
