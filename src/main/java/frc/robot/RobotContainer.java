// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Commands.DriveCommand;
import frc.robot.Commands.ElevatorDown;
import frc.robot.Commands.ElevatorUp;
import frc.robot.Subsystems.DrivetrainSubsystem;
import frc.robot.Subsystems.ElevatorSubsystem;

public class RobotContainer {

  public static CommandXboxController controller = new CommandXboxController(0);
  private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
  

  private final DriveCommand driveCommand = new DriveCommand(drivetrainSubsystem);
  private final ElevatorUp elevatorUp = new ElevatorUp(elevatorSubsystem);
  private final ElevatorDown elevatorDown = new ElevatorDown(elevatorSubsystem);

  Trigger upTrigger = new Trigger(controller.povUp());
  Trigger downTrigger = new Trigger(controller.povDown());

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    drivetrainSubsystem.setDefaultCommand(driveCommand);
    upTrigger.whileTrue(elevatorUp);
    downTrigger.whileTrue(elevatorDown);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
