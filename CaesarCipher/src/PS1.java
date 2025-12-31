class Encryption {
    String string;
    int n;
    boolean toDecode = true;

    public String toEncode(String string, int n) {
        this.string = string;
        this.n = n;
        StringBuilder encoded = new StringBuilder();
        for (char c : string.toCharArray()) {
            if (Character.isUpperCase(c)) {
                char shifted = (char) ((c - 'A' + n) % 26 + 'A');
                encoded.append(shifted);
            } else if (Character.isLowerCase(c)) {
                char shifted = (char) ((c - 'a' + n) % 26 + 'a');
                encoded.append(shifted);
            } else {
                encoded.append(c); // Leave numbers, punctuation, spaces, etc., as-is
            }
        }
        return encoded.toString();
    }

    public String toDecode(String string,int n){
        this.string= string;
        this.n=n;
        StringBuilder decoded = new StringBuilder();
        if(toDecode) {
            for (char c : string.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    char shifted = (char) ((c - 'A' - n + 26) % 26 + 'A');
                    decoded.append(shifted);
                } else if (Character.isLowerCase(c)) {
                    char shifted = (char) ((c - 'a' - n + 26) % 26 + 'a');
                    decoded.append(shifted);
                } else {
                    decoded.append(c); // Leave numbers, punctuation, spaces, etc., as-is
                }
            }
        }else{
            System.out.println("none");
            decoded.append("");
        }
        return decoded.toString();
    }
}

public class PS1 {
    public static void main(String[] args) {
            new UserInterface();
    }
}

