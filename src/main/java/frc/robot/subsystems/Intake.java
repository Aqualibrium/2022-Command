// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.*;
import frc.robot.Constants;
import frc.robot.RobotContainer;


public class Intake extends SubsystemBase {
    TalonSRX conveyor = new TalonSRX(Constants.convey);
    TalonSRX elevator = new TalonSRX(Constants.elevate);
    TalonSRX intakeArm = new TalonSRX(Constants.intakeArm);
    VictorSPX intake = new VictorSPX(Constants.intake);
    
    //private double inSpd = 0.5, upSpd = 0.5;

  public Intake() {
    intakeArm.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * runs the intake motor and the conveyor
   */
  public void runConveyor(){
    conveyor.set(ControlMode.PercentOutput, RobotContainer.conveySpd);
  }

  /**
   * runs the conveyor only
   */
  public void runIntake(){
    intake.set(ControlMode.PercentOutput, Constants.intakeSpd);
  }

  /** 
   * runs the conveyor and intake in reverse
   */
  public void revConveyor() {
    conveyor.set(ControlMode.PercentOutput, -0.5);
    intake.set(ControlMode.PercentOutput, -0.5);
  }

  /**
   * stops the conveyor and the intake motor
   */
  public void stopConveyor(){
    conveyor.set(ControlMode.PercentOutput, 0.0);
    intake.set(ControlMode.PercentOutput, 0.0);
  }

  /**
   * runs only the conveyor motor
   * @param dir - true for forward, false for reverse
   */
  public void onlyConveyor(boolean dir){
    if (dir) conveyor.set(ControlMode.PercentOutput, Constants.intakeSpd);
    else     conveyor.set(ControlMode.PercentOutput, -0.5);

  }

  /**
   * runs the elevator
   */
  public void runElevator(){
    elevator.set(ControlMode.PercentOutput, RobotContainer.elevateSpd);
  }

  /** 
   * runs the elevator in reverse 
   */
  public void revElevator(){
    elevator.set(ControlMode.PercentOutput, -(RobotContainer.elevateSpd/2));
  }

  /**
   * stops the elevator motor
   */
 public void stopElevator(){
        elevator.set(ControlMode.PercentOutput, 0.0);
  }

  /**
   * Moves intake arm up to the limit switch
   */
  public void moveIntakeUp(){
    intakeArm.set(ControlMode.PercentOutput, Constants.inArmUp);
  }
  
  /**
   * Moves intake arm up to the limit switch
   */
  public void moveIntakeDn(){
    intakeArm.set(ControlMode.PercentOutput, Constants.inArmDn);
  }
  
  public void moveIntakeStop(){
    //intakeArm.set(ControlMode.PercentOutput, 0.0);
    intakeArm.set(ControlMode.Disabled, 0.0);
  }
  
  public boolean isArmUp(){
    if (intakeArm.isFwdLimitSwitchClosed() == 1){
      intakeArm.set(ControlMode.Disabled, 0.0);
      return true;
    }
    else return false;
  }

  public boolean isArmDn(){
    if (intakeArm.isRevLimitSwitchClosed() == 1){
            intakeArm.set(ControlMode.Disabled, 0.0);
      return true;
    }
    else return false;
  }
  
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
