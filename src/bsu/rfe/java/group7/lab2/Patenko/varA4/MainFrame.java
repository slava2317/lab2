package bsu.rfe.java.group7.lab2.Patenko.varA4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    // Размеры окна приложения в виде констант
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;
    // Текстовые поля для считывания значений переменных,
// как компоненты, совместно используемые в различных методах
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    // Текстовое поле для отображения результата,
// как компонент, совместно используемый в различных методах
    private JTextField textFieldResult;
    private JTextField textFieldMemory;
    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();
    // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;
    private double sum;
    // Формула №1 для рассчѐта
    public Double calculate1(Double x, Double y, Double z) {
        return Math.sqrt(Math.pow((Math.sin(y) + Math.pow(y,2) + Math.pow(Math.E,Math.cos(y))),2) + Math.pow(Math.log(Math.pow(z,2) + Math.sin(Math.sin(Math.PI * Math.pow(x,2)))),3));
    }
    // Формула №2 для рассчѐта
    public Double calculate2(Double x, Double y, Double z) {
        return Math.sqrt(y) * (3 * Math.pow(z,x))/ Math.sqrt(1 + Math.pow(y,3));
    }
    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }
    // Конструктор класса
    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
// Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));

// Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
//labelResult = new JLabel("0");
        textFieldResult = new JTextField("0", 20);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldX.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(
                BorderFactory.createLineBorder(Color.RED));
        //hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        //hboxVariables.add(Box.createHorizontalGlue());

        // Создать область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Double x = Double.parseDouble(textFieldX.getText());
                Double y = Double.parseDouble(textFieldY.getText());
                Double z = Double.parseDouble(textFieldZ.getText());
                Double result;
                try {
                    if (formulaId==1)
                        result = calculate1(x, y, z) + sum;
                    else
                        result = calculate2(x, y, z) + sum;
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
                textFieldMemory.setText("0");
            }
        });
        JButton buttonMSum = new JButton("M+");
        buttonMSum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Double x = Double.parseDouble(textFieldX.getText());
                Double y = Double.parseDouble(textFieldY.getText());
                Double z = Double.parseDouble(textFieldZ.getText());
                Double res;
                try {
                    if (formulaId==1) {
                        sum += calculate1(x, y, z);
                        res = sum;
                    }
                    else {
                        sum += calculate2(x, y, z);
                        res = sum;
                    }
                    textFieldMemory.setText(res.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sum = 0;
                textFieldMemory.setText("0");
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        //hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        //hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(Box.createHorizontalStrut(10));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(Box.createHorizontalStrut(10));
        hboxButtons.add(buttonMSum);
        //hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(Box.createHorizontalStrut(10));
        hboxButtons.add(buttonMC);
        //hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));


        //Область для лэйбла memory
        JLabel labelForMemory = new JLabel("Memory:");
        textFieldMemory = new JTextField("0", 20);
        textFieldMemory.setMaximumSize(
                textFieldMemory.getPreferredSize());
        Box hboxMemory = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForMemory);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldMemory);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
// Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxMemory);
        contentBox.add(hboxButtons);
        contentBox.add(hboxFormulaType);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
}