
public class TestItem {

    String name;
    int price;
    int cbm;
    int stock;
    int storage;

    public TestItem(String name, int price, int cbm, int stock, int storage) {
        this.name = name;
        this.price = price;
        this.cbm = cbm;
        this.stock = stock;
        this.storage = storage;
    }

    static public final TestItem make_item(int select) {
        TestItem[] item = new TestItem[12];

        item[0] = new TestItem("면  포", 140, 16, 0, 9999);
        item[1] = new TestItem("쌀    ", 800, 96, 0, 14);
        item[2] = new TestItem("인  삼", 1000, 16, 0, 7); // 조선

        item[3] = new TestItem("차    ", 40, 4, 0, 7);
        item[4] = new TestItem("비  단", 1600, 48, 0, 9999);
        item[5] = new TestItem("말    ", 240000, 9000, 0, 15); // 청나라

        item[6] = new TestItem("서  적", 200, 2, 0, 9999);
        item[7] = new TestItem("약  재", 2200, 48, 0, 10);
        item[8] = new TestItem("도자기", 26800, 216, 0, 9999); // 명나라

        item[9] = new TestItem("향  료", 40, 2, 0, 10);
        item[10] = new TestItem("구  리", 90000, 1000, 0, 9999);
        item[11] = new TestItem("은    ", 150000, 1000, 0, 9999); // 일본

        return item[select - 1];
    }
}