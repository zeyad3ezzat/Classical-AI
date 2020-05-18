package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.TestGame;

public class GameBuild extends Application{
//	public static void main(String[] args) {
//		Application.launch(args);
//	}
	//Singleton
//		private static GameBuild buildPage = new GameBuild();
//		private GameBuild() {}
//		public static GameBuild getInstance() {
//			return buildPage;
//		}
	private static boolean setBotDesc = false , EveryThingIsOk = false;
	private int map = 1;
	@Override
	public void start(Stage pStage) throws Exception {
		
		Label simulationMode = new Label("Simulation Mode:");simulationMode.getStylesheets().add("css/Styles2.css");simulationMode.setPrefSize(250, 60);
		Label player1 = new Label("Player 1");player1.getStylesheets().add("css/TextStyles.css");
		Label player2 = new Label("Player2");player2.getStylesheets().add("css/TextStyles.css");
		Label selectPlayer1 = new Label("SelectPlayer1 Type");selectPlayer1.getStylesheets().add("css/TextStyles.css");
		Label selectPlayer2 = new Label("SelectPlayer2 Type");selectPlayer2.getStylesheets().add("css/TextStyles.css");
		Label botDesc = new Label("");botDesc.getStylesheets().add("css/Attacked.css");botDesc.setTextAlignment(TextAlignment.LEFT);botDesc.setMaxWidth(Region.USE_PREF_SIZE);
		botDesc.setWrapText(true);
		
		Button StartGame = new Button("Start Game");StartGame.getStylesheets().add("css/Styles2.css");
		Button back = new Button("BACK");back.getStylesheets().add("css/Styles2.css");
		
//		Label AIbotDesc = new Label("");AIbotDesc.getStylesheets().add("css/TextStyles.css");AIbotDesc.setTextAlignment(TextAlignment.LEFT);AIbotDesc.setMaxWidth(Region.USE_PREF_SIZE);
//		AIbotDesc.setWrapText(true);
		//Radio Buttons
		//1- For player 1 ( Human or Systematic player)
		ToggleGroup tg = new ToggleGroup();
		RadioButton human = new RadioButton("Human");human.getStylesheets().add("css/TextStyles.css");
		RadioButton sysPlayer = new RadioButton("Systematic Bot");sysPlayer.getStylesheets().add("css/TextStyles.css");
		sysPlayer.setToggleGroup(tg);
		human.setToggleGroup(tg);
		
		ObservableList<String> types1 = 
			    FXCollections.observableArrayList(
			        "Passive bot",
			        "Medium bot",
			        "Aggressive bot"
			    );
		ComboBox<String> player1_Type = new ComboBox<String>(types1);player1_Type.getStylesheets().add("css/TextStyles.css");
		player1_Type.setPromptText("Please select bot type");
		//2- For AI types
		ObservableList<String> types2 = 
			    FXCollections.observableArrayList(
			        "Greedy AI bot",
			        "A* AI bot",
			        "Real-time A* bot",
			        "Minimax AI bot"
			    );
		ComboBox<String> player2_Type = new ComboBox<String>(types2);player2_Type.getStylesheets().add("css/TextStyles.css");
		player2_Type.setPromptText("Please select AI type");
		//For Map choosing
		ObservableList<String> maps = 
			    FXCollections.observableArrayList(
			        "Egypt",
			        "United states"
			    );
		ComboBox<String> MAPs = new ComboBox<String>(maps);MAPs.getStylesheets().add("css/TextStyles.css");
		//MAPs.getSelectionModel().selectFirst();
		MAPs.setPromptText("Please select MAP");
		
		VBox leftBox = new VBox();leftBox.getChildren().addAll(player1,selectPlayer1,human,sysPlayer);
		leftBox.setAlignment(Pos.TOP_CENTER);
		VBox rightBox = new VBox();rightBox.getChildren().addAll(player2,selectPlayer2,player2_Type,MAPs);
		rightBox.setAlignment(Pos.TOP_CENTER);
		leftBox.setBorder(new Border(new BorderStroke(Color.GREY, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		rightBox.setBorder(new Border(new BorderStroke(Color.GREY, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		leftBox.setPrefSize(400, 300);
		rightBox.setPrefSize(400, 300);
		
		HBox bigBox = new HBox();
		bigBox.getChildren().addAll(leftBox,rightBox);bigBox.setPrefSize(800, 600);
//		bigBox.setStyle("-fx-background-color:#c9cace");
		

		Pane pane = new Pane();pane.getChildren().addAll(simulationMode,bigBox,StartGame,back);
		Image bg = new Image("pics/RISKPhoto1.jpg");
		
		pane.setBackground(new Background(new BackgroundImage(bg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, 
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		
		Scene scene = new Scene(pane,1024,800); //Width , Height
		
		//Actions
		player1_Type.setOnAction(e->{
			if(!setBotDesc)
			leftBox.getChildren().add(botDesc);
			
			setBotDesc = true;
			if(player1_Type.getValue().toString().equalsIgnoreCase("Passive bot")) {
				botDesc.setText("Bot Description: A completely passive agent, that places all of its bonus armies to the territory with\r\n" + 
						"the fewest armies, and doesn't make any attacks.");
			}
			else if(player1_Type.getValue().toString().equalsIgnoreCase("Medium bot")) {
				botDesc.setText("Bot Description: A nearly pacifst agent, that places its armies like the completely passive agent, then\r\n" + 
						"conquers only the one territory with fewest armies (if it can).");
			}
			else if(player1_Type.getValue().toString().equalsIgnoreCase("Aggressive bot")) {
				botDesc.setText("Bot Description: An aggressive agent, that always places all its bonus armies on the territory with\r\n" + 
						"the most armies, and greedily attempts to attack territories with most armies that\r\n" + 
						"he can attack.");
			}
			else leftBox.getChildren().remove(botDesc);
		});
		sysPlayer.setOnAction(e->{
			
			if(sysPlayer.isSelected()) {
				leftBox.getChildren().add(player1_Type);
			}
			else 	leftBox.getChildren().remove(player1_Type);
		});
		human.setOnAction(e->{
			
			if(human.isSelected())	{
				leftBox.getChildren().remove(player1_Type);
				if(setBotDesc) 	{
					setBotDesc = false;
					leftBox.getChildren().remove(botDesc);
				}
			}
		});
		
		StartGame.setOnAction(e->{
			if((human.isSelected() || !player1_Type.getSelectionModel().isEmpty()) && !player2_Type.getSelectionModel().isEmpty() && !MAPs.getSelectionModel().isEmpty())
				{
				EveryThingIsOk = true;
				
				}
			else {
				EveryThingIsOk = false;
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
//				alert.setHeaderText("Select All ");
				alert.setContentText("Please select all fields!");
				alert.showAndWait();
			}
			if(EveryThingIsOk) {
			String player1Type = "";
			if(human.isSelected())player1Type = "Human";
			else if(sysPlayer.isSelected()) {
				player1Type = player1_Type.getValue().toString();
			}
			String player2Type = player2_Type.getValue().toString();
			if(!MAPs.getSelectionModel().isEmpty())
			map = MAPs.getValue().equalsIgnoreCase("Egypt")?1:2 ;
		
			GamePlay GP = new GamePlay();
			GP.resetScores();
			GP.setMapChosen(map);
			GP.setPlayer1_type(player1Type);GP.setPlayer2_type(player2Type);
			
			
			try {
				
				GP.setStage(pStage);GP.start(GP.getStage());
				TestGame.buttons = GP.getButtons();
				TestGame.StartGame(map , player1Type , player2Type);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		
		back.setOnAction(e->{
			MainMenu mu = new MainMenu();//MainMenu.getInstance();
			try {
				mu.start(pStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		//Positions
		StartGame.setPrefSize((scene.getWidth()/4), scene.getHeight()/14);
		simulationMode.setLayoutX((scene.getWidth()/8)-14);simulationMode.setLayoutY(scene.getHeight()/4);
		bigBox.setLayoutX((scene.getWidth()/9));bigBox.setLayoutY(scene.getHeight()/3);
		leftBox.setMargin(player1, new Insets(15));rightBox.setMargin(player2, new Insets(15));
		leftBox.setMargin(human, new Insets(15));rightBox.setMargin(player2_Type, new Insets(15));
		leftBox.setMargin(player1_Type, new Insets(15));rightBox.setMargin(MAPs, new Insets(100,0,0,0));
		StartGame.setLayoutX(((scene.getWidth()/2))-StartGame.getPrefWidth()/2);StartGame.setLayoutY((scene.getHeight()-120));
		back.setLayoutX(((scene.getWidth()/6))-back.getPrefWidth()/2);back.setLayoutY((scene.getHeight()-120));
		
		pStage.setScene(scene);
		pStage.setTitle("RISK Demo");
		pStage.show();
		pStage.setResizable(false);
		pStage.centerOnScreen();
		
	}
	public int getMap() {
		return map;
	}
	

}
