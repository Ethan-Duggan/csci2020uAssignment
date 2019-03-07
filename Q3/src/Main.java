import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
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
        Line[] lines = new Line[3];
        for(int i = 0; i < 3; i++){
            lines[i] = new Line();
            pane.getChildren().add(lines[i]);
        }
        Text[] angles = new Text[3];
        for(int i = 0; i < 3; i++){
            points[i] = new Circle(320, 80, 10);
            points[i].setStyle("-fx-stroke: black; -fx-fill: red");
            angles[i] = new Text();
            pane.getChildren().addAll(points[i], angles[i]);
            points[i].setOnMouseDragged((e) -> { //calculate distance from center of circle to mouse pointer
                                                double circleCentreToMouseDistanceX = e.getX()-320;
                                                double circleCentreToMouseDistanceY = e.getY()-320;
                                                double circleCentreToMouseDistance = Math.pow((Math.pow(circleCentreToMouseDistanceX,2) + Math.pow(circleCentreToMouseDistanceY,2)),0.5);
                                                double RADIUS_to_circleCentreToMouseDistance_ratio = RADIUS / circleCentreToMouseDistance;
                                                Circle point = (Circle) e.getSource();
                                                point.setCenterX(320 + RADIUS_to_circleCentreToMouseDistance_ratio*circleCentreToMouseDistanceX);
                                                point.setCenterY(320 + RADIUS_to_circleCentreToMouseDistance_ratio*circleCentreToMouseDistanceY);
                                                setLinesAndAngles(lines, points, angles); });
        }

        stage.setScene(scene);
        stage.show();
    }

    private void setLinesAndAngles(Line[] lines, Circle[] points, Text[] angles){
        int[] angleValues = new int[3];
        double[] lineLengths = new double[3];
        for(int i = 0; i < 3; i++){
            //use Pythagorean's theorem to calculate the lengths of the lines, this must be done first in a separate for loop so that they are all determined by the time they need to be used in the next for loop
            lineLengths[i] = Math.pow((Math.pow(lines[i].getEndX()-lines[i].getStartX(),2) + Math.pow(lines[i].getEndY()-lines[i].getStartY(),2)),0.5);
        }
        for(int i = 0; i < 3; i++){
            //set line start and end points to equal the coordinates of 2 of the points
            lines[i].setStartX(points[i].getCenterX());
            lines[i].setStartY(points[i].getCenterY());
            lines[i].setEndX(points[(i+1)%3].getCenterX());
            lines[i].setEndY(points[(i+1)%3].getCenterY());
            //use acos function to determine the angles between the lines, using the formula given in the Assignment pdf
            angleValues[i] = (int) (Math.acos((Math.pow(lineLengths[(i+1)%3],2)-Math.pow(lineLengths[i],2)-Math.pow(lineLengths[(i+2)%3],2)) / (-2*lineLengths[i]*lineLengths[(i+2)%3]))*180/Math.PI);
            //set the text of the angle Text nodes
            angles[i].setText(Integer.toString(angleValues[i]));
            //set the positions of the angle Text nodes
            angles[i].setX(320 + (points[i].getCenterX()-320)*0.85);
            angles[i].setY(320 + (points[i].getCenterY()-320)*0.85);
        }
    }
}
