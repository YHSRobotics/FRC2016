package org.usfirst.frc.team4161.robot.subsystems;

import org.usfirst.frc.team4161.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterArm extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Victor armArticulate = RobotMap.ArmArticulate;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	/**
	 * Rotate the arm. A positive speed is up, a negative speed is down.
	 * It will NOT monitor the limits of the motor!
	 * 
	 * @param speed
	 *            The speed to move the arm.
	 */
	public void ArmRotate(double speed) {
		armArticulate.set(speed/4);
	}


}
