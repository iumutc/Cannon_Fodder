public class KiteShield extends Shield {

    protected int damage;
    protected int stun;

    @Override
    public int getValue() {
        value = 5;
        return value;
    }

    public double getWeight(){
        weight = 3;
        return weight;
    }

    public String getName(){
        name = "Kite Shield";
        return name;
    }

    @Override
    public int calculateDamage(Character character) {
        damage = (getValue()*character.getVitality())/3;
        return damage;
    }

    @Override
    public int stun(Character character) {
        stun = (getValue()*character.getVitality());
        return stun;
    }

    public KiteShield(int valueofKiteShield,double weightofKiteShield){
        this.value = valueofKiteShield;
        this.weight = weightofKiteShield;
    }
    public KiteShield(){}

}
