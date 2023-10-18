package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Lift;

public class LiftPID extends CommandBase{
    private Lift lift;
    private double target;

    public LiftPID(Lift lift,double target){
        this.lift=lift;
        this.target=target;
        addRequirements(lift);
        
    }
    @Override
    public void initialize(){
        SmartDashboard.putBoolean("Lift Finished.", false);

        lift.setTarget(target);
    }

    @Override
    public void end(boolean interrupted){
        SmartDashboard.putBoolean("Lift Finished.", true);

        lift.hold();
    }

    @Override
    public boolean isFinished(){
      // return getController().atSetpoint();
       return lift.atTarget();
       
    }
    
}
