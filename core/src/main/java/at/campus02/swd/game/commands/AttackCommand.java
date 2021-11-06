package at.campus02.swd.game.commands;

import at.campus02.swd.game.gameobjects.Ninja;

public class AttackCommand implements PlayerCommand {
    private Ninja player;

    public AttackCommand(Ninja player) {
        this.player = player;
    }

    @Override
    public void execute() {
        if(this.player.getWeapon() != null) {
            this.player.getWeapon().execute();
        }
    }
}
