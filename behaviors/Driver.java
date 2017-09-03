package behaviors;

import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;

public class Driver
{
  private static EV3ColorSensor sensorECS = new EV3ColorSensor(lejos.hardware.port.SensorPort.S2);
  
  private static SampleProvider colorCS = sensorECS.getRGBMode();
  private static float[] colorSample = new float[colorCS.sampleSize()];
  

  private static SearchTest search = new SearchTest();
  private static PickUpFood pickUp = new PickUpFood();
  private static ReturnToNest returning = new ReturnToNest();
  private static DifferentialPilot pilot = new DifferentialPilot(2.200000047683716D, 6.800000190734863D, Motor.A, Motor.D);
  private static OdometryPoseProvider poseProvider = new OdometryPoseProvider(pilot);
  private static Pose nestPose = poseProvider.getPose();
  
  public Driver() {}
  
  public DifferentialPilot getPilot() { return pilot; }
  

  public OdometryPoseProvider getPoseProvider()
  {
    return poseProvider;
  }
  
  public Pose getNestPose()
  {
    return nestPose;
  }
  


  public static void main(String[] args)
  {
    Waypoint nest = new Waypoint(nestPose.getX(), nestPose.getY());
    System.out.println(nestPose.getX() + "," + nestPose.getY());
    lejos.utility.Delay.msDelay(2000L);
    Motor.B.rotate(90);
    Motor.B.stop();
    search.execute();
    pickUp.execute();
  }
}
