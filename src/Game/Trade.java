package Game;

import java.util.Random;
import java.util.Scanner;

public class Trade {

    Scanner scan = new Scanner(System.in);
    Random random = new Random();

    Item[] item_trade_blackMarket = new Item[5];
    Item[] item_trade = new Item[12];
    Item[] tmp_item = new Item[12];

    boolean entered = false;

    int cbm_after_selling = 0;
    int tmp = 0;
    int count = 0;
    int selected_in_sell = 0;
    int selected_which = 0;

    static int num_priceList;

    static Item[] rice = new Item[15];
    static Item[] rice_secret = new Item[15];
    static int rice_count = 0;

    static Item[] ginseng = new Item[8];
    static Item[] ginseng_secret = new Item[8];
    static int ginseng_count = 0;

    static Item[] tea = new Item[8];
    static Item[] tea_secret = new Item[8];
    static int tea_count = 0;

    static Item[] horse = new Item[16];
    static Item[] horse_secret = new Item[16];
    static int horse_count = 0;

    static Item[] medicine = new Item[11];
    static Item[] medicine_secret = new Item[11];
    static int medicine_count = 0;

    static Item[] flavor = new Item[11];
    static Item[] flavor_secret = new Item[11];
    static int flavor_count = 0;

    static double[] array_1 = {0.5, 0.5, 0.5, 0.85, 1.1, 1.25, 1.1, 1.15, 0.95, 1.05, 0.95, 0.95};
    static double[] array_2 = {0.85, 1.15, 0.8, 0.5, 0.5, 0.5, 0.95, 0.95, 1.05, 1.15, 0.9, 0.95};
    static double[] array_3 = {0.9, 0.85, 1.2, 1.2, 0.95, 1.3, 0.5, 0.5, 0.5, 1.2, 1.1, 1.25};
    static double[] array_4 = {1.25, 1.2, 1.5, 1.1, 1.15, 1.35, 0.85, 1.2, 1.15, 0.5, 0.5, 0.5};

    //최신화 아이템을 먼저 만들어야 함
    public void make_item() {
        for (int i = 0; i < 12; i++) {
            item_trade[i] = Item.make_item(i + 1);
            tmp_item[i] = Item.make_item(i + 1);
        }
        for (int i = 0; i < 5; i++) {
            item_trade_blackMarket[i] = Item.make_item_for_blackMarket(i + 1);
        }
        for (int i = 0; i < rice.length; i++) {
            rice[i] = Item.make_item(2);
            rice_secret[i] = Item.make_item(2);
        }
        for (int i = 0; i < ginseng.length; i++) {
            ginseng[i] = Item.make_item(3);
            ginseng_secret[i] = Item.make_item(3);
            tea[i] = Item.make_item(4);
            tea_secret[i] = Item.make_item(4);
        }
        for (int i = 0; i < horse.length; i++) {
            horse[i] = Item.make_item(6);
            horse_secret[i] = Item.make_item(6);
        }
        for (int i = 0; i < medicine.length; i++) {
            medicine[i] = Item.make_item(8);
            flavor[i] = Item.make_item(10);
            medicine_secret[i] = Item.make_item(8);
            flavor_secret[i] = Item.make_item(10);
        }
    }

    public void show_purchase(int stage) {
        if (stage == 1) {
            for (int i = 0; i < 3; i++) {
                System.out.println(i + 1 + ". " + item_trade[i].name + "\t|\t가격 : " + item_trade[i].price + "\t\t|\t 차지하는 공간 : " + item_trade[i].cbm + "\t\t|\t신선도 : " + item_trade[i].storage);
            }
        } else if (stage == 2) {
            for (int i = 3; i < 6; i++) {
                System.out.println(i - 2 + ". " + item_trade[i].name + "\t|\t가격 : " + item_trade[i].price + "\t\t|\t 차지하는 공간 : " + item_trade[i].cbm + "\t\t|\t신선도 : " + item_trade[i].storage);
            }
        } else if (stage == 3) {
            for (int i = 6; i < 9; i++) {
                System.out.println(i - 5 + ". " + item_trade[i].name + "\t|\t가격 : " + item_trade[i].price + "\t|\t 차지하는 공간 : " + item_trade[i].cbm + "\t\t|\t신선도 : " + item_trade[i].storage);
            }
        } else if (stage == 4) {
            for (int i = 9; i < 12; i++) {
                System.out.println(i - 8 + ". " + item_trade[i].name + "\t\t\t|\t가격 : " + item_trade[i].price + "\t|\t 차지하는 공간 : " + item_trade[i].cbm + "\t\t|\t신선도 : " + item_trade[i].storage);
            }
        }
    }

    public void purchase(int stage, int select, int qtty, int char_money, int char_container) {
        if (stage == 1) {
            if (item_trade[select - 1].price * qtty > char_money) {
                System.out.println("금액이 부족합니다.");
            }
            if (item_trade[select - 1].cbm * qtty > char_container) {
                System.out.println("선적 할 공간이 부족합니다.");
            }
            if (item_trade[select - 1].price * qtty <= char_money & item_trade[select - 1].cbm * qtty <= char_container & char_money >= 0 & char_container >= 0) {
                for (int i = 0; i < 12; i++) {
                    if (item_trade[select - 1].name == Container.item_inven[i].name) { // 인벤토리에 넣는 과정
                        Container.item_inven[i].stock += qtty;
                    }
                }

                if(item_trade[select - 1].name == rice[0].name) {
                    for (int i = 0; i < rice.length; i++) { // 쌀
                        if (item_trade[select - 1].storage == rice[i].storage) { // 쌀 카테고리에 넣는 과정
                            rice[i].stock += qtty;
                            break;
                        }
                    }
                } else if(item_trade[select - 1].name == ginseng[0].name) {
                    for (int i = 0; i <  ginseng.length; i++) { // 인삼
                        if (item_trade[select - 1].storage ==  ginseng[i].storage) {
                             ginseng[i].stock += qtty;
                            break;
                        }
                    }
                }
                System.out.println(item_trade[select - 1].name + " " + qtty + "개를 구매 완료 했습니다.");
            }
        } else if (stage == 2) {
            if (item_trade[select + 2].price * qtty > char_money) {
                System.out.println("금액이 부족합니다.");
            }
            if (item_trade[select + 2].cbm * qtty > char_container) {
                System.out.println("선적 할 공간이 부족합니다.");
            }
            if (item_trade[select + 2].price * qtty <= char_money & item_trade[select + 2].cbm * qtty <= char_container & char_money >= 0 & char_container >= 0) {
                for (int i = 0; i < 12; i++) {
                    if (item_trade[select + 2].name == Container.item_inven[i].name) {
                        Container.item_inven[i].stock += qtty;
                    }
                }

                if(item_trade[select + 2].name == tea[0].name) {
                    for (int i = 0; i < tea.length; i++) { // 차
                        if (item_trade[select + 2].storage == tea[i].storage) {
                            tea[i].stock += qtty;
                            break;
                        }
                    }
                } else if(item_trade[select + 2].name == horse[0].name) {
                    for (int i = 0; i <  horse.length; i++) { // 말
                        if (item_trade[select + 2].storage ==  horse[i].storage) {
                            horse[i].stock += qtty;
                            break;
                        }
                    }
                }

                System.out.println(item_trade[select + 2].name + " " + qtty + "개를 구매 완료 했습니다.");
            }
        } else if (stage == 3) {
            if (item_trade[select + 5].price * qtty > char_money) {
                System.out.println("금액이 부족합니다.");
            }
            if (item_trade[select + 5].cbm * qtty > char_container) {
                System.out.println("선적 할 공간이 부족합니다.");
            }
            if (item_trade[select + 5].price * qtty <= char_money & item_trade[select + 5].cbm * qtty <= char_container & char_money >= 0 & char_container >= 0) {
                for (int i = 0; i < 12; i++) {
                    if (item_trade[select + 5].name == Container.item_inven[i].name) {
                        Container.item_inven[i].stock += qtty;
                    }
                }
                if(item_trade[select + 5].name == medicine[0].name) { //약재
                    for (int i = 0; i < medicine.length; i++) {
                        if (item_trade[select + 5].storage == medicine[i].storage) {
                            medicine[i].stock += qtty;
                            break;
                        }
                    }
                }

                System.out.println(item_trade[select + 5].name + " " + qtty + "개를 구매 완료 했습니다.");
            }
        } else if (stage == 4) {
            if (item_trade[select + 8].price * qtty > char_money) {
                System.out.println("금액이 부족합니다.");
            }
            if (item_trade[select + 8].cbm * qtty > char_container) {
                System.out.println("선적 할 공간이 부족합니다.");
            }
            if (item_trade[select + 8].price * qtty <= char_money & item_trade[select + 8].cbm * qtty <= char_container & char_money >= 0 & char_container >= 0) {
                for (int i = 0; i < 12; i++) {
                    if (item_trade[select + 8].name == Container.item_inven[i].name) {
                        Container.item_inven[i].stock += qtty;
                    }
                }

                if(item_trade[select + 8].name == flavor[0].name) { //향료
                    for (int i = 0; i < flavor.length; i++) {
                        if (item_trade[select + 8].storage == flavor[i].storage) {
                            flavor[i].stock += qtty;
                            break;
                        }
                    }
                }

                System.out.println(item_trade[select + 8].name + " " + qtty + "개를 구매 완료 했습니다.");
            }
        }
    }

