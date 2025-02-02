package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.Subsystems.ElevatorSubsystem;

public class ElevatorDown extends Command {
    
    ElevatorSubsystem elevatorSubsystem;

    public ElevatorDown(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Going down...");
    }

    @Override
    public void execute() {
        elevatorSubsystem.elevate(-0.5);
    }

    @Override
    public boolean isFinished() {
        return RobotContainer.controller.povDown().getAsBoolean() == false;
    }

    @Override
    public void end(boolean isFinished) {
        elevatorSubsystem.elevate(0);
    }
}
