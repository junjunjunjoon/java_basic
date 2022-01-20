package Game;

import javax.swing.*;

public class ThreadStatus extends Thread{

    JLabel target1,target2,target3;

    public ThreadStatus(JLabel t1, JLabel t2, JLabel t3){
        target1 = t1;
        target2 = t2;
        target3 = t3;
    }

    public void run(){
        while(true) {
            try {
                target1.setText("이  름 : \t" + Character.player.name);
                target2.setText("체  력 : \t" + Integer.toString(Character.player.hp));
                target3.setText("선원수 : \t" + Integer.toString(Character.player.crew));
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

}

