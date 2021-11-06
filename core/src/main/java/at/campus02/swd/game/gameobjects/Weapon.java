package at.campus02.swd.game.gameobjects;

public abstract class Weapon {
    protected float range;
    protected GameObject equippedOn;
    protected float damage;

    public Weapon(float range, float damage) {
        this.range = range;
        this.damage = damage;
    }

    public abstract void execute();

    public void setEquippedOn(GameObject equippedOn) {
        this.equippedOn = equippedOn;
    }

    public GameObject getEquippedOn() {
        return equippedOn;
    }
}
