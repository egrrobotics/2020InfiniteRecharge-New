/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.robot.commands.DelayTimed;
import frc.robot.commands.DriveTimed;
import frc.robot.commands.IntakeSpinTimed;
import frc.robot.commands.IntakeLiftTimed;

public class Enginauto extends CommandGroup {
  /**
   * Add your docs here.
   */
  public Enginauto() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.

    addSequential(new DelayTimed(3));
    addSequential(new IntakeSpinTimed(1.5, 1));
    addSequential(new IntakeLiftTimed(0.125, -0.4));
    addSequential(new IntakeSpinTimed(1, 1));
    addSequential(new DriveTimed(2.25, -0.7, -0.2));
  }
}
