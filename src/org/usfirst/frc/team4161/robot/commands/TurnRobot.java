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
		bearing = angleRound(degrees);// put the bearing in the [0,360) range.
		requires(driveTrain);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//update motor speeds and decide the direction based on the difference
		double diff = angleDifference(gyro.getAngle(), bearing),//get the difference
				power = analyzePower(diff);
		if(diff < 0)//turn left
			driveTrain.setDrive(-1*power, power);
		else
			driveTrain.setDrive(power, -1*power);//turn right.
			

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		double diff = Math.abs(angleDifference(gyro.getAngle(), bearing));
		return (diff <= tolerance);//if the difference is within tolerance, consider it done.
	}

	// Called once after isFinished returns true
	protected void end() {
		driveTrain.setDrive(0, 0);//stop the motors.
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	/**
	 * compute the power setting if the robot is angleDifference degrees away
	 * from target.
	 * 
	 * @param angleDifference The difference in angle
	 * @return The power in the range of [.1, 1]
	 */
	private double analyzePower(double angleDifference) {
		
		if(angleDifference < 0)
			angleDifference *= -1;//make sure it is positive.
		double power = Math.pow(2, angleDifference/15) - 1;//use the power equation
		if (power > 1)
			return 1;//power is over 1
		else if (power > .1)//power is between .1 and 1
			return power;
		else//power is below 1
			return .1;

	}

	/**
	 * Determine the angle difference between the heading and the bearing. (in degrees)
	 * @param heading Heading of the robot
	 * @param bearing Desired bearing.
	 * @return Difference in heading in range (-180, 180]
	 */
	private double angleDifference(double heading, double bearing){
		double ret = bearing - heading;
		while(ret <= -180)//get it above -180
			ret += 360;
		while (ret > 180)//get it below 180
			ret -= 360;
		return ret;
	}
	
	/**
	 * get the angle within the range of [0, 360)
	 * 
	 * @param angle
	 *            Angle to 'round'
	 * @return the 'rounded' angle.
	 */
	private double angleRound(double angle) {
		while (angle >= 360)
			angle -= 360;
		while (angle < 0)
			angle += 360;
		return angle;
	}
}
