package Game;

import java.util.Scanner;

public class Shipbuilding {

    Vessel[] vsl_list = new Vessel[5];
    Scanner scan = new Scanner(System.in);
    int input;
    int upgrade_actual_count = 0;
    int org_secret_space = 0;

    public Shipbuilding(){
        vsl_list[0] = new Vessel("잡동사니로 수리한 난파선",600,20,50,3000,0,0);

        vsl_list[1] = new Vessel("잡동사니로 수리한 난파선",600,20,50,3000,0,0);
        vsl_list[2] = new Vessel("수군 전투선단 중맹선(中猛船)", 1000,40,70,18000,140000,0);
        vsl_list[3] = new Vessel("수군 전투선단 대맹선(大猛船)",1200,50,90,27000,260000,0);
        vsl_list[4] = new Vessel("전함 판옥선(板屋船)", 2100,70,120,45000,550000,0);
    }

    public void show_vsl_list(){
        for (int i = 2; i < 5; i++){
            System.out.println(i-1 + ". " + vsl_list[i].name + "\n체력 : " + vsl_list[i].hp + "\t|\t 최소 선원수 : " + vsl_list[i].min_crew + "\t|\t 최대 선원수 : " + vsl_list[i].max_crew + "\t|\t 적재 공간 : " + vsl_list[i].container + "\t|\t 가격 : " + vsl_list[i].price + "\n");
        }
    }

    public void purchase(int select, int char_money){
        while(true) {
            if (vsl_list[select + 1].price > char_money) {
                System.out.println("금액이 부족합니다.\n\n");
                break;
            } else if (vsl_list[0].name == vsl_list[select + 1].name) {
                System.out.println("현재 모선과 동일하여 구매 할 수 없습니다.");
                break;
            } else {
                System.out.println(vsl_list[select + 1].name + "을 구매 완료 했습니다.");
                vsl_list[0].name = vsl_list[select + 1].name;
                vsl_list[0].hp = vsl_list[select + 1].hp;
                vsl_list[0].min_crew = vsl_list[select + 1].min_crew;
                vsl_list[0].max_crew = vsl_list[select + 1].max_crew;
                vsl_list[0].container = vsl_list[select + 1].container;
                vsl_list[0].price = vsl_list[select + 1].price;
                break;
            }
        }
    }

    public void upgrade(String name, int upgrade_count, int char_money){
        if(upgrade_count < 2){
            System.out.println("화물창은 총 2단계까지 늘릴 수 있다네");
            System.out.println("\n1. 화물창 업그레이드 (+1000)\n9. 나가기");
            System.out.println("===========================================");
            input = scan.nextInt();
            if(input == 1) {
                System.out.println("가격은 20000이코라네, 늘리겠는가?\t\t\t 보유한 돈 : " + char_money);
                System.out.println("1. 업그레이드\n2. 취소");
                input = scan.nextInt();
                if (input == 1) {
                    if (char_money < 20000) {
                        System.out.println("돈을 먼저 내야 작업을 할 수 있다네, 투입되는 인력이 하나둘이 아닐세");
                    } else {
                        vsl_list[0].container += 1000;
                        for (int i = 1; i < vsl_list.length; i++){
                            if(vsl_list[i].name == name){
                                vsl_list[i].container += 1000;
                            }
                        }
                        System.out.println("화물창이 잘 늘어났네, 다음에 또 보게나");
                        upgrade_actual_count++; // 실제로 얼마나 업그레이드가 됐는지 확인해야함
                    }
                } else if (input == 2) {
                    System.out.println("다음에 찾아오게나, 또 볼 일이 있을걸세");
                }
            } else if(input == 9){

            } else {
                System.out.println("다시 입력하세요.");
            }
        } else if(upgrade_count >= 2 & upgrade_count < 5){
            System.out.println("화물창을 최대로 늘린 것 같은데, 무슨일로 찾아온겐가?");
            System.out.println("9. 나가기");
            input = scan.nextInt();
            if(input == 8282){
                upgrade_actual_count++;
                System.out.println("안쪽으로 들어오게");
                System.out.println("누구한테 듣고 온겐가, 다른사람들에겐 절대 말하지 말게\n해적들이 비밀 화물창 위치를 알게 된다면 더이상 비밀창고의 역할을 하지 못하게 될걸세");
                System.out.println("1. 비밀 화물창 업그레이드 (+500)");
                System.out.println("===========================================");
                input = scan.nextInt();
                System.out.println("가격은 30000이코라네, 늘리겠는가?\t\t\t 보유한 돈 : " + char_money);
                System.out.println("1. 업그레이드\n2. 취소");
                input = scan.nextInt();
                if(input == 1){
                    if(char_money < 30000){
                        System.out.println("돈을 먼저 내야 작업을 할 수 있다네, 투입되는 인력이 하나둘이 아닐세");
                    } else {
                        vsl_list[0].secret_cntr += 500;
                        for (int i = 1; i < vsl_list.length; i++){
                            if(vsl_list[i].name == name){
                                vsl_list[i].secret_cntr += 500;
                                org_secret_space += 500;
                            }
                        }
                        System.out.println("비밀 화물창이 잘 늘어났네, 다음에 또 보게나");
                       upgrade_actual_count++; // 실제로 얼마나 업그레이드가 됐는지 확인해야함
                    }
                } else if( input == 2){
                    System.out.println("다음에 찾아오게나, 또 볼 일이 있을걸세");
                }
            }
        } else if(upgrade_count >= 4){
            System.out.println("이 배는 내가 더이상 손 댈 곳이 없어 보이네");
        }
    }

