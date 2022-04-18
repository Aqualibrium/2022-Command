// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ShootTilt;
//import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class AutoTilt extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShootTilt m_subsystem;

  /**
   * Command to zero the shooter tilt. Once the tilt is zeroed, can use
   * position control to move the shooter
   *
   * @param subsystem - shoot-tilt subsystem
   */
  public AutoTilt(ShootTilt subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_subsystem.clrCal();
    
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //m_subsystem.tiltMove(Constants.autotilt);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_subsystem.isCal();
  }
}
