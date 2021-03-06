
package org.usfirst.frc.team4982.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

/* Wheel Numbering
 * 0    1
 * 
 * 2    3
 */

public class Robot extends IterativeRobot {
    Joystick driver = new Joystick(0);  //Set first joystick plugged in as driver
    Drive drive = new Drive();
    
    public void robotInit() {

    }

    public void autonomousPeriodic() {

    }

   
    public void teleopPeriodic() {
        driveBase();
    }
    
    
    public void testPeriodic() {
    
    }
    
    public void driveBase(){  //Axis 4 is small switch on the front, Top direction pad is axis 5 & 6
    	double x = driver.getRawAxis(1);
    	double y = driver.getRawAxis(2);
    	double t = driver.getRawAxis(3);
    	
    	if(x > -0.15 && x < 0.15){
    		x = 0;
    	}
    	if(y > -0.15 && y < 0.15){
    		y = 0;
    	}
    	if(t > -0.15 && t < 0.15){
    		t = 0;
    	}
    	double[] wheels = new double[4];
    	wheels[0] = y-(t+x);
    	wheels[1] = y+t+x;
    	wheels[2] = y+x-t;
    	wheels[3] = y+t-x;
    	double max = 1;
    	for(int i = 0; i < 4; i++){
    		if(wheels[i] > max){
    			max = wheels[i];
    		}
    	}
    	for(int i = 0; i < 4; i++){
    		wheels[i] /= max;
    		if(driver.getRawButton(1)){
    			wheels[i] /= 2;
    		}
    		drive.setMotor(wheels[i], i);
    	}
    	drive.calculateMotors();
    	drive.assignMotors();
    }
}
