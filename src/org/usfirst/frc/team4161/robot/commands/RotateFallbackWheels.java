package org.usfirst.frc.team4161.robot.commands;

import org.usfirst.frc.team4161.robot.Robot;
import org.usfirst.frc.team4161.robot.subsystems.FallbackWheels;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateFallbackWheels extends Command {

	private int speed;
	private boolean end = false;
	private FallbackWheels fbwheels = Robot.fallbackWheels;

	/**
	 * A Simple command that will rotate the fallback wheels until interrupted.
	 * WARNING: no overlimit detection! Use with caution. Best used with a
	 * joystick button that will be released after a short time.
	 * 
	 * @param speed Speed to rotate the motors (+ deploy, - retract)
	 */
	public RotateFallbackWheels(int speed) {
		requires(Robot.fallbackWheels);
		this.speed = speed;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		fbwheels.setSpeed(speed);// set the speed of the wheels.
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return end;
	}

	// Called once after isFinished returns true
	protected void end() {
		fbwheels.setSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end = true;
	}
}
