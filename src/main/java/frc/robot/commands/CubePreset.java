package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.ShoulderJoint;

public class CubePreset extends CommandBase{
    private Lift lift;
    private ShoulderJoint shoulder;
    private double liftTarget;
    private double shoulderTarget;

    public CubePreset(Lift lift, double liftTarget,ShoulderJoint shoulder, double shoulderTarget){
        this.lift=lift;
        this.liftTarget=liftTarget;
        this.shoulder=shoulder;
        this.shoulderTarget=shoulderTarget;
    }

    @Override
    public void initialize(){
        new LiftPID(lift, liftTarget);
        new ShoulderPID(shoulder, shoulderTarget);
    }
    
    @Override
    public boolean isFinished(){
        return false;
    }
}
