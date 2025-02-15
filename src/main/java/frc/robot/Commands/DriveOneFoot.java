package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DrivetrainSubsystem;

public class DriveOneFoot extends Command {
    private DrivetrainSubsystem drivetrainSubsystem;
    private double speed = 0.5;

    public DriveOneFoot(DrivetrainSubsystem drivetrainSubsystem) {
        this.drivetrainSubsystem = drivetrainSubsystem;
    }

    @Override
    public void initialize() {
        System.out.println("Driving 1 foot...");
        drivetrainSubsystem.encoderReset();
    }

    @Override
    public void execute() {
        if (drivetrainSubsystem.distanceLeft() <= 12) {
            drivetrainSubsystem.arcadeDrive(speed, 0);
        }
    }

    @Override
    public boolean isFinished() {
        return drivetrainSubsystem.distanceLeft() > 12;
    }

    @Override
    public void end(boolean isFinished) {
        drivetrainSubsystem.Drive(0, 0);
    } 
}
