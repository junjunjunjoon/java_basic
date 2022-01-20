public class TestPiratesAttack extends Thread{

    int characterHp, piratesHp;

//    public TestPiratesAttack(int cHp, int pHp){
//        characterHp = cHp;
//        piratesHp = pHp;
//
//    }


    @Override
    public void run() {
        TestPirates a = new TestPirates();
        a.hp = 100;
        a.name = "해적";
        while(true) {
            try{
                TestCharacter.attacked(a.attack());
                Thread.sleep(1000);
            } catch (Exception e){
                e.getMessage();
             }

        }
    }
}
