package gui;

import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import main.TestGame;

public class GamePlay extends Application{
//		public static void main(String[] args) {
//			Application.launch(args);
//		}
	private static Stage stage ;
//		private static final int MIN_PIXELS = 10;
		private static int width =1024, height=800;
		private int mapChosen = 1; // 1-> Egypt , 2-> US
		private String player1_type = "Human" , player2_type = "Greedy AI bot"; // 1,2,3,4 for them like mentioned in the PDF
		public static int turn = 1; // 1 for Green / 2 for Red turn
//		private static HashMap<Color,Integer>colors_city = new HashMap<>();
		private static VBox leftPlayer , rightPlayer;
		private static Label GameStateBar = new Label("Player1's Turn");
		private static StringProperty play1Phase = new SimpleStringProperty("Idle"), play2Phase= new SimpleStringProperty("Idle"); // Placing / Attack / fortifying / Idle 
		private static StringProperty play1Troops = new SimpleStringProperty("0"),play2Troops = new SimpleStringProperty("0");
		private static StringProperty play1Terri = new SimpleStringProperty("0"),play2Terri = new SimpleStringProperty("0");
		private static Button nextPhase = new Button("Next>>");
		private static HashMap<Integer,Point2D> cities = new HashMap<>();
		private static Button []buttons ;
		@Override
		public void start(Stage pStage) throws Exception {
			leftPlayer = createPlayerPanel(1,player1_type);
			rightPlayer = createPlayerPanel(2,player2_type);
//			fillColors();
//			nextPhase();
			//changeTurns();
//			nextPhase();
//			increaseTerri(turn, 50);	changeTurns();	increaseTerri(turn, 50);
//			leftPlayer.setBorder(new Border(new BorderStroke(Color.GREY, 
//		            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//			rightPlayer.setBorder(new Border(new BorderStroke(Color.GREY, 
//		            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//			
			
			nextPhase.getStylesheets().add("css/TextStyles.css");nextPhase.setPadding(new Insets(15,0,15,15));
			nextPhase.setPrefSize(150, 50);
			if(player1_type.equalsIgnoreCase("Human")) {
			leftPlayer.getChildren().add(nextPhase);
    		leftPlayer.setMargin(nextPhase, new Insets(20,0,20,20));
			}
			Pane map = new Pane();//map.setAlignment(Pos.CENTER);
//			map.setBorder(new Border(new BorderStroke(Color.GREY, 
//		            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
			
			String src = mapChosen==1?"pics/EgyptMap.jpg":"pics/USMap2.png";
			Image OriginalIMG = new Image(src);//OriginalImage
			ImageView mapIMG1 = new ImageView(OriginalIMG);
			//Image fakeIMG = new Image("pics/EgyptMapCOLORED.jpg");//FakeImage
			//ImageView mapIMG2 = new ImageView(fakeIMG);
			//mapIMG2.setOpacity(0.0001);
			map.getChildren().addAll(mapIMG1);//,mapIMG2);
			if(mapChosen==1)
			{
				EgyptCitiesPositions();
				setButtonsOnMap(map,27);
				
			}
			else if (mapChosen ==2)
			{
				width = 1024+181;
				height = 800;
				USStatesPositions();
				setButtonsOnMap(map,50);
			}
			
			BorderPane middlePane = new BorderPane();
			middlePane.setCenter(map);
			
			GameStateBar.getStylesheets().add("css/TextStyles.css"); 
			middlePane.setTop(GameStateBar);
			middlePane.setAlignment(GameStateBar, Pos.TOP_CENTER);middlePane.setMargin(GameStateBar, new Insets(30,0,30,0));
			//For Image Zooming
//			double width1 = OriginalIMG.getWidth();
//	        double height1 = OriginalIMG.getHeight();
//	        double width2 = fakeIMG.getWidth();
//	        double height2 = fakeIMG.getHeight();
////			mapIMG2.setPreserveRatio(true);
//	        reset(mapIMG1, width1, height1);
//	        reset(mapIMG2, width2, height2);

//			
			HBox pane = new HBox();pane.getChildren().addAll(leftPlayer,middlePane,rightPlayer);
			pane.setMargin(leftPlayer, new Insets(0,0,0,5));
			//Image bg = new Image("pics/RISKPhoto1.jpg");
			//leftPlayer.getChildren().get(index)
			
			
			//Actions
//			setButtonsActions(buttons); // Moved to only in the Human mode
//			mapIMG2.setOnMouseClicked(e->{
//				System.out.println("["+e.getX()+", "+e.getY()+"]");
//				PixelReader pixelReader;
//				pixelReader = fakeIMG.getPixelReader();
//				int color = pixelReader.getArgb((int) e.getX(), (int) e.getY());
//				int red = (color >> 16) & 0xff;
//		        int green = (color >> 8) & 0xff;
//		        int blue = color & 0xff;
//		        //c.setFill(Color.rgb(red, green, blue));
//		        System.out.println(Color.rgb(red, green, blue));
//		        if(colors_city.containsKey(Color.rgb(red, green, blue))){
//		        	System.out.println(colors_city.get(Color.rgb(red, green, blue)));
//		        }
//		        else System.out.println("You didn't click on a city :P");
//			});
			nextPhase.setOnAction(e->{
//				if(!play1Phase.get().equalsIgnoreCase("Attacking"))
				nextPhase();
//				else changeTurns();
			});
			
			Image bg = new Image("pics/bg2.jpg");
			
			pane.setBackground(new Background(new BackgroundImage(bg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
					BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
			
			
			
			Scene scene = new Scene(pane,width,height); //Width , Height
			//Positions And Sizes
			leftPlayer.setPrefSize(200, 800);rightPlayer.setPrefSize(200, 800);
			pane.setMargin(middlePane, new Insets(0,10,0,0));
			//map.setPrefSize(635, 700);
			//mapIMG2.setFitHeight(650);mapIMG2.setFitWidth(635);mapIMG1.setFitHeight(650);mapIMG1.setFitWidth(635);
//			mapIMG2.fitWidthProperty().bind(map.widthProperty());
//	        mapIMG2.fitHeightProperty().bind(map.heightProperty());
//	        mapIMG1.fitWidthProperty().bind(map.widthProperty());
//	        mapIMG1.fitHeightProperty().bind(map.heightProperty());
//	        
			
			pStage.setScene(scene);
			pStage.setTitle("RISK Demo");
			pStage.show();
			pStage.setResizable(false);
			pStage.centerOnScreen();
		}

//	    // reset to the top left:
//	    private void reset(ImageView imageView, double width, double height) {
//	        imageView.setViewport(new Rectangle2D(0, 0, width, height));
//	    }
//	    private double clamp(double value, double min, double max) {
//
//	        if (value < min)
//	            return min;
//	        if (value > max)
//	            return max;
//	        return value;
//	    }
//	 // convert mouse coordinates in the imageView to coordinates in the actual image:
//	    private Point2D imageViewToImage(ImageView imageView, Point2D imageViewCoordinates) {
//	        double xProportion = imageViewCoordinates.getX() / imageView.getBoundsInLocal().getWidth();
//	        double yProportion = imageViewCoordinates.getY() / imageView.getBoundsInLocal().getHeight();
//
//	        Rectangle2D viewport = imageView.getViewport();
//	        return new Point2D(
//	                viewport.getMinX() + xProportion * viewport.getWidth(), 
//	                viewport.getMinY() + yProportion * viewport.getHeight());
//	    }
	    
	    private static VBox createPlayerPanel(int pNum  , String playerType) {
	    	
	    	Label name = new Label(playerType);name.getStylesheets().add("css/TextStyles.css");
			Label player1 = new Label("Player"+pNum);player1.getStylesheets().add("css/TextStyles.css");
			Label Troops = new Label("Troops:");Troops.getStylesheets().add("css/TextStyles.css");
			Label noTroops = new Label();noTroops.getStylesheets().add("css/ShinyOrange.css");
			Label RoundPhase = new Label();RoundPhase.getStylesheets().add("css/TextStyles.css");//Attacking / placing troops
			Label Territories = new Label("Territories:");Territories.getStylesheets().add("css/TextStyles.css");
			Label noOwnedTerritories = new Label();noOwnedTerritories.getStylesheets().add("css/ShinyOrange.css");
			Territories.setGraphic(noOwnedTerritories);Territories.setContentDisplay(ContentDisplay.RIGHT);
			Troops.setGraphic(noTroops);Troops.setContentDisplay(ContentDisplay.RIGHT);
			VBox UpPart = new VBox();
			UpPart.getChildren().addAll(player1,name);
			UpPart.setAlignment(Pos.TOP_CENTER);
			UpPart.setMargin(player1, new Insets(15,0,15,0));
			String src ;
	    	if(pNum==1) {
	    		RoundPhase.setText(play1Phase.get());RoundPhase.textProperty().bind(play1Phase);
	    		noTroops.setText(play1Troops.get());noTroops.textProperty().bind(play1Troops);
	    		noOwnedTerritories.setText(play1Terri.get());noOwnedTerritories.textProperty().bind(play1Terri);	
	    		src = "pics/GreenSoldier.png";
	    	}
	    	else {
	    		RoundPhase.setText("RoundPhase:"+play2Phase.get());RoundPhase.textProperty().bind(play2Phase);
	    		noTroops.setText("Troops:"+play2Troops.get());noTroops.textProperty().bind(play2Troops);
	    		noOwnedTerritories.setText("Territories:"+play2Terri.get());noOwnedTerritories.textProperty().bind(play2Terri);	
	    		src = "pics/RedSoldier.png";
	    	}
	    	
	    	
	    	Image playerIMG = new Image(src);
	    	ImageView PImage = new ImageView(playerIMG);
	    	PImage.setFitHeight(300);PImage.setFitWidth(150);
			
	    	VBox player = new VBox();
	    	player.getChildren().addAll(UpPart,RoundPhase,Troops,Territories,PImage);
	    	player.setMargin(Troops, new Insets(15,0,15,0));player.setMargin(Territories, new Insets(15,0,15,0));//player.setMargin(RoundPhase, new Insets(0,0,0,0));
	    	player.setMargin(UpPart, new Insets(0,0,15,0));player.setMargin(PImage, new Insets(0,0,0,15));
	    	PImage.setLayoutY(player.getHeight());
	    	
	    	return player;
	    }
	    public static void changeTurns() {
	    	if(turn == 1) {
	    		turn = 2;
	    		GameStateBar.setText("Player2's Turn");
	    		play1Phase.setValue("Idle");
	    		play2Phase.setValue("Idle");
	    		
	    		if(!nextPhase.isDisable())nextPhase.setDisable(true);
	    	}
	    	else if(turn ==2 ){
	    		turn = 1;
	    		GameStateBar.setText("Player1's Turn");
	    		play2Phase.setValue("Idle");
	    		play1Phase.setValue("Idle");
	    		nextPhase();
	    		if(nextPhase.isDisable())nextPhase.setDisable(false);
	    	}
	    }
	    public static void nextPhase() {
	    	if(TestGame.CheckWinState()) {
	    		Stage st  = new Stage();
	    		TestGame.getWinner();
	    		GameOver go = new GameOver();
	    		go.setWinner(TestGame.getWinner().getId());
	    		try {
					go.start(st);
					turn = 0;
					play2Phase.setValue("GameOver");
					play1Phase.setValue("GameOver");
					stage.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}else {
	    	
	    	 if(turn ==1) {
	    		if(play1Phase.get().equalsIgnoreCase("Idle")) play1Phase.setValue("Placing");
	    		else if(play1Phase.get().equalsIgnoreCase("Placing")) play1Phase.setValue("Attacking");
	    		else if(play1Phase.get().equalsIgnoreCase("Attacking")) {
	    			//play1Phase.setValue("Idle");
	    			changeTurns();
	    		}
	    	}
	    	 if(turn ==2) {
	    		if(play2Phase.get().equalsIgnoreCase("Idle")) play2Phase.setValue("Placing");
	    		else if(play2Phase.get().equalsIgnoreCase("Placing")) play2Phase.setValue("Attacking");
	    		else if(play2Phase.get().equalsIgnoreCase("Attacking")) {
	    			//play2Phase.setValue("Idle");
	    			changeTurns();
	    		}
	    	}
	    	}
//	    	System.out.println("CURR TURN :"+turn);
//	    	System.out.println("Curr Phases: 1: "+play1Phase.get()+"  2:"+play2Phase.get());
	    }
	    public static void increaseTroops(int playerNum , int amount) {
	    	if(playerNum ==1) {
	    		play1Troops.setValue(""+(Integer.parseInt(play1Troops.get())+amount));
	    	}
	    	else if(playerNum ==2) {
	    		play2Troops.setValue(""+(Integer.parseInt(play2Troops.get())+amount));
	    	}
	    }
	    public static void increaseTerri(int playerNum , int amount) {
	    	if(playerNum ==1) {
	    		play1Terri.setValue(""+(Integer.parseInt(play1Terri.get())+amount));
	    		//System.out.println(play1Terri.get());
	    	}
	    	else if(playerNum ==2) {
	    		play2Terri.setValue(""+(Integer.parseInt(play2Terri.get())+amount));
	    	}
	    }
	    private static void setButtonsOnMap(Pane map ,int  n) {
	    	buttons = new Button[n];
			for(int i=1;i<=n;i++) {
				Button but = new Button("0");//+i);
				Point2D pt = cities.get(i);
				but.setPrefWidth(30);but.setPrefHeight(25);
				but.setLayoutX(pt.getX()-15);
				but.setLayoutY(pt.getY()-10);
				but.setWrapText(true);
				map.getChildren().add(but);
//				but.setOpacity(0.5);
				but.getStylesheets().add("css/ShinyOrange.css");
				but.setStyle("-fx-font-size:12");
				buttons[i-1] = but;
			}
	    }
	    private static void EgyptCitiesPositions() {
			cities.put(1,new Point2D(111.0, 125.0)) ;
			cities.put(2,new Point2D(255.0, 67.0)) ;
			cities.put(3,new Point2D(280.0, 62.0)) ;
			cities.put(4,new Point2D(307.0, 42.0)) ;
			cities.put(5,new Point2D(333.0, 54.0)) ;
			cities.put(6,new Point2D(352.0, 10.0)) ;
			cities.put(7,new Point2D(388.0, 24.0)) ;
			cities.put(8,new Point2D(434.0, 89.0)) ;
			cities.put(9,new Point2D(313.0, 63.0)) ;
			cities.put(10,new Point2D(287.0, 83.0)) ;
			cities.put(11,new Point2D(304.0, 107.0)) ;
			cities.put(12,new Point2D(352.0, 68.0)) ;
			cities.put(13,new Point2D(375.0, 78.0)) ;
			cities.put(14,new Point2D(235.0, 161.0)) ;
			cities.put(15,new Point2D(298.0, 140.0)) ;
			cities.put(16,new Point2D(339.0, 137.0)) ;
			cities.put(17,new Point2D(368.0, 119.0)) ;
			cities.put(18,new Point2D(444.0, 157.0)) ;
			cities.put(19,new Point2D(304.0, 163.0)) ;
			cities.put(20,new Point2D(269.0, 201.0)) ;
			cities.put(21,new Point2D(160.0, 383.0)) ;
			cities.put(22,new Point2D(306.0, 257.0)) ;
			cities.put(23,new Point2D(403.0, 259.0)) ;
			cities.put(24,new Point2D(331.0, 291.0)) ;
			cities.put(25,new Point2D(344.0, 326.0)) ;
			cities.put(26,new Point2D(408.0, 337.0)) ;
			cities.put(27,new Point2D(376.0, 457.0)) ;
		}
	    private void USStatesPositions() {
			cities.put(1,new Point2D(142.0, 150.0)) ;
			cities.put(2,new Point2D(109.0, 200.0)) ;
			cities.put(3,new Point2D(102.0, 312.0)) ;
			cities.put(4,new Point2D(148.0, 285.0)) ;
			cities.put(5,new Point2D(196.0, 223.0)) ;
			cities.put(6,new Point2D(250.0, 181.0)) ;
			cities.put(7,new Point2D(268.0, 245.0)) ;
			cities.put(8,new Point2D(206.0, 301.0)) ;
			cities.put(9,new Point2D(201.0, 382.0)) ;
			cities.put(10,new Point2D(289.0, 314.0)) ;
			cities.put(11,new Point2D(280.0, 392.0)) ;
			cities.put(12,new Point2D(370.0, 436.0)) ;
			cities.put(13,new Point2D(405.0, 377.0)) ;
			cities.put(14,new Point2D(385.0, 332.0)) ;
			cities.put(15,new Point2D(376.0, 284.0)) ;
			cities.put(16,new Point2D(368.0, 232.0)) ;
			cities.put(17,new Point2D(364.0, 182.0)) ;
			cities.put(18,new Point2D(438.0, 202.0)) ;
			cities.put(19,new Point2D(450.0, 273.0)) ;
			cities.put(20,new Point2D(461.0, 336.0)) ;
			cities.put(21,new Point2D(464.0, 391.0)) ;
			cities.put(22,new Point2D(473.0, 453.0)) ;
			cities.put(23,new Point2D(509.0, 420.0)) ;
			cities.put(24,new Point2D(549.0, 415.0)) ;
			cities.put(25,new Point2D(634.0, 483.0)) ;
			cities.put(26,new Point2D(597.0, 418.0)) ;
			cities.put(27,new Point2D(633.0, 393.0)) ;
			cities.put(28,new Point2D(656.0, 359.0)) ;
			cities.put(29,new Point2D(651.0, 325.0)) ;
			cities.put(30,new Point2D(619.0, 320.0)) ;
			cities.put(31,new Point2D(552.0, 367.0)) ;
			cities.put(32,new Point2D(575.0, 339.0)) ;
			cities.put(33,new Point2D(508.0, 304.0)) ;
			cities.put(34,new Point2D(498.0, 232.0)) ;
			cities.put(35,new Point2D(552.0, 301.0)) ;
			cities.put(36,new Point2D(561.0, 247.0)) ;
			cities.put(37,new Point2D(588.0, 295.0)) ;
			cities.put(38,new Point2D(642.0, 271.0)) ;
			cities.put(39,new Point2D(681.0, 232.0)) ;
			cities.put(40,new Point2D(670.0, 180.0)) ;
			cities.put(41,new Point2D(705.0, 162.0)) ;
			cities.put(42,new Point2D(741.0, 174.0)) ;
			cities.put(43,new Point2D(756.0, 220.0)) ;
			cities.put(44,new Point2D(763.0, 251.0)) ;
			cities.put(45,new Point2D(747.0, 270.0)) ;
			cities.put(46,new Point2D(721.0, 283.0)) ;
			cities.put(47,new Point2D(712.0, 306.0)) ;
			cities.put(48,new Point2D(721.0, 334.0)) ;
			cities.put(49,new Point2D(72.0, 480.0)) ;
			cities.put(50,new Point2D(93.0, 46.0)) ;
		}
//	    private static void setButtonsActions(Button button) {
////	    	for(int i=0;i<buttonz.length;i++) {
////	    		Button bt = buttonz[i];
//	    		button.setOnAction(e->{
//	    			PlaceTroops pt = new PlaceTroops();
//	    			Stage st = new Stage();
//	    			try {
//						pt.start(st);
////						if(!st.isShowing())
//						int troops = pt.getNoTroops();
//						IntegerProperty trps = new SimpleIntegerProperty(troops);trps.bind(pt.getTrs());
//						trps.addListener(new InvalidationListener() {
//							
//							@Override
//							public void invalidated(Observable observable) {
//								System.out.println(trps.get());
//								
//							}
//						});
//						//System.out.println(trps.get());
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//	    			
//	    			//System.out.println(bt.getText());
//	    		});
////	    	}
//	    }
		public String getPlayer1_type() {
			return player1_type;
		}
		public void setPlayer1_type(String player1_type) {
			this.player1_type = player1_type;
		}
		public String getPlayer2_type() {
			return player2_type;
		}
		public void setPlayer2_type(String player2_type) {
			this.player2_type = player2_type;
		}
		
	    public int getMapChosen() {
			return mapChosen;
		}
		public void setMapChosen(int mapChosen) {
			this.mapChosen = mapChosen;
		}
		public void resetScores() {
	    	GameStateBar.setText("Player1's Turn");
	    	turn =1;
			play1Phase.setValue("Placing");play2Phase.setValue("Idle"); // Placing / Attack / fortifying / Idle 
			play1Troops.setValue("0");play2Troops.setValue("0");
			play1Terri.setValue("0");play2Terri.setValue("0");
			
	    }

		public static StringProperty getPlay1Phase() {
			return play1Phase;
		}

		public static StringProperty getPlay2Phase() {
			return play2Phase;
		}

		public static Button[] getButtons() {
			return buttons;
		}

		public static int getTurn() {
			return turn;
		}

		public static StringProperty getPlay1Troops() {
			return play1Troops;
		}

		public static void setPlay1Troops(int  play1Troops) {
			GamePlay.play1Troops.setValue(""+play1Troops);
		}

		public static StringProperty getPlay2Troops() {
			return play2Troops;
		}

		public static void setPlay2Troops(int play2Troops) {
			GamePlay.play2Troops.setValue(""+play2Troops);
		}

		public static StringProperty getPlay1Terri() {
			return play1Terri;
		}

		public static void setPlay1Terri(int play1Terri) {
			GamePlay.play1Terri.setValue(""+play1Terri);
		}

		public static StringProperty getPlay2Terri() {
			return play2Terri;
		}

		public static void setPlay2Terri(int play2Terri) {
			GamePlay.play2Terri.setValue(""+play2Terri);
		}

		public static void setPlay1Phase(String play1Phase) {
			GamePlay.play1Phase.setValue(play1Phase);
		}

		public Stage getStage() {
			return stage;
		}

		public void setStage(Stage stagee) {
			stage = stagee;
		}
		public static void close() {
			if(stage.isShowing())
			stage.close();
		}
		
		
}

//mapIMG2.setOnScroll(e -> {
//double delta = e.getDeltaY();
//Rectangle2D viewport = mapIMG2.getViewport();
//
//double scale = clamp(Math.pow(0.99, delta),
//
//    // don't scale so we're zoomed in to fewer than MIN_PIXELS in any direction:
//    Math.min(MIN_PIXELS / viewport.getWidth(), MIN_PIXELS / viewport.getHeight()),
//
//    // don't scale so that we're bigger than image dimensions:
//    Math.max(width2 / viewport.getWidth(), height2 / viewport.getHeight())
//
//);
//
//Point2D mouse = imageViewToImage(mapIMG2, new Point2D(e.getX(), e.getY()));
////Point2D mouse = imageViewToImage(mapIMG1, new Point2D(e.getX(), e.getY()));
//
//double newWidth = viewport.getWidth() * scale;
//double newHeight = viewport.getHeight() * scale;
//
//// To keep the visual point under the mouse from moving, we need
//// (x - newViewportMinX) / (x - currentViewportMinX) = scale
//// where x is the mouse X coordinate in the image
//
//// solving this for newViewportMinX gives
//
//// newViewportMinX = x - (x - currentViewportMinX) * scale 
//
//// we then clamp this value so the image never scrolls out
//// of the imageview:
//
//double newMinX = clamp(mouse.getX() - (mouse.getX() - viewport.getMinX()) * scale, 
//        0, width2 - newWidth);
//double newMinY = clamp(mouse.getY() - (mouse.getY() - viewport.getMinY()) * scale, 
//        0, height2 - newHeight);
//
//mapIMG2.setViewport(new Rectangle2D(newMinX, newMinY, newWidth, newHeight));
//mapIMG1.setViewport(new Rectangle2D(newMinX, newMinY, newWidth, newHeight));
////
////mapIMG2.setFitHeight(newHeight);mapIMG2.setFitWidth(newWidth);mapIMG2.setLayoutX(newMinX);mapIMG2.setLayoutY(newMinY);
//
//});

//mapIMG2.setOnMouseClicked(e -> {
//    if (e.getClickCount() == 2) {
//    reset(mapIMG1, width1, height1);
//    reset(mapIMG2, width2, height2);
//}
//});


//mapIMG2.setOnMouseClicked(value);

//pane.setBackground(new Background(new BackgroundImage(bg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, 
//BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
//