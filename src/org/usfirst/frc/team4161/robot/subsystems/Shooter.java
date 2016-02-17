package org.usfirst.frc.team4161.robot.subsystems;

import org.usfirst.frc.team4161.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
	Talon ballCollector = RobotMap.BallCollector;
	Solenoid ballTapOut = RobotMap.BallTapOut, ballTapIn = RobotMap.BallTapIn;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	/**
	 * Set the speed of the collector wheels. A positive value indicates
	 * shooting, a negative value is pulling the ball in..
	 * 
	 * @param speed The speed of the ball collector wheels. Positive is shooting, negative is receiving.
	 */
	public void setCollectorSpeed(double speed) {

		ballCollector.set(speed);

	}
	
	public void releaseBallTapper() {
		ballTapOut.set(false);
		ballTapIn.set(false);
	}

	/**
	 * Sets the ball tapper to the the extended or retracted state.
	 * 
	 * @param isExtended
	 *            True if ball tapper is extended.
	 */
	public void setBallTapper(boolean isExtended) {
		ballTapOut.set(isExtended);
		ballTapIn.set(!isExtended);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

