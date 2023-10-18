package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShoulderJoint;

public class ManualShoulderJoint extends CommandBase {
    private ShoulderJoint shoulder;
    private int dir;

    public ManualShoulderJoint(ShoulderJoint shoulder, int dir){
        this.shoulder=shoulder;
        this.dir=dir;
        addRequirements(shoulder);
    }

    @Override
    public void initialize(){
        shoulder.setSpeedManual(dir*.5);
    }

    @Override
    public void end(boolean interrupted){
        shoulder.hold();
    }

    @Override
    public boolean isFinished(){
        return shoulder.atTarget();
    }
    
}
