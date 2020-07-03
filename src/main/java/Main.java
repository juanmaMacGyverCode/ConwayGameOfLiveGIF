import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;

public class Main {

    public static void main( final String[] args ) {
        World world = new World();
        /*world.set_living_at(new Location(0,1));
        world.set_living_at(new Location(1,2));
        world.set_living_at(new Location(2,0));
        world.set_living_at(new Location(2,1));
        world.set_living_at(new Location(2,2));*/

        /*world.set_living_at(new Location(50, 50));
        world.set_living_at(new Location(50, 52));*/
        /*world.set_living_at(new Location(52, 50));
        world.set_living_at(new Location(52, 52));*/

        /*world.set_living_at(new Location(20,15));
        world.set_living_at(new Location(20,16));
        world.set_living_at(new Location(20,17));
        world.set_living_at(new Location(20,18));
        world.set_living_at(new Location(20,19));
        world.set_living_at(new Location(20,20));
        world.set_living_at(new Location(20,21));
        world.set_living_at(new Location(20,22));
        world.set_living_at(new Location(20,23));
        world.set_living_at(new Location(20,24));*/

        for (int i = 0; i < 1500; i++) {
            int posX = (int) Math.floor(Math.random()*(99+1));
            int posY = (int) Math.floor(Math.random()*(99+1));
            world.set_living_at(new Location(posX,posY));
        }

        String pathToSaveImage = "C:\\Tu\\direccion.bmp";

        BufferedImage img = map( 1000, 1000, world );
        savePNG( img, pathToSaveImage );

        for (int i = 1; i < 200; i++) {
            world.next_turn();
            BufferedImage imgIteration = map( 1000, 1000, world );
            savePNG( imgIteration, "C:\\Users\\Juanma\\Pictures\\Test\\test" + i + ".bmp" );
        }
    }

    private static BufferedImage map( int sizeX, int sizeY, World world){
        final BufferedImage res = new BufferedImage( sizeX, sizeY, BufferedImage.TYPE_INT_RGB );

        for (int x = 0; x < sizeX; x++){
            for (int y = 0; y < sizeY; y++){
                res.setRGB(x, y, Color.WHITE.getRGB() );
            }
        }

        // AQUI LOGICOA
        LinkedList<LivingCell> aliveCells = world.living_Cells();

        for (LivingCell cell: aliveCells) {
            for (int x = cell.location.getPosX()*10; x < cell.location.getPosX()*10 + 10; x++){
                for (int y = cell.location.getPosY()*10; y < cell.location.getPosY()*10 + 10; y++){
                    res.setRGB(x, y, Color.BLACK.getRGB() );
                }
            }
        }
        // FIN

        return res;
    }

    private static void savePNG( final BufferedImage bi, final String path ){
        try {
            RenderedImage rendImage = bi;
            ImageIO.write(rendImage, "bmp", new File(path));

        } catch ( IOException e) {
            e.printStackTrace();
        }
    }

}

/*

package com.example.main.Services;

        import org.w3c.dom.Node;

        import javax.imageio.*;
        import javax.imageio.metadata.IIOInvalidTreeException;
        import javax.imageio.metadata.IIOMetadata;
        import javax.imageio.metadata.IIOMetadataNode;
        import javax.imageio.stream.ImageOutputStream;
        import java.awt.image.BufferedImage;
        import java.io.File;

public class animationGif {
    public static void configure(IIOMetadata meta,
                                 String delayTime,
                                 int imageIndex) {

        String metaFormat = meta.getNativeMetadataFormatName();

        if (!"javax_imageio_gif_image_1.0".equals(metaFormat)) {
            throw new IllegalArgumentException(
                    "Unfamiliar gif metadata format: " + metaFormat);
        }

        Node root = meta.getAsTree(metaFormat);

        //find the GraphicControlExtension node
        Node child = root.getFirstChild();
        while (child != null) {
            if ("GraphicControlExtension".equals(child.getNodeName())) {


*/