package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name="TeleOp")
public class TeleOp extends OpMode {

    public Drivebase db = new Drivebase();
    public Outtake ot = new Outtake();

    public void init() {
        db.init(hardwareMap);
        ot.init(hardwareMap);
    }

    public void loop() {
        db.drive(gamepad1);
        ot.loop(gamepad1);
        if (gamepad1.y) {
            if (ot.ismotorSpinning == true) {
                ot.ismotorSpinning = false;
            }  else {
                ot.ismotorSpinning = true;
            }
        }
        if(gamepad1.a){
            if(ot.isServoSpinning == true){
                ot.isServoSpinning = false;
            } else {
                ot.isServoSpinning= true;
            }
        }
    }
}
