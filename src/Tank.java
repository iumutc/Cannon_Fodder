import java.util.ArrayList;

public class Tank extends Character{

    private int tankHP;

    public Tank(int s, int v, int i, boolean isAlive,int tankHP) {
        super(s, v, i, isAlive);
        this.tankHP=tankHP;
    }

    public int getTankHP(){
        return tankHP;
    }

    public void setTankHP(int tankHP) {
        this.tankHP = tankHP;
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
