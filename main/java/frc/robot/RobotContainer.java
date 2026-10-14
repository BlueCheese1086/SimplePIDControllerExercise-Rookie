// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystem.Subsystem;
import frc.robot.subsystem.SubsystemIOSim;

public class RobotContainer {
  private static final CommandXboxController driver = new CommandXboxController(1); 
  private final Subsystem subsystem; 
  public RobotContainer() {
    
    subsystem = new Subsystem(new SubsystemIOSim()); 
    configureBindings();
  }

  private void configureBindings() {

    driver.povDown().onTrue(subsystem.setSetpoint(0.0));
    driver.povLeft().onTrue(subsystem.setSetpoint(5.0));
    driver.povUp().onTrue(subsystem.setSetpoint(10.0));
    driver.povRight().onTrue(subsystem.setSetpoint(15.0));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
