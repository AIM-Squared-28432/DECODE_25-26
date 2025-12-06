package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp (name="Launch")
public class Launch extends OpMode {

    public Outtake ot = new Outtake();

    public void init() {
        ot.init(hardwareMap);
    }

    public void loop() {
        ot.loop(gamepad2);
    }

}
