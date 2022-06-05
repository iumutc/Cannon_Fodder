public class ShortBlade extends Sword {

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
        int damage = (getValue()* character.getStrength())/2;
        return damage;
    }

    public int inactivityofShortBlade(Character character) {
        int inactivity = (value * character.getStrength());
        return inactivity;
    }
    public ShortBlade(int valueofShortBlade,double weightofShortBlade){
        this.value=valueofShortBlade;
        this.weight=weightofShortBlade;
    }
}
