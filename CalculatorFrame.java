package ru.geekbreins;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorFrame extends JFrame {
    public CalculatorFrame() {
        setTitle("Calculator v1.0");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(150, 50, 400, 500);
        setLayout(new BorderLayout());

        final JTextField inputField = new JTextField();
        add(inputField, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(6, 3));
        add(bottomPanel, BorderLayout.CENTER);

        DigitButtonActionListener digitButtonActionListener = new DigitButtonActionListener(inputField);
        for (int i = 0; i <= 9; i++) {
            JButton jButton = new JButton(String.valueOf(i));
            jButton.addActionListener(digitButtonActionListener);
            bottomPanel.add(jButton);
        }


        JButton plus = new JButton("+");
        plus.addActionListener(digitButtonActionListener);
        bottomPanel.add(plus);

        JButton minus = new JButton("-");
        minus.addActionListener(digitButtonActionListener);
        bottomPanel.add(minus);

        JButton multiply = new JButton("*");
        multiply.addActionListener(digitButtonActionListener);
        bottomPanel.add(multiply);

        JButton split = new JButton("/");
        split.addActionListener(digitButtonActionListener);
        bottomPanel.add(split);

        JButton sqrt = new JButton("\u221A");
        ActionListener sgrtListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText("" + Math.sqrt(Double.parseDouble(inputField.getText())));
            }
        };

        sqrt.addActionListener(sgrtListener);
        bottomPanel.add(sqrt);


        JButton point = new JButton(".");
        ActionListener decimalPoint = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText(inputField.getText() + ".");
            }
        };

        point.addActionListener(decimalPoint);
        bottomPanel.add(point);

        JButton clear = new JButton("C");
        ActionListener clearAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputField.setText("");
            }
        };

        clear.addActionListener(clearAction);
        bottomPanel.add(clear);

        JButton calc = new JButton("=");
        ActionListener result = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
                ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("Nashorn");
                try {
                    inputField.setText("" + scriptEngine.eval(inputField.getText()));
                } catch (ScriptException ex) {
                    ex.printStackTrace();
                }
            }
        };

        calc.addActionListener(result);
        bottomPanel.add(calc);

        setVisible(true);
    }
}
