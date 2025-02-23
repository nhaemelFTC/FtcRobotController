package org.firstinspires.ftc.robotcontroller.internal;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import org.firstinspires.ftc.robotcontroller.internal.Config;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class servoSubsystem {
    private static ElapsedTime runtime = new ElapsedTime();;
    private long squirtMillis = 0;
    private Config config = new Config();
    private Servo trigger;

    public servoSubsystem(HardwareMap hardwareMap){
        config = new Config();
        trigger = hardwareMap.get(Servo.class, config.triggerServoName);
    }

    public enum Triggerstate{
        SQUIRT, RELEASE, CUSTOM
    }
    public servoSubsystem(){

    }
    private Triggerstate state;

    public void init(){
        trigger.resetDeviceConfigurationForOpMode();
        //release();
    }
    //returns the current state of the trigger
    public Triggerstate getState(){
        return state;
    }
    //presses the trigger and starts runtime
    public void squirt(){
        trigger.setPosition(config.triggerSquirtPosition);
        state = Triggerstate.SQUIRT;
        runtime.reset();
    }
    //reloads the servo and stops the runtime
    public void release(){
        trigger.setPosition(config.triggerReleasePosition);
        long addToCount = 0;
        state = Triggerstate.RELEASE;
        addToCount = (long)runtime.milliseconds();
        squirtMillis += addToCount;

    }
    public long getSquirtMillis(){
        return squirtMillis;
    }
    //
    public long getTankPercentage(){
        long returnValue = ((config.squirtGunFullToEmpty - squirtMillis)/300);
        if(returnValue <= 0){
            returnValue = 0;
        }
        return returnValue;
    }


    //
     // Shoots and releases the squirtgun trigger
     //Squirtgun shoots water continuously
     //@param millis how long the shot should be
     //
    public void squirt_and_release(long millis){
        long addToCount = 0;
        squirt();
        runtime.reset();
        while(runtime.milliseconds() < millis){
            addToCount = (long)runtime.milliseconds();
        }
    }
    //sets the trigger servo to a custom (double) value
    public void setPosition(double position){
        trigger.setPosition(position);
        state = Triggerstate.CUSTOM;
    }

}
