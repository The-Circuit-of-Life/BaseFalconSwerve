package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.ArmPreset;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.ShoulderJoint;
import frc.robot.subsystems.Reach;


public class UseArmPreset extends ParallelCommandGroup {
    ArmPreset preset;

    public UseArmPreset(ArmPreset preset, Lift lift, ShoulderJoint shoulderJoint, Reach reach) {
        this.preset = preset;
        ShoulderPID shoulderPID = new ShoulderPID(shoulderJoint, preset.shoulderAngle);
        LiftPID liftPID = new LiftPID(lift, preset.liftAngle);
        DoReach doReach = new DoReach(reach, preset.reachDistance);
        addCommands(shoulderPID, liftPID, doReach);
    }
}
