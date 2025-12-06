package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion;

public class Outtake {
    private DcMotor launch;
    private CRServo guide;
    private CRServo push;
    public boolean isSpinning;

public void init(HardwareMap hwMap) {
    launch = hwMap.get(DcMotor.class, "launch");
    guide = hwMap.get(CRServo.class, "guide");
    push = hwMap.get(CRServo.class, "push");
    launch.setDirection(DcMotorSimple.Direction.REVERSE);
    isSpinning = false;
    launch.setPower(0);
}
public void loop(Gamepad gamepad1){

        if (isSpinning == true) {
            launch.setPower(1.0);
            guide.setPower(1.0);
            push.setPower(1.0);
        } else {
            launch.setPower(0);
            guide.setPower(0);
            push.setPower(0);
        }

    }
}
