import java.util.Scanner;

public class Main {
    // Autora informācija
    // Apliecības numurs: 123456
    // Vārds, uzvārds: Jānis Bērziņš
    // Grupa: IT-21
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            System.out.print("Enter command (comp, decomp, about, exit): ");
            command = scanner.next();
            switch (command) {
                case "comp":
                    String inputString = scanner.next().toUpperCase();
                    if (inputString.matches("[ACGT]+")) {
                        byte[] byteArray = new byte[inputString.length() / 4 + (inputString.length() % 4 == 0 ? 0 : 1)];
                        for (int i = 0; i < inputString.length(); i++) {
                            char nucleotide = inputString.charAt(i);
                            int index = i / 4;
                            switch (nucleotide) {
                                case 'A':
                                    byteArray[index] <<= 2;
                                    break;
                                case 'C':
                                    byteArray[index] = (byte) ((byteArray[index] << 2) | 1);
                                    break;
                                case 'G':
                                    byteArray[index] = (byte) ((byteArray[index] << 2) | 2);
                                    break;
                                case 'T':
                                    byteArray[index] = (byte) ((byteArray[index] << 2) | 3);
                                    break;
                            }
                        }
                        int unusedBits = byteArray.length * 4 - inputString.length() * 2;
                        byteArray[0] |= unusedBits;
                        for (byte b : byteArray) {
                            System.out.printf("%02X ", b);
                        }
                        System.out.println();
                    } else {
                        System.out.println("wrong command format");
                    }
                    break;
                case "decomp":
                    int count = scanner.nextInt();
                    if (count * 4 == args.length - 1) {
                        byte[] byteArray = new byte[count];
                        for (int i = 0; i < count; i++) {
                            byteArray[i] = scanner.nextByte();
                        }
                        StringBuilder result = new StringBuilder();
                        for (byte b : byteArray) {
                            for (int j = 6; j >= 0; j -= 2) {
                                int nucleotide = (b >> j) & 3;
                                switch (nucleotide) {
                                    case 0:
                                        result.append('A');
                                        break;
                                    case 1:
                                        result.append('C');
                                        break;
                                    case 2:
                                        result.append('G');
                                        break;
                                    case 3:
                                        result.append('T');
                                        break;
                                }
                            }
                        }
                        System.out.println(result);
                    } else {
                        System.out.println("wrong command format");
                    }
                    break;
                case "about":
                    System.out.println("Author: Jānis Bērziņš (Student ID: 123456, Group: IT-21)");
                    break;
                case "exit":
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("wrong command");
            }
        } while (!command.equals("exit"));
        scanner.close();
    }
}
