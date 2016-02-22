package org.usfirst.frc.team4161.robot.commands;

import org.usfirst.frc.team4161.robot.Robot;
import org.usfirst.frc.team4161.robot.subsystems.ShooterArm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowerShooterArm extends Command {

	private boolean finished = false;
	ShooterArm arm = Robot.shooterArm;

	/**
	 * Collapse the arm to the fully down position.
	 * 
	 */
	public LowerShooterArm() {
		requires(Robot.shooterArm);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		arm.ArmRotate(-1);//start the motors.
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		if(arm.isLimit())
			finished = true;//the rotation is done!
			

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return finished;
	}

	// Called once after isFinished returns true
	protected void end() {
		arm.ArmRotate(0);//stop the arm.
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
