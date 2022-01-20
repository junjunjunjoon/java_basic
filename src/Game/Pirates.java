package Game;

import java.util.Random;

class Pirates extends People {

    double conciliation_def;
    Random random = new Random();

    public Pirates make_pirates_adventure(int stage) {

        Pirates p1_port = new Pirates();
        Pirates p2_port = new Pirates();
        Pirates p3_port = new Pirates();
//        int a = stage;

        p1_port.name = "생계형 여진족 해적";
        p1_port.hp = 300;
        p1_port.power = 2;
        p1_port.avoiding = 0;
        p1_port.money = 6000;
        p1_port.conciliation_def = 0;
        p1_port.crew = 10;

        p2_port.name = "왜구 해적";
        p2_port.hp = 300;
        p2_port.power = 2;
        p2_port.avoiding = 0;
        p2_port.money = 6000;
        p2_port.conciliation_def = 0;
        p2_port.crew = 15;

        p3_port.name = "정체불명의 해적";
        p3_port.hp = 500;
        p3_port.power = 3;
        p3_port.avoiding = 2;
        p3_port.money = 12000;
        p3_port.conciliation_def = 0;
        p3_port.crew = 20;

        if(stage==1) {
            return p1_port;
        } else if ( stage==2) {
            return p2_port;
        } else {
            return p3_port;
        }
    }

    public Pirates make_pirates(int max_stage) {

        Pirates p1_port = new Pirates();
        Pirates p2_port = new Pirates();
        Pirates p3_port = new Pirates();
        int a = random.nextInt(max_stage);

        p1_port.name = "생계형 여진족 해적";
        p1_port.hp = 300;
        p1_port.power = 2;
        p1_port.avoiding = 0;
        p1_port.money = 6000;
        p1_port.conciliation_def = 0;
        p1_port.crew = 10;

        p2_port.name = "왜구 해적";
        p2_port.hp = 300;
        p2_port.power = 2;
        p2_port.avoiding = 0;
        p2_port.money = 6000;
        p2_port.conciliation_def = 0;
        p2_port.crew = 15;

        p3_port.name = "정체불명의 해적";
        p3_port.hp = 500;
        p3_port.power = 3;
        p3_port.avoiding = 2;
        p3_port.money = 12000;
        p3_port.conciliation_def = 0;
        p3_port.crew = 20;

        if(a==0) {
            return p1_port;
        } else if ( a==1) {
            return p2_port;
        } else {
            return p3_port;
        }
    }

    public int attack() {
        return power * crew;
    }

}

