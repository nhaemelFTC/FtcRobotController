package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp( name = "ChassisControl")
public class ChassisControl extends OpMode {
    public double axial;
    public double lateral;
    public double yaw;
    DcMotor frontRight;
    DcMotor backRight;
    DcMotor frontLeft;
    DcMotor backLeft;
    public ElapsedTime runtime = new ElapsedTime();


    /**
     * this function takes a long milliseconds parameter and sleeps
     * @param millis milliseconds to sleep
     */
    public void sleepmillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
        }
    }

    /**
     * stops all drive motors
     */
    public void off() {
        frontRight.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
    }
    /**
     * User defined init method
     * This method will be called once when the INIT button is pressed.
     */

    public void init() {


        telemetry.addData("Status","In Init()");
        telemetry.update();
        frontRight = hardwareMap.dcMotor.get("Drive front rt");
        backRight = hardwareMap.dcMotor.get("Drive back rt");
        frontLeft = hardwareMap.dcMotor.get("Drive front lt");
        backLeft = hardwareMap.dcMotor.get("Drive back lt");


    }

    /**
     * User defined init_loop method
     * This method will be called repeatedly when the INIT button is pressed.
     * This method is optional. By default this method takes no action.
     */
    public void init_loop(){
        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }
    /**
     * User defined start method.
     * This method will be called once when the PLAY button is first pressed.
     * This method is optional. By default this method takes not action. Example usage: Starting another thread.
     */
    public void start() {

    }

    /**
     * User defined stop method
     * This method will be called when this op mode is first disabled.
     * The stop method is optional. By default this method takes no action.
     */
    public void stop(){

    }


    /**
     * User defined loop method.
     * This method will be called repeatedly in a loop while this op mode is running
     */
    double num = 1;
    String speed = "";
    public void loop() {

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        if(gamepad1.a){
            num = 3;
            speed = "slow";
        }
        if(gamepad1.b){
            num = 2.5;
            speed = "medium";
        }
        if(gamepad1.y){
            num = 2;
            speed = "fast";
        }
        if(gamepad1.x){
            num = 1.5;
            speed = "Ludicrous";
        }
        if(gamepad1.x && gamepad1.y){
            num = 1;
            speed = "plaid";
        }
        axial   = -gamepad1.left_stick_y/num;  // Note: pushing stick forward gives negative value
        lateral =  gamepad1.left_stick_x/num;
        yaw     =  gamepad1.right_stick_x/(num+0.5);
        // Combine the joystick requests for each axis-motion to determine each wheel's power.
        // Set up a variable for each drive wheel to save the power level for telemetry.
        double leftFrontPower  = axial + lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double leftBackPower   = axial - lateral + yaw;
        double rightBackPower  = axial + lateral - yaw;
        // Normalize the values so no wheel power exceeds 100%
        // This ensures that the robot maintains the desired motion.
        double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));
        if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }
        frontLeft.setPower(leftFrontPower);
        frontRight.setPower(rightFrontPower);
        backLeft.setPower(leftBackPower);
        backRight.setPower(rightBackPower);
        // Show the elapsed game time and wheel power
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Front left, Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
        telemetry.addData("Back  left, Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
        telemetry.addData("Speed", speed);
        telemetry.update();
    }
}