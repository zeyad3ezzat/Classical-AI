//package gui;
//
//import javafx.application.Application;
//
//
//import javafx.beans.InvalidationListener;
//import javafx.beans.Observable;
//import javafx.beans.property.DoubleProperty;
//import javafx.beans.property.SimpleDoubleProperty;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseButton;
//import javafx.scene.input.ScrollEvent;
//import javafx.stage.Stage;
//
//
///**
// * 
// * @author O.J. Sousa Rodrigues (office at halbgasse.at)
// */
//public class Test2 extends Application {
//
//    private ImageView imageView = new ImageView();
//    private ScrollPane scrollPane = new ScrollPane();
//    final DoubleProperty zoomProperty = new SimpleDoubleProperty(200);
//
//    @Override
//    public void start(Stage stage) throws Exception {
//
//        zoomProperty.addListener(new InvalidationListener() {
//            @Override
//            public void invalidated(Observable arg0) {
//                imageView.setFitWidth(zoomProperty.get() * 4);
//                imageView.setFitHeight(zoomProperty.get() * 3);
//            }
//        });
//
//        scrollPane.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
//            @Override
//            public void handle(ScrollEvent event) {
//                if (event.getDeltaY() > 0) {
//                    zoomProperty.set(zoomProperty.get() * 1.1);
//                } else if (event.getDeltaY() < 0) {
//                    zoomProperty.set(zoomProperty.get() / 1.1);
//                }
//            }
//        });
//        imageView.setOnMouseClicked(e->{
//        	if (e.getButton() == MouseButton.PRIMARY) {
//        		
//                zoomProperty.set(zoomProperty.get() * 1.1);
//            } else if (e.getButton() == MouseButton.SECONDARY) {
//            	
//                zoomProperty.set(zoomProperty.get() / 1.1);
//            }
//        	
//        });
//
//        imageView.setImage(new Image("pics/EgyptMap.jpg"));
//        imageView.preserveRatioProperty().set(true);
//        scrollPane.setContent(imageView);
//        
//        stage.setScene(new Scene(scrollPane, 400, 300));
//        stage.show();
//
//    }
//
//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//   