package org.usfirst.frc.team4161.robot.commands;

import org.usfirst.frc.team4161.robot.Robot;
import org.usfirst.frc.team4161.robot.RobotMap;
import org.usfirst.frc.team4161.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnRobot extends Command {

	private double bearing, tolerance = 3;
	private AnalogGyro gyro = RobotMap.gyro;
	private DriveTrain driveTrain = Robot.driveTrain;

	/**
	 * Turn the robot degrees number of degrees. A positive number will turn
	 * clockwise, a negative number will turn counter clockwise.
	 * 
	 * @param degrees
	 *            Degrees to turn.
	 */
	public TurnRobot(double degrees) {
		bearing = AngleTools.getRoundedAngle(degrees);// put the bearing in the [0,360) range.
		requires(driveTrain);
		
		System.out.println("TurnRobot: Robot turning from " + gyro.getAngle() + " to " + bearing + ". A difference of " +
				AngleTools.getAngleDifference(gyro.getAngle(), bearing));

	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//update motor speeds and decide the direction based on the difference
		double diff = AngleTools.getAngleDifference(gyro.getAngle(), bearing),//get the difference
				power = AngleTools.computePower(diff);
		if(diff < 0)//turn left
			driveTrain.setDrive(-1*power, power);
		else
			driveTrain.setDrive(power, -1*power);//turn right.
			

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		double diff = Math.abs(AngleTools.getAngleDifference(gyro.getAngle(), bearing));
		return (diff <= tolerance);//if the difference is within tolerance, consider it done.
	}

	// Called once after isFinished returns true
	protected void end() {
		driveTrain.setDrive(0, 0);//stop the motors.
		System.out.println("TurnRobot: Robot finished turning to " + bearing + " degrees.");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}


}
