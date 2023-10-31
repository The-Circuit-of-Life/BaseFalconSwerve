package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private final Reach reach = new Reach(); 
    private final Lift lift = new Lift();
    private final Intake intake = new Intake();
    private final ShoulderJoint shoulder;
    
    DigitalInput limit = new DigitalInput(6);

    /* Controllers */
    private final XboxController driver = new XboxController(0);
    private static XboxController controller = new XboxController(1);



    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kBack.value);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);

    /* Subsystems */
    public final Swerve s_Swerve = new Swerve();


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {

        shoulder = new ShoulderJoint(limit);

        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis)/2, 
                () -> -driver.getRawAxis(strafeAxis)/2, 
                () -> -driver.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean()
            )
        );

        // Configure the button bindings
        configureButtonBindings();
    }

    public boolean Threshold(double axis, double threshold){
        if(axis>threshold){
          return true;
        }
        return false;
      }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
        new Trigger(()->Threshold(controller.getRightTriggerAxis(), 0.5)).whileTrue(new ManualShoulderJoint(shoulder,-1));
        new Trigger(()->Threshold(controller.getLeftTriggerAxis(), 0.5)).whileTrue(new ManualShoulderJoint(shoulder,1));



        new POVButton(controller,0).onTrue(new UseArmPreset(Constants.ArmPresetList.get("CONE_TOP"), lift, shoulder, reach));
        new POVButton(controller,90).onTrue(new UseArmPreset(Constants.ArmPresetList.get("CONE_MID"), lift, shoulder, reach));
        //what direction:
        new POVButton(controller,180).onTrue(new UseArmPreset(Constants.ArmPresetList.get("CONE_INTAKE"), lift, shoulder, reach));
        new POVButton(controller,270).onTrue(new UseArmPreset(Constants.ArmPresetList.get("CONE_INTAKE_TOP"), lift, shoulder, reach));
        new JoystickButton(controller, XboxController.Button.kStart.value).onTrue(new UseArmPreset(Constants.ArmPresetList.get("ZERO"), lift, shoulder, reach));

        new JoystickButton(controller,XboxController.Button.kA.value).onTrue(new UseArmPreset(Constants.ArmPresetList.get("CONE_INTAKE_HUMAN"), lift, shoulder, reach));
        new JoystickButton(controller, XboxController.Button.kRightBumper.value).whileTrue(new ManualLift(lift,-1));
        new JoystickButton(controller, XboxController.Button.kLeftBumper.value).whileTrue(new ManualLift(lift,1));
        new JoystickButton(controller, XboxController.Button.kY.value).whileTrue(new RotateWrist(1,intake));
        new JoystickButton(controller, XboxController.Button.kX.value).whileTrue(new RotateWrist(-1,intake));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new exampleAuto(s_Swerve);
    }

    public Command getAuto3(){
        return new Auto3(s_Swerve, shoulder,lift,intake, reach);
    }
}
