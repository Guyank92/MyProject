package Game;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Geom.Point3D;

class JUNIT {
	double rangeXpixels=1433;
	 double rangeYpixels=-642;
	 double rangeXpolar=0.0100;
	 double rangeYpolar=0.0038;
	 double PolarX0=35.2023;
	 double PolarY0=32.1057;
	 int x,y;
	 
	 //JUNIT for the the two main function in Map as requested
	 //one for converting pixels to polar
	 //second converting polar to pixels
	 
	@Test
	public void pixel2polar() {
		Point3D temp = new Point3D(35.2123,32.10195327102804,0);
		double newX = Math.round(((temp.x()-PolarX0)/rangeXpolar)*rangeXpixels);
		double newY = Math.round(((temp.y()-PolarY0)/rangeYpolar)*rangeYpixels);
		System.out.println(newX +" "+newY);
		assertTrue("pixel converter",newX==1433&&newY==633);
	}
	@Test
	public void polar2pixel() {
		x=1433;
		y=633;
		double newX = rangeXpolar*(x/rangeXpixels)+PolarX0;
		double newY = rangeYpolar*(y/rangeYpixels)+PolarY0;
		assertTrue("polar converter",newX==35.2123&&newY==32.10195327102804);
	}
	
	
	
}
