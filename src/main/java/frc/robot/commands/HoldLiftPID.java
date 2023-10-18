package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Lift;

public class HoldLiftPID extends PIDCommand{
private double fixedSetPoint;
    public HoldLiftPID(Lift lift){
        super(
            new PIDController(.0000267,0,0),
            lift::getPosition,
            lift::getPosition,
            output->lift.setSpeed(output));
        addRequirements(lift);
        
    }

    @Override
    public void initialize(){
        super.initialize();
        fixedSetPoint=m_setpoint.getAsDouble();
    }

    @Override
  public void execute() {
    m_useOutput.accept(
        m_controller.calculate(m_measurement.getAsDouble(), fixedSetPoint));
  }

    @Override
    public boolean isFinished(){
      // return getController().atSetpoint();
       return false;
       
    }
    
}
