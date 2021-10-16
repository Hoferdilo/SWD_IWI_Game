package at.campus02.swd.game.commands;

public class Booster {
    private final float boosterStrength = 10;
    private float boosterDuration = 0;

    public Booster() {
    }

    public void setBooster() {
        boosterDuration = 10;
    }

    public float getBooster() {
        if(boosterDuration-- > 0) {
            return boosterStrength;
        }
        return 0;
    }
}