    public void show_purchase_blackMarket(int stage) {
        if (stage == 1) {
            for (int i = 0; i < 5; i++) {
                System.out.println(i + 1 + ". " + item_trade_blackMarket[i].name + "\t|\t가격 : " + item_trade_blackMarket[i].price);
            }
        } else if (stage == 2) {
            for (int i = 1; i < 5; i++) {
                System.out.println(i + ". " + item_trade_blackMarket[i].name + "\t|\t가격 : " + item_trade_blackMarket[i].price);
            }
        } else if (stage == 3) {
            for (int i = 2; i < 5; i++) {
                System.out.println(i - 1 + ". " + item_trade_blackMarket[i].name + "\t|\t가격 : " + item_trade_blackMarket[i].price);
            }
        } else if (stage == 4) {
            for (int i = 3; i < 5; i++) {
                System.out.println(i - 2 + ". " + item_trade_blackMarket[i].name + "\t\t\t|\t가격 : " + item_trade_blackMarket[i].price);
            }
        }
    }

    public int purchase_blackMarket(int stage, int max_stage, int select) {
        int a = 0;
        if (stage == 1) {
            if (item_trade_blackMarket[select - 1].price > Character.player.get_char_money()) {
                System.out.println("금액이 부족합니다.");
            } else if (item_trade_blackMarket[select - 1].price <= Character.player.get_char_money() & Character.player.get_char_money() >= 0) {
                if (select - 1 < 3) {
                    if (select + 1 <= max_stage) {
                        System.out.println("이미 항로가 개방되어 있어 구매 불가합니다.");
                    } else if (select + stage != max_stage + 1) {
                        System.out.println("이전 항로부터 확보하세요.");
                    } else {
                        System.out.println(item_trade_blackMarket[select - 1].name + " 구매 완료하여 항로가 개방 됐습니다.");
                        a = 1;
                    }
                } else if (select - 1 == 3) { // 해도 어떻게 넣을지 고민해야함
                    System.out.println(item_trade_blackMarket[select - 1].name + " 구매 완료하여 해도가 1일동안 표시됩니다.");
                    a = 2;
                } else if (select - 1 == 4) { // 메인에 가격표 삽입 어떻게 할지 고민해야함, 구매가격을 입력해줘야함
                    //System.out.println(item_trade_blackMarket[select - 1].name + " 구매 완료하였습니다.");
                    int count_priceList = 0;

                    for (int i = 0; i < Container.item_inven.length; i++){
                        if(Container.item_inven[i].open == true){
                            count_priceList++;
                        }
                    }

                    if(count_priceList == 12){
                        System.out.println("가격표가 모두 개방되어 있습니다.");
                        a = 0;
                    } else {
                        while (true) {
                            num_priceList = random.nextInt(12);
                            if (Container.item_inven[num_priceList].open == false) {
                                Container.item_inven[num_priceList].open = true;
                                break;
                            }
                        }
                        System.out.println(Container.item_inven[num_priceList].name + " 가격이 오픈 되었습니다. 인벤토리에서 구매가격을 확인 할 수 있습니다."); // 상점에서 볼 수 있게 할지 고민중
                        a = 3;
                    }
                }
                Character.player.set_char_money(Character.player.get_char_money() - (item_trade_blackMarket[select - 1].price));
            }
        }
        else if (stage == 2) {
            if (item_trade_blackMarket[select].price > Character.player.get_char_money()) {
                System.out.println("금액이 부족합니다.");
            } else if (item_trade_blackMarket[select].price <= Character.player.get_char_money() & Character.player.get_char_money() >= 0) {
                if (select < 3) {
                    if (select + 2 <= max_stage) {
                        System.out.println("이미 항로가 개방되어 있어 구매 불가합니다.");
                    } else if (select + stage != max_stage + 1) {
                        System.out.println("이전 항로부터 확보하세요.");
                    } else {
                        System.out.println(item_trade_blackMarket[select].name + " 구매 완료하여 항로가 개방 됐습니다.");
                        a = 1;
                    }
                } else if (select == 3) { // 해도 어떻게 넣을지 고민해야함
                    System.out.println(item_trade_blackMarket[select].name + " 구매 완료하여 해도가 1일동안 표시됩니다.");
                    a = 2;
                } else if (select == 4) { // 메인에 가격표 삽입 어떻게 할지 고민해야함, 구매가격을 입력해줘야함
                    //System.out.println(item_trade_blackMarket[select - 1].name + " 구매 완료하였습니다.");
                    int count_priceList = 0;

                    for (int i = 0; i < Container.item_inven.length; i++){
                        if(Container.item_inven[i].open == true){
                            count_priceList++;
                        }
                    }

                    if(count_priceList == 12){
                        System.out.println("가격표가 모두 개방되어 있습니다.");
                        a = 0;
                    } else {
                        while (true) {
                            num_priceList = random.nextInt(12);
                            if (Container.item_inven[num_priceList].open == false) {
                                Container.item_inven[num_priceList].open = true;
                                break;
                            }
                        }
                        System.out.println(Container.item_inven[num_priceList].name + " 가격이 오픈 되었습니다. 인벤토리에서 구매가격을 확인 할 수 있습니다."); // 상점에서 볼 수 있게 할지 고민중
                        a = 3;
                    }
                }
                Character.player.set_char_money(Character.player.get_char_money() - (item_trade_blackMarket[select].price));
            }
        }
        else if (stage == 3) {
            if (item_trade_blackMarket[select + 1].price > Character.player.get_char_money()) {
                System.out.println("금액이 부족합니다.");
            } else if (item_trade_blackMarket[select + 1].price <= Character.player.get_char_money() & Character.player.get_char_money() >= 0) {
                if (select < 2) {
                    if (select + 3 <= max_stage) {
                        System.out.println("이미 항로가 개방되어 있어 구매 불가합니다.");
                    } else {
                        System.out.println(item_trade_blackMarket[select + 1].name + " 구매 완료하여 항로가 개방 됐습니다.");
                        a = 1;
                    }
                } else if (select == 2) { // 해도 어떻게 넣을지 고민해야함
                    System.out.println(item_trade_blackMarket[select + 1].name + " 구매 완료하여 해도가 1일동안 표시됩니다.");
                    a = 2;
                } else if (select == 3) { // 메인에 가격표 삽입 어떻게 할지 고민해야함, 구매가격을 입력해줘야함
                    //System.out.println(item_trade_blackMarket[select - 1].name + " 구매 완료하였습니다.");
                    int count_priceList = 0;

                    for (int i = 0; i < Container.item_inven.length; i++){
                        if(Container.item_inven[i].open == true){
                            count_priceList++;
                        }
                    }

                    if(count_priceList == 12){
                        System.out.println("가격표가 모두 개방되어 있습니다.");
                        a = 0;
                    } else {
                        while (true) {
                            num_priceList = random.nextInt(12);
                            if (Container.item_inven[num_priceList].open == false) {
                                Container.item_inven[num_priceList].open = true;
                                break;
                            }
                        }
                        System.out.println(item_trade[num_priceList].name + " 가격이 오픈 되었습니다. 인벤토리에서 구매가격을 확인 할 수 있습니다."); // 상점에서 볼 수 있게 할지 고민중
                        a = 3;
                    }
                }
                Character.player.set_char_money(Character.player.get_char_money() - (item_trade_blackMarket[select + 1].price));
            }
        }
        else if (stage == 4) {
            if (item_trade_blackMarket[select + 2].price > Character.player.get_char_money()) {
                System.out.println("금액이 부족합니다.");
            } else if (item_trade_blackMarket[select + 2].price <= Character.player.get_char_money() & Character.player.get_char_money() >= 0) {
                if (select == 1) { // 해도 어떻게 넣을지 고민해야함
                    System.out.println(item_trade_blackMarket[select + 2].name + " 구매 완료하여 해도가 1일동안 표시됩니다.");
                    a = 2;
                } else if (select == 2) { // 메인에 가격표 삽입 어떻게 할지 고민해야함, 구매가격을 입력해줘야함
                    //System.out.println(item_trade_blackMarket[select - 1].name + " 구매 완료하였습니다.");
                    int count_priceList = 0;

                    for (int i = 0; i < Container.item_inven.length; i++){
                        if(Container.item_inven[i].open == true){
                            count_priceList++;
                        }
                    }

                    if(count_priceList == 12){
                        System.out.println("가격표가 모두 개방되어 있습니다.");
                        a = 0;
                    } else {
                        while (true) {
                            num_priceList = random.nextInt(12);
                            if (Container.item_inven[num_priceList].open == false) {
                                Container.item_inven[num_priceList].open = true;
                                break;
                            }
                        }
                        System.out.println(item_trade[num_priceList].name + " 가격이 오픈 되었습니다. 인벤토리에서 구매가격을 확인 할 수 있습니다."); // 상점에서 볼 수 있게 할지 고민중
                        a = 3;
                    }
                }
                Character.player.set_char_money(Character.player.get_char_money() - (item_trade_blackMarket[select + 2].price));
            }
        }
        System.out.println("");
        return a;
    }

