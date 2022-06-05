public class Wand extends Item{



    protected final double weightofWoodenWand = 0.1;
    protected final double weightofGoldenWand = 0.2;
    protected final double weightofCaduceus = 0.3;

    protected final int valueofWoodenWand = 2;
    protected final int valueofGoldenWand = 3;
    protected final int valueofCaduceus = 5;

    public int calculateDamageofWoodenWand(Character character){
        int damage = (valueofWoodenWand*character.getIntelligence())/4;
        return damage;
    }

    public int calculateDamageofGoldenWand(Character character){
        int damage = (valueofGoldenWand*character.getIntelligence())/4;
        return damage;
    }

    public int calculateDamageofCaduceus(Character character){
        int damage = (valueofCaduceus*character.getIntelligence())/4;
        return damage;
    }

    public int healofWoodenWand(Character character){
        int heal = (valueofWoodenWand*character.getIntelligence());
        return heal;
    }

    public int healofGoldenWand(Character character){
        int heal = (valueofGoldenWand*character.getIntelligence());
        return heal;
    }

    public int healofCaduceus(Character character){
        int heal = (valueofCaduceus*character.getIntelligence());
        return heal;
    }

    @Override
    public int calculateDamage(Character character) {
        return 0;
    }
}
