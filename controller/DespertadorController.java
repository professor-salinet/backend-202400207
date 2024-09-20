package controller;
import model.*;
import view.*;
import java.util.*;

public class DespertadorController extends DespertadorView {
    public static String[] verOpcoes() {
        return DespertadorModel.mostrarOpcoes();
    }

    public static void acaoDespertador(int opcaoUsuario) {
        if (adiamentoAtual < 3) {
            switch (opcaoUsuario) {
                case 1:
                    if (mostrarOpcoesAdiamentos()) {
                        if ((minutoAtual + adiar) > 59) {
                            horaDespertar++;
                            if (horaDespertar > 23) {
                                horaDespertar = 0;
                            }
                            minutoDespertar = (minutoAtual + adiar) - 59;
                        } else {
                            minutoDespertar = minutoAtual + adiar;
                        }
                        adiamentoAtual++;
                        exibirAlarmeAdiado(adiar);
                    } else {
                        break;
                    }
                    break;

                case 2:
                    sairDoSistema();
                    break;

                default:
                    mostrarOpcaoInvalida();
                    break;
            }
        } else {
            sairDoSistema();
        }
    }

    public static void getHMS() {
        date = new Date();
        calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        horaAtual = calendar.get(Calendar.HOUR_OF_DAY);
        minutoAtual = calendar.get(Calendar.MINUTE);
        segundoAtual = calendar.get(Calendar.SECOND);

        if (horaDespertar >= 0 && minutoDespertar >= 0) {
            int[] tempoInicial = {horaAtual, minutoAtual, segundoAtual};
            int[] tempoFinal = {horaDespertar, minutoDespertar};

            int[] tempoRestante = calcularTempoRestante(tempoInicial, tempoFinal);

            horaRestante = tempoRestante[0];
            minutoRestante = tempoRestante[1];
            segundoRestante = tempoRestante[2];
        }
    }

    public static int[] calcularTempoRestante(int[] tempoInicial, int[] tempoFinal) {
        int maxMinutos = 60;
        int maxSegundos = 60;

        int horaInicial = tempoInicial[0];
        int minutoInicial = tempoInicial[1];
        int segundoInicial = tempoInicial[2];

        int horaFinal = tempoFinal[0];
        int minutoFinal = tempoFinal[1];

        System.out.println("minutoFinal: " + minutoFinal);

        int horaRestante = horaFinal - horaInicial;
        int minutoRestante = (minutoFinal - minutoInicial) - 1;
        int segundoRestante = maxSegundos - segundoInicial;

        if (horaRestante > 0 && minutoFinal <= minutoInicial) {
            horaRestante--;
        }

        if (minutoRestante < 0) {
            minutoRestante = ((maxMinutos - minutoInicial) + minutoFinal) - 1;
        }

        if (segundoRestante > maxSegundos) {
            segundoRestante = maxSegundos;
        }

        int[] tempoRestante = {horaRestante, minutoRestante, segundoRestante};
        return tempoRestante;
    }
}
