package org.firstinspires.ftc.robotcontroller.internal;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import android.annotation.SuppressLint;

import org.firstinspires.ftc.robotcontroller.internal.CatConfig;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class turretSubsystem {
    public CatConfig catConfig;
    private DcMotor  turret;
    public void init(){
        turret = hardwareMap.get(DcMotor.class, "turret");
        turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turret.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        turret.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public int getPosition(){
        return turret.getCurrentPosition();
    }
    public DcMotorSimple.Direction getDirection(){
        return turret.getDirection();
    }
    public double getPower (){
        return turret.getPower();
    }
    public void runToPosition(int targetPos){
        turret.setTargetPosition(targetPos);
        turret.setPower(0.3);
    }
    public double getDegrees(){
        return turret.getCurrentPosition()/catConfig.ticksPerDegree;
    }
    /**
     * 0 degrees is straight forward
     * @param degrees
     */
    @SuppressLint("SuspiciousIndentation")
    public void runToDegrees(int degrees){
        if(degrees* (int) catConfig.ticksPerDegree <=90 && degrees* (int) catConfig.ticksPerDegree >= -90)
        turret.setTargetPosition(degrees* (int) catConfig.ticksPerDegree);
        turret.setPower(0.3);
    }

}
