package frc.robot;

public class ArmPreset {
    public double shoulderAngle;
    public double reachDistance;
    public double liftAngle;
    public boolean clawOpen;

    public ArmPreset(double shoulderAngle, double reachDistance, double liftAngle) {
        this.shoulderAngle = shoulderAngle;
        this.reachDistance = reachDistance;
        this.liftAngle = liftAngle;
    }
}
