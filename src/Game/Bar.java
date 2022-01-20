package Game;

import java.util.Random;
import java.util.Scanner;

public class Bar {

    int nominated_crew = 0;
    Random random = new Random();
    Scanner scan = new Scanner(System.in);

    public void tips(int input) {

        if (input < 1 ){
            int a = random.nextInt(5);
            String[] drunk = {
                    "오늘밤도 달리시는 건가요?",
                    "같이 춤춰요, 춤 잘추시잖아요?",
                    "오늘 너무 많이 마시는거 아닌가요?",
                    "선원 모집하고 싶을 때 언제든지 환영이에요.",
                    "오시기 전에 얼마나 따분했는지 몰라요."
            };
            System.out.println(drunk[a]);

        } else if (input >= 1){
            int a = random.nextInt(20);
            String[] tip = {
                    "조선에서 선원을 구하는게 가장 쉬워요, 아무래도 조선사람이 선장인게 제일 낫잖아요?",
                    "화물창 업그레이드를 두 번 하고나면 비밀화물창 업그레이드가 가능해요. ( 8282를 입력하면 안내해준다.)\n비밀화물창에 있는 물건은 해적에게 싸움을 지더라도 뺏기지 않아요. 해적들이 비밀화물창을 알면 안되니, 아무에게도 말해주면 안돼요! ",
                    "청나라는 유목민족들이 많다보니 쌀이 부족해서 비싸게 팔린다고 들었어요.",
                    "조선의 인삼은 사람을 살린다라고 할만큼 인기가 좋다고 들었어요. 다만, 대련에는 인삼이 나기때문에 그렇게 비싸게 팔리지는 않는다고 들었어요.",
                    "중국에서 가져온 면포다보니 중국에서는 잘 팔리진 않는다고 들었어요. 반면에 일본에서는 직물이 부족하여 값이 비싸게 팔린다고 하더라구요.",
                    "명나라에는 서부지역에서 온 상인들도 많아 찾는다고 해요. 차를 찾는 손님들이 많대요.",
                    "비단은 한국과 일본에서 많이 찾아요. 아무래도 명나라엔 비단이 많이 나기때문에 비싸게 팔리지 않는 것 같아요.",
                    "말은 어느지역에서나 비싸게 팔린다고 해요. 조선은 청나라에 조공을통해 말과 면포를 바꾸고 있으니 다른 지역에 파는게 더 괜찮을 것 같아요.\n",
                    "조선은 유교사상으로 서적을 찾는 사람이 많다고 들었어요.",
                    "일본은 전쟁이 잦아 약재를 찾는 사람들이 많고 청나라는 약재가 발달되어 있어서 수입이 잘 되지 않는다고 해요.",
                    "도자기는 청나라 명나라 조선 모두 품질이 뛰어나다고 해요.",
                    "향료는 서부지역에서 온 상인들이 주로 찾는다고 해요.",
                    "제조가 발달한 명나라에서 구리 수요가 많다고 들었어요.",
                    "은은 화폐로 사용되기도 하여 무역이 가장 발달한 지역에 비싸게 팔린다고 해요.",
                    "바다에서 쌀을 2주정도 보관하고 나면 냄새가 나서 제 값을 받지 못해요.",
                    "인삼이나 차 종류는 일주일 안으로 판매하는게 가장 좋아요. 염분을 머금으면 값이 떨어지거든요.",
                    "말을 보름이상 배에 머물게 되면 스트레스를 받아서 질이 떨어져요.",
                    "약재는 말려고 보관하다보니 습도에 취약해요.",
                    "향료는 열흘이 지나면 냄새가 빠지기 시작해요.",
                    "동이 트기전에 잠깐 암시장이 열려요. 암시장에서 항로나 해도를 살 수 있어요."
            };
            System.out.println(tip[a]);
        }
        System.out.println("");
    }

    public void crew_applicants(int crew, int min_crew, int max_crew, int stage) {
        int input = 0;
        int crew_qtty;
        if (stage == 1) {
            crew_qtty = random.nextInt(33) + 7;
        } else {
            crew_qtty = random.nextInt(40);
        }
        System.out.println("\n");
        System.out.println(crew_qtty + "명의 선원이 모집되었습니다.\n필요한 인원수만큼 지정하세요.");
        System.out.println("현재 선원 수 : " + crew + "\t\t(최소 선원수 : " + min_crew + ", 최대 선원수 : " + max_crew + ")");
        System.out.println("\t\t\t\t\t최대 선원 수 까지 필요한 선원 : " + (max_crew - crew));
        while (true) {
            input = scan.nextInt();
            if (input > crew_qtty | input <= 0) {
                System.out.println("모집된 선원보다 작은 값을 입력하세요.");
            } else if (input + crew < min_crew + 1 & input < crew_qtty) {
                System.out.println("나중에 다시 모집하시겠어요?\n1. 예\n2. 아니오, 다시 지정할래요.");
                input = scan.nextInt();
                if (input == 1) {
                    break;
                } else if (input == 2) {
                    System.out.println("모집 할 선원 수를 입력하세요.");
                }
            } else if (input + crew > max_crew) {
                System.out.println("선박의 최대 선원수를 초과했습니다. 다시 선택하세요.");
            } else {//if (input + crew >= min_crew & input + crew <= max_crew) {
                System.out.println(input + "명의 선원을 모집 완료 했습니다.\n");
                nominated_crew = input;
                break;
            }
        }
    }

    public int give_main_nominated_crew() {
        return nominated_crew;
    }

}
