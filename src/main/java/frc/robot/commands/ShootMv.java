// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ShootTilt;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

/** A command to tilt the shooter to the desired posituin. */
public class ShootMv extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShootTilt m_subsystem;
  private int m_posn;
  private double m_speed = -1.0;

  /**
   * Creates a new shooter tilt command
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShootMv(ShootTilt subsystem, int posn) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    m_posn = posn;
    m_speed = -1.0;
  }
  
  /**
   * move shooter to requested position and set the shoot speed
   * @param subsystem
   * @param posn  - shooter position
   * @param speed - shooter speed
   */
  public ShootMv(ShootTilt subsystem, int posn, double speed){
    m_subsystem = subsystem;
    addRequirements(m_subsystem);
    m_posn = posn;
    m_speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.tiltMove(m_posn);
    if (m_speed > 0) RobotContainer.shootSpd = m_speed;
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.tiltStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_subsystem.isDone();
  }
}
