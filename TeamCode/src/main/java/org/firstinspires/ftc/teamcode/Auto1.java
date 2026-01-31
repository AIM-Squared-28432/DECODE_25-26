package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@Autonomous (name="auto")
public class Auto1 extends OpMode {

    public Outtake ot = new Outtake();
    public Drivebase db = new Drivebase();
    public ElapsedTime et = new ElapsedTime();
    public AutoState currentState;
    public Pose2D position1 = new Pose2D(DistanceUnit.MM, 0, -1000, AngleUnit.DEGREES, 0);

    @Override
    public void init() {
        ot.init(hardwareMap);
        db.init(hardwareMap);
        currentState = AutoState.DRIVE;
    }
    public void stopRobot() {
        db.frontRight.setPower(0);
        db.frontLeft.setPower(0);
        db.backRight.setPower(0);
        db.backLeft.setPower(0);
    }

    public enum AutoState {
        DRIVE,
        STOP,
        LAUNCHING;
    }



    @Override
    public void loop() {
        ot.loop(gamepad2);

        switch (currentState) {
            case DRIVE:
                db.setTarget(position1);
                db.driveToTarget();
                if (db.moveFinished) {
                    currentState = AutoState.LAUNCHING;
                }
                break;
            case LAUNCHING:
                ot.motorSpinning = true;
                ot.servoSpinning = true;
                et.reset();
                if (et.milliseconds() == 5000) {
                    currentState = AutoState.STOP;
                }
                break;
            case STOP:
                stopRobot();
                ot.motorSpinning = false;
                ot.servoSpinning = false;
                break;
        }

    }
}
