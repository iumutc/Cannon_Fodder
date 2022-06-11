import java.util.ArrayList;

public class Tank extends Character{



    public Tank(int s, int v, int i, boolean isAlive,int HP, boolean isStunned) {
        super(s, v, i, isAlive,HP, isStunned);
    }



    @Override
    public int getVitality() {
        return vitality;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }


    //start of inventory related codes

}
