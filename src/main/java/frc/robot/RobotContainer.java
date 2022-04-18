// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drive m_drive = new Drive();
  private final Shooter m_shooter = new Shooter();
  private final Intake m_intake = new Intake();
  private final ShootTilt m_tilt = new ShootTilt();
  private final ClimbWinch m_winch = new ClimbWinch();
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final JoystickDrive JoystickDrive = new JoystickDrive(m_drive);
  //private final SequentialCommandGroup m_autoCommand = new SequentialCommandGroup(new AutDrive1(m_drive),
  //       new AutoShoot(m_shooter, m_intake));
  private final SequentialCommandGroup m_autoCommand = 
  new SequentialCommandGroup(    // Autocommand
          new ShootMv(m_tilt, Constants.tilt1),  
          new AutoShoot(m_shooter, m_intake),  
           new ParallelCommandGroup(new AutDrive1(m_drive), 
              new AutoArm(m_intake)),
       // Move robot
         //new ShootMv(m_tilt, Constants.autotilt),        // aim shooter
        //new SetShootSpeed(66.0),

         new AutoIntake(m_intake), // get new ball
         new ShootMv(m_tilt, Constants.autotilt),
         new SetShootSpeed(Constants.autoShootSd),
         new AutoShoot(m_shooter, m_intake),   // shoot again
         new AutDrive2(m_drive));         // drive 2 feet
  
  
  // motor speed variables
  public static double shootSpd = 0.71;
  public static double conveySpd = 0.7;
  public static double elevateSpd = 0.45;
  public static int shootAngle = 0;

    //Driver gamepad
  final static Joystick drvStick = new Joystick(Constants.drvStick);
  final static Joystick LdrvStick = new Joystick(Constants.drvStick);
  final JoystickButton drA = new JoystickButton(drvStick, Constants.drA);
  final JoystickButton drB = new JoystickButton(drvStick, Constants.drB);
  final JoystickButton drY = new JoystickButton(drvStick, Constants.drY);
  final JoystickButton drX = new JoystickButton(drvStick, Constants.drX);
  //final JoystickButton drLT = new JoystickButton(drvStick, Constants.drY);
  //final JoystickButton drRT = new JoystickButton(drvStick, Constants.drX);
  final JoystickButton drLB = new JoystickButton(drvStick, Constants.drLB);
  final JoystickButton drRB = new JoystickButton(drvStick, Constants.drRB);
  final JoystickButton drLT = new JoystickButton(drvStick, Constants.drLT);
  final JoystickButton drRT = new JoystickButton(drvStick, Constants.drRT);
  // Operator gamepad
  final static Joystick opStick = new Joystick(Constants.opStick);
  final JoystickButton op1 = new JoystickButton(opStick, Constants.op1);
  final JoystickButton op2 = new JoystickButton(opStick, Constants.op2);
  final JoystickButton op3 = new JoystickButton(opStick, Constants.op3);
  final JoystickButton op4 = new JoystickButton(opStick, Constants.op4);
  final JoystickButton op5 = new JoystickButton(opStick, Constants.op5);
  final JoystickButton op6 = new JoystickButton(opStick, Constants.op6);
  final JoystickButton op7 = new JoystickButton(opStick, Constants.op7);
  final JoystickButton op8 = new JoystickButton(opStick, Constants.op8);
  final JoystickButton op9 = new JoystickButton(opStick, Constants.op9);
  final JoystickButton op10 = new JoystickButton(opStick, Constants.op10);
  final JoystickButton op11 = new JoystickButton(opStick, Constants.op11);
  final JoystickButton op12 = new JoystickButton(opStick, Constants.op12);
  final JoystickButton op13 = new JoystickButton(opStick, Constants.op13);
  final JoystickButton op14 = new JoystickButton(opStick, Constants.op14);
  final JoystickButton op15 = new JoystickButton(opStick, Constants.op15);
  final JoystickButton op16 = new JoystickButton(opStick, Constants.op16);
  final JoystickButton op17 = new JoystickButton(opStick, Constants.op17);
  final JoystickButton op18 = new JoystickButton(opStick, Constants.op18);
  final JoystickButton op19 = new JoystickButton(opStick, Constants.op19);
  final JoystickButton op20 = new JoystickButton(opStick, Constants.op20);
  final JoystickButton op21 = new JoystickButton(opStick, Constants.op21);
  final JoystickButton op22 = new JoystickButton(opStick, Constants.op22);
  final JoystickButton op23 = new JoystickButton(opStick, Constants.op23);
  final JoystickButton op24 = new JoystickButton(opStick, Constants.op24);
  final JoystickButton op25 = new JoystickButton(opStick, Constants.op25);
  final JoystickButton op26 = new JoystickButton(opStick, Constants.op26);  // thumbwheel forward
  final JoystickButton op27 = new JoystickButton(opStick, Constants.op27);  // thumbwheel reverse
  final JoystickButton op28 = new JoystickButton(opStick, Constants.op28);  // thumbwheel press

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_drive.setDefaultCommand(JoystickDrive);
    UsbCamera camera = CameraServer.startAutomaticCapture("Right", 0);
    UsbCamera cameraL = CameraServer.startAutomaticCapture("Left", 1);
    camera.setResolution(320, 200);
    cameraL.setResolution(320, 200);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /*
     These commands are place holders for when we have commands
    */
    //drA.whileHeld(new IntakeIn(m_intake));    // nitro
    //drB.whileHeld(new ElevatorReverse(m_intake));  // turtle button
    //drX.whenPressed(new TiltShooter(m_tilt));  // not assigned
    drRB.whileHeld(new ShootElevate(m_shooter, m_intake)); // shoot
    drRB.whenReleased(new IntakeRelease(m_intake));
    drLB.whileHeld(new IntakeIn(m_intake));         // intake
    
    // Operator commands
    op1.whileHeld(new ShootUp(m_tilt));   // tilt up (reverse of current)
    op6.whileHeld(new ShootDn(m_tilt));   // tilt down (reverse of current)
    op2.whileHeld(new IntakeArmUp(m_intake));  // drive intake arm up
    op7.whileHeld(new IntakeArmDn(m_intake));  // drive intake arm down
    op3.whileHeld(new Conveyor(m_intake, true));  // conveyor only drive in
    op8.whileHeld(new Conveyor(m_intake, false));   // conveyor only drive out
    op4.whileHeld(new ArmForward(m_winch));
    op9.whileHeld(new ArmBack(m_winch));
    op5.whileHeld(new WinchOut(m_winch));
    op10.whileHeld(new WinchIn(m_winch));

    op11.whenPressed(new ShootMv(m_tilt, Constants.tilt1));
    op11.whenPressed(new SetShootSpeed(0.4)); // Shooter home rev
    op12.whenPressed(new ShootMv(m_tilt, Constants.tilt1)); // forward 5 ft
    op12.whenPressed(new SetShootSpeed(0.63));
    op13.whenPressed(new ShootMv(m_tilt, Constants.tilt2)); // forward 10 ft
    op13.whenPressed(new SetShootSpeed(0.73));
    op14.whenPressed(new ShootMv(m_tilt, Constants.tilt3)); // forward 15 ft
    op15.whenPressed(new ShootMv(m_tilt, Constants.tilt4));  // forward 20 ft
    op16.whenPressed(new ShootMv(m_tilt, Constants.tilt5)); // range 20 ft
    op17.whenPressed(new IntakeArmUp(m_intake));
    op19.whenPressed(new IntakeArmDn(m_intake));
    //op19.whileHeld(new RunShwoopShooter(m_shoot, 0.8));
    //op19.whileHeld(new RunShwoopShooter(m_shoot));
    //op20.whileHeld(new LimelightAim(m_drive, m_limelight));
    op21.whenPressed(new AutoTilt(m_tilt));
    //op22.whenPressed(new IncShoot(m_shoot, 0.03));
    //op23.whenPressed(new IncShoot(m_shoot, -0.03));
    //op24.whenPressed(new StopArm(s_arm));   // arm stops right away
    op22.whileHeld(new ShooterRev(m_shooter));    // reverse shooter
    op23.whileHeld(new ElevatorReverse(m_intake));      // reverse elevator
    op24.whileHeld(new IntakeOut(m_intake));       //reverse intake
    op26.whenPressed(new ShootSpeedUp());
    op27.whenPressed(new ShootSpeedDown());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }

  public static double DriverY() {   // speed
    return drvStick.getRawAxis(1);
  }

  public static double DriverX() {   //Steering
    return drvStick.getRawAxis(4);
  }

  // A button -> turbo
  public static boolean DriverButtonA(){
    return drvStick.getRawButton(1);
  }
 
  
  // A button -> turtle
  public static boolean DriverButtonB(){
    return drvStick.getRawButton(2);
  }

  public static double DriverLT() {    // slow turn left
    return drvStick.getRawAxis(1);
  }

  public static double DriverRT(){
    return drvStick.getRawAxis(2);
  }

  //public static double ArmY() {    // operator joystick
   // return opStick.getRawAxis(1);
  //}
}
