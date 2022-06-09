public class GoldenWand extends Wand{

    protected String name;
    protected int damage;

    @Override
    public int getValue() {
        value = 5;
        return value;
    }

    public double getWeight(){
        weight = 2;
        return weight;
    }

    public String getName(){
        name = "GoldenWand";
        return name;
    }

    @Override
    public int calculateDamage(Character character) {
        damage = (getValue()*character.getIntelligence())/4;
        return damage;
    }

    @Override
    public int heal(Character character) {
        int heal = (getValue()*character.getIntelligence());
        return heal;
    }

    public GoldenWand(int valueofGoldenWand, double weightofGoldenWand){
        this.value = valueofGoldenWand;
        this.weight = weightofGoldenWand;
    }
    public GoldenWand(){}
}

