import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.lang.Math;

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

        calculateButton.setOnAction((e) -> {double investmentAmount = Double.parseDouble(investmentAmountField.getText());
                                            int months = Integer.parseInt(yearsField.getText()) * 12;
                                            double monthlyInterestRate = Double.parseDouble(interestRateField.getText()) / 1200; //1200 comes from needing to divide by 100 to convert from percentage plus needing to divide by 12 to convert to monthly
                                            double futureValue = investmentAmount * Math.pow((1 + monthlyInterestRate), months);
                                            futureValueField.setText(Double.toString(futureValue));});

        pane.addColumn(0, investmentAmountLabel, yearsLabel, interestRateLabel, futureValueLabel);
        pane.addColumn(1, investmentAmountField, yearsField, interestRateField, futureValueField, calculateButton);

        stage.setScene(scene);
        stage.show();
    }
}
