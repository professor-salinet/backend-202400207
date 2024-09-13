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
                if (adiamentoAtual <= 3) {
                    if (mostrarOpcoesAdiamentos()) {
                        exibirAlarmeAdiado(adiar);
                        if ((minutoDespertar + adiar) > 59) {
                            horaDespertar++;
                            if (horaDespertar > 23) {
                                horaDespertar = 0;
                            }
                            minutoDespertar = (minutoDespertar + adiar) - 59;
                        }
                        adiamentoAtual++;
                    } else {
                        break;
                    }
                } else {
                    sairDoSistema();
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
