package d3.protonmanexe;

import java.util.Random;

public class TestOnly {

    public static void main(String[] args) {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();

        while(sb.length() < 8){
            int x = r.nextInt();
            System.out.println("x is " +x);
            String h = Integer.toHexString(x);
            System.out.println("h is " +h);
            sb.append(h);
        }
        System.out.println("sb is " +sb);
    }
}
