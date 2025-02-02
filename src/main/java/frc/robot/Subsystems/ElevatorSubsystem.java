package frc.robot.Subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
    
    SparkMax elevator1 = new SparkMax(13, MotorType.kBrushless);
    SparkMax elevator2 = new SparkMax(14, MotorType.kBrushless);

    public ElevatorSubsystem() {}

    public void elevate(double speed) {
        elevator1.set(speed);
        elevator2.set(speed);
    }
}
