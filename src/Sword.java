public abstract class Sword extends Item{



    protected final double weightofKatana = 2;
    protected double weightofExcalibur = 3;


    protected int valueofKatana = 8;
    protected final int valueofExcalibur = 10;




    public int calculateDamageofKatana(Character character){
        int damage = (valueofKatana*character.getStrength())/5;
        return damage;
    }
    public int calculateDamageofExcalibur(Character character){
        int damage = (valueofExcalibur*character.getStrength())/5;
        return damage;
    }

    //Inactivity is the number of enemies to keep away from fighter!


    public int inactivityofKatana(Character character){
        int inactivity = (valueofKatana*character.getStrength());
        return inactivity;
    }

    public int inactivityofExcalibur(Character character){
        int inactivity = (valueofExcalibur*character.getStrength());
        return inactivity;
    }

}
