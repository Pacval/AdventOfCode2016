package fr.rochet.days;

import fr.rochet.DayInterface;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 implements DayInterface {

    @Override
    public void part1() throws Exception {
        String salt = "jlmsuwbz";

        int count = 0;
        int nbKeys = 0;

        HashMap<Integer, String> hashes = new HashMap<>();

        Pattern pattern3 = Pattern.compile("(.)\\1\\1");

        while (nbKeys < 64) {

            String hash3 = getHash(hashes, salt, count);

            Matcher matcher3 = pattern3.matcher(hash3);
            if (matcher3.find()) {
                String carac = matcher3.group(1);
                Pattern pattern5 = Pattern.compile(carac + carac + carac + carac + carac);

                for (int i = count + 1; i <= count + 1000; i++) {
                    String hash5 = getHash(hashes, salt, i);
                    Matcher matcher5 = pattern5.matcher(hash5);

                    if (matcher5.find()) {
                        nbKeys++;
                        break;
                    }
                }
            }

            // plus utile on libère de la place
            hashes.remove(count);
            count++;
        }

        System.out.println("The index which produces the 64th one-time pad key is : " + (count - 1));
    }

    @Override
    public void part2() throws Exception {
        String salt = "jlmsuwbz";

        int count = 0;
        int nbKeys = 0;

        HashMap<Integer, String> hashes = new HashMap<>();

        Pattern pattern3 = Pattern.compile("(.)\\1\\1");

        while (nbKeys < 64) {

            String hash3 = getAdvancedHash(hashes, salt, count);

            Matcher matcher3 = pattern3.matcher(hash3);
            if (matcher3.find()) {
                String carac = matcher3.group(1);
                Pattern pattern5 = Pattern.compile(carac + carac + carac + carac + carac);

                for (int i = count + 1; i <= count + 1000; i++) {
                    String hash5 = getAdvancedHash(hashes, salt, i);
                    Matcher matcher5 = pattern5.matcher(hash5);

                    if (matcher5.find()) {
                        nbKeys++;
                        break;
                    }
                }
            }

            // plus utile on libère de la place
            hashes.remove(count);
            count++;
        }

        System.out.println("The index which produces the 64th one-time pad key with advanced hashes is : " + (count - 1));
    }

    private String getHash(HashMap<Integer, String> hashes, String salt, int count) throws NoSuchAlgorithmException {
        if (hashes.containsKey(count)) {
            return hashes.get(count);
        } else {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(String.valueOf(salt + count).getBytes());
            byte[] digest = md.digest();
            String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();

            hashes.put(count, hash);
            return hash;
        }
    }

    private String getAdvancedHash(HashMap<Integer, String> hashes, String salt, int count) throws NoSuchAlgorithmException {
        if (hashes.containsKey(count)) {
            return hashes.get(count);
        } else {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(String.valueOf(salt + count).getBytes());
            byte[] digest = md.digest();
            String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();

            for (int i = 0; i < 2016; i++) {
                md.update(hash.getBytes());
                digest = md.digest();
                hash = DatatypeConverter.printHexBinary(digest).toLowerCase();
            }

            hashes.put(count, hash);
            return hash;
        }
    }
}
