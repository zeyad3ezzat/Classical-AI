package gui;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlaceTroops extends Application{
//	public static void main(String[] args) {
//		Application.launch(args);
//	}
	private int noTroops ,  maxNumber ;
	private StringProperty tr = new SimpleStringProperty(""+noTroops);
	private IntegerProperty trs = new SimpleIntegerProperty();
	public PlaceTroops(int MaxNoTroops) {
//		this.noTroops = trooops;
		maxNumber = MaxNoTroops;
	}

	@Override
	public void start(Stage pStage) throws Exception {
		
		Button minus = new Button("<"); minus.getStylesheets().add("css/Red_Buttons.css");
		Label value = new Label(""+noTroops);value.getStylesheets().add("css/Red_Buttons.css");
		Button plus = new Button(">");plus.getStylesheets().add("css/Red_Buttons.css");
		Button max = new Button("Max");max.getStylesheets().add("css/Red_Buttons.css");
		
		value.textProperty().bind(tr);
		HBox valueChooser = new HBox();
		valueChooser.getChildren().addAll(minus,value,plus,max);
		valueChooser.setPrefWidth(300);valueChooser.setAlignment(Pos.CENTER);
		
		Button confirm = new Button("Confirm");confirm.getStylesheets().add("css/Red_Buttons.css");
		VBox pane = new VBox();pane.setAlignment(Pos.CENTER);
		pane.setMargin(valueChooser, new Insets(50,0,20,0));
		pane.getChildren().addAll(valueChooser,confirm);
		Image bg = new Image("pics/troopsPlacing.jpg");
		
		pane.setBackground(new Background(new BackgroundImage(bg, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, 
				BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		//Actions
		minus.setOnAction(e->{
			if(noTroops > 0)noTroops--;
			tr.setValue(""+noTroops);
		});
		plus.setOnAction(e->{
			if(noTroops<maxNumber)noTroops++;
			tr.setValue(""+noTroops);
		});
		max.setOnAction(e->{
			tr.setValue(""+maxNumber);
			noTroops = maxNumber;
		});
		confirm.setOnAction(e->{
			//Pass the new value to be set on the MAP
			trs.setValue(noTroops);
			
			//close the window
			pStage.close();
		});
		Scene scene = new Scene(pane,300,200);
		pStage.setScene(scene);
		pStage.setTitle("PlacingTroops");
		pStage.setResizable(false);
		pStage.show();
	}

	public int getNoTroops() {
		return noTroops;
	}

	public void setNoTroops(int noTroops) {
		this.noTroops = noTroops;
	}

	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
		noTroops = maxNumber;
	}

	public IntegerProperty getTrs() {
		return trs;
	}
	

}
