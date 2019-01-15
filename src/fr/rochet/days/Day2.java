package fr.rochet.days;

import fr.rochet.DayInterface;
import fr.rochet.ExoUtils;

import java.io.IOException;

public class Day2 implements DayInterface {

    @Override
    public void part1() throws IOException {
        String[] entries = ExoUtils.getEntries(2);

        int line = 1, col = 1;
        StringBuilder code = new StringBuilder();

        for (String combinaison : entries) {
            char[] moves = combinaison.toCharArray();
            for (char c : moves) {
                switch (Character.toUpperCase(c)) {
                    case 'U':
                        line = line == 0 ? line : line - 1;
                        break;
                    case 'D':
                        line = line == 2 ? line : line + 1;
                        break;
                    case 'R':
                        col = col == 2 ? col : col + 1;
                        break;
                    case 'L':
                        col = col == 0 ? col : col - 1;
                        break;
                }
            }
            // Pas besoin de stocker le pad on peut retrouver le numéro facilement
            code.append(line * 3 + col + 1);
        }
        System.out.println("The oode is : " + code.toString());
    }

    @Override
    public void part2() throws IOException {
        String[] entries = ExoUtils.getEntries(2);

        char[][] pad = new char[][]{
                {'#','#','#','#','#','#','#'},
                {'#','#','#','1','#','#','#'},
                {'#','#','2','3','4','#','#'},
                {'#','5','6','7','8','9','#'},
                {'#','#','A','B','C','#','#'},
                {'#','#','#','D','#','#','#'},
                {'#','#','#','#','#','#','#'}
        };
        int line = 3, col = 1;
        StringBuilder code = new StringBuilder();

        for (String combinaison : entries) {
            char[] moves = combinaison.toCharArray();
            for (char c : moves) {
                switch (Character.toUpperCase(c)) {
                    case 'U':
                        if(pad[line - 1][col] != '#') {
                            line--;
                        }
                        break;
                    case 'D':
                        if(pad[line + 1][col] != '#') {
                            line++;
                        }
                        break;
                    case 'R':
                        if(pad[line][col + 1] != '#') {
                            col++;
                        }
                        break;
                    case 'L':
                        if(pad[line][col - 1] != '#') {
                            col--;
                        }
                        break;
                }
            }
            code.append(pad[line][col]);
        }

        System.out.println("Correct code is " + code.toString());
    }
}
