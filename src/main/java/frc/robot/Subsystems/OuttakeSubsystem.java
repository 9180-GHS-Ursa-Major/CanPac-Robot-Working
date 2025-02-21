package frc.robot.Subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OuttakeSubsystem extends SubsystemBase {
    
    SparkMax outtakeMotor1 = new SparkMax(11, MotorType.kBrushless);
    SparkMax outtakeMotor2 = new SparkMax(12, MotorType.kBrushless);


    public OuttakeSubsystem() {
    }

    public void setMotors(double speed) {
        setMotors(-speed, speed);
    }

    public void setMotors(double leftSpeed, double rightSpeed) {
        outtakeMotor1.set(leftSpeed);
        outtakeMotor2.set(rightSpeed);
    }

    public void stop() {
        outtakeMotor1.stopMotor();
        outtakeMotor2.stopMotor();
    }
}
