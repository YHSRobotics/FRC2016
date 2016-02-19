package org.usfirst.frc.team4161.robot.commands;

public class AngleTools {
	/**
	 * compute the power setting if the robot is angleDifference degrees away
	 * from target.
	 * 
	 * @param angleDifference The difference in angle
	 * @return The power in the range of [.1, 1]
	 */
	public static double computePower(double angleDifference) {
		
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
	public static double getAngleDifference(double heading, double bearing){
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
	public static double getRoundedAngle(double angle) {
		while (angle >= 360)
			angle -= 360;
		while (angle < 0)
			angle += 360;
		return angle;
	}
	
}
