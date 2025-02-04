package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.Subsystems.OuttakeSubsystem;

public class IntakeCommand extends Command {
 
    private OuttakeSubsystem outtakeSubsystem;

    public IntakeCommand(OuttakeSubsystem outtakeSubsystem) {
        this.outtakeSubsystem = outtakeSubsystem;
        addRequirements(outtakeSubsystem);
    }

    
    @Override
    public void initialize() {
        System.out.println("Intake");
    }

    @Override
    public void execute() {
        outtakeSubsystem.out(0.5);
    }

    @Override
    public void end(boolean isFInished) {
        outtakeSubsystem.out(0);
    }

    @Override
    public boolean isFinished() {
        return RobotContainer.controller.leftTrigger().getAsBoolean() == false;
    }
}
