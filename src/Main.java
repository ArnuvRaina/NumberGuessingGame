import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

class MyFrame extends JFrame implements ActionListener {
    private JTextField inputField;
    private JButton checkButton, playAgainButton, exitButton, setRangeButton;
    private JLabel labelFinal, labelCount, label2;
    private int count, maxRange = 6;
    private int ran = RandomGenerator.random(maxRange);

    public MyFrame() {
        this.setSize(640, 480);
        this.setTitle("Number Game");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon image = new ImageIcon("C:\\Users\\KIIT\\IdeaProjects\\Internship\\src\\dice.png");
        this.setIconImage(image.getImage());

        this.getContentPane().setBackground(new Color(255, 152, 152));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panel.setBackground(new Color(41, 229, 215));

        JLabel label1 = new JLabel("Welcome to Number Guessing Game!");
        label1.setFont(new Font("SF Pro Display", Font.BOLD, 28));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panel.add(label1, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(240, 115, 115));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 10, 30));
        inputPanel.setLayout(new FlowLayout());

        label2 = new JLabel("Enter Your Guess between 1 to " + maxRange + ": ");
        label2.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
        inputPanel.add(label2);

        inputField = new JTextField(10);
        inputPanel.add(inputField);

        panel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(new Color(255, 152, 152));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JPanel buttonInnerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonInnerPanel.setBackground(new Color(255, 152, 152, 202));

        checkButton = new JButton("Roll!");
        checkButton.addActionListener(this);
        buttonInnerPanel.add(checkButton);

        playAgainButton = new JButton("Play Again?");
        playAgainButton.addActionListener(this);
        playAgainButton.setVisible(false);
        buttonInnerPanel.add(playAgainButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);
        buttonInnerPanel.add(exitButton);

        setRangeButton = new JButton("Set Range");
        setRangeButton.addActionListener(this);
        buttonInnerPanel.add(setRangeButton);

        buttonPanel.add(buttonInnerPanel, BorderLayout.CENTER);

        labelCount = new JLabel();
        labelCount.setFont(new Font("SF Pro Display", Font.BOLD, 15));
        labelCount.setText("Tries: 0");
        labelCount.setHorizontalAlignment(SwingConstants.RIGHT);
        buttonPanel.add(labelCount, BorderLayout.EAST);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        labelFinal = new JLabel();
        labelFinal.setFont(new Font("SF Pro Display", Font.BOLD, 20));
        labelFinal.setHorizontalAlignment(SwingConstants.CENTER);
        labelFinal.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        inputPanel.add(labelFinal, BorderLayout.SOUTH);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkButton) {
            try {
                int value1 = Integer.parseInt(inputField.getText());
                if (ran != value1) {
                    labelFinal.setText("Value did not match. Try again");
                    inputField.setText("");
                    count++;
                    labelCount.setText("Tries: " + count);
                } else {
                    labelFinal.setText(value1 + " was the correct value. You Win!");
                    checkButton.setVisible(false);
                    playAgainButton.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input! Please enter valid integers.");
            }
        } else if (e.getSource() == playAgainButton) {
            ran = RandomGenerator.random(maxRange);
            labelFinal.setText("");
            inputField.setText("");
            checkButton.setVisible(true);
            playAgainButton.setVisible(false);
            count = 0;
            labelCount.setText("Tries: " + count);
        }
        else if (e.getSource() == exitButton) {
            System.exit(0);
        }
        else if (e.getSource() == setRangeButton) {
            String input = JOptionPane.showInputDialog(this, "Enter maximum number:");
            try {
                maxRange = Integer.parseInt(input);
                label2.setText("Enter Your number between 1 to " + maxRange + ": ");
                ran = RandomGenerator.random(maxRange);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input! Please enter a valid number.");
            }
        }
    }

    static class RandomGenerator {
        static int random() {
            return new Random().nextInt();
        }

        static int random(int x) {
            return new Random().nextInt(x) + 1;
        }
    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.setVisible(true);
    }
}
