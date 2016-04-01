package org.usfirst.frc.team4161.robot.commands;

import org.usfirst.frc.team4161.robot.ConversionFactor;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ApproachDefense extends CommandGroup {

	private static double startOffsets[] = { -3, 0, 3 }, defenseOffsets[] = { -11.1, -6.66, -2.22, 2.22, 6.66 }, distanceToDefenses = 4;

	/**
	 * Approach and line up with the defense.
	 * 
	 * @param startPosition
	 *            The starting position. 1, 2, or 3.
	 * @param destinationDefense
	 */
	public ApproachDefense(int startPosition, int destinationDefense) {

		// Check for illegal arguments outside of the range.
		if (startPosition < 1 || startPosition > startOffsets.length)
			throw new IllegalArgumentException(
					"The start position must be in the range [1, " + startOffsets.length + "]!");
		if (destinationDefense < 1 || destinationDefense > defenseOffsets.length)
			throw new IllegalArgumentException(
					"The destination defense number must be in the range [1," + defenseOffsets.length + "]!");

		startPosition--;// Make the positions start at zero to align with array
						// indexing.
		destinationDefense--;

		// set up a triangle to compute angle to drive to.
		double opposite = defenseOffsets[destinationDefense] - startOffsets[startPosition];

		// get the angle to drive at.
		double angle = Math.atan(opposite / distanceToDefenses);
		// get the distance to drive.
		double distance = Math.sqrt(Math.pow(opposite, 2) + Math.pow(distanceToDefenses, 2));

		System.out.println("ApproachDefense: Must turn " + angle + "degrees the go " + distance
				+ " feet to reach defense " + destinationDefense + " from start pos " + startPosition);

		addSequential(new TurnRobot(angle));// turn
		addSequential(new DriveStraight(ConversionFactor.feetToTick(distance), 1));// drive
		addSequential(new TurnRobot(-angle));// turn

	}
}
