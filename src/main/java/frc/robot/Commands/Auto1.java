// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Subsystems.DrivetrainSubsystem;
import frc.robot.Subsystems.ElevatorSubsystem;
import frc.robot.Subsystems.OuttakeSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Auto1 extends SequentialCommandGroup {
  /** Creates a new Auto1. */
  public Auto1(DrivetrainSubsystem drivesub, ElevatorSubsystem elevatorsub, OuttakeSubsystem outtakesub) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

  //   addCommands(new AutoDrive(drivesub, 88, 0.5),
  //               new AutoTurn(drivesub, 90),
  //               Commands.parallel(
  //                 new InstantCommand(() -> {elevatorsub.setSetpoint(1.5);}),
  //                 new InstantCommand(() -> {outtakesub.setMotors(0.5);})
  //               ),
  //               new InstantCommand(() -> {outtakesub.setMotors(0);})

                
  //               );

    
  }
}
