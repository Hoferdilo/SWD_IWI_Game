package at.campus02.swd.game.logging;

import com.badlogic.gdx.math.Interpolation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class PositionOutputCSV {

    private PrintWriter printWriter;

    public PositionOutputCSV(String filename) {
        try{
            FileWriter fw = new FileWriter(System.getProperty("user.dir")+File.separator+filename+".csv");
            printWriter = new PrintWriter(new BufferedWriter(fw));
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void print(String output) {
        printWriter.println(output);
        System.out.println(output);
        printWriter.flush();
    }
}
