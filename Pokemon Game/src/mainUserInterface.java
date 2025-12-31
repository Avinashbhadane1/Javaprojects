import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
interface data{
    ArrayList<Map.Entry<String, Integer>> Move = new ArrayList<>();
    ArrayList<String> paths = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> imagePath = new ArrayList<>();
    ArrayList<Integer> health = new ArrayList<>();
    ArrayList<Integer> attack = new ArrayList<>();
    ArrayList<Integer> defence = new ArrayList<>();
    ArrayList<Integer> speed = new ArrayList<>();
    ArrayList<String> types = new ArrayList<>();
    ArrayList<Integer> id = new ArrayList<>();
    ArrayList<Map.Entry<String,int[]>> effects = new ArrayList<>();
}
public class mainUserInterface extends JFrame implements MouseListener, ActionListener,data {
    JMenuBar menuBar;
    JMenuItem exitForSure;
    JMenu options;
    JMenuItem add;
    JPanel panel1;JPanel panel2;JPanel panel3;JPanel panel4;JPanel panel5;JPanel panel6;
    JPanel sub1 = new JPanel();JPanel sub2 = new JPanel();JPanel sub3 = new JPanel();JPanel sub4 = new JPanel();JPanel sub5 = new JPanel();JPanel sub6 = new JPanel();
    JPanel sub7 = new JPanel();JPanel sub8 = new JPanel();JPanel sub9 = new JPanel();JPanel sub10 = new JPanel();JPanel sub11 = new JPanel();JPanel sub12 = new JPanel();
    JLabel l1 = new JLabel();JLabel l2 = new JLabel();JLabel l3 = new JLabel();JLabel l4 = new JLabel();JLabel l5 = new JLabel();JLabel l6 = new JLabel();JLabel l7 = new JLabel();JLabel l8 = new JLabel();
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
    JButton change = new JButton("CHANGE");
    JButton battle = new JButton("BATTLE");
    int onDisplay;

    public void statsDisplayer(int iid){
        onDisplay = iid;
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
    }
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

