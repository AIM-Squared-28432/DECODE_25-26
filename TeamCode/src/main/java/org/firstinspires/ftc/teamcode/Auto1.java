package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name="auto")
public class Auto1 extends OpMode {

    public Outtake ot = new Outtake();
    public Drivebase db = new Drivebase();
    public ElapsedTime et = new ElapsedTime();
    public double MOVING_MS = 1000;
    public AutoState currentState;

    @Override
    public void init() {
        et.reset();
        ot.init(hardwareMap);
        db.init(hardwareMap);
        currentState  = AutoState.PARKING;
    }
    public void foward() {
        db.frontRight.setPower(0.25);
        db.frontLeft.setPower(0.25);
        db.backRight.setPower(0.25);
        db.backLeft.setPower(0.25);
    }
    public void backwards() {
        db.frontRight.setPower(-0.25);
        db.frontLeft.setPower(-0.25);
        db.backRight.setPower(-0.25);
        db.backLeft.setPower(-0.25);
    }
    public void right() {
        db.frontRight.setPower(-0.25);
        db.frontLeft.setPower(0.25);
        db.backRight.setPower(0.25);
        db.backLeft.setPower(-0.25);
    }
    public void left() {
        db.frontRight.setPower(0.25);
        db.frontLeft.setPower(-0.25);
        db.backRight.setPower(-0.25);
        db.backLeft.setPower(0.25);
    }
    public void turnRight() {
        db.frontRight.setPower(-0.25);
        db.frontLeft.setPower(0.25);
        db.backRight.setPower(-0.25);
        db.backLeft.setPower(0.25);
    }
    public void turnLeft() {
        db.frontRight.setPower(0.25);
        db.frontLeft.setPower(-0.25);
        db.backRight.setPower(0.25);
        db.backLeft.setPower(-0.25);
    }
    public void stopRobot() {
        db.frontRight.setPower(0);
        db.frontLeft.setPower(0);
        db.backRight.setPower(0);
        db.backLeft.setPower(0);
    }

    public enum AutoState {
        FOWARD,
        BACWARDS,
        RIGHT,
        LEFT,
        PARKING,
        LAUNCHING;
    }



    @Override
    public void loop() {
        ot.loop(gamepad2);
        db.drive(gamepad1);

        switch (currentState) {
            case PARKING:
                break;
            case FOWARD:
                foward();
                break;
            case BACWARDS:
                backwards();
                break;
            case RIGHT:
                right();
                break;
            case LEFT:
                left();
                break;
            case LAUNCHING:
                ot.isSpinning = true;
                break;
        }

        if (et.milliseconds() > 1000) {
            currentState = AutoState.FOWARD;
        } else if (et.milliseconds() > 3000) {
            currentState  = AutoState.PARKING;
        }
    }
}
