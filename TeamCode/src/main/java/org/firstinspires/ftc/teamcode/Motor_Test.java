package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.concurrent.TimeUnit;

@Autonomous(name="Motor_test")
public class Motor_Test extends OpMode {
    DcMotor hwMotorDriveFrontLeft;
    DcMotor hwMotorDriveFrontRight;

    DcMotor hwMotorDriveBackLeft;
    DcMotor hwMotorDriveBackRight;

    public void init() {
        hwMotorDriveFrontLeft = hardwareMap.dcMotor.get("Drive front lt");
        hwMotorDriveFrontRight = hardwareMap.dcMotor.get("Drive front rt");
        hwMotorDriveBackLeft = hardwareMap.dcMotor.get("Drive back lt");
        hwMotorDriveBackRight = hardwareMap.dcMotor.get("Drive back rt");
    }

    public void sleepSec(int iSecs){
        try {
            Thread.sleep(iSecs*1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public void loop() {
        hwMotorDriveFrontLeft.setPower(1);
        sleepSec(1);
        hwMotorDriveFrontRight.setPower(1);
        sleepSec(1);
        hwMotorDriveBackLeft.setPower(1);
        sleepSec(1);
        hwMotorDriveBackRight.setPower(1);
        sleepSec(1);
        hwMotorDriveFrontLeft.setPower(0);
        hwMotorDriveFrontRight.setPower(0);
        hwMotorDriveBackLeft.setPower(0);
        hwMotorDriveBackRight.setPower(0);
        sleepSec(10);
    }

}