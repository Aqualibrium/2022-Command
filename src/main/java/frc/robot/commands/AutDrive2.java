// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drive;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** Command to drive the robot in a straight line for 5 seconds*/
public class AutDrive2 extends CommandBase {     //#region extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drive m_subsystem;
  private double speed, steer;
  private int counter = 0;
  /**
   * Creates a new Autonomous drive command.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutDrive2(Drive subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    counter = 0;
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    speed = -0.7;
    steer = 0;
    m_subsystem.ArcadeDrive(speed, steer);
    counter += 1;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    speed = 0;
    steer = 0;
    m_subsystem.ArcadeDrive(speed, steer);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (counter > 110);
  }
}
