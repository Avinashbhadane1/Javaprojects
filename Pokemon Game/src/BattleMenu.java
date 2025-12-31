import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.Timer;



public class BattleMenu extends JDialog implements data {

    private final BattlePokemon player;
    private final BattlePokemon opponent;
    private final int difficulty;

    private final JTextArea logArea = new JTextArea();
    private final JButton[] moveButtons = new JButton[4];
    private final JLabel playerImage = new JLabel();
    private final JLabel opponentImage = new JLabel();
    private final JProgressBar playerHpBar = new JProgressBar();
    private final JProgressBar opponentHpBar = new JProgressBar();
    private final JLabel playerNameLabel = new JLabel();
    private final JLabel opponentNameLabel = new JLabel();

    private final Random rnd = new Random();

    public BattleMenu(Frame parent, int playerIndex, int opponentIndex, int difficulty) {
        super(parent, "Pokémon Battle", true);
        this.difficulty = difficulty;

        pokemon basePlayer = new pokemon();
        basePlayer = basePlayer.statSetter(paths.get(playerIndex), basePlayer);

        pokemon baseOpponent = new pokemon();
        baseOpponent = baseOpponent.statSetter(paths.get(opponentIndex), baseOpponent);

        player = new BattlePokemon(basePlayer, playerIndex);
        opponent = new BattlePokemon(baseOpponent, opponentIndex);

        initUI();
        appendLog(String.format("Battle start: %s vs %s (Difficulty: %s)\n",
                player.name, opponent.name, difficultyText()));

        if (player.speed >= opponent.speed) {
            enablePlayerMoves(true);
            appendLog(player.name + " is faster and moves first.\n");
        } else {
            enablePlayerMoves(false);
            appendLog(opponent.name + " is faster and will move first.\n");

            Timer t = new Timer(700, e -> {
                ((Timer) e.getSource()).stop();
                aiTurnAndResume();
            });
            t.setRepeats(false);
            t.start();
        }

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(980, 560);
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout(8, 8));


        JPanel left = new JPanel(new BorderLayout(6,6));
        left.setBackground(Color.BLACK);
        playerImage.setHorizontalAlignment(SwingConstants.CENTER);
        loadImageIntoLabel(playerImage, player.imagePath);
        playerNameLabel.setText(player.name);
        playerNameLabel.setForeground(Color.WHITE);
        playerNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        playerHpBar.setMinimum(0); playerHpBar.setMaximum(player.maxHp); playerHpBar.setValue(player.currentHp);
        playerHpBar.setStringPainted(true);
        playerHpBar.setString(hpString(player.currentHp, player.maxHp));
        left.add(playerImage, BorderLayout.CENTER);

        JPanel pInfo = new JPanel(new GridLayout(2,1));
        pInfo.setBackground(Color.BLACK);
        pInfo.add(playerNameLabel);
        pInfo.add(playerHpBar);
        left.add(pInfo, BorderLayout.SOUTH);


        JPanel right = new JPanel(new BorderLayout(6,6));
        right.setBackground(Color.BLACK);
        opponentImage.setHorizontalAlignment(SwingConstants.CENTER);
        loadImageIntoLabel(opponentImage, opponent.imagePath);
        opponentNameLabel.setText(opponent.name);
        opponentNameLabel.setForeground(Color.WHITE);
        opponentNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        opponentHpBar.setMinimum(0); opponentHpBar.setMaximum(opponent.maxHp); opponentHpBar.setValue(opponent.currentHp);
        opponentHpBar.setStringPainted(true);
        opponentHpBar.setString(hpString(opponent.currentHp, opponent.maxHp));

        JPanel oppInfo = new JPanel(new GridLayout(2,1));
        oppInfo.setBackground(Color.BLACK);
        oppInfo.add(opponentNameLabel);
        oppInfo.add(opponentHpBar);

        right.add(opponentImage, BorderLayout.CENTER);
        right.add(oppInfo, BorderLayout.NORTH);

        JPanel movesPanel = new JPanel(new GridLayout(4,1,6,6));
        movesPanel.setBackground(Color.BLACK);
        movesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Moves"));

        for (int i = 0; i < 4; i++) {
            String text = (i < player.moves.size()) ? player.moves.get(i).getKey() : "----";
            moveButtons[i] = new JButton(text);
            moveButtons[i].setFocusable(false);
            final int idx = i;
            moveButtons[i].addActionListener(e -> onPlayerMove(idx));
            moveButtons[i].setEnabled(i < player.moves.size());
            movesPanel.add(moveButtons[i]);
        }

