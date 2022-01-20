package Game;

public class Vessel extends Unit {

    int min_crew, max_crew = 0;
    int container;
    int price;
    int secret_cntr;

    public Vessel(String name, int hp, int min_crew, int max_crew, int container, int price, int secret_cntr){
        this.name = name;
        this.hp = hp;
        this.min_crew = min_crew;
        this.max_crew = max_crew;
        this.container = container;
        this.price = price;
        this.secret_cntr = secret_cntr;
    }
}

