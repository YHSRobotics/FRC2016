package org.usfirst.frc.team4161.robot.commands;

import org.usfirst.frc.team4161.robot.OI;
import org.usfirst.frc.team4161.robot.Robot;
import org.usfirst.frc.team4161.robot.subsystems.ShooterArm;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateShooterArmWithJoystick extends Command {

	private Joystick js;
	private ShooterArm arm = Robot.shooterArm;
	private boolean end = false;

	/**
	 * This command will use the Y axis of the given joystick to control the
	 * angle of the shooter arm.
	 * 
	 * @param joystick The joystick to read from.
	 */
	public RotateShooterArmWithJoystick(Joystick joystick) {
		js = joystick;
		requires(Robot.shooterArm);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		arm.ArmRotate(js.getY());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return end;// never naturally finish.
	}

	// Called once after isFinished returns true
	protected void end() {
		arm.ArmRotate(0);// stop th motor.
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end = true;// end it!
	}
}
