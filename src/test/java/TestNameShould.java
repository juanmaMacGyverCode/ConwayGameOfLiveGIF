import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestNameShould {
    @Test
    public void test_a_new_world_is_empty(){
        World world = new World();
        // Funciona bien pero el nombre no es simetrico al nombre del test o cambio nombre test o cambio el código
        //assertThat(world.living_Cells().size()).isEqualTo(0);
        assertThat(world.empty()).isTrue();
    }

    @Test
    public void test_a_cell_can_be_added_to_the_world(){
        World world = new World();
        Location location = new Location(1,1);
        world.set_living_at(location);
        // Esto no es simetrico al nombre del test
        //assertThat(world.living_Cells().size()).isEqualTo(1);
        assertThat(world.alive_at(location)).isTrue();
    }

    @Test
    public void test_after_adding_a_cell_the_world_is_not_empty(){
        World world = new World();
        Location location = new Location(1,1);
        world.set_living_at(location);
        assertThat(world.empty()).isFalse();
    }

    @Test
    public void count_cells_alive() {
        World world = new World();
        Location location = new Location(5,5);
        world.set_living_at(location);
        Location location2 = new Location(4,5);
        world.set_living_at(location2);
        Location location3 = new Location(6,5);
        world.set_living_at(location3);
        Location location4 = new Location(4,4);
        world.set_living_at(location4);
        assertThat(world.living_Cells().size()).isEqualTo(4);
    }

    @Test
    public void count_neighbours_cells() {
        World world = new World();
        Location location = new Location(5,5);
        world.set_living_at(location);
        Location location2 = new Location(4,5);
        world.set_living_at(location2);
        Location location3 = new Location(6,5);
        world.set_living_at(location3);
        Location location4 = new Location(4,4);
        world.set_living_at(location4);
        assertThat(world.count_neighbours(location)).isEqualTo(3);
    }

    @Test
    public void survived_the_cell_with_zero_neighbours() {
        World world = new World();
        Location location = new Location(5,5);
        world.set_living_at(location);
        assertThat(world.survivedCell(location)).isFalse();
    }

    @Test
    public void survived_the_cell_with_one_neighbours() {
        World world = new World();
        Location targetLocation = new Location(5,5);
        world.set_living_at(new Location(5,5));
        world.set_living_at(new Location(5,4));
        assertThat(world.survivedCell(targetLocation)).isFalse();
    }

    @Test
    public void survived_the_cell_with_two_neighbours() {
        World world = new World();
        Location targetLocation = new Location(5,5);
        world.set_living_at(new Location(5,5));
        world.set_living_at(new Location(5,4));
        world.set_living_at(new Location(5,6));
        assertThat(world.survivedCell(targetLocation)).isTrue();
    }

    @Test
    public void survived_the_cell_with_three_neighbours() {
        World world = new World();
        Location targetLocation = new Location(5,5);
        world.set_living_at(new Location(5,5));
        world.set_living_at(new Location(5,4));
        world.set_living_at(new Location(5,6));
        world.set_living_at(new Location(4,4));
        assertThat(world.survivedCell(targetLocation)).isTrue();
    }

    @Test
    public void survived_the_cell_with_four_neighbours() {
        World world = new World();
        Location targetLocation = new Location(5,5);
        world.set_living_at(new Location(5,5));
        world.set_living_at(new Location(5,4));
        world.set_living_at(new Location(5,6));
        world.set_living_at(new Location(4,4));
        world.set_living_at(new Location(4,5));
        assertThat(world.survivedCell(targetLocation)).isFalse();
    }

    @Test
    public void execute_one_iteration_game_of_life_zero_cells() {
        World world = new World();
        world.next_turn();
        assertThat(world.living_Cells().size()).isEqualTo(0);
    }

    @Test
    public void execute_one_iteration_game_of_life_one_cell_and_die() {
        World world = new World();
        world.set_living_at(new Location(5,5));
        assertThat(world.living_Cells().size()).isEqualTo(1);

        world.next_turn();
        assertThat(world.living_Cells().size()).isEqualTo(0);
    }

    @Test
    public void execute_one_iteration_game_of_life_oscillator_blinker() {
        World world = new World();
        world.set_living_at(new Location(5,4));
        world.set_living_at(new Location(5,5));
        world.set_living_at(new Location(5,6));
        assertThat(world.living_Cells().size()).isEqualTo(3);

        world.next_turn();
        assertThat(world.living_Cells().size()).isEqualTo(3);

        assertThat(world.alive_at(new Location(4,5))).isTrue();
        assertThat(world.alive_at(new Location(5,5))).isTrue();
        assertThat(world.alive_at(new Location(6,5))).isTrue();

    }

    @Test
    public void execute_one_iteration_game_of_life_still_life_block() {
        World world = new World();
        world.set_living_at(new Location(4,4));
        world.set_living_at(new Location(4,5));
        world.set_living_at(new Location(5,4));
        world.set_living_at(new Location(5,5));
        assertThat(world.living_Cells().size()).isEqualTo(4);

        world.next_turn();
        assertThat(world.living_Cells().size()).isEqualTo(4);

        assertThat(world.alive_at(new Location(4,4))).isTrue();
        assertThat(world.alive_at(new Location(4,5))).isTrue();
        assertThat(world.alive_at(new Location(5,4))).isTrue();
        assertThat(world.alive_at(new Location(5,5))).isTrue();

    }
}
