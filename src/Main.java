package critterworld;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import critterworld.Critter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.event.ActionEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
//import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Main extends Application  {
//static GridPane grid = new GridPane();
static Button button;
static Button buttonstep;
static Button seedbutton;
static Button quitbutton;
static Button clearbutton;
static Button runbutton;
static Button stopbutton;
static Group grappo;
static Stage secondaryStage;
static ComboBox typesofcritters;
static ComboBox numofcritters;
static ComboBox statsofcritters;
static ComboBox numofsteps;
static ComboBox speeds;
static Stage thirdstage;
static TextArea Consolebox;
static double scale;

@Override
public void start(Stage primaryStage) {
	
	
	try {	Pane layout = new Pane();		
			Pane critterworld = new Pane();
			Stage secondaryStage = new Stage();
			//Group grappo;
			Stage thirdstage = new Stage();
			Consolebox = new TextArea();
			VBox VirtualBox = new VBox();
			
			OutputStream theoutstream = new OutputStream() {
				@Override
				public void write(int console) throws IOException {
					Consolebox.appendText(String.valueOf((char) console));
				}
			};
			VirtualBox.getChildren().add(Consolebox);
			System.setOut(new PrintStream(theoutstream, true));
			System.setErr(new PrintStream(theoutstream, true));
		
			Scene thirdscreen = new Scene(VirtualBox, 320, 300);
			
			primaryStage.setTitle("Critters2");
			secondaryStage.setTitle("CritterWorld");
			grappo = new Group();

			List<String> results = new ArrayList<String>();
			List<String> listof = new ArrayList<String>();
			File[] files = new File("src/" + Critter.class.getPackage().toString().split(" ")[1]).listFiles();
			// If this pathname does not denote a directory, then listFiles()
			// returns null.

			for (File file : files) {
				if (file.isFile()) {

					results.add(file.getName().split("\\.")[0]);
				}

			}
			int size = results.size();
			for (int i = 0; i < size; i++) {
				try {
					// String classname =results.get(i).toString();
					// StringBuilder classnames =new StringBuilder (classname);			
					Class<?> mycritter = Class
							.forName(Critter.class.getPackage().toString().split(" ")[1] + "." + results.get(i));
					if (Class.forName(Critter.class.getPackage().toString().split(" ")[1] + ".Critter")
							.isAssignableFrom(mycritter)) {
						listof.add(results.get(i));
					}
				} catch (Exception e) {
				}
			}
			listof.remove(2);
		
			/// Welcome to Critters lab! 
			Label welcome= new Label("Welcome to Critters");
			welcome.setLayoutX(10);
			welcome.setLayoutY(10);
			
			
			// Creat the Label Create a Critter and the dropdown box for it
			Label createacritter = new Label("Create a Critter (Eg.Algae)");
			createacritter.setLayoutX(10);
			createacritter.setLayoutY(110);
			ComboBox typesofcritters = new ComboBox();
			for (int i = 0; i < listof.size(); i++) {
				typesofcritters.getItems().addAll(listof.get(i));
			}
			typesofcritters.setPromptText("Choose a Critter");
			typesofcritters.setLayoutX(200);
			typesofcritters.setLayoutY(110);
			
			// Number of critters and its dropdown box
			Label numberofcritters = new Label("Number of Critters");
			numberofcritters.setLayoutX(10);
			numberofcritters.setLayoutY(160);
			ComboBox numofcritters = new ComboBox();
			numofcritters.getItems().addAll("1", "10", "100", "1000");
			numofcritters.setPromptText("Choose a Number");
			numofcritters.setLayoutX(200);
			numofcritters.setLayoutY(160);
			
			// Add a critter button
			button = new Button();
			button.setText("Add Critter");
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {

					try {
						String crittersname = typesofcritters.getValue().toString();
						int number=0;
						if(numofcritters.getValue()==null){
							 number=1;
						}
						else{
						 number = Integer.parseInt(numofcritters.getValue().toString());}

						makecritterscreen(crittersname, number);
						Critter.displayWorld(critterworld);

					} catch (Exception e) {
						
					}
				}

			});
			button.setLayoutX(360);
			button.setLayoutY(200);
			
			
			
			// Seed Label, Textfield and button
			Label seedslabel = new Label("Seeds");
			seedslabel.setLayoutX(10);
			seedslabel.setLayoutY(240);
			TextField seedstext = new TextField();
			seedstext.setPromptText("Set Random Number");
			seedstext.setLayoutX(200);
			seedstext.setLayoutY(240);

			seedbutton = new Button();
			seedbutton.setText("Seed");
			seedbutton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					try{
					int numberseed = Integer.parseInt(seedstext.getText());
					Critter.setSeed((long)numberseed);}
					catch(Exception e){}
				}
			});
			seedbutton.setLayoutX(360);
			seedbutton.setLayoutY(240);
			
			
			// steps Label and drop down box and button
			Label stepslabel= new Label("# of steps");
			stepslabel.setLayoutX(10);
			stepslabel.setLayoutY(300);
			
			ComboBox numofsteps = new ComboBox();
			numofsteps.getItems().addAll("1", "10", "100", "1000");
			numofsteps.setPromptText("Choose a Number");
			numofsteps.setLayoutX(200);
			numofsteps.setLayoutY(300);
			
			buttonstep = new Button();
			buttonstep.setText("step");
			buttonstep.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event){
					if(numofsteps.getValue()==null){
						grappo.getChildren().clear();
						Critter.worldTimeStep();
						Critter.displayWorld(new Object());
					}
					else {int numsteps = Integer.parseInt(numofsteps.getValue().toString());
						for (int i = 0; i < numsteps; i++) {
							grappo.getChildren().clear();
							Critter.worldTimeStep();
						}
					}
					Critter.displayWorld(new Object());
				}
			});
			buttonstep.setLayoutX(360);
			buttonstep.setLayoutY(300);
			
			// Runstats Label, Dropdown box for runstats
			Label statslabel = new Label("Run stats for:");
			statslabel.setLayoutX(10);
			statslabel.setLayoutY(350);

			ComboBox statsofcritters = new ComboBox();
			for (int i = 0; i < listof.size(); i++) {
				statsofcritters.getItems().addAll(listof.get(i));
			}
			statsofcritters.setPromptText("Choose a Critter");
			statsofcritters.setLayoutX(200);
			statsofcritters.setLayoutY(350);
			
			// speed label, dropdown box
			Label speedlabel = new Label("Speed:");
			speedlabel.setLayoutX(10);
			speedlabel.setLayoutY(390);

			ComboBox speeds = new ComboBox();
			speeds.getItems().addAll(1.0, 10.0, 100.0);
			speeds.setPromptText("Choose a speed");
			speeds.setLayoutX(200);
			speeds.setLayoutY(390);
			
			// Run button
			runbutton = new Button();
			runbutton.setText("Run");
			runbutton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {		
					
					try {
						String critterstat = statsofcritters.getValue().toString();
						//System.out.println(critterstat);
						Consolebox.clear();
					Class<?> critters1 = Class.forName(Critter.class.getPackage().toString().split(" ")[1] + "."  + critterstat);
						Method meth = critters1.getMethod("runStats", List.class);    //this method runs the getMethod from critters
				        meth.invoke(null, Critter.getInstances(critterstat)); //finally, the getInstances is called and uses the critter class that the user specified

					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}//retrieves the class from the string   
				}
			});
			runbutton.setLayoutX(360);
			runbutton.setLayoutY(390);
			
			// Clear button
			clearbutton = new Button();
			clearbutton.setText("Clear");
			clearbutton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					grappo.getChildren().clear();
					Critter.clearWorld();
					Critter.displayWorld(new Object());
				}

			});
			clearbutton.setLayoutX(200);
			clearbutton.setLayoutY(460);
			
			Timeline timeline = new Timeline();
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.setAutoReverse(true);
			@SuppressWarnings("rawtypes")
			EventHandler animation = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					try{
						
						Double yo=0.0;
						double speedofanimation = yo.parseDouble(speeds.getValue().toString());
						//double sped = (double) speedofanimation;
						TimeUnit.MILLISECONDS.sleep((long) (1000.0 - speedofanimation*300));
					}
					catch(Exception e){
						//e.printStackTrace();
						Double yo =1.0;
						try {
							TimeUnit.MILLISECONDS.sleep((long) (1000.0 - yo*300));
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							//e1.printStackTrace();
						}
					}
				
				Critter.worldTimeStep();	
				grappo.getChildren().clear();
				Critter.displayWorld(new Object());
				try{
				RunStat(statsofcritters.getValue().toString());}
				catch(Exception e){}
				}
			};
			Duration dure = Duration.millis(250);
			@SuppressWarnings("unchecked")
			KeyFrame kf = new KeyFrame(dure, animation);
			timeline.getKeyFrames().add(kf);
			
			// STOP button
			stopbutton = new Button();
			stopbutton.setText("Stop");
			stopbutton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					typesofcritters.setDisable(false);
					numofcritters.setDisable(false);
					button.setDisable(false);
					seedstext.setDisable(false);
					seedbutton.setDisable(false);
					numofsteps.setDisable(false);
					buttonstep.setDisable(false);
					statsofcritters.setDisable(false);
					speeds.setDisable(false);
					runbutton.setDisable(false);
					clearbutton.setDisable(false);
					quitbutton.setDisable(false);
					timeline.stop();

				}

			});
			stopbutton.setLayoutX(450);
			stopbutton.setLayoutY(390);
			
			//Animation stuff
			Button Starter = new Button();
			Starter.setText("Start Animation");
			Starter.setLayoutX(300);
			Starter.setLayoutY(460);
			Starter.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					
					typesofcritters.setDisable(true);
					numofcritters.setDisable(true);
					button.setDisable(true);
					seedstext.setDisable(true);
					seedbutton.setDisable(true);
					numofsteps.setDisable(true);
					buttonstep.setDisable(true);
					statsofcritters.setDisable(true);
					speeds.setDisable(true);
					runbutton.setDisable(true);
					clearbutton.setDisable(true);
					quitbutton.setDisable(true);				
					timeline.playFromStart();
				}
	});

			// quit button
			quitbutton = new Button();
			quitbutton.setText("Quit");
			quitbutton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					primaryStage.close();
					secondaryStage.close();
					thirdstage.close();
					// System.exit(0);
				}
			});
			quitbutton.setLayoutX(450);
			quitbutton.setLayoutY(460);
			
			// Putting all the buttons, labels and textfields on to the layout

			layout.getChildren().addAll(button, buttonstep, welcome, createacritter, numberofcritters, seedslabel,
					seedstext, seedbutton, quitbutton, typesofcritters, numofcritters, stepslabel, numofsteps,
					statslabel, statsofcritters, speedlabel, speeds, runbutton, clearbutton, stopbutton, Starter);
			//critterworld.setScene(new Scene(grappo, 1600, 1007, Color.SKYBLUE)); 
			critterworld.getChildren().add(grappo);
			
			Scene scene1 = new Scene(layout, 500, 750);
			primaryStage.setScene(scene1);
			critterworld.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			secondaryStage.setScene(new Scene(critterworld, 1600, 1007));
		
		// This sets the primarystage all the way to the left side
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX(primaryScreenBounds.getMaxX()/2 -190 );// + primaryScreenBounds.getWidth() - 328 to get to the right side
		primaryStage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() -1040);
		
		
		// this sets the secondarystage all the way to the right side
		Rectangle2D secondaryScreenBounds = Screen.getPrimary().getVisualBounds();
		secondaryStage.setX(secondaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 328);//  to get to the right side
		secondaryStage.setY(secondaryScreenBounds.getMinY() + secondaryScreenBounds.getHeight() -1040);
		
		
		thirdstage.setScene(thirdscreen);
		thirdstage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 328);
		thirdstage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 840);
		
		
		secondaryStage.show();
		thirdstage.show();
		primaryStage.show();
		
			// closing both stages at the same time
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(final WindowEvent event) {

					primaryStage.close();
					secondaryStage.close();
					thirdstage.close();
				}

			});

			secondaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(final WindowEvent event) {
					// event.consume();
					// Boolean answer = ConfirmBox.display("Are you sure?");
					primaryStage.close();
					secondaryStage.close();
					thirdstage.close();
				}

			});
			
			thirdstage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(final WindowEvent event) {
					// event.consume();
					// Boolean answer = ConfirmBox.display("Are you sure?");
					primaryStage.close();
					secondaryStage.close();
					thirdstage.close();
				}

			});
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	

}

	public static void makecritterscreen(String Critts, int numberofcrit) {
		for (int i = 0; i < numberofcrit; i++) {
			try {
				Critter.makeCritter(Critts);

			} catch (InvalidCritterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Critter.displayWorld(new Object());
		}

	}

	
public void RunStat(String val){
		
		try{
			Consolebox.clear();
		Class<?> crit = Class.forName(Critter.class.getPackage().toString().split(" ")[1] + "." + val);//retrieves the class from the
		Method meth = crit.getMethod("runStats", List.class);    //string. this gets a method
		meth.invoke(null, Critter.getInstances(val));
		}
		catch(Exception e){
			//e.printStackTrace();
		}
	}

	
	
	public static void makeshapesofcrits(Critter thecrit, int x_coord, int y_coord) {
		double xcoordinate = x_coord*12+6 ;
		double ycoordinate = y_coord*12+6 ;
		switch (thecrit.viewShape()) {

		case CIRCLE:
			Circle circlecritter = new Circle(xcoordinate, ycoordinate, 3);
			circlecritter.setFill(thecrit.viewColor());
			circlecritter.setStroke(thecrit.viewOutlineColor());
			grappo.getChildren().addAll(circlecritter);
			break;
		case SQUARE:
			Rectangle square = new Rectangle(xcoordinate - 3, ycoordinate - 3, 6, 6);
			square.setStroke(thecrit.viewOutlineColor());
			square.setFill(thecrit.viewColor());
			grappo.getChildren().addAll(square);
			break;
		case TRIANGLE:
			Polygon Triangle = new Polygon();
			Double[] Trianglepoints = { xcoordinate, ycoordinate - 6, xcoordinate - 6, ycoordinate + 6, xcoordinate + 6,
					ycoordinate + 6 };
			Triangle.getPoints().addAll(Trianglepoints);
			Triangle.setFill(thecrit.viewColor());
			Triangle.setStroke(thecrit.viewOutlineColor());
			
			grappo.getChildren().add(Triangle);
			break;
		case DIAMOND:
			Double[] Diamondpoints = { xcoordinate, ycoordinate - 6, xcoordinate - 6, ycoordinate, xcoordinate,
					ycoordinate + 6, xcoordinate + 6, ycoordinate };
			Polygon Diamond = new Polygon();
			Diamond.getPoints().addAll(Diamondpoints);
			Diamond.setFill(thecrit.viewColor());
			Diamond.setStroke(thecrit.viewOutlineColor());
			grappo.getChildren().add(Diamond);
			break;
		case STAR:
			Double[] Starcoordinates = {
			xcoordinate,ycoordinate-6, 
			xcoordinate+1.2,ycoordinate-1.2, 
			xcoordinate+6,ycoordinate-1.2, 
			xcoordinate+3,ycoordinate+1.2, 
			xcoordinate+4.2,ycoordinate+6, 
			xcoordinate,ycoordinate+3, 
			xcoordinate-4.2,ycoordinate+6,
			xcoordinate-3,ycoordinate+1.2, 
			xcoordinate-6,ycoordinate-1.2, 
			xcoordinate-1.2,ycoordinate-1.2  
			};
			Polygon DarkStar = new Polygon();
			DarkStar.getPoints().addAll(Starcoordinates);
			DarkStar.setFill(thecrit.viewColor());
			DarkStar.setStroke(thecrit.viewOutlineColor());
			grappo.getChildren().add(DarkStar);
			break;
		
		}

	}

	public static void main(String[] args) {
		launch(args);

	}

}
