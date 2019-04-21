package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;

import GIS.Fruit;
import GIS.Packman;
import Game.Game;

public class CsvMaker {
	
	/*
	 * @param game object making csv
	 * static function to make csv file
	 */
	public static void csvMaker(Game game) {
		 int idP=0;
		 int idF=0;
	     StringBuilder sb = new StringBuilder();	
		 try {
			PrintWriter pw = new PrintWriter(new File("Result.csv"));
			sb.append("Type,id,Lat,Lon,Alt,Speed/Weight,Radius,3,12 \n"); //first row 
			Iterator<Packman> iterP = game.packmans.iterator();
			Iterator<Fruit> iterF= game.fruits.iterator();
			while(iterP.hasNext()) {
				Packman p = iterP.next();
				sb.append("P,");            		//Type of object
				sb.append(idP++ +","); 				//id of the Packman	
				sb.append(p.getLocation().y()+",");		// Lat /x value
				sb.append(p.getLocation().x()+",");		//Lon /y value
				sb.append(p.getLocation().z()+",");		//alt /z value
				sb.append(p.getSpeed()+","); 		//Packman's speed
				sb.append(p.getR()+"\n");		//radius
			}
			while(iterF.hasNext()) {
				Fruit f =iterF.next();
				sb.append("F,");            		//Type of object
				sb.append(idF++ +","); 				//id of the Fruit	
				sb.append(f.getLocation().y()+",");		// Lat /x value
				sb.append(f.getLocation().x()+",");		//Lon /y value
				sb.append(f.getLocation().z()+",");		//alt /z value
				sb.append(f.getWeight()+"\n");       //fruit's weight
						
			}
			pw.write(sb.toString());
			pw.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 System.out.println("Done!");
	}

}
