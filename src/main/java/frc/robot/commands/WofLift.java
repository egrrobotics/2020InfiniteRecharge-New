/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class WofLift extends Command {

  double power;

  public WofLift(double percentage) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.wof);
    power = percentage;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if ((power > 0) && (Robot.wof.getLiftPosition() < RobotMap.wofLiftLimit)) {
      Robot.wof.setLiftPower(power);
    } else if ((power < 0) && (Robot.wof.getLiftPosition() > 0)) {
      Robot.wof.setLiftPower(power);
    } else {
      Robot.wof.setLiftPower(0);
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.wof.setLiftPower(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.wof.setLiftPower(0);
  }
}
