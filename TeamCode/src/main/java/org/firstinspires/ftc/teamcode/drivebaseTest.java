package org.firstinspires.ftc.teamcode;

import static com.sun.tools.doclint.Entity._int;
import static com.sun.tools.doclint.Entity.macr;
import static com.sun.tools.doclint.Entity.pi;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class drivebaseTest {
    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;

    public void init(HardwareMap hwMap) {
        frontRight = hwMap.get(DcMotor.class, "frontRight");
        frontLeft = hwMap.get(DcMotor.class, "frontLeft");
        backRight = hwMap.get(DcMotor.class, "backRight");
        backLeft = hwMap.get(DcMotor.class, "backLeft");
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void drive(double x, double y, double rx) {
        double frontRightPower;
        double frontLeftPower;
        double backLeftPower;
        double backRightPower;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0);
        frontRightPower = (y - x - rx) / denominator;   // correct
        frontLeftPower = (y + x + rx) / denominator;    // incorrect
        backRightPower = (y + x - rx) / denominator;    // incorrect
        backLeftPower = (y - x + rx) / denominator;     // correct

        frontRight.setPower(frontRightPower);
        frontLeft.setPower(frontLeftPower);
        backRight.setPower(backRightPower);
        backLeft.setPower(backLeftPower);

    }
}