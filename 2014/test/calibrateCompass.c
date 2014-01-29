#pragma config(StandardModel, "teddy")
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//

#include "..\library\sensors\drivers\hitechnic-compass.h"

task main()
{
	nMotorPIDSpeedCtrl[driveLeft] = mtrSpeedReg;
	nMotorPIDSpeedCtrl[driveRight] = mtrSpeedReg;

	wait1Msec(1000);
	eraseDisplay();

	HTMCstartCal(HTMC);
	motor[driveRight] = 20;
	motor[driveLeft] = -20;
	wait1Msec(40000);
	HTMCstopCal(HTMC);
}