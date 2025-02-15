// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import ca.frc6390.athena.sensors.limitswitch.GenericLimitSwitch;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
  private final SparkMax up1, up2;
  private final RelativeEncoder up1Encoder, up2Encoder;
  private double setpoint, speed = 0;
  private boolean homeElevator = false, override = false;
  private final double MAX_SETPOINT = 3.14; // <---  :O its meee
  private final double MAX_OUTPUT = 0.5;
  private PIDController pid = new PIDController(0.85, 0, 0.1);
  private GenericLimitSwitch lowerLimit;

  /** Creates a new Elevator2. */
  public ElevatorSubsystem() {
    up1 = new SparkMax(13, MotorType.kBrushless);
    up2 = new SparkMax(14, MotorType.kBrushless);

    up1Encoder = up1.getEncoder();
    up2Encoder = up2.getEncoder();

    lowerLimit = new GenericLimitSwitch(0); //DIO port on rio

    lowerLimit.getTrigger().whileTrue(new InstantCommand(() -> {
      homeElevator = false;
      zeroEncoders();
         stopElevator();
     }));
    // what does this doo

    setpoint = 0;
  }

  public void setOverride(boolean override) {
    this.override = override;
  }

  public void setSpeed(double speed){
    this.speed = speed;
  }

  public void moveMotors(double speed) {
    // if (lowerLimit.isPressed() && speed < 0){
    //   speed = 0;
    //   homeElevator = false;
    // }
    // someone pls write what this does ^

    speed = Math.copySign(Math.min(MAX_OUTPUT, Math.abs(speed)), speed);

    up1.set(-speed);
    up2.set(-speed);
  }

  public void setHomeElevator(boolean home){
    homeElevator = home;
  }

  public void zeroEncoders(){
    up1Encoder.setPosition(0);
    up2Encoder.setPosition(0);
  }

  public void stopElevator(){
    up1.stopMotor();
    up2.stopMotor();
  }

  public void setSetpoint(double setpoint){
    this.setpoint = Math.min(setpoint, MAX_SETPOINT);
  }

  public double getPosition(){
    return -up2Encoder.getPosition() * (1d/12d);
  }

  @Override
  public void periodic() {
    

    speed = homeElevator ? -0.05 : pid.calculate(getPosition(),setpoint);
    if(!override){
      moveMotors(speed);
    }

    SmartDashboard.putNumber("Elevator Position", getPosition());
    SmartDashboard.putNumber("Elevator Setpoint", setpoint);
    // This method will be called once per scheduler run
  }
}
