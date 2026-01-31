package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp")
public class TeleOp extends OpMode {

    public Drivebase db = new Drivebase();
    public Outtake ot = new Outtake();
    public Intake it = new Intake();

    public void init() {
        db.init(hardwareMap);
        ot.init(hardwareMap);
        it.init(hardwareMap);
    }

    public void loop() {
        db.drive(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
        ot.loop(gamepad1);
        it.loop(gamepad1);

        if (gamepad1.y) {
            if (ot.motorSpinning == true) {
                ot.motorSpinning = false;
            } else {
                ot.motorSpinning = true;
            }
        }
        if (gamepad1.a) {
            if (ot.servoSpinning == true) {
                ot.servoSpinning = false;
            } else {
                ot.servoSpinning = true;
            }
        }
        if (gamepad1.b) {
            if (it.intakeSpinning == true) {
                it.intakeSpinning = false;
            } else {
                it.intakeSpinning = true;
            }
        }
        if (gamepad1.x) {
            if (it.intakeSpinning == true) {
                it.motor.setDirection(DcMotorSimple.Direction.REVERSE);
                it.intakeSpinning = false;
            } else {
                it.motor.setDirection(DcMotorSimple.Direction.FORWARD);
                it.intakeSpinning = true;
            }
        }
    }
}
