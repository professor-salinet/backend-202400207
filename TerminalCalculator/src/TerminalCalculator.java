import java.util.*;
public class TerminalCalculator {
    private static String numero1;
    private static String numero2;
    public static void main(String[] args) {
        Scanner scnInput = new Scanner(System.in);
        System.out.println("Digite o primeiro número que será somado e tecle Enter");
        numero1 = scnInput.nextLine();
        System.out.println("Digite o segundo número que será somado e tecle Enter");
        numero2 = scnInput.nextLine();
        System.out.println(String.format("O resultado da soma é: %d", Integer.valueOf(numero1) + Integer.valueOf(numero2)));
        scnInput.close();
    }
}