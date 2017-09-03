package behaviors;

import lejos.robotics.navigation.MoveController;
import lejos.robotics.navigation.MoveListener;

public class Movemenet implements MoveController
{
  public Movemenet() {}
  
  public lejos.robotics.navigation.Move getMovement()
  {
    return null;
  }
  




  public void addMoveListener(MoveListener listener) {}
  




  public void forward() {}
  



  public void backward() {}
  



  public void stop() {}
  



  public boolean isMoving()
  {
    return false;
  }
  




  public void travel(double distance) {}
  



  public void travel(double distance, boolean immediateReturn) {}
  



  public void setTravelSpeed(double speed) {}
  



  public double getTravelSpeed()
  {
    return 0.0D;
  }
  

  public double getMaxTravelSpeed()
  {
    return 0.0D;
  }
}
