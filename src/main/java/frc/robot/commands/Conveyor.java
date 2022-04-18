// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that runs the intake */
public class Conveyor extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Intake m_subsystem;
  private final boolean m_dir;
  /**
   * Creates a new command to run the intake
   *
   * @param subsystem The subsystem used by this command.
   */
  public Conveyor(Intake subsystem, boolean dir) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    m_dir = dir;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (m_dir) m_subsystem.onlyConveyor(true);
    else m_subsystem.onlyConveyor(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.stopConveyor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
