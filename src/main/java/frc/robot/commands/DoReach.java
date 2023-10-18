package frc.robot.commands;

import frc.robot.Constants.ReachDir;

import frc.robot.subsystems.Reach;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DoReach extends CommandBase {
    private Reach reachSubsystem;
    private double distance;

    public DoReach(Reach reachSubsystem, double distance) {

        this.reachSubsystem = reachSubsystem;
        this.distance = distance;
        addRequirements(reachSubsystem);
    }

    @Override
    public void initialize() {
        reachSubsystem.moveToDistance(distance);
    }

    @Override
    public void end(boolean interrupted){
        
    }


    @Override
    public boolean isFinished() {
        return false;
    }

}
