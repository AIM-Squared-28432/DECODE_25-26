package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous (name="auto")
public class Auto1 extends OpMode {

    public Outtake ot = new Outtake();
    public Drivebase db = new Drivebase();
    public ElapsedTime et = new ElapsedTime();
    public AutoState currentState;

    @Override
    public void init() {
        ot.init(hardwareMap);
        db.init(hardwareMap);
        et.reset();
        currentState  = AutoState.STOP;
    }
    public void backwards() {
        db.frontRight.setPower(-1.0);
        db.frontLeft.setPower(-1.0);
        db.backRight.setPower(-1.0);
        db.backLeft.setPower(-1.0);
    }
    public void stopRobot() {
        db.frontRight.setPower(0);
        db.frontLeft.setPower(0);
        db.backRight.setPower(0);
        db.backLeft.setPower(0);
    }

    public enum AutoState {
        BACkWARDS,
        STOP,
        LAUNCHING,
        PARKING;
    }



    @Override
    public void loop() {
        ot.loop(gamepad2);
        db.drive(gamepad1);

        switch (currentState) {
            case STOP:
                ot.motorSpining = false;
                ot.servoSpinning = false;
                if (et.milliseconds() > 1000) {
                    ot.motorSpining = true;
                    currentState = AutoState.BACkWARDS;
                }
                break;
            case BACkWARDS:
                backwards();
                if (et.milliseconds() > 3000) {
                    currentState = AutoState.LAUNCHING;
                }
                break;
            case LAUNCHING:
                if (ot.motorSpining == true) {
                    ot.servoSpinning = true;
                }
                if (et.milliseconds() > 10000) {
                    currentState = AutoState.STOP;
                }
                break;
        }

    }
}
