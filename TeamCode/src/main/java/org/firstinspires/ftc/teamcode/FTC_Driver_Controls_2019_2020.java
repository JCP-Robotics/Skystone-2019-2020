/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import java.util.Timer;
import java.util.TimerTask;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="FTC_First_Competition_2019_2020", group="Linear Opmode")
//@Disabled
public class FTC_Driver_Controls_2019_2020 extends LinearOpMode {

    private ElapsedTime timer = new ElapsedTime();
    private DcMotor motor1 = null;
    private DcMotor motor2 = null;
    private DcMotor motor3 = null;
    private DcMotor motor4 = null;
    private DcMotor motor5 = null;
    private DcMotor motor6 = null;
    private Servo servo0 = null;
    private Servo leftServo = null;
    private Servo rightServo = null;


    private static void verticalMotion(DcMotor motor_1,DcMotor motor_2,DcMotor motor_3,DcMotor motor_4,double power){
        motor_1.setPower(-power);
        motor_2.setPower(-power);
        motor_3.setPower(power);
        motor_4.setPower(power);

    }
    private static void horizontalMotion(DcMotor motor_1,DcMotor motor_2,DcMotor motor_3,DcMotor motor_4,double power){
        motor_1.setPower(-power);
        motor_2.setPower(power);
        motor_3.setPower(power);
        motor_4.setPower(-power);

    }
    private static void rotate(DcMotor motor_1,DcMotor motor_2,DcMotor motor_3,DcMotor motor_4,double power){
        motor_1.setPower(power);
        motor_2.setPower(power);
        motor_3.setPower(power);
        motor_4.setPower(power);

    }
    private static void stop(DcMotor motor_1,DcMotor motor_2,DcMotor motor_3,DcMotor motor_4){
        motor_1.setPower(0);
        motor_2.setPower(0);
        motor_3.setPower(0);
        motor_4.setPower(0);

    }



    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        motor1 = hardwareMap.get(DcMotor.class, "motor1");
        motor2 = hardwareMap.get(DcMotor.class, "motor2");
        motor3 = hardwareMap.get(DcMotor.class, "motor3");
        motor4 = hardwareMap.get(DcMotor.class, "motor4");
        motor5 = hardwareMap.get(DcMotor.class, "motor5");
        motor6 = hardwareMap.get(DcMotor.class, "motor6");
        servo0 = hardwareMap.get(Servo.class, "servo0");
        leftServo = hardwareMap.get(Servo.class, "leftServo");
        rightServo = hardwareMap.get(Servo.class, "rightServo");

        float servoMode = (float)0.5;
        boolean clawOpen = false;
        boolean foundationClawOpen = false;
        boolean xPressFlag = false;

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if(false){
                if(gamepad1.a){
                    if(clawOpen)
                        servo0.setPosition(1.0);
                    else
                        servo0.setPosition(0.5);
                }

            }
            if(true){
                if(!gamepad1.x)
                    xPressFlag = false;
                telemetry.addData("leftServo",leftServo.getPosition());
                telemetry.addData("leftServoController",leftServo.getController().getServoPosition(leftServo.getPortNumber()));

                if (leftServo.getPosition() == .5)
                    foundationClawOpen = false;
                if(leftServo.getPosition() == 0 )
                    foundationClawOpen = true;


                if(gamepad1.x && !xPressFlag) {
                    xPressFlag = true;
                    if (foundationClawOpen) {
                        leftServo.setPosition(0.5);
                        rightServo.setPosition(0.36);


                    } else {
                        leftServo.setPosition(0);
                        rightServo.setPosition(0.85);
                    }
                }


            }
            if(false){
                if (gamepad1.a) {
                    servoMode -= 0.01;
                    if (servoMode < 0.5) {
                        servoMode = (float) 0.5;
                    }

                } else if (gamepad1.b) {
                    servoMode += 0.01;
                    if (servoMode > 1) {
                        servoMode = 1;
                    }
                }

                double servoMode1 = servoMode;

                servo0.setPosition(servoMode1);

                telemetry.addData("servoPos", servoMode1);
            }


            //servo1.setPower();

            if(false) {
                if (gamepad1.right_bumper) {
                    motor5.setPower(0.65);
                } else if (gamepad1.left_bumper) {
                    motor5.setPower(-0.65);
                } else {
                    motor5.setPower(0);
                }
            }



            if(!gamepad1.right_bumper && gamepad1.right_trigger == 0)
                motor5.setPower(0);
            else if(gamepad1.right_bumper)
                motor5.setPower(0.65);
            else if(gamepad1.right_trigger !=0)
                motor5.setPower(-0.65);

            if(gamepad1.left_bumper) {
                servoMode -= 0.01;
                if (servoMode < 0.5) {
                    servoMode = (float) 0.5;
                }
            }
            else if(gamepad1.left_trigger !=0){
                servoMode += 0.01;
                if (servoMode > 1) {
                    servoMode = 1;
                }
            }
            double servoMode1 = servoMode;

            servo0.setPosition(servoMode1);


            if(false){
                if(gamepad1.x){
                    motor6.setPower(0.5);
                }else if(gamepad1.y){
                    motor6.setPower(-0.5);
                }else
                    motor6.setPower(0);
            }

            if(false){
                if(gamepad1.right_trigger != 0)
                    motor6.setPower(0.5);
                else if(gamepad1.left_trigger != 0)
                    motor6.setPower(-0.5);
                else
                    motor6.setPower(0);
            }



            boolean check1 = true;
            boolean check2 = true;

            // Setup a variable for each drive wheel to save power level for telemetry

            double y_power;

            double x_power;

            double rotation_power;

            y_power = gamepad1.left_stick_y;
            telemetry.addData("y_positionL",gamepad1.left_stick_y);

            x_power = gamepad1.left_stick_x;
            telemetry.addData("x_positionL",gamepad1.left_stick_x);

            rotation_power = gamepad1.right_stick_x;
            telemetry.addData("xPositionR",gamepad1.right_stick_x);

            if(Math.abs(y_power)>Math.abs(x_power)){
                verticalMotion(motor1, motor2, motor3, motor4, y_power);
                check1 = false;
            }else if (Math.abs(x_power) > 0){
                horizontalMotion(motor1, motor2, motor3, motor4, x_power);
                check1 = false;
            }

            if(Math.abs(rotation_power) > 0 ) {
                rotate(motor1, motor2, motor3, motor4, rotation_power);
                check2 = false;
            }

            if(gamepad1.atRest()){
                stop(motor1, motor2, motor3, motor4);
            }






            // Show the elapsed game time and wheel power.
            telemetry.addData("y_power",y_power);
            telemetry.addData("x_power",x_power);
            telemetry.addData("rotation_power",rotation_power);
            telemetry.addData("test",1);
            telemetry.update();

        }
    }
}
