package NavigatorTest;

import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.Pose;

public class Test
{
  public Test() {}
  
  private static lejos.robotics.navigation.DifferentialPilot pilot = new lejos.robotics.navigation.DifferentialPilot(2.200000047683716D, 6.800000190734863D, lejos.hardware.motor.Motor.A, lejos.hardware.motor.Motor.D);
  private static OdometryPoseProvider poseProvider = new OdometryPoseProvider(pilot);
  
  public static void main(String[] args) {
    Pose nestPose = poseProvider.getPose();
    System.out.println(nestPose.getX() + ", " + nestPose.getY());
    lejos.utility.Delay.msDelay(3000L);
    pilot.travel(10.0D);
    Pose currentPose = poseProvider.getPose();
    System.out.println(currentPose.getX() + ", " + currentPose.getY());
    lejos.utility.Delay.msDelay(3000L);
  }
}
