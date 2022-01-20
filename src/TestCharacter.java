import java.util.Scanner;

public class TestCharacter {

    static String name;
    static int hp;
    static int crew;

    public TestCharacter() {
        name = "";
        hp = 0;
        crew = 100; // 50
    }

    static public int get_char_hp() {
        return hp;
    }
    static public void set_char_hp(int input_hp)
    {
        hp = input_hp;
    }

    static public int attack() {
        int sum = 0; // 데미지 총 합
        Scanner scan = new Scanner(System.in);

        System.out.println("1. 경궁 공격");
        System.out.println("2. 화궁 공격");
        System.out.println("3. 회유 하기");
        System.out.println("4. 도망 가기");
        int input = scan.nextInt();

        // 경궁 공격
        if (input == 1) {
            sum = 10;
        }
        return sum;
    }

    static public void attacked(int sum) {
        hp = hp - sum;
    }
}
