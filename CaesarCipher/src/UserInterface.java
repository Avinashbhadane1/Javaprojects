import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class UserInterface extends JFrame implements ActionListener, MouseListener {
    Encryption encrypt  = new Encryption();

    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    JPanel panel4;
    JButton encode;
    JButton decode;
    JButton level3;
    JTextArea show;
    JButton newWindow;
    JButton clear;
    JTextField textField;
    JTextArea jTextArea;
    public UserInterface(){
        Border border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        setTitle("Caesar Cipher");
        setLayout(new GridLayout(2,2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650,280);
        setResizable(false);
        setLocationRelativeTo(null);


        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();

        jTextArea = new JTextArea("Type your text here");
        jTextArea.setPreferredSize(new Dimension(310,140));
        jTextArea.setFont(new Font("Arial", Font.PLAIN,16));
        jTextArea.setMaximumSize(new Dimension(300,90));
        jTextArea.setLineWrap(true);
        jTextArea.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        jTextArea.setWrapStyleWord(true);
        jTextArea.setAutoscrolls(true);
        jTextArea.setBorder(border);

        encode = new JButton("ENCODE");
        encode.setPreferredSize(new Dimension(200,32));
        encode.setFont(new Font("Arial", Font.BOLD,17));
        encode.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        encode.setBackground(new Color(235,236,240));
        encode.setFocusable(false);
        encode.setVisible(true);

        clear = new JButton("CLEAR");
        clear.setPreferredSize(new Dimension(200,32));
        clear.setFont(new Font("Arial", Font.BOLD,17));
        clear.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        clear.setBackground(new Color(235,236,240));
        clear.setFocusable(false);
        clear.setVisible(true);

        decode = new JButton("DECODE");
        decode.setPreferredSize(new Dimension(200,32));
        decode.setFont(new Font("Arial", Font.BOLD,17));
        decode.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        decode.setBackground(new Color(235,236,240));
        decode.setFocusable(false);
        decode.setVisible(true);

        level3 = new JButton("LEVEL 3");
        level3.setPreferredSize(new Dimension(200,32));
        level3.setFont(new Font("Arial", Font.BOLD,17));
        level3.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        level3.setBackground(new Color(235,236,240));
        level3.setFocusable(false);
        level3.setVisible(true);

        textField = new JTextField("Type shift number");
        textField.setHorizontalAlignment(JTextField.LEFT);
        textField.setPreferredSize(new Dimension(300,32));
        textField.setFont(new Font("Arial", Font.BOLD,17));
        textField.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        show = new JTextArea("Decode or Encoded texts will appear here");
        show.setPreferredSize(new Dimension(300,110));
        show.setBackground(Color.BLACK);
        show.setOpaque(true);
        show.setFont(new Font("Arial", Font.PLAIN, 12));
        show.setForeground(Color.WHITE);
        show.setBorder(BorderFactory.createEtchedBorder());
        show.setLineWrap(true);
        show.setAutoscrolls(true);
        show.setWrapStyleWord(true);
        show.setEditable(false);

        newWindow = new JButton("LEVEL 2");
        newWindow.setPreferredSize(new Dimension(200,32));
        newWindow.setFont(new Font("Arial", Font.BOLD,17));
        newWindow.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        newWindow.setBackground(new Color(235,236,240));
        newWindow.setFocusable(false);
        newWindow.setVisible(true);

        textField.addMouseListener(this);
        jTextArea.addMouseListener(this);
        encode.addActionListener(this);
        decode.addActionListener(this);
        level3.addActionListener(this);
        clear.addActionListener(this);
        newWindow.addActionListener(this);

        panel1.add(jTextArea);
        panel3.add(textField);
        panel3.add(encode);
        panel3.add(clear);
        panel2.add(show);
        panel4.add(decode);
        panel4.add(newWindow);
        panel4.add(level3);
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==newWindow){
            new level2();
            dispose();
        }
        if(e.getSource()==level3){
            new level3();
            dispose();
        }
        int n;
        String values;


        if(e.getSource()==encode){

            if(Objects.equals(show.getText(), "")){
                n  = Integer.parseInt(textField.getText());
                values = encrypt.toEncode(jTextArea.getText(), n);

                    try {
                        if (!(Objects.equals(textField.getText(), null)) && !(Objects.equals(jTextArea.getText(), null))) {
                            show.setText(values);
                            decode.setEnabled(true);
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Please Fill both fields correctly");
                    }

            }else{
                JOptionPane.showMessageDialog(null,"click clear button");
            }

        }
        if(e.getSource()== decode) {
            if(textField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Type shift to decode");
            }else {
                n = Integer.parseInt(textField.getText());
                values = encrypt.toDecode(show.getText(), n);
                show.setText("");
                show.setText(values);
                decode.setEnabled(false);
            }
        }
        if(e.getSource()==clear){
            show.setText("");
        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource()==jTextArea){
            jTextArea.setText("");
        }
        if(e.getSource()==textField){
            textField.setText("");
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {


    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
