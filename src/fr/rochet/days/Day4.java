package fr.rochet.days;

import fr.rochet.DayInterface;
import fr.rochet.ExoUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day4 implements DayInterface {

    private class Room {
        String name;
        int sectorId;
        String checksum;

        Room(String name, int sectorId, String checksum) {
            this.name = name;
            this.sectorId = sectorId;
            this.checksum = checksum;
        }
    }

    class ValueComparator implements Comparator<String> {

        Map<String, Long> base;

        public ValueComparator(Map<String, Long> base) {
            this.base = base;
        }

        public int compare(String a, String b) {
            if (base.get(a) > base.get(b)) {
                return -1;
            } else if (base.get(a) < base.get(b)) {
                return 1;
            } else {
                return a.compareTo(b);
            }
        }
    }

    @Override
    public void part1() throws IOException {
        String[] entries = ExoUtils.getEntries(4);

        int sumOfSetorId = 0;

        Pattern pattern = Pattern.compile("^([\\D]+)([\\d]+)\\[(\\w+)]");

        for (String strRoom : entries) {
            Matcher matcher = pattern.matcher(strRoom);
            if (matcher.matches()) {

                String name = matcher.group(1);
                // Méthode pas forcément très performante sur des très grande listes mais rigolote à mettre en place
                String occ = StringUtils.join(
                        Arrays.stream(name.replace("-", "").split(""))  // on récupère tous les caractères de la chaine sauf les "-"
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // on les regroupe, on les compte, et on les stocke dans une map
                                .entrySet().stream() // on stream cette map
                                .sorted(Collections.reverseOrder(Map.Entry.<String, Long>comparingByValue()) // pour l'ordonner par value (occurence)
                                        .thenComparing(Map.Entry.comparingByKey())) // puis par key (lettre)
                                .map(Map.Entry::getKey) // on récupère les keys
                                .collect(Collectors.toList()), "")
                        .substring(0, 5); // que l'on joint dans une string, et qu'on coupe à 5 caractères.

                if (occ.equals(matcher.group(3))){
                    sumOfSetorId += Integer.parseInt(matcher.group(2));
                }
            }
        }

        System.out.println("The sum of the sector IDs of the real rooms is : " + sumOfSetorId);
    }

    @Override
    public void part2() throws IOException {
        String[] entries = ExoUtils.getEntries(4);

        Pattern pattern = Pattern.compile("^([\\D]+)([\\d]+)\\[(\\w+)]");

        for (String strRoom : entries) {

            Matcher matcher = pattern.matcher(strRoom);
            if (matcher.matches()) {

                String name = matcher.group(1);
                int sectorId = Integer.parseInt(matcher.group(2));

                char[] decomposedName = name.toCharArray();
                for (int i = 0; i < sectorId; i++) {
                    for (int c = 0; c < decomposedName.length; c++) {
                        if (decomposedName[c] == '-') {
                            decomposedName[c] = ' ';
                        } else if (decomposedName[c] == ' '){
                            decomposedName[c] = '-';
                        } else if (decomposedName[c] == 'z'){
                            decomposedName[c] = 'a';
                        } else {
                            decomposedName[c] = (char) (decomposedName[c] + 1);
                        }
                    }
                }

                if (String.valueOf(decomposedName).equals("northpole object storage ")) {
                    System.out.println("The sector ID of the room where North Pole objects are stored is " + sectorId);
                }
            }
        }
    }
}
