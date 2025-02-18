package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
@TeleOp(name = "Turret Test", group = "test")
public class TurretTest extends OpMode {
    Gamepad currentGamepad1 = new Gamepad();
    Gamepad currentGamepad2 = new Gamepad();

    Gamepad previousGamepad1 = new Gamepad();
    Gamepad previousGamepad2 = new Gamepad();

    public boolean ticksIsTrue;
    public turretSubsystem turret;
    @Override
    public void init() {
        turret.init();
        ticksIsTrue = true;

    }

    @Override
    public void loop() {
        previousGamepad1.copy(currentGamepad1);
        previousGamepad2.copy(currentGamepad2);
        currentGamepad1.copy(gamepad1);
        currentGamepad2.copy(gamepad2);
        telemetry.addData("degrees:",turret.getDegrees());
        telemetry.addData("ticks:",turret.getPosition());
        telemetry.addData("Run Using Ticks" , ticksIsTrue);
        telemetry.addData("Run Using Degrees", !ticksIsTrue);
        telemetry.update();
        if(currentGamepad1.a && !previousGamepad1.a && ticksIsTrue){
            ticksIsTrue = false;
        } else if(currentGamepad1.a && !previousGamepad1.a && !ticksIsTrue){
            ticksIsTrue = true;
        }
        if(currentGamepad1.dpad_up && !previousGamepad1.dpad_up){
            turret.runToDegrees((int)turret.getDegrees() + 5);
        }
        if(currentGamepad1.dpad_down && !previousGamepad1.dpad_down){
            turret.runToDegrees((int)turret.getDegrees() -5);
        }
    }
}
