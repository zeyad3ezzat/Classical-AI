package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.TestGame;

public class GameOver extends Application{
private static int winner = 1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage pStage) throws Exception {
		ImageView winImage = new ImageView();
		Label winName = new Label("");winName.getStylesheets().add("css/TextStyles.css");
		Button newGame = new Button("New Game");newGame.getStylesheets().add("css/Styles2.css");
		Button exit = new Button("Exit");exit.getStylesheets().add("css/Styles2.css");
		
		VBox box = new VBox();
		Pane pane = new Pane();//pane.setAlignment(Pos.CENTER);
		box.getChildren().addAll(newGame,exit);
		
		if(winner == 1)//Green 
		{
			winImage.setImage(new Image("pics/GreenVictory.gif"));
			winName.setText("Winner is Green!");
			winName.setStyle("-fx-text-fill:GREEN;-fx-font-size:25px");
		}
		else if (winner == 2) //RED
			{
			winImage.setImage(new Image("pics/Redvictory.gif"));
			winName.setText("Winner is Red!");
			winName.setStyle("-fx-text-fill:RED;-fx-font-size:25px");
			
			}
		newGame.setOnAction(e->{
			
			TestGame game = new TestGame();
			try {
				game.start(pStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		exit.setOnAction(e->{
			pStage.close();
		});
		
		pane.getChildren().addAll(winImage,winName,box);
		box.setMargin(newGame, new Insets(380,0,15,15));box.setMargin(exit, new Insets(0,0,0,15));
		winName.setLayoutX(150);winName.setLayoutY(10);
		Scene scene = new Scene(pane,490,490);
		
		
		pStage.setScene(scene);
		pStage.setTitle("PlacingTroops");
		pStage.setResizable(false);
		pStage.show();
		pStage.centerOnScreen();
	}
	public static void setWinner(int win) {
		winner = win;
	}
	

}
