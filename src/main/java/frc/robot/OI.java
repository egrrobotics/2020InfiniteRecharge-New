/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ClimberLift;
import frc.robot.commands.ClimberWinch;
import frc.robot.commands.IntakeLift;
import frc.robot.commands.IntakeSpinner;
import frc.robot.commands.WofColorTest;
import frc.robot.commands.WofLift;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  // Driver XBox controller.
  public Joystick driver = new Joystick(0);
  public Button driverButtonA = new JoystickButton(driver, 1);
  public Button driverButtonB = new JoystickButton(driver, 2);
  public Button driverButtonX = new JoystickButton(driver, 3);
  public Button driverButtonY = new JoystickButton(driver, 4);
  public Button driverButtonLeftBumper = new JoystickButton(driver, 5);
  public Button driverButtonRightBumper = new JoystickButton(driver, 6);
  public Button driverButtonBack = new JoystickButton(driver, 7);
  public Button driverButtonStart = new JoystickButton(driver, 8);
  public Button driverButtonLeftAxisPress = new JoystickButton(driver, 9);
  public Button driverButtonRightAxisPress = new JoystickButton(driver, 10);

  // Operator XBox controller.
  public Joystick operator = new Joystick(1);
  public Button operatorButtonA = new JoystickButton(operator, 1);
  public Button operatorButtonB = new JoystickButton(operator, 2);
  public Button operatorButtonX = new JoystickButton(operator, 3);
  public Button operatorButtonY = new JoystickButton(operator, 4);
  public Button operatorButtonLeftBumper = new JoystickButton(operator, 5);
  public Button operatorButtonRightBumper = new JoystickButton(operator, 6);
  public Button operatorButtonBack = new JoystickButton(operator, 7);
  public Button operatorButtonStart = new JoystickButton(operator, 8);
  public Button operatorButtonLeftAxisPress = new JoystickButton(operator, 9);
  public Button operatorButtonRightAxisPress = new JoystickButton(operator, 10);

  public OI() {

    // Intake spinner in/out.
    operatorButtonA.whileHeld(new IntakeSpinner(-1));
    operatorButtonB.whileHeld(new IntakeSpinner(1));

    // Intake spinner up/down.
    operatorButtonY.whileHeld(new IntakeLift(0.3));
    operatorButtonX.whileHeld(new IntakeLift(-0.3));

    // Climber hook lift up/down.
    operatorButtonRightBumper.whileHeld(new ClimberLift(0.45));
    operatorButtonLeftBumper.whileHeld(new ClimberLift(-0.2));

    // Climber winch (only goes in!)
    operatorButtonBack.whileHeld(new ClimberWinch(0.5));
    operatorButtonStart.whileHeld(new ClimberWinch(1));

    // Wof lift up/down.
    driverButtonA.whileHeld(new WofLift(0.25));
    driverButtonB.whileHeld(new WofLift(-0.25));

    // Color match testing.
    driverButtonStart.whileHeld(new WofColorTest());

  }
  
}
