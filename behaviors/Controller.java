package behaviors;

import java.io.PrintStream;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Controller extends Arbitrator
{
  public Controller(Behavior[] behaviorList)
  {
    super(behaviorList);
    System.out.println(behaviorList[0]);
  }
}
