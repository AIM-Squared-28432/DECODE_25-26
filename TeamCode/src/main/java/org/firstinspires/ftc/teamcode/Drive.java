package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name="Drive")
public class Drive extends OpMode {

    public Drivebase db = new Drivebase();

    public void init() {
        db.init(hardwareMap);
    }

    public void loop() {
        db.drive(gamepad1);
    }

}
