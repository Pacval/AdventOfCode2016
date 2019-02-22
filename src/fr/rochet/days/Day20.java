package fr.rochet.days;

import fr.rochet.DayInterface;
import fr.rochet.ExoUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day20 implements DayInterface {

    private class Block {
        long min;
        long max;

        Block(long min, long max) {
            this.min = min;
            this.max = max;
        }

        long getMin() {
            return min;
        }

        long getMax() {
            return max;
        }

        boolean isIn(long i) {
            return i >= min && i <= max;
        }

        boolean isOut(long i) {
            return i <= min || i >= max;
        }
    }

    @Override
    public void part1() throws Exception {
        String[] entries = ExoUtils.getEntries(20);

        List<Block> blocks = new ArrayList<>();
        for (String entry : entries) {
            blocks.add(new Block(Long.parseLong(entry.split("-")[0]), Long.parseLong(entry.split("-")[1])));
        }

        long ip = 0L;
        while (anyMatch(blocks, ip)) {
            final long finalIp = ip;
            ip = blocks.stream().filter(x -> x.isIn(finalIp)).max(Comparator.comparing(Block::getMax)).get().max + 1;
        }

        System.out.println("the lowest-valued IP that is not blocked is : " + ip);
    }

    @Override
    public void part2() throws Exception {
        String[] entries = ExoUtils.getEntries(20);

        List<Block> blocks = new ArrayList<>();
        for (String entry : entries) {
            blocks.add(new Block(Long.parseLong(entry.split("-")[0]), Long.parseLong(entry.split("-")[1])));
        }

        long allowedIp = 0;

        long ip = 0;
        while (ip <= 4294967295L) {
            if (anyMatch(blocks, ip)) {
                final long finalIp = ip;
                ip = blocks.stream().filter(x -> x.isIn(finalIp)).max(Comparator.comparing(Block::getMax)).get().max + 1;
            } else {
                final long finalIp = ip;
                long nextBlockedIp = blocks.stream().filter(x -> x.min > finalIp).min(Comparator.comparing(Block::getMin)).get().min;
                allowedIp += nextBlockedIp - ip;
                ip = nextBlockedIp;
            }
        }

        System.out.println("There are " + allowedIp + " allowed IP");
    }

    private boolean anyMatch(List<Block> blocks, long ip) {
        return blocks.stream().anyMatch(x -> x.isIn(ip));
    }
}
