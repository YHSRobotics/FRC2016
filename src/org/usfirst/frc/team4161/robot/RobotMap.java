package org.usfirst.frc.team4161.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
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
	private final static int LTreadID = 0, RTreadID = 1, ArmArticulateID = 4,
			BallTapLimitID = 0, BallCollectorID = 2, ShooterActuatorID = 3;

	public static Talon LTread = new Talon(LTreadID), RTread = new Talon(RTreadID);
	
	public static Victor ArmArticulate = new Victor(ArmArticulateID), BallCollector = new Victor(BallCollectorID), ShooterActuator = new Victor(ShooterActuatorID);


}
