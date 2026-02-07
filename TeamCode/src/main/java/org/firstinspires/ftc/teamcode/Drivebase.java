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

public class Drivebase {
    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;
    public IMU imu;
    public DcMotorEx xEncoder;
    public DcMotorEx yEncoder;
    public double xTPM = 2000/(48*Math.PI);
    public double yTPM = 2000/(32*Math.PI);
    // good odometry pod
    public YawPitchRollAngles orientation;
    public Pose2D target = new Pose2D(DistanceUnit.MM, 0, 0, AngleUnit.DEGREES, 0);
    public double xError;
    public double yError;
    public double headingError;
    public double kPX = 0.01;
    public double kPY = 0.01;
    public double kPHeading = 0.01;
    public double xyThreshold = 5;
    public double headingThreshold = 0.01;
    public boolean moveFinished;


    public void init(HardwareMap hwMap){
      frontRight = hwMap.get(DcMotor.class, "frontRight");
      frontLeft = hwMap.get(DcMotor.class, "frontLeft");
      backRight = hwMap.get(DcMotor.class, "backRight");
      backLeft = hwMap.get(DcMotor.class, "backLeft");
      frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
      frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
      backRight.setDirection(DcMotorSimple.Direction.REVERSE);


        xEncoder = hwMap.get(DcMotorEx.class, "frontRight");
      yEncoder = hwMap.get(DcMotorEx.class, "backLeft");
      imu = hwMap.get(IMU.class, "imu");
      IMU.Parameters perameters = new IMU.Parameters(new RevHubOrientationOnRobot
                      (RevHubOrientationOnRobot.LogoFacingDirection.DOWN,
                      RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
      imu.initialize(perameters);
      orientation = imu.getRobotYawPitchRollAngles();

    }

    public void drive(double x, double y, double rx) {
        double frontRightPower;
        double frontLeftPower;
        double backLeftPower;
        double backRightPower;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0);
        frontRightPower = (y - x - rx)/denominator;
        frontLeftPower = (y + x + rx)/denominator;
        backRightPower = (y + x - rx)/denominator;
        backLeftPower = (y - x + rx)/denominator;

        frontRight.setPower(frontRightPower);
        frontLeft.setPower(frontLeftPower);
        backRight.setPower(backRightPower);
        backLeft.setPower(backLeftPower);

    }

    public void setAllModes(DcMotor.RunMode mode) {
        frontLeft.setMode(mode);
        backRight.setMode(mode);
    }

    public void setTarget (Pose2D target) {
        this.target = target;
    }

    public void driveToTarget() {
        double heading = orientation.getYaw(AngleUnit.DEGREES);
        double xError = target.getX(DistanceUnit.MM) - xEncoder.getCurrentPosition()/xTPM;
        double yError = target.getY(DistanceUnit.MM) - yEncoder.getCurrentPosition()/yTPM;
        double headingError = target.getHeading(AngleUnit.DEGREES) - heading;
        double powerX = xError * kPX;
        double powerY = yError * kPY;
        double powerHeading = headingError * kPHeading;
        drive(powerX, powerY, powerHeading);
    }

    public boolean moveFinished () {
        return Math.abs(xError) < xyThreshold && Math.abs(yError) < xyThreshold && Math.abs(headingError) < headingThreshold;
    }
}