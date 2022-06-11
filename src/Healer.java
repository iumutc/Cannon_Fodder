import java.util.ArrayList;

public class Healer extends Character{



    public Healer(int s, int v, int i, boolean isAlive,int HP, boolean isStunned) {
        super(s, v, i, isAlive, HP, isStunned);

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
