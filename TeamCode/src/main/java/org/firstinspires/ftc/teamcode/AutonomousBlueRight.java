package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by 7416 on 11/5/2017.
 */
@Autonomous(name="Blue Right", group="Autonomous")
public class AutonomousBlueRight extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private ElapsedTime turntime = new ElapsedTime();
    private DcMotor rightDriveFront = null;
    private DcMotor leftDriveFront = null;
    private DcMotor rightDriveBack = null;
    private DcMotor leftDriveBack = null;
    private DcMotor liftMotor = null;
    private DcMotor extendingMotor = null;
    private Servo leftClamp = null;
    private Servo rightClamp = null;
    @Override
    public void runOpMode() {
        rightDriveFront = hardwareMap.get(DcMotor.class, "right_drive_front");
        rightDriveBack = hardwareMap.get(DcMotor.class, "right_drive_back");
        leftDriveFront = hardwareMap.get(DcMotor.class, "left_drive_front");
        leftDriveBack = hardwareMap.get(DcMotor.class, "left_drive_back");
        rightDriveFront.setDirection(DcMotor.Direction.FORWARD);
        rightDriveBack.setDirection(DcMotor.Direction.FORWARD);
        leftDriveFront.setDirection(DcMotor.Direction.REVERSE);
        leftDriveBack.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            double rightPowerFront = 0;
            double rightPowerBack = 0;
            double leftPowerFront = 0;
            double leftPowerBack = 0;
            leftDriveFront.setPower(1.0);
            leftDriveBack.setPower(1.0);
            rightDriveFront.setPower(1.0);
            rightDriveBack.setPower(1.0);
            sleep(1000);
            leftDriveFront.setPower(0);
            leftDriveBack.setPower(0);
            rightDriveFront.setPower(0);
            rightDriveBack.setPower(0);
            break;
        }
    }
}
