package frc.robot.Subsystems;

import ca.frc6390.athena.sensors.camera.limelight.LimelightConfig;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LimelightConstants;

public class LimelightSubsystem extends SubsystemBase {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    
    
    

    // Initializes a new LimelightSubsystem
    public LimelightSubsystem() {}

    public double distance() {
        double targetOffsetAngle_Vertical = ty.getDouble(LimelightConstants.vOffset);
        double angleToGoalDegrees = LimelightConstants.vLLOffset + targetOffsetAngle_Vertical;
        double angleToGoalRadians = angleToGoalDegrees * (Math.PI / 180.0);
        double distanceToGoalIn = (LimelightConstants.tHeightIn - LimelightConstants.lLHeightIn) / Math.tan(angleToGoalRadians);
        return distanceToGoalIn;
    }
    public void periodic() {
        SmartDashboard.putNumber("Limelight Distance", distance());
    }
}
