package org.usfirst.frc.team4161.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	private final static int LTreadID = 0, RTreadID = 0, ArmArticulateID = 0, BallTapOutID = 0, BallTapInID = 0,
			BallTapLimitID = 0, BallCollectorID = 0;

	public static Victor LTread = new Victor(LTreadID), RTread = new Victor(RTreadID),
			ArmArticulate = new Victor(ArmArticulateID), BallCollector = new Victor(BallCollectorID);

	public static Solenoid BallTapOut = new Solenoid(BallTapOutID), BallTapIn = new Solenoid(BallTapInID);
	public static DigitalInput BallTapLimit = new DigitalInput(BallTapLimitID);

}
