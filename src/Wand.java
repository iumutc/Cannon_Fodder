public class Wand extends Item implements SpecialAction{

    @Override
    public int calculateDamage(Character character) {
        return 0;
    }

    @Override
    public int heal(Character character) {
        return 0;
    }

    @Override
    public int stun(Character character) {
        return 0;
    }


    @Override
    public void specialAction(Character target,Character healer) {

        target.setHP(target.getHP()+healer.activeWeapon.heal(healer));
        System.out.println(target.getClass().toString()+" healed "+healer.activeWeapon.heal(healer)+" HP");
    }
}
