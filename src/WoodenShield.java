public class WoodenShield extends Shield {

    protected int damage;
    protected int stun;

    @Override
    public int getValue() {
        value = 2;
        return value;
    }

    public double getWeight(){
        weight = 1;
        return weight;
    }

    public String getName(){
        name = "Wooden Shield";
        return name;
    }

    @Override
    public int calculateDamage(Character character) {
        damage = (getValue()* character.getVitality())/3;
        return damage;
    }

    //Stun is the number of enemies to stun!
    @Override
    public int stun(Character character) {
        stun = (getValue()*character.getVitality());
        return stun;
    }

    public WoodenShield(int valueofWoodenShield,double weightofWoodenShield){
        this.value = valueofWoodenShield;
        this.weight = weightofWoodenShield;
    }
    public WoodenShield(){}
}
