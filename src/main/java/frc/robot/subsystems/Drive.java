// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.*;

import frc.robot.Constants;
import frc.robot.RobotContainer;
//import frc.robot.RobotContainer;

public class Drive extends SubsystemBase {
  private CANSparkMax leftM;
  private CANSparkMax leftS;

  private CANSparkMax rightM;
  private CANSparkMax rightS;
  double speed, steer, lDrive, rDrive, lSpeed, rSpeed;

  public Drive() {
    leftM = new CANSparkMax(Constants.lMaster, MotorType.kBrushless);
    leftS = new CANSparkMax(Constants.lSlave1, MotorType.kBrushless);
    rightM = new CANSparkMax(Constants.rMaster, MotorType.kBrushless);
    rightS = new CANSparkMax(Constants.rSlave1, MotorType.kBrushless);

    leftM.restoreFactoryDefaults();
    leftS.restoreFactoryDefaults();
    rightM.restoreFactoryDefaults();
    rightS.restoreFactoryDefaults();
    leftM.setIdleMode(IdleMode.kCoast);
    leftS.setIdleMode(IdleMode.kCoast);

    // left side inverted
    leftM.setInverted(true);
    leftS.setInverted(true);
    rightS.setIdleMode(IdleMode.kCoast);
    rightM.setIdleMode(IdleMode.kCoast);
    
    //set open loop ramp rate - may have to adjust lower
    leftM.setOpenLoopRampRate(0.6);
    leftS.setOpenLoopRampRate(0.6);
    rightM.setOpenLoopRampRate(0.6);
    rightS.setOpenLoopRampRate(0.6);

    // slaves follow the master
    leftS.follow(leftM);
    rightS.follow(rightM);

    leftM.burnFlash();
    leftS.burnFlash();
    rightM.burnFlash();
    rightS.burnFlash();
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  //public void TankDrive(double lDrive, double rDrive, double speed){
    //lSpeed = speed * speed * speed * 0.8 * Constants.maxSpd; 
    //rSpeed = speed * speed * speed * 0.8 * Constants.maxSpd;
    //lDrive = lSpeed;
    //rDrive = rSpeed;

    //leftM.set(lDrive);
    //rightM.set(rDrive);
    

  //}
  public void ArcadeDrive(double speed, double steer) {  
    
  
    speed = speed * speed * speed * 0.8 * Constants.maxSpd;
    steer = steer * steer * steer * 0.5 * Constants.maxSpd;


    lDrive = speed + steer;
    rDrive = speed - steer;

    // nitro button and turtle button
    if (RobotContainer.DriverButtonA()){
      lDrive = lDrive * 2;
      rDrive = rDrive * 2;
    } else if (RobotContainer.DriverButtonB()) {
      lDrive = lDrive * 0.5;
      rDrive = rDrive * 0.5;
    }
   
    
    leftM.set(lDrive);
    rightM.set(rDrive);
    rightS.set(rDrive);
    leftS.set(lDrive);
    SmartDashboard.putNumber("drive speed", lDrive);
    SmartDashboard.putNumber("drive steer", rDrive);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
