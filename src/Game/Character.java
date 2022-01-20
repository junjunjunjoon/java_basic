package Game;

import java.util.Scanner;

class Character extends People {

    Scanner scan = new Scanner(System.in);
    double conciliation_atk;
    int special_function;
    int sailing;
    static Character player = new Character();

    public Character() {
        name = "";
        hp = 0;
        power = 2;
        avoiding = 0;
        money = 2000000; //500000;
        conciliation_atk = 0;
        crew = 1; // 50
        special_function = 0;
        sailing = 0;
    }

//    public Character make_character() {
//        Character A = new Character();
//        A.name = this.name;
//        A.hp = this.hp;
//        A.power = this.power;
//        A.avoiding = this.avoiding;
//        A.money = this.money;
//        A.conciliation_atk = this.conciliation_atk;
//        A.crew = this.crew;
//        A.special_function = this.special_function;
//        A.sailing = this.sailing;
//        return A;
//    }

    public int get_special_function() {
        return special_function;
    }

    public void type_player_name() {
        System.out.print("\n==============================================================================================" +
                "\n캐릭터명 입력 : ");
        name = scan.next();
    }

    public void choice_special_function() { // main에 만드는 것도 고려해야함.
        System.out.println("\n" +
                "원하는 특수기능을 선택하세요. (추후 변경 불가)\n" +
                "1. 검투술 - 선원의 공격력이 세진다. (100%)\n" +
                "2. 회유 - 회유 성공률이 올라간다. (10%)\n" +
                "3. 항해술 - 항해중 해적 만나는 빈도수가 적어진다. (-1)\n" +
                "4. 술꾼 - 정보를 얻을 확률이 올라간다. (33% 확률에서 75%로 상승)\n" +
                "5. 회피력 - 공격을 회피할 능력이 올라간다. (매 공격 데미지 10씩 회피)\n" +
                "6. 장사꾼 - 교역 시 물건을 더 비싸게 판다. (+0.02%)");

        special_function = scan.nextInt();
        while (true) { // 특수기능 선택
            if (special_function == 1) {
                power += 1;
                break;
            } else if (special_function == 2) {
                conciliation_atk += 1;
                break;
            } else if (special_function == 3) {
                sailing += 1;
                break;
            } else if (special_function == 4) {
                special_function = 4;
                break;
            } else if (special_function == 5) {
                avoiding += 10;
                break;
            } else if (special_function == 6) {
                break;
            } else {
                System.out.println("다시 입력하세요.");
            }
        }
    }

    public int get_sailing() {
        return sailing;
    }

    public int attack() {
        int sum = 0; // 데미지 총 합
        Scanner scan = new Scanner(System.in);

        System.out.println("1. 경궁 공격");
        System.out.println("2. 화궁 공격");
        System.out.println("3. 회유 하기");
        System.out.println("4. 도망 가기");
        int input = scan.nextInt();

        // 경궁 공격
        if (input == 1) {
            sum += power * crew - avoiding;
        } else if (input == 2) {
            sum += (power * crew - avoiding) / 4;
        } else if (input == 3) {
            //회유하기 어떻게 만들지?
        } else if (input == 4) {
            //도망가기 어떻게 만들지?
        }
        return sum;
    }

    public int attack_normal() {
        int sum = 0; // 데미지 총 합
        // 경궁 공격
        sum += power * crew - avoiding;
        return sum;
    }

    public int attack_fire() {
        int sum = 0; // 데미지 총 합
        // 불공격
        sum += (power * crew - avoiding) / 4;

        return sum;
    }

    public String get_char_name() {
        return name;
    }

    public void set_char_name(String name) {
        this.name = name;
    }

    public int get_char_hp() {
        return this.hp;
    }

    public void set_char_hp(int hp) {
        this.hp = hp;
    }

    public int get_char_power() {
        return power;
    }

    public void set_char_power(int power) {
        this.power = power;
    }

    public int get_char_avoiding() {
        return avoiding;
    }

    public void set_char_avoiding(int avoiding) {
        this.avoiding = avoiding;
    }

    public int get_char_money() {
        return money;
    }

    public void set_char_money(int money) {
        this.money = money;
    }

    public double get_char_conciliation() {
        return conciliation_atk;
    }

    public void set_char_conciliation(double conciliation_atk) {
        this.conciliation_atk = conciliation_atk;
    }

    public int get_char_crew() {
        return crew;
    }

    public void set_char_crew(int crew) {
        this.crew = crew;
    }

    public int get_char_function() {
        return special_function;
    }

    public void set_char_function(int function) {
        this.special_function = function;
    }


}
