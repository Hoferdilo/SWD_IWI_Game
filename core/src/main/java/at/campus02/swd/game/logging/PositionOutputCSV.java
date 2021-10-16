package at.campus02.swd.game.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class PositionOutputCSV implements IPositionOutput {

    private PrintWriter printWriter;

    public PositionOutputCSV() {
        try{
            FileWriter fw = new FileWriter(new File(System.getProperty("user.dir")+File.separator+"log.csv"));
            printWriter = new PrintWriter(new BufferedWriter(fw));
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void printPosition(float x, float y) {
        if(x > 300) {
            printWriter.println(String.format("%.2f;%.2f;", x,y).toCharArray());
            printWriter.flush();
        }
        else {
            System.out.format("Position ist %.2f/%.2f\n", x,y);
        }
    }
}
