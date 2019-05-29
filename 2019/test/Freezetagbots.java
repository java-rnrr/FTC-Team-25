/*
 * Copyright (c) September 2019 FTC Teams 25/5218
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

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.RobotLog;


import team25core.Robot;
import team25core.RobotEvent;
import team25core.TankDriveTask;
import team25core.TouchSensorCriteria;
import team25core.TwoWheelDirectDrivetrain;

@TeleOp(name = "FreezeTagCoda3")
//@Disabled
public class Freezetagbots extends Robot {

    private DcMotor leftWheel;
    private DcMotor rightWheel;
    private TouchSensor touchGo;
    private TouchSensor touchStop;

    private TwoWheelDirectDrivetrain drivetrain;
    private TankDriveTask driveTask;
    private double value;
    //private boolean leftispressed;
    //private boolean rightispressed;

    private TouchSensorCriteria goTouchSensorCriteria;
    private TouchSensorCriteria stopTouchSensorCriteria;

    //private static final int TICKS_PER_INCH = 79;

    @Override
    public void handleEvent(RobotEvent e)
    {
       // Nothing to do here...
    }

    @Override
    public void init()
    {
        leftWheel = hardwareMap.get(DcMotor.class, "leftWheel");
        rightWheel = hardwareMap.get(DcMotor.class, "rightWheel");

        touchGo = hardwareMap.get (TouchSensor.class, "touchGo");
        touchStop = hardwareMap.get (TouchSensor.class, "touchStop");

        drivetrain = new TwoWheelDirectDrivetrain(leftWheel,rightWheel);
        goTouchSensorCriteria = new TouchSensorCriteria(touchGo, "goSensor");
        stopTouchSensorCriteria = new TouchSensorCriteria(touchStop, "stopSensor");

    }

    @Override
    public void start()
    {
        // Instantiating a two wheel drivetrain
        driveTask = new TankDriveTask(this, drivetrain, goTouchSensorCriteria,
                                       stopTouchSensorCriteria);
        this.addTask(driveTask);
    }
//    @Override
//    public void loop()
//    {
//
//        value = touchGo.getValue();
//        leftispressed = touchGo.isPressed();
//        telemetry.addData("sensorvalue:",Double.toString(value));
//        telemetry.addData("leftispressed:",Boolean.toString(leftispressed));
//        telemetry.addData("rightispressed:",Boolean.toString(rightispressed));
//
//    }
}
