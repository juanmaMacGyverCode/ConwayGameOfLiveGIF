
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainGif {

    public static void main(String[] args) throws Exception {

        World world = new World();

        // Random
        for (int i = 0; i < 1500; i++) {
            int posX = (int) Math.floor(Math.random()*(99+1));
            int posY = (int) Math.floor(Math.random()*(99+1));
            world.set_living_at(new Location(posX,posY));
        }

        //BufferedImage img = GifSequenceWriter.map( 1000, 1000, world );
        //GifSequenceWriter.savePNG( img, "C:\\Users\\Juanma\\Pictures\\Test\\test0.bmp" );

        // Crear primera imagen y definir la salida.
        BufferedImage first = GifSequenceWriter.map( 1000, 1000, world );
        ImageOutputStream output = new FileImageOutputStream(new File("C:\\Users\\Juanma\\Pictures\\Test\\ConwayX.gif"));

        // Definir como se secuenciará cada imagen y insertar la primera imagen
        GifSequenceWriter writer = new GifSequenceWriter(output, first.getType(), 66, true);
        //writer.writeToSequence(first);

        for (int i = 1; i < 200; i++) {
            world.next_turn();
            BufferedImage imgIteration = GifSequenceWriter.map( 1000, 1000, world );
            writer.writeToSequence(imgIteration);
            System.out.println(i);
            //GifSequenceWriter.savePNG( imgIteration, "C:\\Users\\Juanma\\Pictures\\Test\\test" + i + ".bmp" );
        }


        /////////////////////

        // Crear primera imagen y definir la salida.
        //BufferedImage first = ImageIO.read(new File("C:\\Users\\Juanma\\Pictures\\Test\\test0.bmp"));
        //ImageOutputStream output = new FileImageOutputStream(new File("C:\\Users\\Juanma\\Pictures\\Test\\ConwayX.gif"));

        // Definir como se secuenciará cada imagen y insertar la primera imagen
        //GifSequenceWriter writer = new GifSequenceWriter(output, first.getType(), 250, true);
        //writer.writeToSequence(first);

        // Cargar todas las imagenes como FILES (yo lo quiero en BufferedImage)
        /*File[] images = new File[]{
                new File("C:\\Users\\Juanma\\Pictures\\Test\\test1.bmp"),
                new File("C:\\Users\\Juanma\\Pictures\\Test\\test2.bmp"),
                new File("C:\\Users\\Juanma\\Pictures\\Test\\test3.bmp"),
        };*/

        // Crear todos los BufferedImage y insertarlos en el GIF
        /*for (File image : images) {
            BufferedImage next = ImageIO.read(image);
            writer.writeToSequence(next);
        }*/

        // Cerrar
        writer.close();
        output.close();
    }

}
