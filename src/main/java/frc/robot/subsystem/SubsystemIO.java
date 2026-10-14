// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystem;

import static edu.wpi.first.units.Units.Celsius;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.measure.Temperature;

/** Add your docs here. */
public interface SubsystemIO {
    @AutoLog 
    public static class SubsystemIOInputs {
        public double setpoint = 0.0; 

        public double voltage = 0.0; 

        public double velocity = 0.0; 

        public Temperature temp = Celsius.zero();
        
        public double stator = 0.0; 

        public double supply = 0.0; 

        public boolean connected = false; 

    }

    public default void setVelocity(double setpoint) {} 

    public default void updateInputs(SubsystemIOInputs inputs) {} 


}
