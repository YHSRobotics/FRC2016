package org.usfirst.frc.team4161.robot.commands;

import org.usfirst.frc.team4161.robot.Robot;
import org.usfirst.frc.team4161.robot.subsystems.ShooterArm;
import edu.wpi.first.wpilibj.command.Command;

public class RotateShooterArm extends Command {
	private ShooterArm shooterArm = Robot.shooterArm;

	private int ticks;
	private double maxPower;

	/**
	 * Drive straight forward for x number of ticks.
	 * 
	 * @param ticks
	 *            Number of ticks to drive forward for.
	 * @param forward
	 *            If true, drive forward. Else drive backwards.
	 */
	public RotateShooterArm(int ticks, boolean forward) {
		this(ticks, forward ? 1 : -1);// call the other constructor.
	}

	/**
	 * Makes the robot drive straight, but does not exceed maxPower. If maxPower
	 * is negative, it will drive backwards.
	 * 
	 * @param ticks
	 *            Ticks to drive for.
	 * @param maxPower
	 *            Maximum power to use. Must be in range [-1, 1] (NOTE: 0 will
	 *            not go anywhere).
	 */
	public RotateShooterArm(int ticks, double maxPower) {
		requires(shooterArm);
		this.ticks = ticks;
		this.maxPower = maxPower;

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		shooterArm.ArmRotate(maxPower);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		ticks--;
		if (shooterArm.isLimit()) {
			ticks = 0;// stop!
			shooterArm.ArmRotate(0);// stop the arm
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return ticks <= 0;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

}
