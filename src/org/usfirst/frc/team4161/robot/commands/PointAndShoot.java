package org.usfirst.frc.team4161.robot.commands;

import org.usfirst.frc.team4161.robot.ConversionFactor;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PointAndShoot extends CommandGroup {

	private static double defenseOffset[] = { -11.1, -6.66, -2.22, 2.22, 6.66 }, towerDistance = 191.5, desiredDistance = 5,
			desiredAngle = 5;

	/**
	 * Navigate to and shoot at the tower from the startDefense.
	 * @param startDefense The defense the robot is currently past.
	 */
    public  PointAndShoot(int startDefense) {
    	
    	//compute the turning angle and distance to the tower
    	double angle = Math.atan(defenseOffset[startDefense]/towerDistance),
    			distance = Math.sqrt(Math.pow(towerDistance, 2) + Math.pow(defenseOffset[startDefense], 2));
    	
    	distance -= desiredDistance;//don't go all the way to the tower.
    	
    	System.out.println("PointAndShoot: Must turn " + desiredAngle + " degrees and drive " + distance
    			+ " feet to get to the tower from defense #" + startDefense + ".");
    	
    	//rotate the arm to the distance.
    	addParallel(new RotateShooterArm(ConversionFactor.armDegreesToTick(desiredAngle), true));
    	//turn to the desired angle
    	addSequential(new TurnRobot(angle));
    	//drive up to the 
    	addSequential(new DriveStraight(ConversionFactor.feetToTick(distance), true));
    	addSequential(new FireBall());//fire the ball!
    	
    }
}
