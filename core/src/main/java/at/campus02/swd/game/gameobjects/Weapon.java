package at.campus02.swd.game.gameobjects;

public abstract class Weapon {
    protected GameObject equippedOn;
    protected float damage;

    public Weapon(float damage) {
        this.damage = damage;
    }

    public abstract void execute(float delta);

    public void setEquippedOn(GameObject equippedOn) {
        this.equippedOn = equippedOn;
    }

    public GameObject getEquippedOn() {
        return equippedOn;
    }
}
