package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion;

public class Outtake {
    private DcMotor launch;
    private CRServo guide;
    private CRServo push;
    public boolean motorSpinning;
    public boolean servoSpinning;

    public void init(HardwareMap hwMap) {
        launch = hwMap.get(DcMotor.class, "launch");
        guide = hwMap.get(CRServo.class, "guide");
        push = hwMap.get(CRServo.class, "push");
        push.setDirection(DcMotorSimple.Direction.REVERSE);
        launch.setDirection(DcMotorSimple.Direction.REVERSE);
        motorSpinning = false;
        servoSpinning = false;
    }

    public void loop(Gamepad gamepad1) {
        if (motorSpinning == true) {
            outtake();
        } else {
            outtakeStop();
        }

        if (servoSpinning == true) {
            servoIn();
        } else {
            servoStop();
        }

    }
    public void servoIn () {
        guide.setPower(1.0);
        push.setPower(1.0);
    }
    public void servoOut () {
        guide.setPower(-1.0);
        push.setPower(-1.0);
    }

    public void servoStop () {
        guide.setPower(0);
        push.setPower(0);
    }

    public void outtake () {
        launch.setPower(1.0);
    }
    public void outtakeOut () {
        launch.setPower(-1);
    }

    public void outtakeStop () {
        launch.setPower(0);
    }
}
