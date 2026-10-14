package frc.robot.subsystem;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Subsystem extends SubsystemBase{
    private SubsystemIO io;
    private double setpoint;

    @Override 
    public void periodic() {
        io.updateInputs(inputs);
        Logger.processInputs("Subsystem", inputs);
    }

    private SubsystemIOInputsAutoLogged inputs; 
    public Subsystem(SubsystemIO io) {
        this.io = io;
        inputs = new SubsystemIOInputsAutoLogged(); 
    }

    public Command setSetpoint(double setpoint) {
        return Commands.runOnce(() -> this.io.setVelocity(setpoint));
    }

    

}
