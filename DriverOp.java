package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by 7416 on 10/30/2017.
 */
@TeleOp(name="DriverOp", group="Linear Opmode")
public class DriverOp extends LinearOpMode {

    //create necessary variables for motors
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor rightDriveFront = null;
    private DcMotor leftDriveFront = null;
    private DcMotor rightDriveBack = null;
    private DcMotor leftDriveBack = null;
    private DcMotor liftMotor = null;
    private DcMotor extender = null;
    //private DcMotor extendingMotor = null;
    private DcMotor mainClamp = null;
    private Servo left = null;
    private Servo right = null;
    private Servo release = null;
    public boolean hasExtended = false;
    public void runOpMode() {

        //update status
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //initialize motors and main program
        rightDriveFront = hardwareMap.get(DcMotor.class, "right_drive_front");
        rightDriveBack = hardwareMap.get(DcMotor.class, "right_drive_back");
        leftDriveFront = hardwareMap.get(DcMotor.class, "left_drive_front");
        leftDriveBack = hardwareMap.get(DcMotor.class, "left_drive_back");
        liftMotor = hardwareMap.get(DcMotor.class, "lift");
        extender = harwareMap.get(DcMotor.class, "extender");
        //extendingMotor = hardwareMap.get(DcMotor.class, "left-drive_extend");
        left = hardwareMap.get(Servo.class, "left_hand");
        right = hardwareMap.get(Servo.class, "right_hand");
        release = hardwareMap.get(Servo.class, "release_lift");
        rightDriveFront.setDirection(DcMotor.Direction.FORWARD);
        rightDriveBack.setDirection(DcMotor.Direction.FORWARD);
        leftDriveFront.setDirection(DcMotor.Direction.REVERSE);
        leftDriveBack.setDirection(DcMotor.Direction.REVERSE);
        liftMotor.setDirection(DcMotor.Direction.FORWARD);
        extender.setDirection(DcMotor.Direction.FORWARD);

        //wait for program start
        waitForStart();
        runtime.reset();

        //checks if the op mode is running
        while (opModeIsActive()) {

            //initialize power variables
            double rightPowerFront;
            double rightPowerBack;
            double leftPowerFront;
            double leftPowerBack;
            double liftPower;
            double extPower;
            double extenderPower;
            double drive = -gamepad1.left_stick_y;
            double turn  =  gamepad1.right_stick_x;
            double lift = -gamepad2.left_stick_y;
            //re-initialize power vars
            leftPowerFront = Range.clip(-gamepad1.left_stick_y, -1.0, 1.0);
            leftPowerBack = Range.clip(-gamepad1.left_stick_y, -1.0, 1.0);
            rightPowerFront = Range.clip(-gamepad1.right_stick_y, -1.0, 1.0);
            rightPowerBack = Range.clip(-gamepad1.right_stick_y, -1.0, 1.0);
            liftPower = Range.clip(lift, -1.0, 1.0);
            rightDriveFront.setPower(rightPowerFront);
            rightDriveBack.setPower(rightPowerBack);
            leftDriveFront.setPower(leftPowerFront);
            leftDriveBack.setPower(leftPowerBack);
            liftMotor.setPower(liftPower);
            extender.setPower(extenderPower);
            //lift component
            if (gamepad2.left_bumper) {
                left.setPosition(0.5);
                right.setPosition(1);
            }
            if (gamepad2.right_bumper) {
                left.setPosition(1);
                right.setPosition(0.5);
            }
            if (gamepad2.a) {
                release.setPosition(1);
            }
            if (gamepad2.x) {
                if (hasExtended) {
                    extendedPower = -1.0;
                    sleep(5000);
                    hasExtended = false;
                } else if (!hasExtended) {
                    extenderPower = 1.0;
                    sleep(5000);
                    hasExtended = true;
                }
            }
            //update telemetry
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left front (%.2f), right front(%.2f), left back (%.2f), right back (%.2f), lift (%.2f)", leftPowerFront, rightPowerFront, leftPowerBack, rightPowerBack, liftPower);
            telemetry.addData("Game Pad 1 right stick y", "%.2f", gamepad1.right_stick_y);
            telemetry.update();
        }
    }
}
