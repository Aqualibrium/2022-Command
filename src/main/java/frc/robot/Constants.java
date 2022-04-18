// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
        //driver
        public static int drvStick = 0;
        public static int drA = 1;
        public static int drB = 2;
        public static int drX = 3;
        public static int drY = 4;
        public static int drLB = 5;
        public static int drRB = 6;
        public static int drLT = 7;
        public static int drRT = 8;
        //operator
        public static int opStick = 1;
        public static int op1 = 1;
        public static int op2 = 2;
        public static int op3 = 3;
        public static int op4 = 4;
        public static int op5 = 5;
        public static int op6 = 6;
        public static int op8 = 8;
        public static int op7 = 7;
        public static int op9 = 9;
        public static int op10 = 10;
        public static int op11 = 11;
        public static int op12 = 12;
        public static int op13 = 13;
        public static int op14 = 14;
        public static int op15 = 15;
        public static int op16 = 16;
        public static int op17 = 17;
        public static int op18 = 18;
        public static int op19 = 19;
        public static int op20 = 20;
        public static int op21 = 21;
        public static int op22 = 22;
        public static int op23 = 23;
        public static int op24 = 24;
        public static int op25 = 25;
        public static int op26 = 26;
        public static int op27 = 27;
        public static int op28 = 28;

         //Drive Motors
        public static final int lMaster = 5;
        public static final int lSlave1 = 6;
        public static final int rMaster = 3;
        public static final int rSlave1 = 4;
        public static final double maxSpd = 0.5;

        // Intake
        public static final int convey = 30;
        public static final int elevate = 31;
        public static final int intakeArm = 32;
        public static final int intake = 33;
        public static final double intakeSpd = 0.7;
        public static final double inArmUp = 0.6;
        public static final double inArmDn = -0.7;

        // Shooter
        public static final int shootL = 20;
        public static final int shootR = 21;
        public static final int tiltMotor = 42;
        // tilt motor PID
        public static final double kTiltP = 0.04;
        public static final double kTiltI = 0.00;
        public static final double kTiltD = 0.10;

        // shooter speed vs range
        public static final double shoot5ft = 0.65;
        public static final int autotilt = -16;
        public static final int tilt1 = -27;
        public static final int tilt2 = -20;
        public static final int tilt3 = -43;
        public static final int tilt4 = -47;
        public static final int tilt5 = -16;
        public static final double shoot10ft = 0.70;
        public static final double shoot15ft = 0.78;
        public static final double shoot20ft = 0.85;
        public static final double autoShootSd = 0.63;
        
        // Climber
        public static final int winch = 40;
        public static final int ClimbArm = 41;
}
