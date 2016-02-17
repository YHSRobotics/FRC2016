package org.usfirst.frc.team4161.robot.subsystems;

import org.usfirst.frc.team4161.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterArm extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Victor armArticulate = RobotMap.ArmArticulate;
	DigitalInput ballTapLimit = RobotMap.BallTapLimit;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}


	/**
	 * Rotate the arm. A positive speed is up, a negative speed is down. It will
	 * not rotate down past the limit switch defined in RobotMap.
	 * 
	 * @param speed
	 *            The speed to move the arm.
	 */
	public void ArmRotate(double speed) {
		if ((!ballTapLimit.get()) || speed > 0) {// if it is at limit, don't do
													// anything.
			armArticulate.set(speed);
		}
	}


}
