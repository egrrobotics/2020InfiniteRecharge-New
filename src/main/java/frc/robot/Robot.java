/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.commands.AutoOnTheLine;
import frc.robot.commands.Enginauto;
import frc.robot.commands.MoveItMoveIt;
import frc.robot.commands.NopeAuto;
import frc.robot.commands.TestAuto;
import frc.robot.subsystems.ClimbLift;
import frc.robot.subsystems.ClimbWinch;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeLift;
import frc.robot.subsystems.IntakeSpin;
import frc.robot.subsystems.Wof;

// import frc.robot.util.WofMatchResult;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  // Color Sensor
  public static I2C.Port i2cPort = I2C.Port.kOnboard;
  public static ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  public static ColorMatch m_colorMatcher = new ColorMatch();
  public static Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  public static Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  public static Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  public static Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
  private String cColor;
  private Boolean isBlue, isRed, isGreen, isYellow;

  // Defaults
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;

  // Other Subsystems
  public static IntakeLift intakeLift = new IntakeLift();
  public static IntakeSpin intakeSpin = new IntakeSpin();
  public static ClimbLift climbLift = new ClimbLift();
  public static ClimbWinch climbWinch = new ClimbWinch();
  public static Wof wof = new Wof();
  public static DriveTrain driveTrain = new DriveTrain();

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    m_chooser.setDefaultOption("On the Line", new AutoOnTheLine());
    m_chooser.addOption("Enginauto", new Enginauto());
    m_chooser.addOption("Test", new TestAuto());
    m_chooser.addOption("Nope", new NopeAuto());
    m_chooser.addOption("Move It Move It!", new MoveItMoveIt());
    SmartDashboard.putData("Auto mode", m_chooser);

    // Color Sensor
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget); 
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    // Get color value.
    cColor = wof.getWheelColor();
    SmartDashboard.putString("Color", cColor);

    // Set bools.
    if (cColor == "Blue") { isBlue = true; isYellow = false; isRed = false; isGreen = false; }
    else if (cColor == "Yellow") { isBlue = false; isYellow = true; isRed = false; isGreen = false; }
    else if (cColor == "Red") { isBlue = false; isYellow = false; isRed = true; isGreen = false; }
    else if (cColor == "Green") { isBlue = false; isYellow = false; isRed = false; isGreen = true; }
    else { isBlue = false; isYellow = false; isRed = false; isGreen = false; }
    
    // Update dash.
    SmartDashboard.putBoolean("Blue", isBlue);
    SmartDashboard.putBoolean("Yellow", isYellow);
    SmartDashboard.putBoolean("Red", isRed);
    SmartDashboard.putBoolean("Green", isGreen);

  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
