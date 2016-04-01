package org.usfirst.frc.team4161.robot.commands;

import org.usfirst.frc.team4161.robot.Robot;
import org.usfirst.frc.team4161.robot.subsystems.ShooterArm;
import edu.wpi.first.wpilibj.command.Command;

public class RotateShooterArm extends Command {
	private ShooterArm shooterArm = Robot.shooterArm;

	private int ticks;
	private double maxPower;

	/**
	 * Rotate arm for x number of ticks.
	 * 
	 * @param ticks
	 *            Number of ticks to rotate arm for.
	 * @param forward
	 *            If true, rotate up, else rotate backwards.
	 */
	public RotateShooterArm(int ticks, boolean forward) {
		this(ticks, forward ? 1 : -1);// call the other constructor.
	}

	/**
	 * Makes the robot arm rotate.
	 * 
	 * @param ticks
	 *            Ticks to rotate for .
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
		System.out.println("RotateArmShooter: Arm commanded to rotate for " 
				+ ticks + " ticks");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		ticks--;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return ticks <= 0;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("RotateArmShooter: Arm rotation is complete at power " + maxPower);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

}
