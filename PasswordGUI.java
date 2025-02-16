import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordGUI extends JFrame {
    private JTextField passwordField;
    private JLabel strengthLabel;
    private JSpinner lengthSpinner;
    private JCheckBox upperCheckBox, lowerCheckBox, digitCheckBox, symbolCheckBox;

    public PasswordGUI() {
        setTitle("Password Generator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        passwordField = new JTextField();
        passwordField.setEditable(false);
        add(passwordField);

        strengthLabel = new JLabel("Strength: ");
        add(strengthLabel);

        lengthSpinner = new JSpinner(new SpinnerNumberModel(12, 6, 32, 1));
        add(new JLabel("Password Length:"));
        add(lengthSpinner);

        JPanel checkBoxPanel = new JPanel();
        upperCheckBox = new JCheckBox("Uppercase", true);
        lowerCheckBox = new JCheckBox("Lowercase", true);
        digitCheckBox = new JCheckBox("Numbers", true);
        symbolCheckBox = new JCheckBox("Symbols", true);
        checkBoxPanel.add(upperCheckBox);
        checkBoxPanel.add(lowerCheckBox);
        checkBoxPanel.add(digitCheckBox);
        checkBoxPanel.add(symbolCheckBox);
        add(checkBoxPanel);

        JButton generateButton = new JButton("Generate Password");
        generateButton.addActionListener(new GenerateButtonListener());
        add(generateButton);
    }

    private String generatePassword(int length, boolean hasUpper, boolean hasLower, boolean hasDigit, boolean hasSymbol) {
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String symbols = "!@#$%^&*()-_=+[]{}|;:'\",.<>?/";

        StringBuilder password = new StringBuilder();
        StringBuilder charPool = new StringBuilder();

        if (hasUpper) charPool.append(upperCase);
        if (hasLower) charPool.append(lowerCase);
        if (hasDigit) charPool.append(digits);
        if (hasSymbol) charPool.append(symbols);

        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * charPool.length());
            password.append(charPool.charAt(randomIndex));
        }

        return password.toString();
    }

    private class GenerateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int length = (int) lengthSpinner.getValue();
            boolean hasUpper = upperCheckBox.isSelected();
            boolean hasLower = lowerCheckBox.isSelected();
            boolean hasDigit = digitCheckBox.isSelected();
            boolean hasSymbol = symbolCheckBox.isSelected();

            Password password = new Password(generatePassword(length, hasUpper, hasLower, hasDigit, hasSymbol));
            passwordField.setText(password.toString());
            strengthLabel.setText("Strength: " + password.determineScore());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PasswordGUI gui = new PasswordGUI();
            gui.setVisible(true);
        });
    }
}

