import javax.swing.*;

public class TestCountTime extends Thread {

    JLabel target;
    static boolean noontime = true;
    static boolean black_market = false;
    static int howmanydays = 0;
    static boolean stop = true;
    // 아이템에 보관일수 추가해서 보관일수가 달라지면 다른 상품으로 분류해야함. 보관일수까지 체크해서 같으면 같이 보관
    // 다르면 다른 상품으로 분류해서 배열에 넣어야함.
    // 우선 1일,2일,3일로 나눠서 분류. 나중에는 더 기간을 길게 잡아서 분류가 많아지지않도록 해야함

    public TestCountTime(JLabel t) {
        target = t;
    }

    public void run() {
        int n = 0;
        int count = 0;
        target.setText("낮");
        while(true) {
            while (stop == true) {
                try {
                    Thread.sleep(1000);
                    count++;
                    if (count == 5) { //30으로 바꿔야함
                        noontime = false;
                    } else if (count == 10) { // 60으로 바꿔야함
                        noontime = true;
                        black_market = false;
                        howmanydays++;
                        count = 0;
                    } else if (count == 8) { // 45로 바꿔야함
                        black_market = true;
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                if (noontime == true) {
                    target.setText("낮");
                } else if (noontime == false) {
                    target.setText("밤");
                    if (black_market == true) {
                        target.setText("밤 (암시장 개장)");
                    }
                }
            }
        }
    }
}
