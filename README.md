# Third Project .
## Introduction
Welcome , this game is simulation of the original packman game on google earth maps.

There are three options for fruits and packman addition :

1. Click on "Insert" which is in the bar menu and add packmans and fruits as you like.
![Image description](https://github.com/Sniryefet/papi2/blob/master/Pictures%20for%20readme/insert.PNG)
2. Click on "Menu" which is in the bar menu and then click on "Load File". 
![Image description](https://github.com/Sniryefet/papi2/blob/master/Pictures%20for%20readme/Save%20as%20kml.PNG)
3. You can also mix the two options together adding fruits and packmans manually and then load a file and vice versa , In any case the fruits and packman will be added and won't be replaced with the other option.  

### Notice
* Make sure the file is csv file which answering the requirements like so

![Image description](https://github.com/Sniryefet/papi2/blob/master/Pictures%20for%20readme/Upload%20a%20file.PNG)

* The order of each column is also important and swapping between them may cause inaccuracy.

* By adding packmans manually their speed and raduis will be set to 1.

## System Structure
The program structure is devided into 5 packages ,each package with a distinctive target :

**GIS** - Contains "Packman","Fruit", "Type" classes which are the basis of the game.
you can see each object characteristic . 

**File Format** - Contains classes which responsible for the reading csv files ,and making kml .

**Algorithm** - Contains classes wich calculate the path for the packmans

**Geom** - Contains geographic class  "MyCoords" which helps with a different calculations like distance between points, angle and more  . Also contains "Point3D" witch is class with the basic characteristic of a point in 3D space.

**Game** - This package contains the GUI of the app , "Game" class which is the main object of the game containing ArrayLists of packmans and fruits in it.
Also have the "map" class which is responsible for the conversion between pixels to polar coordinates and vice versa. 

## The algorithm we used
In order to calculate the path for each packman we used a greedy algorithm technic
which is not the optimal one but making a good result overall.


## How to run the game
In order to run the game first add packman and fruits as you like (look into the "Introduction" for more datails).
Then click on "Run" which is located in the menu bar "Menu" --> "Run"

![Image description](https://github.com/Sniryefet/papi2/blob/master/Pictures%20for%20readme/Save%20as%20csv.PNG).

It will activate the algorithm making a path for each packman and an animation of the packman travelling on the route eating his fruits :tw-1f60b: :tw-1f60b: :tw-1f60b: . 
 

## How to save the game
There are two options to save the game :

1. Save it as csv 

![Image description](https://github.com/Sniryefet/papi2/blob/master/Pictures%20for%20readme/Save%20as%20csv.PNG).

2. Save it as kml file which can be used on "Google Earth"

![Image description](https://github.com/Sniryefet/papi2/blob/master/Pictures%20for%20readme/Save%20as%20kml.PNG)

* Either file you make will be saved in your project folder.

