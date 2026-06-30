import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class Calculator{
    int boardWidth =360;
    int boardHeight =540;

    Color customLightGray = new Color(212,212,210);
    Color customDarkGray = new Color(80,80,80);
    Color customblack= new Color(28,28,28);
    Color customOrange = new Color(255,100,90);
    Color customSciColor= new Color(105,105,105);
    
    String[] buttonValues={
        "AC", "⌫", "%", "÷",
        "7", "8", "9" ,"×",
        "4", "5","6","–",
        "1","2", "3", "+",
        "0", ".","+/-", "="
    };
    String[] rightSymbols = {"÷","×","–","+","="};
    String[] topSymbols = {"AC","⌫","%"};

    String[] sciButtonValues = {
        "sin", "cos", "tan", "√",
        "ln", "log", "x²", "π"
    };
    
    boolean isScientificMode = false;
    int collapsedHeight = boardHeight;
    int expandedHeight = boardHeight ;
    
    JPanel sciPanel = new JPanel();
    JButton toggleSciButton = new JButton("ƒ(x)");
    JPanel mainPanel = new JPanel();

    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel=new JLabel();
    JPanel displayPanel=new JPanel();
    JPanel buttonsPanel=new JPanel();
 

    String A ="0";
    String operator =null;
    String B=null;


    Calculator(){
        
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        displayLabel.setBackground(customblack);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Ariel",Font.PLAIN,80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel, BorderLayout.CENTER);

        toggleSciButton.setFocusable(false);
        toggleSciButton.setFont(new Font("Ariel", Font.PLAIN, 16));
        toggleSciButton.setBackground(customDarkGray);
        toggleSciButton.setForeground(Color.white);
        displayPanel.add(toggleSciButton, BorderLayout.NORTH);

        frame.add(displayPanel,BorderLayout.NORTH);

        buttonsPanel.setLayout(new GridLayout(5,4));
        buttonsPanel.setBackground(customblack);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
        frame.add(mainPanel, BorderLayout.CENTER);
 
        frame.add(displayPanel,BorderLayout.NORTH);
        

        for (int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            if(buttonValue=="⌫" || buttonValue=="AC"){
                button.setFont(new Font("Ariel",Font.PLAIN,25));
            }else if(Arrays.asList(rightSymbols).contains(buttonValue)){
                button.setFont(new Font("Ariel",Font.PLAIN,34));
            }
            else{button.setFont(new Font("Ariel",Font.PLAIN,30));}
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setContentAreaFilled(true); 
            button.setFocusPainted(true);  
            button.setBorder(new LineBorder(customblack));

            if(Arrays.asList(topSymbols).contains(buttonValue)){
                button.setBackground(customLightGray);
                button.setForeground(customblack);
            }else if(Arrays.asList(rightSymbols).contains(buttonValue)){
                button.setBackground(customOrange);
                button.setForeground(Color.white);
            }
            else{
                button.setBackground(customDarkGray);
                button.setForeground(Color.white);
            }

            buttonsPanel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();
                    if(Arrays.asList(rightSymbols).contains(buttonValue)){
                        if(buttonValue== "="){
                            if(A!= null){
                                B=displayLabel.getText();
                                double a = Double.parseDouble(A);
                                double b = Double.parseDouble(B);
                                switch(operator){
                                    case "+" : displayLabel.setText(removeZeroDecimal(a+b));
                                    case "–" : displayLabel.setText(removeZeroDecimal(a-b));
                                    case "÷": displayLabel.setText(removeZeroDecimal(a/b));
                                    case "×" : displayLabel.setText(removeZeroDecimal(a*b));
                                    default: return;
                                }
                            }
                        }
                        else if("÷×–+".contains(buttonValue)){
                            if(operator==null){
                                A=displayLabel.getText();
                                displayLabel.setText("0");
                                B="0";
                            }
                            operator = buttonValue;
                        }
                    }
                    else if(Arrays.asList(topSymbols).contains(buttonValue)){
                        if(buttonValue== "AC"){
                            clearAll();
                            displayLabel.setText("0");

                        }
                        else if(buttonValue== "⌫"){
                            int num = Integer.parseInt(displayLabel.getText());
                            num/=10;
                            displayLabel.setText(Integer.toString(num));

                        } else if(buttonValue== "%"){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay/=100;
                            displayLabel.setText(removeZeroDecimal(numDisplay));

                        }
                         
                    }
                    else{ // digits or .
                        if(buttonValue== "."){
                            if(!displayLabel.getText().contains(buttonValue)){
                                displayLabel.setText(displayLabel.getText() +buttonValue);
                            }

                        }
                        else if("0123456789".contains(buttonValue)){
                            if(displayLabel.getText()=="0"){
                                displayLabel.setText(buttonValue);
                            }
                            else{
                                displayLabel.setText(displayLabel.getText() +buttonValue);
                            }
                        }
                        else if(buttonValue== "+/-"){ 
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay*= -1;
                            displayLabel.setText(removeZeroDecimal(numDisplay));
                            
                        }
                    }
                }
            });

        frame.setVisible(true);
        }
        sciPanel.setLayout(new GridLayout(2,4));
        sciPanel.setBackground(customblack);

        for (String sciValue : sciButtonValues) {
            JButton sciButton = new JButton(sciValue);
            sciButton.setFont(new Font("Ariel", Font.PLAIN, 22));
            sciButton.setFocusable(false);
            sciButton.setBackground(customSciColor);
            sciButton.setForeground(Color.WHITE);
            sciButton.setBorder(new LineBorder(customblack));
            sciPanel.add(sciButton);

            sciButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    double num = Double.parseDouble(displayLabel.getText());
                    double result;
                    switch (sciValue) {
                        case "sin": result = Math.sin(Math.toRadians(num)); break;
                        case "cos": result = Math.cos(Math.toRadians(num)); break;
                        case "tan": result = Math.tan(Math.toRadians(num)); break;
                        case "√":   result = Math.sqrt(num); break;
                        case "ln":  result = Math.log(num); break;
                        case "log": result = Math.log10(num); break;
                        case "x²":  result = Math.pow(num, 2); break;
                        case "π":   result = Math.PI; break;
                        default: return;
                    }
                    displayLabel.setText(removeZeroDecimal(result));
                    
                }
            });
        }
        toggleSciButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isScientificMode = !isScientificMode;
                if (isScientificMode) {
                    mainPanel.add(sciPanel, BorderLayout.NORTH);
                    frame.setSize(boardWidth, expandedHeight);
                } else {
                    mainPanel.remove(sciPanel);
                    frame.setSize(boardWidth, collapsedHeight);
                }
                frame.revalidate();
                frame.repaint();
            }
        });
        
        

    }
    void clearAll(){
        A="0";
        operator=null;
        B=null;
    }

    String removeZeroDecimal(double numDisplay){
        if(numDisplay%1 == 0){
            return Integer.toString((int) numDisplay);
        }
        else{
            return Double.toString(numDisplay);
        }

    }

}
