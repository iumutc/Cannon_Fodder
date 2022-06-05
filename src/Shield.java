public class Shield extends Item{



    protected final double weightofWoodenShield = 1;
    protected final double weightofRoundShield = 2;
    protected final double weightofKiteShield = 3;

    protected final int valueofWoodenShield = 2;
    protected final int valueofRoundShield = 3;
    protected final int valueofKiteShield = 5;

/*    public int calculateDamageofWoodenShield(){
        int damage = (valueofWoodenShield*myVitality)/3;
        return damage;
    }*/

    public int calculateDamageofRoundShield(Character character){
        int damage = (valueofRoundShield*character.getVitality())/3;
        return damage;
    }

    public int calculateDamageofKiteShield(Character character){
        int damage = (valueofKiteShield*character.getVitality())/3;
        return damage;
    }

    //Stun is the number of enemies to stun!
    public int stunofWoodenShield(Character character){
        int stun = (valueofWoodenShield*character.getVitality());
        return stun;
    }

    public int stunofRoundShield(Character character){
        int stun = (valueofRoundShield*character.getVitality());
        return stun;
    }

    public int stunofKiteShield(Character character){
        int stun = (valueofKiteShield*character.getVitality());
        return stun;
    }

    @Override
    public int calculateDamage(Character character) {
        return 0;
    }
}
