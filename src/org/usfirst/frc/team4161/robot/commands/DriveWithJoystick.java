package org.usfirst.frc.team4161.robot.commands;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4161.robot.Robot;

/**
 *
 */
public class DriveWithJoystick extends Command {

	private Joystick L, R, XBox;
	private boolean isXbox;

	/**
	 * Start a command to follow the joystick movements and control the robot.
	 * @param left Left joystick.
	 * @param right Right Joystick.
	 */
    public DriveWithJoystick(Joystick left, Joystick right) {
    	requires(Robot.driveTrain);
    	L = left;
    	R = right;
    	isXbox = false;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    /**
     * Start a command to follow the xbox joystick movements to control the
     * robot treads.
     * @param joystick the joystick to read from.
     */
    public DriveWithJoystick(Joystick joystick){
    	XBox = joystick;
    	isXbox = true;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(isXbox)
    		Robot.driveTrain.setDrive(XBox.getRawAxis(1), XBox.getRawAxis(5));
    	else
    		Robot.driveTrain.setDrive(L.getY(), R.getY());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
