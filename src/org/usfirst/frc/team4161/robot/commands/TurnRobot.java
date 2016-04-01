package org.usfirst.frc.team4161.robot.commands;

import org.usfirst.frc.team4161.robot.ConversionFactor;
import org.usfirst.frc.team4161.robot.Robot;
import org.usfirst.frc.team4161.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnRobot extends Command {

	private int ticks, startTicks;
	private boolean turnRight;
	// accelerationThreshold prevents the robot from going from 0 to full
	// acceleration too fast. The higher the value, the longer the robot will
	// take to speed up.
	private final double accelerationThreshold = 100;
	private DriveTrain driveTrain = Robot.driveTrain;
	private Preferences prefs = null;

	/**
	 * Turn the robot for a number of ticks. If turnRight is true, the robot
	 * will turn to the right, else it will turn left.
	 * 
	 * @param ticks
	 *            number of ticks to turn for. A negative value will be ignored
	 *            and set to positive.
	 * @param turnRight
	 *            if true, the robot will turn right.
	 * 
	 */
	public TurnRobot(int ticks, boolean turnRight) {
		requires(driveTrain);
		ticks = (ticks < 0) ? -ticks : ticks;// check for negative.
		this.ticks = ticks;
		this.turnRight = turnRight;
		startTicks = ticks;
		System.out.println("TurnRobot: Robot turning for " + ticks
				+ " ticks isRight: " + turnRight);

	}

	/**
	 * Turn the robot degrees number of degrees. A positive number will turn
	 * clockwise, a negative number will turn counter clockwise.
	 * 
	 * @param degrees
	 *            degrees to turn.
	 */
	public TurnRobot(double degrees) {
		this(ConversionFactor.driveDegreesToTick(degrees), 
				(degrees>0)?true:false);
	}

	/**
	 * This constructor will read the value at the key "TurnTickCount" from the
	 * preferences object and turn right that many ticks when executed.
	 * 
	 * @param prefs
	 *            the preferences object to read from.
	 */
	public TurnRobot(Preferences prefs) {
		requires(driveTrain);
		this.prefs = prefs;
		ticks = 1;
		startTicks = 1;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (prefs != null) {// read from the prefs file.
			ticks = prefs.getInt("TurnTickCount", 0);
			if (ticks < 0) {
				turnRight = false;
				ticks *= -1;
			} else
				turnRight = true;
			startTicks = ticks;
		}
		System.out.println("TurnRobot: Turning for " + ticks + " ticks");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double power = 1;// assume power of 1

		if (ticks <= accelerationThreshold && ticks < (startTicks - ticks)) {
			// don't decelerate too fast
			double reductionFactor = ticks / accelerationThreshold;
			power *= reductionFactor;
		} else if (startTicks - ticks <= accelerationThreshold) {
			// don't accelerate too fast.
			double reductionFactor = (startTicks - ticks)
					/ accelerationThreshold;
			power *= reductionFactor;
		}

		if (turnRight)// turn right
			driveTrain.setDrive(power, -power);
		else
			driveTrain.setDrive(-power, power);// turn right.

		ticks--;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (ticks <= 0);// have all the ticks completed?
	}

	// Called once after isFinished returns true
	protected void end() {
		driveTrain.setDrive(0, 0);// stop the motors.
		System.out.println("TurnRobot: Robot finished for " + startTicks
				+ " ticks.");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

}
