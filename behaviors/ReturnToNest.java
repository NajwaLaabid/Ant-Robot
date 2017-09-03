package behaviors;

import java.io.PrintStream;
import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;

public class ReturnToNest
{
  private static Driver driver = new Driver();
  private static DifferentialPilot pilot = new DifferentialPilot(2.200000047683716D, 6.800000190734863D, Motor.A, Motor.D);
  private static lejos.robotics.localization.OdometryPoseProvider poseProvider = driver.getPoseProvider();
  private static Pose nest = driver.getNestPose();
  private static Navigator pathfinder = new Navigator(pilot);
  
  public ReturnToNest() {}
  
  public void execute() { System.out.println("Return method called");
    Pose finalPose = poseProvider.getPose();
    System.out.println("Final Pose :" + finalPose.getX() + ", " + finalPose.getY());
    lejos.utility.Delay.msDelay(2000L);
    pathfinder.goTo(nest.getX(), nest.getY());
    
    while (!pilot.isMoving())
    {
      System.out.println("returning");
      pathfinder.goTo(nest.getX(), nest.getY());
    }
  }
}
