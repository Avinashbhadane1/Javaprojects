import java.util.*;

public class level2Logic {
    int [] scores;

    public String decoder(String string, int shift){
        StringBuilder decoded = new StringBuilder();
        for (char c : string.toCharArray()) {
            if (Character.isUpperCase(c)) {
                char shifted = (char) ((c - 'A' - shift + 26) % 26 + 'A');
                decoded.append(shifted);
            } else if (Character.isLowerCase(c)) {
                char shifted = (char) ((c - 'a' - shift + 26) % 26 + 'a');
                decoded.append(shifted);
            } else {
                decoded.append(c); // Leave numbers, punctuation, spaces, etc., as-is
            }
        }
        return decoded.toString();
    }
    public String [] checker(String [] strings){
        String [] list = new String[strings.length];
        String string;
        scores = new int[strings.length];
        String [] work;
        int [] words = new int[26];
        for(int i =0;i<26;i++){
            words[i] = 0;
        }
        for(int i = 0;i<strings.length;i++){
            scores[i] = 0;
            string = strings[i];
            for(int j = 0;j<strings[i].length();j++) {
                work = new String[string.length()];
                work[j] = String.valueOf(string.charAt(j));
                work[j] = work[j].toUpperCase();
                if(Objects.equals(work[j], "A")){
                    words[0]++;
                }else if(Objects.equals(work[j],"B")){
                    words[1]++;
                }else if(Objects.equals(work[j],"C")){
                    words[2]++;
                }else if(Objects.equals(work[j],"D")){
                    words[3]++;
                }else if(Objects.equals(work[j],"E")){
                    words[4]++;
                }else if(Objects.equals(work[j],"F")){
                    words[5]++;
                }else if(Objects.equals(work[j],"G")){
                    words[6]++;
                }else if(Objects.equals(work[j],"H")){
                    words[7]++;
                }else if(Objects.equals(work[j],"I")){
                    words[8]++;
                }else if(Objects.equals(work[j],"J")){
                    words[9]++;
                }else if(Objects.equals(work[j],"K")){
                    words[10]++;
                }else if(Objects.equals(work[j],"L")){
                    words[11]++;
                }else if(Objects.equals(work[j],"M")){
                    words[12]++;
                }else if(Objects.equals(work[j],"N")){
                    words[13]++;
                }else if(Objects.equals(work[j],"O")){
                    words[14]++;
                }else if(Objects.equals(work[j],"P")){
                    words[15]++;
                }else if(Objects.equals(work[j],"Q")){
                    words[16]++;
                }else if(Objects.equals(work[j],"R")){
                    words[17]++;
                }else if(Objects.equals(work[j],"S")){
                    words[18]++;
                }else if(Objects.equals(work[j],"T")){
                    words[19]++;
                }else if(Objects.equals(work[j],"U")){
                    words[20]++;
                }else if(Objects.equals(work[j],"V")){
                    words[21]++;
                }else if(Objects.equals(work[j],"W")){
                    words[22]++;
                }else if(Objects.equals(work[j],"X")){
                    words[23]++;
                }else if(Objects.equals(work[j],"Y")){
                    words[24]++;
                }else if(Objects.equals(work[j],"Z")){
                    words[25]++;
                }
            }
            if(((double) words[0] /strings[i].length())*100 <= 8.5 && ((double)words[0]/strings[i].length())*100 >= 7.8){
                scores[i] += 4;
            }if(((double) words[1] /strings[i].length())*100 <= 1.8 && ((double)words[1]/strings[i].length())*100 >= 1.1){
                scores[i] += 4;
            }if(((double) words[2] /strings[i].length())*100 <= 3.2 && ((double)words[2]/strings[i].length())*100 >= 2.4){
                scores[i] += 4;
            }if(((double) words[3] /strings[i].length())*100 <= 4.7 && ((double)words[3]/strings[i].length())*100 >= 4.0){
                scores[i] += 4;
            }if(((double) words[4] /strings[i].length())*100 <= 13.0 && ((double)words[4]/strings[i].length())*100 >= 11.3){
                scores[i] += 4;
            }if(((double) words[5] /strings[i].length())*100 <= 2.6 && ((double)words[5]/strings[i].length())*100 >= 1.8){
                scores[i] += 4;
            }if(((double) words[6] /strings[i].length())*100 <= 2.5 && ((double)words[6]/strings[i].length())*100 >= 1.6){
                scores[i] += 4;
            }if(((double) words[7] /strings[i].length())*100 <= 6.5 && ((double)words[7]/strings[i].length())*100 >= 5.7){
                scores[i] += 4;
            }if(((double) words[8] /strings[i].length())*100 <= 7.7 && ((double)words[8]/strings[i].length())*100 >= 6.2){
                scores[i] += 4;
            }if(((double) words[9] /strings[i].length())*100 <= 0.25 && ((double)words[9]/strings[i].length())*100 >= 0.07){
                scores[i] += 4;
            }if(((double) words[10] /strings[i].length())*100 <= 1.17 && ((double)words[10]/strings[i].length())*100 >= 0.81){
                scores[i] += 4;
            }if(((double) words[11] /strings[i].length())*100 <= 4.3 && ((double)words[11]/strings[i].length())*100 >= 3.5){
                scores[i] += 4;
            }if(((double) words[12] /strings[i].length())*100 <= 2.7 && ((double)words[12]/strings[i].length())*100 >= 2.0){
                scores[i] += 4;
            }if(((double) words[13] /strings[i].length())*100 <= 7.2 && ((double)words[13]/strings[i].length())*100 >= 6.2){
                scores[i] += 4;
            }if(((double) words[14] /strings[i].length())*100 <= 7.8 && ((double)words[14]/strings[i].length())*100 >= 7.0){
                scores[i] += 4;
            }if(((double) words[15] /strings[i].length())*100 <= 2.4 && ((double)words[15]/strings[i].length())*100 >= 1.4){
                scores[i] += 4;
            }if(((double) words[16] /strings[i].length())*100 <= 0.25 && ((double)words[16]/strings[i].length())*100 >= 0.081){
                scores[i] += 4;
            }if(((double) words[17] /strings[i].length())*100 <= 6.5 && ((double)words[17]/strings[i].length())*100 >= 5.5){
                scores[i] += 4;
            }if(((double) words[18] /strings[i].length())*100 <= 7.0 && ((double)words[18]/strings[i].length())*100 >= 5.8){
                scores[i] += 4;
            }if(((double) words[19] /strings[i].length())*100 <= 9.5 && ((double)words[19]/strings[i].length())*100 >= 8.0){
                scores[i] += 4;
            }if(((double) words[20] /strings[i].length())*100 <= 3.3 && ((double)words[20]/strings[i].length())*100 >= 2.3){
                scores[i] += 4;
            }if(((double) words[21] /strings[i].length())*100 <= 1.20 && ((double)words[21]/strings[i].length())*100 >= 0.78){
                scores[i] += 4;
            }if(((double) words[22] /strings[i].length())*100 <= 2.9 && ((double)words[22]/strings[i].length())*100 >= 1.9){
                scores[i] += 4;
            }if(((double) words[23] /strings[i].length())*100 <= 0.20 && ((double)words[23]/strings[i].length())*100 >= 0.10){
                scores[i] += 4;
            }if(((double) words[24] /strings[i].length())*100 <= 2.5 && ((double)words[24]/strings[i].length())*100 >= 1.5){
                scores[i] += 4;
            }if(((double) words[25] /strings[i].length())*100 <= 0.1 && ((double)words[25]/strings[i].length())*100 >= 0.054){
                scores[i] += 4;
            }

            if(string.contains("the ")){
                scores[i] += 50;
            }if(string.contains("be ")){
                scores[i] += 50;
            }if(string.contains("to ")){
                scores[i] += 50;
            }if(string.contains("of ")){
                scores[i] += 50;
            }if(string.contains("and ")){
                scores[i] += 50;
            }if(string.contains(" a ")){
                scores[i] += 50;
            }if(string.contains("am ")){
                scores[i] += 50;
            }if(string.contains("in ")){
                scores[i] += 50;
            }if(string.contains("that ")){
                scores[i] += 50;
            }if(string.contains("have ")){
                scores[i] += 50;
            }if(string.contains("I ")){
                scores[i] += 50;
            }if(string.contains("it ")){
                scores[i] += 50;
            }if(string.contains("for ")){
                scores[i] += 50;
            }if(string.contains("not ")){
                scores[i] += 50;
            }if(string.contains("on ")){
                scores[i] += 50;
            }if(string.contains("with ")){
                scores[i] += 50;
            }if(string.contains("he ")){
                scores[i] += 50;
            }if(string.contains("as ")){
                scores[i] += 50;
            }if(string.contains("you ")){
                scores[i] += 50;
            }if(string.contains("do ")){
                scores[i] += 50;
            }if(string.contains("at ")){
                scores[i] += 50;
            }if(string.contains("this ")){
                scores[i] += 50;
            }if(string.contains("but ")){
                scores[i] += 50;
            }if(string.contains("his ")){
                scores[i] += 50;
            }if(string.contains("by ")){
                scores[i] += 50;
            }if(string.contains("from ")){
                scores[i] += 50;
            }if(string.contains("they ")){
                scores[i] += 50;
            }if(string.contains("we ")){
                scores[i] += 50;
            }if(string.contains(" say ")){
                scores[i] += 50;
            }if(string.contains(" her ")){
                scores[i] += 50;
            }if(string.contains(" she ")){
                scores[i] += 50;
            }if(string.contains(" or ")){
                scores[i] += 50;
            }if(string.contains(" an ")){
                scores[i] += 50;
            }if(string.contains(" will ")){
                scores[i] += 50;
            }if(string.contains(" my ")){
                scores[i] += 50;
            }if(string.contains(" one ")){
                scores[i] += 50;
            }if(string.contains("all ")){
                scores[i] += 50;
            }if(string.contains("would ")){
                scores[i] += 50;
            }if(string.contains("there ")){
                scores[i] += 50;
            }if(string.contains("their ")){
                scores[i] += 50;
            }if(string.contains("what ")){
                scores[i] += 50;
            }if(string.contains("so ")){
                scores[i] += 50;
            }if(string.contains(" up ")){
                scores[i] += 50;
            }if(string.contains("out ")){
                scores[i] += 50;
            }if(string.contains("if ")){
                scores[i] += 50;
            }if(string.contains("about ")){
                scores[i] += 50;
            }if(string.contains("who ")){
                scores[i] += 50;
            }if(string.contains("get ")){
                scores[i] += 50;
            }if(string.contains("which ")){
                scores[i] += 50;
            }if(string.contains("go ")){
                scores[i] += 50;
            }if(string.contains("me ")){
                scores[i] += 50;
            }if(string.contains("when ")){
                scores[i] += 50;
            }if(string.contains("make ")){
                scores[i] += 50;
            }if(string.contains("can ")){
                scores[i] += 50;
            }if(string.contains("no ")){
                scores[i] += 50;
            }if(string.contains("just ")){
                scores[i] += 50;
            }if(string.contains("him ")){
                scores[i] += 50;
            }if(string.contains("know ")){
                scores[i] += 50;
            }if(string.contains("take ")){
                scores[i] += 50;
            }if(string.contains("people ")){
                scores[i] += 50;
            }if(string.contains("into ")){
                scores[i] += 50;
            }if(string.contains("year ")){
                scores[i] += 50;
            }if(string.contains("your ")){
                scores[i] += 50;
            }if(string.contains("good ")){
                scores[i] += 50;
            }if(string.contains("some ")){
                scores[i] += 50;
            }if(string.contains("would ")){
                scores[i] += 50;
            }if(string.contains("could ")){
                scores[i] += 50;
            }if(string.contains("them ")){
                scores[i] += 50;
            }if(string.contains("see ")){
                scores[i] += 50;
            }if(string.contains("other ")){
                scores[i] += 50;
            }if(string.contains("than ")){
                scores[i] += 50;
            }if(string.contains("then ")){
                scores[i] += 50;
            }if(string.contains("now ")){
                scores[i] += 50;
            }if(string.contains("look ")){
                scores[i] += 50;
            }if(string.contains("only ")){
                scores[i] += 50;
            }if(string.contains("come ")){
                scores[i] += 50;
            }if(string.contains("it's ")){
                scores[i] += 50;
            }if(string.contains("its ")){
                scores[i] += 50;
            }if(string.contains("think ")){
                scores[i] += 50;
            }if(string.contains("also ")){
                scores[i] += 50;
            }if(string.contains("back ")){
                scores[i] += 50;
            }if(string.contains("after ")){
                scores[i] += 50;
            }if(string.contains("use ")){
                scores[i] += 50;
            }if(string.contains("two ")){
                scores[i] += 50;
            }if(string.contains("how ")){
                scores[i] += 50;
            }if(string.contains("our ")){
                scores[i] += 50;
            }if(string.contains("first ")){
                scores[i] += 50;
            }if(string.contains("well ")){
                scores[i] += 50;
            }if(string.contains("way ")){
                scores[i] += 50;
            }if(string.contains("even ")){
                scores[i] += 50;
            }if(string.contains("new ")){
                scores[i] += 50;
            }if(string.contains("want ")){
                scores[i] += 50;
            }if(string.contains("because ")){
                scores[i] += 50;
            }if(string.contains("any ")){
                scores[i] += 50;
            }if(string.contains("these ")){
                scores[i] += 50;
            }if(string.contains("give ")){
                scores[i] += 50;
            }if(string.contains("day ")){
                scores[i] += 50;
            }if(string.contains("most ")){
                scores[i] += 50;
            }if(string.contains("us ")){
                scores[i] += 50;
            }if(string.contains("do ")){
                scores[i] += 50;
            }if(string.contains("into ")){
                scores[i] += 50;
            }if(string.contains("is ")){
                scores[i] += 50;
            }
            if(((double) words[25] /string.length())*100 > 5.0){
                scores[i] -= 10;
            }
            if(((double) words[9] /string.length())*100 > 10.0){
                scores[i] -= 10;
            }
            if(((double) words[16] /string.length())*100 > 10.0){
                scores[i] -= 10;
            }
            if(((double) words[23] /string.length())*100 > 6.0){
                scores[i] -= 10;
            }
            if(((double) words[1] /string.length())*100 > 10.0){
                scores[i] -= 10;
            }
            if(((double) words[15] /string.length())*100 > 10.0){
                scores[i] -= 10;
            }
            if(((double) words[10] /string.length())*100 > 10.0){
                scores[i] -= 10;
            }
            if(((double) words[21] /string.length())*100 > 10.0){
                scores[i] -= 10;
            }
            for(int k =0;k<26;k++){
                words[k] = 0;
            }
        }
        List<Map.Entry<String, Integer>> sort = new ArrayList<>();
        for(int i = 0;i< strings.length;i++){
            sort.add(new AbstractMap.SimpleEntry<>(strings[i],scores[i]));
        }
        sort.sort((a,b) -> b.getValue()-a.getValue());
        for(int i = 0;i<sort.size();i++){
            Map.Entry<String,Integer> entry = sort.get(i);
            System.out.println(entry.getKey() + " - "  + entry.getValue());
            list[i] = entry.getKey();
        }
        return list;
    }
}
