package org.firstinspires.ftc.robotcontroller.internal;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;

import android.annotation.SuppressLint;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class turretSubsystem {
    public Config config;
    private DcMotorEx  turret;
    private int targetPosition = 0;
    public final static double kp = 0.0015, ki = 0, kd = 0;
    private double lastError = 0;
    private double integralSum = 0;
    private ElapsedTime timer = new ElapsedTime();



    public turretSubsystem(HardwareMap hardwareMap){
        config = new Config();
        turret = hardwareMap.get(DcMotorEx.class, config.turretName);
    }
    private int getTargetPosition() { return targetPosition; }
    public void update() {
        double power = calculatePower();
        if(power < 0.4 && power > 0.05) {
            power *= 2.5;
        }
        if(power > -0.4 && power < 0.05) {
            power *= 2.5;
        }
            turret.setPower(power);
    }
    private double calculatePower() {
        double error = getTargetPosition() - turret.getCurrentPosition();
        /*if(error < 40 && error > 0){
            error = 30;
        }*/

        integralSum += error * timer.seconds();
        double derivative = (error - lastError) / timer.seconds();
        lastError = error;
        timer.reset();
        return (error * kp) + (derivative * kd) + (integralSum * ki);
    }

    //inits and sets the runmodes for the turret motor
    public void init(){
        turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void setTargetPosition(int value) {
            targetPosition = value;
    }
    //resets the turret encoder value to 0
    public void resetEncoder(){
        turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    //returns the current value of the turret
    public int getPosition(){
        return turret.getCurrentPosition();
    }
    //returns the direction (FORWARD or REVERSE) of the turret
    public DcMotorSimple.Direction getDirection(){
        return turret.getDirection();
    }
    //gets the current (double) power value of the turret motor
    public double getPower (){
        return turret.getPower();
    }
    //runs turret to a specified position
    //TODO: function needs limits as to not destroy the hardware
    //returns the current degree value of the turret, 0 is middle,
    // negative is to the left, positive to the right
    public double getDegrees(){
        return turret.getCurrentPosition()/config.turretTicksPerDegree;
    }
    /**
     * 0 degrees is straight forward
     * @param degrees
     */
    //TODO check and refine the degrees
    @SuppressLint("SuspiciousIndentation")
    public void runToDegrees(double degrees) {
        //if(degrees* config.turretTicksPerDegree <=config.maxiumumDegrees && degrees* config.turretTicksPerDegree >= config.minimumDegrees)
        setTargetPosition((int) degrees * (int) config.turretTicksPerDegree);

    }
    //}
    //shuts down the motor until the code is stopped
    public void terminateMotor(){
            turret.setMotorDisable();
    }

}
