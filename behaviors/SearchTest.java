package behaviors;

import java.io.PrintStream;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Pose;
import lejos.utility.Delay;

public class SearchTest
{
  private static EV3IRSensor sensorEIR = new EV3IRSensor(SensorPort.S3);
  private static SampleProvider bearingIR = sensorEIR.getSeekMode();
  
  private static float[] infraredSample = new float[bearingIR.sampleSize()];
  private static float bearing;
  private static float distance;
  private static float speed = 200.0F;
  private static DifferentialPilot pilot = new DifferentialPilot(2.200000047683716D, 6.800000190734863D, Motor.A, Motor.D);
  private static OdometryPoseProvider poseProvider = new OdometryPoseProvider(pilot);
  private static Pose nestPose = new Pose(0.0F, 0.0F, 0.0F);
  
  public SearchTest() {}
  
  public void execute() {
    do {
      pilot.setTravelSpeed(speed);
      pilot.forward();
      bearingIR.fetchSample(infraredSample, 0);
      bearing = infraredSample[0];
      distance = infraredSample[1];
      
      while (infraredSample[1] < 1000.0F)
      {
        bearingIR.fetchSample(infraredSample, 0);
        bearing = infraredSample[0];
        distance = infraredSample[1];
        System.out.println("Bearing from Beacon: " + bearing);
        System.out.println("Distance from Beacon: " + distance);
        Pose currentPose = poseProvider.getPose();
        System.out.println("Current Pose: " + currentPose.getX() + ", " + currentPose.getY());
        if (bearing == 0.0D)
        {
          if ((distance > 2.0F) && (distance < 10.0F))
          {
            pilot.setRotateSpeed(20.0D);
          }
          else
          {
            pilot.setRotateSpeed(speed);
          }
          pilot.forward();
        }
        if (bearing > 0.0F)
        {
          if ((distance > 2.0F) && (distance < 10.0F))
          {
            pilot.setRotateSpeed(20.0D);
          }
          else {
            pilot.setRotateSpeed(speed);
          }
          
          pilot.travelArc(10.0D, 10.0D);
          
          System.out.println("Negative");
        }
        else if (bearing < 0.0F)
        {
          if ((distance > 2.0F) && (distance < 10.0F))
          {
            pilot.setRotateSpeed(20.0D);
          }
          else {
            pilot.setRotateSpeed(speed);
          }
          pilot.travelArc(-10.0D, 10.0D);
          System.out.println("Positive");
        }
        
        if ((distance <= 2.0F) && (distance > 0.0F))
        {
          pilot.quickStop();
          return;
        }
        
      }
    } while ((distance > 2.0F) || (distance <= 0.0F));
    
    System.out.println("Nest Pose: (0.0, 0.0)");
    Pose currentPose = poseProvider.getPose();
    System.out.println("Current Pose: " + currentPose.getX() + ", " + currentPose.getY());
    Delay.msDelay(3000L);
  }
}
