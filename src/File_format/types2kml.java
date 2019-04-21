package File_format;

import Game.Game;
import Geom.Point3D;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import GIS.*;

public class types2kml {

	/*
	 * Function which make a kml file to run on google earth
	 * @param Game game is an object containing ArrayLists of fruits and packmans
	 */
	public static void types2Kml(Game game) {

		String line = "";
		String cvsSplitBy = ",";
		String OutFile = "output.kml";
		PrintWriter pw = null;
		File ans = new File(OutFile);
		try 
		{
			pw = new PrintWriter(ans);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		Iterator<Packman> it = game.packmans.iterator();		
		Iterator<Point3D> inPath;
		Iterator<Long> inTimes;
		pw.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		pw.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>https://upload.wikimedia.org/wikipedia/commons/f/f6/Choice_toxicity_icon.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\n");

		while(it.hasNext()) {
			Packman pac=it.next();
			inPath = pac.getPath().getP().iterator();
			inTimes= pac.getPath().getTimeStamps().iterator();

			while(inPath.hasNext()) {
				Point3D pd=(Point3D) inPath.next();
				long now = inTimes.next();
				pw.append("<Placemark>\n");
				pw.append("<description>"+"Packmans' id: "+pac.getId()+"</description>\n");
				pw.append("<TimeStamp>\n");
				pw.append("<when>"+giveMeStamp(now)+"</when>\n");
				pw.append("</TimeStamp>\n");
				pw.append("<styleUrl>#red</styleUrl>\r\n");
				pw.append("<Point>\n");
				pw.append("<coordinates>"+pd.toString()+"</coordinates>\n");
				pw.append("</Point>\n");
				pw.append("</Placemark>\n");
			}
		}
		pw.append("</Folder>\n");
		pw.append("</Document>\r\n" + 
				"</kml>"); 
		pw.close();
		System.out.println("done");
	}

	private static String giveMeStamp(Long now) {
		String x = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(now);
		String a = x.substring(0, 10);
		String b = x.substring(11,x.length());
		return 	a+"T"+b+"Z"; 
		
	}
}