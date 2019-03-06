import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        HBox pane = new HBox();
        Scene scene = new Scene(pane,216,96);

        Random rand = new Random();
        int[] cardNums = new int[3];
        for(int i = 0; i < 3; i++){
            cardNums[i] = rand.nextInt(52) + 1;
            //this for loop ensures that all 3 cards are distinct
            for(int j = 0; j < i; j++){
                if(cardNums[i] == cardNums[j]){
                    cardNums[i] = ((cardNums[i] + 1) % 52) + 1;
                }
            }
        }

        Image[] cardImages = new Image[3];
        for(int i = 0; i < 3; i++){
            cardImages[i] = new Image(String.format("cards/%d.png", cardNums[i]));
        }


        ImageView[] cards = new ImageView[3];
        for(int i = 0; i < 3; i++){
            cards[i] = new ImageView(cardImages[i]);
            pane.getChildren().add(cards[i]);
        }


        stage.setScene(scene);
        stage.show();
    }
}
