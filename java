package com.company;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Stream;


class Key {

    public static String generate() throws
            NoSuchAlgorithmException {

        SecureRandom random = SecureRandom.getInstanceStrong();
        byte[] values = new byte[32];
        random.nextBytes(values);

        StringBuilder sb = new StringBuilder();
        for (byte b : values) {
            sb.append(String.format("%02x", b));
        }
        String array = sb.toString();
        return array;
    }
}

public class Main {
    public static void main(String[] args)
            throws NoSuchAlgorithmException {
        int c = args.length;
        Stream<String> stream = Arrays.stream(args);
        if (Test(stream)) {
            if (c >= 3 & c % 2 == 1) {
                    Random random = new Random();
                    Scanner y = new Scanner(System.in);
                    int h = random.nextInt(c);
                    String hex = Key.generate() + args[h];
                    String HMAC = org.apache.commons.
                            codec.digest.DigestUtils.
                            sha256Hex(hex);
                    System.out.println("HMAC: " + Key.generate());
                    System.out.println("Available moves:");
                    for (int i = 1; i < c+1; i++) {
                        System.out.println(i + " - " + args[i-1]);
                    }
                    System.out.println("0 - exit");
                    System.out.print("Enter your move: ");
                    int t = y.nextInt();
                    if (t == 0) {}
                        else{
                    System.out.println("Your move: " + args[t - 1]);
                    System.out.println("Computer move: " + args[h]);
                    Integer[] turns1 = new Integer[c];
                    for (int i = 0; i < c; i++) {
                        turns1[i] = i;
                    }
                    Integer[] turns = new Integer[c * 2];
                    Integer[] turns2 = turns1.clone();
                    System.arraycopy(turns1, 0, turns, 0, (c));
                    System.arraycopy(turns2, 0, turns, (c), (c));
                    Integer[] turns3 = new Integer[c / 2];
                    for (int i = 0; i < c / 2; i++) {
                        turns3[i] = turns[t + 1 + i];
                    }
                    if (Arrays.asList(turns3).contains(h)) {
                        System.out.println("You lose!");
                    } else if (t == h + 1) {
                        System.out.println("Tie!");
                    } else {
                        System.out.println("You win!");
                    }
                    System.out.println("HMAC key: " + HMAC + '\n');
                }
            } else {
                System.out.println("Invalid number of parametrs");
            }
        } else {
            System.out.println("Invalid parametrs");
        }
    }
    public static <T> boolean Test(final Stream<T> stream) {
        final Set<T> seen = new HashSet<>();
        return stream.allMatch(seen::add);
    }
}
