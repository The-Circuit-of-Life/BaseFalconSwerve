package frc.robot.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;


public class TimedIntake extends CommandBase {

  double startTime;
  Intake intake;
  int direction;
  double seconds;




  /**
   * Creates a new TimedIntake.
   */
  public TimedIntake(Intake intake, double sec, int direction) {
    this.seconds = sec;
    // Use addRequirements() here to declare subsystem dependencies.
    this.intake = intake;
    this.direction = direction;
    startTime = Timer.getFPGATimestamp();

  }
  
  @Override
  public void initialize() {
    intake.setSpeed(0);
    
  }

  @Override
  public void execute(){
if(Timer.getFPGATimestamp() - startTime >= 1){
  intake.setSpeed(.5*direction);
}
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.setSpeed(0);
  }
  
  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() - startTime >= seconds;
  }

}