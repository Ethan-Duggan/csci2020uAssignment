import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        int[] letterCounts = new int[26]; //used to store number of each letter found in file.

        BorderPane masterPane = new BorderPane();
        Scene scene = new Scene(masterPane);

        Pane histogramPane = new Pane();
        masterPane.setCenter(histogramPane);
        histogramPane.setMinWidth(418);
        histogramPane.setMinHeight(270);

        FlowPane UIPane = new FlowPane();
        masterPane.setBottom(UIPane);

        //add nodes for histogram
        Text[] histogramLetters = new Text[26];
        Rectangle[] histogramBars = new Rectangle[26];
        for(int i = 0; i < 26; i++){
            histogramLetters[i] = new Text();
            histogramLetters[i].setText(Character.toString((char) (65 + i))); //set the text for each element of histogramLetters to the appropriate capital letter
            histogramBars[i] = new Rectangle();
            histogramPane.getChildren().addAll(histogramLetters[i],histogramBars[i]);
        }
        Line histogramBaseLine = new Line();
        histogramPane.getChildren().add(histogramBaseLine);

        //add nodes for UI pane
        Label label = new Label("Filename");
        TextField fileName = new TextField();
        Button viewButton = new Button("View");
        UIPane.getChildren().addAll(label, fileName, viewButton);

        //set event handler for viewButton
        viewButton.setOnAction((e)->{
            File file = new File(fileName.getText());
            //following code catches the exception if the file does not exist
            Scanner input = null;
            try {
                input = new Scanner(file);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            //reset letterCounts elements to zero
            for(int i = 0; i < 26; i++){letterCounts[i] = 0;}
            //read the file and count letters
            input.useDelimiter(""); //causes input.next() to read one character at a time
            while(input.hasNext()){
                char c = input.next().charAt(0);
                if(c >= 'a' && c <= 'z'){
                    letterCounts[c-97]++;
                } else if(c >= 'A' && c <= 'Z'){
                    letterCounts[c-65]++;
                }
            }
            //find largest letter count, the histogramBar representing the largest letterCount will reach to the top and all others will be to scale
            int largestCount = 0;
            for(int i = 0; i < 26; i++){
                if(letterCounts[i] > largestCount) largestCount = letterCounts[i];
            }
            //set histogram bar heights
            for(int i = 0; i < 26; i++){
                double barHeight = 240*((double)letterCounts[i]/largestCount);//((double)letterCounts[i]/largestCount) is the ratio of the current letterCount to the largest, must be cast as double to avoid integer division, 240 is the height of the highest histogramBar
                histogramBars[i].setHeight(barHeight);
                histogramBars[i].setY(250-barHeight);//250 is the height of the histogramBaseLine
            }
        });

        fileName.setOnKeyPressed((e)->{
            if(e.getCode() == KeyCode.ENTER){
                viewButton.fire();
            }
        });

        //set scene and show stage
        stage.setScene(scene);
        stage.show();

        //set node positions based on size of panes/scene
        for(int i = 0; i < 26; i++){
            double xpos = histogramPane.getWidth()/2 + (-208 + i*16); //determine the x-coordinates of the histogram bars and letters
            histogramLetters[i].setX(xpos+3); //the +3 makes the letters look more centered under the bars
            histogramLetters[i].setY(262);
            histogramBars[i].setX(xpos);
            histogramBars[i].setY(250);
            histogramBars[i].setWidth(16);
            histogramBars[i].setHeight(0);
            histogramBars[i].setStyle("-fx-stroke:black; -fx-fill:white");
        }
        histogramBaseLine.setStartX(0); histogramBaseLine.setStartY(250); histogramBaseLine.setEndX(histogramPane.getWidth()); histogramBaseLine.setEndY(250);
        fileName.setMinWidth(418 - label.getWidth() - viewButton.getWidth());
    }
}
