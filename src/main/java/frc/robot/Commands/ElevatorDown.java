package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.Subsystems.Elevator2;
import frc.robot.Subsystems.ElevatorSubsystem;

public class ElevatorDown extends Command {
    
    Elevator2 elevatorSubsystem;
    double setpoint;

    public ElevatorDown(Elevator2 elevatorSubsystem, double setpoint) {
        this.elevatorSubsystem = elevatorSubsystem;
        this.setpoint = setpoint;
        addRequirements(elevatorSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Going down...");
    }

    @Override
    public void execute() {
        elevatorSubsystem.setSetpoint(setpoint);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean isFinished) {
        
    }
}
