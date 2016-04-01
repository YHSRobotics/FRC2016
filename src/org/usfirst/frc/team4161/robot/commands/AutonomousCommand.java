package org.usfirst.frc.team4161.robot.commands;

import org.usfirst.frc.team4161.robot.ConversionFactor;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {
    
    public  AutonomousCommand(int startingPosition, int targetDefense) {
    	
    	//TODO: Add ball pickup?
    	if(startingPosition == 6){//go straight is selected
    		addSequential(new DriveStraight(5.0, 1.0));
    	}
    	else if(startingPosition == 7){//do nothing is selected.
    		//do nothing.
    	}
    	else{
    		addSequential(new ApproachDefense(startingPosition, targetDefense));//go to the defense
    		addSequential(new InfiltrateDefense());//breach it
    		addSequential(new PointAndShoot(targetDefense));//shoot!
    	}
    	
    }
}
