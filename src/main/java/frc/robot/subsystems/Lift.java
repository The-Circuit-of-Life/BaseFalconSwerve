package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class Lift extends SubsystemBase {
   
   private TalonFX lift;
   private PIDController controller;
   private double target;
   private static enum Mode{
    MANUAL,
    TARGET
   }
   private Mode mode;
  
   

    public Lift() {
        lift = new TalonFX(27);
        lift.configFactoryDefault();
        lift.setNeutralMode(NeutralMode.Brake);
        lift.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        lift.setSelectedSensorPosition(0);
        controller=new PIDController(.000023, 0.00000016, 0);
        mode=Mode.MANUAL;
        controller.setTolerance(1000);
    }

    public void setTarget(double target){
        mode=Mode.TARGET;
        controller.reset();
        this.target=target;
        controller.setSetpoint(target);
    }

    public boolean atTarget(){
      SmartDashboard.putNumber("tolerance", controller.getPositionTolerance());
      SmartDashboard.putNumber("lift position error", controller.getPositionError());

      return controller.atSetpoint();
      
    }
    public void setSpeed(double x){
      lift.set(ControlMode.PercentOutput,x);
    }

    public void setSpeedManual(double speed){
      mode=Mode.MANUAL;
      setSpeed(speed);
    }
    public void hold(){
      setTarget(getPosition());
    }

    @Override
    public void periodic(){
      SmartDashboard.putNumber("lift pos", lift.getSelectedSensorPosition());
      if(mode==Mode.TARGET){
        double output=controller.calculate(getPosition(),target);
        SmartDashboard.putNumber("output",output);
        setSpeed(output);
      }
      SmartDashboard.putNumber("error", Math.abs(controller.getPositionError()));
      

      if(Math.abs(controller.getPositionError())< 3000){
          controller.setP(.00008);
      }
      if(Math.abs(controller.getPositionError())>3000){
        controller.setP(.000022);
      }
    }

    
    public double getPosition(){
      return lift.getSelectedSensorPosition();
    }
  }
    
