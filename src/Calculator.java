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
    Color customOrange = new Color(255,149,0);
    
    String[] buttonValues={
        "AC", "+/-", "%", "÷",
        "7", "8", "9" ,"x",
        "4", "5","6","-",
        "1","2", "3", "+",
        "0", ".","✓", "="
    };
    String[] rightSymbols = {"÷","x","-","+","="};
    String[] topSymbols = {"AC","+/-","%"};

    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel=new JLabel();
    JPanel displayPanel=new JPanel();
    JPanel buttonsPanel=new JPanel();


    
    class RoundedBorder implements Border {
        private int radius;
    
        RoundedBorder(int radius) {
            this.radius = radius;
        }
    
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }
    
        public boolean isBorderOpaque() {
            return true;
        }
    
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            // Draw the curved perimeter boundary
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }


    Calculator(){
        frame.setVisible(true);
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
        displayPanel.add(displayLabel);
        frame.add(displayPanel,BorderLayout.NORTH);

        buttonsPanel.setLayout(new GridLayout(5,4));
        buttonsPanel.setBackground(customblack);
        frame.add(buttonsPanel);

        for (int i = 0; i <= buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Ariel",Font.PLAIN,30));
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
        }

    }

}
