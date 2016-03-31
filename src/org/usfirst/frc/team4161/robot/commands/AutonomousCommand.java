package org.usfirst.frc.team4161.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {
    
    public  AutonomousCommand(int startingPosition, int targetDefense) {
    	
    	//TODO: Add ball pickup?
    	
    	addSequential(new ApproachDefense(startingPosition, targetDefense));//go to the defense
    	addSequential(new InfiltrateDefense());//breach it
    	addSequential(new PointAndShoot(targetDefense));//shoot!
    	
    }
}
