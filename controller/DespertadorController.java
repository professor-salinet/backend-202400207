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

        if (horaDespertar > 0 && minutoDespertar > 0) {
            // horaRestante = horaDespertar - horaAtual;
            // if (horaRestante > 0 && minutoDespertar <= minutoAtual) {
            //     horaRestante--;
            // }

            // minutoRestante = (minutoDespertar - minutoAtual) - 1;
            // if (minutoRestante <= 0) {
            //     minutoRestante = ((maxMinuto - minutoAtual) + minutoDespertar) - 1;
            // }

            // segundoRestante = (maxSegundo - segundoAtual) + 1;
            // if (segundoRestante > maxSegundo) {
            //     segundoRestante = maxSegundo;
            // }

            int[] tempoInicial = {horaAtual, minutoAtual, segundoAtual};
            int[] tempoFinal = {horaDespertar, minutoDespertar, 60};

            int[] tempoRestante = calcularTempoRestante(tempoInicial, tempoFinal);

            horaRestante = tempoRestante[0];
            minutoRestante = tempoRestante[1];
            segundoRestante = tempoRestante[2];
        }
    }

    public static int[] calcularTempoRestante(int[] tempoInicial, int[] tempoFinal) {
        int maxHoras = 24;
        int maxMinutos = 60;
        int maxSegundos = 60;

        int totalHorasPorDia = maxHoras;
        int totalMinutosPorDia = totalHorasPorDia * maxMinutos;
        int totalSegundosPorDia = totalMinutosPorDia * maxSegundos;

        int horaInicial = tempoInicial[0];
        int minutoInicial = tempoInicial[1];
        int segundoInicial = tempoInicial[2];

        int horaFinal = tempoFinal[0];
        int minutoFinal = tempoFinal[1];
        int segundoFinal = tempoFinal[2];

        int[] tempoRestante = {0,0,0};

        tempoRestante[0] = tempoFinal[0] - tempoInicial[0]; // horas restantes
        tempoRestante[1] = (tempoFinal[1] - tempoInicial[1]) - 1; // minutos restantes
        tempoRestante[2] = tempoFinal[2] - tempoInicial[2]; // segundos restantes

        // System.out.println("horas restantes antes: " + tempoRestante[0]);
        // System.out.println("minutos restantes antes: " + tempoRestante[1]);
        // System.out.println("segundos restantes antes: " + tempoRestante[2]);

        // validador de hora
        if (tempoRestante[0] > 0 && tempoFinal[1] <= tempoInicial[1]) {
            tempoRestante[0]--;
        }

        // validador de minuto
        if (tempoRestante[1] < 0) {
            tempoRestante[1] = ((maxMinutos - minutoInicial) + minutoFinal) - 1;
        }

        // validador de segundo
        if (tempoRestante[2] > maxSegundos) {
            tempoRestante[2] = maxSegundos;
        }

        // System.out.println("horas restantes depois: " + tempoRestante[0]);
        // System.out.println("minutos restantes depois: " + tempoRestante[1]);
        // System.out.println("segundos restantes depois: " + tempoRestante[2]);
        return tempoRestante;
    }
}