        right.add(movesPanel, BorderLayout.SOUTH);

        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(logArea);
        scroll.setBorder(BorderFactory.createTitledBorder("Battle Log"));
        scroll.setPreferredSize(new Dimension(420, 460));

        add(left, BorderLayout.WEST);
        add(scroll, BorderLayout.CENTER);
        add(right, BorderLayout.EAST);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottom.setBackground(Color.BLACK);
        JButton forfeit = new JButton("Forfeit");
        forfeit.setFocusable(false);
        forfeit.addActionListener(e -> {
            appendLog("You forfeited the battle.");
            JOptionPane.showMessageDialog(this, "You forfeited. You lose.");
            dispose();
        });
        bottom.add(forfeit);
        add(bottom, BorderLayout.SOUTH);

        left.setPreferredSize(new Dimension(260, 480));
        right.setPreferredSize(new Dimension(300, 480));
    }

    private void onPlayerMove(int idx) {
        if (idx >= player.moves.size()) return;

        if (player.isAsleep()) {
            appendLog(player.name + " is asleep and can't move!");
            player.decrementSleep();

            enablePlayerMoves(false);
            Timer t = new Timer(600, e -> {
                ((Timer) e.getSource()).stop();
                aiTurnAndResume();
            });
            t.setRepeats(false);
            t.start();
            return;
        }
        if (player.isParalyzed()) {
            if (player.paralysisSkipThisTurn()) {
                appendLog(player.name + " is paralyzed and couldn't move!");
                player.decrementParalysis();
                enablePlayerMoves(false);
                Timer t = new Timer(600, e -> {
                    ((Timer) e.getSource()).stop();
                    aiTurnAndResume();
                });
                t.setRepeats(false);
                t.start();
                return;
            } else {
                player.decrementParalysis();
            }
        }


        Map.Entry<String,Integer> move = player.moves.get(idx);
        appendLog(player.name + " used " + move.getKey() + "!");
        int power = move.getValue();

        if (power <= 0) {
            String mk = move.getKey().toLowerCase();
            if (mk.contains("poison")) {
                if (!opponent.isPoisoned()) { opponent.applyPoison(4); appendLog(opponent.name + " was poisoned!"); }
                else appendLog(opponent.name + " is already poisoned.");
            } else if (mk.contains("sleep")) {
                if (!opponent.isAsleep()) { int dur = 2 + rnd.nextInt(3); opponent.applySleep(dur); appendLog(opponent.name + " fell asleep (" + dur + " turns)."); }
                else appendLog(opponent.name + " is already asleep.");
            } else if (mk.contains("paral")) {
                if (!opponent.isParalyzed()) { opponent.applyParalysis(3); appendLog(opponent.name + " was paralyzed!"); }
                else appendLog(opponent.name + " is already paralyzed.");
            } else {
                appendLog("But it had no immediate effect.");
            }
        } else {

            int dmg = calculateDamage(player.attack, opponent.defence, power);
            opponent.currentHp = Math.max(0, opponent.currentHp - dmg);
            appendLog(String.format("%s took %d damage! (HP: %d/%d)", opponent.name, dmg, opponent.currentHp, opponent.maxHp));
        }

        updateBars();

        if (opponent.isFainted()) {
            appendLog(opponent.name + " fainted! You win!");
            JOptionPane.showMessageDialog(this, "You won! 🎉");
            dispose();
            return;
        }

        enablePlayerMoves(false);
        Timer t = new Timer(700, e -> {
            ((Timer) e.getSource()).stop();
            aiTurnAndResume();
        });
        t.setRepeats(false);
        t.start();
    }

    private void aiTurnAndResume() {

        if (opponent.isAsleep()) {
            appendLog(opponent.name + " is asleep and cannot move.");
            opponent.decrementSleep();
            applyPoisonEndOfTurn(); // poison applied at end of turn
            enablePlayerMoves(true);
            return;
        }
        if (opponent.isParalyzed()) {
            if (opponent.paralysisSkipThisTurn()) {
                appendLog(opponent.name + " is paralyzed and couldn't move!");
                opponent.decrementParalysis();
                applyPoisonEndOfTurn();
                enablePlayerMoves(true);
                return;
            } else {
                opponent.decrementParalysis();
            }
        }

        int moveIdx = chooseAiMove(opponent, player, difficulty);
        if (moveIdx < 0 || moveIdx >= opponent.moves.size()) {
            appendLog(opponent.name + " couldn't find a move!");
            enablePlayerMoves(true);
            applyPoisonEndOfTurn();
            return;
        }
        Map.Entry<String,Integer> move = opponent.moves.get(moveIdx);
        appendLog(opponent.name + " used " + move.getKey() + "!");
        int power = move.getValue();

        if (power <= 0) {
            String mk = move.getKey().toLowerCase();
            if (mk.contains("poison")) {
                if (!player.isPoisoned()) { player.applyPoison(4); appendLog(player.name + " was poisoned!"); }
                else appendLog(player.name + " is already poisoned.");
            } else if (mk.contains("sleep")) {
                if (!player.isAsleep()) { int dur = 2 + rnd.nextInt(3); player.applySleep(dur); appendLog(player.name + " fell asleep (" + dur + " turns)."); }
                else appendLog(player.name + " is already asleep.");
            } else if (mk.contains("paral")) {
                if (!player.isParalyzed()) { player.applyParalysis(3); appendLog(player.name + " was paralyzed!"); }
                else appendLog(player.name + " is already paralyzed.");
            } else {
                appendLog("But it had no immediate effect.");
            }
        } else {
            int dmg = calculateDamage(opponent.attack, player.defence, power);
            player.currentHp = Math.max(0, player.currentHp - dmg);
            appendLog(String.format("%s took %d damage! (HP: %d/%d)", player.name, dmg, player.currentHp, player.maxHp));
        }

        updateBars();

        if (player.isFainted()) {
            appendLog(player.name + " fainted! You lose.");
            JOptionPane.showMessageDialog(this, "You lost! 💀");
            dispose();
            return;
        }

        applyPoisonEndOfTurn();

        enablePlayerMoves(true);
    }

    private void applyPoisonEndOfTurn() {
        if (player.isPoisoned()) {
            int dmg = Math.max(1, (int) Math.ceil(player.maxHp * 0.10));
            player.currentHp = Math.max(0, player.currentHp - dmg);
            appendLog(player.name + " took " + dmg + " poison damage! (HP: " + player.currentHp + "/" + player.maxHp + ")");
            player.decrementPoison();
            updateBars();
            if (player.isFainted()) {
                appendLog(player.name + " fainted from poison! You lose.");
                JOptionPane.showMessageDialog(this, "You lost! 💀");
                dispose();
                return;
            }
        }
        if (opponent.isPoisoned()) {
            int dmg = Math.max(1, (int) Math.ceil(opponent.maxHp * 0.10));
            opponent.currentHp = Math.max(0, opponent.currentHp - dmg);
            appendLog(opponent.name + " took " + dmg + " poison damage! (HP: " + opponent.currentHp + "/" + opponent.maxHp + ")");
            opponent.decrementPoison();
            updateBars();
            if (opponent.isFainted()) {
                appendLog(opponent.name + " fainted from poison! You win!");
                JOptionPane.showMessageDialog(this, "You won! 🎉");
                dispose();
            }
        }
    }

    private int calculateDamage(int atk, int def, int movePower) {

        double level = 50; // assume both are around level 50
        double baseDamage = (((2 * level / 5 + 2) * movePower * (atk / (double) def)) / 50) + 2;

        baseDamage *= (0.85 + rnd.nextDouble() * 0.15);

        int dmg = (int) Math.round(baseDamage);

        if (rnd.nextDouble() < 0.1) {
            dmg = (int) (dmg * 1.5);
            appendLog("Critical hit!");
        }

        return Math.max(dmg, 1);
    }


    private void enablePlayerMoves(boolean enable) {
        for (JButton b : moveButtons) {
            if (b != null) b.setEnabled(enable);
        }
    }

    private void updateBars() {
        playerHpBar.setMaximum(player.maxHp); playerHpBar.setValue(player.currentHp);
        playerHpBar.setString(hpString(player.currentHp, player.maxHp));
        opponentHpBar.setMaximum(opponent.maxHp); opponentHpBar.setValue(opponent.currentHp);
        opponentHpBar.setString(hpString(opponent.currentHp, opponent.maxHp));
    }

    private String hpString(int cur, int max) {
        return String.format("HP: %d / %d", cur, max);
    }

    private void appendLog(String s) {
        logArea.append(s + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    private String difficultyText() {
        switch (difficulty) {
            case 0: return "Easy";
            case 1: return "Medium";
            default: return "Hard";
        }
    }

    // AI move selection heuristic
    private int chooseAiMove(BattlePokemon cpu, BattlePokemon player, int difficulty) {
        if (cpu.moves.isEmpty()) return -1;
        if (difficulty == 0) { // easy - random
            return rnd.nextInt(cpu.moves.size());
        }

        int strongest = 0;
        int maxPow = -1;
        for (int i = 0; i < cpu.moves.size(); i++) {
            int p = cpu.moves.get(i).getValue();
            if (p > maxPow) { maxPow = p; strongest = i; }
        }
        if (difficulty == 1) { // medium

            if ((double)player.currentHp / player.maxHp > 0.7) {
                for (int i = 0; i < cpu.moves.size(); i++) {
                    String n = cpu.moves.get(i).getKey().toLowerCase();
                    if (n.contains("paral") || n.contains("poison") || n.contains("sleep")) return i;
                }
            }

            return rnd.nextDouble() < 0.7 ? strongest : rnd.nextInt(cpu.moves.size());
        }

        if (difficulty == 2) {

            if ((double)player.currentHp / player.maxHp < 0.25) return strongest;

            for (int i = 0; i < cpu.moves.size(); i++) {
                String n = cpu.moves.get(i).getKey().toLowerCase();
                if (n.contains("paral") || n.contains("poison") || n.contains("sleep")) return i;
            }
            return strongest;
        }
        return 0;
    }

    private void loadImageIntoLabel(JLabel label, String imgName) {
        if (imgName == null) {
            label.setText("No Image");
            label.setForeground(Color.WHITE);
            return;
        }
        ImageIcon icon = null;
        try {
            java.net.URL res = getClass().getResource("/Images/" + imgName);
            if (res != null) icon = new ImageIcon(res);
            else {
                java.io.File f = new java.io.File("images/" + imgName);
                if (f.exists()) icon = new ImageIcon(f.getAbsolutePath());
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        if (icon == null) {
            label.setText("No Image");
            label.setForeground(Color.WHITE);
            label.setHorizontalAlignment(SwingConstants.CENTER);
        } else {
            Image im = icon.getImage().getScaledInstance(260, 200, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(im));
        }
    }

    private class BattlePokemon {
        String name;
        int maxHp;
        int currentHp;
        int attack;
        int defence;
        int speed;
        String imagePath;
        ArrayList<Map.Entry<String,Integer>> moves = new ArrayList<>();

        // status trackers
        int poisonTurns = 0;
        int paralysisTurns = 0;
        int sleepTurns = 0;

        BattlePokemon(pokemon base, int idx) {
            try {
                this.name = base.getName();
                this.maxHp = Math.max(1, base.getHp());
                this.currentHp = this.maxHp;
                this.attack = base.getAttackPower();
                this.defence = base.getDefencePower();
                this.speed = base.getSpeed();
                try { this.imagePath = data.imagePath.get(idx); } catch (Exception e) { this.imagePath = null; }
                try {
                    ArrayList<Map.Entry<String,Integer>> m = base.getMoves();
                    if (m != null) this.moves.addAll(m);
                } catch (Exception ex) { /* ignore */ }
            } catch (Exception ex) {
                this.name = "Unknown";
                this.maxHp = 50;
                this.currentHp = 50;
                this.attack = 10;
                this.defence = 10;
                this.speed = 10;
                this.imagePath = null;
            }
        }

        boolean isPoisoned() { return poisonTurns > 0; }
        boolean isParalyzed() { return paralysisTurns > 0; }
        boolean isAsleep() { return sleepTurns > 0; }
        boolean isFainted() { return currentHp <= 0; }

        void applyPoison(int turns) { poisonTurns = Math.max(poisonTurns, turns); }
        void applyParalysis(int turns) { paralysisTurns = Math.max(paralysisTurns, turns); }
        void applySleep(int turns) { sleepTurns = Math.max(sleepTurns, turns); }

        void decrementPoison() { if (poisonTurns > 0) poisonTurns--; }
        void decrementParalysis() { if (paralysisTurns > 0) paralysisTurns--; }
        void decrementSleep() { if (sleepTurns > 0) sleepTurns--; }

        boolean paralysisSkipThisTurn() { return rnd.nextDouble() < 0.5; }
    }
}

