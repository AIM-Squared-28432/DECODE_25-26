package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivebase {
    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;

    public void init(HardwareMap hwMap){
      frontRight = hwMap.get(DcMotor.class, "frontRight");
      frontLeft = hwMap.get(DcMotor.class, "frontLeft");
      backRight = hwMap.get(DcMotor.class, "backRight");
      backLeft = hwMap.get(DcMotor.class, "backLeft");
      frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
      backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
      frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void drive(Gamepad gamepad1) {
        double frontRightPower;
        double frontLeftPower;
        double backLeftPower;
        double backRightPower;

        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        double denominator = Math.max(1,Math.abs(y) + Math.abs(x) + Math.abs(rx));
        frontRightPower = (y - x - rx)/denominator;
        frontLeftPower = (y + x + rx)/denominator;
        backRightPower = (y + x - rx)/denominator;
        backLeftPower = (y - x + rx)/denominator;

        frontRight.setPower(frontRightPower);
        frontLeft.setPower(frontLeftPower);
        backRight.setPower(backRightPower);
        backLeft.setPower(backLeftPower);

    }

}