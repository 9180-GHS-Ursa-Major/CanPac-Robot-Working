package frc.robot;

public class Constants {

    public class EncoderConstants {
        public static double encoderResolution = 2048.0;
        public static double wheelDiameterInches = 6;
        public static double wheelCircumference = Math.PI * wheelDiameterInches;
        public static double distancePerPulse = wheelCircumference / encoderResolution;
    }

    public class LimelightConstants {
        // Target offset from vertical
        public static double vOffset = 0.0;
        
        // Limelight offset from vertical
        public static double vLLOffset = 0.0;

        // Distance from centre of Limelight lens to the floor
        public static double lLHeightIn = 0.0;

        // Distance from target to floor
        public static double tHeightIn = 0.0;

       


    }
}