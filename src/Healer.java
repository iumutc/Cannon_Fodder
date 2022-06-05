import java.util.ArrayList;

public class Healer extends Character{

    private int healerHP;

    public Healer(int s, int v, int i, boolean isAlive,int healerHP) {
        super(s, v, i, isAlive);
        this.healerHP=healerHP;
    }


    public int getHealerHP(){
        return healerHP;
    }
    public void setHealerHP(int healerHP) {
        this.healerHP = healerHP;
    }


    @Override
    public int getIntelligence() {
        return intelligence;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }


    //start of inventory related codes

}
