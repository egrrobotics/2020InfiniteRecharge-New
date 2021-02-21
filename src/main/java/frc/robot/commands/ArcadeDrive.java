/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.LinearFilter;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArcadeDrive extends Command {
  double throttle;
  double wheel; 
  double leftPower;
  double rightPower;
  LinearFilter filterThrottle;
  public ArcadeDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    filterThrottle = LinearFilter.singlePoleIIR(0.05, 0.02);
  }

  public double deadBand(double x){
    if (Math.abs(x)<.2){
      return 0;
    }else{
      return x;
    }
  }
  
  public double clip(double x){

    if (Robot.m_oi.driverButtonLeftBumper.get()) {

      if (RobotController.getBatteryVoltage() > 10.5) {
        return x;
      }

    } else {

      if (RobotController.getBatteryVoltage() > 10.5) {
        if (x>.825) return .825;
        if (x<-.825) return -.825;
      } else {
        if (x>.75) return .75;
        if (x<-.75) return -.75;
      }

    }

    return x;

  }

  public double slowMode(double x) {
    if (Robot.m_oi.driverButtonRightBumper.get()) {
      return 0.25 * x;
    } else {
      return x;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    throttle = filterThrottle.calculate(deadBand(-slowMode(Robot.m_oi.driver.getRawAxis(1))));
    wheel = 0.9 * deadBand(slowMode(Robot.m_oi.driver.getRawAxis(4)));
    leftPower = deadBand(clip(throttle + wheel));
    rightPower = deadBand(clip(throttle - wheel));
    Robot.driveTrain.setPower(leftPower, rightPower);
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
    filterThrottle.reset();
  }
}
