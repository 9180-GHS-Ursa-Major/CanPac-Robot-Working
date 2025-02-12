// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
  private final SparkMax up1, up2;
  private final RelativeEncoder up1Encoder, up2Encoder;
  private double setpoint, speed = 0;
  private boolean override = false;
  private final double MAX_SETPOINT = 3.14;
  private PIDController pid = new PIDController(0.85, 0, 0);

  /** Creates a new Elevator2. */
  public ElevatorSubsystem() {
    up1 = new SparkMax(13, MotorType.kBrushless);
    up2 = new SparkMax(14, MotorType.kBrushless);

    up1Encoder = up1.getEncoder();
    up2Encoder = up2.getEncoder();

    up1Encoder.setPosition(0);
    up2Encoder.setPosition(0);

    setpoint = 0;
  }

  public void setSpeed(double speed){
    this.speed = speed;
  }

  private void moveMotors(double speed) {
    up1.set(-speed);
    up2.set(-speed);
  }

  public void setOverride(boolean override){
    this.override = override;
    if (override == false) {
      up2Encoder.setPosition(0);
    }
  }

  public boolean getOverride(){
    return override;
  }

  public void setSetpoint(double setpoint){
    this.setpoint = Math.min(setpoint, MAX_SETPOINT);
  }

  public double getPosition(){
    return -up2Encoder.getPosition() * (1d/12d);
  }

  @Override
  public void periodic() {

    
    if(override){
      moveMotors(speed);
    }else {
      speed = pid.calculate(getPosition(),setpoint);
      moveMotors(speed);
    }

    SmartDashboard.putNumber("Elevator Position", getPosition());
    SmartDashboard.putNumber("Elevator Setpoint", setpoint);
    // This method will be called once per scheduler run
  }
}
