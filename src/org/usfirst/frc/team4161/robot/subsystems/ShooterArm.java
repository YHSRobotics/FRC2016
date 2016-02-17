package org.usfirst.frc.team4161.robot.subsystems;

import org.usfirst.frc.team4161.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterArm extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Talon armArticulate = RobotMap.ArmArticulate;
	DigitalInput ballTapLimit = RobotMap.BallTapLimit;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	/**
	 * Rotate the arm. A positive speed is up, a negative speed is down. It will
	 * not rotate down if the limit switch is pressed, but it will NOT monitor
	 * the limit switch to stop the arm!
	 * 
	 * @param speed
	 *            The speed to move the arm.
	 */
	public void ArmRotate(double speed) {
		if ((!ballTapLimit.get()) || speed > 0) {// if it is at limit, don't do
													// anything.
			armArticulate.set(speed);
		} else
			armArticulate.set(0);// stop it if it is at the limit.
	}
	
	/**
	 * Is the arm at the bottom limit?
	 * @return True if at the bottom limit, else false.
	 */
	public boolean isLimit(){
		return ballTapLimit.get();
	}

}
