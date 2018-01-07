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
@Autonomous(name="Blue Right test", group="Autonomous")
public class color_test extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    ColorSensor sensor;
    DeviceInterfaceModule cdim;
    static final int LED_CHANNEL = 5;
    public float[] getColor() {
        float hsvValues[] = {0F, 0F, 0F};
        final float values[] = hsvValues;
        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);
        boolean bLedOn = true;
        boolean bCurrState = false;
        boolean bPrevState = false;
        bCurrState = gamepad2.x;

        // check for button-press state transitions.
        if ((bCurrState == true) && (bCurrState != bPrevState)) {

            // button is transitioning to a pressed state. Toggle the LED.
            bLedOn = !bLedOn;
            cdim.setDigitalChannelState(LED_CHANNEL, bLedOn);
        }
        bPrevState = bCurrState;
        cdim = hardwareMap.deviceInterfaceModule.get("dim");
        cdim.setDigitalChannelMode(LED_CHANNEL, DigitalChannel.Mode.OUTPUT);
        sensor = hardwareMap.colorSensor.get("sensor_color");
        cdim.setDigitalChannelState(LED_CHANNEL, bLedOn);
        Color.RGBToHSV((sensor.red() * 255) / 800, (sensor.green() * 255) / 800, (sensor.blue() * 255) / 800, hsvValues);
        telemetry.addData("LED", bLedOn ? "On" : "Off");
        telemetry.addData("Clear", sensor.alpha());
        telemetry.addData("Red  ", sensor.red());
        telemetry.addData("Green", sensor.green());
        telemetry.addData("Blue ", sensor.blue());
        telemetry.addData("Hue", hsvValues[0]);
        relativeLayout.post(new Runnable() {
            public void run() {
                relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
            }
        });
        telemetry.update();
        return values;
    }
    @Override
    public void runOpMode() {

        waitForStart();
        runtime.reset();
        while (opModeIsActive()) {
            double rightPowerFront = 0;
            double rightPowerBack = 0;
            double leftPowerFront = 0;
            double leftPowerBack = 0;
            if (runtime.seconds() < 5) {
                //Image Scanning code here
            }
            getColor();
        }
    }
}