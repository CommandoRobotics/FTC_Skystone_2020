package org.firstinspires.ftc.teamcode.TestCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.APIs.FTCOmniDriveAPI;

@TeleOp(name="TeleopMain")

public class TeleopMain extends LinearOpMode {

    @Override
    public void runOpMode(){
        DcMotor leftDriveMotor = hardwareMap.get(DcMotor.class, "leftDrive");
        DcMotor rightDriveMotor = hardwareMap.get(DcMotor.class, "rightDrive");
        DcMotor strafeDriveMotor = hardwareMap.get(DcMotor.class, "strafeDrive");
        TouchSensor intakeButton = hardwareMap.get(TouchSensor.class, "intakeButton");
        ColorSensor colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        
        FTCOmniDriveAPI RIPSteve = new FTCOmniDriveAPI();
        gamepad1.setJoystickDeadzone(0);
        
        
        telemetry.addLine("Robot Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            RIPSteve.calculateWheelSpeeds(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            leftDriveMotor.setPower(RIPSteve.getLeftMotorSpeed());
            rightDriveMotor.setPower(-RIPSteve.getRightMotorSpeed());
            strafeDriveMotor.setPower(RIPSteve.getStrafeMotorSpeed());
            
            telemetry.addData("Intake button pressed" , intakeButton.isPressed());
            telemetry.addData("Color sensor red value" , colorSensor.red());
            telemetry.addData("Color sensor green value" , colorSensor.green());
            telemetry.addData("Color sensor blue value" , colorSensor.blue());
            
            telemetry.update();
            
        }
    }
}