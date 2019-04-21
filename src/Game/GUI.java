package Game;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.color.*;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import File_format.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Algorithms.*;
import GIS.Fruit;
import GIS.Packman;
import GIS.Types;
import Geom.Point3D;

public class GUI extends JFrame implements MouseListener {
	Dimension dimensionSize= new Dimension();
	BufferedImage image ;
	JMenuItem menuItem;
	JMenuBar menuBar;
	Image packmanImage ,fruitImage;
	Map map=new Map();
	Game game =new Game();
	ArrayList <Types> types ;
	Color colorsArr [] ;
	Iterator<Packman> itPac= game.packmans.iterator();
	Iterator<Fruit> itFru = game.fruits.iterator();
	boolean packmanORfruit,enableAdd,runActivated;
	double ratioWidth ;
	double ratioHeight;
	int x =-1, y=-1; 

	/*
	 * GUI construcor setting all the basic 
	 * such as starting window size and more
	 * then calling the startGUI method to activate the gui officially
	 */
	public GUI() {
		startGUI();
		setVisible(true);
		setTitle("Welcome");
		setJMenuBar(menuBar);
		setSize(image.getWidth(),image.getHeight());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addMouseListener(this); //detect Mouse clicks (not used at the moment)
		this.addComponentListener(new ComponentAdapter() { // detects window changing 
			public void componentResized(ComponentEvent e) {
				dimensionSize= e.getComponent().getSize();
				ratioHeight=dimensionSize.getHeight()/image.getHeight();
				ratioWidth = dimensionSize.getWidth()/image.getWidth();
				enableAdd=false;
				runActivated = true;
			}
		});
		colorsArr=new Color[13];
		colorsArr[0] = Color.red;
		colorsArr[1] = Color.blue;
		colorsArr[2] = Color.yellow;
		colorsArr[3] = Color.green;
		colorsArr[4] = Color.gray;
		colorsArr[5] = Color.ORANGE;
		colorsArr[6] = Color.pink;
		colorsArr[7] = Color.white;
		colorsArr[8] = Color.CYAN;
		colorsArr[9] = Color.MAGENTA;
		colorsArr[10] = Color.LIGHT_GRAY;
		colorsArr[11] = Color.darkGray;
		colorsArr[12] = Color.BLACK;

	}
	/*
	 * the main function Starting the gui
	 */
	private void startGUI() {
		MenuBar menuBar = new MenuBar();
		//defining "Menu" menu titles
		Menu menu = new Menu("Menu"); 
		MenuItem item1 = new MenuItem("Load File");
		MenuItem item2 = new MenuItem("Run");
		MenuItem item3 = new MenuItem("Clear");
		MenuItem item4 = new MenuItem("Save result as kml");
		MenuItem item7 = new MenuItem("Save result as csv");
		//setting main menu "Menu"
		menuBar.add(menu);
		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		menu.add(item4);
		menu.add(item7);
		this.setMenuBar(menuBar);
		menu.setFont(new Font("Courier New", Font.ITALIC, 12));
		//defining "insert" menu titles
		Menu menu2 =new Menu("Insert");
		MenuItem item5=new MenuItem("Add Fruits");
		MenuItem item6=new MenuItem("Add Packmans");
		//setting "insert" menu
		menuBar.add(menu2);
		menu2.add(item5);
		menu2.add(item6);
		menu2.setFont(new Font("Courier New", Font.ITALIC, 12));

		//Load File button Listener
		item1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				loadFile();

			}
		});
		//Run method listener
		item2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(ShortestPathAlgo.didRun==true) {
					Iterator itPac= game.packmans.iterator();		
					while(itPac.hasNext()) {
						Packman p=(Packman) itPac.next();
						p.getPath().p.clear();
						p.getPath().p.add(p.getLocation());
					}
					ShortestPathAlgo.didRun=false;
				}
				runActivated=true;
				ShortestPathAlgo.route(game);
				System.out.println("The running time is: "+ShortestPathAlgo.runningTime(game));
				repaint();

				for(Packman packman : game.packmans) {
					new GUI_Run_Activated(GUI.this,packman).start();

				}
				enableAdd=false;
				
			}


		});
		//Clear Method from the "Menu" menu
		item3.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				x=-1;
				y=-1;
				enableAdd=false;
				packmanORfruit=false;		
				game.packmans.clear(); //not sure what to fill inside the 
				game.fruits.clear();
				repaint();
			}
		});
		//Save as kml file 
		item4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShortestPathAlgo.route(game);
				types2kml.types2Kml(game);

			}
		});

		//Save result as csv file 
		item7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CsvMaker.csvMaker(game);		
			}
		});
		//Adding packmans listener through "Insert" menu
		item5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				enableAdd=true;
				packmanORfruit= false;
				if(ShortestPathAlgo.didRun==true) {
					Iterator itPac= game.packmans.iterator();		
					while(itPac.hasNext()) {
						Packman p=(Packman) itPac.next();
						p.getPath().p.clear();
						p.getPath().p.add(p.getLocation());
					}
					ShortestPathAlgo.didRun=false;
				}
				runActivated=false;

			}

		});

		//Adding fruits Listener through "Insert" menu
		item6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				enableAdd=true;
				packmanORfruit=true;
				runActivated=false;

			}
		});


		try {
			image = ImageIO.read(new File("Ariel1.png"));
			packmanImage=ImageIO.read(new File("packman1.png"));
			fruitImage =ImageIO.read(new File("fruit1.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/*
	 * (non-Javadoc)
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 * Main function of the graphic responsible 
	 * for all the drawing and backgroung image and more
	 */
	public void paint(Graphics g)
	{
		g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(), this);
		if(x!=-1 && y!=-1)
		{
			int r = 20;
			x = x - (r / 2+10);
			y = y - (r / 2+10);
			if(packmanORfruit&&enableAdd) {
				if(!runActivated) {
					Packman p = new Packman((int)(x/ratioWidth),(int)(y/ratioHeight));    //need to add Packman (need to change pixels to deg)
					game.packmans.add(p);           //add the fruit to the game
				}
			}
			else if(enableAdd) {
				if(!runActivated) {
					Fruit f = new Fruit((int)(x/ratioWidth),(int)(y/ratioHeight));
					game.fruits.add(f);
				}
			}
			if(runActivated) {
				int i=0;
				itPac= game.packmans.iterator();		
				while(itPac.hasNext()) {
					Packman p =itPac.next();
					if(p.getPath().p.size()==1||p.getPath().p.size()==0) continue; //if the size is 0 the packman should not move and we should
					//go search for a route in the other packmans
					Iterator<Point3D> start= p.getPath().p.iterator();  //first "p" is the packman ,second is the "p" the
					Iterator<Point3D> target= p.getPath().p.iterator(); //name of the "path" in the "path" class

					Point3D startP;
					Point3D targetP = target.next();

					g.setColor(colorsArr[i%13]);
					//drawing the lines
					while(target.hasNext()) {
						startP=start.next();
						startP=map.polar2pixels(startP);
						targetP=target.next();
						targetP=map.polar2pixels(targetP);
						Graphics2D g2 = (Graphics2D) g;
						g2.setStroke(new BasicStroke(5));
						g2.drawLine((int)(startP.x()*ratioWidth)+16,(int)(startP.y()*ratioHeight)+12,
								(int)(targetP.x()*ratioWidth) +16,(int)(targetP.y()*ratioHeight)+12);
					}

					i++;
				}

				runActivated=false;
			}
			synchronized(game.fruits) {
				itFru= game.fruits.iterator();
				while(itFru.hasNext()) {
					Fruit fTemp= itFru.next();
					g.drawImage(fruitImage,(int)(fTemp.getLocationInPixels().x()*ratioWidth),(int)(fTemp.getLocationInPixels().y()*ratioHeight),(int)(2*r*ratioWidth),(int)(2*r*ratioHeight),this);
				}
			}
			itPac= game.packmans.iterator();  //for the repaint we need to draw every packman again
			while(itPac.hasNext()) {
				Packman pTemp = itPac.next();
				g.drawImage(packmanImage,(int)(pTemp.getLocationInPixels().x()*ratioWidth),(int)(pTemp.getLocationInPixels().y()*ratioHeight),(int)(2*r*ratioWidth),(int)(2*r*ratioHeight),this);
			}
		}
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 * sensor for mouse clicking in case we want 
	 * to add fruit/packman directly and we clicked on "insert" 
	 */
	public void mouseClicked(MouseEvent m) {
		System.out.println("("+ m.getX() + "," + m.getY() +")");
		x = m.getX();
		y = m.getY();
		if(ShortestPathAlgo.didRun==true) {
			enableAdd=true;
		}
		repaint();

	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 *function for the Load of the csv file
	 *also analaizng it with game.buildgame funciton
	 *from the Game class 
	 */
	public void loadFile() {
		//		try read from the file (Copied code from Elizabeth )
		FileDialog fd = new FileDialog(this, "Open text file", FileDialog.LOAD);
		fd.setFile("*.csv");
		fd.setDirectory("C:");
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();
		if(fileName!=null) {
			System.out.println("The file that opened is: "+folder+fileName);
			game.buildAgame(folder + fileName);
			x=1;
			y=1;
			repaint();
		}
	}
	public static void main (String [] args) {
		new GUI();	
	}

}
