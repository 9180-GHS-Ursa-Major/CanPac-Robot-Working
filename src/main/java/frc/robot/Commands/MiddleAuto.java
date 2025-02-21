package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DrivetrainSubsystem;

public class MiddleAuto extends Command {
    private DrivetrainSubsystem drivetrainSubsystem;
    private double speed;
    private double distance;
    //7ft. 4 in.

    public MiddleAuto(DrivetrainSubsystem drivetrainSubsystem, double speed, double distance) {
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.speed = speed;
        this.distance = distance;
    }

    @Override
    public void initialize() {
        System.out.println("Driving 1 foot...");
        drivetrainSubsystem.encoderReset();
        System.out.println("Command starting");
    }
// turn to 88
    @Override
    public void execute() {
        if (Math.abs(drivetrainSubsystem.distanceLeft()) <= distance) {
            drivetrainSubsystem.Drive(0, speed);
        }
    }

    @Override
    public boolean isFinished() {
        return Math.abs(drivetrainSubsystem.distanceLeft()) >= distance;
    }

    @Override
    public void end(boolean isFinished) {
        System.out.println("Command finished");
        drivetrainSubsystem.Drive(0, 0);
    } 
}
