import javax.swing.*;

public class TestJFrame extends JFrame {

    JLabel lb_noontime, lb_windDirection, lb_charName, lb_charHp, lb_charCrew;

    public TestJFrame(){
        super("정보 출력창");
        setSize(300,300);
        setLocation(800,150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x버튼 누르면 창닫기가능하게
        setLayout(null);//데이터를 어떻게 붙일지 방향을 결정

        lb_noontime = new JLabel();
        lb_windDirection = new JLabel();
        lb_charName = new JLabel();
        lb_charHp = new JLabel();
        lb_charCrew = new JLabel();;

        lb_noontime.setBounds(30,10,200,100); // 위치 지정
        lb_noontime.setFont(lb_noontime.getFont().deriveFont(20.0f)); // 폰트 크기 조정

        lb_windDirection.setBounds(30,40,200,100); // 위치 지정
        lb_windDirection.setFont(lb_windDirection.getFont().deriveFont(20.0f)); // 폰트 크기 조정

        lb_charName.setBounds(30,100,200,100); // 위치 지정
        lb_charName.setFont(lb_windDirection.getFont().deriveFont(15.0f)); // 폰트 크기 조정
        lb_charHp.setBounds(30,130,200,100); // 위치 지정
        lb_charHp.setFont(lb_windDirection.getFont().deriveFont(15.0f)); // 폰트 크기 조정
        lb_charCrew.setBounds(30,160,200,100); // 위치 지정
        lb_charCrew.setFont(lb_windDirection.getFont().deriveFont(15.0f)); // 폰트 크기 조정

        this.add(lb_noontime);
        this.add(lb_windDirection);
        this.add(lb_charName);
        this.add(lb_charHp);
        this.add(lb_charCrew);

        new TestCountTime(lb_noontime).start();//멀티스레드로 카운트출력
        new TestWindDirection(lb_windDirection).start();//멀티스레드로 시간 출력
        new TestStatus(lb_charName,lb_charHp,lb_charCrew).start(); //캐릭터 상태 출력

    }

}
