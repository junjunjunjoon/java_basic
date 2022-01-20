package Game;

import javax.swing.*;

public class ThreadJFrame extends JFrame {

    JTextArea ta;
    JLabel lb_noontime, lb_windDirection, lb_charName, lb_charHp, lb_charCrew, lb_days;
    String name;
    int hp;
    int crew;

    public ThreadJFrame(){
        super("정보 출력창");
        setSize(300,700);
        setLocation(1230,100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//x버튼 누르면 창닫기가능하게
        setLayout(null);//데이터를 어떻게 붙일지 방향을 결정

        this.name = name;
        this.hp = hp;
        this.crew = crew;

        lb_noontime = new JLabel();
        lb_windDirection = new JLabel();
        lb_charName = new JLabel();
        lb_charHp = new JLabel();
        lb_charCrew = new JLabel();
        lb_days = new JLabel();

        ta = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JMenuBar mb = new JMenuBar();

        //밤,낮 시간
        lb_days.setBounds(30,0,200,100); // 위치 지정
        lb_days.setFont(lb_noontime.getFont().deriveFont(17.0f)); // 폰트 크기 조정
        lb_noontime.setBounds(30,40,200,100); // 위치 지정
        lb_noontime.setFont(lb_noontime.getFont().deriveFont(20.0f)); // 폰트 크기 조정

        //풍향
        lb_windDirection.setBounds(30,70,200,100); // 위치 지정
        lb_windDirection.setFont(lb_windDirection.getFont().deriveFont(20.0f)); // 폰트 크기 조정

        //캐릭터 정보
        lb_charName.setBounds(30,140,200,100); // 위치 지정
        lb_charName.setFont(lb_windDirection.getFont().deriveFont(15.0f)); // 폰트 크기 조정
        lb_charHp.setBounds(30,170,200,100); // 위치 지정
        lb_charHp.setFont(lb_windDirection.getFont().deriveFont(15.0f)); // 폰트 크기 조정
        lb_charCrew.setBounds(30,200,200,100); // 위치 지정
        lb_charCrew.setFont(lb_windDirection.getFont().deriveFont(15.0f)); // 폰트 크기 조정
        scrollPane.setBounds(15,300,260,350);

        this.add(scrollPane);
        scrollPane.setVisible(true);
        this.add(lb_noontime);
        this.add(lb_days);
        this.add(lb_windDirection);
        this.add(lb_charName);
        this.add(lb_charHp);
        this.add(lb_charCrew);
        this.setJMenuBar(mb);

        new ThreadCountTime(lb_noontime, lb_days).start(); // 밤,낮 출력
        new ThreadWindDirection(lb_windDirection).start(); //풍향 출력
        new ThreadStatus(lb_charName,lb_charHp,lb_charCrew).start(); //캐릭터 상태 출력
    }
}

