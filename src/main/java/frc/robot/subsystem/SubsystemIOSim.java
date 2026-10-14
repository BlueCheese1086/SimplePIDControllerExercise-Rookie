// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystem;

import static edu.wpi.first.units.Units.Celsius;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;
import frc.robot.util.LoggedTunableNumber;

/** Add your docs here. */
public class SubsystemIOSim implements SubsystemIO{
    private DCMotorSim motor; 

    private PIDController controller; 
    private SimpleMotorFeedforward feedForward;

    private static final LoggedTunableNumber kP = new LoggedTunableNumber("Subsystem/kP", 0.0); 
    private static final LoggedTunableNumber kI = new LoggedTunableNumber("Subsystem/kI", 0.0); 
    private static final LoggedTunableNumber kD = new LoggedTunableNumber("Subsystem/kD", 0.0); 
    private static final LoggedTunableNumber kV = new LoggedTunableNumber("Subsystem/kV", 0.0);

    private static final LoggedTunableNumber SETPOINT = new LoggedTunableNumber("Subsystem/SETPOINT", 0.0);

    private double setpoint = 0.0; 
    private boolean loop = true; 

    public SubsystemIOSim() {
        controller = new PIDController(kP.get(), kI.get(), kD.get()); 
        feedForward = new SimpleMotorFeedforward(0.0, kV.get());

        motor = new DCMotorSim(LinearSystemId.createDCMotorSystem(DCMotor.getKrakenX60(1), 0.001, 10.0), DCMotor.getKrakenX60(1)); 
    }

    @Override 
    public void setVelocity(double setpoint) {}

    @Override 
    public void updateInputs(SubsystemIOInputs inputs) {

        this.setpoint = SETPOINT.get(); 
        controller.setSetpoint(this.setpoint);
        
        controller.setP(kP.getAsDouble()); 
        controller.setI(kI.getAsDouble()); 
        controller.setD(kD.getAsDouble()); 
        feedForward.setKv(kV.getAsDouble());

        double output = controller.calculate(motor.getAngularVelocityRadPerSec()); 
        double feed = feedForward.calculate(motor.getAngularVelocityRadPerSec()); 

        motor.setInputVoltage(output + feed);

        motor.update(0.02);

        inputs.voltage = motor.getInputVoltage(); 
        inputs.velocity = motor.getAngularVelocityRadPerSec(); 
        inputs.temp = Celsius.of(67); 
        inputs.setpoint = this.setpoint; 

        Logger.recordOutput("Subsystem/Voltage", inputs.voltage); 
        Logger.recordOutput("Subsystem/Velocity", inputs.velocity); 
        Logger.recordOutput("Subsystem/Temp", inputs.temp); 
        Logger.recordOutput("Subsystem/Setpoint", inputs.setpoint); 


    }

}
