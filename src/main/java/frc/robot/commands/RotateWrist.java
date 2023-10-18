package frc.robot.commands;


import frc.robot.subsystems.Intake;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class RotateWrist extends CommandBase{

    private Intake wrist;
    private int direction;

    public RotateWrist(int direction, Intake wrist){
        this.direction = direction;
        this.wrist = wrist;
        addRequirements(wrist);
    }


    @Override
    public void initialize() {
  //this creates an instance if there there is non, or retunrs the instance
  //refers to intakedown method cycle(subsystems) which uses a boolean to chane the position of the pneumatic 
      wrist.setSpeed(direction);
  
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        //wrist.setSpeed(0);
        wrist.stop();
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
  
      return false;
      //!RobotContainer.deployIntake.get();
      
    }



}