package org.usfirst.frc.team4161.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team4161.robot.commands.ActuateBallTapper;
import org.usfirst.frc.team4161.robot.commands.RotateFallbackWheels;
import org.usfirst.frc.team4161.robot.commands.SpinUpCollector;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	public static Joystick LJoystick = new Joystick(0), RJoystick = new Joystick(0), AimJoystick = new Joystick(0);

	Button fireActuator = new JoystickButton(null, 0), collectorSpinup = new JoystickButton(null, 0),
			shooterSpinup = new JoystickButton(null, 0), fbDeploy = new JoystickButton(null, 0),
			fbRetract = new JoystickButton(null, 0);

	public OI() {
		fireActuator.whenPressed(new ActuateBallTapper());
		collectorSpinup.whenPressed(new SpinUpCollector(-1, false));
		shooterSpinup.whenPressed(new SpinUpCollector(1, false));
		collectorSpinup.whenReleased(new SpinUpCollector(0, false));// if
																	// release
																	// buttons.
																	// stop
																	// spinup.
		shooterSpinup.whenReleased(new SpinUpCollector(0, false));
		fbDeploy.whileHeld(new RotateFallbackWheels(1));
		fbRetract.whileHeld(new RotateFallbackWheels(-1));
	}

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
