import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoanCalculatorApp extends Application {

    @Override
    public void start(Stage primaryStage) {

       /** Created the UI element where it ask the user the the loan amount*/

        Label lblLoanAmount = new Label("Loan Amount:");
        TextField tfLoanAmount = new TextField();

        /* Ask the user for the annual interest rate*/

        Label lblAnnualRate = new Label("Annual Interest Rate:");
        TextField tfAnnualRate = new TextField();

        Label lblYears = new Label("Number of Years:");
        TextField tfYears = new TextField();

        /* New button is created to compute the payment*/

        Button btnCompute = new Button("Compute Payment");

        Label lblMonthlyPayment = new Label("Monthly Payment:");
        TextField tfMonthlyPayment = new TextField();
        tfMonthlyPayment.setEditable(false);

        Label lblTotalPayment = new Label("Total Payment:");
        TextField tfTotalPayment = new TextField();
        tfTotalPayment.setEditable(false);

        /* Layout is set up for the boxes to work and show */

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(lblLoanAmount, 0, 0);
        grid.add(tfLoanAmount, 1, 0);
        grid.add(lblAnnualRate, 0, 1);
        grid.add(tfAnnualRate, 1, 1);
        grid.add(lblYears, 0, 2);
        grid.add(tfYears, 1, 2);
        grid.add(btnCompute, 1, 3);
        grid.add(lblMonthlyPayment, 0, 4);
        grid.add(tfMonthlyPayment, 1, 4);
        grid.add(lblTotalPayment, 0, 5);
        grid.add(tfTotalPayment, 1, 5);


        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setTitle("Loan Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();


        btnCompute.setOnAction(e -> {
            try {
                double loanAmount = Double.parseDouble(tfLoanAmount.getText());
                double annualRate = Double.parseDouble(tfAnnualRate.getText());
                int years = Integer.parseInt(tfYears.getText());

                double monthlyRate = annualRate / 1200;
                int months = years * 12;

                double monthlyPayment = (loanAmount * monthlyRate) /
                        (1 - Math.pow(1 + monthlyRate, -months));
                double totalPayment = monthlyPayment * months;
/*If the input does not meet the standards then then would result in an error message */

                tfMonthlyPayment.setText(String.format("%.2f", monthlyPayment));
                tfTotalPayment.setText(String.format("%.2f", totalPayment));
            } catch (NumberFormatException ex) {
                tfMonthlyPayment.setText("Error");
                tfTotalPayment.setText("Error");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
