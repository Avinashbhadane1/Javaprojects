public class ProblemSolution {
    public static int[] matMul3X3into3X1 (int [][]a,int [] b ){
        int [] res = new int[3];
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                res[i] += a[i][j]*b[j];
                res[i] = res[i]%26;
            }
        }
        return res;
    }
    public static int modInv(int a) {
        a = ((a % 26) + 26) % 26;
        for (int x = 1; x < 26; x++) {
            if ((a * x) % 26 == 1) return x;
        }
        throw new RuntimeException("No modular inverse");
    }

    public static int[][] inv3X3(int[][] a) {
        int[][] cof = new int[3][3];

        int det =
                a[0][0]*(a[1][1]*a[2][2] - a[1][2]*a[2][1])
                        - a[0][1]*(a[1][0]*a[2][2] - a[1][2]*a[2][0])
                        + a[0][2]*(a[1][0]*a[2][1] - a[1][1]*a[2][0]);

        det = ((det % 26) + 26) % 26;
        int detInv = modInv(det);

        cof[0][0] =  (a[1][1]*a[2][2] - a[1][2]*a[2][1]);
        cof[0][1] = -(a[1][0]*a[2][2] - a[1][2]*a[2][0]);
        cof[0][2] =  (a[1][0]*a[2][1] - a[1][1]*a[2][0]);

        cof[1][0] = -(a[0][1]*a[2][2] - a[0][2]*a[2][1]);
        cof[1][1] =  (a[0][0]*a[2][2] - a[0][2]*a[2][0]);
        cof[1][2] = -(a[0][0]*a[2][1] - a[0][1]*a[2][0]);

        cof[2][0] =  (a[0][1]*a[1][2] - a[0][2]*a[1][1]);
        cof[2][1] = -(a[0][0]*a[1][2] - a[0][2]*a[1][0]);
        cof[2][2] =  (a[0][0]*a[1][1] - a[0][1]*a[1][0]);

        int[][] inv = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                inv[j][i] = ((cof[i][j] * detInv) % 26 + 26) % 26;
            }
        }
        return inv;
    }
    public static int[][] keyMaker (String keyword){
        //keyword will always be of length9
        keyword = keyword.toUpperCase();
        int [][] result = new int[3][3];
        int len = 0;
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                int c = keyword.charAt(len)-'A';
                result[i][j] = c;
                len++;
            }
        }
        return result;
    }
    public static String hillCipher3X3(String keyword, String cipherText) {

        int[][] key = keyMaker(keyword);

        cipherText = cipherText.toUpperCase();
        StringBuilder sb = new StringBuilder(cipherText);

        while (sb.length() % 3 != 0) {
            sb.append('X');
        }

        cipherText = sb.toString();
        int blocks = cipherText.length() / 3;
        String[] arr = new String[blocks];

        for (int i = 0; i < blocks; i++) {
            arr[i] = cipherText.substring(i * 3, i * 3 + 3);
        }

        int[][] keyInv = inv3X3(key);
        StringBuilder plainText = new StringBuilder();

        for (String s : arr) {
            int[] b = {
                    s.charAt(0) - 'A',
                    s.charAt(1) - 'A',
                    s.charAt(2) - 'A'
            };

            int[] res = matMul3X3into3X1(keyInv, b);

            for (int v : res) {
                plainText.append((char) (v + 'A'));
            }
        }

        return plainText.toString();
    }

    public static void main(String[] args) {
        String res = hillCipher3X3("BCDABEAAB","RYTYWXGUDTQTUCTKSTCVZ");
        System.out.println(res);
    }
}