    mainUserInterface(){
        new effectsData();
        setTitle("Pokemon Battle");
        setSize(1500,820);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1,2));
        setFocusable(false);
        setResizable(false);
        menuBar = new JMenuBar();
        options = new JMenu("OPTIONS");
        exitForSure = new JMenuItem("EXIT");
        add = new JMenuItem("ADD POKEMON");
        exitForSure.addActionListener(this);
        add.addActionListener(this);
        options.add(add);
        options.add(exitForSure);
        menuBar.add(options);

        paths.add("articuno");
        paths.add("blastoise");
        paths.add("butterfree");
        paths.add("charizard");
        paths.add("gengar");
        paths.add("lugia");
        paths.add("mega_mewtwo_x");
        paths.add("pikachu");
        paths.add("alakazam");

        pokemon articuno = new pokemon();
        articuno = articuno.statSetter(paths.getFirst(),articuno);

        pokemon blastoise = new pokemon();
        blastoise = blastoise.statSetter(paths.get(1),blastoise);

        pokemon butterfree = new pokemon();
        butterfree = butterfree.statSetter(paths.get(2),butterfree);

        pokemon charizard = new pokemon();
        charizard = charizard.statSetter(paths.get(3),charizard);

        pokemon gengar = new pokemon();
        gengar = gengar.statSetter(paths.get(4),gengar);

        pokemon lugia = new pokemon();
        lugia = lugia.statSetter(paths.get(5),lugia);

        pokemon mega_mewtwo_x = new pokemon();
        mega_mewtwo_x = mega_mewtwo_x.statSetter(paths.get(6),mega_mewtwo_x);

        pokemon pikachu = new pokemon();
        pikachu = pikachu.statSetter(paths.get(7),pikachu);

        pokemon alakazam = new pokemon();
        alakazam = alakazam.statSetter(paths.get(8),alakazam);


        ImageIcon imgIc = new ImageIcon("pokeball.png");
        Image img = imgIc.getImage();
        setIconImage(img);

        id.add(articuno.getId());id.add(blastoise.getId());id.add(butterfree.getId());id.add(charizard.getId());
        id.add(gengar.getId());id.add(lugia.getId());id.add(mega_mewtwo_x.getId());id.add(pikachu.getId());id.add(alakazam.getId()); 
        imagePath.add("articuno.png");imagePath.add("blastoise.png");imagePath.add("butterfree.png");imagePath.add("charizard.png");
        imagePath.add("gengar.png");imagePath.add("lugia.png");imagePath.add("mega_mewtwo_x.png");imagePath.add("pikachu.png");imagePath.add("alakazam.png");
        types.add(articuno.getType());types.add(blastoise.getType());types.add(butterfree.getType());types.add(charizard.getType());
        types.add(gengar.getType());types.add(lugia.getType());types.add(mega_mewtwo_x.getType());types.add(pikachu.getType());types.add(alakazam.getType());
        names.add(articuno.getName());names.add(blastoise.getName());names.add(butterfree.getName());names.add(charizard.getName());
        names.add(gengar.getName());names.add(lugia.getName());names.add(mega_mewtwo_x.getName());names.add(pikachu.getName());names.add(alakazam.getName());        health.add(articuno.getHp());health.add(blastoise.getHp());health.add(butterfree.getHp());health.add(charizard.getHp());
        health.add(gengar.getHp());health.add(lugia.getHp());health.add(mega_mewtwo_x.getHp());health.add(pikachu.getHp());health.add(alakazam.getHp());
        attack.add(articuno.getAttackPower());attack.add(blastoise.getAttackPower());attack.add(butterfree.getAttackPower());attack.add(charizard.getAttackPower());
        attack.add(gengar.getAttackPower());attack.add(lugia.getAttackPower());attack.add(mega_mewtwo_x.getAttackPower());attack.add(pikachu.getAttackPower());attack.add(alakazam.getAttackPower());
        defence.add(articuno.getDefencePower());defence.add(blastoise.getDefencePower());defence.add(butterfree.getDefencePower());defence.add(charizard.getDefencePower());
        defence.add(gengar.getDefencePower()); defence.add(lugia.getDefencePower());defence.add(mega_mewtwo_x.getDefencePower());defence.add(pikachu.getDefencePower());defence.add(alakazam.getDefencePower());
        speed.add(articuno.getSpeed());speed.add(blastoise.getSpeed());speed.add(butterfree.getSpeed());speed.add(charizard.getSpeed());
        speed.add(gengar.getSpeed());speed.add(lugia.getSpeed());speed.add(mega_mewtwo_x.getSpeed());speed.add(pikachu.getSpeed());speed.add(alakazam.getSpeed());
        Move.addAll(articuno.getMoves());Move.addAll(blastoise.getMoves());Move.addAll(butterfree.getMoves());Move.addAll(charizard.getMoves());
        Move.addAll(gengar.getMoves());Move.addAll(lugia.getMoves());Move.addAll(mega_mewtwo_x.getMoves());Move.addAll(pikachu.getMoves());Move.addAll(alakazam.getMoves());

        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(4,4));
        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2,1));
        panel3 = new JPanel();
        panel3.setLayout(new GridLayout(6,1));
        panel4 = new JPanel();
        panel4.setLayout(new GridLayout(2,1));
        panel5 = new JPanel(new GridLayout(4,1));
        panel6 = new JPanel(new GridLayout(1,2));

        change.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        change.setForeground(Color.BLACK);
        change.setBackground(Color.WHITE);
        change.setFocusable(false);
        change.setFont(new Font("Arial",Font.BOLD,34));
        change.addActionListener(this);

        battle.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        battle.setForeground(Color.BLACK);
        battle.setBackground(Color.WHITE);
        battle.setFocusable(false);
        battle.setFont(new Font("Arial",Font.BOLD,34));
        battle.addActionListener(this);

        sub1 = setter(nameDisplayer0,nameDisplayer1,10,8,34,15);sub2 = setter(typeDisplayer0,typeDisplayer1,10,8,34,15);
        sub3 = setter(hpDisplayer0,hpDisplayer1,10,8,34,15);sub4 = setter(attackDisplayer0,attackDisplayer1,10,8,34,15);
        sub5 = setter(defenceDisplayer0,defenceDisplayer1,10,8,34,15);sub6 = setter(speedDisplayer0,speedDisplayer1,10,8,34,15);
        sub7 = setter(move1,moveDamage1,8,6,20,15);sub8 = setter(move2,moveDamage2,8,6,20,15);
        sub9 = setter(move3,moveDamage3,8,6,20,15);sub10 = setter(move4,moveDamage4,8,6,20,15);
        sub11 = new JPanel(new FlowLayout(FlowLayout.CENTER,20,50));
        sub11.setBackground(Color.BLACK);sub11.add(change);
        sub12 = new JPanel(new FlowLayout(FlowLayout.CENTER,20,50));
        sub12.setBackground(Color.BLACK);sub12.add(battle);

        panel3.add(sub1);panel3.add(sub2);panel3.add(sub3);panel3.add(sub4);panel3.add(sub5);panel3.add(sub6);
        panel5.add(sub7);panel5.add(sub8);panel5.add(sub9);panel5.add(sub10);
        panel6.add(sub11);panel6.add(sub12);
        panel4.add(panel5);panel4.add(panel6);
        panel2.add(panel3);panel2.add(panel4);

        l1 = articuno.pokemonSetter(l1,"articuno.png",articuno.getName());
        l2 = blastoise.pokemonSetter(l2,"blastoise.png",blastoise.getName());
        l3 = butterfree.pokemonSetter(l3,"butterfree.png",butterfree.getName());
        l4 = charizard.pokemonSetter(l4,"charizard.png",charizard.getName());
        l5 = gengar.pokemonSetter(l5,"gengar.png",gengar.getName());
        l6 = lugia.pokemonSetter(l6,"lugia.png",lugia.getName());
        l7 = mega_mewtwo_x.pokemonSetter(l7,"mega_mewtwo_x.png",mega_mewtwo_x.getName());
        l8 = pikachu.pokemonSetter(l8,"pikachu.png",pikachu.getName());

        l1.addMouseListener(this);
        l2.addMouseListener(this);
        l3.addMouseListener(this);
        l4.addMouseListener(this);
        l5.addMouseListener(this);
        l6.addMouseListener(this);
        l7.addMouseListener(this);
        l8.addMouseListener(this);

        panel1.add(l1);
        panel1.add(l2);
        panel1.add(l3);
        panel1.add(l4);
        panel1.add(l5);
        panel1.add(l6);
        panel1.add(l7);
        panel1.add(l8);
        setJMenuBar(menuBar);
        add(panel1);
        add(panel2);
        statsDisplayer(0);
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel src = (JLabel) e.getSource();
        int idCheck = names.indexOf(src.getText());
        if (idCheck != -1) {
            statsDisplayer(idCheck);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==change){
            ArrayList<Integer> idUnavailable = new ArrayList<>();
            JLabel[] setOfThem = {l1,l2,l3,l4,l5,l6,l7,l8};
            for (JLabel label : setOfThem) {
                for (int j = 0; j < names.size(); j++) {
                    if (Objects.equals(label.getText(), names.get(j))) {
                        idUnavailable.add(j);
                    }
                }
            }
            changePokemon ch = new changePokemon(onDisplay,idUnavailable);
            ImageIcon icon = new ImageIcon(imagePath.get(ch.getId()));
            Image image = icon.getImage().getScaledInstance(200, 170, Image.SCALE_SMOOTH);
            for (JLabel label : setOfThem) {
                if (Objects.equals(label.getText(), names.get(onDisplay))) {
                    pokemon pokemon = new pokemon();
                    pokemon.statSetter(paths.get(ch.getId()),pokemon);
                    label = pokemon.pokemonSetter(label,imagePath.get(ch.getId()),names.get(ch.getId()));
                    label.setIcon(new ImageIcon(image));
                    onDisplay = ch.getId();
                }
            }

            statsDisplayer(ch.getId());
        }
        if(e.getSource()==battle){
            PokemonDialog opponentPokemon = new PokemonDialog(this);
            new BattleMenu(this, onDisplay , opponentPokemon.getIdSelected(), opponentPokemon.getDifficulty());
        }
        if(e.getSource()==exitForSure){
            System.exit(0);
        }
        if(e.getSource()==add){
            addingPokemon extra = new addingPokemon(this);
        }
    }

    public static void main(String[] args) {
        new mainUserInterface();
    }
}
