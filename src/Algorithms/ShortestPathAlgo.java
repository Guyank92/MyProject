package Algorithms;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import GIS.Fruit;
import GIS.Packman;
import GIS.Types;
import Game.Game;
import Geom.MyCoords;
import Geom.Point3D;

public class ShortestPathAlgo {
	static MyCoords mc = new MyCoords();
	static public boolean didRun=false;
	/*
	 * static function to calculate the route
	 * @param Game game is the game object contains the fruits and packmans
	 */

	public static void route(Game game) {
		long zeroTime = new Date().getTime();
		Iterator<Packman> itPac= game.packmans.iterator();		
		while (itPac.hasNext()) {
			Packman puki = itPac.next();
			puki.getPath().getTimeStamps().add(zeroTime);
		}
	//	Iterator<Fruit> itFru = game.fruits.iterator();
		ArrayList<Fruit> tempFruits = new ArrayList(game.fruits);
		Iterator<Fruit> itFru = tempFruits.iterator();
		itPac= game.packmans.iterator();
		while((tempFruits).size()>0) {
		//	itFru = game.fruits.iterator();
			itFru = tempFruits.iterator();
			if(game.packmans.size()==0) return;
			Packman bestP = game.packmans.get(0);
			Fruit bestF = game.fruits.get(0);
			double minPrice = Integer.MAX_VALUE;
			while(itFru.hasNext()) {
				itPac= game.packmans.iterator();
				Fruit fru = itFru.next();	
				while(itPac.hasNext()) {
					Packman pac = itPac.next();
					if(time(fru,pac)<minPrice) {
						minPrice=time(fru,pac);
						bestP=pac;	
						bestF = fru;
						//packman with shortest distance to the fruit
					}
				}
			}
			bestP.getPath().getTimeStamps().add((long) (bestP.getPath().getTimeStamps().get((bestP.getPath().getTimeStamps().size())-1)
					+1000*time(bestF,bestP)));
			bestP.getPath().getP().add(bestF.getLocation());

		//	game.fruits.remove(bestF);
			tempFruits.remove(bestF);
		}
		didRun=true;
	}



	/*
	 * @return static function to calculate the time 
	 * @param Fruit f,Packman p for the given packman and fruit the function will 
	 *  calculate the time it takes for the packman to eat the fruit
	 */
	private static double time(Fruit f,Packman p) {
		//x=vt -> t=x/v
		double dis = mc.distance3d(f.getLocation(),p.getPath().p.get((p.getPath().p.size())-1));
		dis-=p.getR();
		return dis/p.getSpeed();

	}
	/*
	 *@return return the time of the route
	 *@param game object with the points 
	 */
	public static double runningTime(Game game) {
		double dis = 0;
		double speed=0;
		Iterator<Packman> itPac= game.packmans.iterator();
		Iterator<Point3D> itPath;
		while (itPac.hasNext()) {
			Packman temp = itPac.next();
			itPath=temp.getPath().getP().iterator();
			Point3D p = temp.getLocation();
			Point3D nextP=temp.getLocation();
			while(itPath.hasNext()) {
				p=nextP;
				nextP=itPath.next();
				dis+=mc.distance3d(p,nextP);
			}
			speed+=temp.getSpeed();
		}
		return dis/speed;
	}
}
