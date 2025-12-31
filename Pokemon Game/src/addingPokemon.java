import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class addingPokemon extends JDialog implements ActionListener, data {
    JTextField nameField, typeField, hpField, attackField, defenceField, speedField;
    JComboBox<String> imageCombo;
    JTextField[] moveNames = new JTextField[4];
    JTextField[] moveDamages = new JTextField[4];
    JButton submit, cancel;

    public addingPokemon(JFrame parent) {
        super(parent, "Add New Pokémon", true);
        setSize(500, 700);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel statsPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        statsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Basic Stats",
                TitledBorder.LEFT, TitledBorder.TOP));

        statsPanel.add(new JLabel("Name:")); nameField = new JTextField(); statsPanel.add(nameField);
        statsPanel.add(new JLabel("Type:")); typeField = new JTextField(); statsPanel.add(typeField);
        statsPanel.add(new JLabel("HP:")); hpField = new JTextField(); statsPanel.add(hpField);
        statsPanel.add(new JLabel("Attack:")); attackField = new JTextField(); statsPanel.add(attackField);
        statsPanel.add(new JLabel("Defence:")); defenceField = new JTextField(); statsPanel.add(defenceField);
        statsPanel.add(new JLabel("Speed:")); speedField = new JTextField(); statsPanel.add(speedField);

        mainPanel.add(statsPanel);

        JPanel imagePanel = new JPanel(new GridLayout(1, 2, 10, 10));
        imagePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Image Selection",
                TitledBorder.LEFT, TitledBorder.TOP));

        imagePanel.add(new JLabel("Image File:"));
        imageCombo = new JComboBox<>(loadAvailableImages());
        imagePanel.add(imageCombo);

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(imagePanel);

        JPanel movesPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        movesPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), "Moves",
                TitledBorder.LEFT, TitledBorder.TOP));

        for (int i = 0; i < 4; i++) {
            JPanel moveRow = new JPanel(new GridLayout(1, 2, 5, 5));
            moveNames[i] = new JTextField();
            moveDamages[i] = new JTextField();

            JPanel moveBox = new JPanel(new GridLayout(1, 4, 5, 5));
            moveBox.add(new JLabel("Move " + (i + 1) + ":"));
            moveBox.add(moveNames[i]);
            moveBox.add(new JLabel("Damage:"));
            moveBox.add(moveDamages[i]);

            movesPanel.add(moveBox);
        }

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(movesPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        submit = new JButton("Add Pokémon");
        cancel = new JButton("Cancel");
        submit.setPreferredSize(new Dimension(150, 40));
        cancel.setPreferredSize(new Dimension(150, 40));
        submit.addActionListener(this);
        cancel.addActionListener(this);

        buttonPanel.add(submit);
        buttonPanel.add(cancel);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private String[] loadAvailableImages() {
        java.util.List<String> options = new ArrayList<>();
        options.add("None");

        File folder = new File("src/Images");
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".png"));
            if (files != null) {
                for (File f : files) {
                    String fileName = f.getName();
                    String baseName = fileName.substring(0, fileName.lastIndexOf("."));

                    if (imagePath.contains("/Images/" + fileName)) continue;
                    if (names.contains(baseName)) continue;

                    options.add(fileName);
                }
            }
        }
        return options.toArray(new String[0]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            try {
                String name = nameField.getText().trim();
                String type = typeField.getText().trim();
                int hp = Integer.parseInt(hpField.getText().trim());
                int attackVal = Integer.parseInt(attackField.getText().trim());
                int defenceVal = Integer.parseInt(defenceField.getText().trim());
                int speedVal = Integer.parseInt(speedField.getText().trim());

                paths.add("C:\\Users\\HP\\IdeaProjects\\PS2\\src\\Pokemon\\" + name);
                names.add(name);
                types.add(type);
                health.add(hp);
                attack.add(attackVal);
                defence.add(defenceVal);
                speed.add(speedVal);

                int newId = names.size() - 1;
                id.add(newId);

                String selectedImage = (String) imageCombo.getSelectedItem();
                if (selectedImage != null && !selectedImage.equals("None")) {
                    imagePath.add("/Images/" + selectedImage);
                } else {
                    imagePath.add("None");
                }

                for (int i = 0; i < 4; i++) {
                    String moveName = moveNames[i].getText().trim();
                    int moveDamage = Integer.parseInt(moveDamages[i].getText().trim());
                    Move.add(new AbstractMap.SimpleEntry<>(moveName, moveDamage));
                }
                savePokemonToFile(name);

                JOptionPane.showMessageDialog(this, "Pokémon added successfully!");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == cancel) {
            dispose();
        }
    }

    private void savePokemonToFile(String name) {
        File file = new File("src/Pokemon/" + name);

        try (FileWriter writer = new FileWriter(file, false)) { // false = overwrite if exists
            int lastId = id.getLast();

            writer.write(lastId + "\n");
            writer.write(names.getLast() + "\n");
            writer.write(types.getLast() + "\n");
            writer.write(health.getLast() + "\n");
            writer.write(attack.getLast() + "\n");
            writer.write(defence.getLast() + "\n");
            writer.write(speed.getLast() + "\n");

            for (int i = 0; i < 4; i++) {
                if(i==3){
                    var move = Move.get((lastId * 4 )+ i);
                    writer.write(move.getKey() + "\n" + move.getValue() );
                    break;
                }
                var move = Move.get((lastId * 4 )+ i);
                writer.write(move.getKey() + "\n" + move.getValue() + "\n");
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Failed to save Pokémon file: " + ex.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
