package Algorithms;

import java.util.ArrayList;

import Geom.Point3D;

public class Path {
	public ArrayList<Point3D> p = new ArrayList();
	private ArrayList<Long> timeStamps = new ArrayList();
	/*
	 * @return return ArrayList<Point3D>  
	 */
	public ArrayList<Point3D> getP() {
		return p;
	}
	/*
	 * @return return arraylist of timeStamps
	 */
	public ArrayList<Long> getTimeStamps() {
		return timeStamps;
	}
	/*
	 * @param getting arraylist of timestamps
	 */
	public void setTimeStamps(ArrayList<Long> timeStamps) {
		this.timeStamps = timeStamps;
	}
	/*
	 * @param ArrayList<Point3D> p setting the path with the 
	 * given ArrayList
	 */
	public void setP(ArrayList<Point3D> p) {
		this.p = p;
	}

}
