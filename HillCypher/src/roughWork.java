public class roughWork {
    public static void main(String[] args) {
        int [][] mat = new int[3][3];
//        System.out.println(mat.length);
//        System.out.println(mat[0].length);
        for(int i = 0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

        String n = "Avi";
        String m = "nashavi";
        System.out.println(n+ m);
        System.out.println(m.substring(3,6));
    }
}
