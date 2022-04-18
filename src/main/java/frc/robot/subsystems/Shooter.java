// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Shooter extends SubsystemBase {
  VictorSPX lShoot = new VictorSPX(Constants.shootL);
  VictorSPX rShoot = new VictorSPX(Constants.shootR);
  private double speed;
  /** Creates a new system to drive the shooter */
  public Shooter() {
    //SmartDashboard.putNumber("Shooter Speed", speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    speed = RobotContainer.shootSpd;
    SmartDashboard.putNumber("Shooter Speed", speed);
  }
  
  public void runShoot(){
    lShoot.set(ControlMode.PercentOutput, speed);
    rShoot.set(ControlMode.PercentOutput, speed);
  }
  public void runShoot(double speed){
    lShoot.set(ControlMode.PercentOutput, speed);
    rShoot.set(ControlMode.PercentOutput, speed);
 
  }
  public void ShootSpeedUp(){
    speed = RobotContainer.shootSpd;
  }
  public void ShootSpeedDown(){
    speed = RobotContainer.shootSpd;
  }
  public void reverseShooter(){    
    lShoot.set(ControlMode.PercentOutput, -0.4);
    rShoot.set(ControlMode.PercentOutput, -0.4);
  }
  
  public void stopShoot(){
    lShoot.set(ControlMode.PercentOutput, 0.0);
    rShoot.set(ControlMode.PercentOutput, 0.0);
  }
  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
