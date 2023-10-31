package frc.robot.autos;

import com.ctre.phoenix.sensors.Pigeon2;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.TimedIntake;
import frc.robot.commands.UseArmPreset;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Reach;
import frc.robot.subsystems.ShoulderJoint;
import frc.robot.subsystems.Swerve;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Auto3 extends SequentialCommandGroup{

    public Auto3(Swerve s_Swerve, ShoulderJoint shoulder, Lift lift, Intake intake, Reach reach){
    super(
        //new WaitCommand(3),
        new ParallelCommandGroup(
            //new UseArmPreset(Constants.ArmPresetList.get("CUBE_MID"), lift, shoulder, reach),
            new TimedIntake(intake, 4, 1)),
            new InstantCommand(() -> {s_Swerve.zeroGyro();})        
    );
    }

}
