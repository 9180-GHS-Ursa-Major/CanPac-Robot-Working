package frc.robot.Subsystems;

import java.nio.file.ClosedDirectoryStreamException;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
    
    SparkMax elevator1 = new SparkMax(13, MotorType.kBrushless);
    SparkMax elevator2 = new SparkMax(14, MotorType.kBrushless);
    private final RelativeEncoder eEncoder;
    private SparkClosedLoopController closedLoopController;
    private SparkMaxConfig motorConfig;
    double kPEP = 0;
    double kPEI = 0;
    double kPED = 0;
    double kVEP = 0;
    double kVEI = 0;
    double kVED = 0;
    double kMinOut = 0;
    double kMaxOut = 0;



    public ElevatorSubsystem() {
        eEncoder = elevator1.getEncoder();
        closedLoopController = elevator1.getClosedLoopController();
        motorConfig = new SparkMaxConfig();
        
        /*
         * Configuring conversion factors for the encoder
         * Since we're using the built-in neo encoder, no conversion has to be done
         */
        motorConfig.encoder
        .positionConversionFactor(1)
        .velocityConversionFactor(1);

        motorConfig.closedLoop
        .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
        //Set PID values for position control.  We don't pass a closed loop through; it will automatically set to 0.
        .p(kPEP)
        .i(kPEI)
        .d(kPED)
        .outputRange(-1, 1)
        //Set PID values for velocity control in slot 1
        .p(kVEP, ClosedLoopSlot.kSlot1)
        .i(kVEI, ClosedLoopSlot.kSlot1)
        .d(kVED, ClosedLoopSlot.kSlot1)
        .velocityFF(kVED, ClosedLoopSlot.kSlot1)
        .outputRange(-1, 1, ClosedLoopSlot.kSlot1);
        
        elevator1.configure(motorConfig, ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);

        SmartDashboard.setDefaultNumber("Target Position", 0);
        SmartDashboard.setDefaultNumber("Target Velocity", 0);
        SmartDashboard.setDefaultBoolean("Control Mode", false);
        SmartDashboard.setDefaultBoolean("Reset Encoder", false);
    }

    public void elevate(double speed) {
        elevator1.set(speed);
        elevator2.set(speed);
    }

    
    public void teleopPeriodic() {
        if (SmartDashboard.getBoolean("Control Mode", false)) {
      /*
       * Get the target velocity from SmartDashboard and set it as the setpoint
       * for the closed loop controller.
       */
      double targetVelocity = SmartDashboard.getNumber("Target Velocity", 0);
      closedLoopController.setReference(targetVelocity, ControlType.kVelocity, ClosedLoopSlot.kSlot1);
    } else {
      /*
       * Get the target position from SmartDashboard and set it as the setpoint
       * for the closed loop controller.
       */
      double targetPosition = SmartDashboard.getNumber("Target Position", 0);
      closedLoopController.setReference(targetPosition, ControlType.kPosition, ClosedLoopSlot.kSlot0);
    }
    }

    public void periodic() {
        // Display encoder position and velocity
        SmartDashboard.putNumber("Actual Position", eEncoder.getPosition());
        SmartDashboard.putNumber("Actual Velocity", eEncoder.getVelocity());

        if (SmartDashboard.getBoolean("Reset Encoder", false)) {
            SmartDashboard.putBoolean("Reset Encoder", false);
            // Reset the encoder position to 0
            eEncoder.setPosition(0);
    }
  }
    }

