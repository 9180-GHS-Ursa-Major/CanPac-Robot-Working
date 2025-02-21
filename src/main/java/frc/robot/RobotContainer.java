// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import ca.frc6390.athena.controllers.DebouncedController;
import ca.frc6390.athena.controllers.EnhancedXboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Commands.AutoTurn;
import frc.robot.Commands.DriveCommand;
import frc.robot.Commands.DriveOneFoot;
import frc.robot.Commands.MiddleAuto;
//import frc.robot.Commands.ElevatorDown;
//import frc.robot.Commands.ElevatorUp;
import frc.robot.Subsystems.DrivetrainSubsystem;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.OuttakeSubsystem;


public class RobotContainer {
  
  private double l2SetPoint = 0.8;
  private double l3SetPoint = 2.3;
  public static EnhancedXboxController controller = new EnhancedXboxController(0);

  //This is where we add all the subsystems to RobotContainer.
  //Keep them here to keep it clean
  private final ElevatorSubsystem elevator = new ElevatorSubsystem();
  private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
  private final OuttakeSubsystem outtakeSubsystem = new OuttakeSubsystem();
  

  //This is where we add all the commands to RobotContainer
  //Keep them here to keep it clean

  // private final ElevatorUp elevatorUp = new ElevatorUp(elevatorSubsystem);
  // private final ElevatorDown elevatorDown = new ElevatorDown(elevator, 1);
  // Not neccessary ^, dont touch
  

  SendableChooser<Command> chooser = new SendableChooser<>();
  
  public RobotContainer() {
    configureBindings();
    SmartDashboard.putData(chooser);
    //chooser.setDefaultOption("Command1", driveCommand);
  }

  private void configureBindings() {
    // Start of the chooser
    chooser.addOption("AUTO1", new MiddleAuto(drivetrainSubsystem));
    chooser.addOption("AUTO2", new MiddleAuto(drivetrainSubsystem));

    chooser.addOption("AUTO3", new MiddleAuto(drivetrainSubsystem));
    chooser.addOption("1 Foot Autor", new DriveOneFoot(drivetrainSubsystem));
    chooser.addOption("Turn 45 Degrees", new AutoTurn(drivetrainSubsystem, l3SetPoint, l2SetPoint));

  drivetrainSubsystem.setDefaultCommand(new DriveCommand(drivetrainSubsystem));
    // upTrigger.whileTrue(elevatorUp); //also not neccessary, dont touch
    // controller.back.whileTrue(new InstantCommand(() 
    // -> elevator.setHomeElevator(true))).onFalse(()
    //  -> {elevator.setHomeElevator(false); elevator.zeroEncoders();});

    controller.a.onTrue(() -> elevator.setHomeElevator(true));
    controller.b.onTrue(() -> elevator.setSetpoint(l2SetPoint)); //1.25
    controller.y.onTrue(() -> elevator.setSetpoint(l3SetPoint)); //2
    controller.x.whileTrue(() -> outtakeSubsystem.setMotors(0.25, -0.05)).onFalse(outtakeSubsystem::stop);

    controller.leftBumper.whileTrue(() -> { elevator.setOverride(true); elevator.moveMotors(-0.05);}).onFalse(() ->{elevator.setOverride(false); elevator.stopElevator();});
    controller.rightBumper.whileTrue(() -> {elevator.setOverride(true); elevator.moveMotors(0.1);}).onFalse(() -> {elevator.setOverride(false); elevator.stopElevator();});
    // the override ^
    
    controller.leftTrigger.tiggerAt(0.5).whileTrue(() -> outtakeSubsystem.setMotors(0.275)).onFalse(outtakeSubsystem::stop);
    controller.rightTrigger.tiggerAt(0.5).whileTrue(() -> outtakeSubsystem.setMotors(-0.35)).onFalse(outtakeSubsystem::stop);
    //Not working for somereason ^, instead X and B are controlling these???
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}
