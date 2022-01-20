package Game;

public class ThreadAutoAttack extends Thread{

    int stage;
    int vsl_org_hp;
    static boolean stop = false;
    int distinguish;
    int max_stage;
    //static boolean work = true;
    //static boolean Character_life = true;

    public ThreadAutoAttack(int stage, int max_stage, int vsl_org_hp, int distinguish){
        this.stage = stage;
        this.vsl_org_hp = vsl_org_hp;
        this.distinguish = distinguish;
        this.max_stage = max_stage;
    }

    public void run(){

        Pirates new_pirates = new Pirates();
        if(distinguish == 1) {
            new_pirates = new_pirates.make_pirates_adventure(stage);
        } else if(distinguish == 2){
            new_pirates = new_pirates.make_pirates(max_stage);
        }

        while(!stop){
                Character.player.attacked(new_pirates.attack());
                System.out.println(new_pirates.name + "이 " + Character.player.get_char_name() + "을 공격합니다.");
                if (Character.player.hp <= 0) {
                    Character.player.hp = 0;
                    stop = true;
                    //Character_life = false;
                }
                System.out.println(Character.player.name + "은 " + new_pirates.name + "의 공격을 받아 Hp가 " + (new_pirates.power * new_pirates.crew - Character.player.avoiding) + "만큼 감소하여 현재 체력은 " + Character.player.hp + "/" + vsl_org_hp + "입니다.\n");

                try{
                  Thread.sleep(3000);
                }catch (Exception e){
                    e.getMessage();
                }

        }
    }
}
