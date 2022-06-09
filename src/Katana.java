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

    //Inactivity is the number of enemies to keep away from fighter!
    @Override
    public int inactivity(Character character) {
        int inactivity = (getValue()*character.getStrength());
        return inactivity;
    }

    public Katana(int valueofKatana, double weightofKatana){
        this.value = valueofKatana;
        this.weight = weightofKatana;
    }
    public Katana(){}
}
