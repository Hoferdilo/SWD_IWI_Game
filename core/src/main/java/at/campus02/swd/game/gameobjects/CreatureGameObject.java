package at.campus02.swd.game.gameobjects;

public interface CreatureGameObject extends GameObject {
    Weapon getWeapon();
    void setWeapon(Weapon weapon);
}
