/*
 * Copyright (c) September 2017 FTC Teams 25/5218
 *
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification,
 *  are permitted (subject to the limitations in the disclaimer below) provided that
 *  the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice, this list
 *  of conditions and the following disclaimer.
 *
 *  Redistributions in binary form must reproduce the above copyright notice, this
 *  list of conditions and the following disclaimer in the documentation and/or
 *  other materials provided with the distribution.
 *
 *  Neither the name of FTC Teams 25/5218 nor the names of their contributors may be used to
 *  endorse or promote products derived from this software without specific prior
 *  written permission.
 *
 *  NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 *  LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *  AS IS AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 *  THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
 *  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 *  DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *  CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 *  TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import team25core.FourWheelDirectDrivetrain;
import team25core.GamepadTask;
import team25core.Robot;
import team25core.RobotEvent;
import team25core.TankDriveTask;

import static test.JezminConstants.*;


@TeleOp(name = "JezminFourWheelDriveTaskExample")
@Disabled
public class JezminFourWheelDriveTaskExample extends Robot {
    protected Robot robot;
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private Servo   grabRight;
    private Servo   grabLeft;

    private FourWheelDirectDrivetrain drivetrain;

    private TankDriveTask driveTask;

    private static final int TICKS_PER_INCH = 79;

    @Override
    public void handleEvent(RobotEvent e)
    {
        GamepadTask.GamepadEvent event = (GamepadTask.GamepadEvent) e;
        if (event.kind == GamepadTask.EventKind.LEFT_BUMPER_DOWN) {
            grabLeft.setPosition(GRAB_LEFT_OPEN);
        }
        else if (event.kind == GamepadTask.EventKind.LEFT_BUMPER_UP) {
            grabLeft.setPosition(GRAB_LEFT_CLOSE);
        }
        else if (event.kind == GamepadTask.EventKind.RIGHT_BUMPER_DOWN) {
            grabRight.setPosition(GRAB_RIGHT_OPEN);
        }
        else if (event.kind == GamepadTask.EventKind.RIGHT_BUMPER_UP) {
            grabRight.setPosition(GRAB_RIGHT_CLOSE);
        }
    }

    @Override
    public void init()
    {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        grabRight = hardwareMap.get(Servo.class, "grabRight");
        grabLeft = hardwareMap.get(Servo.class, "grabLeft");

        drivetrain = new FourWheelDirectDrivetrain(frontRight, backRight, frontLeft, backLeft);
    }

    @Override
    public void start()
    {
        this.addTask(new TankDriveTask(this, drivetrain));
        this.addTask(driveTask);
        this.addTask(new GamepadTask(this, GamepadTask.GamepadNumber.GAMEPAD_1));
    }

}
