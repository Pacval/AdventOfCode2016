package fr.rochet.days;

import fr.rochet.DayInterface;
import fr.rochet.ExoUtils;

import java.util.ArrayList;
import java.util.List;

public class Day15 implements DayInterface {

    private class Disc {
        int number;
        int positions;
        int currentPosition;

        public Disc(int number, int positions, int currentPosition) {
            this.number = number;
            this.positions = positions;
            this.currentPosition = currentPosition;
        }

        void move() {
            currentPosition = currentPosition + 1 == positions ? 0 : currentPosition + 1;
        }

        boolean ballWillPass() {
            return (currentPosition + number) % positions == 0;
        }
    }

    @Override
    public void part1() throws Exception {
        String[] entries = ExoUtils.getEntries(15);

        List<Disc> discs = new ArrayList<>();
        for (String entry : entries) {
            discs.add(new Disc(
                    Integer.parseInt(entry.split(" ")[1].replace("#", "")),
                    Integer.parseInt(entry.split(" ")[3]),
                    Integer.parseInt(entry.split(" ")[11].replace(".", ""))
            ));
        }

        int count = 0;
        while (discs.stream().anyMatch(x -> !x.ballWillPass())) {
            discs.forEach(Disc::move);
            count++;
        }

        System.out.println("You have to push the button at second " + count);
    }

    @Override
    public void part2() throws Exception {
        String[] entries = ExoUtils.getEntries(15);

        List<Disc> discs = new ArrayList<>();
        for (String entry : entries) {
            discs.add(new Disc(
                    Integer.parseInt(entry.split(" ")[1].replace("#", "")),
                    Integer.parseInt(entry.split(" ")[3]),
                    Integer.parseInt(entry.split(" ")[11].replace(".", ""))
            ));
        }

        // Nouveau disque
        discs.add(new Disc(discs.size() + 1, 11, 0));

        int count = 0;
        while (discs.stream().anyMatch(x -> !x.ballWillPass())) {
            discs.forEach(Disc::move);
            count++;
        }

        System.out.println("With the new disc, you have to push the button at second " + count);
    }
}
