import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.lang.Math;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 640, 640);

        final int RADIUS = 240; //radius of the main circle

        Circle circle = new Circle(320,320, RADIUS);
        circle.setStyle("-fx-stroke: black; -fx-fill: white");
        pane.getChildren().add(circle);

        Circle[] points = new Circle[3];
        for(int i = 0; i < 3; i++){
            points[i] = new Circle(320, 80, 10);
            points[i].setStyle("-fx-stroke: black; -fx-fill: red");
            points[i].setOnMouseDragged((e) -> { //calculate distance from center of circle to mouse pointer
                                            double circleCentreToMouseDistanceX = e.getX()-320;
                                            double circleCentreToMouseDistanceY = e.getY()-320;
                                            double circleCentreToMouseDistance = Math.pow((Math.pow(circleCentreToMouseDistanceX,2) + Math.pow(circleCentreToMouseDistanceY,2)),0.5);
                                            double RADIUS_to_circleCentreToMouseDistance_ratio = RADIUS / circleCentreToMouseDistance;
                                            Circle point = (Circle) e.getSource();
                                            point.setCenterX(320 + RADIUS_to_circleCentreToMouseDistance_ratio*circleCentreToMouseDistanceX);
                                            point.setCenterY(320 + RADIUS_to_circleCentreToMouseDistance_ratio*circleCentreToMouseDistanceY); });

            pane.getChildren().add(points[i]);
        }


        stage.setScene(scene);
        stage.show();
    }
}
