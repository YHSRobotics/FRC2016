package org.usfirst.frc.team4161.robot.subsystems;

import org.usfirst.frc.team4161.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
	Victor ballCollector = RobotMap.BallCollector, shooterActuator = RobotMap.ShooterActuator;
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
		shooterActuator.set(0.0);
	}

	/**
	 * Sets the ball tapper to the the extended or retracted state.
	 * 
	 * @param isExtended
	 *            True if ball tapper is extended.
	 */
	public void setBallTapper(boolean isExtended) {
		shooterActuator.set(isExtended ? 0.5 : 0.0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

