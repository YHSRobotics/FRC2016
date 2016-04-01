package org.usfirst.frc.team4161.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4161.robot.Robot;
/**
 *
 */
public class SpinUpCollector extends Command {

	private int countdown;
	private double speed;
	
	/**
	 * Spin up the collector arm.
	 * @param speed the speed to spinup to. Positive is shoot, Negative is collect..
	 * @param block if true, the command will wait to spin up. Else it will end right away.
	 */
    public SpinUpCollector(double speed, boolean block) {
    	requires(Robot.shooter);
    	this.speed = speed;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	countdown = 200;
    	Robot.shooter.setCollectorSpeed(speed);
    	System.out.println("SpinUpCollector: Spinning up collector to " + speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	countdown--;//decrement counter.
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return countdown <= 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("SpinUpCollector: The collector is spun up! Power: " + speed);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	countdown = 0;//stop it!
    }
}
