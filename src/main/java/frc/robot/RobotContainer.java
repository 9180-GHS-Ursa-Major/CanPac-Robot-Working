// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Commands.DriveCommand;
import frc.robot.Commands.ElevatorDown;
import frc.robot.Commands.ElevatorUp;
import frc.robot.Commands.IntakeCommand;
import frc.robot.Commands.OuttakeCommand;
import frc.robot.Subsystems.DrivetrainSubsystem;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.OuttakeSubsystem;

public class RobotContainer {

  
  public static CommandXboxController controller = new CommandXboxController(0);

  //This is where we add all the subsystems to RobotContainer.
  //Keep them here to keep it clean
  private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
  private final OuttakeSubsystem outtakeSubsystem = new OuttakeSubsystem();
  

  //This is where we add all the commands to RobotContainer
  //Keep them here to keep it clean
  private final DriveCommand driveCommand = new DriveCommand(drivetrainSubsystem);
  private final ElevatorUp elevatorUp = new ElevatorUp(elevatorSubsystem);
  private final ElevatorDown elevatorDown = new ElevatorDown(elevatorSubsystem);
  private final OuttakeCommand outtakeCommand = new OuttakeCommand(outtakeSubsystem);
  private final IntakeCommand intakeCommand = new IntakeCommand(outtakeSubsystem);

  SendableChooser<Command> chooser = new SendableChooser<>();
  

  Trigger upTrigger = new Trigger(controller.povUp());
  Trigger downTrigger = new Trigger(controller.povDown());
  Trigger outTrigger = new Trigger(controller.rightTrigger());
  Trigger inTrigger = new Trigger(controller.leftTrigger());

  public RobotContainer() {
    configureBindings();
    chooser.setDefaultOption("Command1", driveCommand);
  }

  private void configureBindings() {
    // Start of the chooser
    drivetrainSubsystem.setDefaultCommand(driveCommand);
    upTrigger.whileTrue(elevatorUp);
    downTrigger.whileTrue(elevatorDown);
    outTrigger.whileTrue(outtakeCommand);
    inTrigger.whileTrue(intakeCommand);

   
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}
