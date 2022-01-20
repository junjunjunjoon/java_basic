import javax.swing.*;
import java.util.Random;

public class TestWindDirection extends Thread {

    JLabel target;
    Random random = new Random();
    //int[] wind_direction = new int[4];
    static int wind_result = 7;
    // 북부터 시계 반대방향으로 1,2,3,4,5,6,7,8 이고 9는 무풍
    int count = 0;

    public TestWindDirection(JLabel t) {
        target = t;
    }

    public void run() {
        target.setText("북서풍");
        while (true) {
            try {
                int max = 0, max_second = 0;
                int maxIndex = 0, maxIndex_second = 0;
                int[] wind_direction = new int[4];

                for (int i = 0; i < wind_direction.length; i++) { // 값 넣기
                    int a = random.nextInt(4);
                    wind_direction[i] = a;
                }

                //test
//                for (int i = 0; i < wind_direction.length; i++) { // 값 출력
//                    System.out.print(wind_direction[i]);
//                }
//                System.out.println("");

                for (int i = 0; i < wind_direction.length; i++) { // 가장 큰 값 가져오기
                    if (wind_direction[i] > max) {
                        max = wind_direction[i];
                        maxIndex = i;
                    }
                }
                wind_direction[maxIndex] = 0; // 제일 높은 곳에 0을 넣어라.

                //test
//                for (int i = 0; i < wind_direction.length; i++){ // 값 출력
//                    System.out.print(wind_direction[i]);
//                }
//                System.out.println("");

                for (int i = 0; i < wind_direction.length; i++) { // 두번째 큰 값 가져오기
                    if (wind_direction[i] > max_second) {
                        max_second = wind_direction[i];
                        maxIndex_second = i;
                    }
                }

                //test
//              System.out.println("max = " + max + "\tmax_second = "+ max_second);

                if (max == max_second) { // 최대값과 두번째 값이 같은지? 무풍이나 방향이 정해질 수 있음, 방향이 2군데이거나 무풍임
                    if (maxIndex + maxIndex_second == 1 | maxIndex + maxIndex_second == 5) { //동서 동일한지, 남북 동일한지 확인하는 과정
                        wind_result = 9; // 무풍
                    } else if (maxIndex + maxIndex_second == 2) { // 남동
                        wind_result = 6;
                    } else if (maxIndex + maxIndex_second == 3 & maxIndex != 0 & maxIndex_second != 0) { // 남서
                        wind_result = 4;
                    } else if (maxIndex + maxIndex_second == 4) { // 북서
                        wind_result = 2;
                    } else if (maxIndex + maxIndex_second == 3 & (maxIndex == 0 | maxIndex_second == 0)) { // 북동
                        wind_result = 8;
                    }
                } else if (max != max_second) { // 방향이 한군데
                    if (max == 0) {
                        wind_result = 7; // 동
                    } else if (max == 1) {
                        wind_result = 3; // 서
                    } else if (max == 2) {
                        wind_result = 5; // 남
                    } else if (max == 3) {
                        wind_result = 1; // 북
                    }
                }
                Thread.sleep(3000); // 바뀌는 시간 변경해줘야함

                if (wind_result == 1) {
                    target.setText("북풍");
                } else if (wind_result == 2) {
                    target.setText("북서풍");
                } else if (wind_result == 3) {
                    target.setText("서풍");
                } else if (wind_result == 4) {
                    target.setText("남서풍");
                } else if (wind_result == 5) {
                    target.setText("남풍");
                } else if (wind_result == 6) {
                    target.setText("남동풍");
                } else if (wind_result == 7) {
                    target.setText("동풍");
                } else if (wind_result == 8) {
                    target.setText("북동풍");
                } else if (wind_result == 9) {
                    target.setText("무풍");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

