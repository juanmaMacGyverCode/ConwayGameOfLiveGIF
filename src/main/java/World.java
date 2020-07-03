import java.util.LinkedList;

public class World {
    LivingCell matriz[][] = new LivingCell[100][100];

    public World() {
    }

    public LinkedList<LivingCell> living_Cells() {
        LinkedList<LivingCell> livingCellsList = new LinkedList<LivingCell>();

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (this.matriz[i][j] != null) {
                    Location location = new Location(i, j);
                    livingCellsList.add(new LivingCell(location));
                }
            }
        }
        return livingCellsList;
    }

    public void set_living_at(Location location) {
        LivingCell livingCell = new LivingCell(location);
        this.matriz[location.getPosX()][location.getPosY()] = livingCell;
    }

    public boolean empty() {
        int cellsAlive = living_Cells().size();
        if (cellsAlive == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean alive_at(Location location) {
        return this.matriz[location.getPosX()][location.getPosY()] instanceof LivingCell;
    }

    public int count_neighbours(Location location) {

        int amountNeighbours = 0;
        for (int i = location.getPosX() - 1; i <= location.getPosX() + 1; i++) {
            for (int j = location.getPosY() - 1; j <= location.getPosY() + 1; j++) {
                if (i < 0 || j < 0 || i >= 100 || j >= 100) {
                    continue;
                }
                if (this.matriz[i][j] != null) {
                    if (i != location.getPosX() || j != location.getPosY()) {
                        amountNeighbours++;
                    }
                }
            }
        }

        return amountNeighbours;
    }

    public boolean survivedCell(Location location) {
        int amountNeighbours = count_neighbours(location);
        // REGLAS
        if (amountNeighbours == 2 || amountNeighbours == 3) {
            return true;
        }
        return false;
    }

    public LivingCell find_target_cell(int posX, int posY) {
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[i].length; j++) {
                if (this.matriz[i][j] == null) {
                    continue;
                }
                if (this.matriz[i][j].getLocation().getPosX() == posX
                        && this.matriz[i][j].getLocation().getPosY() == posY) {
                    return this.matriz[i][j];
                }
            }
        }

        return null;
    }

    public void next_turn() {
        LivingCell newMatriz[][] = new LivingCell[100][100];
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz[i].length; j++) {
                Location locationTarget = new Location(i, j);

                // Si sobrevive la celula
                if (survivedCell(locationTarget)) {
                    if (alive_at(locationTarget) && count_neighbours(locationTarget) == 3) {
                        newMatriz[i][j] = new LivingCell(locationTarget);
                    }
                    if (alive_at(locationTarget) && count_neighbours(locationTarget) == 2) {
                        newMatriz[i][j] = new LivingCell(locationTarget);
                    }
                }

                // Creacion de nuevas celulas
                if (count_neighbours(locationTarget) == 3) {
                    newMatriz[i][j] = new LivingCell(locationTarget);
                }

            }
        }
        this.matriz = newMatriz;
    }
}


