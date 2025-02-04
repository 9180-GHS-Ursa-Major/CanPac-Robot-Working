public class Constants {

    public class EncoderConstants {
        public double encoderResolution = 2048.0;
        public double wheelDiameterInches = 6;
        public double wheelCircumference = Math.PI * wheelDiameterInches;
        public double distancePerPulse = wheelCircumference / encoderResolution;
    }
}