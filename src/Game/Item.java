package Game;

public class Item {

    String name;
    int price;
    int cbm;
    int stock;
    int storage;
    boolean open;


    public Item(String name, int price, int cbm, int stock, int storage, boolean open){
        this.name = name;
        this.price = price;
        this.cbm = cbm;
        this.stock = stock;
        this.storage = storage;
        this.open = false;
    }

    static public final Item make_item(int select){
        Item[] item = new Item[12];

        item[0] = new Item("면  포", 140, 16, 0,9999, false);
        item[1] = new Item("쌀    ", 800, 96, 0,14, false);
        item[2] = new Item("인  삼", 1000, 16, 0,7, false); // 조선

        item[3] = new Item("차    ", 40, 4, 0,7, false);
        item[4] = new Item("비  단", 1600, 48, 0,9999, false);
        item[5] = new Item("말    ", 240000, 9000, 0,15, false); // 청나라

        item[6] = new Item("서  적", 200, 2, 0,9999, false);
        item[7] = new Item("약  재", 2200, 48, 0,10, false);
        item[8] = new Item("도자기", 26800, 216, 0,9999, false); // 명나라

        item[9] = new Item("향  료", 40, 2, 0,10, false);
        item[10] = new Item("구  리", 90000, 1000, 0,9999, false);
        item[11] = new Item("은    ", 150000, 1000, 0,9999, false); // 일본

        return item[select-1];
    }

    static public final Item make_item_for_blackMarket(int select){
        Item[] item_blackMarket = new Item[5];

        item_blackMarket[0] = new Item("청나라 - 대련 항로", 10000, 0, 0,0, false); // 조선 : 청,명,일본
        item_blackMarket[1] = new Item("명나라 - 상해 항로", 40000, 0, 0,0, false); // 청 : 명, 일본
        item_blackMarket[2] = new Item("일본 - 고베 항로  ", 400000, 0, 0,0, false); // 명 : 일본
        item_blackMarket[3] = new Item("해  도 (1일)     ", 30000, 0, 0,0, false);
        item_blackMarket[4] = new Item("가격표           ", 100000, 0, 0,0, false);

        return item_blackMarket[select-1];
    }
}
