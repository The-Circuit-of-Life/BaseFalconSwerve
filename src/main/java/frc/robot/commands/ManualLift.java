package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lift;

public class ManualLift extends CommandBase {
    private Lift lift;
    private int dir;


    public ManualLift(Lift lift, int dir){
        this.lift=lift;
        this.dir=dir;
        addRequirements(lift);
    }


    @Override
    public void initialize(){
        lift.setSpeedManual(.2*dir);
    }

    @Override 
    public void end(boolean interrupted){
        lift.hold();
        
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
