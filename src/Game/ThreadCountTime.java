package Game;

import javax.swing.*;

public class ThreadCountTime extends Thread {

    JLabel target1, target2;
    static boolean noontime = true;
    static boolean black_market = true; //암시장 항상 열리게 되어있음
    static int howmanydays = 1;
    static int count_for_time = 0;
    static boolean stop = false;
    static boolean work = true;
    // 아이템에 보관일수 추가해서 보관일수가 달라지면 다른 상품으로 분류해야함. 보관일수까지 체크해서 같으면 같이 보관
    // 다르면 다른 상품으로 분류해서 배열에 넣어야함.
    // 우선 1일,2일,3일로 나눠서 분류. 나중에는 더 기간을 길게 잡아서 분류가 많아지지않도록 해야함

    public ThreadCountTime(JLabel t1, JLabel t2) {
        target1 = t1;
        target2 = t2;
    }

    public void run() {
        int n = 0;
        target1.setText("낮");
        while (!stop) {
            if (work) {
                count_for_time++;
                if (count_for_time == 15) { //30으로 바꿔야함
                    noontime = false;
                } else if (count_for_time == 30) { // 60으로 바꿔야함
                    noontime = true;
//                    black_market = false; //암시장 항상 열리게 되어있음
                    howmanydays++;
                    count_for_time = 0;
                } else if (count_for_time == 20) { // 45로 바꿔야함
                    black_market = true;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                target2.setText(Integer.toString(howmanydays) + "days");
                if (noontime == true) {
                    target1.setText("낮");
                } else if (noontime == false) {
                    target1.setText("밤");
                    if (black_market == true) {
                        target1.setText("밤 (암시장 개장)");
                    }
                }
            } else {
                Thread.yield();
            }
        }
    }
}
