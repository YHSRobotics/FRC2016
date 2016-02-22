package org.usfirst.frc.team4161.robot.commands;

import org.usfirst.frc.team4161.robot.Robot;
import org.usfirst.frc.team4161.robot.RobotMap;
import org.usfirst.frc.team4161.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	private DriveTrain driveTrain = Robot.driveTrain;
	private AnalogGyro gyro = RobotMap.gyro;

	private int ticks;
	private boolean backwards;
	private double heading, maxPower;

	/**
	 * Drive straight forward for x number of ticks.
	 * 
	 * @param ticks
	 *            Number of ticks to drive forward for.
	 * @param forward
	 *            If true, drive forward. Else drive backwards.
	 */
	public DriveStraight(int ticks, boolean forward) {
		this(ticks, forward ? 1 : -1);// call the other constructor.
	}

	/**
	 * Makes the robot drive straight, but does not exceed maxPower. If maxPower
	 * is negative, it will drive backwards.
	 * 
	 * @param ticks
	 *            Ticks to drive for.
	 * @param maxPower
	 *            Maximum power to use. Must be in range [0, 1] (NOTE: 0 will
	 *            not go anywhere).
	 */
	public DriveStraight(int ticks, double maxPower) {
		requires(driveTrain);
		this.ticks = ticks;
		if (maxPower < 0) {
			maxPower = -1 * maxPower;// make the power positive.
			backwards = true;// go backwards
		} else
			backwards = false;// go forwards.

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		heading = RobotMap.gyro.getAngle();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		ticks--;
		double diff = AngleTools.getAngleDifference(gyro.getAngle(), heading);
		double compPower = 1 - AngleTools.computePower(diff, maxPower), // get
																		// the
																		// computed
																		// power
				regPower = backwards ? -1 * maxPower : maxPower;// get 'regular'
																// power.
		compPower = backwards ? -1 * compPower : compPower;// check for
															// backwards.

		if (diff < -1)// correct left
			driveTrain.setDrive(compPower, regPower);
		else if (diff > 1)// correct right.
			driveTrain.setDrive(regPower, compPower);
		else
			driveTrain.setDrive(regPower, regPower);// go straight.
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
