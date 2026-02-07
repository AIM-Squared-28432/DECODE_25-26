package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    public DcMotor motor;
    public boolean intakeSpinning;


    public void init(HardwareMap hwMap) {
        motor = hwMap.get(DcMotor.class, "motor");
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeSpinning = false;

    }


    public void loop(Gamepad gamepad1) {
        if (intakeSpinning == true) {
            spinIn();
        } else if (intakeSpinning == false){
            stopSpin();
        }
    }

    public void spinIn () {
        motor.setPower(1);
    }

    public void spinOut(){
        motor.setPower(-1);
    }

    public void stopSpin() {
        motor.setPower(0);
    }
}
