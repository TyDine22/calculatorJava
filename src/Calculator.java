import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Calculator extends JFrame {
    private JTextField firstNumberField;
    private JTextField secondNumberField;
    private JTextField resultField;
    private JButton addBtn;
    private JButton subBtn;
    private JButton mulBtn;
    private JButton divBtn;
    private JButton equalsBtn;
    private JButton clearBtn;
    private String selectedOperation;

    public Calculator() {
        JLabel firstNumberLabel = new JLabel("First number: ");
        JLabel secondNumberLabel = new JLabel("Second number: ");
        JLabel resultLabel = new JLabel("Result: ");

        firstNumberField = new JTextField(10);
        secondNumberField = new JTextField(10);
        resultField = new JTextField(10);
        resultField.setEditable(false);

        addBtn = new JButton("+");
        subBtn = new JButton("-");
        mulBtn = new JButton("*");
        divBtn = new JButton("/");
        equalsBtn = new JButton("=");
        clearBtn = new JButton("Clear");

        addBtn.addActionListener(new Operation());
        subBtn.addActionListener(new Operation());
        mulBtn.addActionListener(new Operation());
        divBtn.addActionListener(new Operation());
        equalsBtn.addActionListener(new EqualAction());
        clearBtn.addActionListener(new ClearAction());

        setLayout(new GridLayout(6, 2, 4, 4));
        add(firstNumberLabel);
        add(firstNumberField);
        add(secondNumberLabel);
        add(secondNumberField);
        add(resultLabel);
        add(resultField);
        add(addBtn);
        add(subBtn);
        add(mulBtn);
        add(divBtn);
        add(equalsBtn);
        add(clearBtn);

        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
}

    private class Operation implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            selectedOperation = source.getText();
        }
    }

    private class EqualAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double firstNumber = Double.parseDouble(firstNumberField.getText());
                double secondNumber = Double.parseDouble(secondNumberField.getText());
                double result = 0;

                switch (selectedOperation) {
                    case "+":
                        result = firstNumber + secondNumber;
                        break;
                    case "-":
                        result = firstNumber - secondNumber;
                        break;
                    case "*":
                        result = firstNumber * secondNumber;
                        break;
                    case "/":
                        if (secondNumber != 0) {
                            result = firstNumber / secondNumber;
                        } else {
                            resultField.setText("Error: Division by zero");
                            return;
                        }
                        break;
                    default:
                        resultField.setText("Error: No operation selected");
                        return;
                }

                resultField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                resultField.setText("Error: Invalid input");
            }
        }
    }

    private class ClearAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            firstNumberField.setText("");
            secondNumberField.setText("");
            resultField.setText("");
            selectedOperation = null;
        }
    }}
