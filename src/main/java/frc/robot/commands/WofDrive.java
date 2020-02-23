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

public class WofDrive extends Command {
  public WofDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.wof);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    // Init.
    double liftPower = 0; double spinnerPower = 0;
    int currentPOV = Robot.m_oi.operator.getPOV();

    // Lift power.
    if (currentPOV == 315 || currentPOV == 0 || currentPOV == 45) {
      if (Robot.wof.getLiftPosition() < RobotMap.wofLiftLimit) {
        liftPower = -0.25;
      }
    } else if (currentPOV == 135 || currentPOV == 180 || currentPOV == 225) {
      if (Robot.wof.getLiftPosition() > 0) {
        liftPower = 0.25;
      }
    }

    // Spinner power.
    if (currentPOV == 225 || currentPOV == 270 || currentPOV == 315) {
      spinnerPower = 0.5;
    } else if (currentPOV == 45 || currentPOV == 90 || currentPOV == 135) {
      spinnerPower = -0.5;
    }
    
    // Set powers.
    Robot.wof.setLiftPower(liftPower);
    Robot.wof.setSpinnerPower(spinnerPower);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
