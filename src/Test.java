import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        TestMusicPlayer introMusic = new TestMusicPlayer("normal.mp3", true);
//        introMusic.start(); // 노래 잠시 중단

        // 캐릭터 임의값
        TestCharacter character = new TestCharacter();
        character.name = "곽민준";
        character.hp = 100;
        character.crew = 50;

        System.out.println("노래 나온다!!");

        TestJFrame makeFrame = new TestJFrame();
        TestPiratesAttack A = new TestPiratesAttack();
        A.start();


        int input = scan.nextInt();
        TestCountTime.stop = false;
        input = scan.nextInt();
        TestCountTime.stop = true;
        input = scan.nextInt();



//        while(true) {  // noontime으로 밤 낮 구별 할 수 있음
//            if (TestCountTime.noontime == true) {
//                System.out.println("낮");
//            } else if (TestCountTime.noontime == false) {
//                System.out.println("밤");
//            }
//            int input = scan.nextInt();
//        }

        //test 체력감소하는지
//        while(true){
//            int input = scan.nextInt();
//            if(input == 1){
//                TestCharacter.set_char_hp(character.hp - 10); // get으로해도되고, 그냥 .로 해도 됨
//                System.out.println(TestCharacter.get_char_hp());
//                System.out.println("체력 감소");
//            }
//        }

//        int input = scan.nextInt();
//        if(input == 1){
//            introMusic.close();
//        }

    }
}