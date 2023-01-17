import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class UnitConverter extends JFrame {
    private JTextField inputField;
    private JLabel inputLabel;
    private JComboBox<String> categoryUnit;
    private JComboBox<String> inputSpecificUnit;
    private JComboBox<String> outputSpecificUnit;
    private JLabel outputLabel;
    private JTextField outputField;
    private JButton convertButton;

    public UnitConverter() {

        DecimalFormat df = new DecimalFormat("#.##");

        // Initialise les composants de l'interface utilisateur
        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(60, 20));
        inputLabel = new JLabel("Input:");
        categoryUnit = new JComboBox<>(new String[] { "Distance", "Temperature", "Weight" });
        inputSpecificUnit = new JComboBox<>(new String[] { "Miles", "Kilometers" });
        outputSpecificUnit = new JComboBox<>(new String[] { "Miles", "Kilometers" });
        inputSpecificUnit.setVisible(false);
        outputSpecificUnit.setVisible(false);
        outputLabel = new JLabel("Output:");
        outputField = new JTextField();
        outputField.setPreferredSize(new Dimension(60, 20));
        convertButton = new JButton("Convert");

        // Ajoute un écouteur d'événements pour la catégorie d'unités
        categoryUnit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryUnit.getSelectedItem();
                if (selectedCategory.equals("Distance")) {
                    inputSpecificUnit.setModel(new DefaultComboBoxModel<>(new String[] { "Miles", "Kilometers" }));
                    outputSpecificUnit.setModel(new DefaultComboBoxModel<>(new String[] { "Miles", "Kilometers" }));
                } else if (selectedCategory.equals("Temperature")) {
                    inputSpecificUnit.setModel(new DefaultComboBoxModel<>(new String[] { "Fahrenheit", "Celsius" }));
                    outputSpecificUnit.setModel(new DefaultComboBoxModel<>(new String[] { "Fahrenheit", "Celsius" }));
                } else if (selectedCategory.equals("Weight")) {
                    inputSpecificUnit.setModel(new DefaultComboBoxModel<>(new String[] { "Pounds", "Kilograms" }));
                    outputSpecificUnit.setModel(new DefaultComboBoxModel<>(new String[] { "Pounds", "Kilograms" }));
                }
                inputSpecificUnit.setVisible(true);
                outputSpecificUnit.setVisible(true);
            }
        });

        // Ajoute un écouteur d'événements au bouton Convertir
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                double inputValue = Double.parseDouble(input);
                String inputUnitString = (String) inputSpecificUnit.getSelectedItem();
                String outputUnitString = (String) outputSpecificUnit.getSelectedItem();
                double outputValue = 0;

                // Convertit les unités en fonction de la catégorie sélectionnée
                String selectedCategory = (String) categoryUnit.getSelectedItem();
                if (selectedCategory.equals("Distance")) {
                    if (inputUnitString.equals("Miles") && outputUnitString.equals("Kilometers")) {
                        outputValue = inputValue * 1.60934;
                    } else if (inputUnitString.equals("Kilometers") && outputUnitString.equals("Miles")) {
                        outputValue = inputValue / 1.60934;
                    } else {
                        outputValue = inputValue;
                    }
                } else if (selectedCategory.equals("Temperature")) {
                    if (inputUnitString.equals("Fahrenheit") && outputUnitString.equals("Celsius")) {
                        outputValue = (inputValue - 32) * (5.0 / 9.0);
                    } else if (inputUnitString.equals("Celsius") && outputUnitString.equals("Fahrenheit")) {
                        outputValue = inputValue * (9.0 / 5.0) + 32;
                    } else {
                        outputValue = inputValue;
                    }
                } else if (selectedCategory.equals("Weight")) {
                    if (inputUnitString.equals("Pounds") && outputUnitString.equals("Kilograms")) {
                        outputValue = inputValue * 0.453592;
                    } else if (inputUnitString.equals("Kilograms") && outputUnitString.equals("Pounds")) {
                        outputValue = inputValue / 0.453592;
                    } else {
                        outputValue = inputValue;
                    }
                }
                String outputValueString = df.format(outputValue);
                outputField.setText(outputValueString);
            }
        });

        // Ajoute les composants à la fenêtre
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(5, 5, 5, 5);
        add(categoryUnit, c);
        c.gridx = 1;
        add(inputLabel, c);
        c.gridx = 2;
        add(inputField, c);
        c.gridx = 3;
        add(inputSpecificUnit, c);
        c.gridx = 1;
        c.gridy = 1;
        add(outputLabel, c);
        c.gridx = 2;
        add(outputField, c);
        c.gridx = 3;
        add(outputSpecificUnit, c);
        c.gridx = 2;
        c.gridy = 2;
        add(convertButton, c);

        // Définit les propriétés de la fenêtre
        setTitle("Unit Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new UnitConverter();
    }
}
