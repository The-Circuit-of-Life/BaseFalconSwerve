package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Reach;
import frc.robot.subsystems.ShoulderJoint;

public class ShoulderPID extends CommandBase {
    private ShoulderJoint shoulder;
    private double targetPos;
    public ShoulderPID(ShoulderJoint shoulder, double targetPos){
        this.shoulder = shoulder;
        this.targetPos = targetPos;
    }

    @Override
    public void initialize() {
        shoulder.setTarget(targetPos);
    }

    @Override
    public void end(boolean interrupted) {
        shoulder.hold();
    }

    @Override
    public boolean isFinished(){
        return shoulder.atTarget();
    }
    
}