    public int show_sell(int stage, int char_container) {
        entered = false;

        for (int i = 0; i < 12; i++) {
            tmp_item[i] = Item.make_item(i + 1);
        }

        if (stage == 1) {
            //int count = 0;
            for (int i = 0; i < Container.item_inven.length; i++) {
                tmp_item[i].price *= array_1[i];
                if (Container.item_inven[i].stock != 0) {
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("판매하실 물건이 없습니다.");
                return 1;
            } else {
                int count_tmp = 1;

                for (int i = 0; i < Container.item_inven.length; i++) {
                    if (Container.item_inven[i].stock != 0) {
                        tmp_item[count_tmp - 1].name = Container.item_inven[i].name;
                        tmp_item[count_tmp - 1].stock = Container.item_inven[i].stock;
                        tmp_item[count_tmp - 1].cbm = Container.item_inven[i].cbm;
                        tmp_item[count_tmp - 1].storage = Container.item_inven[i].storage;
                        tmp_item[count_tmp - 1].price = tmp_item[i].price;
                        tmp++;
                        //tmp_money = tmp_item[i].price;
                        count_tmp++;
                    }
                }
                for (int i = 0; i < count; i++) {
                    System.out.println(i + 1 + ". " + tmp_item[i].name + "\t\t|\t수량 : " + tmp_item[i].stock + "\t\t|\t 개별 차지하는 공간 : " + tmp_item[i].cbm + "\t\t|\t 판매 가격 : " + tmp_item[i].price);
                }
                //여기다가 추가 수정중
                System.out.print("===========================================\n판매 하실 상품을 선택하세요.");
                System.out.println("\t\t\t\t\t보유한 돈 : " + Character.player.money + "\t\t보유한 공간 : " + char_container);
                selected_which = scan.nextInt();
                if (selected_which == 20000) {
                    count = 0;
                    return 1;
                }
                if (tmp_item[selected_which - 1].storage < 5000) {
                    entered = true;
                    if (tmp_item[selected_which - 1].name == rice[0].name) {
                        show_rice(stage);
                    } else if (tmp_item[selected_which - 1].name == ginseng[0].name) {
                        show_ginseng(stage);
                    } else if (tmp_item[selected_which - 1].name == tea[0].name) {
                        show_tea(stage);
                    } else if (tmp_item[selected_which - 1].name == horse[0].name) {
                        show_horse(stage);
                    } else if (tmp_item[selected_which - 1].name == medicine[0].name) {
                        show_medicine(stage);
                    } else if (tmp_item[selected_which - 1].name == flavor[0].name) {
                        show_flavor(stage);
                    }
                    System.out.print("===========================================\n판매 하실 상품을 선택하세요.");
                    System.out.println("\t\t\t\t\t보유한 돈 : " + Character.player.money + "\t\t보유한 공간 : " + char_container);
                    selected_in_sell = scan.nextInt();
                }
            }
        } else if (stage == 2) {
            //int count = 0;
            for (int i = 0; i < Container.item_inven.length; i++) {
                tmp_item[i].price *= array_2[i];
                if (Container.item_inven[i].stock != 0) {
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("판매하실 물건이 없습니다.");
                return 1;
            } else {
                int count_tmp = 1;

                for (int i = 0; i < Container.item_inven.length; i++) {
                    if (Container.item_inven[i].stock != 0) {
                        tmp_item[count_tmp - 1].name = Container.item_inven[i].name;
                        tmp_item[count_tmp - 1].stock = Container.item_inven[i].stock;
                        tmp_item[count_tmp - 1].cbm = Container.item_inven[i].cbm;
                        tmp_item[count_tmp - 1].storage = Container.item_inven[i].storage;
                        tmp_item[count_tmp - 1].price = tmp_item[i].price;
                        tmp++;
                        //tmp_money = tmp_item[i].price;
                        count_tmp++;
                    }
                }
                for (int i = 0; i < count; i++) {
                    System.out.println(i + 1 + ". " + tmp_item[i].name + "\t\t|\t수량 : " + tmp_item[i].stock + "\t\t|\t 개별 차지하는 공간 : " + tmp_item[i].cbm + "\t\t|\t 판매 가격 : " + tmp_item[i].price);
                }
                System.out.print("===========================================\n판매 하실 상품을 선택하세요.");
                System.out.println("\t\t\t\t\t보유한 돈 : " + Game.Character.player.money + "\t\t보유한 공간 : " + char_container);
                selected_which = scan.nextInt();
                if (selected_which == 20000) {
                    count = 0;
                    return 1;
                }
                if (tmp_item[selected_which - 1].storage < 5000) {
                    entered = true;
                    if (tmp_item[selected_which - 1].name == rice[0].name) {
                        show_rice(stage);
                    } else if (tmp_item[selected_which - 1].name == ginseng[0].name) {
                        show_ginseng(stage);
                    } else if (tmp_item[selected_which - 1].name == tea[0].name) {
                        show_tea(stage);
                    } else if (tmp_item[selected_which - 1].name == horse[0].name) {
                        show_horse(stage);
                    } else if (tmp_item[selected_which - 1].name == medicine[0].name) {
                        show_medicine(stage);
                    } else if (tmp_item[selected_which - 1].name == flavor[0].name) {
                        show_flavor(stage);
                    }
                    System.out.print("===========================================\n판매 하실 상품을 선택하세요.");
                    System.out.println("\t\t\t\t\t보유한 돈 : " + Game.Character.player.money + "\t\t보유한 공간 : " + char_container);
                    selected_in_sell = scan.nextInt();
                }
            }
        } else if (stage == 3) {
            //int count = 0;
            for (int i = 0; i < Container.item_inven.length; i++) {
                tmp_item[i].price *= array_3[i];
                if (Container.item_inven[i].stock != 0) {
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("판매하실 물건이 없습니다.");
                return 1;
            } else {
                int count_tmp = 1;

                for (int i = 0; i < Container.item_inven.length; i++) {
                    if (Container.item_inven[i].stock != 0) {
                        tmp_item[count_tmp - 1].name = Container.item_inven[i].name;
                        tmp_item[count_tmp - 1].stock = Container.item_inven[i].stock;
                        tmp_item[count_tmp - 1].cbm = Container.item_inven[i].cbm;
                        tmp_item[count_tmp - 1].storage = Container.item_inven[i].storage;
                        tmp_item[count_tmp - 1].price = tmp_item[i].price;
                        tmp++;
                        //tmp_money = tmp_item[i].price;
                        count_tmp++;
                    }
                }
                for (int i = 0; i < count; i++) {
                    System.out.println(i + 1 + ". " + tmp_item[i].name + "\t\t|\t수량 : " + tmp_item[i].stock + "\t\t|\t 개별 차지하는 공간 : " + tmp_item[i].cbm + "\t\t|\t 판매 가격 : " + tmp_item[i].price);
                }
                System.out.print("===========================================\n판매 하실 상품을 선택하세요.");
                System.out.println("\t\t\t\t\t보유한 돈 : " + Game.Character.player.money + "\t\t보유한 공간 : " + char_container);
                selected_which = scan.nextInt();
                if (selected_which == 20000) {
                    count = 0;
                    return 1;
                }
                if (tmp_item[selected_which - 1].storage < 5000) {
                    entered = true;
                    if (tmp_item[selected_which - 1].name == rice[0].name) {
                        show_rice(stage);
                    } else if (tmp_item[selected_which - 1].name == ginseng[0].name) {
                        show_ginseng(stage);
                    } else if (tmp_item[selected_which - 1].name == tea[0].name) {
                        show_tea(stage);
                    } else if (tmp_item[selected_which - 1].name == horse[0].name) {
                        show_horse(stage);
                    } else if (tmp_item[selected_which - 1].name == medicine[0].name) {
                        show_medicine(stage);
                    } else if (tmp_item[selected_which - 1].name == flavor[0].name) {
                        show_flavor(stage);
                    }
                    System.out.print("===========================================\n판매 하실 상품을 선택하세요.");
                    System.out.println("\t\t\t\t\t보유한 돈 : " + Game.Character.player.money + "\t\t보유한 공간 : " + char_container);
                    selected_in_sell = scan.nextInt();
                }
            }
        } else if (stage == 4) {
            //int count = 0;
            for (int i = 0; i < Container.item_inven.length; i++) {
                tmp_item[i].price *= array_4[i];
                if (Container.item_inven[i].stock != 0) {
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("판매하실 물건이 없습니다.");
                return 1;
            } else {
                int count_tmp = 1;

                for (int i = 0; i < Container.item_inven.length; i++) {
                    if (Container.item_inven[i].stock != 0) {
                        tmp_item[count_tmp - 1].name = Container.item_inven[i].name;
                        tmp_item[count_tmp - 1].stock = Container.item_inven[i].stock;
                        tmp_item[count_tmp - 1].cbm = Container.item_inven[i].cbm;
                        tmp_item[count_tmp - 1].storage = Container.item_inven[i].storage;
                        tmp_item[count_tmp - 1].price = tmp_item[i].price;
                        tmp++;
                        //tmp_money = tmp_item[i].price;
                        count_tmp++;
                    }
                }
                for (int i = 0; i < count; i++) {
                    System.out.println(i + 1 + ". " + tmp_item[i].name + "\t\t|\t수량 : " + tmp_item[i].stock + "\t\t|\t 개별 차지하는 공간 : " + tmp_item[i].cbm + "\t\t|\t 판매 가격 : " + tmp_item[i].price);
                }
                System.out.print("===========================================\n판매 하실 상품을 선택하세요.");
                System.out.println("\t\t\t\t\t보유한 돈 : " + Game.Character.player.money + "\t\t보유한 공간 : " + char_container);
                selected_which = scan.nextInt();
                if (selected_which == 20000) {
                    count = 0;
                    return 1;
                }
                if (tmp_item[selected_which - 1].storage < 5000) {
                    entered = true;
                    if (tmp_item[selected_which - 1].name == rice[0].name) {
                        show_rice(stage);
                    } else if (tmp_item[selected_which - 1].name == ginseng[0].name) {
                        show_ginseng(stage);
                    } else if (tmp_item[selected_which - 1].name == tea[0].name) {
                        show_tea(stage);
                    } else if (tmp_item[selected_which - 1].name == horse[0].name) {
                        show_horse(stage);
                    } else if (tmp_item[selected_which - 1].name == medicine[0].name) {
                        show_medicine(stage);
                    } else if (tmp_item[selected_which - 1].name == flavor[0].name) {
                        show_flavor(stage);
                    }
                    System.out.print("===========================================\n판매 하실 상품을 선택하세요.");
                    System.out.println("\t\t\t\t\t보유한 돈 : " + Game.Character.player.money + "\t\t보유한 공간 : " + char_container);
                    selected_in_sell = scan.nextInt();
                }
            }
        }
        return 0;
    } // 2스테이지부터 수정해야함

    public int sell(int char_money, int char_container) {

        while (true) {
            if (entered == false) {
                if (selected_which == 20000) {
                    for (int i = 0; i < 12; i++) { // tmp값 초기화
                        tmp_item[i].price = Container.item_secret[i].price;
                    }
                    count = 0;
                    return 1;
                } else if (selected_which > count + 1) {
                    System.out.println("옳바른 숫자를 입력하세요.");
                    selected_which = scan.nextInt();
                } else {
                    while (true) {
                        System.out.println("판매하실 수량을 입력하세요.");
                        int qtty = scan.nextInt();
                        if (tmp_item[selected_which - 1].stock < qtty) {
                            System.out.println("재고보다 많은 양을 판매 할 수 없습니다. 다시 입력하세요.");
                        } else {
                            tmp_item[selected_which - 1].stock += -qtty;
                            char_money += tmp_item[selected_which - 1].price * qtty;
                            char_container += tmp_item[selected_which - 1].cbm * qtty;
                            cbm_after_selling = char_container;
                            //여기가 문제
                            System.out.println(tmp_item[selected_which - 1].name + " " + qtty + "개 판매하여 총 " + tmp_item[selected_which - 1].price * qtty + "이코 벌었습니다.");
                            Container.item_inven[Container.get_number_through_name_in_inven(tmp_item[selected_which - 1].name)].stock = tmp_item[selected_which - 1].stock;
                            for (int i = 0; i < tmp_item.length; i++) {
                                tmp_item[i].price = Container.item_secret[i].price;
                            }
                            count = 0;
                            return char_money;
                        }
                    }
                }
            } else if (entered == true) {
                if (tmp_item[selected_which - 1].name == rice[0].name) { // 쌀
                    if (selected_in_sell == 20000) {
                        for (int i = 0; i < tmp_item.length; i++) { // tmp값 초기화
                            tmp_item[i].price = Container.item_secret[i].price;
                        }
                        for (int i = 0; i < rice.length; i++) {
                            rice[i].price = Item.make_item(2).price;
                        }
                        rice_count = 0;
                        count = 0;
                        return 1;
                    } else if (selected_in_sell > rice_count + 1) {
                        System.out.println("옳바른 숫자를 입력하세요.");
                        selected_which = scan.nextInt();
                    } else {
                        while (true) {
                            System.out.println("판매하실 수량을 입력하세요.");
                            int qtty = scan.nextInt();
                            if (rice[selected_in_sell - 1].stock < qtty) {
                                System.out.println("재고보다 많은 양을 판매 할 수 없습니다. 다시 입력하세요.");
                            } else {
                                tmp_item[selected_which - 1].stock += -qtty;
                                rice[selected_in_sell - 1].stock += -qtty;
                                if (rice[selected_in_sell - 1].stock <= 0) {
                                    rice[selected_in_sell - 1].storage = Item.make_item(2).storage;
                                }
                                char_money += rice[selected_in_sell - 1].price * qtty;
                                char_container += tmp_item[selected_which - 1].cbm * qtty;
                                cbm_after_selling = char_container;
                                //여기가 문제
                                System.out.println(rice[selected_in_sell - 1].name + " " + qtty + "개 판매하여 총 " + rice[selected_in_sell - 1].price * qtty + "이코 벌었습니다.");
                                Container.item_inven[Container.get_number_through_name_in_inven(tmp_item[selected_which - 1].name)].stock = tmp_item[selected_which - 1].stock;
                                for (int i = 0; i < tmp_item.length; i++) {
                                    tmp_item[i].price = Container.item_secret[i].price;
                                }
                                for (int i = 0; i < rice.length; i++) {
                                    rice[i].price = Item.make_item(2).price;
                                }
                                count = 0;
                                rice_count = 0;
                                return char_money;
                            }
                        }
                    }
                } else if (tmp_item[selected_which - 1].name == ginseng[0].name) { // 인삼
                    if (selected_in_sell == 20000) {
                        for (int i = 0; i < tmp_item.length; i++) { // tmp값 초기화
                            tmp_item[i].price = Container.item_secret[i].price;
                        }
                        for (int i = 0; i < ginseng.length; i++) {
                            ginseng[i].price = Item.make_item(3).price;
                        }
                        ginseng_count = 0;
                        count = 0;
                        return 1;
                    } else if (selected_in_sell > ginseng_count + 1) {
                        System.out.println("옳바른 숫자를 입력하세요.");
                        selected_which = scan.nextInt();
                    } else {
                        while (true) {
                            System.out.println("판매하실 수량을 입력하세요.");
                            int qtty = scan.nextInt();
                            if (ginseng[selected_in_sell - 1].stock < qtty) {
                                System.out.println("재고보다 많은 양을 판매 할 수 없습니다. 다시 입력하세요.");
                            } else {
                                tmp_item[selected_which - 1].stock += -qtty;
                                ginseng[selected_in_sell - 1].stock += -qtty;
                                if (ginseng[selected_in_sell - 1].stock == 0) {
                                    ginseng[selected_in_sell - 1].storage = Item.make_item(3).storage;
                                }
                                char_money += ginseng[selected_in_sell - 1].price * qtty;
                                char_container += tmp_item[selected_which - 1].cbm * qtty;
                                cbm_after_selling = char_container;
                                //여기가 문제
                                System.out.println(ginseng[selected_in_sell - 1].name + " " + qtty + "개 판매하여 총 " + ginseng[selected_in_sell - 1].price * qtty + "이코 벌었습니다.");
                                Container.item_inven[Container.get_number_through_name_in_inven(tmp_item[selected_which - 1].name)].stock = tmp_item[selected_which - 1].stock;
                                for (int i = 0; i < tmp_item.length; i++) {
                                    tmp_item[i].price = Container.item_secret[i].price;
                                }
                                for (int i = 0; i < ginseng.length; i++) {
                                    ginseng[i].price = Item.make_item(3).price;
                                }
                                count = 0;
                                ginseng_count = 0;
                                return char_money;
                            }
                        }
                    }
                } else if (tmp_item[selected_which - 1].name == tea[0].name) { // 차
                    if (selected_in_sell == 20000) {
                        for (int i = 0; i < tmp_item.length; i++) { // tmp값 초기화
                            tmp_item[i].price = Container.item_secret[i].price;
                        }
                        for (int i = 0; i < tea.length; i++) {
                            tea[i].price = Item.make_item(4).price;
                        }
                        tea_count = 0;
                        count = 0;
                        return 1;
                    } else if (selected_in_sell > tea_count + 1) {
                        System.out.println("옳바른 숫자를 입력하세요.");
                        selected_which = scan.nextInt();
                    } else {
                        while (true) {
                            System.out.println("판매하실 수량을 입력하세요.");
                            int qtty = scan.nextInt();
                            if (tea[selected_in_sell - 1].stock < qtty) {
                                System.out.println("재고보다 많은 양을 판매 할 수 없습니다. 다시 입력하세요.");
                            } else {
                                tmp_item[selected_which - 1].stock += -qtty;
                                tea[selected_in_sell - 1].stock += -qtty;
                                if (tea[selected_in_sell - 1].stock == 0) {
                                    tea[selected_in_sell - 1].storage = Item.make_item(4).storage;
                                }
                                char_money += tea[selected_in_sell - 1].price * qtty;
                                char_container += tmp_item[selected_which - 1].cbm * qtty;
                                cbm_after_selling = char_container;
                                //여기가 문제
                                System.out.println(tea[selected_in_sell - 1].name + " " + qtty + "개 판매하여 총 " + tea[selected_in_sell - 1].price * qtty + "이코 벌었습니다.");
                                Container.item_inven[Container.get_number_through_name_in_inven(tmp_item[selected_which - 1].name)].stock = tmp_item[selected_which - 1].stock;
                                for (int i = 0; i < tmp_item.length; i++) {
                                    tmp_item[i].price = Container.item_secret[i].price;
                                }
                                for (int i = 0; i < tea.length; i++) {
                                    tea[i].price = Item.make_item(4).price;
                                }
                                count = 0;
                                tea_count = 0;
                                return char_money;
                            }
                        }
                    }
                } else if (tmp_item[selected_which - 1].name == horse[0].name) { // 말
                    if (selected_in_sell == 20000) {
                        for (int i = 0; i < tmp_item.length; i++) { // tmp값 초기화
                            tmp_item[i].price = Container.item_secret[i].price;
                        }
                        for (int i = 0; i < horse.length; i++) {
                            horse[i].price = Item.make_item(6).price;
                        }
                        horse_count = 0;
                        count = 0;
                        return 1;
                    } else if (selected_in_sell > horse_count + 1) {
                        System.out.println("옳바른 숫자를 입력하세요.");
                        selected_which = scan.nextInt();
                    } else {
                        while (true) {
                            System.out.println("판매하실 수량을 입력하세요.");
                            int qtty = scan.nextInt();
                            if (horse[selected_in_sell - 1].stock < qtty) {
                                System.out.println("재고보다 많은 양을 판매 할 수 없습니다. 다시 입력하세요.");
                            } else {
                                tmp_item[selected_which - 1].stock += -qtty;
                                horse[selected_in_sell - 1].stock += -qtty;
                                if (horse[selected_in_sell - 1].stock == 0) {
                                    horse[selected_in_sell - 1].storage = Item.make_item(6).storage;
                                }
                                char_money += horse[selected_in_sell - 1].price * qtty;
                                char_container += tmp_item[selected_which - 1].cbm * qtty;
                                cbm_after_selling = char_container;
                                //여기가 문제
                                System.out.println(horse[selected_in_sell - 1].name + " " + qtty + "개 판매하여 총 " + horse[selected_in_sell - 1].price * qtty + "이코 벌었습니다.");
                                Container.item_inven[Container.get_number_through_name_in_inven(tmp_item[selected_which - 1].name)].stock = tmp_item[selected_which - 1].stock;
                                for (int i = 0; i < tmp_item.length; i++) {
                                    tmp_item[i].price = Container.item_secret[i].price;
                                }
                                for (int i = 0; i < horse.length; i++) {
                                    horse[i].price = Item.make_item(6).price;
                                }
                                count = 0;
                                horse_count = 0;
                                return char_money;
                            }
                        }
                    }
                }else if (tmp_item[selected_which - 1].name == medicine[0].name) { // 약재
                    if (selected_in_sell == 20000) {
                        for (int i = 0; i < tmp_item.length; i++) { // tmp값 초기화
                            tmp_item[i].price = Container.item_secret[i].price;
                        }
                        for (int i = 0; i < medicine.length; i++) {
                            medicine[i].price = Item.make_item(8).price;
                        }
                        medicine_count = 0;
                        count = 0;
                        return 1;
                    } else if (selected_in_sell > medicine_count + 1) {
                        System.out.println("옳바른 숫자를 입력하세요.");
                        selected_which = scan.nextInt();
                    } else {
                        while (true) {
                            System.out.println("판매하실 수량을 입력하세요.");
                            int qtty = scan.nextInt();
                            if (medicine[selected_in_sell - 1].stock < qtty) {
                                System.out.println("재고보다 많은 양을 판매 할 수 없습니다. 다시 입력하세요.");
                            } else {
                                tmp_item[selected_which - 1].stock += -qtty;
                                medicine[selected_in_sell - 1].stock += -qtty;
                                if (medicine[selected_in_sell - 1].stock == 0) {
                                    medicine[selected_in_sell - 1].storage = Item.make_item(8).storage;
                                }
                                char_money += medicine[selected_in_sell - 1].price * qtty;
                                char_container += tmp_item[selected_which - 1].cbm * qtty;
                                cbm_after_selling = char_container;
                                //여기가 문제
                                System.out.println(medicine[selected_in_sell - 1].name + " " + qtty + "개 판매하여 총 " + medicine[selected_in_sell - 1].price * qtty + "이코 벌었습니다.");
                                Container.item_inven[Container.get_number_through_name_in_inven(tmp_item[selected_which - 1].name)].stock = tmp_item[selected_which - 1].stock;
                                for (int i = 0; i < tmp_item.length; i++) {
                                    tmp_item[i].price = Container.item_secret[i].price;
                                }
                                for (int i = 0; i < medicine.length; i++) {
                                    medicine[i].price = Item.make_item(8).price;
                                }
                                count = 0;
                                medicine_count = 0;
                                return char_money;
                            }
                        }
                    }
                } else if (tmp_item[selected_which - 1].name == flavor[0].name) { // 향료
                    if (selected_in_sell == 20000) {
                        for (int i = 0; i < tmp_item.length; i++) { // tmp값 초기화
                            tmp_item[i].price = Container.item_secret[i].price;
                        }
                        for (int i = 0; i < flavor.length; i++) {
                            flavor[i].price = Item.make_item(10).price;
                        }
                        flavor_count = 0;
                        count = 0;
                        return 1;
                    } else if (selected_in_sell > flavor_count + 1) {
                        System.out.println("옳바른 숫자를 입력하세요.");
                        selected_which = scan.nextInt();
                    } else {
                        while (true) {
                            System.out.println("판매하실 수량을 입력하세요.");
                            int qtty = scan.nextInt();
                            if (flavor[selected_in_sell - 1].stock < qtty) {
                                System.out.println("재고보다 많은 양을 판매 할 수 없습니다. 다시 입력하세요.");
                            } else {
                                tmp_item[selected_which - 1].stock += -qtty;
                                flavor[selected_in_sell - 1].stock += -qtty;
                                if (flavor[selected_in_sell - 1].stock == 0) {
                                    flavor[selected_in_sell - 1].storage = Item.make_item(10).storage;
                                }
                                char_money += flavor[selected_in_sell - 1].price * qtty;
                                char_container += tmp_item[selected_which - 1].cbm * qtty;
                                cbm_after_selling = char_container;
                                //여기가 문제
                                System.out.println(flavor[selected_in_sell - 1].name + " " + qtty + "개 판매하여 총 " + flavor[selected_in_sell - 1].price * qtty + "이코 벌었습니다.");
                                Container.item_inven[Container.get_number_through_name_in_inven(tmp_item[selected_which - 1].name)].stock = tmp_item[selected_which - 1].stock;
                                for (int i = 0; i < tmp_item.length; i++) {
                                    tmp_item[i].price = Container.item_secret[i].price;
                                }
                                for (int i = 0; i < flavor.length; i++) {
                                    flavor[i].price = Item.make_item(10).price;
                                }
                                count = 0;
                                flavor_count = 0;
                                return char_money;
                            }
                        }
                    }
                }
            }
        }
    }

    public void clear_storage(){
        for (int i = 0; i < rice.length; i++){
            rice[i].stock = 0;
            rice[i].storage = 0;
        }
        for (int i = 0; i < ginseng.length; i++){
            ginseng[i].stock = 0;
            ginseng[i].storage = 0;
        }
        for (int i = 0; i < tea.length; i++){
            tea[i].stock = 0;
            tea[i].storage = 0;
        }
        for (int i = 0; i < horse.length; i++){
            horse[i].stock = 0;
            horse[i].storage = 0;
        }
        for (int i = 0; i < medicine.length; i++){
            medicine[i].stock = 0;
            medicine[i].storage = 0;
        }
        for (int i = 0; i < flavor.length; i++){
            flavor[i].stock = 0;
            flavor[i].storage = 0;
        }
    }

    public void clear_secret_storage(){
        for (int i = 0; i < rice_secret.length; i++){
            rice_secret[i].stock = 0;
            rice_secret[i].storage = 0;
        }
        for (int i = 0; i < ginseng_secret.length; i++){
            ginseng_secret[i].stock = 0;
            ginseng_secret[i].storage = 0;
        }
        for (int i = 0; i < tea_secret.length; i++){
            tea_secret[i].stock = 0;
            tea_secret[i].storage = 0;
        }
        for (int i = 0; i < horse_secret.length; i++){
            horse_secret[i].stock = 0;
            horse_secret[i].storage = 0;
        }
        for (int i = 0; i < medicine_secret.length; i++){
            medicine_secret[i].stock = 0;
            medicine_secret[i].storage = 0;
        }
        for (int i = 0; i < flavor_secret.length; i++){
            flavor_secret[i].stock = 0;
            flavor_secret[i].storage = 0;
        }
    }

    public void input_data_storage(String name, int stock) {

        if(name == rice[0].name) {
            for (int i = 0; i < rice.length; i++) { // 쌀
                for (int j = 0; j < rice.length; j++){
                    if (rice[j].storage == rice[i].storage) { // 쌀 카테고리에 넣는 과정
                        rice[j].stock += stock;
                        break;
                    }
                }
                break;
            }
        } else if(name == ginseng[0].name) {
            for (int i = 0; i < ginseng.length; i++) { // 쌀
                for (int j = 0; j < ginseng.length; j++){
                    if (ginseng[j].storage == ginseng[i].storage) { // 쌀 카테고리에 넣는 과정
                        ginseng[j].stock += stock;
                        break;
                    }
                }
                break;
            }
        } else if(name == tea[0].name) {
            for (int i = 0; i < tea.length; i++) { // 쌀
                for (int j = 0; j < tea.length; j++){
                    if (tea[j].storage == tea[i].storage) { // 쌀 카테고리에 넣는 과정
                        tea[j].stock += stock;
                        break;
                    }
                }
                break;
            }
        } else if(name == horse[0].name) {
            for (int i = 0; i < horse.length; i++) { // 쌀
                for (int j = 0; j < horse.length; j++){
                    if (horse[j].storage == horse[i].storage) { // 쌀 카테고리에 넣는 과정
                        horse[j].stock += stock;
                        break;
                    }
                }
                break;
            }
        } else if(name == medicine[0].name) {
            for (int i = 0; i < medicine.length; i++) { // 쌀
                for (int j = 0; j < medicine.length; j++){
                    if (medicine[j].storage == medicine[i].storage) { // 쌀 카테고리에 넣는 과정
                        medicine[j].stock += stock;
                        break;
                    }
                }
                break;
            }
        } else if(name == flavor[0].name) {
            for (int i = 0; i < flavor.length; i++) { // 쌀
                for (int j = 0; j < flavor.length; j++){
                    if (flavor[j].storage == flavor[i].storage) { // 쌀 카테고리에 넣는 과정
                        flavor[j].stock += stock;
                        break;
                    }
                }
                break;
            }
        }
    }

    public void throw_data_storage(String name, int stock) {

        if(name == rice[0].name) {
            for (int i = 0; i < rice.length; i++) { // 쌀
                for (int j = 0; j < rice.length; j++){
                    if (rice[j].storage == rice[i].storage) { // 쌀 카테고리에 넣는 과정
                        rice[j].stock -= stock;
                        if(rice[j].stock <= 0){
                            rice[j].storage = Item.make_item(2).storage;
                        }
                        break;
                    }
                }
                break;
            }
        } else if(name == ginseng[0].name) {
            for (int i = 0; i < ginseng.length; i++) { // 쌀
                for (int j = 0; j < ginseng.length; j++){
                    if (ginseng[j].storage == ginseng[i].storage) { // 쌀 카테고리에 넣는 과정
                        ginseng[j].stock -= stock;
                        if(ginseng[j].stock <= 0){
                            ginseng[j].storage = Item.make_item(3).storage;
                        }
                        break;
                    }
                }
                break;
            }
        } else if(name == tea[0].name) {
            for (int i = 0; i < tea.length; i++) { // 쌀
                for (int j = 0; j < tea.length; j++){
                    if (tea[j].storage == tea[i].storage) { // 쌀 카테고리에 넣는 과정
                        tea[j].stock -= stock;
                        if(tea[j].stock <= 0){
                            tea[j].storage = Item.make_item(4).storage;
                        }
                        break;
                    }
                }
                break;
            }
        } else if(name == horse[0].name) {
            for (int i = 0; i < horse.length; i++) { // 쌀
                for (int j = 0; j < horse.length; j++){
                    if (horse[j].storage == horse[i].storage) { // 쌀 카테고리에 넣는 과정
                        horse[j].stock -= stock;
                        if(horse[j].stock <= 0){
                            horse[j].storage = Item.make_item(6).storage;
                        }
                        break;
                    }
                }
                break;
            }
        } else if(name == medicine[0].name) {
            for (int i = 0; i < medicine.length; i++) { // 쌀
                for (int j = 0; j < medicine.length; j++){
                    if (medicine[j].storage == medicine[i].storage) { // 쌀 카테고리에 넣는 과정
                        medicine[j].stock -= stock;
                        if(medicine[j].stock <= 0){
                            medicine[j].storage = Item.make_item(8).storage;
                        }
                        break;
                    }
                }
                break;
            }
        } else if(name == flavor[0].name) {
            for (int i = 0; i < flavor.length; i++) { // 쌀
                for (int j = 0; j < flavor.length; j++){
                    if (flavor[j].storage == flavor[i].storage) { // 쌀 카테고리에 넣는 과정
                        flavor[j].stock -= stock;
                        if(flavor[j].stock <= 0){
                            flavor[j].storage = Item.make_item(10).storage;
                        }
                        break;
                    }
                }
                break;
            }
        }
    }

    public void show_rice(int stage) {
        for (int i = 0; i < rice.length; i++) {
            if (rice[i].stock != 0) {
                rice_count++;
            }
            if (stage == 1) {
                rice[i].price *= array_1[1];
            } else if (stage == 2) {
                rice[i].price *= array_2[1];
            } else if (stage == 3) {
                rice[i].price *= array_3[1];
            } else if (stage == 4) {
                rice[i].price *= array_4[1];
            }
        }

        for (int i = 0; i < rice.length; i++) {
            if (rice[i].stock != 0) {
                if (rice[i].storage <= 0) {
                    rice[i].price *= 0.6;
                }
                System.out.println(i + 1 + ". " + rice[i].name + "\t\t|\t수량 : " + rice[i].stock + "\t\t|\t 신선도 : " + rice[i].storage + "\t\t|\t 판매 가격 : " + rice[i].price);
            }
        }
    }

    public void show_rice_for_cntr(int stage) {


        for (int i = 0; i < rice.length; i++) {
            if (rice[i].stock != 0) {
                System.out.println(i + 1 + ". " + rice[i].name + "\t\t|\t수량 : " + rice[i].stock + "\t\t|\t 신선도 : " + rice[i].storage + "\t|\t 개별 차지하는 공간 : " + rice[i].cbm + "\t\t|\t 총 차지하는 공간 : " + rice[i].cbm * rice[i].stock);
            }
            rice_count++;
        }
    }

    public void show_rice_secret_for_cntr(int stage) {
        for (int i = 0; i < rice_secret.length; i++) {
            if (rice_secret[i].stock != 0) {
                System.out.println(i + 1 + ". " + rice_secret[i].name + "\t\t|\t수량 : " + rice_secret[i].stock + "\t\t|\t 신선도 : " + rice_secret[i].storage + "\t|\t 개별 차지하는 공간 : " + rice_secret[i].cbm + "\t\t|\t 총 차지하는 공간 : " + rice_secret[i].cbm * rice_secret[i].stock);
            }
        }
    }

    public void show_ginseng(int stage) {
        for (int i = 0; i < ginseng.length; i++) {
            if (ginseng[i].stock != 0) {
                ginseng_count++;
            }
            if (stage == 1) {
                ginseng[i].price *= array_1[2];
            } else if (stage == 2) {
                ginseng[i].price *= array_2[2];
            } else if (stage == 3) {
                ginseng[i].price *= array_3[2];
            } else if (stage == 4) {
                ginseng[i].price *= array_4[2];
            }
        }

        for (int i = 0; i < ginseng.length; i++) {
            if (ginseng[i].stock != 0) {
                if (ginseng[i].storage <= 0) {
                    ginseng[i].price *= 0.6;
                }
                System.out.println(i + 1 + ". " + ginseng[i].name + "\t\t|\t수량 : " + ginseng[i].stock + "\t\t|\t 신선도 : " + ginseng[i].storage + "\t\t|\t 판매 가격 : " + ginseng[i].price);
            }
        }
    }

    public void show_ginseng_for_cntr(int stage) {
        for (int i = 0; i < ginseng.length; i++) {
            if (ginseng[i].stock != 0) {
                System.out.println(i + 1 + ". " + ginseng[i].name + "\t\t|\t수량 : " + ginseng[i].stock + "\t\t|\t 신선도 : " + ginseng[i].storage + "\t|\t 개별 차지하는 공간 : " + ginseng[i].cbm + "\t\t|\t 총 차지하는 공간 : " + ginseng[i].cbm * ginseng[i].stock);
            }
            ginseng_count++;

        }
    }

    public void show_ginseng_secret_for_cntr(int stage) {
        for (int i = 0; i < ginseng_secret.length; i++) {
            if (ginseng_secret[i].stock != 0) {
                System.out.println(i + 1 + ". " + ginseng_secret[i].name + "\t\t|\t수량 : " + ginseng_secret[i].stock + "\t\t|\t 신선도 : " + ginseng_secret[i].storage + "\t|\t 개별 차지하는 공간 : " + ginseng_secret[i].cbm + "\t\t|\t 총 차지하는 공간 : " + ginseng_secret[i].cbm * ginseng_secret[i].stock);
            }
        }
    }

    public void show_tea(int stage) {
        for (int i = 0; i < tea.length; i++) {
            if (tea[i].stock != 0) {
                tea_count++;
            }
            if (stage == 1) {
                tea[i].price *= array_1[3];
            } else if (stage == 2) {
                tea[i].price *= array_2[3];
            } else if (stage == 3) {
                tea[i].price *= array_3[3];
            } else if (stage == 4) {
                tea[i].price *= array_4[3];
            }
        }

        for (int i = 0; i < tea.length; i++) {
            if (tea[i].stock != 0) {
                if (tea[i].storage <= 0) {
                    tea[i].price *= 0.6;
                }
                System.out.println(i + 1 + ". " + tea[i].name + "\t\t|\t수량 : " + tea[i].stock + "\t\t|\t 신선도 : " + tea[i].storage + "\t\t|\t 판매 가격 : " + tea[i].price);
            }
        }
    }

    public void show_tea_for_cntr(int stage) {
        for (int i = 0; i < tea.length; i++) {
            if (tea[i].stock != 0) {
                System.out.println(i + 1 + ". " + tea[i].name + "\t\t|\t수량 : " + tea[i].stock + "\t\t|\t 신선도 : " + tea[i].storage + "\t|\t 개별 차지하는 공간 : " + tea[i].cbm + "\t\t|\t 총 차지하는 공간 : " + tea[i].cbm * tea[i].stock);
            }
            tea_count++;

        }
    }

    public void show_tea_secret_for_cntr(int stage) {
        for (int i = 0; i < tea_secret.length; i++) {
            if (tea_secret[i].stock != 0) {
                System.out.println(i + 1 + ". " + tea_secret[i].name + "\t\t|\t수량 : " + tea_secret[i].stock + "\t\t|\t 신선도 : " + tea_secret[i].storage + "\t|\t 개별 차지하는 공간 : " + tea_secret[i].cbm + "\t\t|\t 총 차지하는 공간 : " + tea_secret[i].cbm * tea_secret[i].stock);
            }
        }
    }

    public void show_horse(int stage) {
        for (int i = 0; i < horse.length; i++) {
            if (horse[i].stock != 0) {
                horse_count++;
            }
            if (stage == 1) {
                horse[i].price *= array_1[5];
            } else if (stage == 2) {
                horse[i].price *= array_2[5];
            } else if (stage == 3) {
                horse[i].price *= array_3[5];
            } else if (stage == 4) {
                horse[i].price *= array_4[5];
            }
        }

        for (int i = 0; i < horse.length; i++) {
            if (horse[i].stock != 0) {
                if (horse[i].storage <= 0) {
                    horse[i].price *= 0.6;
                }
                System.out.println(i + 1 + ". " + horse[i].name + "\t\t|\t수량 : " + horse[i].stock + "\t\t|\t 신선도 : " + horse[i].storage + "\t\t|\t 판매 가격 : " + horse[i].price);
            }
        }
    }

    public void show_horse_for_cntr(int stage) {
        for (int i = 0; i < horse.length; i++) {
            if (horse[i].stock != 0) {
                System.out.println(i + 1 + ". " + horse[i].name + "\t\t|\t수량 : " + horse[i].stock + "\t\t|\t 신선도 : " + horse[i].storage + "\t|\t 개별 차지하는 공간 : " + horse[i].cbm + "\t\t|\t 총 차지하는 공간 : " + horse[i].cbm * horse[i].stock);
            }
            horse_count++;
        }
    }

    public void show_horse_secret_for_cntr(int stage) {
        for (int i = 0; i < horse_secret.length; i++) {
            if (horse_secret[i].stock != 0) {
                System.out.println(i + 1 + ". " + horse_secret[i].name + "\t\t|\t수량 : " + horse_secret[i].stock + "\t\t|\t 신선도 : " + horse_secret[i].storage + "\t|\t 개별 차지하는 공간 : " + horse_secret[i].cbm + "\t\t|\t 총 차지하는 공간 : " + horse_secret[i].cbm * horse_secret[i].stock);
            }
        }
    }

    public void show_medicine(int stage) {
        for (int i = 0; i < medicine.length; i++) {
            if (medicine[i].stock != 0) {
                medicine_count++;
            }
            if (stage == 1) {
                medicine[i].price *= array_1[7];
            } else if (stage == 2) {
                medicine[i].price *= array_2[7];
            } else if (stage == 3) {
                medicine[i].price *= array_3[7];
            } else if (stage == 4) {
                medicine[i].price *= array_4[7];
            }
        }

        for (int i = 0; i < medicine.length; i++) {
            if (medicine[i].stock != 0) {
                if (medicine[i].storage <= 0) {
                    medicine[i].price *= 0.6;
                }
                System.out.println(i + 1 + ". " + medicine[i].name + "\t\t|\t수량 : " + medicine[i].stock + "\t\t|\t 신선도 : " + medicine[i].storage + "\t\t|\t 판매 가격 : " + medicine[i].price);
            }
        }
    }

    public void show_medicine_for_cntr(int stage) {
        for (int i = 0; i < medicine.length; i++) {
            if (medicine[i].stock != 0) {
                System.out.println(i + 1 + ". " + medicine[i].name + "\t\t|\t수량 : " + medicine[i].stock + "\t\t|\t 신선도 : " + medicine[i].storage + "\t|\t 개별 차지하는 공간 : " + medicine[i].cbm + "\t\t|\t 총 차지하는 공간 : " + medicine[i].cbm * medicine[i].stock);
            }
            medicine_count++;
        }
    }

    public void show_medicine_secret_for_cntr(int stage) {
        for (int i = 0; i < medicine_secret.length; i++) {
            if (medicine_secret[i].stock != 0) {
                System.out.println(i + 1 + ". " + medicine_secret[i].name + "\t\t|\t수량 : " + medicine_secret[i].stock + "\t\t|\t 신선도 : " + medicine_secret[i].storage + "\t|\t 개별 차지하는 공간 : " + medicine_secret[i].cbm + "\t\t|\t 총 차지하는 공간 : " + medicine_secret[i].cbm * medicine_secret[i].stock);
            }
        }
    }

    public void show_flavor(int stage) {
        for (int i = 0; i < flavor.length; i++) {
            if (flavor[i].stock != 0) {
                flavor_count++;
            }
            if (stage == 1) {
                flavor[i].price *= array_1[9];
            } else if (stage == 2) {
                flavor[i].price *= array_2[9];
            } else if (stage == 3) {
                flavor[i].price *= array_3[9];
            } else if (stage == 4) {
                flavor[i].price *= array_4[9];
            }
        }

        for (int i = 0; i < flavor.length; i++) {
            if (flavor[i].stock != 0) {
                if (flavor[i].storage <= 0) {
                    flavor[i].price *= 0.6;
                }
                System.out.println(i + 1 + ". " + flavor[i].name + "\t\t|\t수량 : " + flavor[i].stock + "\t\t|\t 신선도 : " + flavor[i].storage + "\t\t|\t 판매 가격 : " + flavor[i].price);
            }
        }
    }

    public void show_flavor_for_cntr(int stage) {
        for (int i = 0; i < flavor.length; i++) {
            if (flavor[i].stock != 0) {
                System.out.println(i + 1 + ". " + flavor[i].name + "\t\t|\t수량 : " + flavor[i].stock + "\t\t|\t 신선도 : " + flavor[i].storage + "\t|\t 개별 차지하는 공간 : " + flavor[i].cbm + "\t\t|\t 총 차지하는 공간 : " + flavor[i].cbm * flavor[i].stock);
            }
            flavor_count++;
        }
    }

    public void show_flavor_secret_for_cntr(int stage) {
        for (int i = 0; i < flavor_secret.length; i++) {
            if (flavor_secret[i].stock != 0) {
                System.out.println(i + 1 + ". " + flavor_secret[i].name + "\t\t|\t수량 : " + flavor_secret[i].stock + "\t\t|\t 신선도 : " + flavor_secret[i].storage + "\t|\t 개별 차지하는 공간 : " + flavor_secret[i].cbm + "\t\t|\t 총 차지하는 공간 : " + flavor_secret[i].cbm * flavor_secret[i].stock);
            }
        }
    }

    public int get_cbm_after_selling() {
        return cbm_after_selling;
    }

}
