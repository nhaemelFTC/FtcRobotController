package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Config {
    //motor declerations
    public static final String turretName = "turretMotor";
    public static final double turretMotorSpeed = 1;
    private static final int MotorRpm = 223;
    private static final double ticksPerRevolution = 751.8; //537.7
    private static final double ticksPerDegree = ticksPerRevolution/360; //
    private static final double degreesPerTick = 360/ticksPerRevolution;
    public static final double minimumDegrees = -150;
    public static final double maxiumumDegrees = 150;
    //servo declerations
    public static final String triggerServoName = "triggerServo";
    public static final double triggerReleasePosition = 0.0;
    public static final double triggerSquirtPosition = 1.0;
    //squirtgun declerations
    public static final double gearRatio = 0.2;
    public static final long squirtGunFullToEmpty = 30000;//milliseconds
    //turret declerations
    public static final double turretRpm = MotorRpm* (1/gearRatio);
    public static final double turretTicksPerRevolution = ticksPerRevolution *(1/gearRatio);
    public static final double turretTicksPerDegree = ticksPerDegree*(1/gearRatio);
    public static final double turretDegreesPerTick = degreesPerTick *(1/gearRatio);
}
