// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator2 extends SubsystemBase {
  private final SparkMax up1, up2;
  private final RelativeEncoder up1Encoder, up2Encoder;
  private double setpoint;
  private final double MAX_SETPOINT = 2; //3.14
  private PIDController pid = new PIDController(0.2, 0, 0);

  /** Creates a new Elevator2. */
  public Elevator2() {
    up1 = new SparkMax(13, MotorType.kBrushless);
    up2 = new SparkMax(14, MotorType.kBrushless);

    up1Encoder = up1.getEncoder();
    up2Encoder = up2.getEncoder();

    up1Encoder.setPosition(0);
    up2Encoder.setPosition(0);

    setpoint = 0;
  }

  public void moveMotors(double speed) {
    up1.set(-speed);
    up2.set(-speed);
  }

  public void setSetpoint(double setpoint){
    this.setpoint = setpoint;
  }

  public double getPosition(){
    return -up2Encoder.getPosition() * (1d/12d);
  }

  @Override
  public void periodic() {

    double speed = pid.calculate(getPosition(),setpoint);
    System.out.println("P:"+getPosition()+" S:"+speed);

    moveMotors(speed);
    // This method will be called once per scheduler run
  }
}
