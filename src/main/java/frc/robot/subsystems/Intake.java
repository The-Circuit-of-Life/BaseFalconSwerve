package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import frc.robot.Constants;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

public class Intake extends SubsystemBase {
    private CANSparkMax left;
    //private CANSparkMax right;

    public Intake(){
        left = new CANSparkMax(3, MotorType.kBrushless);
       // right = new CANSparkMax(Constants.wristRight, MotorType.kBrushless);
        left.restoreFactoryDefaults();
        //right.restoreFactoryDefaults();
        //right.setInverted(true);
        //right.follow(left);
        // right.setIdleMode(IdleMode.kBrake);
        left.setIdleMode(IdleMode.kBrake);
        

    }

public void setSpeed(double x){

    left.set(x);
   // right.set(-x);
}

public void stop(){
    left.stopMotor();
   // right.stopMotor();
}
public void periodic(){
    
}
    
}
