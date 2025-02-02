package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.Subsystems.ElevatorSubsystem;

public class ElevatorUp extends Command {
    
    ElevatorSubsystem elevatorSubsystem;

    public ElevatorUp(ElevatorSubsystem elevatorSubsystem) {
        this.elevatorSubsystem = elevatorSubsystem;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Going up...");
    }

    @Override
    public void execute() {
        elevatorSubsystem.elevate(0.5);
    }

    @Override
    public boolean isFinished() {
        return RobotContainer.controller.povUp().getAsBoolean();
    }

    @Override
    public void end(boolean isFinished) {
        elevatorSubsystem.elevate(0);
    }
}
