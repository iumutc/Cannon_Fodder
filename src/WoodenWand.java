public class WoodenWand extends Wand{
    @Override
    public int getValue(){
        value=2;
        return value;
    }
    public double getWeight(){
        weight = 1;
        return weight;
    }
    public String getName(){
        name="Wooden Wand";
        return name;
    }

    public int calculateDamage(Character character){
        int damage = (getValue()* character.getStrength())/2;
        return damage;
    }

    @Override
    public int heal(Character character) {
        int heal = (getValue()*character.getIntelligence());
        return heal;
    }

    public WoodenWand(int woodenWandValue, double woodenWandWeight){
        this.value=woodenWandValue;
        this.weight=woodenWandWeight;
    }
    public WoodenWand(){}
}
