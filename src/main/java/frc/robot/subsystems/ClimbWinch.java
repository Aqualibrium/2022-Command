// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.Constants;
import com.revrobotics.*;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
//import com.revrobotics.SparkMaxLimitSwitch;
//import edu.wpi.first.wpilibj.DigitalInput;

public class ClimbWinch extends SubsystemBase {
  private CANSparkMax winch;
  private RelativeEncoder m_encoder;
  private VictorSPX arm;
  private SparkMaxLimitSwitch m_forwardLimit;
  private SparkMaxLimitSwitch m_reverseLimit;
  DigitalInput tiltLimit = new DigitalInput(1);
  /** Creates a new Climber Winch subsystem. */
  public ClimbWinch() {
    winch = new CANSparkMax(Constants.winch, MotorType.kBrushless);
    winch.setIdleMode(IdleMode.kBrake);
    m_encoder = winch.getEncoder();
    arm = new VictorSPX(Constants.ClimbArm);
    winch.restoreFactoryDefaults();
    m_forwardLimit =
      winch.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
    m_reverseLimit =
      winch.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
    m_forwardLimit.enableLimitSwitch(true);
    m_reverseLimit.enableLimitSwitch(true);
  }

  @Override
   public void periodic() {
    // This method will be called once per scheduler run
     SmartDashboard.putBoolean("Climb in", m_forwardLimit.isPressed());
     SmartDashboard.putBoolean("Climb out", m_reverseLimit.isPressed());
     if (m_forwardLimit.isPressed()) m_encoder.setPosition(0.0);
     SmartDashboard.putNumber("Climber Posn", m_encoder.getPosition());
  }

  public void winchIn() {
    winch.set(0.5);
  }

  public void winchOut() {
    winch.set(-0.5);
  }

  public void winchStop(){
    winch.set(0.0);
  }
  
  public void armF(){
    arm.set(ControlMode.PercentOutput, 0.4);
  }

  public void armR(){
    arm.set(ControlMode.PercentOutput, -0.4);
  }

  public void armS(){
    arm.set(ControlMode.Disabled, 0.0);
  }
  public boolean armDone(){
    return !tiltLimit.get();
  }
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
