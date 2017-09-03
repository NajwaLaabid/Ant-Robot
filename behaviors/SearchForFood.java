package behaviors;

import java.io.PrintStream;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Pose;
import lejos.utility.Delay;

public class SearchForFood
{
  private static EV3IRSensor sensorEIR = new EV3IRSensor(lejos.hardware.port.SensorPort.S3);
  private static SampleProvider bearingIR = sensorEIR.getSeekMode();
  
  private static float[] infraredSample = new float[bearingIR.sampleSize()];
  private static float bearing;
  private static float distance;
  private static float speed = 200.0F;
  private static DifferentialPilot pilot = new DifferentialPilot(2.200000047683716D, 6.800000190734863D, Motor.A, Motor.D);
  private static OdometryPoseProvider poseProvider = new OdometryPoseProvider(pilot);
  private static Pose nestPose = poseProvider.getPose();
  
  public SearchForFood() {}
  
  public void execute() {
    do {
      Motor.A.setSpeed(speed);
      Motor.D.setSpeed(speed);
      Motor.A.forward();
      Motor.D.forward();
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
        if (bearing == 0.0D)
        {
          Motor.A.setSpeed(speed);
          Motor.D.setSpeed(speed);
          Motor.A.forward();
          Motor.D.forward();
        }
        if (bearing > 0.0F)
        {
          if ((distance > 2.0F) && (distance < 10.0F))
          {
            Motor.A.setSpeed(20);
          }
          else {
            Motor.A.setSpeed(speed);
          }
          Motor.D.setSpeed(0);
          Motor.A.forward();
          Motor.D.forward();
          System.out.println("Negative");
        }
        else if (bearing < 0.0F)
        {
          if ((distance > 2.0F) && (distance < 10.0F))
          {
            Motor.D.setSpeed(20);
          }
          else {
            Motor.D.setSpeed(speed);
          }
          Motor.A.setSpeed(0);
          Motor.A.forward();
          Motor.D.forward();
          System.out.println("Positive");
        }
        
        if ((distance <= 2.0F) && (distance > 0.0F))
        {
          Motor.A.stop();
          Motor.D.stop();
          return;
        }
        
      }
    } while ((distance > 1.0F) || (distance <= 0.0F));
    

    Motor.A.stop();
    Motor.D.stop();
    System.out.println("Touching Food");
    Delay.msDelay(3000L);
  }
}
