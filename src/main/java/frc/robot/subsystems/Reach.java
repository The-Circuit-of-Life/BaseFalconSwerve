package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import frc.robot.Constants;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


    public class Reach extends SubsystemBase{
        //instance variables 
        private TalonFX motor;
        private DigitalInput limiter;
        private double currentPos;
        private double direction;
        private double targetDistance;
        private boolean calibrating = false;
        private boolean movingDistance = false;
    

        //constructor
        public Reach(){
            motor= new TalonFX(30);
            limiter = new DigitalInput(Constants.REACH_LIMITSWITCH);
             motor.configFactoryDefault();
             motor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
             motor.configClearPositionOnLimitR(calibrating, 0);
             motor.setSelectedSensorPosition(0);
             
        }
        //methods

        public void setSpeed(double x){
            motor.set(TalonFXControlMode.PercentOutput,x);
        }

        public double  getPosition(){
            return motor.getSelectedSensorPosition();
        }

        public void moveToDistance(double distance) {
            if(!calibrating) {
                currentPos = motor.getSelectedSensorPosition();
                direction = Math.signum(distance-currentPos);
                motor.set(TalonFXControlMode.PercentOutput, Constants.REACH_SPEED*direction);
                targetDistance = distance;
                movingDistance = true;
            }
            
        }

        public void calibrate() {
            motor.set(TalonFXControlMode.PercentOutput,Constants.REACH_CALIBRATE_SPEED);
            calibrating = true;
        } 

        @Override
        public void periodic() {
            if(calibrating && limiter.get()) {
               
                calibrating = false;
                motor.setSelectedSensorPosition(0);
            }

            currentPos = motor.getSelectedSensorPosition();
            double newDir = Math.signum(targetDistance-currentPos);
            if(movingDistance && newDir != direction) {
                motor.set(TalonFXControlMode.PercentOutput, 0);
                movingDistance = false;
            }
            //System.out.println("reach pos" +motor.getSelectedSensorPosition());
        }
        
    }

