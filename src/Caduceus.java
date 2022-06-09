public class Caduceus extends Wand{

    protected int damage;

    @Override
    public int getValue() {
        value = 7;
        return value;
    }

    public double getWeight(){
        weight = 3;
        return weight;
    }

    public String getName(){
        name = "Caduceus";
        return name;
    }

    @Override
    public int calculateDamage(Character character) {
        damage = (getValue()*character.getIntelligence())/4;
        return damage;
    }

    @Override
    public int heal(Character character) {
        int heal = (getValue()*character.getIntelligence());
        return heal;
    }

    public Caduceus(int valueofCaduceus, double weightofCaduceus){
        this.value = valueofCaduceus;
        this.weight = weightofCaduceus;
    }

    public Caduceus(){}
}
