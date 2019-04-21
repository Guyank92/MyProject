package Game;

import java.util.Iterator;

import GIS.Fruit;
import GIS.Packman;
import Geom.MyCoords;
import Geom.Point3D;

public class GUI_Run_Activated extends Thread{
	GUI gui ;
	double angle;
	int pointPathSize;
	Packman packman;
	MyCoords myCoords=new MyCoords() ;
	Map map =new Map();
	
	/*
	 * java constructor
	 */
	public GUI_Run_Activated(GUI gui ,Packman packman) {
		this.packman=packman;
		this.gui=gui;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		Iterator<Point3D> startIter= packman.path.p.iterator(); 
		Iterator<Point3D> endIter= packman.path.p.iterator();
		if(packman.path.p.size()==1) return; 	//Incase there is only the packman himself

		double currentX=packman.getLocationInPixels().x(); //packman original place x
		double currentY=packman.getLocationInPixels().y();  //packman original place y
		double speed = packman.getSpeed();  //packman's speed

		Point3D startPoint;
		Point3D endPoint=endIter.next();


		//try again
		while(endIter.hasNext()) { //while the packman still has fruits to eat
			startPoint =startIter.next();
			endPoint=endIter.next(); //setting the points
			Point3D point= new Point3D(endPoint.x(),endPoint.y(),endPoint.z());

			double distanceM = myCoords.distance3d(startPoint, endPoint); //distance of the interval in meters

			startPoint= map.polar2pixels(startPoint);
			endPoint=map.polar2pixels(endPoint);   //convert the polar to pixel

			double deltaPixel_X=endPoint.x()-startPoint.x();	//
			double deltaPixel_Y=endPoint.y()-startPoint.y();	 // 			
			double distanceP= Math.sqrt(Math.pow(deltaPixel_X,2)+Math.pow(deltaPixel_Y,2)); //distance of the interval in pixels

			double meters2pixRatio = distanceP/distanceM; 			// its good

			int steps =(int)(Math.round(distanceP/speed));

			double stepX=deltaPixel_X/(distanceM*meters2pixRatio); //
			double stepY=deltaPixel_Y/(distanceM*meters2pixRatio);

			for(int i =0; i <steps*speed ;i++) {
				currentX+=stepX;
				currentY+=stepY;
				Point3D tempPoint = map.pixels2polar((int)Math.round(currentX),(int)Math.round(currentY));
				packman.setNewCoords(packman.getId(),tempPoint.x(),tempPoint.y(),packman.getLocationInPixels().z()); //probably a problem
				gui.enableAdd=false;
				gui.runActivated=true;
				gui.repaint();
				try {
					Thread.sleep((int)(40/speed));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}			
			}
			synchronized(gui.game.fruits) { //fruits remove from screen
				Iterator<Fruit> fruitIter = gui.game.fruits.iterator();
				Fruit fru ;
				while(fruitIter.hasNext()) {
					fru=fruitIter.next();
					if(fru.getLocation().x()==point.x()&&fru.getLocation().y()==point.y()&&fru.getLocation().z()==point.z())
						fruitIter.remove();
				}
				gui.runActivated=true;
				gui.repaint();
			}
		}

	}



}
