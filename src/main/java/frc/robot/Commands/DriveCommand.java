package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.Subsystems.DrivetrainSubsystem;

public class DriveCommand extends Command {
    
    DrivetrainSubsystem drivetrainSubsystem;

    public DriveCommand(DrivetrainSubsystem drivetrainSubsystem) {
        this.drivetrainSubsystem = drivetrainSubsystem;
        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Driving...");
    }

    @Override
    public void execute() {
        double forwardVelocity = RobotContainer.controller.getLeftY();
        double rotationVelocity = RobotContainer.controller.getRightX();
        drivetrainSubsystem.Drive(forwardVelocity, rotationVelocity);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
