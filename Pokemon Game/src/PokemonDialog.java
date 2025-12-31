import javax.swing.*;
import java.awt.*;


public class PokemonDialog extends JDialog implements data{
    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getIdSelected() {
        return idSelected;
    }

    public void setIdSelected(int idSelected) {
        this.idSelected = idSelected;
    }

    private int difficulty;//0,1,2 for easy medium and hard
    private int idSelected;
    public PokemonDialog(Frame parent) {
        super(parent, "Choose Pokémon for Opponent", true); // modal dialog
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(300, 200);
        setLocationRelativeTo(parent);

        String[] pokemons = new String[names.size()];
        for(int i = 0;i<pokemons.length;i++){
            pokemons[i] = names.get(i);
        }
        JComboBox<String> comboBox = new JComboBox<>(pokemons);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Select Pokémon:"));
        topPanel.add(comboBox);

        JRadioButton easy = new JRadioButton("Easy");
        JRadioButton medium = new JRadioButton("Medium");
        JRadioButton difficult = new JRadioButton("Hard");

        ButtonGroup difficultyGroup = new ButtonGroup();
        difficultyGroup.add(easy);
        difficultyGroup.add(medium);
        difficultyGroup.add(difficult);

        easy.setSelected(true);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(3, 1));
        middlePanel.setBorder(BorderFactory.createTitledBorder("Difficulty"));
        middlePanel.add(easy);
        middlePanel.add(medium);
        middlePanel.add(difficult);

        JButton selectBtn = new JButton("Select");
        selectBtn.setFocusable(false);
        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setFocusable(false);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(selectBtn);
        bottomPanel.add(cancelBtn);

        selectBtn.addActionListener(_ -> {
            String chosenPokemon = (String) comboBox.getSelectedItem();
            String difficulty = easy.isSelected() ? "Easy" : (medium.isSelected() ? "Medium" : "Hard");
            switch (difficulty){
                case "Easy":setDifficulty(0); break;
                case "Medium":setDifficulty(1); break;
                case "Hard":setDifficulty(2); break;
            }
            setIdSelected(comboBox.getSelectedIndex());
            JOptionPane.showMessageDialog(this,
                    "Opponent has been given " + chosenPokemon + " with difficulty " + difficulty);
            dispose();
        });

        cancelBtn.addActionListener(_ -> dispose());

        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}

