// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** Command to drive the robot in a straight line for 5 seconds*/
public class AutoShoot extends CommandBase {     //#region extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter m_subsystem;
  private final Intake m_subsystem1;
  private int counter = 0;
  
  private double m_speed = Constants.autoShootSd;
  /**
   * Creates a new Autonomous drive command.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoShoot(Shooter subsystem, Intake subsystem1) {
    m_subsystem = subsystem;
    m_subsystem1 = subsystem1;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_subsystem, m_subsystem1);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    counter = 0;
    m_subsystem.runShoot(m_speed);
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    counter += 1;
    if (counter == 40) m_subsystem1.runElevator();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem1.stopElevator();
    m_subsystem.stopShoot();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (counter > 120);
  }
}
