package frc.robot;

public class Constants {

    public class EncoderConstants {
        public static double encoderResolution = 2048.0;
        public static double wheelDiameterInches = 6;
        public static double wheelCircumference = Math.PI * wheelDiameterInches;
        public static double distancePerPulse = wheelCircumference / encoderResolution;
    }
}