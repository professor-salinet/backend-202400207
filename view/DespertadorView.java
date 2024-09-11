package view;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DespertadorView {
    public static void main(String[] args) {
        int horaAtual;
        int minutoAtual;
        int segundoAtual;

        int horaDespertar = 15;
        int minutoDespertar = 11;
        int segundoDespertar = 0;

        Timer timer = new Timer();

        Date date = null;
        Calendar calendar = null;
        boolean sair = false;
        int adiar = 10; // segundos

        Scanner scnDespertador = new Scanner(System.in);

        while (sair == false) {
            date = new Date();
            calendar = GregorianCalendar.getInstance();
            calendar.setTime(date);
            horaAtual = calendar.get(Calendar.HOUR_OF_DAY);
            minutoAtual = calendar.get(Calendar.MINUTE);
            segundoAtual = calendar.get(Calendar.SECOND);

            if (horaAtual >= horaDespertar && minutoAtual >= minutoDespertar && segundoAtual >= segundoDespertar) {
                System.out.println("Acorda, seu despertador está chamando.");
                
                // aqui vocês terão que chamar o switch/case da controller q vai perguntar pro usuário o que ele deseja fazer, se vai adiar ou se vai parar o despertador
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
    }
}