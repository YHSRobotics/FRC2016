package org.usfirst.frc.team4161.robot.commands;

import org.usfirst.frc.team4161.robot.ConversionFactor;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {
    
    public  AutonomousCommand(int startingPosition, int targetDefense) {
    	
    	//TODO: Add ball pickup?
    	if(startingPosition == 3){
    		addSequential(new DriveStraight(ConversionFactor.feetToTick(10), true));
    	}
    	else{
    		addSequential(new ApproachDefense(startingPosition, targetDefense));//go to the defense
    		addSequential(new InfiltrateDefense());//breach it
    		addSequential(new PointAndShoot(targetDefense));//shoot!
    	}
    	
    }
}
