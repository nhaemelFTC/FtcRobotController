package org.firstinspires.ftc.robotcontroller.internal;
import org.firstinspires.ftc.robotcontroller.internal.Config;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class servoSubsystem {
    private static ElapsedTime runtime = new ElapsedTime();;

    private long squirtMillis = 0;
    private Config config = new Config();

    public enum Triggerstate{
        SQUIRT, RELEASE, CUSTOM
    }
    private Triggerstate state;
    private Servo trigger;
    public void init(){
        trigger.resetDeviceConfigurationForOpMode();
        release();
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
        long returnValue = 100*(config.squirtGunFullToEmpty - squirtMillis);
        if(returnValue <= 0){
            returnValue = 0;
        }
        return returnValue;
    }


    /**
     * Shoots and releases the squirtgun trigger
     * Squirtgun shoots water continuously
     * @param millis how long the shot should be
     */
    public void squirt_and_release(long millis){
        long addToCount = 0;
        squirt();
        runtime.reset();
        while(runtime.milliseconds() < millis){
            addToCount = (long)runtime.milliseconds();
        }
        if(runtime.milliseconds() > millis){
            release();
            squirtMillis += addToCount;
        }
    }
    //sets the trigger servo to a custom (double) value
    public void setPosition(double position){
        trigger.setPosition(position);
        state = Triggerstate.CUSTOM;
    }

}
