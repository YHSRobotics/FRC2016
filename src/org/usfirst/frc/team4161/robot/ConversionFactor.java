package org.usfirst.frc.team4161.robot;

public class ConversionFactor {

	public static double ticksToFeet = 1, ticksToDegree = 10;

	/**
	 * Given the desired degree, returns the number of (estimated) ticks to turn
	 * the arm for at full power.
	 * 
	 * @param degrees
	 *            Desired degree of arm turn. Negatives are ignored
	 * @return The number of (estimated) ticks to turn arm for.
	 */
	public static int degreeToTick(double degrees) {
		return (int) Math.abs(Math.round(Math.abs(degrees) * ticksToDegree));
	}

	/**
	 * Given the desired distance, returns the number of (estimated) ticks to
	 * drive robot at full power.
	 * 
	 * @param feet
	 *            Desired distance to drive robot. Negatives are ignored
	 * @return The number of (estimated) ticks to drive for.
	 */
	public static int feetToTick(double feet) {
		return (int) Math.abs(Math.round(Math.abs(feet) * ticksToFeet));
	}

}
