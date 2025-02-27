// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.DrivetrainSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class AutoTurn extends Command {
  /** Creates a new DriveForwardCommand. */
  DrivetrainSubsystem drivetrainSubsystem;
  //DriveCommand driveCommand;
  final double speed;
  final double angle;

  /**
   * 
   * @param drivetrainSubsystem the drivetrain subsystem
   * @param speed the speed of the robot in decimal percentage
   * @param angle the angle in degrees
   */
  public AutoTurn(DrivetrainSubsystem drivetrainSubsystem, double speed, double angle) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.speed = speed;
    this.angle = angle;
    this.drivetrainSubsystem = drivetrainSubsystem;
    addRequirements(drivetrainSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drivetrainSubsystem.resetLeftEncoder();
    drivetrainSubsystem.zeroGyro();
  }
  

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrainSubsystem.Drive(speed,0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrainSubsystem.Drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(drivetrainSubsystem.angle()) >= angle;
  }
}
