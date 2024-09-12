package controller;
import model.*;
import view.*;
import java.util.*;

public class DespertadorController extends DespertadorView {
    public static String[] verOpcoes() {
        return DespertadorModel.mostrarOpcoes();
    }

    public static String acaoDespertador(int opcaoUsuario) {
        String resposta = "";
        Scanner scnAdiar = new Scanner(System.in);
        int respostaAdiar;
        switch (opcaoUsuario) {
            case 1:
                System.out.println("Digite o número da opção abaixo e tecle Enter:");
                for (int a = 0; a < adiamentos.length; a++) {
                    System.out.println(
                        String.format(
                            "[%d] >> adiar %d minutos", 
                            a + 1, 
                            adiamentos[a]
                        )
                    );
                }
                respostaAdiar = scnAdiar.nextInt();
                adiar = adiamentos[respostaAdiar - 1];

                resposta = "Ok! Alarme adiado em: " + adiar + " minutos.";
                minutoDespertar += adiar;
                if (minutoDespertar > 59) {
                    minutoDespertar = 59;
                }
                break;

            case 2:
                System.out.println("Ok! Alarme parado.");
                System.exit(0);
                break;

            default:
                resposta = "Opção inválida.";
                break;
        }
        scnAdiar.close();
        return resposta;
    }

    public static void getHMS() {
        date = new Date();
        calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        horaAtual = calendar.get(Calendar.HOUR_OF_DAY);
        minutoAtual = calendar.get(Calendar.MINUTE);
        segundoAtual = calendar.get(Calendar.SECOND);
    }
}
