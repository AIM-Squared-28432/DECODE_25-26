package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Outtake {
    private DcMotor outtake;
    private boolean isSpinning;

public void init(){
    outtake = hardwareMap.get(DcMotor.class, "outtake");
    isSpinning = false;
}
public void loop(Gamepad gamepad2){
    if (gamepad2.y) {
        if (isSpinning == false) {
            outtake.setPower(1.0);
            isSpinning = true;
        } else {
            outtake.setPower(0);
        }
   }
}
}
