
package org.usfirst.frc.team4161.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team4161.robot.commands.ActuateBallTapper;
import org.usfirst.frc.team4161.robot.commands.AutonomousCommand;
import org.usfirst.frc.team4161.robot.commands.DriveStraight;
import org.usfirst.frc.team4161.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team4161.robot.commands.RotateShooterArmWithJoystick;
import org.usfirst.frc.team4161.robot.commands.SpinUpCollector;
import org.usfirst.frc.team4161.robot.commands.TurnRobot;
import org.usfirst.frc.team4161.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4161.robot.subsystems.Shooter;
import org.usfirst.frc.team4161.robot.subsystems.ShooterArm;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DriveTrain driveTrain = new DriveTrain();
	public static final ShooterArm shooterArm = new ShooterArm();
	public static final Shooter shooter = new Shooter();
	public static OI oi;

	Command autonomousCommand;
	Command driveWithJoystick, aimWithJoystick;
	SendableChooser startPosChooser, defensePosChooser;
	Preferences prefs;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();

		startPosChooser = new SendableChooser();
		startPosChooser.addDefault("1", 1);
		startPosChooser.addObject("2", 2);
		startPosChooser.addObject("3", 3);
		startPosChooser.addObject("Straight Forward", 4);
		startPosChooser.addObject("No Autonomous", 5);

		defensePosChooser = new SendableChooser();
		defensePosChooser.addDefault("1", 1);
		defensePosChooser.addObject("2", 2);
		defensePosChooser.addObject("3", 3);
		defensePosChooser.addObject("4", 4);

		prefs = Preferences.getInstance();

		prefs.putInt("DriveTickCount", 100);
		prefs.putInt("TurnTickCount", 100);

		SmartDashboard.putData("Starting Position", startPosChooser);
		SmartDashboard.putData("Desired Defense", defensePosChooser);
		SmartDashboard.putData("Drive With Joystick", new DriveWithJoystick(OI.DriveJoystick));
		SmartDashboard.putData("Aim With Joystick", new RotateShooterArmWithJoystick(OI.AimJoystick));
		SmartDashboard.putData("Drive for x ticks", new DriveStraight(prefs));
		SmartDashboard.putData("Turn for x ticks", new TurnRobot(prefs));
		SmartDashboard.putData("Shooter Test", new SpinUpCollector(1, false));
		SmartDashboard.putData("Collector Test", new SpinUpCollector(-.5, false));
		SmartDashboard.putData("Deactivate Shooter/Collector", new SpinUpCollector(0, false));
		SmartDashboard.putData("Actuate Ball-Tapper", new ActuateBallTapper());

		RobotMap.LTread.setInverted(true);// make the left tread go forward at
											// 1.0
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		// obligatory comment.

		if (driveWithJoystick != null)
			driveWithJoystick.cancel();// disable joystick following.
		if (aimWithJoystick != null)
			aimWithJoystick.cancel();
		driveTrain.setDrive(0, 0);
		shooterArm.ArmRotate(0);

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		int startPos = (Integer) startPosChooser.getSelected(), defensePos = (Integer) defensePosChooser.getSelected();
		autonomousCommand = new AutonomousCommand(startPos, defensePos);
		System.out.println("Autonomous started: penetrate defense " + defensePos + " from start pos" + startPos);

		//autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();

		// start the autonomous commands.
		driveWithJoystick = new DriveWithJoystick(OI.DriveJoystick);
		driveWithJoystick.start();// start it!
		aimWithJoystick = new RotateShooterArmWithJoystick(OI.AimJoystick);
		aimWithJoystick.start();// start it!

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {	
		Scheduler.getInstance().run();// this will run all current commands.
		SmartDashboard.putNumber("L: ",OI.DriveJoystick.getRawAxis(1));
		SmartDashboard.putNumber("R: ",OI.DriveJoystick.getRawAxis(5));
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
		Scheduler.getInstance().run();//run the scheduler.
	}
}
