import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.*;
import java.util.*;

class pokemon{
    private int id;
    private String name;
    private String type;
    private int hp;
    private int attackPower;
    private int defencePower;
    private int speed;
    private ArrayList<Map.Entry<String,Integer>> moves = new ArrayList<>();
    public JLabel pokemonSetter(JLabel label,String ImagePath,String name){
        label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(ImagePath));
        icon.setDescription(ImagePath);
        label.setIcon(icon);
        Image image = icon.getImage().getScaledInstance(200, 170, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(image));
        label.setText(name);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }
    public pokemon statSetter(String path,pokemon p)  {
        ArrayList<Map.Entry<String,Integer>> arrayList  = new ArrayList<>();
        File file = new File(path);
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            p.setId(Integer.parseInt(br.readLine()));
            p.setName(br.readLine());
            p.setType(br.readLine());
            p.setHp(Integer.parseInt(br.readLine()));
            p.setAttackPower(Integer.parseInt(br.readLine()));
            p.setDefencePower(Integer.parseInt(br.readLine()));
            p.setSpeed(Integer.parseInt(br.readLine()));
            String moveName;
            while ((moveName = br.readLine()) != null) {
                String valueLine = br.readLine();
                if (valueLine == null) break; // avoid odd leftover
                int value = Integer.parseInt(valueLine);
                arrayList.add(new AbstractMap.SimpleEntry<>(moveName, value));
            }
            p.setMoves(arrayList);
        } catch (Exception e){e.printStackTrace();}
        return p;
    }
    public pokemon(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getDefencePower() {
        return defencePower;
    }

    public void setDefencePower(int defencePower) {
        this.defencePower = defencePower;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public ArrayList<Map.Entry<String, Integer>> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Map.Entry<String, Integer>> moves) {
        this.moves = moves;
    }
    public void erase(pokemon p){
        p.setId(-1);
        p.setName("");
        p.setType("");
        p.setHp(0);
        p.setAttackPower(0);
        p.setDefencePower(0);
        p.setSpeed(0);
        p.setMoves(null);
    }
}
