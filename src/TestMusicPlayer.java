import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;


public class TestMusicPlayer extends Thread {

    public Player player;
    public boolean isLoop;
    public File file;
    public FileInputStream fis;
    public BufferedInputStream bis;

    public TestMusicPlayer(String name, boolean isLoop) {
        try {
            this.isLoop = isLoop;
            file = new File(Test.class.getResource("./music/" + name).toURI());
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    public int getTime() {
//        if (player == null)
//            return 0;
//        return player.getPosition();
//    }

    public void close() {
        isLoop = false;
        player.close();
        this.interrupt();
    }

    @Override
    public void run() {
        try {
            do {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                player = new Player(bis);
                player.play();
            } while (isLoop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

