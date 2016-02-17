package org.usfirst.frc.team4161.robot.subsystems;

import org.usfirst.frc.team4161.robot.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	Talon L = RobotMap.LTread, R = RobotMap.RTread;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	/**
	 * Set the speed of the left and right drive train components.
	 * 
	 * @param left Speed of the left tread from -1.0 to 1.0
	 * @param right Speed of the right tread from -1.0 to 1.0
	 */
	public void setDrive(double left, double right){
		L.set(left);
		R.set(right);//set the speed of the motors.
	}
	
	/**
	 * Stop the treads in place.
	 */
	public void stopDrive(){
		L.set(0);
		R.set(0);
	}
	
	public void initDefaultCommand() {

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
