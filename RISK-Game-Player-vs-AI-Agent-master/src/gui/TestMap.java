//package gui;
//
//import java.awt.Point;
//import java.util.HashMap;
//
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.geometry.Point2D;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.image.PixelReader;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundImage;
//import javafx.scene.layout.BackgroundPosition;
//import javafx.scene.layout.BackgroundRepeat;
//import javafx.scene.layout.BackgroundSize;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.stage.Stage;
//
//public class TestMap extends Application{
//		public static void main(String[] args) {
//			Application.launch(args);
//		}
//		private int mapChosen = 1; // 1-> Egypt , 2-> US
//		private static HashMap<Integer,Point2D> cities = new HashMap<>();
//		private static Button []buttons ;
//		@Override
//		public void start(Stage pStage) throws Exception {
////			EgyptCitiesPositions();
//			USStatesPositions();
//			Circle c = new Circle(30);
//			
//			VBox leftPlayer = new VBox();
//			VBox rightPlayer = new VBox();
//			Pane map = new Pane();
//			Image img = new Image("pics/US.jpg");
//			ImageView mapIMG = new ImageView(img);
//			map.getChildren().addAll(mapIMG);
//			
//			HBox pane = new HBox();pane.getChildren().addAll(leftPlayer,map,rightPlayer,c);
//			//Image bg = new Image("pics/RISKPhoto1.jpg");
//			
//			mapIMG.setOnMouseClicked(e->{
//				System.out.println("["+e.getX()+", "+e.getY()+"]");
//				PixelReader pixelReader;
//				pixelReader = img.getPixelReader();
//				int color = pixelReader.getArgb((int) e.getX(), (int) e.getY());
//				int red = (color >> 16) & 0xff;
//		        int green = (color >> 8) & 0xff;
//		        int blue = color & 0xff;
//		        c.setFill(Color.rgb(red, green, blue));
//			});
//			setButtonsOnUSMap(map,50);
//			
////			pane.setBackground(new Background(new BackgroundImage(bg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, 
////					BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
////			
////			
//			Scene scene = new Scene(pane,1024,800); //Width , Height
//			//Positions And Sizes
//			
//			
//			
//			
//			pStage.setScene(scene);
//			pStage.setTitle("RISK Demo");
//			pStage.show();
//			pStage.setResizable(false);
//			
//		}
//		private void citiesButtons() {
//			
//		}
//		private static void setButtonsOnUSMap(Pane map ,int  n) {
//	    	buttons = new Button[n];
//			for(int i=1;i<=n;i++) {
//				Button but = new Button(""+i);
//				Point2D pt = cities.get(i);
//				but.setPrefWidth(30);
//				but.setLayoutX(pt.getX()-15);
//				but.setLayoutY(pt.getY()-10);
//				map.getChildren().add(but);
////				but.setOpacity(0.5);
//				but.getStylesheets().add("css/ShinyOrange.css");
//				but.setStyle("-fx-font-size:12");
//				buttons[i-1] = but;
//				
//			}
//			
//	    }
//		private void EgyptCitiesPositions() {
//			cities.put(1,new Point2D(111.0, 125.0)) ;
//			cities.put(2,new Point2D(255.0, 67.0)) ;
//			cities.put(3,new Point2D(280.0, 62.0)) ;
//			cities.put(4,new Point2D(307.0, 42.0)) ;
//			cities.put(5,new Point2D(333.0, 54.0)) ;
//			cities.put(6,new Point2D(352.0, 10.0)) ;
//			cities.put(7,new Point2D(388.0, 24.0)) ;
//			cities.put(8,new Point2D(434.0, 89.0)) ;
//			cities.put(9,new Point2D(313.0, 63.0)) ;
//			cities.put(10,new Point2D(287.0, 83.0)) ;
//			cities.put(11,new Point2D(304.0, 107.0)) ;
//			cities.put(12,new Point2D(352.0, 68.0)) ;
//			cities.put(13,new Point2D(375.0, 78.0)) ;
//			cities.put(14,new Point2D(235.0, 161.0)) ;
//			cities.put(15,new Point2D(298.0, 140.0)) ;
//			cities.put(16,new Point2D(339.0, 137.0)) ;
//			cities.put(17,new Point2D(368.0, 119.0)) ;
//			cities.put(18,new Point2D(444.0, 157.0)) ;
//			cities.put(19,new Point2D(304.0, 163.0)) ;
//			cities.put(20,new Point2D(269.0, 201.0)) ;
//			cities.put(21,new Point2D(160.0, 383.0)) ;
//			cities.put(22,new Point2D(306.0, 257.0)) ;
//			cities.put(23,new Point2D(403.0, 259.0)) ;
//			cities.put(24,new Point2D(331.0, 291.0)) ;
//			cities.put(25,new Point2D(344.0, 326.0)) ;
//			cities.put(26,new Point2D(408.0, 337.0)) ;
//			cities.put(27,new Point2D(376.0, 457.0)) ;
//		}
//		private void USStatesPositions() {
//			cities.put(1,new Point2D(142.0, 150.0)) ;
//			cities.put(2,new Point2D(109.0, 200.0)) ;
//			cities.put(3,new Point2D(102.0, 312.0)) ;
//			cities.put(4,new Point2D(148.0, 285.0)) ;
//			cities.put(5,new Point2D(196.0, 223.0)) ;
//			cities.put(6,new Point2D(250.0, 181.0)) ;
//			cities.put(7,new Point2D(268.0, 245.0)) ;
//			cities.put(8,new Point2D(206.0, 301.0)) ;
//			cities.put(9,new Point2D(201.0, 382.0)) ;
//			cities.put(10,new Point2D(289.0, 314.0)) ;
//			cities.put(11,new Point2D(280.0, 392.0)) ;
//			cities.put(12,new Point2D(370.0, 436.0)) ;
//			cities.put(13,new Point2D(405.0, 377.0)) ;
//			cities.put(14,new Point2D(385.0, 332.0)) ;
//			cities.put(15,new Point2D(376.0, 284.0)) ;
//			cities.put(16,new Point2D(368.0, 232.0)) ;
//			cities.put(17,new Point2D(364.0, 182.0)) ;
//			cities.put(18,new Point2D(438.0, 202.0)) ;
//			cities.put(19,new Point2D(450.0, 273.0)) ;
//			cities.put(20,new Point2D(461.0, 336.0)) ;
//			cities.put(21,new Point2D(464.0, 391.0)) ;
//			cities.put(22,new Point2D(473.0, 453.0)) ;
//			cities.put(23,new Point2D(509.0, 420.0)) ;
//			cities.put(24,new Point2D(549.0, 415.0)) ;
//			cities.put(25,new Point2D(634.0, 483.0)) ;
//			cities.put(26,new Point2D(597.0, 418.0)) ;
//			cities.put(27,new Point2D(633.0, 393.0)) ;
//			cities.put(28,new Point2D(656.0, 359.0)) ;
//			cities.put(29,new Point2D(651.0, 325.0)) ;
//			cities.put(30,new Point2D(619.0, 320.0)) ;
//			cities.put(31,new Point2D(552.0, 367.0)) ;
//			cities.put(32,new Point2D(575.0, 339.0)) ;
//			cities.put(33,new Point2D(508.0, 304.0)) ;
//			cities.put(34,new Point2D(498.0, 232.0)) ;
//			cities.put(35,new Point2D(552.0, 301.0)) ;
//			cities.put(36,new Point2D(561.0, 247.0)) ;
//			cities.put(37,new Point2D(588.0, 295.0)) ;
//			cities.put(38,new Point2D(642.0, 271.0)) ;
//			cities.put(39,new Point2D(681.0, 232.0)) ;
//			cities.put(40,new Point2D(670.0, 180.0)) ;
//			cities.put(41,new Point2D(705.0, 162.0)) ;
//			cities.put(42,new Point2D(741.0, 174.0)) ;
//			cities.put(43,new Point2D(756.0, 220.0)) ;
//			cities.put(44,new Point2D(763.0, 251.0)) ;
//			cities.put(45,new Point2D(747.0, 270.0)) ;
//			cities.put(46,new Point2D(721.0, 283.0)) ;
//			cities.put(47,new Point2D(712.0, 306.0)) ;
//			cities.put(48,new Point2D(721.0, 334.0)) ;
//			cities.put(49,new Point2D(72.0, 480.0)) ;
//			cities.put(50,new Point2D(93.0, 46.0)) ;
//		}
////		private static void fillColors() {
////	    	//ArrayList<Color> colors = new ArrayList<>();
////	    	colors_city.put(Color.valueOf("0xe8f2adff"), 1);
////	    	colors_city.put(Color.valueOf("0xffcdceff"), 2);
////	    	colors_city.put(Color.valueOf("0xfff112ff"), 3);
////	    	colors_city.put(Color.valueOf("0xffdea9ff"), 4);
////	    	colors_city.put(Color.valueOf("0x208380ff"), 5);
////	    	colors_city.put(Color.valueOf("0x833221ff"), 6);
////	    	colors_city.put(Color.valueOf("0xd0c81dff"), 7);
////	    	colors_city.put(Color.valueOf("0x972dcdff"), 8);
////	    	colors_city.put(Color.valueOf("0x09fd46ff"), 9);
////	    	colors_city.put(Color.valueOf("0xf2199cff"), 10);
////	    	colors_city.put(Color.valueOf("0xf3780eff"), 11);
////	    	colors_city.put(Color.valueOf("0x1ac545ff"), 12);
////	    	colors_city.put(Color.valueOf("0xd05febff"), 13);
////	    	colors_city.put(Color.valueOf("0x6082edff"), 14);
////	    	colors_city.put(Color.valueOf("0x2341a1ff"), 15);
////	    	colors_city.put(Color.valueOf("0x0d45f4ff"), 16);
////	    	colors_city.put(Color.valueOf("0xfd3eb2ff"), 17);
////	    	colors_city.put(Color.valueOf("0xc0600aff"), 18);
////	    	colors_city.put(Color.valueOf("0x543904ff"), 19);
////	    	colors_city.put(Color.valueOf("0x864408ff"), 20);
////	    	colors_city.put(Color.valueOf("0xd54113ff"), 21);
////	    	colors_city.put(Color.valueOf("0x790542ff"), 22);
////	    	colors_city.put(Color.valueOf("0xd47eadff"), 23);
////	    	colors_city.put(Color.valueOf("0x62cf8eff"), 24);
////	    	colors_city.put(Color.valueOf("0xe3f1c0ff"), 25);
////	    	colors_city.put(Color.valueOf("0x571516ff"), 26);
////	    	colors_city.put(Color.valueOf("0xf7cb22ff"), 27);
////	    }
//}
