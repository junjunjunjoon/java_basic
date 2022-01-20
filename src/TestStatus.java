import javax.swing.*;

public class TestStatus extends Thread{

    JLabel target1,target2,target3;
//    String name;
//    int hp;
//    int crew;

    public TestStatus(JLabel t1, JLabel t2, JLabel t3){
        target1 = t1;
        target2 = t2;
        target3 = t3;
//        this.name = name;
//        this.hp = hp;
//        this.crew = crew;
    }

    public void run(){
        while(true) {
            try {
                target1.setText("이  름 : \t" + TestCharacter.name);
                target2.setText("체  력 : \t" + Integer.toString(TestCharacter.get_char_hp()));
                target3.setText("선원수 : \t" + Integer.toString(TestCharacter.crew));
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

}
