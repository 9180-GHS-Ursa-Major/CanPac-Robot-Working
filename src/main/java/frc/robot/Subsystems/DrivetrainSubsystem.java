package frc.robot.Subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {
    
    SparkMax left1, left2, right1, right2;
    DifferentialDrive diff;

    public DrivetrainSubsystem() {
        left1 = new SparkMax(4,MotorType.kBrushed);
        left2 = new SparkMax(3, MotorType.kBrushed);
        right1 = new SparkMax(2, MotorType.kBrushed);
        right2 = new SparkMax(1, MotorType.kBrushed);

        SparkBaseConfig config = new SparkMaxConfig();
        config.inverted(true);

        left1.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        left2.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        diff = new DifferentialDrive(this::leftMotors, this::rightMotors);
    }

    public void leftMotors(double speed) {
        left1.set(speed);
        left2.set(speed);
    }

    public void rightMotors(double speed) {
        right1.set(speed);
        right2.set(speed);
    }

    public void Drive(double xSpeed, double rSpeed) {
        diff.arcadeDrive(xSpeed, rSpeed);
    }

    @Override
    public void periodic() {
        // This method is called periodically
        // This is where to put the encoder readings
    }
}
