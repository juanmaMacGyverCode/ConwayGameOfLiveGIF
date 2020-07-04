import java.awt.image.BufferedImage;

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

        //String pathToSaveImage = "C:\\Tu\\direccion.bmp";

        BufferedImage img = GifSequenceWriter.map( 1000, 1000, world );
        GifSequenceWriter.savePNG( img, "C:\\Users\\Juanma\\Pictures\\Test\\test0.bmp" );

        for (int i = 1; i < 200; i++) {
            world.next_turn();
            BufferedImage imgIteration = GifSequenceWriter.map( 1000, 1000, world );
            GifSequenceWriter.savePNG( imgIteration, "C:\\Users\\Juanma\\Pictures\\Test\\test" + i + ".bmp" );
        }
    }



}