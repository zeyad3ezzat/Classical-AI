package gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestGUI extends Application{

	public static void main(String[] args) {
		Application.launch(args);
		System.out.println("ASKJDHAKSJDHAKSJD");
	}

	@Override
	public void start(Stage pStage) throws Exception {
//		MainMenu main = new MainMenu();//MainMenu.getInstance();
//		main.start(pStage);
		GameBuild GB = new GameBuild();
		GB.start(pStage);

	}

}
