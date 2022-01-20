package Game;

import java.util.Scanner;

public class Container {

    Scanner scan = new Scanner(System.in);

    // 최신화
    static Item[] item_inven = new Item[12];
    static Item[] item_secret = new Item[12];


    static public void make_item_inven_n_secret() {
        for (int i = 0; i < 12; i++) {
            item_inven[i] = Item.make_item(i + 1);
            item_secret[i] = Item.make_item(i + 1);
        }
    }     // 최신화 인벤토리 null 값 제거

    public void input_data_secret(String name, int stock) {

        for (int i = 0; i < item_secret.length; i++) {
            if (item_secret[i].name == name) {
                for (int j = 0; j < 1; j++) {
                    item_secret[i].stock += stock;
                }
            }
        }
    }     // 최신화

    public void input_data_inven(String name, int stock) {
        for (int i = 0; i < item_inven.length; i++) {
            if (item_inven[i].name == name) {
                for (int j = 0; j < 1; j++) {
                    item_inven[i].stock += stock;
                }
            }
        }
    }

    public void clear_inven() {
        for (int i = 0; i < item_inven.length; i++) {
            item_inven[i].stock = 0;
        }
    }

    public void clear_secret() {
        for (int i = 0; i < item_secret.length; i++) {
            item_secret[i].stock = 0;
        }
    }

    public void show_inven() {
        int count = 1;

        for (int i = 0; i < 12; i++) {
            if (item_inven[i].stock != 0) {
                if(item_inven[i].open == true){
                    System.out.println(count + ". " + item_inven[i].name + "\t\t|\t수량 : " + item_inven[i].stock + "\t\t|\t 개별 차지하는 공간 : " + item_inven[i].cbm + "\t\t|\t 총 차지하는 공간 : " + item_inven[i].cbm * item_inven[i].stock + "\t\t|\t 구매 한 가격 : " + item_inven[i].price);
                } else {
                    System.out.println(count + ". " + item_inven[i].name + "\t\t|\t수량 : " + item_inven[i].stock + "\t\t|\t 개별 차지하는 공간 : " + item_inven[i].cbm + "\t\t|\t 총 차지하는 공간 : " + item_inven[i].cbm * item_inven[i].stock);
                }
                count++;
            }
        }
    }

    static public int get_number_through_num_in_inven(int select) {
        int count = 0;

        for (int i = 0; i < item_inven.length; i++) {
            if (item_inven[i].stock != 0) {
                count++;
            }
            if(count == select){
                count = i;
                break;
            }
        }

        return count;
    }

    static public int get_number_through_num_in_secret(int select) {
        int count = 0;

        for (int i = 0; i < item_secret.length; i++) {
            if (item_secret[i].stock != 0) {
                count++;
            }
            if(count == select){
                count = i;
                break;
            }
        }
        return count;
    }

    static public int get_number_through_name_in_inven(String name) {
        int index = 0;

        for (int i = 0; i < item_inven.length; i++) {
            if (item_inven[i].name == name) {
                index = i;
            }
        }
        return index;
    }

    public void show_secret() {
        int count = 1;

        for (int i = 0; i < 12; i++) {
            if (item_secret[i].stock != 0) {
                System.out.println(count + ". " + item_secret[i].name + "\t\t|\t수량 : " + item_secret[i].stock + "\t\t|\t 개별 차지하는 공간 : " + item_secret[i].cbm + "\t\t|\t 총 차지하는 공간 : " + item_secret[i].cbm * item_secret[i].stock);
                count++;
            }
        }
    }

    public String get_name_in_inven(int select) {

        return item_inven[select - 1].name;
    }

    public String get_name_in_secret(int select) {

        return item_secret[select - 1].name;
    }

    public int get_cbm_through_name(String name) {
        int tmp = 0;
        for (int i = 0; i < item_inven.length; i++) {
            if (item_inven[i].name == name) {
                tmp = item_inven[i].cbm;
            }
        }
        return tmp;
    }

    public int distinguish_inven() {
        int count = 0;

        for (int i = 0; i < item_inven.length; i++) {
            if (item_inven[i].stock != 0) {
                count++;
            }
        }
        return count;
    }

    public int distinguish_secret() {
        int count = 0;

        for (int i = 0; i < item_secret.length; i++) {
            if (item_secret[i].stock != 0) {
                count++;
            }
        }
        return count;
    }
}
