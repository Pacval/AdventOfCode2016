package fr.rochet.days;

import fr.rochet.DayInterface;
import fr.rochet.ExoUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 implements DayInterface {

    @Override
    public void part1() throws Exception {
        String[] entries = ExoUtils.getEntries(7);

        Pattern abbaPattern = Pattern.compile(".*(.)(?!\\1)(.)\\2\\1.*");
        Pattern excluderPatter = Pattern.compile(".*\\[[^]]*(.)(.)\\2\\1[^\\[]*].*");

        int nbIpSupportingTls = 0;

        for (String ipAdress : entries) {
            Matcher abbaMatcher = abbaPattern.matcher(ipAdress);
            if (abbaMatcher.matches()) {
                Matcher excluderMatcher = excluderPatter.matcher(ipAdress);
                if (!excluderMatcher.matches()) {
                    nbIpSupportingTls++;
                }
            }
        }

        System.out.println("Number of IPs supporting TLS : " + nbIpSupportingTls);
    }

    @Override
    public void part2() throws Exception {
        String[] entries = ExoUtils.getEntries(7);

        Pattern abaPattern = Pattern.compile("(?=(.)(?!\\1)(.)\\1).");

        boolean supportSsl;
        int nbIpSupportingSsl = 0;

        for (String ipAdress : entries) {
            String[] blocks = ipAdress.replace('[', ']').split("]");
            supportSsl = false;

            for (int i = 0; i < blocks.length; i += 2) {
                Matcher abaMatcher = abaPattern.matcher(blocks[i]);
                while (abaMatcher.find()) {
                    String bab = abaMatcher.group(2) + abaMatcher.group(1) + abaMatcher.group(2);
                    for (int k = 1; k < blocks.length; k += 2) {
                        if (blocks[k].contains(bab)) {
                            supportSsl = true;
                        }
                    }
                }
            }

            if (supportSsl) {
                nbIpSupportingSsl++;
            }
        }

        System.out.println("Number of IPs supporting SSL : " + nbIpSupportingSsl);
    }
}
