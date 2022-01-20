package Game;

import java.util.Random;

public class ThreadChart extends Thread {

    Random random = new Random();
    static boolean status = false;
    static int[] howmany_pirates = new int[4];
//    static int[] whom = new int[4];

    public void run() {
        status = true;

        //System.out.println("스레드 실행 됨");
        for (int i = 0; i < 4; i++) {
            howmany_pirates[i] = random.nextInt(3);
        }
//        for (int i = 0; i < 4; i++) {
//            whom[i] = random.nextInt(3)+1;
//        }

        try {
            Thread.sleep(60000); // JFrame에 띄울지 고민
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        status = false;
    }

    public void close(){
        this.interrupt();
    }
}
