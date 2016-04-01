package org.usfirst.frc.team4161.robot.commands;

import org.usfirst.frc.team4161.robot.ConversionFactor;
import org.usfirst.frc.team4161.robot.Robot;
import org.usfirst.frc.team4161.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {

	private DriveTrain driveTrain = Robot.driveTrain;

	private int ticks, startTicks, accelerationThreshold = 50;
	private boolean backwards;
	private double maxPower;
	private Preferences prefs = null;

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
	 * Makes the robot drive a number of ticks specified in the preferences
	 * under the tag "DriveTickCount"
	 * 
	 * @param prefs
	 *            The preferences to read from.
	 */
	public DriveStraight(Preferences prefs) {
		requires(driveTrain);
		this.prefs = prefs;
		ticks = 1;
		startTicks = 1;
	}
	
	public DriveStraight(double feet, double maxPower){
		ticks = ConversionFactor.feetToTick(feet);
		//remove negative.
		if (maxPower < 0) {
			maxPower = -1 * maxPower;// make the power positive.
			backwards = true;// go backwards
		} else
			backwards = false;// go forwards.
		this.maxPower = maxPower;
		ticks = (int)(ticks/maxPower);
		startTicks = ticks;
		//counteract the need for more ticks at low power
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
		this.maxPower = maxPower;
		startTicks = ticks;
//		if (maxPower < 0) {
//			maxPower = -1 * maxPower;// make the power positive.
//			backwards = true;// go backwards
//		} else
//			backwards = false;// go forwards.

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (prefs != null) {
			ticks = prefs.getInt("DriveTickCount", 10);
			if (ticks < 0) {
				ticks *= -1;
				backwards = true;
			} else
				backwards = false;
			startTicks = ticks;
		}
		System.out.println("DriveStraight: Commanded to drive straight for "
				+ ticks + " ticks at " + maxPower);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double regPower = backwards ? -1 * maxPower : maxPower;// get 'regular'
																// power.
		if ((ticks <= accelerationThreshold) && (ticks < (startTicks - ticks))) {// don't
																				// decelerate
																				// too
																				// fast.
			double reductionFactor = ticks / accelerationThreshold;
			regPower *= reductionFactor;
		} else if ((startTicks - ticks) <= accelerationThreshold) {// don't
																	// accelerate
																	// too fast.
			double reductionFactor = (startTicks - ticks)
					/ accelerationThreshold;
			regPower *= reductionFactor;
		}
		
		driveTrain.setDrive(regPower, regPower);// go straight.
		//System.out.println(maxPower);
		ticks--;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (ticks <= 0);
	}

	// Called once after isFinished returns true
	protected void end() {
		driveTrain.setDrive(0, 0);
		System.out.println("DriveStraight: Drove Straight for " + startTicks
				+ " ticks. DONE!");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
