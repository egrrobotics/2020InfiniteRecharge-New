/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeLiftDrive extends Command {

  double power;

  public IntakeLiftDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.intakeLift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  public double deadBand(double x){
    if (Math.abs(x)<.2){
      return 0;
    }else{
      return x;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double leftTrigger = deadBand(Robot.m_oi.operator.getRawAxis(2));
    double rightTrigger = deadBand(Robot.m_oi.operator.getRawAxis(3));
    if (leftTrigger > 0 && rightTrigger == 0) {
      Robot.intakeLift.setLiftPower(leftTrigger);
    } else if (rightTrigger > 0 && leftTrigger == 0) {
      Robot.intakeLift.setLiftPower(-rightTrigger);
    } else {
      Robot.intakeLift.setLiftPower(0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.intakeLift.setLiftPower(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.intakeLift.setLiftPower(0);
  }
}
