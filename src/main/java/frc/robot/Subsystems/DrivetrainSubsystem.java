package frc.robot.Subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.EncoderConfigAccessor;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.EncoderConstants;

public class DrivetrainSubsystem extends SubsystemBase {
    
    SparkMax left1, left2, right1, right2;
    DifferentialDrive diff;
    Encoder encoderLeft, encoderRight;

    public DrivetrainSubsystem() {
        left1 = new SparkMax(4,MotorType.kBrushed);
        left2 = new SparkMax(3, MotorType.kBrushed);
        right1 = new SparkMax(2, MotorType.kBrushed);
        right2 = new SparkMax(1, MotorType.kBrushed);
        
        encoderLeft = new Encoder(0, 1);
        encoderRight = new Encoder(1, 2);
        encoderLeft.setDistancePerPulse(EncoderConstants.distancePerPulse);
        encoderRight.setDistancePerPulse(EncoderConstants.distancePerPulse);

        SparkBaseConfig config = new SparkMaxConfig();
        config.inverted(true);

        left1.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        left2.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        diff = new DifferentialDrive(this::leftMotors, this::rightMotors);
    }

    /**
     * Controls the left motors of the robot
     * @param speed the speed of the left motors
     */
    public void leftMotors(double speed) {
        left1.set(speed);
        left2.set(speed);
    }

    /**
     * Controls the right motors of the robot
     * @param speed the speed of the right motors
     */
    public void rightMotors(double speed) {
        right1.set(speed);
        right2.set(speed);
    }

    public void Drive(double xSpeed, double rSpeed) {
        diff.arcadeDrive(xSpeed, rSpeed);
    }

    //Distance as calculated from the left-side encoder
    public double distanceLeft() {
        return encoderLeft.getDistance();
    }



    

    @Override
    public void periodic() {
        // This method is called periodically
        // This is where to put the encoder readings
        // SmartDashboard.putNumber("Distance Left", distanceLeft());
    }

    public void arcadeDrive(double speed, double rot) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'arcadeDrive'");
    }
}
