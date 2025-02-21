// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DrivetrainSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class AutoDrive extends Command {
  /** Creates a new DriveForwardCommand. */
  DrivetrainSubsystem drivetrainSubsystem;
  //DriveCommand driveCommand;
  final double speed;
  final double distance;

  public AutoDrive(DrivetrainSubsystem drivetrainSubsystem, double speed, double distance) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.speed = speed;
    this.distance = distance;
    this.drivetrainSubsystem = drivetrainSubsystem;
    addRequirements(drivetrainSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Driving" + distance + "inches");
    drivetrainSubsystem.resetLeftEncoder();    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Drive starting");
    drivetrainSubsystem.Drive(0,speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("Drive ended");
    drivetrainSubsystem.Drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("End conditions met");
    return Math.abs(drivetrainSubsystem.distanceLeft())>=distance;
  }
}
