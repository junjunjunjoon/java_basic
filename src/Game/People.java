package Game;

class People extends Unit{

    int power = 1;
    int crew;
    int avoiding = 0;
    int money;

    public void attacked(int sum) {
        hp = hp - sum;
    }
}
