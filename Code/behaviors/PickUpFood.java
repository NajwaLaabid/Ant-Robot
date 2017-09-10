package behaviors;

import lejos.hardware.motor.NXTRegulatedMotor;

public class PickUpFood {
  public PickUpFood() {}
  
  public void execute() {
    lejos.hardware.motor.Motor.A.stop();
    lejos.hardware.motor.Motor.D.stop();
    lejos.hardware.motor.Motor.B.setSpeed(70);
    lejos.hardware.motor.Motor.B.rotate(90);
    lejos.hardware.motor.Motor.C.setSpeed(20);
    lejos.hardware.motor.Motor.C.rotate(-90);
    lejos.hardware.motor.Motor.B.rotate(-90);
    

    lejos.hardware.motor.Motor.A.setSpeed(200);
    lejos.hardware.motor.Motor.D.setSpeed(200);
    lejos.hardware.motor.Motor.A.rotate(64816, true);
    lejos.hardware.motor.Motor.D.rotate(64816);
  }
}
