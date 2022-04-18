// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.Constants;
//import com.revrobotics.CANSparkMax.*;
//import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ShootTilt extends SubsystemBase {
  private CANSparkMax tilt;
  public RelativeEncoder m_encoder;
  private SparkMaxPIDController m_pidController;
  DigitalInput tiltLimit = new DigitalInput(0);
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
  private boolean isZero = false; 
  private int m_posn;

  /** Creates a new Shooter tilt subsystem */
  public ShootTilt() {
  tilt = new CANSparkMax(Constants.tiltMotor, MotorType.kBrushless);
  tilt.restoreFactoryDefaults();
  m_encoder = tilt.getEncoder();
  m_pidController = tilt.getPIDController();
  tilt.setClosedLoopRampRate(1.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Tilt Posn", m_encoder.getPosition());
    SmartDashboard.putBoolean("Tilt Lim", !tiltLimit.get());
    SmartDashboard.putBoolean("Tilt Zero", isZero);
    if (!tiltLimit.get() && !isZero) {
      tilt.set(0.0);
      m_encoder.setPosition(-5.0);
      isZero = true;
      // set soft limits
      tilt.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
      tilt.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
      tilt.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 10);
      tilt.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, -52);
      // PID Coefficients
      m_pidController.setP(Constants.kTiltP);
      m_pidController.setI(Constants.kTiltI);
      m_pidController.setD(Constants.kTiltD);
      m_pidController.setIZone(0.0);
      m_pidController.setFF(0.0);
      m_pidController.setOutputRange(-0.8, 0.8);
    }
  }

  public void tiltDn(double speed){
    if (isZero) tilt.set(speed);
    else tilt.set(speed/2);
  }

  public void tiltUp(double speed){
    tilt.set(speed);
  }
  
  /**
   * stop shooter tilt motor
   */
  public void tiltStop(){
    //tilt.stopMotor(); 
    tilt.set(0.0);
  }
 
  /**
   * checks if tilt motor is at target. Works in position mode only
   * indeterminate if in percent output mode
   * @return true if motor position is a half turn or less from 
   * the requested position
   */
  public boolean isDone(){
    return (Math.abs(m_posn - m_encoder.getPosition()) < 0.5);
  }  

  /**
   * checks if calibration is complete
   * @return true if zeroed
   */
  public boolean isCal(){
    //return isZero;
    return true;
  }

  /**
   * Clears the zero on the tilt system
   */
  public void clrCal(){
    // disable soft limits
    tilt.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, false);
    tilt.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, false);
    isZero = false;
  }
  public void tiltMove(int posn){
    m_posn = posn;
    if (isZero){
      m_pidController.setReference(m_posn, CANSparkMax.ControlType.kPosition);
    }
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
