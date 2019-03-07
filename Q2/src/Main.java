import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);

        TextField investmentAmountField = new TextField();
        TextField yearsField = new TextField();
        TextField interestRateField = new TextField();

        TextField futureValueField = new TextField();
        futureValueField.setEditable(false); //make sure the user cannot edit this field

        Label investmentAmountLabel = new Label("Investment Amount");
        Label yearsLabel = new Label("Years");
        Label interestRateLabel = new Label("Annual Interest Rate");
        Label futureValueLabel = new Label("Future Value");

        Button calculateButton = new Button("Calculate");

        //calculateButton.setOnAction((e) -> {futureValueField.setText();});

        pane.addColumn(0, investmentAmountLabel, yearsLabel, interestRateLabel, futureValueLabel);
        pane.addColumn(1, investmentAmountField, yearsField, interestRateField, futureValueField, calculateButton);



        stage.setScene(scene);
        stage.show();
    }
}
