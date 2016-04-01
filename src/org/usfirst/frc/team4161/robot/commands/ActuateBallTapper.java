package org.usfirst.frc.team4161.robot.commands;

import org.usfirst.frc.team4161.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ActuateBallTapper extends Command {

	public boolean extend;
	int countdown;
	
	/**
	 * Extend then retract the ball tapper.
	 */
    public ActuateBallTapper() {
    	requires(Robot.shooter);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	countdown = 100;
    	Robot.shooter.setBallTapper(true);
    	System.out.println("ActuateBallTapper: Actuating it...");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	countdown--;
    	if(countdown == 20)
    		Robot.shooter.setBallTapper(false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return countdown <= 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.releaseBallTapper();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		Robot.shooter.setBallTapper(false);//retract the arm
		countdown = 0;//stop the countdown
    }
}
