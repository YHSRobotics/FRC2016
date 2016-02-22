package org.usfirst.frc.team4161.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FireBall extends CommandGroup {
    
    public  FireBall() {
    	addSequential(new SpinUpCollector(1, true));//spin up the motors
    	addSequential(new ActuateBallTapper());//fire the ball.
    	System.out.println("FireBall: Ball Fired!");
    	addSequential(new SpinUpCollector(0, false));//stop the spinup motors.
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
