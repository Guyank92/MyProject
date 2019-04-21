package Game;
import File_format.*;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import GIS.*;
import java.util.Iterator;

import javax.imageio.ImageIO;
public class Game {
	public ArrayList<Fruit> fruits =new ArrayList();
	public ArrayList<Packman> packmans =new ArrayList();
	String path="game_1543684662657.csv";

/*
 * @param String filePath getting a file from the user 
 * (not with "insert" at the manu bar list)
 * , analaize it to fruits and packman with the reader and the type maker 	
 */
public void buildAgame(String filePath) {
	ArrayList<ArrayList<String>> table  =new ArrayList<ArrayList<String>>();
	ArrayList<Types> types = new ArrayList<Types>();
	table=CsvReader.Csvreader(filePath);
	types=List2types.makeTypes(table);
	for(Types t : types) {
		if(t instanceof Fruit) {
			fruits.add((Fruit) t);
		}
		else if(t instanceof Packman) {
			packmans.add((Packman) t);
		}
	}
}

public void animation(Game game) {
	for(int i =0;i<game.packmans.size();i++) {
		
	}
}



























}
