/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  VictorSPX leftA;
  VictorSPX leftB;
  VictorSPX rightA;
  VictorSPX rightB;

 
  public DriveTrain() {
    leftA = new VictorSPX(RobotMap.driveLeftA);
    leftB = new VictorSPX(RobotMap.driveLeftB);
    rightA = new VictorSPX(RobotMap.driveRightA);
    rightB = new VictorSPX(RobotMap.driveRightB); 
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
    SmartDashboard.putNumber("Left Drive", leftPower);
  }

  private void setRightPower(double rightPower) {
    rightPower = deadBand(rightPower);
    rightA.set(ControlMode.PercentOutput, rightPower);
    rightB.set(ControlMode.PercentOutput, rightPower);
    SmartDashboard.putNumber("Right Drive", rightPower);
  }

  public void setPower(double power) {
    setLeftPower(power);
    setRightPower(power);
  }
  
  public void setPower(double leftPower, double rightPower) {
    setLeftPower(leftPower);
    setRightPower(rightPower);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    this.setDefaultCommand(new ArcadeDrive());
  }
}
