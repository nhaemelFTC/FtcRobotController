package org.firstinspires.ftc.robotcontroller.internal;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;

import android.annotation.SuppressLint;

import org.firstinspires.ftc.robotcontroller.internal.Config;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class turretSubsystem {
    private Config config;
    private DcMotorEx  turret;
    //inits and sets the runmodes for the turret motor
    public void init(){
        turret = hardwareMap.get(DcMotorEx.class, config.turretName);
        turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turret.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turret.setMode(DcMotor.RunMode.RUN_TO_POSITION);
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
    public void runToPosition(int targetPos){
        turret.setTargetPosition(targetPos);
        turret.setPower(0.3);
    }
    //returns the current degree value of the turret, 0 is middle,
    // negative is to the left, positive to the right
    public double getDegrees(){
        return turret.getCurrentPosition()/config.ticksPerDegree;
    }
    /**
     * 0 degrees is straight forward
     * @param degrees
     */
    //TODO check and refine the degrees
    @SuppressLint("SuspiciousIndentation")
    public void runToDegrees(double degrees){
        if(degrees* config.ticksPerDegree <=config.maxiumumDegrees && degrees* config.ticksPerDegree >= config.minimumDegrees)
        turret.setTargetPosition((int)degrees * (int)config.ticksPerDegree);
        turret.setPower(0.3);
    }
    //shuts down the motor until the code is stopped
    public void terminateMotor(){
            turret.setMotorDisable();
    }

}
