package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class MainMenu extends Application{

//	public static void main(String[] args) {
//		Application.launch(args);
//	}
	//Singleton
//	private static MainMenu mainPage = new MainMenu();
//	private MainMenu() {}
//	public static MainMenu getInstance() {
//		return mainPage;
//	}
	@Override
	public void start(Stage pStage) throws Exception {
		
		VBox box = new VBox();
		Button newGame = new Button("NewGame");newGame.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
		Button exit = new Button("Exit");exit.setFont(Font.font("Comic Sans MS" ,FontPosture.ITALIC , 20));
		box.getChildren().addAll(newGame,exit);
		
		
		//Styles
		newGame.getStylesheets().add("css/Styles2.css");
		exit.getStylesheets().add("css/Styles2.css");
		

		//Actions
		exit.setOnAction(e->{
			pStage.close();
		});
		newGame.setOnAction(e->{
			GameBuild GB = new GameBuild();//GameBuild.getInstance();
			try {
				GB.start(pStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		Pane pane = new Pane();pane.getChildren().addAll(box);
		Image bg = new Image("pics/RISKPhoto1.jpg");
		
		
		pane.setBackground(new Background(new BackgroundImage(bg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, 
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		Scene scene = new Scene(pane,1024,800); //Width , Height
		box.setLayoutX((scene.getWidth()/2)-90);box.setLayoutY(scene.getHeight()/2);
		box.setMargin(newGame, new Insets(120,0,0,0));
		box.setMargin(exit, new Insets(20,0,0,0));
		
		pStage.setScene(scene);
		pStage.setTitle("RISK Demo");
		pStage.show();
		pStage.setResizable(false);
		
		
	}
	
	

}
