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
        switch (opcaoUsuario) {
            case 1:
                mostrarOpcoesAdiamentos();
                resposta = "Ok! Alarme adiado em: " + adiar + " minutos.";
                minutoDespertar += adiar;
                if (minutoDespertar > 59) {
                    minutoDespertar = 59;
                }
                break;

            case 2:
                sairDoSistema();
                break;

            default:
                resposta = "Opção inválida.";
                break;
        }
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
