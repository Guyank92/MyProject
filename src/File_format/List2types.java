package File_format;

import java.util.ArrayList;
import java.util.Iterator;
import Geom.*;
import Game.*;
import GIS.*;

public class List2types {
	
	/*
	 * @param ArrayList<ArrayList<String>> table which contain what we read from the csv file
	 * @return retun Arraylist containing fruits and packman together
	 */
	public static ArrayList<Types> makeTypes(ArrayList<ArrayList<String>> table) {
		ArrayList<Types> types=new ArrayList<Types>();
		Iterator<ArrayList<String>> it = table.iterator();
		it.next();
		while (it.hasNext()) {
			ArrayList<String> p = new ArrayList<String>(it.next());
			if(p.get(0).equals("F")) {
				Fruit tut = new Fruit(p.get(3),p.get(2),p.get(4),p.get(5));
				types.add(tut);
			}
			else if(p.get(0).equals("P")) {
				Packman hamudi = new Packman(p.get(1),p.get(3),p.get(2),p.get(4),p.get(5),p.get(6));
				types.add(hamudi);
			}
			else {
				System.out.println("you are wrong smh");
			}
			
		}
		return types;
	}

}
