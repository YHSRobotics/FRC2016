package org.usfirst.frc.team4161.robot.subsystems;

import org.usfirst.frc.team4161.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FallbackWheels extends Subsystem {

	Talon L = RobotMap.LFallback, R = RobotMap.RFallback;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	/**
	 * Set the speed of the fallback extension cable. A positive value means
	 * deploy the wheels. A negative will retract the wheels. There is NO system
	 * to prevent over-retracting. WANRING: Do NOT leave retracting w/o attendance!
	 * 
	 * @param speed Speed to retract/deploy fallback wheels (+ deploy, - retract)
	 */
	public void setSpeed(double speed) {
		L.set(speed);
		R.set(speed);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
