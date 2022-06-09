public class RoundShield extends Shield {

    protected int stun;
    protected int damage;

    @Override
    public int getValue() {
        value = 3;
        return value;
    }

    public double getWeight(){
        weight = 2;
        return weight;
    }

    public String getName(){
        name = "Round Shield";
        return name;
    }

    @Override
    public int calculateDamage(Character character) {
        int damage = (getValue()*character.getVitality())/3;
        return damage;
    }

    @Override
    public int stun(Character character) {
        int stun = (getValue()*character.getVitality());
        return stun;
    }

    public RoundShield(int valueofRoundShield,double weightofRoundShield){
        this.value = valueofRoundShield;
        this.weight = weightofRoundShield;
    }

    public RoundShield(){}




}
