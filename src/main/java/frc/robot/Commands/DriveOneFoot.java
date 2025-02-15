package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DrivetrainSubsystem;

public class DriveOneFoot extends Command {
    private DrivetrainSubsystem drivetrainSubsystem;

    public DriveOneFoot(DrivetrainSubsystem drivetrainSubsystem) {
        this.drivetrainSubsystem = drivetrainSubsystem;
    }

    @Override
    public void initialize() {
        System.out.println("Driving 1 foot...");
    }

    @Override
    public void execute() {
        
    }
}
