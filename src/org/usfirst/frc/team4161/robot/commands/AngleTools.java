package org.usfirst.frc.team4161.robot.commands;

public class AngleTools {
	/**
	 * compute the power setting if the robot is angleDifference degrees away
	 * from target. Essentially calls computePower(angleDifference, 0.1, 1)
	 * 
	 * @param angleDifference
	 *            The difference in angle
	 * @return The power in the range of [.1, 1]
	 */
	public static double computePower(double angleDifference) {

		return computePower(angleDifference, 0.1, 1);

	}
	/**
	 * compute the power setting if the robot is angleDifference degrees away
	 * from target. Essentially calls computePower(angleDifference, 0.1, maxPower)
	 * 
	 * @param angleDifference
	 *            The difference in angle
	 * @param maxPower The maximum power value to return.
	 * @return The power in the range of [.1, maxPower]
	 */
	public static double computePower(double angleDifference, double maxPower){
		return computePower(angleDifference, 0.1, 1);
	}
	
	/**
	 * Compute the power setting of the robot when approaching a desired
	 * heading/distance. The lower the angleDifference, the lower the computed
	 * power. The returned power is in the range [minPower, maxPower].
	 * 
	 * @param angleDifference The difference between the heading/bearing of the robot.
	 * @param minPower Minimum power to return.
	 * @param maxPower Maximum power to return.
	 * @return
	 */
	public static double computePower(double angleDifference, double minPower, double maxPower) {

		if (angleDifference < 0)
			angleDifference *= -1;// make sure it is positive.
		double power = Math.pow(2, angleDifference / 15) - 1;// use the power
																// equation
		if (power > maxPower)
			return maxPower;// power is over 1
		else if (power > minPower)// power is between .1 and 1
			return power;
		else// power is below 1
			return minPower;

	}

	/**
	 * Determine the angle difference between the heading and the bearing. (in
	 * degrees)
	 * 
	 * @param heading
	 *            Heading of the robot
	 * @param bearing
	 *            Desired bearing.
	 * @return Difference in heading in range (-180, 180]
	 */
	public static double getAngleDifference(double heading, double bearing) {
		double ret = bearing - heading;
		while (ret <= -180)// get it above -180
			ret += 360;
		while (ret > 180)// get it below 180
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
