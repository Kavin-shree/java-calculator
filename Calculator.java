import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener, KeyListener {

    JTextField textField;
    double num1, num2, result;
    String operator;

    public Calculator() {

        setTitle("Java Calculator");
        setSize(300,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        textField.addKeyListener(this);
        add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,4));

        String buttons[] = {
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0","C","=","+"
        };

        for(String b : buttons){
            JButton btn = new JButton(b);
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel);
    }

    public void calculate() {

        num2 = Double.parseDouble(textField.getText());

        switch(operator){
            case "+": result = num1 + num2; break;
            case "-": result = num1 - num2; break;
            case "*": result = num1 * num2; break;
            case "/": result = num1 / num2; break;
        }

        textField.setText("" + result);
    }

    public void actionPerformed(ActionEvent e){

        String command = e.getActionCommand();

        if(command.matches("[0-9.]")){
            textField.setText(textField.getText() + command);
        }

        else if(command.matches("[/\\*\\-\\+]")){
            num1 = Double.parseDouble(textField.getText());
            operator = command;
            textField.setText("");
        }

        else if(command.equals("=")){
            calculate();
        }

        else if(command.equals("C")){
            textField.setText("");
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {

        char key = e.getKeyChar();

        if(Character.isDigit(key) || key=='.'){
            textField.setText(textField.getText()+key);
        }

        if(key=='+' || key=='-' || key=='*' || key=='/'){
            num1 = Double.parseDouble(textField.getText());
            operator = ""+key;
            textField.setText("");
        }

        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            calculate();
        }

        if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
            String text = textField.getText();
            if(text.length()>0)
                textField.setText(text.substring(0,text.length()-1));
        }
    }

    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        new Calculator().setVisible(true);
    }
}