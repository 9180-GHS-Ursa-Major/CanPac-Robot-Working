// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import ca.frc6390.athena.controllers.DebouncedController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Commands.DriveCommand;
// import frc.robot.Commands.ElevatorDown;
// import frc.robot.Commands.ElevatorUp;
import frc.robot.Subsystems.DrivetrainSubsystem;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.OuttakeSubsystem;

public class RobotContainer {

  
  public static DebouncedController controller = new DebouncedController(0);

  //This is where we add all the subsystems to RobotContainer.
  //Keep them here to keep it clean
  private final ElevatorSubsystem elevator = new ElevatorSubsystem();
  private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
  // private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();s
  private final OuttakeSubsystem outtakeSubsystem = new OuttakeSubsystem();
  

  //This is where we add all the commands to RobotContainer
  //Keep them here to keep it clean
  private final DriveCommand driveCommand = new DriveCommand(drivetrainSubsystem);
  // private final ElevatorUp elevatorUp = new ElevatorUp(elevatorSubsystem);
  // private final ElevatorDown elevatorDown = new ElevatorDown(elevator, 1);
  

  SendableChooser<Command> chooser = new SendableChooser<>();
  
  public RobotContainer() {
    configureBindings();
    chooser.setDefaultOption("Command1", driveCommand);
  }

  private void configureBindings() {
    // Start of the chooser
   drivetrainSubsystem.setDefaultCommand(driveCommand);
    // upTrigger.whileTrue(elevatorUp);
    controller.back.onTrue(new InstantCommand(() -> elevator.setOverride(!elevator.getOverride())));
    controller.start.onTrue(new InstantCommand(() -> elevator.setSpeed(-0.05)));

    controller.a.onTrue(new InstantCommand(() -> elevator.setSetpoint(0)));
    controller.b.onTrue(new InstantCommand(() -> elevator.setSetpoint(1.5)));
    controller.y.onTrue(new InstantCommand(() -> elevator.setSetpoint(3)));
    // controller.a.whil
    
    controller.leftTriggerB.whileTrue(() -> outtakeSubsystem.setMotors(0.5)).onFalse(outtakeSubsystem::stop);
    controller.rightTriggerB.whileTrue(() -> outtakeSubsystem.setMotors(-0.5)).onFalse(outtakeSubsystem::stop);

  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}
