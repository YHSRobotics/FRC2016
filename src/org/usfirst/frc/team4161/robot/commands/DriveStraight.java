package org.usfirst.frc.team4161.robot.commands;

import org.usfirst.frc.team4161.robot.Robot;
import org.usfirst.frc.team4161.robot.RobotMap;
import org.usfirst.frc.team4161.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {
	
	private DriveTrain driveTrain = Robot.driveTrain;
	private AnalogGyro gyro = RobotMap.gyro;

	private int ticks;
	private double heading;
	/**
	 * Drive straight forward for x number of ticks.
	 * @param ticks Number of ticks to drive forward for.
	 */
    public DriveStraight(int ticks) {
    	this.ticks = ticks;
    	requires(driveTrain);//uses the drive train.
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveTrain.setDrive(1, 1);
    	heading = RobotMap.gyro.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	ticks--;
    	double diff = AngleTools.getAngleDifference(gyro.getAngle(), heading);
    	if(diff < -1)//correct left
    		driveTrain.setDrive(1 - AngleTools.computePower(diff), 1);
    	else if(diff > 1)//correct right.
    		driveTrain.setDrive(1, 1 - AngleTools.computePower(diff));
    	else
    		driveTrain.setDrive(1, 1);//go straight.
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ticks <= 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
