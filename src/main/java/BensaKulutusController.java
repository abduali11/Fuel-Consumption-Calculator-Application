import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Locale;
import java.util.ResourceBundle;

public class BensaKulutusController {
    @FXML
    private TextField etäisyys;
    @FXML
    private TextField kulutus;
    @FXML
    private Label tulos;
    @FXML
    private Button calculateButton;
    @FXML
    private Button enButton;
    @FXML
    private Button frButton;
    @FXML
    private Button jpButton;
    @FXML
    private Button irButton;

    @FXML
    private Label distanceLabel;
    @FXML
    private Label consumptionLabel;

    private ResourceBundle bundle;

    public void initialize() {
        // Default language
        loadLanguage(Locale.ENGLISH);
        // You can also use Locale.English easily and simply
        enButton.setOnAction(event -> loadLanguage(Locale.ENGLISH));
        frButton.setOnAction(event -> loadLanguage(Locale.FRENCH));
        jpButton.setOnAction(event -> loadLanguage(Locale.JAPANESE));
        irButton.setOnAction(event -> loadLanguage(new Locale("fa", "IR")));

        // Add event handler for calculate button
        calculateButton.setOnAction(event -> calculateFuelConsumption());
    }

    private void loadLanguage(Locale locale) {
        bundle = ResourceBundle.getBundle("message", locale);

        // Set text for UI elements
        distanceLabel.setText(bundle.getString("distance"));
        consumptionLabel.setText(bundle.getString("consumption"));
        calculateButton.setText(bundle.getString("calculate"));
        enButton.setText("EN");
        frButton.setText("FR");
        jpButton.setText("JP");
        irButton.setText("IR");

        tulos.setText("");
    }

    private void calculateFuelConsumption() {
        try {
            double distance = Double.parseDouble(etäisyys.getText());
            double fuel = Double.parseDouble(kulutus.getText());

            // Calculate fuel consumption in L/100km
            double consumption = (fuel / distance) * 100;

            // Calculate cost, assuming 1.77 per liter
            double cost = fuel * 1.77;

            // display result
            String result = bundle.getString("result");
            tulos.setText(String.format(result, consumption, cost));
        } catch (NumberFormatException e) {
            tulos.setText(bundle.getString("error_invalid_input"));
        }
    }
}
