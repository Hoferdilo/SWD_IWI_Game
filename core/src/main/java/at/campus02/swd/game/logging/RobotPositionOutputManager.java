package at.campus02.swd.game.logging;

public class RobotPositionOutputManager implements  IPositionOutputManager {

    private PositionOutputLine positionLineOutput;
    private PositionOutputCSV positionOutputCSV;
    private final float THRESHOLD = 300;

    public RobotPositionOutputManager(PositionOutputLine lineOutput, PositionOutputCSV positionOutputCSV) {
        this.positionLineOutput = lineOutput;
        this.positionOutputCSV = positionOutputCSV;
    }

    @Override
    public void printPosition(float x, float y) {
        String output = String.format(";%.2f;%.2f",x,y);
        if(x > THRESHOLD) {
            positionOutputCSV.print(output);
        }
        else {
            positionLineOutput.print(output);
        }
    }
}
