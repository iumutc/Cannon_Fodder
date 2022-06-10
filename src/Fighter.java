public class Fighter extends Character {










    public Fighter(int s, int v, int i, boolean isAlive,int HP) {
        super(s, v, i, isAlive,HP);
    }


  //start of inventory related codes
    public void calculateInventorySpace(){
        super.calculateInventorySpace();
    }
    public void displayInventory(){
        super.displayInventory();
    }
    public void pickItem(){
        super.pickItem();
    }
    public void selectItem(){
        super.selectItem();
    }
    public void examineItem(){
        super.examineItem();
    }

    public void discardItem(){
        super.discardItem();
    }

}
