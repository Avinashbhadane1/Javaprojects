import java.util.AbstractMap;

public class effectsData implements data{
    effectsData(){
        effects.add(new AbstractMap.SimpleEntry<>("poison", new int[]{25, 3}));
        effects.add(new AbstractMap.SimpleEntry<>("paralysis", new int[]{20, 2}));
        effects.add(new AbstractMap.SimpleEntry<>("sleep", new int[]{10, 2}));
        effects.add(new AbstractMap.SimpleEntry<>("confused", new int[]{15, 2}));
    }
}
