package Game;

import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        Scanner scan = new Scanner(System.in);

        int input = 0;
        int stage = 1;
        int go_port = 0;
        int max_stage = 1;
        int upgrade_count = 0;
        int count_ending = 5; // 4번 빌리면 엔딩

        Voyage voy = new Voyage(); // 항해 연결
        //Character.player Character.player = new Character.player(); //주인공 연결
        Prologue prologue = new Prologue(); // 프롤로그 연결
        Shipbuilding shipbuilding = new Shipbuilding(); // 조선소 연결   // 초기 값을 어떻게 설정 할 것인가?
        Trade trade = new Trade(); // 교역소 연결
        trade.make_item(); // 무역에 아이템 만들기기
        Pirates new_pirates = new Pirates(); // 해적 연결
        Bar bar = new Bar(); // 술집 연결
        Container cntr = new Container(); // 화물창 연결
        ThreadMusicPlayer introMusic = new ThreadMusicPlayer("normal.mp3", true);
        ThreadMusicPlayer fightMusic = new ThreadMusicPlayer("fighting.mp3", true);

        ThreadChart marineChart = new ThreadChart();

        input = scan.nextInt();

        introMusic.start(); // 인트로음악 음악중지
        cntr.make_item_inven_n_secret(); // 컨테이너 화물창, 비밀창 만들기
        prologue.show_1(); // 프롤로그 보여주기

        String STR = "과거 할아버지께서는 정2품 문관 판서로 한양에서 손꼽히는 벼슬을 가지고 있었다.\n" +
                "다만 아버지를 벼슬길로 올리기 위해 청탁을 하는 과정에서 문제가 발생되었고 벼슬 자리에서 박탈 당하게 된다.\n" +
                "\n" +
                "이후 진도로 내려와 벼슬을 탐하지 않으며 살아가게 된다.\n" +
                "나는 지방을 벗어나 과거에 급제하고 싶었으나 할아버지를 반대하는 세력에 의해 문과는 계속해서 낙방하게 된다.\n" +
                "\n" +
                "결국 실력으로 인정받아 벼슬길로 오르기 위해 무예를 갈고 닦았다.\n" +
                "초시, 복시, 전시에서 모두 우수한 결과를 내어 무과의 길에 오르게 되었다.\n" +
                "\n" +
                "할아버지와 아버지께 인사를 드리기 위해 진도로 내려와 짐을 싸고 있을 무렵 " +
                "왜구 해적들이 쳐들어와 마을을 약탈해 갔다.\n" +
                "그 과정에서 재산을 지키시려는 할아버지와 아버지는 모두 돌아가시게 되었고 과거길을 포기하고 해적들을 소탕하기로 마음을 먹는다.";
        System.out.println("프롤로그\n");

        for (int i = 0; i < STR.length(); i++) { // 프롤로그 타이핑 효과
            Thread.sleep(25);
            System.out.print( STR.charAt(i) );
        }

        Character.player.type_player_name(); //캐릭터명 입력 받는 메서드
        Character.player.choice_special_function(); // 캐릭터 특수기능을 받는 메서드
        if (Character.player.special_function == 6){
            for (int i = 0; i < 12; i++){
                Trade.array_1[i] += 0.02;
                Trade.array_2[i] += 0.02;
                Trade.array_3[i] += 0.02;
                Trade.array_4[i] += 0.02;
            }
        } // 장사꾼 특수기능 추가

        prologue.show_2();
        input = scan.nextInt();
        if (input == 1) {
            Character.player.set_char_money(Character.player.get_char_money() + 80000);
            System.out.println("고모부께 80000이코 지원을 받게 되었다."); // 금액 변경해야함
        } else if (input == 2) {
            System.out.println("사실 주실 것 같지도 않았다. 어떻게든 잘 해보면 될 것 같다.");
        }
        prologue.show_3(); // 배 수리 전
        prologue.show_4(); // 배 수리 후
        Character.player.set_char_hp(shipbuilding.give_main_vsl_hp()); // 원래거
        ThreadJFrame makeFrame = new ThreadJFrame(); // 프레임 시작
        Runnable r = new ThreadTimeStorage(); // 보관시간 체크 시작
        Thread timeStorage = new Thread(r);
        timeStorage.start();

        while (true) { // 메인 화면

            if (Character.player.get_char_money() >= 5000000 & max_stage == 4) {
                System.out.println("게임에서 승리했습니다!");
                System.exit(0);
            } // 게임 엔딩
            if (count_ending < 4 & Character.player.get_char_money() > 10000 & stage == 1) {
                System.out.println("얼른 갚으라고 하지 않았는가. 이자 받길 원하는겐가?");
                System.out.println("===========================================");
                System.out.println("고모부께서 " + 10000 + "이코를 갖갔습니다. 현재 보유 금액은 " + Character.player.get_char_money() + "입니다.");
                //count_ending++;
            }
            System.out.println("\n");
            Port port = new Port(stage); // 현재 항구 표시

            if (stage == 1) {
                System.out.println("1. 출항\n" +
                        "2. 교역\n" +
                        "3. 조선소\n" +
                        "4. 술집\n" +
                        "5. 나의 정보\n" +
                        "6. 화물창\n" +
                        "7. 고모부댁\n" +
                        "9. 종료");
            } else if (stage != 1) {
                System.out.println("1. 출항\n" +
                        "2. 교역\n" +
                        "3. 조선소\n" +
                        "4. 술집\n" +
                        "5. 나의 정보\n" +
                        "6. 화물창\n" +
                        "9. 종료");
            }
            input = scan.nextInt();
            if (input == 1) { // 출항
                introMusic.close();

                if (fightMusic.isAlive()) { // 다시켜지는에러
                    fightMusic.interrupt();
                }
                fightMusic = new ThreadMusicPlayer("fighting.mp3", true);
                fightMusic.start(); // 전투음악 음악시작

                AttackLoop:
                while (true) {
                    if (shipbuilding.vsl_list[0].min_crew > Character.player.crew) {
                        System.out.println("선원이 부족하여 출항을 할 수 없습니다.\n술집에서 선원을 모아보세요.");
                        break;
                    }
                    System.out.println("1. 모험하기\n2. 이동하기\n9. 나가기"); // 만약에 모험하기로 항로를 확인했으면 마지막루트로 갈때까지 모험하기가 보여지면 안됨.
                    input = scan.nextInt();
                    if (input == 1) { // 모험하기
                        int tmp_fight;
                        int tmp_crew;
                        if (max_stage > stage) {
                            System.out.println("해당 지역에서는 모험이 불가합니다. 최종 모험지로 이동하세요.");
                            break;
                        } else {
                            ThreadCountTime.work = false; // 시간 멈춤
                            ThreadWindDirection.work = false; // 바람 멈춤

                            for (int i = 0; i < 1; i++) {
                                int count_fire = 0;
                                System.out.println("\n\n");
                                System.out.println("========================================");
                                System.out.println("\t\t항해중");
                                System.out.println("========================================");
                                System.out.println("해적이 나타났습니다.");

                                new_pirates = new_pirates.make_pirates_adventure(stage);

                                ThreadAutoAttack autoAttack = new ThreadAutoAttack(stage, max_stage, shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()), 1);
                                ThreadAutoAttack.stop = false;
                                autoAttack.start();

                                while (true) {

                                    if (Character.player.hp == 0) {
                                        System.out.println(Character.player.name + "이 패배 했습니다.");
                                        tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                        int tmp = (Character.player.get_char_crew() - tmp_crew);
                                        if (tmp <= 1) {
                                            Character.player.set_char_crew(1);
                                        } else {
                                            Character.player.set_char_crew(tmp);
                                            if (tmp_crew != 0) {
                                                System.out.println("선원 " + tmp_crew + "명 죽었습니다.");
                                            }
                                        }
                                        cntr.clear_inven();
                                        trade.clear_storage();
                                        System.out.println("배에 있는 모든 물건을 약탈 당했습니다.");
                                        Character.player.set_char_hp(1);
                                        shipbuilding.set_inven_cntr(shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()));
                                        break AttackLoop;
                                    }

                                    ABCLoop:
                                    while (true) {
                                        Thread.sleep(2000);
                                        System.out.println("1. 경궁 공격");
                                        System.out.println("2. 화궁 공격");
                                        System.out.println("3. 회유 하기");
                                        System.out.println("4. 회항");
                                        input = scan.nextInt();

                                        if (Character.player.hp <= 0) {
                                            System.out.println("이미 캐릭터가 죽었습니다.");
                                            break ABCLoop;
                                        }

                                        if (input == 1) {
                                            new_pirates.attacked(Character.player.attack_normal());
                                            if (count_fire < 6 & count_fire > 0) { // 0~6, 5번
                                                new_pirates.attacked(Character.player.attack_fire());
                                                count_fire++;
                                                System.out.println(Character.player.name + "이 " + new_pirates.name + "을  경궁 공격합니다.");
                                                if (new_pirates.hp <= 0) {
                                                    new_pirates.hp = 0;
                                                }
                                                System.out.println(new_pirates.name + "은 " + Character.player.name + "의 공격을 받아 Hp가 " + ((Character.player.power * Character.player.crew - new_pirates.avoiding) + Character.player.attack_fire()) + "만큼 감소하여 현재 체력은 " + new_pirates.hp + "/300 입니다.\n");
                                                break;
                                            }
                                            System.out.println(Character.player.name + "이 " + new_pirates.name + "을 경궁 공격합니다.");
                                            if (new_pirates.hp <= 0) {
                                                new_pirates.hp = 0;
                                            }
                                            System.out.println(new_pirates.name + "은 " + Character.player.name + "의 공격을 받아 Hp가 " + Character.player.attack_normal() + "만큼 감소하여 현재 체력은 " + new_pirates.hp + "/300 입니다.\n");
                                            break;
                                        } else if (input == 2) {
                                            new_pirates.attacked(Character.player.attack_fire());
                                            count_fire = 1;
                                            System.out.println(Character.player.name + "이 " + new_pirates.name + "을 화궁 공격합니다.");
                                            if (new_pirates.hp <= 0) {
                                                new_pirates.hp = 0;
                                            }
                                            System.out.println(new_pirates.name + "은 " + Character.player.name + "의 공격을 받아 Hp가 " + Character.player.attack_fire() + "만큼 감소하여 현재 체력은 " + new_pirates.hp + "/300 입니다.\n");
                                            break;
                                        } else if (input == 3) {
                                            while (true) {
                                                System.out.println("=====================================================\n보유한 금액 : " + Character.player.money);
                                                System.out.println("뇌물을 보낼 돈을 입력하세요. ");
                                                input = scan.nextInt();
                                                if (Character.player.money < input & input != 9) {
                                                    System.out.println("돈이 부족합니다. 다시 입력하세요.");
                                                } else if (input == 9) {
                                                    break;
                                                } else {
                                                    System.out.println(input + "이코를 " + new_pirates.name + "의 부선장에게 보냈습니다.");
                                                    Character.player.money -= input;
                                                    System.out.println("보유한 금액 : " + Character.player.money + "\n=====================================================");
                                                    if (input < (new_pirates.money / 3)) {
                                                        tmp_fight = random.nextInt(10);
                                                        if (tmp_fight < 3 + Character.player.conciliation_atk - new_pirates.conciliation_def) {
                                                            System.out.println(new_pirates.name + "이 회유 당했습니다.\n");
                                                            new_pirates.hp = 0;
                                                            break;
                                                        } else {
                                                            System.out.println("회유에 실패 했습니다.\n");
                                                            break;
                                                        }
                                                    } else if (input < (new_pirates.money / 3 * 2) & input > (new_pirates.money / 3)) {
                                                        tmp_fight = random.nextInt(10);
                                                        if (tmp_fight < 5 + Character.player.conciliation_atk - new_pirates.conciliation_def) {
                                                            System.out.println(new_pirates.name + "이 회유 당했습니다.");
                                                            new_pirates.hp = 0;
                                                            break;
                                                        } else {
                                                            System.out.println("회유에 실패 했습니다.");
                                                            break;
                                                        }
                                                    } else if (input > (new_pirates.money / 3 * 2)) {
                                                        tmp_fight = random.nextInt(10);
                                                        if (tmp_fight < 7 + Character.player.conciliation_atk - new_pirates.conciliation_def) {
                                                            System.out.println(new_pirates.name + "이 회유 당했습니다.");
                                                            new_pirates.hp = 0;
                                                            break;
                                                        } else {
                                                            System.out.println("회유에 실패 했습니다.");
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                            break;
                                        } else if (input == 4) { // Letsgo
                                            ReturnLoop:
                                            while (true) {
                                                while (true) {
                                                    System.out.println("1. 화물창 비우기\n2. 회항 시도하기");
                                                    input = scan.nextInt();
                                                    if (input == 1) {
                                                        cntr.show_inven();
                                                        System.out.println("=======================");
                                                        if (cntr.distinguish_inven() == 0) {
                                                            System.out.println("화물창에 화물이 없습니다.");
                                                            break;
                                                        }
                                                        System.out.println("바다에 던질 화물을 선택하세요.");
                                                        input = scan.nextInt();
                                                        if (cntr.distinguish_inven() != 0) {
                                                            while (true) {
                                                                //Item[] tmp_item = new Item[12];
                                                                int count_tmp = 1;
                                                                for (int k = 0; k < Container.item_inven.length; k++) {
                                                                    if (Container.item_inven[k].stock != 0) {
                                                                        trade.tmp_item[count_tmp - 1].name = Container.item_inven[k].name;
                                                                        trade.tmp_item[count_tmp - 1].stock = Container.item_inven[k].stock;
                                                                        trade.tmp_item[count_tmp - 1].cbm = Container.item_inven[k].cbm;
                                                                        trade.tmp_item[count_tmp - 1].storage = Container.item_inven[k].storage;
                                                                        trade.tmp_item[count_tmp - 1].price = Container.item_inven[k].price;
                                                                        count_tmp++;
                                                                    }
                                                                }

                                                                if (trade.tmp_item[input - 1].storage < 5000) {
                                                                    if (trade.tmp_item[input - 1].name == trade.rice[0].name) {
                                                                        trade.show_rice_for_cntr(stage);
                                                                    } else if (trade.tmp_item[input - 1].name == trade.ginseng[0].name) {
                                                                        trade.show_ginseng_for_cntr(stage);
                                                                    } else if (trade.tmp_item[input - 1].name == trade.tea[0].name) {
                                                                        trade.show_tea_for_cntr(stage);
                                                                    } else if (trade.tmp_item[input - 1].name == trade.horse[0].name) {
                                                                        trade.show_horse_for_cntr(stage);
                                                                    } else if (trade.tmp_item[input - 1].name == trade.medicine[0].name) {
                                                                        trade.show_medicine_for_cntr(stage);
                                                                    } else if (trade.tmp_item[input - 1].name == trade.flavor[0].name) {
                                                                        trade.show_flavor_for_cntr(stage);
                                                                    }
                                                                    System.out.print("===========================================\n바다에 던질 화물을 선택하세요.");
                                                                    System.out.println("\t\t\t\t\t보유한 공간 : " + shipbuilding.give_main_vsl_container());
                                                                    int selected_in_sell = scan.nextInt();

                                                                    // 9/23복사
                                                                    if (trade.tmp_item[input - 1].name == trade.rice[0].name) { // 쌀
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.rice_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.rice_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.rice[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.rice[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.rice[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.rice[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.rice[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.rice_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    } else if (trade.tmp_item[input - 1].name == trade.ginseng[0].name) { // 인삼
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.ginseng_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.ginseng_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.ginseng[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.ginseng[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.ginseng[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.ginseng[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.ginseng[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.ginseng_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    } else if (trade.tmp_item[input - 1].name == trade.tea[0].name) { // 차
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.tea_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.tea_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.tea[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.tea[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.tea[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.tea[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.tea[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.tea_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    } else if (trade.tmp_item[input - 1].name == trade.horse[0].name) { // 말
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.horse_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.horse_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.horse[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.horse[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.horse[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.horse[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.horse[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.horse_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    } else if (trade.tmp_item[input - 1].name == trade.medicine[0].name) { // 약재
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.medicine_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.medicine_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.medicine[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.medicine[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.medicine[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.medicine[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.medicine[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.medicine_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    } else if (trade.tmp_item[input - 1].name == trade.flavor[0].name) { // 향료
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.flavor_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.flavor_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.flavor[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.flavor[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.flavor[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.flavor[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.flavor[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.flavor_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    }
                                                                } else {
                                                                    System.out.println("던질 수량을 선택하세요.");
                                                                    int qtty = scan.nextInt();
                                                                    if (qtty > cntr.item_inven[cntr.get_number_through_num_in_inven(input)].stock) {
                                                                        System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                                    } else {
                                                                        cntr.input_data_inven(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, -qtty);
                                                                        trade.throw_data_storage(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, qtty);
                                                                        shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() + (cntr.get_cbm_through_name(cntr.get_name_in_inven(input)) * qtty));
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                            cntr.show_inven();
                                                            System.out.println("=====================");
                                                            //break;
                                                        } else {
                                                            System.out.println("옳바른 번호를 다시 입력하세요.");
                                                        }
                                                    } else if (input == 2) {
                                                        if (shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.75 < shipbuilding.get_vsl_container()) {
                                                            int a = random.nextInt(10);
                                                            if (a <= 8) {
                                                                System.out.println("회항에 성공했습니다.");

                                                                tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                                                int tmp = (Character.player.get_char_crew() - tmp_crew);
                                                                if (tmp <= 1) {
                                                                    Character.player.set_char_crew(1);
                                                                } else {
                                                                    Character.player.set_char_crew(tmp);
                                                                    if (tmp_crew != 0) {
                                                                        System.out.println("선원 " + tmp_crew + "명 죽었습니다.");
                                                                    }
                                                                }
                                                                ThreadAutoAttack.stop = true;
                                                                break AttackLoop;

                                                            } else {
                                                                System.out.println("회항에 실패 했습니다.\n");
                                                                break ReturnLoop;
                                                            }
                                                        } else if (shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.75 >= shipbuilding.get_vsl_container() & shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.5 < shipbuilding.get_vsl_container()) {
                                                            int a = random.nextInt(10);
                                                            if (a <= 6) {
                                                                System.out.println("회항에 성공했습니다.");

                                                                tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                                                int tmp = (Character.player.get_char_crew() - tmp_crew);
                                                                if (tmp <= 1) {
                                                                    Character.player.set_char_crew(1);
                                                                } else {
                                                                    Character.player.set_char_crew(tmp);
                                                                    if (tmp_crew != 0) {
                                                                        System.out.println("선원 " + tmp_crew + "명 죽었습니다.");
                                                                    }
                                                                }
                                                                ThreadAutoAttack.stop = true;
                                                                break AttackLoop;
                                                            } else {
                                                                System.out.println("회항에 실패 했습니다.\n");
                                                                break ReturnLoop;
                                                            }
                                                        } else if (shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.5 >= shipbuilding.get_vsl_container() & shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.25 < shipbuilding.get_vsl_container()) {
                                                            int a = random.nextInt(10);
                                                            if (a <= 4) {
                                                                System.out.println("회항에 성공했습니다.");

                                                                tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                                                int tmp = (Character.player.get_char_crew() - tmp_crew);
                                                                if (tmp <= 1) {
                                                                    Character.player.set_char_crew(1);
                                                                } else {
                                                                    Character.player.set_char_crew(tmp);
                                                                    if (tmp_crew != 0) {
                                                                        System.out.println("선원 " + tmp_crew + "명 죽었습니다.");
                                                                    }
                                                                }
                                                                ThreadAutoAttack.stop = true;
                                                                break AttackLoop;

                                                            } else {
                                                                System.out.println("회항에 실패 했습니다.\n");
                                                                break ReturnLoop;
                                                            }
                                                        } else if (shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.75 >= shipbuilding.get_vsl_container() & shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.5 < shipbuilding.get_vsl_container()) {
                                                            int a = random.nextInt(10);
                                                            if (a <= 2) {
                                                                System.out.println("회항에 성공했습니다.");

                                                                tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                                                int tmp = (Character.player.get_char_crew() - tmp_crew);
                                                                if (tmp <= 1) {
                                                                    Character.player.set_char_crew(1);
                                                                } else {
                                                                    Character.player.set_char_crew(tmp);
                                                                    if (tmp_crew != 0) {
                                                                        System.out.println("선원 " + tmp_crew + "명 죽었습니다.");
                                                                    }
                                                                }
                                                                ThreadAutoAttack.stop = true;
                                                                break AttackLoop;

                                                            } else {
                                                                System.out.println("회항에 실패 했습니다.\n");
                                                                break ReturnLoop;
                                                            }
                                                        }
                                                    } else {
                                                        System.out.println("옳바른 숫자를 다시 입력하세요.");
                                                    }
                                                    break;
                                                }
                                                //break;
                                            }
                                        } else {
                                            System.out.println("옳바른 숫자를 다시 입력하세요.");
                                        }
                                        break;
                                    }

                                    if (new_pirates.hp == 0) {
                                        ThreadAutoAttack.stop = true;
                                        //System.out.println(new_pirates.name + "이 죽었습니다.");
                                        Character.player.money += new_pirates.money;
                                        tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                        Character.player.set_char_crew(Character.player.get_char_crew() - tmp_crew);
                                        Character.player.hp += Character.player.hp * 0.1;
                                        Character.player.set_char_hp(Character.player.hp);
                                        //System.out.println(Character.player.get_char_hp());
                                        if (tmp_crew != 0) {
                                            System.out.println("선원 " + tmp_crew + "명이 죽었습니다.");
                                        }
                                        if (Character.player.hp > (shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()))) { // 여기 배 체력으로 바꿔줘야함
                                            Character.player.hp = (shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()));
                                        }
                                        System.out.println("내부 수리를 통해 체력이 10% 회복하여 현재 체력은 " + Character.player.hp + "입니다.\n");
                                        System.out.println(new_pirates.name + "의 돈 " + new_pirates.money + "를 획득했습니다.");
                                        while (true) {
                                            int random_item = random.nextInt(11) + 1; // 12개중에 아이템 뽑는 것.
                                            int random_qtty = random.nextInt(20);
                                            if (trade.item_trade[random_item].cbm * random_qtty < 1000) {
                                                while (true) {
                                                    System.out.println(trade.item_trade[random_item].name + " " + random_qtty + "개 획득 했습니다. (개별 차지하는 공간 : " + trade.item_trade[random_item].cbm + " / 총 공간 : " + trade.item_trade[random_item].cbm * random_qtty + ")");
                                                    System.out.println("==================================");
                                                    System.out.println("화물창 총 공간 : " + shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) + "\t\t남은 공간 : " + shipbuilding.give_main_vsl_container());
                                                    System.out.println("==================================");
                                                    cntr.show_inven();

                                                    System.out.println("\n1. 물품 선적하기\n2. 화물창 비우기\n9. 나가기");
                                                    input = scan.nextInt();
                                                    if (input == 1) {
                                                        if (random_qtty == 0) {
                                                            System.out.println("선적 할 아이템이 없습니다.");
                                                        } else {
                                                            while (true) {
                                                                System.out.println("원하는 수량을 선택하세요.");
                                                                input = scan.nextInt();
                                                                if (trade.item_trade[random_item].cbm * input > shipbuilding.give_main_vsl_container()) {
                                                                    System.out.println("화물 공간이 부족합니다. 다시 선택하세요.");
                                                                } else if (input > random_qtty) {
                                                                    System.out.println("다시 입력하세요.");
                                                                } else {
                                                                    cntr.input_data_inven(trade.item_trade[random_item].name, input);
                                                                    trade.input_data_storage(trade.item_trade[random_item].name, input);
                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() - trade.item_trade[random_item].cbm * input);
                                                                    System.out.println("선적 완료 됐습니다.");
                                                                    System.out.println("==================================");
                                                                    System.out.println("화물창 총 공간 : " + shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) + "\t\t남은 공간 : " + shipbuilding.give_main_vsl_container());
                                                                    System.out.println("==================================");
                                                                    cntr.show_inven();
                                                                    System.out.println("");
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        break;

                                                    } else if (input == 2) {
                                                        //악
                                                        while (true) {
                                                            if (cntr.distinguish_inven() == 0) {
                                                                System.out.println("화물창에 화물이 없습니다.");
                                                                break;
                                                            }
                                                            System.out.println("바다에 던질 화물을 선택하세요.");
                                                            input = scan.nextInt();
                                                            if (cntr.distinguish_inven() != 0) {
                                                                while (true) {
                                                                    //Item[] tmp_item = new Item[12];
                                                                    int count_tmp = 1;
                                                                    for (int k = 0; k < Container.item_inven.length; k++) {
                                                                        if (Container.item_inven[k].stock != 0) {
                                                                            trade.tmp_item[count_tmp - 1].name = Container.item_inven[k].name;
                                                                            trade.tmp_item[count_tmp - 1].stock = Container.item_inven[k].stock;
                                                                            trade.tmp_item[count_tmp - 1].cbm = Container.item_inven[k].cbm;
                                                                            trade.tmp_item[count_tmp - 1].storage = Container.item_inven[k].storage;
                                                                            trade.tmp_item[count_tmp - 1].price = Container.item_inven[k].price;
                                                                            count_tmp++;
                                                                        }
                                                                    }

                                                                    if (trade.tmp_item[input - 1].storage < 5000) {
                                                                        if (trade.tmp_item[input - 1].name == trade.rice[0].name) {
                                                                            trade.show_rice_for_cntr(stage);
                                                                        } else if (trade.tmp_item[input - 1].name == trade.ginseng[0].name) {
                                                                            trade.show_ginseng_for_cntr(stage);
                                                                        } else if (trade.tmp_item[input - 1].name == trade.tea[0].name) {
                                                                            trade.show_tea_for_cntr(stage);
                                                                        } else if (trade.tmp_item[input - 1].name == trade.horse[0].name) {
                                                                            trade.show_horse_for_cntr(stage);
                                                                        } else if (trade.tmp_item[input - 1].name == trade.medicine[0].name) {
                                                                            trade.show_medicine_for_cntr(stage);
                                                                        } else if (trade.tmp_item[input - 1].name == trade.flavor[0].name) {
                                                                            trade.show_flavor_for_cntr(stage);
                                                                        }
                                                                        System.out.print("===========================================\n바다에 던질 화물을 선택하세요.");
                                                                        System.out.println("\t\t\t\t\t보유한 공간 : " + shipbuilding.give_main_vsl_container());
                                                                        int selected_in_sell = scan.nextInt();

                                                                        // 9/23복사
                                                                        if (trade.tmp_item[input - 1].name == trade.rice[0].name) { // 쌀
                                                                            if (selected_in_sell == 20000) {
                                                                                trade.rice_count = 0;
                                                                                break;
                                                                                //count = 0;
                                                                            } else if (selected_in_sell > trade.rice_count + 1) {
                                                                                System.out.println("옳바른 숫자를 입력하세요.");
                                                                            } else {
                                                                                while (true) {
                                                                                    System.out.println("던질 수량을 입력하세요.");
                                                                                    int qtty = scan.nextInt();
                                                                                    if (trade.rice[selected_in_sell - 1].stock < qtty) {
                                                                                        System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                    } else {
                                                                                        trade.tmp_item[input - 1].stock += -qtty;
                                                                                        trade.rice[selected_in_sell - 1].stock += -qtty;
                                                                                        if (trade.rice[selected_in_sell - 1].stock <= 0) {
                                                                                            trade.rice[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                        }
                                                                                        shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                        //여기가 문제
                                                                                        System.out.println(trade.rice[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                        Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                        trade.rice_count = 0;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                            }
                                                                            break;
                                                                        } else if (trade.tmp_item[input - 1].name == trade.ginseng[0].name) { // 인삼
                                                                            if (selected_in_sell == 20000) {
                                                                                trade.ginseng_count = 0;
                                                                                break;
                                                                                //count = 0;
                                                                            } else if (selected_in_sell > trade.ginseng_count + 1) {
                                                                                System.out.println("옳바른 숫자를 입력하세요.");
                                                                            } else {
                                                                                while (true) {
                                                                                    System.out.println("던질 수량을 입력하세요.");
                                                                                    int qtty = scan.nextInt();
                                                                                    if (trade.ginseng[selected_in_sell - 1].stock < qtty) {
                                                                                        System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                    } else {
                                                                                        trade.tmp_item[input - 1].stock += -qtty;
                                                                                        trade.ginseng[selected_in_sell - 1].stock += -qtty;
                                                                                        if (trade.ginseng[selected_in_sell - 1].stock <= 0) {
                                                                                            trade.ginseng[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                        }
                                                                                        shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                        //여기가 문제
                                                                                        System.out.println(trade.ginseng[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                        Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                        trade.ginseng_count = 0;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                            }
                                                                            break;
                                                                        } else if (trade.tmp_item[input - 1].name == trade.tea[0].name) { // 차
                                                                            if (selected_in_sell == 20000) {
                                                                                trade.tea_count = 0;
                                                                                break;
                                                                                //count = 0;
                                                                            } else if (selected_in_sell > trade.tea_count + 1) {
                                                                                System.out.println("옳바른 숫자를 입력하세요.");
                                                                            } else {
                                                                                while (true) {
                                                                                    System.out.println("던질 수량을 입력하세요.");
                                                                                    int qtty = scan.nextInt();
                                                                                    if (trade.tea[selected_in_sell - 1].stock < qtty) {
                                                                                        System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                    } else {
                                                                                        trade.tmp_item[input - 1].stock += -qtty;
                                                                                        trade.tea[selected_in_sell - 1].stock += -qtty;
                                                                                        if (trade.tea[selected_in_sell - 1].stock <= 0) {
                                                                                            trade.tea[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                        }
                                                                                        shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                        //여기가 문제
                                                                                        System.out.println(trade.tea[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                        Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                        trade.tea_count = 0;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                            }
                                                                            break;
                                                                        } else if (trade.tmp_item[input - 1].name == trade.horse[0].name) { // 말
                                                                            if (selected_in_sell == 20000) {
                                                                                trade.horse_count = 0;
                                                                                break;
                                                                                //count = 0;
                                                                            } else if (selected_in_sell > trade.horse_count + 1) {
                                                                                System.out.println("옳바른 숫자를 입력하세요.");
                                                                            } else {
                                                                                while (true) {
                                                                                    System.out.println("던질 수량을 입력하세요.");
                                                                                    int qtty = scan.nextInt();
                                                                                    if (trade.horse[selected_in_sell - 1].stock < qtty) {
                                                                                        System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                    } else {
                                                                                        trade.tmp_item[input - 1].stock += -qtty;
                                                                                        trade.horse[selected_in_sell - 1].stock += -qtty;
                                                                                        if (trade.horse[selected_in_sell - 1].stock <= 0) {
                                                                                            trade.horse[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                        }
                                                                                        shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                        //여기가 문제
                                                                                        System.out.println(trade.horse[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                        Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                        trade.horse_count = 0;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                            }
                                                                            break;
                                                                        } else if (trade.tmp_item[input - 1].name == trade.medicine[0].name) { // 약재
                                                                            if (selected_in_sell == 20000) {
                                                                                trade.medicine_count = 0;
                                                                                break;
                                                                                //count = 0;
                                                                            } else if (selected_in_sell > trade.medicine_count + 1) {
                                                                                System.out.println("옳바른 숫자를 입력하세요.");
                                                                            } else {
                                                                                while (true) {
                                                                                    System.out.println("던질 수량을 입력하세요.");
                                                                                    int qtty = scan.nextInt();
                                                                                    if (trade.medicine[selected_in_sell - 1].stock < qtty) {
                                                                                        System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                    } else {
                                                                                        trade.tmp_item[input - 1].stock += -qtty;
                                                                                        trade.medicine[selected_in_sell - 1].stock += -qtty;
                                                                                        if (trade.medicine[selected_in_sell - 1].stock <= 0) {
                                                                                            trade.medicine[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                        }
                                                                                        shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                        //여기가 문제
                                                                                        System.out.println(trade.medicine[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                        Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                        trade.medicine_count = 0;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                            }
                                                                            break;
                                                                        } else if (trade.tmp_item[input - 1].name == trade.flavor[0].name) { // 향료
                                                                            if (selected_in_sell == 20000) {
                                                                                trade.flavor_count = 0;
                                                                                break;
                                                                                //count = 0;
                                                                            } else if (selected_in_sell > trade.flavor_count + 1) {
                                                                                System.out.println("옳바른 숫자를 입력하세요.");
                                                                            } else {
                                                                                while (true) {
                                                                                    System.out.println("던질 수량을 입력하세요.");
                                                                                    int qtty = scan.nextInt();
                                                                                    if (trade.flavor[selected_in_sell - 1].stock < qtty) {
                                                                                        System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                    } else {
                                                                                        trade.tmp_item[input - 1].stock += -qtty;
                                                                                        trade.flavor[selected_in_sell - 1].stock += -qtty;
                                                                                        if (trade.flavor[selected_in_sell - 1].stock <= 0) {
                                                                                            trade.flavor[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                        }
                                                                                        shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                        //여기가 문제
                                                                                        System.out.println(trade.flavor[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                        Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                        trade.flavor_count = 0;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                            }
                                                                            break;
                                                                        }
                                                                    } else {
                                                                        System.out.println("던질 수량을 선택하세요.");
                                                                        int qtty = scan.nextInt();
                                                                        if (qtty > cntr.item_inven[cntr.get_number_through_num_in_inven(input)].stock) {
                                                                            System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                                        } else {
                                                                            cntr.input_data_inven(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, -qtty);
                                                                            trade.throw_data_storage(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, qtty);
                                                                            shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() + (cntr.get_cbm_through_name(cntr.get_name_in_inven(input)) * qtty));
                                                                            break;
                                                                        }
                                                                    }
                                                                }
                                                                break;
                                                            } else {
                                                                System.out.println("옳바른 번호를 다시 입력하세요.");
                                                            }
                                                        }
                                                    } else if (input == 9) {
                                                        break;
                                                    }
                                                }
                                                break;
                                            } else {
                                            }
                                        }
                                        break;
                                    }
                                }

                            }

                            if (stage == 4) {
                                System.out.println("최종 도착지입니다.\n3000000 이코를 모으면 게임이 끝납니다.");
                            }

                            ThreadCountTime.howmanydays += 4; //
                            ThreadTimeStorage.reduce_storage(); // 4일씩 줄이기
                            System.out.println("4일 소요 되었습니다.");
                            stage++;
                            max_stage++;
                            break;
                        }
                    }
                    else if (input == 2) { // 이동하기
                        if (ThreadChart.status == false) {
                            int tmp_fight;
                            int tmp_crew;
                            int random_howmany = random.nextInt(3);
                            if (random_howmany - Character.player.get_sailing() < 0) {
                                random_howmany = 1;
                            }
                            go_port = port.ChoiceDestination(stage, max_stage);
                            if (go_port == -1) {
                                break;
                            }
                            //해적 만나는거 추가해주자
                            if (stage == 1 & max_stage == 1) {
                                break;
                            }
//                            Runnable r1 = new ThreadTimeDate(stage, go_port);
//                            Thread timeDate = new Thread(r1);
//                            timeDate.start();

                            ThreadTimeStorage.TimeDate(stage, go_port);

//                            ThreadTimeDate.work = true; //날짜 계산 (이동중)
                            ThreadCountTime.work = false; // 시간 멈춤 (이동중)
                            ThreadWindDirection.work = false; // 바람 멈춤 (이동중)

                            for (int i = 0; i < random_howmany - Character.player.get_sailing(); i++) {
                                int count_fire = 0;
                                System.out.println("\n\n");
                                System.out.println("========================================");
                                System.out.println("\t\t항해중");
                                System.out.println("========================================");

                                System.out.println("해적이 나타났습니다.");

                                // 해적 정보 등록
//                                Pirates new_pirates = new Pirates();
                                new_pirates = new_pirates.make_pirates(max_stage);

                                ThreadAutoAttack autoAttack = new ThreadAutoAttack(stage, max_stage, shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()), 2);
                                ThreadAutoAttack.stop = false;
                                autoAttack.start();

                                while (true) {

                                    if (Character.player.hp == 0) {
                                        System.out.println(Character.player.name + "이 패배 했습니다.");
                                        tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                        int tmp = (Character.player.get_char_crew() - tmp_crew);
                                        if (tmp <= 1) {
                                            Character.player.set_char_crew(1);
                                        } else {
                                            Character.player.set_char_crew(tmp);
                                            if (tmp_crew != 0) {
                                                System.out.println("선원 " + tmp_crew + "명 죽었습니다.");
                                            }
                                        }
                                        cntr.clear_inven();
                                        trade.clear_storage();
                                        System.out.println("배에 있는 모든 물건을 약탈 당했습니다.");
                                        Character.player.set_char_hp(1);
                                        shipbuilding.set_inven_cntr(shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()));
                                        ThreadAutoAttack.stop = true;
                                        break AttackLoop;
                                    }

                                    while (true) {
                                        Thread.sleep(2000);
                                        System.out.println("1. 경궁 공격");
                                        System.out.println("2. 화궁 공격");
                                        System.out.println("3. 회유 하기");
                                        System.out.println("4. 회항");
                                        input = scan.nextInt();

                                        if (input == 1) {
                                            new_pirates.attacked(Character.player.attack_normal());
                                            if (count_fire < 6 & count_fire > 0) { // 0~6, 5번
                                                new_pirates.attacked(Character.player.attack_fire());
                                                count_fire++;
                                                System.out.println(Character.player.name + "이 " + new_pirates.name + "을  경궁 공격합니다.");
                                                if (new_pirates.hp <= 0) {
                                                    new_pirates.hp = 0;
                                                }
                                                System.out.println(new_pirates.name + "은 " + Character.player.name + "의 공격을 받아 Hp가 " + ((Character.player.power * Character.player.crew - new_pirates.avoiding) + Character.player.attack_fire()) + "만큼 감소하여 현재 체력은 " + new_pirates.hp + "/300 입니다.\n");
                                                break;
                                            }
                                            System.out.println(Character.player.name + "이 " + new_pirates.name + "을 경궁 공격합니다.");
                                            if (new_pirates.hp <= 0) {
                                                new_pirates.hp = 0;
                                            }
                                            System.out.println(new_pirates.name + "은 " + Character.player.name + "의 공격을 받아 Hp가 " + Character.player.attack_normal() + "만큼 감소하여 현재 체력은 " + new_pirates.hp + "/300 입니다.\n");
                                            break;
                                        } else if (input == 2) {
                                            new_pirates.attacked(Character.player.attack_fire());
                                            count_fire = 1;
                                            System.out.println(Character.player.name + "이 " + new_pirates.name + "을 화궁 공격합니다.");
                                            if (new_pirates.hp <= 0) {
                                                new_pirates.hp = 0;
                                            }
                                            System.out.println(new_pirates.name + "은 " + Character.player.name + "의 공격을 받아 Hp가 " + Character.player.attack_fire() + "만큼 감소하여 현재 체력은 " + new_pirates.hp + "/300 입니다.\n");
                                            break;
                                        } else if (input == 3) {
                                            while (true) {
                                                System.out.println("=====================================================\n보유한 금액 : " + Character.player.money);
                                                System.out.println("뇌물을 보낼 돈을 입력하세요. ");
                                                input = scan.nextInt();
                                                if (Character.player.money < input & input != 9) {
                                                    System.out.println("돈이 부족합니다. 다시 입력하세요.");
                                                } else if (input == 9) {
                                                    break;
                                                } else {
                                                    System.out.println(input + "이코를 " + new_pirates.name + "의 부선장에게 보냈습니다.");
                                                    Character.player.money -= input;
                                                    System.out.println("보유한 금액 : " + Character.player.money + "\n=====================================================");
                                                    if (input < (new_pirates.money / 3)) {
                                                        tmp_fight = random.nextInt(10);
                                                        if (tmp_fight < 3 + Character.player.conciliation_atk - new_pirates.conciliation_def) {
                                                            System.out.println(new_pirates.name + "이 회유 당했습니다.\n");
                                                            new_pirates.hp = 0;
                                                            break;
                                                        } else {
                                                            System.out.println("회유에 실패 했습니다.\n");
                                                            break;
                                                        }
                                                    } else if (input < (new_pirates.money / 3 * 2) & input > (new_pirates.money / 3)) {
                                                        tmp_fight = random.nextInt(10);
                                                        if (tmp_fight < 5 + Character.player.conciliation_atk - new_pirates.conciliation_def) {
                                                            System.out.println(new_pirates.name + "이 회유 당했습니다.");
                                                            new_pirates.hp = 0;
                                                            break;
                                                        } else {
                                                            System.out.println("회유에 실패 했습니다.");
                                                            break;
                                                        }
                                                    } else if (input > (new_pirates.money / 3 * 2)) {
                                                        tmp_fight = random.nextInt(10);
                                                        if (tmp_fight < 7 + Character.player.conciliation_atk - new_pirates.conciliation_def) {
                                                            System.out.println(new_pirates.name + "이 회유 당했습니다.");
                                                            new_pirates.hp = 0;
                                                            break;
                                                        } else {
                                                            System.out.println("회유에 실패 했습니다.");
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                            break;
                                        } else if (input == 4) { // Letsgo
                                            ReturnLoop:
                                            while (true) {
                                                while (true) {
                                                    System.out.println("1. 화물창 비우기\n2. 회항 시도하기");
                                                    input = scan.nextInt();
                                                    if (input == 1) {
                                                        cntr.show_inven();
                                                        System.out.println("=======================");
                                                        if (cntr.distinguish_inven() == 0) {
                                                            System.out.println("화물창에 화물이 없습니다.");
                                                            break;
                                                        }
                                                        System.out.println("바다에 던질 화물을 선택하세요.");
                                                        input = scan.nextInt();
                                                        if (cntr.distinguish_inven() != 0) {
                                                            while (true) {
                                                                //Item[] tmp_item = new Item[12];
                                                                int count_tmp = 1;
                                                                for (int k = 0; k < Container.item_inven.length; k++) {
                                                                    if (Container.item_inven[k].stock != 0) {
                                                                        trade.tmp_item[count_tmp - 1].name = Container.item_inven[k].name;
                                                                        trade.tmp_item[count_tmp - 1].stock = Container.item_inven[k].stock;
                                                                        trade.tmp_item[count_tmp - 1].cbm = Container.item_inven[k].cbm;
                                                                        trade.tmp_item[count_tmp - 1].storage = Container.item_inven[k].storage;
                                                                        trade.tmp_item[count_tmp - 1].price = Container.item_inven[k].price;
                                                                        count_tmp++;
                                                                    }
                                                                }

                                                                if (trade.tmp_item[input - 1].storage < 5000) {
                                                                    if (trade.tmp_item[input - 1].name == trade.rice[0].name) {
                                                                        trade.show_rice_for_cntr(stage);
                                                                    } else if (trade.tmp_item[input - 1].name == trade.ginseng[0].name) {
                                                                        trade.show_ginseng_for_cntr(stage);
                                                                    } else if (trade.tmp_item[input - 1].name == trade.tea[0].name) {
                                                                        trade.show_tea_for_cntr(stage);
                                                                    } else if (trade.tmp_item[input - 1].name == trade.horse[0].name) {
                                                                        trade.show_horse_for_cntr(stage);
                                                                    } else if (trade.tmp_item[input - 1].name == trade.medicine[0].name) {
                                                                        trade.show_medicine_for_cntr(stage);
                                                                    } else if (trade.tmp_item[input - 1].name == trade.flavor[0].name) {
                                                                        trade.show_flavor_for_cntr(stage);
                                                                    }
                                                                    System.out.print("===========================================\n바다에 던질 화물을 선택하세요.");
                                                                    System.out.println("\t\t\t\t\t보유한 공간 : " + shipbuilding.give_main_vsl_container());
                                                                    int selected_in_sell = scan.nextInt();

                                                                    // 9/23복사
                                                                    if (trade.tmp_item[input - 1].name == trade.rice[0].name) { // 쌀
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.rice_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.rice_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.rice[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.rice[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.rice[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.rice[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.rice[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.rice_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    } else if (trade.tmp_item[input - 1].name == trade.ginseng[0].name) { // 인삼
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.ginseng_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.ginseng_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.ginseng[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.ginseng[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.ginseng[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.ginseng[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.ginseng[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.ginseng_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    } else if (trade.tmp_item[input - 1].name == trade.tea[0].name) { // 차
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.tea_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.tea_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.tea[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.tea[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.tea[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.tea[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.tea[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.tea_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    } else if (trade.tmp_item[input - 1].name == trade.horse[0].name) { // 말
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.horse_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.horse_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.horse[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.horse[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.horse[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.horse[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.horse[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.horse_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    } else if (trade.tmp_item[input - 1].name == trade.medicine[0].name) { // 약재
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.medicine_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.medicine_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.medicine[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.medicine[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.medicine[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.medicine[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.medicine[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.medicine_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    } else if (trade.tmp_item[input - 1].name == trade.flavor[0].name) { // 향료
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.flavor_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.flavor_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.flavor[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.flavor[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.flavor[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.flavor[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.flavor[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.flavor_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    }
                                                                } else {
                                                                    System.out.println("던질 수량을 선택하세요.");
                                                                    int qtty = scan.nextInt();
                                                                    if (qtty > cntr.item_inven[cntr.get_number_through_num_in_inven(input)].stock) {
                                                                        System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                                    } else {
                                                                        cntr.input_data_inven(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, -qtty);
                                                                        trade.throw_data_storage(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, qtty);
                                                                        shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() + (cntr.get_cbm_through_name(cntr.get_name_in_inven(input)) * qtty));
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                            cntr.show_inven();
                                                            System.out.println("=====================");
                                                            //break;
                                                        } else {
                                                            System.out.println("옳바른 번호를 다시 입력하세요.");
                                                        }
                                                    } else if (input == 2) {
                                                        if (shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.75 < shipbuilding.get_vsl_container()) {
                                                            int a = random.nextInt(10);
                                                            if (a <= 8) {
                                                                System.out.println("회항에 성공했습니다.");

                                                                tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                                                int tmp = (Character.player.get_char_crew() - tmp_crew);
                                                                if (tmp <= 1) {
                                                                    Character.player.set_char_crew(1);
                                                                } else {
                                                                    Character.player.set_char_crew(tmp);
                                                                    if (tmp_crew != 0) {
                                                                        System.out.println("선원 " + tmp_crew + "명 죽었습니다.");
                                                                    }
                                                                }
                                                                ThreadAutoAttack.stop = true;
                                                                break AttackLoop;

                                                            } else {
                                                                System.out.println("회항에 실패 했습니다.\n");
                                                                break ReturnLoop;
                                                            }
                                                        } else if (shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.75 >= shipbuilding.get_vsl_container() & shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.5 < shipbuilding.get_vsl_container()) {
                                                            int a = random.nextInt(10);
                                                            if (a <= 6) {
                                                                System.out.println("회항에 성공했습니다.");

                                                                tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                                                int tmp = (Character.player.get_char_crew() - tmp_crew);
                                                                if (tmp <= 1) {
                                                                    Character.player.set_char_crew(1);
                                                                } else {
                                                                    Character.player.set_char_crew(tmp);
                                                                    if (tmp_crew != 0) {
                                                                        System.out.println("선원 " + tmp_crew + "명 죽었습니다.");
                                                                    }
                                                                }
                                                                ThreadAutoAttack.stop = true;
                                                                break AttackLoop;
                                                            } else {
                                                                System.out.println("회항에 실패 했습니다.\n");
                                                                break ReturnLoop;
                                                            }
                                                        } else if (shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.5 >= shipbuilding.get_vsl_container() & shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.25 < shipbuilding.get_vsl_container()) {
                                                            int a = random.nextInt(10);
                                                            if (a <= 4) {
                                                                System.out.println("회항에 성공했습니다.");

                                                                tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                                                int tmp = (Character.player.get_char_crew() - tmp_crew);
                                                                if (tmp <= 1) {
                                                                    Character.player.set_char_crew(1);
                                                                } else {
                                                                    Character.player.set_char_crew(tmp);
                                                                    if (tmp_crew != 0) {
                                                                        System.out.println("선원 " + tmp_crew + "명 죽었습니다.");
                                                                    }
                                                                }
                                                                ThreadAutoAttack.stop = true;
                                                                break AttackLoop;

                                                            } else {
                                                                System.out.println("회항에 실패 했습니다.\n");
                                                                break ReturnLoop;
                                                            }
                                                        } else if (shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.75 >= shipbuilding.get_vsl_container() & shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.5 < shipbuilding.get_vsl_container()) {
                                                            int a = random.nextInt(10);
                                                            if (a <= 2) {
                                                                System.out.println("회항에 성공했습니다.");

                                                                tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                                                int tmp = (Character.player.get_char_crew() - tmp_crew);
                                                                if (tmp <= 1) {
                                                                    Character.player.set_char_crew(1);
                                                                } else {
                                                                    Character.player.set_char_crew(tmp);
                                                                    if (tmp_crew != 0) {
                                                                        System.out.println("선원 " + tmp_crew + "명 죽었습니다.");
                                                                    }
                                                                }
                                                                ThreadAutoAttack.stop = true;
                                                                break AttackLoop;

                                                            } else {
                                                                System.out.println("회항에 실패 했습니다.\n");
                                                                break ReturnLoop;
                                                            }
                                                        }
                                                    } else {
                                                        System.out.println("옳바른 숫자를 다시 입력하세요.");
                                                    }
                                                    break;
                                                }
                                                //break;
                                            }
                                        } else {
                                            System.out.println("옳바른 숫자를 다시 입력하세요.");
                                        }
                                    }

                                    if (new_pirates.hp == 0) {
                                        ThreadAutoAttack.stop = true;
                                        Character.player.money += new_pirates.money;
                                        tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                        Character.player.set_char_crew(Character.player.get_char_crew() - tmp_crew);
                                        Character.player.hp += Character.player.hp * 0.1;
                                        Character.player.set_char_hp(Character.player.hp);
                                        if (tmp_crew != 0) {
                                            System.out.println("선원 " + tmp_crew + "명이 죽었습니다.");
                                        }
                                        if (Character.player.hp > (shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()))) { // 여기 배 체력으로 바꿔줘야함
                                            Character.player.hp = (shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()));
                                        }
                                        System.out.println("내부 수리를 통해 체력이 10% 회복하여 현재 체력은 " + Character.player.hp + "입니다.\n");
                                        System.out.println(new_pirates.name + "의 돈 " + new_pirates.money + "를 획득했습니다.");
                                        while (true) {
                                            int random_item = random.nextInt(11) + 1; // 12개중에 아이템 뽑는 것.
                                            int random_qtty = random.nextInt(20);
                                            if (trade.item_trade[random_item].cbm * random_qtty < 1000) {
                                                while (true) {
                                                    System.out.println(trade.item_trade[random_item].name + " " + random_qtty + "개 획득 했습니다. (개별 차지하는 공간 : " + trade.item_trade[random_item].cbm + " / 총 공간 : " + trade.item_trade[random_item].cbm * random_qtty + ")");
                                                    System.out.println("==================================");
                                                    System.out.println("화물창 총 공간 : " + shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) + "\t\t남은 공간 : " + shipbuilding.give_main_vsl_container());
                                                    System.out.println("==================================");
                                                    cntr.show_inven();

                                                    System.out.println("\n1. 물품 선적하기\n2. 화물창 비우기\n9. 나가기");
                                                    input = scan.nextInt();
                                                    if (input == 1) {
                                                        if (random_qtty == 0) {
                                                            System.out.println("선적 할 아이템이 없습니다.");
                                                        } else {
                                                            while (true) {
                                                                System.out.println("원하는 수량을 선택하세요.");
                                                                input = scan.nextInt();
                                                                if (trade.item_trade[random_item].cbm * input > shipbuilding.give_main_vsl_container()) {
                                                                    System.out.println("화물 공간이 부족합니다. 다시 선택하세요.");
                                                                } else if (input > random_qtty) {
                                                                    System.out.println("다시 입력하세요.");
                                                                } else {
                                                                    cntr.input_data_inven(trade.item_trade[random_item].name, input);
                                                                    trade.input_data_storage(trade.item_trade[random_item].name, input);
                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() - trade.item_trade[random_item].cbm * input);
                                                                    System.out.println("선적 완료 됐습니다.");
                                                                    System.out.println("==================================");
                                                                    System.out.println("화물창 총 공간 : " + shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) + "\t\t남은 공간 : " + shipbuilding.give_main_vsl_container());
                                                                    System.out.println("==================================");
                                                                    cntr.show_inven();
                                                                    System.out.println("");
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        break;

                                                    } else if (input == 2) {
                                                        //악
                                                        while (true) {
                                                            if (cntr.distinguish_inven() == 0) {
                                                                System.out.println("화물창에 화물이 없습니다.");
                                                                break;
                                                            }
                                                            System.out.println("바다에 던질 화물을 선택하세요.");
                                                            input = scan.nextInt();
                                                            if (cntr.distinguish_inven() != 0) {
                                                                while (true) {
                                                                    //Item[] tmp_item = new Item[12];
                                                                    int count_tmp = 1;
                                                                    for (int k = 0; k < Container.item_inven.length; k++) {
                                                                        if (Container.item_inven[k].stock != 0) {
                                                                            trade.tmp_item[count_tmp - 1].name = Container.item_inven[k].name;
                                                                            trade.tmp_item[count_tmp - 1].stock = Container.item_inven[k].stock;
                                                                            trade.tmp_item[count_tmp - 1].cbm = Container.item_inven[k].cbm;
                                                                            trade.tmp_item[count_tmp - 1].storage = Container.item_inven[k].storage;
                                                                            trade.tmp_item[count_tmp - 1].price = Container.item_inven[k].price;
                                                                            count_tmp++;
                                                                        }
                                                                    }

                                                                    if (trade.tmp_item[input - 1].storage < 5000) {
                                                                        if (trade.tmp_item[input - 1].name == trade.rice[0].name) {
                                                                            trade.show_rice_for_cntr(stage);
                                                                        } else if (trade.tmp_item[input - 1].name == trade.ginseng[0].name) {
                                                                            trade.show_ginseng_for_cntr(stage);
                                                                        } else if (trade.tmp_item[input - 1].name == trade.tea[0].name) {
                                                                            trade.show_tea_for_cntr(stage);
                                                                        } else if (trade.tmp_item[input - 1].name == trade.horse[0].name) {
                                                                            trade.show_horse_for_cntr(stage);
                                                                        } else if (trade.tmp_item[input - 1].name == trade.medicine[0].name) {
                                                                            trade.show_medicine_for_cntr(stage);
                                                                        } else if (trade.tmp_item[input - 1].name == trade.flavor[0].name) {
                                                                            trade.show_flavor_for_cntr(stage);
                                                                        }
                                                                        System.out.print("===========================================\n바다에 던질 화물을 선택하세요.");
                                                                        System.out.println("\t\t\t\t\t보유한 공간 : " + shipbuilding.give_main_vsl_container());
                                                                        int selected_in_sell = scan.nextInt();

                                                                        // 9/23복사
                                                                        if (trade.tmp_item[input - 1].name == trade.rice[0].name) { // 쌀
                                                                            if (selected_in_sell == 20000) {
                                                                                trade.rice_count = 0;
                                                                                break;
                                                                                //count = 0;
                                                                            } else if (selected_in_sell > trade.rice_count + 1) {
                                                                                System.out.println("옳바른 숫자를 입력하세요.");
                                                                            } else {
                                                                                while (true) {
                                                                                    System.out.println("던질 수량을 입력하세요.");
                                                                                    int qtty = scan.nextInt();
                                                                                    if (trade.rice[selected_in_sell - 1].stock < qtty) {
                                                                                        System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                    } else {
                                                                                        trade.tmp_item[input - 1].stock += -qtty;
                                                                                        trade.rice[selected_in_sell - 1].stock += -qtty;
                                                                                        if (trade.rice[selected_in_sell - 1].stock <= 0) {
                                                                                            trade.rice[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                        }
                                                                                        shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                        //여기가 문제
                                                                                        System.out.println(trade.rice[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                        Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                        trade.rice_count = 0;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                            }
                                                                            break;
                                                                        } else if (trade.tmp_item[input - 1].name == trade.ginseng[0].name) { // 인삼
                                                                            if (selected_in_sell == 20000) {
                                                                                trade.ginseng_count = 0;
                                                                                break;
                                                                                //count = 0;
                                                                            } else if (selected_in_sell > trade.ginseng_count + 1) {
                                                                                System.out.println("옳바른 숫자를 입력하세요.");
                                                                            } else {
                                                                                while (true) {
                                                                                    System.out.println("던질 수량을 입력하세요.");
                                                                                    int qtty = scan.nextInt();
                                                                                    if (trade.ginseng[selected_in_sell - 1].stock < qtty) {
                                                                                        System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                    } else {
                                                                                        trade.tmp_item[input - 1].stock += -qtty;
                                                                                        trade.ginseng[selected_in_sell - 1].stock += -qtty;
                                                                                        if (trade.ginseng[selected_in_sell - 1].stock <= 0) {
                                                                                            trade.ginseng[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                        }
                                                                                        shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                        //여기가 문제
                                                                                        System.out.println(trade.ginseng[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                        Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                        trade.ginseng_count = 0;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                            }
                                                                            break;
                                                                        } else if (trade.tmp_item[input - 1].name == trade.tea[0].name) { // 차
                                                                            if (selected_in_sell == 20000) {
                                                                                trade.tea_count = 0;
                                                                                break;
                                                                                //count = 0;
                                                                            } else if (selected_in_sell > trade.tea_count + 1) {
                                                                                System.out.println("옳바른 숫자를 입력하세요.");
                                                                            } else {
                                                                                while (true) {
                                                                                    System.out.println("던질 수량을 입력하세요.");
                                                                                    int qtty = scan.nextInt();
                                                                                    if (trade.tea[selected_in_sell - 1].stock < qtty) {
                                                                                        System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                    } else {
                                                                                        trade.tmp_item[input - 1].stock += -qtty;
                                                                                        trade.tea[selected_in_sell - 1].stock += -qtty;
                                                                                        if (trade.tea[selected_in_sell - 1].stock <= 0) {
                                                                                            trade.tea[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                        }
                                                                                        shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                        //여기가 문제
                                                                                        System.out.println(trade.tea[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                        Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                        trade.tea_count = 0;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                            }
                                                                            break;
                                                                        } else if (trade.tmp_item[input - 1].name == trade.horse[0].name) { // 말
                                                                            if (selected_in_sell == 20000) {
                                                                                trade.horse_count = 0;
                                                                                break;
                                                                                //count = 0;
                                                                            } else if (selected_in_sell > trade.horse_count + 1) {
                                                                                System.out.println("옳바른 숫자를 입력하세요.");
                                                                            } else {
                                                                                while (true) {
                                                                                    System.out.println("던질 수량을 입력하세요.");
                                                                                    int qtty = scan.nextInt();
                                                                                    if (trade.horse[selected_in_sell - 1].stock < qtty) {
                                                                                        System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                    } else {
                                                                                        trade.tmp_item[input - 1].stock += -qtty;
                                                                                        trade.horse[selected_in_sell - 1].stock += -qtty;
                                                                                        if (trade.horse[selected_in_sell - 1].stock <= 0) {
                                                                                            trade.horse[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                        }
                                                                                        shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                        //여기가 문제
                                                                                        System.out.println(trade.horse[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                        Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                        trade.horse_count = 0;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                            }
                                                                            break;
                                                                        } else if (trade.tmp_item[input - 1].name == trade.medicine[0].name) { // 약재
                                                                            if (selected_in_sell == 20000) {
                                                                                trade.medicine_count = 0;
                                                                                break;
                                                                                //count = 0;
                                                                            } else if (selected_in_sell > trade.medicine_count + 1) {
                                                                                System.out.println("옳바른 숫자를 입력하세요.");
                                                                            } else {
                                                                                while (true) {
                                                                                    System.out.println("던질 수량을 입력하세요.");
                                                                                    int qtty = scan.nextInt();
                                                                                    if (trade.medicine[selected_in_sell - 1].stock < qtty) {
                                                                                        System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                    } else {
                                                                                        trade.tmp_item[input - 1].stock += -qtty;
                                                                                        trade.medicine[selected_in_sell - 1].stock += -qtty;
                                                                                        if (trade.medicine[selected_in_sell - 1].stock <= 0) {
                                                                                            trade.medicine[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                        }
                                                                                        shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                        //여기가 문제
                                                                                        System.out.println(trade.medicine[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                        Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                        trade.medicine_count = 0;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                            }
                                                                            break;
                                                                        } else if (trade.tmp_item[input - 1].name == trade.flavor[0].name) { // 향료
                                                                            if (selected_in_sell == 20000) {
                                                                                trade.flavor_count = 0;
                                                                                break;
                                                                                //count = 0;
                                                                            } else if (selected_in_sell > trade.flavor_count + 1) {
                                                                                System.out.println("옳바른 숫자를 입력하세요.");
                                                                            } else {
                                                                                while (true) {
                                                                                    System.out.println("던질 수량을 입력하세요.");
                                                                                    int qtty = scan.nextInt();
                                                                                    if (trade.flavor[selected_in_sell - 1].stock < qtty) {
                                                                                        System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                    } else {
                                                                                        trade.tmp_item[input - 1].stock += -qtty;
                                                                                        trade.flavor[selected_in_sell - 1].stock += -qtty;
                                                                                        if (trade.flavor[selected_in_sell - 1].stock <= 0) {
                                                                                            trade.flavor[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                        }
                                                                                        shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                        //여기가 문제
                                                                                        System.out.println(trade.flavor[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                        Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                        trade.flavor_count = 0;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                            }
                                                                            break;
                                                                        }
                                                                    } else {
                                                                        System.out.println("던질 수량을 선택하세요.");
                                                                        int qtty = scan.nextInt();
                                                                        if (qtty > cntr.item_inven[cntr.get_number_through_num_in_inven(input)].stock) {
                                                                            System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                                        } else {
                                                                            cntr.input_data_inven(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, -qtty);
                                                                            trade.throw_data_storage(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, qtty);
                                                                            shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() + (cntr.get_cbm_through_name(cntr.get_name_in_inven(input)) * qtty));
                                                                            break;
                                                                        }
                                                                    }
                                                                }
                                                                break;
                                                            } else {
                                                                System.out.println("옳바른 번호를 다시 입력하세요.");
                                                            }
                                                        }
                                                    } else if (input == 9) {
                                                        break;
                                                    }
                                                }
                                                break;
                                            } else {
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
                            Character.player.set_char_hp(Character.player.hp);
                            stage = go_port;
                        } else if (ThreadChart.status == true) {
                            int tmp_fight;
                            int tmp_crew;
                            go_port = port.ChoiceDestination_Chart(stage, max_stage);
                            //System.out.println("====================================");
                            if (go_port == -1) {
                                break;
                            }
                            //해적 만나는거 추가해주자
                            if (stage == 1 & max_stage == 1) {
                                break;
                            }
//                            Runnable r1 = new ThreadTimeDate(stage, go_port);
//                            Thread timeDate = new Thread(r1);
//                            timeDate.start();
                            ThreadTimeStorage.TimeDate(stage, go_port);

//                            ThreadTimeDate.work = true; //날짜 계산 (이동중)
                            ThreadCountTime.work = false; // 시간 멈춤 (이동중)
                            ThreadWindDirection.work = false; // 바람 멈춤 (이동중)

                            for (int i = 0; i < ThreadChart.howmany_pirates[go_port - 1] - Character.player.get_sailing(); i++) {
                                int count_fire = 0;
                                System.out.println("\n\n");
                                System.out.println("========================================");
                                System.out.println("\t\t항해중");
                                System.out.println("========================================");

                                System.out.println("해적이 나타났습니다.");

                                // 해적 정보 등록
//                                Pirates new_pirates = new Pirates();
                                new_pirates = new_pirates.make_pirates(max_stage);

                                ThreadAutoAttack autoAttack = new ThreadAutoAttack(stage, max_stage, shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()), 1);
                                ThreadAutoAttack.stop = false;
                                autoAttack.start();

                                while (true) {

                                    if (Character.player.hp == 0) {
                                        System.out.println(Character.player.name + "이 패배 했습니다.");
                                        tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                        int tmp = (Character.player.get_char_crew() - tmp_crew);
                                        if (tmp <= 1) {
                                            Character.player.set_char_crew(1);
                                        } else {
                                            Character.player.set_char_crew(tmp);
                                            if (tmp_crew != 0) {
                                                System.out.println("선원 " + tmp_crew + "명 죽었습니다.");
                                            }
                                        }
                                        cntr.clear_inven();
                                        trade.clear_storage();
                                        System.out.println("배에 있는 모든 물건을 약탈 당했습니다.");
                                        Character.player.set_char_hp(1);
                                        shipbuilding.set_inven_cntr(shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()));
                                        break AttackLoop;
                                    }

                                    while (true) {
                                        Thread.sleep(2000);
                                        System.out.println("1. 경궁 공격");
                                        System.out.println("2. 화궁 공격");
                                        System.out.println("3. 회유 하기");
                                        System.out.println("4. 회항");
                                        input = scan.nextInt();

                                        if (input == 1) {
                                            new_pirates.attacked(Character.player.attack_normal());
                                            if (count_fire < 6 & count_fire > 0) { // 0~6, 5번
                                                new_pirates.attacked(Character.player.attack_fire());
                                                count_fire++;
                                                System.out.println(Character.player.name + "이 " + new_pirates.name + "을  경궁 공격합니다.");
                                                if (new_pirates.hp <= 0) {
                                                    new_pirates.hp = 0;
                                                }
                                                System.out.println(new_pirates.name + "은 " + Character.player.name + "의 공격을 받아 Hp가 " + ((Character.player.power * Character.player.crew - new_pirates.avoiding) + Character.player.attack_fire()) + "만큼 감소하여 현재 체력은 " + new_pirates.hp + "/300 입니다.\n");
                                                break;
                                            }
                                            System.out.println(Character.player.name + "이 " + new_pirates.name + "을 경궁 공격합니다.");
                                            if (new_pirates.hp <= 0) {
                                                new_pirates.hp = 0;
                                            }
                                            System.out.println(new_pirates.name + "은 " + Character.player.name + "의 공격을 받아 Hp가 " + Character.player.attack_normal() + "만큼 감소하여 현재 체력은 " + new_pirates.hp + "/300 입니다.\n");
                                            break;
                                        } else if (input == 2) {
                                            new_pirates.attacked(Character.player.attack_fire());
                                            count_fire = 1;
                                            System.out.println(Character.player.name + "이 " + new_pirates.name + "을 화궁 공격합니다.");
                                            if (new_pirates.hp <= 0) {
                                                new_pirates.hp = 0;
                                            }
                                            System.out.println(new_pirates.name + "은 " + Character.player.name + "의 공격을 받아 Hp가 " + Character.player.attack_fire() + "만큼 감소하여 현재 체력은 " + new_pirates.hp + "/300 입니다.\n");
                                            break;
                                        } else if (input == 3) {
                                            while (true) {
                                                System.out.println("=====================================================\n보유한 금액 : " + Character.player.money);
                                                System.out.println("뇌물을 보낼 돈을 입력하세요. ");
                                                input = scan.nextInt();
                                                if (Character.player.money < input & input != 9) {
                                                    System.out.println("돈이 부족합니다. 다시 입력하세요.");
                                                } else if (input == 9) {
                                                    break;
                                                } else {
                                                    System.out.println(input + "이코를 " + new_pirates.name + "의 부선장에게 보냈습니다.");
                                                    Character.player.money -= input;
                                                    System.out.println("보유한 금액 : " + Character.player.money + "\n=====================================================");
                                                    if (input < (new_pirates.money / 3)) {
                                                        tmp_fight = random.nextInt(10);
                                                        if (tmp_fight < 3 + Character.player.conciliation_atk - new_pirates.conciliation_def) {
                                                            System.out.println(new_pirates.name + "이 회유 당했습니다.\n");
                                                            new_pirates.hp = 0;
                                                            break;
                                                        } else {
                                                            System.out.println("회유에 실패 했습니다.\n");
                                                            break;
                                                        }
                                                    } else if (input < (new_pirates.money / 3 * 2) & input > (new_pirates.money / 3)) {
                                                        tmp_fight = random.nextInt(10);
                                                        if (tmp_fight < 5 + Character.player.conciliation_atk - new_pirates.conciliation_def) {
                                                            System.out.println(new_pirates.name + "이 회유 당했습니다.");
                                                            new_pirates.hp = 0;
                                                            break;
                                                        } else {
                                                            System.out.println("회유에 실패 했습니다.");
                                                            break;
                                                        }
                                                    } else if (input > (new_pirates.money / 3 * 2)) {
                                                        tmp_fight = random.nextInt(10);
                                                        if (tmp_fight < 7 + Character.player.conciliation_atk - new_pirates.conciliation_def) {
                                                            System.out.println(new_pirates.name + "이 회유 당했습니다.");
                                                            new_pirates.hp = 0;
                                                            break;
                                                        } else {
                                                            System.out.println("회유에 실패 했습니다.");
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                            break;
                                        } else if (input == 4) { // Letsgo
                                            ReturnLoop:
                                            while (true) {
                                                while (true) {
                                                    System.out.println("1. 화물창 비우기\n2. 회항 시도하기");
                                                    input = scan.nextInt();
                                                    if (input == 1) {
                                                        cntr.show_inven();
                                                        System.out.println("=======================");
                                                        if (cntr.distinguish_inven() == 0) {
                                                            System.out.println("화물창에 화물이 없습니다.");
                                                            break;
                                                        }
                                                        System.out.println("바다에 던질 화물을 선택하세요.");
                                                        input = scan.nextInt();
                                                        if (cntr.distinguish_inven() != 0) {
                                                            while (true) {
                                                                //Item[] tmp_item = new Item[12];
                                                                int count_tmp = 1;
                                                                for (int k = 0; k < Container.item_inven.length; k++) {
                                                                    if (Container.item_inven[k].stock != 0) {
                                                                        trade.tmp_item[count_tmp - 1].name = Container.item_inven[k].name;
                                                                        trade.tmp_item[count_tmp - 1].stock = Container.item_inven[k].stock;
                                                                        trade.tmp_item[count_tmp - 1].cbm = Container.item_inven[k].cbm;
                                                                        trade.tmp_item[count_tmp - 1].storage = Container.item_inven[k].storage;
                                                                        trade.tmp_item[count_tmp - 1].price = Container.item_inven[k].price;
                                                                        count_tmp++;
                                                                    }
                                                                }

                                                                if (trade.tmp_item[input - 1].storage < 5000) {
                                                                    if (trade.tmp_item[input - 1].name == trade.rice[0].name) {
                                                                        trade.show_rice_for_cntr(stage);
                                                                    } else if (trade.tmp_item[input - 1].name == trade.ginseng[0].name) {
                                                                        trade.show_ginseng_for_cntr(stage);
                                                                    } else if (trade.tmp_item[input - 1].name == trade.tea[0].name) {
                                                                        trade.show_tea_for_cntr(stage);
                                                                    } else if (trade.tmp_item[input - 1].name == trade.horse[0].name) {
                                                                        trade.show_horse_for_cntr(stage);
                                                                    } else if (trade.tmp_item[input - 1].name == trade.medicine[0].name) {
                                                                        trade.show_medicine_for_cntr(stage);
                                                                    } else if (trade.tmp_item[input - 1].name == trade.flavor[0].name) {
                                                                        trade.show_flavor_for_cntr(stage);
                                                                    }
                                                                    System.out.print("===========================================\n바다에 던질 화물을 선택하세요.");
                                                                    System.out.println("\t\t\t\t\t보유한 공간 : " + shipbuilding.give_main_vsl_container());
                                                                    int selected_in_sell = scan.nextInt();

                                                                    // 9/23복사
                                                                    if (trade.tmp_item[input - 1].name == trade.rice[0].name) { // 쌀
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.rice_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.rice_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.rice[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.rice[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.rice[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.rice[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.rice[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.rice_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    } else if (trade.tmp_item[input - 1].name == trade.ginseng[0].name) { // 인삼
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.ginseng_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.ginseng_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.ginseng[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.ginseng[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.ginseng[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.ginseng[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.ginseng[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.ginseng_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    } else if (trade.tmp_item[input - 1].name == trade.tea[0].name) { // 차
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.tea_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.tea_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.tea[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.tea[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.tea[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.tea[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.tea[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.tea_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    } else if (trade.tmp_item[input - 1].name == trade.horse[0].name) { // 말
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.horse_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.horse_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.horse[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.horse[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.horse[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.horse[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.horse[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.horse_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    } else if (trade.tmp_item[input - 1].name == trade.medicine[0].name) { // 약재
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.medicine_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.medicine_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.medicine[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.medicine[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.medicine[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.medicine[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.medicine[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.medicine_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    } else if (trade.tmp_item[input - 1].name == trade.flavor[0].name) { // 향료
                                                                        if (selected_in_sell == 20000) {
                                                                            trade.flavor_count = 0;
                                                                            break;
                                                                            //count = 0;
                                                                        } else if (selected_in_sell > trade.flavor_count + 1) {
                                                                            System.out.println("옳바른 숫자를 입력하세요.");
                                                                        } else {
                                                                            while (true) {
                                                                                System.out.println("던질 수량을 입력하세요.");
                                                                                int qtty = scan.nextInt();
                                                                                if (trade.flavor[selected_in_sell - 1].stock < qtty) {
                                                                                    System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                                } else {
                                                                                    trade.tmp_item[input - 1].stock += -qtty;
                                                                                    trade.flavor[selected_in_sell - 1].stock += -qtty;
                                                                                    if (trade.flavor[selected_in_sell - 1].stock <= 0) {
                                                                                        trade.flavor[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                    }
                                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                    //여기가 문제
                                                                                    System.out.println(trade.flavor[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                    Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                    trade.flavor_count = 0;
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    }
                                                                } else {
                                                                    System.out.println("던질 수량을 선택하세요.");
                                                                    int qtty = scan.nextInt();
                                                                    if (qtty > cntr.item_inven[cntr.get_number_through_num_in_inven(input)].stock) {
                                                                        System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                                    } else {
                                                                        cntr.input_data_inven(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, -qtty);
                                                                        trade.throw_data_storage(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, qtty);
                                                                        shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() + (cntr.get_cbm_through_name(cntr.get_name_in_inven(input)) * qtty));
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                            cntr.show_inven();
                                                            System.out.println("=====================");
                                                            //break;
                                                        } else {
                                                            System.out.println("옳바른 번호를 다시 입력하세요.");
                                                        }
                                                    } else if (input == 2) {
                                                        if (shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.75 < shipbuilding.get_vsl_container()) {
                                                            int a = random.nextInt(10);
                                                            if (a <= 8) {
                                                                System.out.println("회항에 성공했습니다.");

                                                                tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                                                int tmp = (Character.player.get_char_crew() - tmp_crew);
                                                                if (tmp <= 1) {
                                                                    Character.player.set_char_crew(1);
                                                                } else {
                                                                    Character.player.set_char_crew(tmp);
                                                                    if (tmp_crew != 0) {
                                                                        System.out.println("선원 " + tmp_crew + "명 죽었습니다.");
                                                                    }
                                                                }
                                                                ThreadAutoAttack.stop = true;
                                                                break AttackLoop;

                                                            } else {
                                                                System.out.println("회항에 실패 했습니다.\n");
                                                                break ReturnLoop;
                                                            }
                                                        } else if (shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.75 >= shipbuilding.get_vsl_container() & shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.5 < shipbuilding.get_vsl_container()) {
                                                            int a = random.nextInt(10);
                                                            if (a <= 6) {
                                                                System.out.println("회항에 성공했습니다.");

                                                                tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                                                int tmp = (Character.player.get_char_crew() - tmp_crew);
                                                                if (tmp <= 1) {
                                                                    Character.player.set_char_crew(1);
                                                                } else {
                                                                    Character.player.set_char_crew(tmp);
                                                                    if (tmp_crew != 0) {
                                                                        System.out.println("선원 " + tmp_crew + "명 죽었습니다.");
                                                                    }
                                                                }
                                                                ThreadAutoAttack.stop = true;
                                                                break AttackLoop;
                                                            } else {
                                                                System.out.println("회항에 실패 했습니다.\n");
                                                                break ReturnLoop;
                                                            }
                                                        } else if (shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.5 >= shipbuilding.get_vsl_container() & shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.25 < shipbuilding.get_vsl_container()) {
                                                            int a = random.nextInt(10);
                                                            if (a <= 4) {
                                                                System.out.println("회항에 성공했습니다.");

                                                                tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                                                int tmp = (Character.player.get_char_crew() - tmp_crew);
                                                                if (tmp <= 1) {
                                                                    Character.player.set_char_crew(1);
                                                                } else {
                                                                    Character.player.set_char_crew(tmp);
                                                                    if (tmp_crew != 0) {
                                                                        System.out.println("선원 " + tmp_crew + "명 죽었습니다.");
                                                                    }
                                                                }
                                                                ThreadAutoAttack.stop = true;
                                                                break AttackLoop;

                                                            } else {
                                                                System.out.println("회항에 실패 했습니다.\n");
                                                                break ReturnLoop;
                                                            }
                                                        } else if (shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.75 >= shipbuilding.get_vsl_container() & shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) * 0.5 < shipbuilding.get_vsl_container()) {
                                                            int a = random.nextInt(10);
                                                            if (a <= 2) {
                                                                System.out.println("회항에 성공했습니다.");

                                                                tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                                                int tmp = (Character.player.get_char_crew() - tmp_crew);
                                                                if (tmp <= 1) {
                                                                    Character.player.set_char_crew(1);
                                                                } else {
                                                                    Character.player.set_char_crew(tmp);
                                                                    if (tmp_crew != 0) {
                                                                        System.out.println("선원 " + tmp_crew + "명 죽었습니다.");
                                                                    }
                                                                }
                                                                ThreadAutoAttack.stop = true;
                                                                break AttackLoop;

                                                            } else {
                                                                System.out.println("회항에 실패 했습니다.\n");
                                                                break ReturnLoop;
                                                            }
                                                        }
                                                    } else {
                                                        System.out.println("옳바른 숫자를 다시 입력하세요.");
                                                    }
                                                    break;
                                                }
                                                //break;
                                            }
                                        } else {
                                            System.out.println("옳바른 숫자를 다시 입력하세요.");
                                        }
                                    }

                                    if (new_pirates.hp == 0) {
                                        //System.out.println(new_pirates.name + "이 죽었습니다.");
                                        ThreadAutoAttack.stop = true;
                                        Character.player.money += new_pirates.money;
                                        tmp_crew = random.nextInt((int) ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.hp) * 5 / 100) + 1);
                                        Character.player.set_char_crew(Character.player.get_char_crew() - tmp_crew);
                                        Character.player.hp += Character.player.hp * 0.1;
                                        Character.player.set_char_hp(Character.player.hp);
                                        if (tmp_crew != 0) {
                                            System.out.println("선원 " + tmp_crew + "명이 죽었습니다.");
                                        }
                                        if (Character.player.hp > (shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()))) { // 여기 배 체력으로 바꿔줘야함
                                            Character.player.hp = (shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()));
                                        }
                                        System.out.println("내부 수리를 통해 체력이 10% 회복하여 현재 체력은 " + Character.player.hp + "입니다.\n");
                                        System.out.println(new_pirates.name + "의 돈 " + new_pirates.money + "를 획득했습니다.");
                                        while (true) {
                                            int random_item = random.nextInt(11) + 1; // 12개중에 아이템 뽑는 것.
                                            int random_qtty = random.nextInt(20);
                                            if (trade.item_trade[random_item].cbm * random_qtty < 1000) {
                                                while (true) {
                                                    System.out.println(trade.item_trade[random_item].name + " " + random_qtty + "개 획득 했습니다. (개별 차지하는 공간 : " + trade.item_trade[random_item].cbm + " / 총 공간 : " + trade.item_trade[random_item].cbm * random_qtty + ")");
                                                    System.out.println("==================================");
                                                    System.out.println("화물창 총 공간 : " + shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) + "\t\t남은 공간 : " + shipbuilding.give_main_vsl_container());
                                                    System.out.println("==================================");
                                                    cntr.show_inven();

                                                    System.out.println("\n1. 물품 선적하기\n2. 화물창 비우기\n9. 나가기");
                                                    input = scan.nextInt();
                                                    if (input == 1) {
                                                        if (random_qtty == 0) {
                                                            System.out.println("선적 할 아이템이 없습니다.");
                                                        } else {
                                                            while (true) {
                                                                System.out.println("원하는 수량을 선택하세요.");
                                                                input = scan.nextInt();
                                                                if (trade.item_trade[random_item].cbm * input > shipbuilding.give_main_vsl_container()) {
                                                                    System.out.println("화물 공간이 부족합니다. 다시 선택하세요.");
                                                                } else if (input > random_qtty) {
                                                                    System.out.println("다시 입력하세요.");
                                                                } else {
                                                                    cntr.input_data_inven(trade.item_trade[random_item].name, input);
                                                                    trade.input_data_storage(trade.item_trade[random_item].name, input);
                                                                    shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() - trade.item_trade[random_item].cbm * input);
                                                                    System.out.println("선적 완료 됐습니다.");
                                                                    System.out.println("==================================");
                                                                    System.out.println("화물창 총 공간 : " + shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) + "\t\t남은 공간 : " + shipbuilding.give_main_vsl_container());
                                                                    System.out.println("==================================");
                                                                    cntr.show_inven();
                                                                    System.out.println("");
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        break;

                                                    } else if (input == 2) {
                                                        while (true) {
                                                            //Item[] tmp_item = new Item[12];
                                                            int count_tmp = 1;
                                                            for (int k = 0; k < Container.item_inven.length; k++) {
                                                                if (Container.item_inven[k].stock != 0) {
                                                                    trade.tmp_item[count_tmp - 1].name = Container.item_inven[k].name;
                                                                    trade.tmp_item[count_tmp - 1].stock = Container.item_inven[k].stock;
                                                                    trade.tmp_item[count_tmp - 1].cbm = Container.item_inven[k].cbm;
                                                                    trade.tmp_item[count_tmp - 1].storage = Container.item_inven[k].storage;
                                                                    trade.tmp_item[count_tmp - 1].price = Container.item_inven[k].price;
                                                                    count_tmp++;
                                                                }
                                                            }

                                                            if (trade.tmp_item[input - 1].storage < 5000) {
                                                                if (trade.tmp_item[input - 1].name == trade.rice[0].name) {
                                                                    trade.show_rice_for_cntr(stage);
                                                                } else if (trade.tmp_item[input - 1].name == trade.ginseng[0].name) {
                                                                    trade.show_ginseng_for_cntr(stage);
                                                                } else if (trade.tmp_item[input - 1].name == trade.tea[0].name) {
                                                                    trade.show_tea_for_cntr(stage);
                                                                } else if (trade.tmp_item[input - 1].name == trade.horse[0].name) {
                                                                    trade.show_horse_for_cntr(stage);
                                                                } else if (trade.tmp_item[input - 1].name == trade.medicine[0].name) {
                                                                    trade.show_medicine_for_cntr(stage);
                                                                } else if (trade.tmp_item[input - 1].name == trade.flavor[0].name) {
                                                                    trade.show_flavor_for_cntr(stage);
                                                                }
                                                                System.out.print("===========================================\n바다에 던질 화물을 선택하세요.");
                                                                System.out.println("\t\t\t\t\t보유한 공간 : " + shipbuilding.give_main_vsl_container());
                                                                int selected_in_sell = scan.nextInt();

                                                                // 9/23복사
                                                                if (trade.tmp_item[input - 1].name == trade.rice[0].name) { // 쌀
                                                                    if (selected_in_sell == 20000) {
                                                                        trade.rice_count = 0;
                                                                        break;
                                                                        //count = 0;
                                                                    } else if (selected_in_sell > trade.rice_count + 1) {
                                                                        System.out.println("옳바른 숫자를 입력하세요.");
                                                                    } else {
                                                                        while (true) {
                                                                            System.out.println("던질 수량을 입력하세요.");
                                                                            int qtty = scan.nextInt();
                                                                            if (trade.rice[selected_in_sell - 1].stock < qtty) {
                                                                                System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                            } else {
                                                                                trade.tmp_item[input - 1].stock += -qtty;
                                                                                trade.rice[selected_in_sell - 1].stock += -qtty;
                                                                                if (trade.rice[selected_in_sell - 1].stock <= 0) {
                                                                                    trade.rice[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                }
                                                                                shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                //여기가 문제
                                                                                System.out.println(trade.rice[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                trade.rice_count = 0;
                                                                                break;
                                                                            }
                                                                        }
                                                                    }
                                                                    break;
                                                                } else if (trade.tmp_item[input - 1].name == trade.ginseng[0].name) { // 인삼
                                                                    if (selected_in_sell == 20000) {
                                                                        trade.ginseng_count = 0;
                                                                        break;
                                                                        //count = 0;
                                                                    } else if (selected_in_sell > trade.ginseng_count + 1) {
                                                                        System.out.println("옳바른 숫자를 입력하세요.");
                                                                    } else {
                                                                        while (true) {
                                                                            System.out.println("던질 수량을 입력하세요.");
                                                                            int qtty = scan.nextInt();
                                                                            if (trade.ginseng[selected_in_sell - 1].stock < qtty) {
                                                                                System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                            } else {
                                                                                trade.tmp_item[input - 1].stock += -qtty;
                                                                                trade.ginseng[selected_in_sell - 1].stock += -qtty;
                                                                                if (trade.ginseng[selected_in_sell - 1].stock <= 0) {
                                                                                    trade.ginseng[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                }
                                                                                shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                //여기가 문제
                                                                                System.out.println(trade.ginseng[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                trade.ginseng_count = 0;
                                                                                break;
                                                                            }
                                                                        }
                                                                    }
                                                                    break;
                                                                } else if (trade.tmp_item[input - 1].name == trade.tea[0].name) { // 차
                                                                    if (selected_in_sell == 20000) {
                                                                        trade.tea_count = 0;
                                                                        break;
                                                                        //count = 0;
                                                                    } else if (selected_in_sell > trade.tea_count + 1) {
                                                                        System.out.println("옳바른 숫자를 입력하세요.");
                                                                    } else {
                                                                        while (true) {
                                                                            System.out.println("던질 수량을 입력하세요.");
                                                                            int qtty = scan.nextInt();
                                                                            if (trade.tea[selected_in_sell - 1].stock < qtty) {
                                                                                System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                            } else {
                                                                                trade.tmp_item[input - 1].stock += -qtty;
                                                                                trade.tea[selected_in_sell - 1].stock += -qtty;
                                                                                if (trade.tea[selected_in_sell - 1].stock <= 0) {
                                                                                    trade.tea[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                }
                                                                                shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                //여기가 문제
                                                                                System.out.println(trade.tea[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                trade.tea_count = 0;
                                                                                break;
                                                                            }
                                                                        }
                                                                    }
                                                                    break;
                                                                } else if (trade.tmp_item[input - 1].name == trade.horse[0].name) { // 말
                                                                    if (selected_in_sell == 20000) {
                                                                        trade.horse_count = 0;
                                                                        break;
                                                                        //count = 0;
                                                                    } else if (selected_in_sell > trade.horse_count + 1) {
                                                                        System.out.println("옳바른 숫자를 입력하세요.");
                                                                    } else {
                                                                        while (true) {
                                                                            System.out.println("던질 수량을 입력하세요.");
                                                                            int qtty = scan.nextInt();
                                                                            if (trade.horse[selected_in_sell - 1].stock < qtty) {
                                                                                System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                            } else {
                                                                                trade.tmp_item[input - 1].stock += -qtty;
                                                                                trade.horse[selected_in_sell - 1].stock += -qtty;
                                                                                if (trade.horse[selected_in_sell - 1].stock <= 0) {
                                                                                    trade.horse[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                }
                                                                                shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                //여기가 문제
                                                                                System.out.println(trade.horse[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                trade.horse_count = 0;
                                                                                break;
                                                                            }
                                                                        }
                                                                    }
                                                                    break;
                                                                } else if (trade.tmp_item[input - 1].name == trade.medicine[0].name) { // 약재
                                                                    if (selected_in_sell == 20000) {
                                                                        trade.medicine_count = 0;
                                                                        break;
                                                                        //count = 0;
                                                                    } else if (selected_in_sell > trade.medicine_count + 1) {
                                                                        System.out.println("옳바른 숫자를 입력하세요.");
                                                                    } else {
                                                                        while (true) {
                                                                            System.out.println("던질 수량을 입력하세요.");
                                                                            int qtty = scan.nextInt();
                                                                            if (trade.medicine[selected_in_sell - 1].stock < qtty) {
                                                                                System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                            } else {
                                                                                trade.tmp_item[input - 1].stock += -qtty;
                                                                                trade.medicine[selected_in_sell - 1].stock += -qtty;
                                                                                if (trade.medicine[selected_in_sell - 1].stock <= 0) {
                                                                                    trade.medicine[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                }
                                                                                shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                //여기가 문제
                                                                                System.out.println(trade.medicine[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                trade.medicine_count = 0;
                                                                                break;
                                                                            }
                                                                        }
                                                                    }
                                                                    break;
                                                                } else if (trade.tmp_item[input - 1].name == trade.flavor[0].name) { // 향료
                                                                    if (selected_in_sell == 20000) {
                                                                        trade.flavor_count = 0;
                                                                        break;
                                                                        //count = 0;
                                                                    } else if (selected_in_sell > trade.flavor_count + 1) {
                                                                        System.out.println("옳바른 숫자를 입력하세요.");
                                                                    } else {
                                                                        while (true) {
                                                                            System.out.println("던질 수량을 입력하세요.");
                                                                            int qtty = scan.nextInt();
                                                                            if (trade.flavor[selected_in_sell - 1].stock < qtty) {
                                                                                System.out.println("재고보다 많은 양을 버릴 수 없습니다. 다시 입력하세요.");
                                                                            } else {
                                                                                trade.tmp_item[input - 1].stock += -qtty;
                                                                                trade.flavor[selected_in_sell - 1].stock += -qtty;
                                                                                if (trade.flavor[selected_in_sell - 1].stock <= 0) {
                                                                                    trade.flavor[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                                                                }
                                                                                shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() + trade.tmp_item[input - 1].cbm * qtty);

                                                                                //여기가 문제
                                                                                System.out.println(trade.flavor[selected_in_sell - 1].name + " " + qtty + "개 버렸습니다.");
                                                                                Container.item_inven[Container.get_number_through_name_in_inven(trade.tmp_item[input - 1].name)].stock = trade.tmp_item[input - 1].stock;
                                                                                trade.flavor_count = 0;
                                                                                break;
                                                                            }
                                                                        }
                                                                    }
                                                                    break;
                                                                }
                                                            } else {
                                                                System.out.println("던질 수량을 선택하세요.");
                                                                int qtty = scan.nextInt();
                                                                if (qtty > cntr.item_inven[cntr.get_number_through_num_in_inven(input)].stock) {
                                                                    System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                                } else {
                                                                    cntr.input_data_inven(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, -qtty);
                                                                    trade.throw_data_storage(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, qtty);
                                                                    shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() + (cntr.get_cbm_through_name(cntr.get_name_in_inven(input)) * qtty));
                                                                    break;
                                                                }
                                                            }
                                                        }


                                                        break;
                                                    } else if (input == 9) {
                                                        break;
                                                    }
                                                }
                                                break;
                                            } else {
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
//                            System.out.println(ThreadTimeDate.tmp_howmanydays);
//                            ThreadCountTime.howmanydays += ThreadTimeDate.tmp_howmanydays; // 날짜 계산
//                            ThreadCountTime.work = true; // 시간 멈춤 (이동중)
//                            ThreadWindDirection.work = true; // 바람 멈춤 (이동중)


                            Character.player.set_char_hp(Character.player.hp);
                            stage = go_port;
                            //marineChart.close();
                        }
                        while (true) {
                            if (!ThreadTimeStorage.work) {
                                ThreadCountTime.howmanydays += ThreadTimeStorage.tmp_howmanydays; // 날짜 계산
                                //System.out.println(ThreadWindDirection.wind_result);
                                System.out.println(ThreadTimeStorage.tmp_howmanydays + "일 소요되었습니다.");
                                break;
                            }
                        }

                    } else if (input == 9) {
                        break;
                    }
                    break;
                }

                if (introMusic.isAlive()) { // 다시켜지는에러
                    introMusic.interrupt();
                }
                introMusic = new ThreadMusicPlayer("normal.mp3", true);
                introMusic.start(); // 인트로음악 음악중지

                fightMusic.close(); // 전투음악 음악중지

                ThreadCountTime.work = true; // 시간 멈춤 (이동중)
                ThreadWindDirection.work = true; // 바람 멈춤 (이동중)

            } // 항해
            else if (input == 2) { // 교역
                if (ThreadCountTime.noontime == true) {

                    while (true) {
                        int qtty = 0;
                        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                        System.out.println("\n어서오게나, 둘러보게\n1. 상품 구매\n2. 상품 판매\n9. 나가기");
                        input = scan.nextInt();
                        if (input == 1) {
                            while (true) {
                                trade.show_purchase(stage);
                                System.out.println("===========================================");
                                System.out.println("원하는 상품을 고르세요.\t\t\t\t\t보유한 돈 : " + Character.player.get_char_money() + "\t\t보유한 공간 : " + shipbuilding.give_main_vsl_container());
                                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t나가려면 9을 입력하세요.");
                                input = scan.nextInt();
                                if (1 <= input & input <= 3) {
                                    System.out.println("구매 원하시는 수량을 입력하세요.");
                                    qtty = scan.nextInt();
                                    trade.purchase(stage, input, qtty, Character.player.get_char_money(), shipbuilding.get_vsl_container());
                                    if (stage == 1 & trade.item_trade[input - 1].price * qtty <= Character.player.get_char_money() & trade.item_trade[input - 1].cbm * qtty <= shipbuilding.get_vsl_container()) {
                                        Character.player.set_char_money(Character.player.get_char_money() - (trade.item_trade[input - 1].price * qtty));
                                        shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() - (trade.item_trade[input - 1].cbm * qtty));
                                    } // 스테이지별로 교역 추가해야함
                                    else if (stage == 2 & trade.item_trade[input + 2].price * qtty <= Character.player.get_char_money() & trade.item_trade[input + 2].cbm * qtty <= shipbuilding.get_vsl_container()) {
                                        Character.player.set_char_money(Character.player.get_char_money() - (trade.item_trade[input + 2].price * qtty));
                                        shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() - (trade.item_trade[input + 2].cbm * qtty));

                                    } else if (stage == 3 & trade.item_trade[input + 5].price * qtty <= Character.player.get_char_money() & trade.item_trade[input + 5].cbm * qtty <= shipbuilding.get_vsl_container()) {
                                        Character.player.set_char_money(Character.player.get_char_money() - (trade.item_trade[input + 5].price * qtty));
                                        shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() - (trade.item_trade[input + 5].cbm * qtty));

                                    } else if (stage == 4 & trade.item_trade[input + 8].price * qtty <= Character.player.get_char_money() & trade.item_trade[input + 8].cbm * qtty <= shipbuilding.get_vsl_container()) {
                                        Character.player.set_char_money(Character.player.get_char_money() - (trade.item_trade[input + 8].price * qtty));
                                        shipbuilding.set_vsl_container(shipbuilding.get_vsl_container() - (trade.item_trade[input + 8].cbm * qtty));
                                    }
                                } else if (input == 9) {

                                    break;
                                } else {
                                    System.out.println("다시 입력하세요.");
                                }
                            }
                        } else if (input == 2) {
                            while (true) {
                                System.out.println("값 잘 쳐줌세, 여기만큼 잘 사주는 곳 없다네\t\t\t나가려면 20000을 입력하세요.\n");

                                if (trade.show_sell(stage, shipbuilding.give_main_vsl_container()) == 0) {
                                    int tmp = trade.sell(Character.player.get_char_money(), shipbuilding.give_main_vsl_container());
                                    if (tmp == 1) {
                                        break;
                                    } else {
                                        Character.player.set_char_money(tmp);
                                        shipbuilding.set_vsl_container(trade.get_cbm_after_selling());
                                    }
                                }
                                System.out.println("\n보유한 금액 : " + Character.player.get_char_money());
                                System.out.println("========================\n1. 계속 판매\n9. 나가기");
                                input = scan.nextInt();
                                if (input == 9) {
                                    break;
                                }
                            }
                        } else if (input == 9) {
                            //ThreadWindDirection.stop = true; // 시간 다시 시작
                            break;
                        }
                    }

                }
                else if (ThreadCountTime.black_market == true) { // 암시장
                    while (true) {
                        System.out.println("\n조용히 살 것만 사고 나가시게\n1. 상품 구매\n9. 나가기");
                        input = scan.nextInt();
                        if (input == 1) {
                            while (true) {
                                trade.show_purchase_blackMarket(stage);
                                System.out.println("===========================================");
                                System.out.println("원하는 상품을 고르세요.\t\t\t\t\t보유한 돈 : " + Character.player.get_char_money());
                                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t나가려면 9을 입력하세요.");
                                input = scan.nextInt();
                                if (stage == 1) {
                                    if (1 <= input & input <= 3) {
                                        // 1이면 항로, 2이면 해도, 3이면 가격표
                                        if (trade.purchase_blackMarket(stage, max_stage, input) == 1) {
                                            max_stage++;
                                        }
                                    } else if (input == 4) {
                                        if (trade.purchase_blackMarket(stage, max_stage, input) == 2) {
                                            if (marineChart.isAlive()) { // 다시켜지는에러
                                                marineChart.interrupt();
                                            }
                                            marineChart = new ThreadChart();
                                            marineChart.start();
                                        }
                                    } else if (input == 5) {
                                        trade.purchase_blackMarket(stage, max_stage, input);
                                        continue;
                                    } else if (input == 9) {
                                        break;
                                    } else {
                                        System.out.println("다시 입력하세요.");
                                    }
                                } else if (stage == 2) {
                                    if (1 <= input & input <= 2) {
                                        // 1이면 항로, 2이면 해도, 3이면 가격표
                                        if (trade.purchase_blackMarket(stage, max_stage, input) == 1) {
                                            max_stage++;
                                        }
                                    } else if (input == 3) {
                                        if (trade.purchase_blackMarket(stage, max_stage, input) == 2) {
                                            if (marineChart.isAlive()) { // 다시켜지는에러
                                                marineChart.interrupt();
                                            }
                                            marineChart = new ThreadChart();
                                            marineChart.start();
                                        }
                                    } else if (input == 4) {
                                        trade.purchase_blackMarket(stage, max_stage, input);
                                        continue;
                                    } else if (input == 9) {
                                        break;
                                    } else {
                                        System.out.println("다시 입력하세요.");
                                    }
                                } else if (stage == 3) {
                                    if (input == 1) {
                                        // 1이면 항로, 2이면 해도, 3이면 가격표
                                        if (trade.purchase_blackMarket(stage, max_stage, input) == 1) {
                                            max_stage++;
                                        }
                                    } else if (input == 2) {
                                        if (trade.purchase_blackMarket(stage, max_stage, input) == 2) {
                                            if (marineChart.isAlive()) { // 다시켜지는에러
                                                marineChart.interrupt();
                                            }
                                            marineChart = new ThreadChart();
                                            marineChart.start();
                                        }
                                    } else if (input == 3) {
                                        trade.purchase_blackMarket(stage, max_stage, input);
                                        continue;
                                    } else if (input == 9) {
                                        break;
                                    } else {
                                        System.out.println("다시 입력하세요.");
                                    }
                                } else if (stage == 4) {
                                    if (1 <= input & input <= 2) {
                                        // 1이면 항로, 2이면 해도, 3이면 가격표
                                        if (input == 1) {
                                            if (trade.purchase_blackMarket(stage, max_stage, input) == 2) {
                                                if (marineChart.isAlive()) { // 다시켜지는에러
                                                    marineChart.interrupt();
                                                }
                                                marineChart = new ThreadChart();
                                                marineChart.start();
                                            }
                                        } else if (input == 2) {
                                            trade.purchase_blackMarket(stage, max_stage, input);
                                            continue;
                                        } else if (input == 9) {
                                            break;
                                        } else {
                                            System.out.println("다시 입력하세요.");
                                        }
                                    } else if (input == 9) {
                                        break;
                                    } else {
                                        System.out.println("다시 입력하세요.");
                                    }
                                }
                            }
                        } else if (input == 9) {
                            break;
                        }
                    }
                } else if (ThreadCountTime.noontime == false) { // 영업종료
                    System.out.println("영업종료 되었습니다.");
                }

            } // 교역
            else if (input == 3) { // 조선소
                while (true) {
                    //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("\n우리 조선소로 찾아와줘소 고맙네. \n원하는게 뭔가?");
                    System.out.println("1. 선박 구매\n2. 업그레이드\n3. 수리\n9. 나가기");
                    input = scan.nextInt();
                    if (input == 1) {
                        while (true) {
                            shipbuilding.show_vsl_list();
                            System.out.println("===========================================");
                            System.out.println("원하는 배를 고르세요.\t\t\t\t\t\t\t\t\t\t\t\t\t\t 보유한 돈 : " + Character.player.get_char_money());
                            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t나가려면 9을 입력하세요.");
                            input = scan.nextInt();
                            if (input > 3 & input != 9) {
                                System.out.println("다시 입력하세요.");
                            } else if (input == 9) {
                                break;
                            } else {
                                if (cntr.distinguish_inven() != 0) {
                                    System.out.println("화물창에 화물이 있어 새로운 배를 구매 할 수 없습니다.\n화물 창을 비운 후 구매하세요.");
                                    break;
                                }
                                if (cntr.distinguish_secret() != 0) {
                                    while (true) {
                                        System.out.println("비밀 화물창의 위치를 아무도 모르기 때문에 물건은 폐기 됩니다.\n1. 계속 진행\n9. 나가기");
                                        input = scan.nextInt();
                                        if (input == 1) {
                                            upgrade_count = 0; // 배 업그레이드 초기화
                                            shipbuilding.setUpgrade_actual_count(0);
                                            shipbuilding.purchase(input, Character.player.get_char_money());
                                            Character.player.set_char_money(Character.player.get_char_money() - shipbuilding.give_main_vsl_price());
                                            Character.player.set_char_hp(shipbuilding.get_vsl_hp());
                                            shipbuilding.set_secret_cntr(shipbuilding.get_vsl_org_secret(shipbuilding.give_main_vsl_name())); // 비밀화물창 원래대로 업데이트
                                            shipbuilding.org_secret_space = 0;
                                            cntr.clear_secret();
                                            trade.clear_secret_storage();
                                            break;
                                            //Character.player.set_char_crew();
                                        } else if (input == 9) {
                                            break;
                                        }
                                    }
                                    break;
                                } else {
                                    upgrade_count = 0; // 배 업그레이드 초기화
                                    shipbuilding.setUpgrade_actual_count(0);
                                    shipbuilding.purchase(input, Character.player.get_char_money());
                                    Character.player.set_char_money(Character.player.get_char_money() - shipbuilding.give_main_vsl_price());
                                    Character.player.set_char_hp(shipbuilding.get_vsl_hp());
                                    shipbuilding.set_secret_cntr(shipbuilding.get_vsl_org_secret(shipbuilding.give_main_vsl_name())); // 비밀화물창 원래대로 업데이트
                                    shipbuilding.org_secret_space = 0;
                                    cntr.clear_secret();
                                    trade.clear_secret_storage();
                                    break;
                                }
                            }
                        }
                    } else if (input == 2) {
                        shipbuilding.upgrade(shipbuilding.give_main_vsl_name(), upgrade_count, Character.player.get_char_money());
                        if (shipbuilding.getUpgrade_actual_count() > 0 & shipbuilding.getUpgrade_actual_count() < 3 & shipbuilding.getUpgrade_actual_count() - upgrade_count == 1) {
                            upgrade_count++;
                            Character.player.set_char_money(Character.player.get_char_money() - 2000);
                            //System.out.println("test1");
                        } else if (shipbuilding.getUpgrade_actual_count() >= 3 & shipbuilding.getUpgrade_actual_count() <= 5 & shipbuilding.getUpgrade_actual_count() - upgrade_count >= 1) {
                            if (shipbuilding.getUpgrade_actual_count() - upgrade_count == 2) {
                                upgrade_count++;
                            }
                            upgrade_count++;
                            Character.player.set_char_money(Character.player.get_char_money() - 3000);
                            //System.out.println("test2");
                        }
                    } else if (input == 3) {
                        //int tmp = 0;
                        System.out.println("배 상태를 먼저 점검해보아야겠네, 잠시만 기다려 주게나");
                        if (Character.player.get_char_hp() >= shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) / 2) {
                            System.out.println("배 손상이 그렇게 크지 않군, 가격은 " + ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.get_char_hp()) * 100) + "이코 이네.");
                            //System.out.println(Character.player.get_char_hp()); //테스트용
                            if (Character.player.get_char_money() - ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.get_char_hp()) * 100) < 0) {
                                System.out.println("돈이 부족하군, 수리를 위해 인력이 투입되어야해서 수리가 불가하네");
                                break;
                            } else {
                                Character.player.set_char_money(Character.player.get_char_money() - ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.get_char_hp()) * 100));
                            }
                        } else {
                            System.out.println("배가 꽤 손상 되었군, 가격은 " + ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.get_char_hp()) * 150) + "이코 이네.");
                            Character.player.set_char_money(Character.player.get_char_money() - ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.get_char_hp()) * 150));
                            if (Character.player.get_char_money() - ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.get_char_hp()) * 100) < 0) {
                                System.out.println("돈이 부족하군, 수리를 위해 인력이 투입되어야해서 수리가 불가하네");
                                break;
                            } else {
                                Character.player.set_char_money(Character.player.get_char_money() - ((shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) - Character.player.get_char_hp()) * 100));
                            }
                        }
                        Character.player.set_char_hp(shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()));
                        System.out.println("수리가 완료 되었습니다.\n나가시려면 아무 숫자를 입력하세요.");
                        input = scan.nextInt();
                    } else if (input == 9) {
                        break;
                    }
                }
            } // 조선소
            else if (input == 4) { // 술집
                while (true) {
                    System.out.println("\n무슨일로 찾아오셨어요?\n1. 술 마시기\n2. 선원 모집하기\n9. 나가기");
                    input = scan.nextInt();
                    if (input == 1) {
                        while (true) {
                            System.out.println("술 한잔 해요. " + Character.player.name + "씨가 와서 다들 기뻐하고 있어요.");
                            System.out.println("보유한 금액 : " + Character.player.get_char_money());
                            System.out.println("1. 마시기 (100 이코)\n9. 나가기.");
                            input = scan.nextInt();
                            if (input == 1) {
                                while (true) {
                                    if (Character.player.get_char_money() < 100) {
                                        System.out.println("돈이 부족합니다.");
                                        break;
                                    }
                                    Character.player.set_char_money(Character.player.get_char_money() - 100);
                                    input = random.nextInt(3);
                                    if (Character.player.get_special_function() == 4) {
                                        input++;
                                    }
                                    bar.tips(input);
                                    break;
                                }
                            } else if (input == 9) {
                                break;
                            }
                        }
                    } else if (input == 2) {
                        while (true) {
                            System.out.println("6000이코를 지불하여 선원 모집을 하시겠습니까?");
                            System.out.println("현재 선원수 : " + Character.player.get_char_crew() + "\t\t(최소 선원수 : " + shipbuilding.give_main_vsl_min() + " 최대 선원수 : " + shipbuilding.give_main_vsl_max() + ")");
                            System.out.println("===========================================");
                            System.out.println("1. 모집하기\n9. 나가기");
                            input = scan.nextInt();
                            if (input == 1) {
                                if (Character.player.get_char_money() < 600) {
                                    System.out.println("돈이 부족하여 선원을 모집 할 수 없습니다.");
                                    break;
                                } else if (Character.player.get_char_crew() >= shipbuilding.give_main_vsl_max()) {
                                    System.out.println("현재 선박의 정원이 모두 차 있습니다.");
                                    break;
                                } else {
                                    Character.player.set_char_money(Character.player.get_char_money() - 600);
                                    bar.crew_applicants(Character.player.get_char_crew(), shipbuilding.give_main_vsl_min(), shipbuilding.give_main_vsl_max(), stage);
                                    Character.player.set_char_crew(Character.player.get_char_crew() + bar.give_main_nominated_crew());
                                }
                            } else if (input == 9) {
                                break;
                            }
                        }
                    } else if (input == 9) {
                        break;
                    }
                }
            } // 술집
            else if (input == 5) { // 나의 정보
                System.out.println("==================================");
                System.out.println("캐릭터명 : " + Character.player.get_char_name() +
                        "\n선박명 : " + shipbuilding.give_main_vsl_name() +
                        "\n선박 체력 : " + Character.player.get_char_hp() + "/" + shipbuilding.get_vsl_org_hp(shipbuilding.give_main_vsl_name()) +
                        "\n선원 : " + Character.player.get_char_crew() + "/" + shipbuilding.get_vsl_org_crew(shipbuilding.give_main_vsl_name()) + "\t(최소 선원수 : " + shipbuilding.give_main_vsl_min() + ")" +
                        "\n돈 : " + Character.player.get_char_money() +
                        "\n주 활동 지역 : " + port.portname[port.go_often_stage()]);
                System.out.println("==================================\n나가려면 아무 숫자를 입력하세요.");
                input = scan.nextInt();
            } // 내정보
            else if (input == 6) { // 화물창
                int qtty = 0;
                while (true) {
                    if (upgrade_count < 3) {
                        System.out.println("==================================");
                        System.out.println("화물창 총 공간 : " + shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) + "\t\t남은 공간 : " + shipbuilding.give_main_vsl_container());
                        System.out.println("==================================");

                        cntr.show_inven();
                        System.out.println("나가려면 아무 숫자를 입력하세요.");
                        input = scan.nextInt();
                        break;
                    } else if (upgrade_count >= 3) {
                        int selected_for_secret = 0;
                        System.out.println("==================================");
                        System.out.println("화물창 총 공간 : " + shipbuilding.get_vsl_org_cntr(shipbuilding.give_main_vsl_name()) + "\t\t남은 공간 : " + shipbuilding.give_main_vsl_container());
                        System.out.println("==================================");

                        cntr.show_inven();

                        System.out.println("=====================================");
                        System.out.println("비밀창 총 공간 : " + shipbuilding.org_secret_space + "\t\t비밀창 남은 공간 : " + shipbuilding.get_secret_cntr());
                        System.out.println("=====================================");

                        cntr.show_secret();

                        System.out.println("\n1. 비밀 화물창에 적재하기\n2. 비밀 화물창에서 적출하기\n9. 나가기");
                        input = scan.nextInt();
                        if (input == 1) {
                            while (true) {
                                if (cntr.distinguish_inven() == 0) {
                                    System.out.println("화물창에 화물이 없습니다.");
                                    break;
                                }
                                System.out.println("비밀화물창에 적입할 화물을 선택하세요.");
                                input = scan.nextInt();

                                if (input > cntr.item_inven.length | input <= 0 | cntr.item_inven[cntr.get_number_through_num_in_inven(input)].stock == 0) {
                                    System.out.println("옳바른 숫자를 입력하세요.");
                                    break;
                                } else if (cntr.distinguish_inven() != 0) {
                                    while (true) {
                                        if (cntr.item_inven[cntr.get_number_through_num_in_inven(input)].storage < 5000) {
                                            //System.out.println(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name);//test // 정확히 잘 뽑아냈음
                                            //쌀
                                            if (cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name == trade.rice[0].name) {
                                                trade.show_rice_for_cntr(stage);
                                                System.out.println("===========================================\n적입할 슬롯을 선택하세요.");
                                                selected_for_secret = scan.nextInt(); // 여기서
                                                if (selected_for_secret > trade.rice.length | selected_for_secret <= 0 | trade.rice[selected_for_secret - 1].stock == 0) {
                                                    System.out.println("옳바른 숫자를 입력하세요.");
                                                    break;
                                                } else {
                                                    System.out.println("적입할 수량을 선택하세요.");
                                                    qtty = scan.nextInt();
                                                    if (qtty > trade.rice[selected_for_secret - 1].stock) {
                                                        System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                    } else if ((trade.rice[selected_for_secret - 1].cbm * qtty) > shipbuilding.get_secret_cntr()) {
                                                        System.out.println("적재 공간이 부족합니다.");
                                                    } else {
                                                        //sell 할때처럼 비워지면 업데이트해줘야함
                                                        trade.rice[selected_for_secret - 1].stock += -qtty;
                                                        for (int i = 0; i < 14; i++) { //secret에 넣는 과정
                                                            if (cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name == trade.rice_secret[i].name & trade.rice[i].storage == trade.rice_secret[i].storage) { // 이부분 조금 이상함
                                                                trade.rice_secret[i].stock += qtty;
                                                                break;
                                                            }
                                                        }
                                                        if (trade.rice[selected_for_secret - 1].stock == 0) {
                                                            trade.rice[selected_for_secret - 1].storage = Item.make_item(2).storage;
                                                        }
                                                        trade.rice_count = 0;
                                                        cntr.input_data_secret(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, qtty);
                                                        cntr.input_data_inven(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, -qtty);
                                                        shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() + trade.rice[selected_for_secret - 1].cbm * qtty);
                                                        //인벤창 남은 공간 늘려주는 것
                                                        shipbuilding.set_secret_cntr(shipbuilding.get_secret_cntr() - trade.rice[selected_for_secret - 1].cbm * qtty);
                                                        //비밀 창 남은 공간 줄여주는 것
                                                        System.out.println(cntr.item_inven[input - 1].name);//test
                                                        break;
                                                    }
                                                }
                                            } // 인삼
                                            else if (cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name == trade.ginseng[0].name) {
                                                trade.show_ginseng_for_cntr(stage);
                                                System.out.println("===========================================\n적입할 슬롯을 선택하세요.");
                                                selected_for_secret = scan.nextInt(); // 여기서
                                                if (selected_for_secret > trade.ginseng.length | selected_for_secret <= 0 | trade.ginseng[selected_for_secret - 1].stock == 0) {
                                                    System.out.println("옳바른 숫자를 입력하세요.");
                                                    break;
                                                } else {
                                                    System.out.println("적입할 수량을 선택하세요.");
                                                    qtty = scan.nextInt();
                                                    if (qtty > trade.ginseng[selected_for_secret - 1].stock) {
                                                        System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                    } else if ((trade.ginseng[selected_for_secret - 1].cbm * qtty) > shipbuilding.get_secret_cntr()) {
                                                        System.out.println("적재 공간이 부족합니다.");
                                                    } else {
                                                        //sell 할때처럼 비워지면 업데이트해줘야함
                                                        trade.ginseng[selected_for_secret - 1].stock += -qtty;
                                                        for (int i = 0; i < 14; i++) { //secret에 넣는 과정
                                                            if (cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name == trade.ginseng_secret[i].name & trade.ginseng[i].storage == trade.ginseng_secret[i].storage) { // 이부분 조금 이상함
                                                                trade.ginseng_secret[i].stock += qtty;
                                                                break;
                                                            }
                                                        }
                                                        if (trade.ginseng[selected_for_secret - 1].stock == 0) {
                                                            trade.ginseng[selected_for_secret - 1].storage = Item.make_item(3).storage;
                                                        }
                                                        trade.ginseng_count = 0;
                                                        cntr.input_data_secret(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, qtty);
                                                        cntr.input_data_inven(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, -qtty);
                                                        shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() + trade.ginseng[selected_for_secret - 1].cbm * qtty);
                                                        //인벤창 남은 공간 늘려주는 것
                                                        shipbuilding.set_secret_cntr(shipbuilding.get_secret_cntr() - trade.ginseng[selected_for_secret - 1].cbm * qtty);
                                                        //비밀 창 남은 공간 줄여주는 것
                                                        System.out.println(cntr.item_inven[input - 1].name);//test
                                                        break;
                                                    }
                                                }
                                            } // 차
                                            else if (cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name == trade.tea[0].name) {
                                                trade.show_tea_for_cntr(stage);
                                                System.out.println("===========================================\n적입할 슬롯을 선택하세요.");
                                                selected_for_secret = scan.nextInt(); // 여기서
                                                if (selected_for_secret > trade.tea.length | selected_for_secret <= 0 | trade.tea[selected_for_secret - 1].stock == 0) {
                                                    System.out.println("옳바른 숫자를 입력하세요.");
                                                    break;
                                                } else {
                                                    System.out.println("적입할 수량을 선택하세요.");
                                                    qtty = scan.nextInt();
                                                    if (qtty > trade.tea[selected_for_secret - 1].stock) {
                                                        System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                    } else if ((trade.tea[selected_for_secret - 1].cbm * qtty) > shipbuilding.get_secret_cntr()) {
                                                        System.out.println("적재 공간이 부족합니다.");
                                                    } else {
                                                        //sell 할때처럼 비워지면 업데이트해줘야함
                                                        trade.tea[selected_for_secret - 1].stock += -qtty;
                                                        for (int i = 0; i < 14; i++) { //secret에 넣는 과정
                                                            if (cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name == trade.tea_secret[i].name & trade.tea[i].storage == trade.tea_secret[i].storage) { // 이부분 조금 이상함
                                                                trade.tea_secret[i].stock += qtty;
                                                                break;
                                                            }
                                                        }
                                                        if (trade.tea[selected_for_secret - 1].stock == 0) {
                                                            trade.tea[selected_for_secret - 1].storage = Item.make_item(4).storage;
                                                        }
                                                        trade.tea_count = 0;
                                                        cntr.input_data_secret(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, qtty);
                                                        cntr.input_data_inven(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, -qtty);
                                                        shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() + trade.tea[selected_for_secret - 1].cbm * qtty);
                                                        //인벤창 남은 공간 늘려주는 것
                                                        shipbuilding.set_secret_cntr(shipbuilding.get_secret_cntr() - trade.tea[selected_for_secret - 1].cbm * qtty);
                                                        //비밀 창 남은 공간 줄여주는 것
                                                        System.out.println(cntr.item_inven[input - 1].name);//test
                                                        break;
                                                    }
                                                }
                                            } // 말
                                            else if (cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name == trade.horse[0].name) {
                                                trade.show_horse_for_cntr(stage);
                                                System.out.println("===========================================\n적입할 슬롯을 선택하세요.");
                                                selected_for_secret = scan.nextInt(); // 여기서
                                                if (selected_for_secret > trade.horse.length | selected_for_secret <= 0 | trade.horse[selected_for_secret - 1].stock == 0) {
                                                    System.out.println("옳바른 숫자를 입력하세요.");
                                                    break;
                                                } else {
                                                    System.out.println("적입할 수량을 선택하세요.");
                                                    qtty = scan.nextInt();
                                                    if (qtty > trade.horse[selected_for_secret - 1].stock) {
                                                        System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                    } else if ((trade.horse[selected_for_secret - 1].cbm * qtty) > shipbuilding.get_secret_cntr()) {
                                                        System.out.println("적재 공간이 부족합니다.");
                                                    } else {
                                                        //sell 할때처럼 비워지면 업데이트해줘야함
                                                        trade.horse[selected_for_secret - 1].stock += -qtty;
                                                        for (int i = 0; i < 14; i++) { //secret에 넣는 과정
                                                            if (cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name == trade.horse_secret[i].name & trade.horse[i].storage == trade.horse_secret[i].storage) { // 이부분 조금 이상함
                                                                trade.horse_secret[i].stock += qtty;
                                                                break;
                                                            }
                                                        }
                                                        if (trade.horse[selected_for_secret - 1].stock == 0) {
                                                            trade.horse[selected_for_secret - 1].storage = Item.make_item(6).storage;
                                                        }
                                                        trade.horse_count = 0;
                                                        cntr.input_data_secret(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, qtty);
                                                        cntr.input_data_inven(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, -qtty);
                                                        shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() + trade.horse[selected_for_secret - 1].cbm * qtty);
                                                        //인벤창 남은 공간 늘려주는 것
                                                        shipbuilding.set_secret_cntr(shipbuilding.get_secret_cntr() - trade.horse[selected_for_secret - 1].cbm * qtty);
                                                        //비밀 창 남은 공간 줄여주는 것
                                                        System.out.println(cntr.item_inven[input - 1].name);//test
                                                        break;
                                                    }
                                                }
                                            } // 약
                                            else if (cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name == trade.medicine[0].name) {
                                                trade.show_medicine_for_cntr(stage);
                                                System.out.println("===========================================\n적입할 슬롯을 선택하세요.");
                                                selected_for_secret = scan.nextInt(); // 여기서
                                                if (selected_for_secret > trade.medicine.length | selected_for_secret <= 0 | trade.medicine[selected_for_secret - 1].stock == 0) {
                                                    System.out.println("옳바른 숫자를 입력하세요.");
                                                    break;
                                                } else {
                                                    System.out.println("적입할 수량을 선택하세요.");
                                                    qtty = scan.nextInt();
                                                    if (qtty > trade.medicine[selected_for_secret - 1].stock) {
                                                        System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                    } else if ((trade.medicine[selected_for_secret - 1].cbm * qtty) > shipbuilding.get_secret_cntr()) {
                                                        System.out.println("적재 공간이 부족합니다.");
                                                    } else {
                                                        //sell 할때처럼 비워지면 업데이트해줘야함
                                                        trade.medicine[selected_for_secret - 1].stock += -qtty;
                                                        for (int i = 0; i < 14; i++) { //secret에 넣는 과정
                                                            if (cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name == trade.medicine_secret[i].name & trade.medicine[i].storage == trade.medicine_secret[i].storage) { // 이부분 조금 이상함
                                                                trade.medicine_secret[i].stock += qtty;
                                                                break;
                                                            }
                                                        }
                                                        if (trade.medicine[selected_for_secret - 1].stock == 0) {
                                                            trade.medicine[selected_for_secret - 1].storage = Item.make_item(8).storage;
                                                        }
                                                        trade.medicine_count = 0;
                                                        cntr.input_data_secret(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, qtty);
                                                        cntr.input_data_inven(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, -qtty);
                                                        shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() + trade.medicine[selected_for_secret - 1].cbm * qtty);
                                                        //인벤창 남은 공간 늘려주는 것
                                                        shipbuilding.set_secret_cntr(shipbuilding.get_secret_cntr() - trade.medicine[selected_for_secret - 1].cbm * qtty);
                                                        //비밀 창 남은 공간 줄여주는 것
                                                        System.out.println(cntr.item_inven[input - 1].name);//test
                                                        break;
                                                    }
                                                }
                                            } // 향료
                                            else if (cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name == trade.flavor[0].name) {
                                                trade.show_flavor_for_cntr(stage);
                                                System.out.println("===========================================\n적입할 슬롯을 선택하세요.");
                                                selected_for_secret = scan.nextInt(); // 여기서
                                                if (selected_for_secret > trade.flavor.length | selected_for_secret <= 0 | trade.flavor[selected_for_secret - 1].stock == 0) {
                                                    System.out.println("옳바른 숫자를 입력하세요.");
                                                    break;
                                                } else {
                                                    System.out.println("적입할 수량을 선택하세요.");
                                                    qtty = scan.nextInt();
                                                    if (qtty > trade.flavor[selected_for_secret - 1].stock) {
                                                        System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                    } else if ((trade.flavor[selected_for_secret - 1].cbm * qtty) > shipbuilding.get_secret_cntr()) {
                                                        System.out.println("적재 공간이 부족합니다.");
                                                    } else {
                                                        //sell 할때처럼 비워지면 업데이트해줘야함
                                                        trade.flavor[selected_for_secret - 1].stock += -qtty;
                                                        for (int i = 0; i < 14; i++) { //secret에 넣는 과정
                                                            if (cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name == trade.flavor_secret[i].name & trade.flavor[i].storage == trade.flavor_secret[i].storage) { // 이부분 조금 이상함
                                                                trade.flavor_secret[i].stock += qtty;
                                                                break;
                                                            }
                                                        }
                                                        if (trade.flavor[selected_for_secret - 1].stock == 0) {
                                                            trade.flavor[selected_for_secret - 1].storage = Item.make_item(10).storage;
                                                        }
                                                        trade.flavor_count = 0;
                                                        cntr.input_data_secret(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, qtty);
                                                        cntr.input_data_inven(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, -qtty);
                                                        shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() + trade.flavor[selected_for_secret - 1].cbm * qtty);
                                                        //인벤창 남은 공간 늘려주는 것
                                                        shipbuilding.set_secret_cntr(shipbuilding.get_secret_cntr() - trade.flavor[selected_for_secret - 1].cbm * qtty);
                                                        //비밀 창 남은 공간 줄여주는 것
                                                        System.out.println(cntr.item_inven[input - 1].name);//test
                                                        break;
                                                    }
                                                }
                                            }
                                        } else {
                                            System.out.println("적입할 수량을 선택하세요.");
                                            qtty = scan.nextInt();
                                            if (qtty > cntr.item_inven[cntr.get_number_through_num_in_inven(input)].stock) {
                                                System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                            } else if ((cntr.get_cbm_through_name(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name) * qtty) > shipbuilding.get_secret_cntr()) {
                                                System.out.println("적재 공간이 부족합니다.");
                                            } else {
                                                cntr.input_data_secret(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, qtty);
                                                cntr.input_data_inven(cntr.item_inven[cntr.get_number_through_num_in_inven(input)].name, -qtty);
                                                shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() + (cntr.get_cbm_through_name(cntr.get_name_in_inven(input)) * qtty));
                                                //인벤창 남은 공간 늘려주는 것
                                                shipbuilding.set_secret_cntr(shipbuilding.get_secret_cntr() - (cntr.get_cbm_through_name(cntr.get_name_in_inven(input)) * qtty));
                                                //비밀 창 남은 공간 줄여주는 것
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                } else {
                                    System.out.println("옳바른 번호를 다시 입력하세요.");
                                }
                            }
                        } else if (input == 2) {
                            while (true) {
                                if (cntr.distinguish_secret() == 0) {
                                    System.out.println("비밀 화물창에 화물이 없습니다.");
                                    break;
                                }
                                System.out.println("비밀화물창에서 적출할 화물을 선택하세요.");
                                input = scan.nextInt();

                                if (cntr.distinguish_secret() != 0 & input >= 0 & input < 12) {
                                    while (true) {
                                        if (cntr.item_secret[cntr.get_number_through_num_in_secret(input)].storage < 5000) {
                                            //System.out.println(cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name);//test // 정확히 잘 뽑아냈음
                                            if (cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name == trade.rice_secret[0].name) { // 쌀
                                                trade.show_rice_secret_for_cntr(stage);
                                                System.out.println("===========================================\n적출할 슬롯을 선택하세요.");
                                                selected_for_secret = scan.nextInt(); // 여기서
                                                if (selected_for_secret > trade.rice_secret.length | selected_for_secret <= 0 | trade.rice_secret[selected_for_secret - 1].stock == 0) {
                                                    System.out.println("옳바른 숫자를 입력하세요.");
                                                    break;
                                                } else {
                                                    System.out.println("적입할 수량을 선택하세요.");
                                                    qtty = scan.nextInt();
                                                    if (qtty > trade.rice_secret[selected_for_secret - 1].stock) {
                                                        System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                    } else {
                                                        trade.rice_secret[selected_for_secret - 1].stock += -qtty;
                                                        for (int i = 0; i < 14; i++) { //secret에 넣는 과정
                                                            if (cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name == trade.rice_secret[i].name & trade.rice[i].storage == trade.rice_secret[i].storage) { // 이부분 조금 이상함
                                                                trade.rice[i].stock += qtty;
                                                                break;
                                                            }
                                                        }
                                                        if (trade.rice_secret[selected_for_secret - 1].stock == 0) {
                                                            trade.rice_secret[selected_for_secret - 1].storage = Item.make_item(2).storage;
                                                        }
                                                        trade.rice_count = 0; //필요할까싶음
                                                        cntr.input_data_inven(cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name, qtty);
                                                        cntr.input_data_secret(cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name, -qtty);
                                                        shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() - trade.rice_secret[selected_for_secret - 1].cbm * qtty);
                                                        //인벤창 남은 공간 늘려주는 것
                                                        shipbuilding.set_secret_cntr(shipbuilding.get_secret_cntr() + trade.rice_secret[selected_for_secret - 1].cbm * qtty);
                                                        //비밀 창 남은 공간 줄여주는 것
                                                        break;
                                                    }
                                                }
                                            } else if (cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name == trade.ginseng_secret[0].name) { //인삼
                                                trade.show_ginseng_secret_for_cntr(stage);
                                                System.out.println("===========================================\n적출할 슬롯을 선택하세요.");
                                                selected_for_secret = scan.nextInt(); // 여기서
                                                if (selected_for_secret > trade.ginseng_secret.length | selected_for_secret <= 0 | trade.ginseng_secret[selected_for_secret - 1].stock == 0) {
                                                    System.out.println("옳바른 숫자를 입력하세요.");
                                                    break;
                                                } else {
                                                    System.out.println("적입할 수량을 선택하세요.");
                                                    qtty = scan.nextInt();
                                                    if (qtty > trade.ginseng_secret[selected_for_secret - 1].stock) {
                                                        System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                    } else {
                                                        trade.ginseng_secret[selected_for_secret - 1].stock += -qtty;
                                                        for (int i = 0; i < 14; i++) { //secret에 넣는 과정
                                                            if (cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name == trade.ginseng_secret[i].name & trade.ginseng[i].storage == trade.ginseng_secret[i].storage) { // 이부분 조금 이상함
                                                                trade.ginseng[i].stock += qtty;
                                                                break;
                                                            }
                                                        }
                                                        if (trade.ginseng_secret[selected_for_secret - 1].stock == 0) {
                                                            trade.ginseng_secret[selected_for_secret - 1].storage = Item.make_item(3).storage;
                                                        }
                                                        trade.ginseng_count = 0; //필요할까싶음
                                                        cntr.input_data_inven(cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name, qtty);
                                                        cntr.input_data_secret(cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name, -qtty);
                                                        shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() - trade.ginseng_secret[selected_for_secret - 1].cbm * qtty);
                                                        //인벤창 남은 공간 늘려주는 것
                                                        shipbuilding.set_secret_cntr(shipbuilding.get_secret_cntr() + trade.ginseng_secret[selected_for_secret - 1].cbm * qtty);
                                                        //비밀 창 남은 공간 줄여주는 것
                                                        break;
                                                    }
                                                }
                                            } else if (cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name == trade.tea_secret[0].name) { //차
                                                trade.show_tea_secret_for_cntr(stage);
                                                System.out.println("===========================================\n적출할 슬롯을 선택하세요.");
                                                selected_for_secret = scan.nextInt(); // 여기서
                                                if (selected_for_secret > trade.tea_secret.length | selected_for_secret <= 0 | trade.tea_secret[selected_for_secret - 1].stock == 0) {
                                                    System.out.println("옳바른 숫자를 입력하세요.");
                                                    break;
                                                } else {
                                                    System.out.println("적입할 수량을 선택하세요.");
                                                    qtty = scan.nextInt();
                                                    if (qtty > trade.tea_secret[selected_for_secret - 1].stock) {
                                                        System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                    } else {
                                                        trade.tea_secret[selected_for_secret - 1].stock += -qtty;
                                                        for (int i = 0; i < 14; i++) { //secret에 넣는 과정
                                                            if (cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name == trade.tea_secret[i].name & trade.tea[i].storage == trade.tea_secret[i].storage) { // 이부분 조금 이상함
                                                                trade.tea[i].stock += qtty;
                                                                break;
                                                            }
                                                        }
                                                        if (trade.tea_secret[selected_for_secret - 1].stock == 0) {
                                                            trade.tea_secret[selected_for_secret - 1].storage = Item.make_item(4).storage;
                                                        }
                                                        trade.tea_count = 0; //필요할까싶음
                                                        cntr.input_data_inven(cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name, qtty);
                                                        cntr.input_data_secret(cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name, -qtty);
                                                        shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() - trade.tea_secret[selected_for_secret - 1].cbm * qtty);
                                                        //인벤창 남은 공간 늘려주는 것
                                                        shipbuilding.set_secret_cntr(shipbuilding.get_secret_cntr() + trade.tea_secret[selected_for_secret - 1].cbm * qtty);
                                                        //비밀 창 남은 공간 줄여주는 것
                                                        break;
                                                    }
                                                }
                                            } else if (cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name == trade.horse_secret[0].name) {
                                                trade.show_horse_secret_for_cntr(stage);
                                                System.out.println("===========================================\n적출할 슬롯을 선택하세요.");
                                                selected_for_secret = scan.nextInt(); // 여기서
                                                if (selected_for_secret > trade.horse_secret.length | selected_for_secret <= 0 | trade.horse_secret[selected_for_secret - 1].stock == 0) {
                                                    System.out.println("옳바른 숫자를 입력하세요.");
                                                    break;
                                                } else {
                                                    System.out.println("적입할 수량을 선택하세요.");
                                                    qtty = scan.nextInt();
                                                    if (qtty > trade.horse_secret[selected_for_secret - 1].stock) {
                                                        System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                    } else {
                                                        trade.horse_secret[selected_for_secret - 1].stock += -qtty;
                                                        for (int i = 0; i < 14; i++) { //secret에 넣는 과정
                                                            if (cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name == trade.horse_secret[i].name & trade.horse[i].storage == trade.horse_secret[i].storage) { // 이부분 조금 이상함
                                                                trade.horse[i].stock += qtty;
                                                                break;
                                                            }
                                                        }
                                                        if (trade.horse_secret[selected_for_secret - 1].stock == 0) {
                                                            trade.horse_secret[selected_for_secret - 1].storage = Item.make_item(6).storage;
                                                        }
                                                        trade.horse_count = 0; //필요할까싶음
                                                        cntr.input_data_inven(cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name, qtty);
                                                        cntr.input_data_secret(cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name, -qtty);
                                                        shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() - trade.horse_secret[selected_for_secret - 1].cbm * qtty);
                                                        //인벤창 남은 공간 늘려주는 것
                                                        shipbuilding.set_secret_cntr(shipbuilding.get_secret_cntr() + trade.horse_secret[selected_for_secret - 1].cbm * qtty);
                                                        //비밀 창 남은 공간 줄여주는 것
                                                        break;
                                                    }
                                                }
                                            } else if (cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name == trade.medicine_secret[0].name) {
                                                trade.show_medicine_secret_for_cntr(stage);
                                                System.out.println("===========================================\n적출할 슬롯을 선택하세요.");
                                                selected_for_secret = scan.nextInt(); // 여기서
                                                if (selected_for_secret > trade.medicine_secret.length | selected_for_secret <= 0 | trade.medicine_secret[selected_for_secret - 1].stock == 0) {
                                                    System.out.println("옳바른 숫자를 입력하세요.");
                                                    break;
                                                } else {
                                                    System.out.println("적입할 수량을 선택하세요.");
                                                    qtty = scan.nextInt();
                                                    if (qtty > trade.medicine_secret[selected_for_secret - 1].stock) {
                                                        System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                    } else {
                                                        trade.medicine_secret[selected_for_secret - 1].stock += -qtty;
                                                        for (int i = 0; i < 14; i++) { //secret에 넣는 과정
                                                            if (cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name == trade.medicine_secret[i].name & trade.medicine[i].storage == trade.medicine_secret[i].storage) { // 이부분 조금 이상함
                                                                trade.medicine[i].stock += qtty;
                                                                break;
                                                            }
                                                        }
                                                        if (trade.medicine_secret[selected_for_secret - 1].stock == 0) {
                                                            trade.medicine_secret[selected_for_secret - 1].storage = Item.make_item(8).storage;
                                                        }
                                                        trade.medicine_count = 0; //필요할까싶음
                                                        cntr.input_data_inven(cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name, qtty);
                                                        cntr.input_data_secret(cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name, -qtty);
                                                        shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() - trade.medicine_secret[selected_for_secret - 1].cbm * qtty);
                                                        //인벤창 남은 공간 늘려주는 것
                                                        shipbuilding.set_secret_cntr(shipbuilding.get_secret_cntr() + trade.medicine_secret[selected_for_secret - 1].cbm * qtty);
                                                        //비밀 창 남은 공간 줄여주는 것
                                                        break;
                                                    }
                                                }
                                            } else if (cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name == trade.flavor_secret[0].name) {
                                                trade.show_flavor_secret_for_cntr(stage);
                                                System.out.println("===========================================\n적출할 슬롯을 선택하세요.");
                                                selected_for_secret = scan.nextInt(); // 여기서
                                                if (selected_for_secret > trade.flavor_secret.length | selected_for_secret <= 0 | trade.flavor_secret[selected_for_secret - 1].stock == 0) {
                                                    System.out.println("옳바른 숫자를 입력하세요.");
                                                    break;
                                                } else {
                                                    System.out.println("적입할 수량을 선택하세요.");
                                                    qtty = scan.nextInt();
                                                    if (qtty > trade.flavor_secret[selected_for_secret - 1].stock) {
                                                        System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                    } else {
                                                        trade.flavor_secret[selected_for_secret - 1].stock += -qtty;
                                                        for (int i = 0; i < 14; i++) { //secret에 넣는 과정
                                                            if (cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name == trade.flavor_secret[i].name & trade.flavor[i].storage == trade.flavor_secret[i].storage) { // 이부분 조금 이상함
                                                                trade.flavor[i].stock += qtty;
                                                                break;
                                                            }
                                                        }
                                                        if (trade.flavor_secret[selected_for_secret - 1].stock == 0) {
                                                            trade.flavor_secret[selected_for_secret - 1].storage = Item.make_item(10).storage;
                                                        }
                                                        trade.flavor_count = 0; //필요할까싶음
                                                        cntr.input_data_inven(cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name, qtty);
                                                        cntr.input_data_secret(cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name, -qtty);
                                                        shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() - trade.flavor_secret[selected_for_secret - 1].cbm * qtty);
                                                        //인벤창 남은 공간 늘려주는 것
                                                        shipbuilding.set_secret_cntr(shipbuilding.get_secret_cntr() + trade.flavor_secret[selected_for_secret - 1].cbm * qtty);
                                                        //비밀 창 남은 공간 줄여주는 것
                                                        break;
                                                    }
                                                }
                                            }
                                        } else {
                                            System.out.println("적입할 수량을 선택하세요.");
                                            qtty = scan.nextInt();

                                            if (qtty > cntr.item_secret[cntr.get_number_through_num_in_secret(input)].stock) {
                                                System.out.println("재고보다 많은 수량을 선택 할 수 없습니다.");
                                                //} else if ((cntr.get_cbm_through_name(cntr.get_name_in_inven(input)) * qtty) > shipbuilding.get_secret_cntr()){
                                                //  System.out.println("적재 공간이 부족합니다.");
                                            } else {
                                                cntr.input_data_inven(cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name, qtty);
                                                cntr.input_data_secret(cntr.item_secret[cntr.get_number_through_num_in_secret(input)].name, -qtty);
                                                shipbuilding.set_vsl_container(shipbuilding.give_main_vsl_container() - (cntr.get_cbm_through_name(cntr.get_name_in_secret(input)) * qtty));
                                                //인벤창 남은 공간 줄여주는 것
                                                shipbuilding.set_secret_cntr(shipbuilding.get_secret_cntr() + (cntr.get_cbm_through_name(cntr.get_name_in_secret(input)) * qtty));
                                                //비밀 창 남은 공간 늘려주는 것
                                                break;
                                            }
                                        }
                                    }
                                    break;
                                } else {
                                    System.out.println("옳바른 번호를 다시 입력하세요.");
                                }
                            }
                        } else if (input == 9) {
                            break;
                        }
                    }
                }
            } // 화물창
            else if (input == 7) {
                if (stage != 1) {
                    break;
                }
                System.out.println("어지간히도 찾아오는구만, 그만 찾아오라고 네 누이께 그렇게 말을 했건만... 쯧쯧..");
                System.out.println("1. 돈을 빌린다.\n9. 나간다.");
                input = scan.nextInt();
                while (true) {
                    if (input == 1) {
                        if (Character.player.get_char_money() >= 6000) {
                            System.out.println("돈도 있는 양반이 낮짝 두껍게 돈을 빌리러 오다니 누굴 밥줄로 아는겐가?");
                            break;
                        } else {
                            Character.player.set_char_money(Character.player.get_char_money() + 10000);
                            count_ending--;
                            if (count_ending == 0) {
                                System.out.println("게임에서 패배했습니다.");
                                System.exit(0);
                            }
                            System.out.println("10000 이코를 빌려줄테니 얼른 갚게. 기회는 " + (count_ending - 1) + "번 남았으니 잘 해봐. 누굴 닮아 저리 무능할고...");
                            break;
                        }
                    } else if (input == 9) {
                        break;
                    } else {
                        System.out.println("다시 입력하세요.");
                    }
                }
            }
            else if (input == 9) {
                System.exit(0);
            }
            else if (input == 1004){
                Character.player.money += 1000000;
            }
        }
    }
}
