package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.Rotation;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class ShoulderJoint extends SubsystemBase {
    //instance variables 
    private TalonFX leftMotor;
    private TalonFX rightMotor;  
    public static CANCoder leftCoder;
    private PIDController controller;
    private static enum Mode{
        MANUAL,
        TARGET,
    }
    private Mode mode;
    private double target;

    DigitalInput limit;

    

    CANCoderConfiguration _canconfig;
    //constructor
    public ShoulderJoint(DigitalInput limit){
        leftMotor = new TalonFX(Constants.shoulderJointLeftID);
        rightMotor = new TalonFX(Constants.shoulderJointRightID);

        leftMotor.configFactoryDefault();
        rightMotor.configFactoryDefault();
        
        this.limit = limit;

        controller = new PIDController(.000008,0.000002,0);
        mode = Mode.MANUAL;
       
       
        //Encoder setup
        //leftCoder = new CANCoder(Constants.cancoderid);
        rightMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        rightMotor.setSelectedSensorPosition(0);
        leftMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
        leftMotor.setSelectedSensorPosition(0);
        
        _canconfig=new CANCoderConfiguration(); 
       // leftCoder.setPosition(0);
        leftMotor.setNeutralMode(NeutralMode.Brake);
        rightMotor.setNeutralMode(NeutralMode.Brake);

        

        //leftCoder.setPositionConversionFactor(Constants.shoulderAngleConv);

       
       // leftCoder.configAllSettings(_canconfig);

    }


    public void setTarget(double target){
        mode=Mode.TARGET;
        this.target=target;
    }

    public boolean atTarget(){
      return controller.atSetpoint(); 
    }



    
    public void setSpeed(double x){
        leftMotor.set(ControlMode.PercentOutput,x);
        rightMotor.set(ControlMode.PercentOutput,-x);
    }

    public void setSpeedManual(double speed){
        mode=Mode.MANUAL;
        setSpeed(speed*.5);
      }
      public void hold(){
        SmartDashboard.putBoolean("Hold", true);
        setTarget(getPosition());
      }
    public double getPosition(){
        return leftMotor.getSelectedSensorPosition();
      }

@Override 
public void periodic(){
    SmartDashboard.putNumber("shoulder pos", getPosition());
if(mode==Mode.TARGET){
    double output=controller.calculate(getPosition(),target);
    setSpeed(output);
}

  SmartDashboard.putBoolean("limit", limit.get());

  if(limit.get()) {
    leftMotor.setSelectedSensorPosition(0);
    rightMotor.setSelectedSensorPosition(0);
  }

    
}
 


   
}