    public int get_vsl_hp(){
        return this.vsl_list[0].hp;
    }

    public String give_main_vsl_name(){
        return vsl_list[0].name;
    }

    public int give_main_vsl_hp(){
        return vsl_list[0].hp;
    }

    public void set_vsl_hp(int vsl_hp){
        this.vsl_list[0].hp = vsl_hp;
    }
    public int give_main_vsl_min(){
        return vsl_list[0].min_crew;
    }
    public int give_main_vsl_max(){
        return vsl_list[0].max_crew;
    }
    public int give_main_vsl_container(){
        return vsl_list[0].container;
    }
    public int give_main_vsl_price(){
        return vsl_list[0].price;
    }

    public int get_vsl_container(){
        return vsl_list[0].container;
    }
    public void set_vsl_container(int vsl_container){
        this.vsl_list[0].container = vsl_container;
    }

    public int get_secret_cntr() {
        return vsl_list[0].secret_cntr;
    }
    public void set_secret_cntr(int secret_cntr) {
        vsl_list[0].secret_cntr = secret_cntr;
    }
    public void set_inven_cntr(int inven_cntr) {
        vsl_list[0].container = inven_cntr;
    }



    public int getUpgrade_actual_count() {
        return upgrade_actual_count;
    }
    public void setUpgrade_actual_count(int count) {
        this.upgrade_actual_count = count;
    }

    public int get_vsl_org_cntr(String name){
        int tmp = 0;
        for (int i = 1; i < vsl_list.length; i++){
            if(this.vsl_list[i].name == name){
                tmp = this.vsl_list[i].container;
            }
        }
        return tmp;
    }
    public int get_vsl_org_secret(String name){
        int tmp = 0;
        for (int i = 1; i < vsl_list.length; i++){
            if(this.vsl_list[i].name == name){
                tmp = this.vsl_list[i].secret_cntr;
            }
        }
        return tmp;
    }

    public int get_vsl_org_hp(String name){
        int tmp = 0;
        for (int i = 1; i < vsl_list.length; i++){
            if(this.vsl_list[i].name == name){
                tmp = this.vsl_list[i].hp;
            }
        }
        return tmp;
    }
    public int get_vsl_org_crew(String name){
        int tmp = 0;
        for (int i = 1; i < vsl_list.length; i++){
            if(this.vsl_list[i].name == name){
                tmp = this.vsl_list[i].max_crew;
            }
        }
        return tmp;
    }
}
