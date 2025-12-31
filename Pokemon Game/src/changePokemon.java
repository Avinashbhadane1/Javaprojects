import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class changePokemon extends JDialog implements ActionListener,data {
    JComboBox comboBox;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private int id;
    JButton load = new JButton("Load"), select = new JButton("Select");
    JPanel p1 = new JPanel(),p2 = new JPanel(),p3 = new JPanel(),p4 = new JPanel();
    JPanel p5 = new JPanel(),p6 = new JPanel(),p7 = new JPanel(),p8 = new JPanel();
    JPanel sub1 = new JPanel();JPanel sub2 = new JPanel();JPanel sub3 = new JPanel();JPanel sub4 = new JPanel();JPanel sub5 = new JPanel();JPanel sub6 = new JPanel();JPanel sub7 = new JPanel();JPanel sub8 = new JPanel();JPanel sub9 = new JPanel();JPanel sub10 = new JPanel();
    JLabel imageLabel = new JLabel();
    JButton nameDisplayer0 = new JButton("Name : "); JTextField nameDisplayer1 = new JTextField();
    JButton hpDisplayer0 = new JButton("hp : "); JTextField hpDisplayer1 = new JTextField();
    JButton attackDisplayer0 = new JButton("attack : "); JTextField attackDisplayer1 = new JTextField();
    JButton defenceDisplayer0 = new JButton("defence : "); JTextField defenceDisplayer1 = new JTextField();
    JButton speedDisplayer0 = new JButton("speed : "); JTextField speedDisplayer1 = new JTextField();
    JButton typeDisplayer0 = new JButton("Type : "); JTextField typeDisplayer1 = new JTextField();
    JButton move1 = new JButton(); JTextField moveDamage1 = new JTextField();
    JButton move2 = new JButton(); JTextField moveDamage2 = new JTextField();
    JButton move3 = new JButton(); JTextField moveDamage3 = new JTextField();
    JButton move4 = new JButton(); JTextField moveDamage4 = new JTextField();
    public JPanel setter(JButton button, JTextField field,int hGap,int vGap,int size,int columnSetter){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,hGap,vGap));
        panel.setBackground(Color.black);
        button.setEnabled(false);
        button.setFocusable(false);
        button.setFont(new Font("Arial",Font.BOLD,size));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.add(button);

        field.setFont(new Font("Arial", Font.BOLD, size));
        field.setForeground(Color.WHITE);
        field.setBackground(Color.BLACK);
        field.setEnabled(false);
        field.setEditable(false);
        field.setColumns(columnSetter);
        field.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.add(field);

        return panel;
    }
    public void statsDisplayer(int iid){
        nameDisplayer1.setText(names.get(iid));
        typeDisplayer1.setText(types.get(iid));
        hpDisplayer1.setText(String.valueOf(health.get(iid)));
        defenceDisplayer1.setText(String.valueOf(defence.get(iid)));
        attackDisplayer1.setText(String.valueOf(attack.get(iid)));
        speedDisplayer1.setText(String.valueOf(speed.get(iid)));
        move1.setText("Move : " + Move.get(iid*4).getKey());
        move2.setText("Move : " + Move.get((iid*4)+1).getKey());
        move3.setText("Move : " + Move.get((iid*4)+2).getKey());
        move4.setText("Move : " + Move.get((iid*4)+3).getKey());
        moveDamage1.setText("Deals a damage of " + Move.get(iid*4).getValue());
        moveDamage2.setText("Deals a damage of " + Move.get((iid*4)+1).getValue());
        moveDamage3.setText("Deals a damage of " + Move.get((iid*4)+2).getValue());
        moveDamage4.setText("Deals a damage of " + Move.get((iid*4)+3).getValue());
        imageLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath.get(iid))));
        Image image = icon.getImage().getScaledInstance(200, 170, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
    }
    changePokemon(int id, ArrayList<Integer> idUnavailable){
        setTitle("Changing Pokemon");
        setSize(1000,500);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2,2));
        setModal(true);
        setFocusable(false);
        setResizable(false);

        p1.setLayout(new GridLayout(1,1));
        imageLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath.get(id))));
        Image image = icon.getImage().getScaledInstance(200, 170, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        p1.add(imageLabel);

        p2.setLayout(new FlowLayout());
        ArrayList<String> tempNames = new ArrayList<>(names);
        for (Integer integer : idUnavailable) {
            tempNames.remove(names.get(integer));
        }
        comboBox = new JComboBox(tempNames.toArray());
        comboBox.setEditable(false);
        comboBox.setFocusable(false);
        p2.add(comboBox);

        String check = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
        for(int i =0 ;i<names.size();i++){
            if(Objects.equals(names.get(i), check)){
                statsDisplayer(i);
            }
        }
        sub1 = setter(nameDisplayer0,nameDisplayer1,4,2,12,15);sub2 = setter(typeDisplayer0,typeDisplayer1,4,2,12,15);
        sub3 = setter(hpDisplayer0,hpDisplayer1,4,2,12,15);sub4 = setter(attackDisplayer0,attackDisplayer1,4,2,12,15);
        sub5 = setter(defenceDisplayer0,defenceDisplayer1,4,2,12,15);sub6 = setter(speedDisplayer0,speedDisplayer1,4,2,12,15);
        sub7 = setter(move1,moveDamage1,5,3,15,15);sub8 = setter(move2,moveDamage2,5,3,15,15);
        sub9 = setter(move3,moveDamage3,5,3,15,15);sub10 = setter(move4,moveDamage4,5,3,15,15);

        p3.setLayout(new GridLayout(1,2));
        p3.setBackground(Color.cyan);
        p4.setLayout(new GridLayout(1,2));
        p5.setLayout(new GridLayout(6,1));
        p6.setLayout(new GridLayout(4,1));
        p5.add(sub1);p5.add(sub2);p5.add(sub3);p5.add(sub4);p5.add(sub5);p5.add(sub6);
        p6.add(sub7);p6.add(sub8);p6.add(sub9);p6.add(sub10);
        p3.add(p5);
        p3.add(p6);

        load.setHorizontalAlignment(JButton.CENTER);
        load.setVerticalAlignment(JButton.CENTER);
        load.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        load.setForeground(Color.BLACK);
        load.setBackground(Color.WHITE);
        load.setFocusable(false);
        load.setFont(new Font("Arial",Font.BOLD,34));
        load.addActionListener(this);

        select.setHorizontalAlignment(JButton.CENTER);
        select.setVerticalAlignment(JButton.CENTER);
        select.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        select.setForeground(Color.BLACK);
        select.setBackground(Color.WHITE);
        select.setFocusable(false);
        select.setFont(new Font("Arial",Font.BOLD,34));
        select.addActionListener(this);

        p7.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        p7.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        p7.add(load);
        p8.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        p8.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        p8.add(select);
        p4.add(p7);
        p4.add(p8);

        add(p1);
        add(p2);
        add(p3);
        add(p4);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==load){
            String check = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
            for(int i =0 ;i<names.size();i++){
                if(Objects.equals(names.get(i), check)){
                    statsDisplayer(i);
                }
            }
        }
        if(e.getSource()==select){
            String check = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
            for(int i =0 ;i<names.size();i++){
                if(Objects.equals(names.get(i), check)){
                    setId(i);
                }
            }
            dispose();
        }
    }
}
