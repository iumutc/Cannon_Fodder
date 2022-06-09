public class Armor extends Item{


    @Override
    public int getValue(){
        return value;
    }
    public double getWeight(){
        return weight;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public Armor(String armorName, int armorValue, double armorWeight){
        this.name=armorName;
        this.value=armorValue;
        this.weight=armorWeight;
    }

    @Override
    public int calculateDamage(Character character) {
        return 0;
    }

    @Override
    public int heal(Character character) {
        return 0;
    }

    @Override
    public int inactivity(Character character) {
        return 0;
    }

    @Override
    public int stun(Character character) {
        return 0;
    }
}
