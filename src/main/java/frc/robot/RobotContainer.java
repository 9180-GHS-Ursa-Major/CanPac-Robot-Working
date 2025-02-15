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
//import frc.robot.Commands.ElevatorDown;
//import frc.robot.Commands.ElevatorUp;
import frc.robot.Subsystems.DrivetrainSubsystem;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.OuttakeSubsystem;

public class RobotContainer {
  
  public static DebouncedController controller = new DebouncedController(0);

  //This is where we add all the subsystems to RobotContainer.
  //Keep them here to keep it clean
  private final ElevatorSubsystem elevator = new ElevatorSubsystem();
  private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
  private final OuttakeSubsystem outtakeSubsystem = new OuttakeSubsystem();
  

  //This is where we add all the commands to RobotContainer
  //Keep them here to keep it clean

  // private final ElevatorUp elevatorUp = new ElevatorUp(elevatorSubsystem);
  // private final ElevatorDown elevatorDown = new ElevatorDown(elevator, 1);
  // Not neccessary ^, dont touch
  

  SendableChooser<Command> chooser = new SendableChooser<>();
  
  public RobotContainer() {
    configureBindings();
    //chooser.setDefaultOption("Command1", driveCommand);
  }

  private void configureBindings() {
    // Start of the chooser
  drivetrainSubsystem.setDefaultCommand(new DriveCommand(drivetrainSubsystem));
    // upTrigger.whileTrue(elevatorUp); //also not neccessary, dont touch
    controller.back.whileTrue(new InstantCommand(() -> elevator.setHomeElevator(true))).onFalse(() -> {elevator.setHomeElevator(false); elevator.zeroEncoders();});

    controller.a.onTrue(() -> elevator.setHomeElevator(true));
    controller.b.onTrue(() -> elevator.setSetpoint(1.25)); //l2
    controller.y.onTrue(() -> elevator.setSetpoint(2.5)); //L3

    controller.rightY.tiggerAt(0.8).whileTrue(() -> { elevator.setOverride(true); elevator.moveMotors(-0.05);}).onFalse(() ->{elevator.setOverride(false); elevator.stopElevator();});
    controller.rightY.tiggerAt(-0.8).whileTrue(() -> {elevator.setOverride(true); elevator.moveMotors(0.05);}).onFalse(() ->{elevator.setOverride(false); elevator.stopElevator();});
    // the override ^
    
    controller.leftTriggerB.whileTrue(() -> outtakeSubsystem.setMotors(0.5)).onFalse(outtakeSubsystem::stop);
    controller.rightTriggerB.whileTrue(() -> outtakeSubsystem.setMotors(-0.5)).onFalse(outtakeSubsystem::stop);

  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}
