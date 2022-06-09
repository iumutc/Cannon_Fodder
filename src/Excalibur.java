public class Excalibur extends Sword {
    protected int value;
    protected double weight;
    protected int damage;
    protected int inactivity;


    @Override
    public int getValue() {
        value = 10;
        return value;
    }

    public String getName(){
        name = "Excalibur";
        return name;
    }

    public double getWeight(){
        weight = 3;
        return weight;
    }

    @Override
    public int calculateDamage(Character character) {
        damage = (getValue()*character.getStrength())/5;
        return damage;
    }

    @Override
    public int inactivity(Character character) {
        inactivity = (getValue()*character.getStrength());
        return inactivity;
    }

    public Excalibur(int valueofExcalibur,double weightofExcalibur){
        this.value = valueofExcalibur;
        this.weight = weightofExcalibur;
    }

    public Excalibur(){}
}
