package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Reach;

public class ReachToPosition extends CommandBase {
    private double startPos;
    private Reach reach;
    private double currentPos;
    private double targetPos;
    private double error;
    private double  expectedDistance;
    private double  distanceTraveled;


    public ReachToPosition(Reach reach, double targetPos){
        this.reach=reach;
        this.targetPos=targetPos;

        startPos=0;
        currentPos=0;
    }

    @Override 
    public void initialize(){
        startPos=reach.getPosition();
    }

    @Override 
    public void execute(){
        currentPos= reach.getPosition();
        error= targetPos-currentPos;
        reach.setSpeed(.2);
        expectedDistance= targetPos-startPos;
        distanceTraveled= currentPos-startPos;
        System.out.println("current pos "+ currentPos);
        System.out.println("target pos "+ targetPos);


    }

    @Override
    public void end(boolean interrupted){
        reach.setSpeed(0);
    }

    @Override
    public boolean isFinished(){
        if(expectedDistance<=distanceTraveled){
            return true;
        }
        return false;
    }

    
}
