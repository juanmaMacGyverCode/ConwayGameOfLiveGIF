import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainGif {

    // Si no tienes la carpeta temp, creala.
    public static final String PATH_IMAGE_GIF = "C:\\temp\\ConwayX.gif";

    public static final int INITIAL_NUMBER_CELLS = 1000;
    public static final int X_SIZE = 50;
    public static final int Y_SIZE = 50;
    public static final int FACTOR_SIZE = 10;
    public static final int DELAY_GIF = 50; // miliseconds
    public static final int NUMBER_OF_ITERATIONS = 500;
    public static final int[] SURVIVAL_RULES = {2,3};
    public static final int[] REBORN_RULES = {3};

    public static void main(String[] args) throws Exception {

        World world = new World();

        for (int i = 0; i < INITIAL_NUMBER_CELLS; i++) {
            int posX = (int) Math.floor(Math.random()*X_SIZE);
            int posY = (int) Math.floor(Math.random()*X_SIZE);
            world.set_living_at(new Location(posX,posY));
        }

        BufferedImage first = GifSequenceWriter.map(X_SIZE*FACTOR_SIZE, Y_SIZE*FACTOR_SIZE, world );
        ImageOutputStream output = new FileImageOutputStream(new File(PATH_IMAGE_GIF));

        GifSequenceWriter writer = new GifSequenceWriter(output, first.getType(), DELAY_GIF, true);
        writer.writeToSequence(first);

        for (int i = 1; i < NUMBER_OF_ITERATIONS; i++) {
            world.next_turn();
            BufferedImage imgIteration = GifSequenceWriter.map(X_SIZE*FACTOR_SIZE, Y_SIZE*FACTOR_SIZE, world );
            writer.writeToSequence(imgIteration);
            System.out.println(i + 1);
        }

        writer.close();
        output.close();
    }

}
