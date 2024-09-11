package view;
import java.util.*;

import controller.DespertadorController;

public class DespertadorView {
    public static int minutoDespertar = 19;
    public static int adiar = 10; // minutos

    public static void main(String[] args) {
        int horaAtual;
        int minutoAtual;
        int segundoAtual;

        int horaDespertar = 17;
        int segundoDespertar = 10;

        Date date = null;
        Calendar calendar = null;
        boolean sair = false;

        Scanner scnDespertador = new Scanner(System.in);
        int respostaUsuario;

        while (sair == false) {
            date = new Date();
            calendar = GregorianCalendar.getInstance();
            calendar.setTime(date);
            horaAtual = calendar.get(Calendar.HOUR_OF_DAY);
            minutoAtual = calendar.get(Calendar.MINUTE);
            segundoAtual = calendar.get(Calendar.SECOND);

            if (horaAtual >= horaDespertar && minutoAtual >= minutoDespertar && segundoAtual >= segundoDespertar) {
                System.out.println("Acorda, seu despertador está chamando.");
                System.out.println("Digite abaixo uma opção numérica:");
                String[] opcoes = DespertadorController.verOpcoes();
                for (int c = 0; c < opcoes.length; c++) {
                    System.out.println(String.format("[%d] >> %s", c + 1, opcoes[c]));
                }
                respostaUsuario = scnDespertador.nextInt();
                System.out.println(DespertadorController.acaoDespertador(respostaUsuario));
            }
            System.out.println(String.format("Agora são: %d:%d:%d", horaAtual, minutoAtual, segundoAtual));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print("\033[H\033[2J");  
            System.out.flush();
        }

        scnDespertador.close();
    }
}