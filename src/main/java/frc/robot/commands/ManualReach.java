package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Reach;

public class ManualReach extends CommandBase {
    private Reach reach;
    private int direction;

    public ManualReach(Reach reach, int direction){
        this.reach=reach;
        this.direction=direction;
    }

    @Override
    public void initialize(){
        reach.setSpeed(direction*.4);
    }

    @Override
    public void end(boolean interrupted){
        reach.setSpeed(0);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
    
}
