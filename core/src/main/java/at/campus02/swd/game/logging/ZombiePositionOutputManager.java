package at.campus02.swd.game.logging;

public class ZombiePositionOutputManager implements IPositionOutputManager {
    private PositionOutputCSV positionOutputCSV;
    private String sponsor = "";

    public ZombiePositionOutputManager(PositionOutputCSV positionOutputCSV, String sponsor) {
        this.positionOutputCSV = positionOutputCSV;
        this.sponsor = sponsor;
    }

    @Override
    public void printPosition(float x, float y) {
        String output = String.format("%s;%.2f;%.2f;",sponsor,x,y);
        positionOutputCSV.print(output);
    }
}
