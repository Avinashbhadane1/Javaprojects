import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class level2 extends JFrame implements ActionListener {

    JPanel panel1;
    JPanel panel2;
    JPanel panel3;
    int counter = 0;
    JPanel panel4;
    JButton decode;
    JButton next;
    JButton exit;
    JTextArea textArea;
    JTextArea show;
    String [] display = new String[26];
    public level2() {
        setTitle("LEVEL 2");
        setLayout(new GridLayout(1,2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,280);
        setResizable(false);
        setLocationRelativeTo(null);

        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();


        textArea = new JTextArea("Type your encoded text here.It should be at least a sentence");
        textArea.setFont(new Font("Arial", Font.PLAIN,18));
        textArea.setPreferredSize(new Dimension(280,110));
        textArea.setLineWrap(true);
        textArea.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        textArea.setWrapStyleWord(true);
        textArea.setAutoscrolls(true);
        textArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        show = new JTextArea("Next button shows the next best decode");
        show.setFont(new Font("Arial", Font.PLAIN,18));
        show.setPreferredSize(new Dimension(280,230));
        show.setLineWrap(true);
        show.setEditable(false);
        show.setBackground(Color.BLACK);
        show.setForeground(Color.WHITE);
        show.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
        show.setWrapStyleWord(true);
        show.setAutoscrolls(true);
        textArea.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        decode = new JButton("DECODE");
        decode.setPreferredSize(new Dimension(200,32));
        decode.setFont(new Font("Arial", Font.BOLD,17));
        decode.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        decode.setBackground(new Color(235,236,240));
        decode.setFocusable(false);
        decode.setVisible(true);
        decode.addActionListener(this);

        next = new JButton("NEXT");
        next.setPreferredSize(new Dimension(200,32));
        next.setFont(new Font("Arial", Font.BOLD,17));
        next.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        next.setBackground(new Color(235,236,240));
        next.setFocusable(false);
        next.setEnabled(false);
        next.setVisible(true);
        next.addActionListener(this);

        exit = new JButton("EXIT");
        exit.setPreferredSize(new Dimension(200,32));
        exit.setFont(new Font("Arial", Font.BOLD,17));
        exit.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        exit.setBackground(new Color(235,236,240));
        exit.setFocusable(false);
        exit.setVisible(true);
        exit.addActionListener(this);

        panel1.setLayout(new GridLayout(2,1));
        panel2.setLayout(new FlowLayout());
        panel2.add(show);

        panel1.add(panel3);
        panel1.add(panel4);
        panel3.add(textArea);
        panel4.add(decode);
        panel4.add(next);
        panel4.add(exit);
        add(panel1);
        add(panel2);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==exit){
            dispose();
        }
        if(e.getSource()==decode){
            level2Logic l = new level2Logic();
            String [] res = new String[26];
            for(int i = 0;i<26;i++){
                res[i] = l.decoder(textArea.getText(),i);
            }
            display = l.checker(res);
            show.setText(display[0]);
            next.setEnabled(true);
            counter++;
        }
        if(e.getSource()==next){
                if(counter!=25){
                    show.setText(display[counter]);
                }else{
                    show.setText(display[0]);
                    counter=0;
                }
            counter++;
        }
    }
}
