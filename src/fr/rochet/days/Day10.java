package fr.rochet.days;

import fr.rochet.DayInterface;
import fr.rochet.ExoUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Day10 implements DayInterface {

    private class Bot {
        private List<Integer> values;
        private boolean isBotLow;
        private int low;
        private boolean isBotHigh;
        private int high;

        Bot() {
            values = new ArrayList<>();
        }
    }

    @Override
    public void part1() throws Exception {
        String[] entries = ExoUtils.getEntries(10);

        int searchedValue1 = 61, searchedValue2 = 17;

        Map<Integer, Bot> bots = new HashMap<>();

        // initialisation
        for (String inst : entries) {
            if (inst.split(" ")[0].equals("value")) {
                int nbBot = Integer.parseInt(inst.split(" ")[5]);
                if (!bots.containsKey(nbBot)) {
                    bots.put(nbBot, new Bot());
                }
                bots.get(nbBot).values.add(Integer.parseInt(inst.split(" ")[1]));

            } else if (inst.split(" ")[0].equals("bot")) {
                int nbBot = Integer.parseInt(inst.split(" ")[1]);
                if (!bots.containsKey(nbBot)) {
                    bots.put(nbBot, new Bot());
                }
                bots.get(nbBot).isBotLow = inst.split(" ")[5].equals("bot");
                bots.get(nbBot).low = Integer.parseInt(inst.split(" ")[6]);
                bots.get(nbBot).isBotHigh = inst.split(" ")[10].equals("bot");
                bots.get(nbBot).high = Integer.parseInt(inst.split(" ")[11]);
            }
        }

        // Process
        while (bots.values().stream()
                .noneMatch(bot -> bot.values.size() == 2 && bot.values.contains(searchedValue1) && bot.values.contains(searchedValue2))) {

            List<Integer> botsWith2items = bots.entrySet().stream()
                    .filter(bot -> bot.getValue().values.size() == 2).map(Map.Entry::getKey).collect(Collectors.toList());

            for (int nbBot : botsWith2items) {
                Bot bot = bots.get(nbBot);
                int low = bot.values.stream().mapToInt(e -> e).min().getAsInt();
                int high = bot.values.stream().mapToInt(e -> e).max().getAsInt();

                if (bot.isBotLow) {
                    bots.get(bot.low).values.add(low);
                }
                if (bot.isBotHigh) {
                    bots.get(bot.high).values.add(high);
                }

                bot.values.clear();
            }
        }

        int matchingBot = bots.entrySet().stream()
                .filter(bot -> bot.getValue().values.size() == 2 && bot.getValue().values.contains(searchedValue1) && bot.getValue().values.contains(searchedValue2))
                .findFirst()
                .map(Map.Entry::getKey)
                .get();

        System.out.println("The number of the matching bot is : " + matchingBot);
    }


    @Override
    public void part2() throws Exception {
        String[] entries = ExoUtils.getEntries(10);

        Map<Integer, Bot> bots = new HashMap<>();

        // initialisation
        for (String inst : entries) {
            if (inst.split(" ")[0].equals("value")) {
                int nbBot = Integer.parseInt(inst.split(" ")[5]);
                if (!bots.containsKey(nbBot)) {
                    bots.put(nbBot, new Bot());
                }
                bots.get(nbBot).values.add(Integer.parseInt(inst.split(" ")[1]));

            } else if (inst.split(" ")[0].equals("bot")) {
                int nbBot = Integer.parseInt(inst.split(" ")[1]);
                if (!bots.containsKey(nbBot)) {
                    bots.put(nbBot, new Bot());
                }
                bots.get(nbBot).isBotLow = inst.split(" ")[5].equals("bot");
                bots.get(nbBot).low = Integer.parseInt(inst.split(" ")[6]);
                bots.get(nbBot).isBotHigh = inst.split(" ")[10].equals("bot");
                bots.get(nbBot).high = Integer.parseInt(inst.split(" ")[11]);
            }
        }

        Map<Integer, Integer> outputs = new HashMap<>();

        // Process
        while (!outputs.containsKey(0) || !outputs.containsKey(1) || !outputs.containsKey(2)) {

            List<Integer> botsWith2items = bots.entrySet().stream()
                    .filter(bot -> bot.getValue().values.size() == 2).map(Map.Entry::getKey).collect(Collectors.toList());

            for (int nbBot : botsWith2items) {
                Bot bot = bots.get(nbBot);
                int low = bot.values.stream().mapToInt(e -> e).min().getAsInt();
                int high = bot.values.stream().mapToInt(e -> e).max().getAsInt();

                if (bot.isBotLow) {
                    bots.get(bot.low).values.add(low);
                } else {
                    outputs.putIfAbsent(bot.low, low);
                }
                if (bot.isBotHigh) {
                    bots.get(bot.high).values.add(high);
                } else {
                    outputs.putIfAbsent(bot.high, high);
                }

                bot.values.clear();
            }
        }

        int result = outputs.get(0) * outputs.get(1) * outputs.get(2);

        System.out.println("The multiplication of the 3 first outputs is : " + result);
    }
}
