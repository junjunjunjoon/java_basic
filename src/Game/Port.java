package Game;

import java.util.Scanner;

public class Port {

    String[] portname = {"조선 - 부산", "청나라 - 대련", "명나라 - 상해", "일본 - 고베" };
    Scanner scan = new Scanner(System.in);
    static int[] go_often_stage = new int[4];

    public Port(int stage){
        if(stage == 1){
            System.out.println(portname[stage - 1]);
        }
        else if(stage == 2){
            System.out.println(portname[stage - 1]);
        }
        else if(stage == 3){
            System.out.println(portname[stage - 1]);
        }
        else if(stage == 4){
            System.out.println(portname[stage - 1]);
        }
    }

    public int ChoiceDestination (int stage, int max_stage){
        int input = 0;

        if(stage == 1 & max_stage == 1){
            System.out.println("모험을 통해 항로를 확보하세요.");
            return -1;
        }
        else if(stage != 1 & stage == max_stage){
            System.out.println("이동할 목적지를 지정하세요.\t(나가려면 9를 입력하세요.)");
            for (int i = 0; i < stage - 1; i++){
                System.out.println(i+1 + ". " + portname[i]);
            }
            input = scan.nextInt();
            if(input == 9){
                return -1;
            }
        }
        else if(stage < max_stage){
            System.out.println("이동할 목적지를 지정하세요.\t(나가려면 9를 입력하세요.)");
            for (int i = 0; i < max_stage - 1; i++){
                if(i+1 < stage){
                    System.out.println(i+1 + ". " + portname[i]);
                }
                else if(i+1 >= stage){
                    System.out.println(i+1 + ". " + portname[i+1]);
                }
            }
            int tmp = scan.nextInt();
            if(tmp == 9){
                return -1;
            }
            if(tmp < stage){
                input = tmp;
            }
            else if(tmp >= stage){
                input = tmp+1;
            }

        }
        if(input == 9){
            return -1;
        }

        go_often_stage[input - 1]++;
        return input;
    }

    public int ChoiceDestination_Chart (int stage, int max_stage){
        int input = 0;
        //System.out.println("Chart 창으로 들어옴");

        if(stage == 1 & max_stage == 1){
            System.out.println("모험을 통해 항로를 확보하세요.");
            return -1;
        }
        else if(stage != 1 & stage == max_stage){
            System.out.println("이동할 목적지를 지정하세요.\t(나가려면 9를 입력하세요.)");
            for (int i = 0; i < stage - 1; i++){
                System.out.println(i+1 + ". " + portname[i]);
            }
            System.out.println("===============================");
            for (int i = 0; i < stage - 1; i++){
                System.out.println(portname[i] + " | 해적 빈도수 : " + ThreadChart.howmany_pirates[i]);
            }
            input = scan.nextInt();
            if(input == 9){
                return -1;
            }
        }
        else if(stage < max_stage){
            System.out.println("이동할 목적지를 지정하세요.\t(나가려면 9를 입력하세요.)");
            for (int i = 0; i < max_stage - 1; i++){
                if(i+1 < stage){
                    System.out.println(i+1 + ". " + portname[i]);
                }
                else if(i+1 >= stage){
                    System.out.println(i+1 + ". " + portname[i+1]);
                }
            }
            System.out.println("===============================");
            for (int i = 0; i < max_stage - 1; i++){
                if(i+1 < stage){
                    System.out.println(portname[i] + " | 해적 빈도수 : " + ThreadChart.howmany_pirates[i]);
                }
                else if(i+1 >= stage){
                    System.out.println(portname[i+1] + " | 해적 빈도수 : " + ThreadChart.howmany_pirates[i+1]);
                }

            }
            int tmp = scan.nextInt();
            if(tmp == 9){
                return -1;
            }
            if(tmp < stage){
                input = tmp;
            }
            else if(tmp >= stage){
                input = tmp+1;
            }
        }

        go_often_stage[input - 1]++;
        return input;
    }

    public int go_often_stage(){
        int most = go_often_stage[0];
        int most_index = 0;

        for (int i = 0; i < go_often_stage.length; i++){
            if ( go_often_stage[i] > most){
                most = go_often_stage[i];
                most_index = i;
            }
        }
        return most_index;
    }

}

