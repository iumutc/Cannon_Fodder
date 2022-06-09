public class ShortBlade extends Sword {

    protected int damage;
    protected int inactivity;

    @Override
    public int getValue(){
        value=5;
        return value;
    }
    public double getWeight(){
        weight = 1;
        return weight;
    }
    public String getName(){
        name="Short Blade";
        return name;
    }

    public int calculateDamage(Character character){
        damage = (getValue()* character.getStrength())/2;
        return damage;
    }

    @Override
    public int inactivity(Character character) {
        inactivity = (getValue() * character.getStrength());
        return inactivity;
    }

    public ShortBlade(int valueofShortBlade,double weightofShortBlade){
        this.value=valueofShortBlade;
        this.weight=weightofShortBlade;
    }
    public ShortBlade(){}
}
