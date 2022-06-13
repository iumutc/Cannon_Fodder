public class Katana extends Sword{

    @Override
    public int getValue() {
        value = 8;
        return value;
    }

    public double getWeight(){
        weight = 2;
        return weight;
    }

    public String getName(){
        name = "Katana";
        return name;
    }

    @Override
    public int calculateDamage(Character character) {
        int damage = (getValue()*character.getStrength())/5;
        return damage;
    }


    public Katana(int valueofKatana, double weightofKatana){
        this.value = valueofKatana;
        this.weight = weightofKatana;
    }

}
