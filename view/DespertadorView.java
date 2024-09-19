package view;
import java.util.*;

import controller.*;

public class DespertadorView {
    public static int horaDespertar;
    public static int minutoDespertar;
    public static int adiar = 10; // minutos
    public static int[] adiamentos = {1, 5, 10, 15};
    public static int qtdAdiamento = 3;
    public static int adiamentoAtual = 0;

    public static Date date = null;
    public static Calendar calendar = null;

    public static int horaAtual;
    public static int minutoAtual;
    public static int segundoAtual;

    public static int horaRestante;
    public static int minutoRestante;
    public static int segundoRestante;

    public static int maxHora = 23;
    public static int maxMinuto = 59;
    public static int maxSegundo = 59;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static Scanner scnInput = new Scanner(System.in);
    public static Scanner scnInputString = new Scanner(System.in);

    public static void main(String[] args) {
        boolean sair = false;

        int respostaUsuario;

        DespertadorController.getHMS();

        System.out.println(
            String.format(
                "Agora são: %s%d:%d:%d%s.", 
                ANSI_BLUE,
                horaAtual, 
                minutoAtual, 
                segundoAtual,
                ANSI_RESET
            )
        );

        configurarDespertador();

        while (sair == false) {
            DespertadorController.getHMS();

            mostrarHorarioAtual();
            mostrarTempoRestante();
            mostrarAdiamentos();

            if (horaAtual >= horaDespertar) {
                if (minutoAtual >= minutoDespertar) {
                    if (podeAdiar()) {
                        System.out.println("Acorda, seu despertador está chamando.");
                        System.out.println("Digite um número abaixo das seguintes opções:");
                        String[] opcoes = DespertadorController.verOpcoes();

                        for (int c = 0; c < opcoes.length; c++) {
                            System.out.println(
                                String.format(
                                    "[%d] >> %s", 
                                    c + 1, 
                                    opcoes[c]
                                )
                            );
                        }

                        respostaUsuario = scnInput.nextInt();
                        DespertadorController.acaoDespertador(respostaUsuario);
                    } else {
                        novoDespertador();
                    }
                }
            } 


            contador(1000);
            limparTela();
        }
        scnInput.close();
        scnInputString.close();
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    public static void mostrarHorarioAtual() {
        System.out.println(
            String.format(
                "Agora são: %s%d:%d:%d%s.", 
                ANSI_YELLOW, 
                horaAtual, 
                minutoAtual, 
                segundoAtual,
                ANSI_RESET
            )
        );
    }

    public static void mostrarAdiamentos() {
        System.out.println(
            String.format(
                "Adiado %s%d%s vez(es).",
                ANSI_GREEN,
                adiamentoAtual,
                ANSI_RESET
            )
        );
    }

    public static void mostrarTempoRestante() {
        System.out.println(
            String.format(
                "Falta(m) %s%d%s horas, %s%d%s minutos e %s%d%s segundos para o próximo alarme que irá despertar às %s%d:%d%s.",
                ANSI_CYAN,
                horaRestante,
                ANSI_RESET, 
                ANSI_CYAN,
                minutoRestante,
                ANSI_RESET, 
                ANSI_CYAN,
                segundoRestante,
                ANSI_RESET,
                ANSI_RED,
                horaDespertar, 
                minutoDespertar, 
                ANSI_RESET
            )
        );
    }

    public static void mostrarOpcaoInvalida() {
        System.err.println("Opção inválida.");
    }

    public static void contador(int milisseg) {
        try {
            Thread.sleep(milisseg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void configurarDespertador() {
        adiamentoAtual = 0;
        configurarHora();
        configurarMinuto();
    }

    public static void configurarHora() {
        limparTela();
        mostrarHorarioAtual();
        System.out.println("Digite abaixo a hora que você deseja configurar o despertador e tecle Enter:");
        horaDespertar = scnInput.nextInt();
        if (horaDespertar < horaAtual) {
            System.err.println("Ops! Digite um número igual ou maior que: " + horaAtual + " e tente novamente.");
            configurarHora();
        } else if (horaDespertar > maxHora) {
            System.err.println("Ops! Digite um número igual ou menor que " + maxHora + " e tente novamente.");
            configurarHora();
        }
    }

    public static void configurarMinuto() {
        limparTela();
        mostrarHorarioAtual();
        System.out.println("Digite abaixo o minuto que você deseja configurar o despertador e tecle Enter:");
        minutoDespertar = scnInput.nextInt();
        if (horaDespertar == horaAtual) {
            if (minutoDespertar < minutoAtual) {
                System.err.println("Ops! Digite um número igual ou maior que: " + minutoAtual + " e tente novamente.");
                configurarMinuto();
            }
        }
        if (minutoDespertar > maxMinuto) {
            System.err.println("Ops! Digite um número igual ou menor que " + maxMinuto + " e tente novamente.");
            configurarMinuto();
        }
    }

    public static void sairDoSistema() {
        System.out.println("Ok! Alarme parado.");
        System.exit(0);
    }

    public static boolean mostrarOpcoesAdiamentos() {
        System.out.println("Digite o número da opção abaixo e tecle Enter:");
        for (int a = 0; a < adiamentos.length; a++) {
            System.out.println(
                String.format(
                    "[%d] >> adiar %d minuto(s)", 
                    a + 1, 
                    adiamentos[a]
                )
            );
        }
        int respostaUsuario = scnInput.nextInt();
        if (respostaUsuario <= adiamentos.length) {
            adiar = adiamentos[respostaUsuario - 1];
            return true;
        } else {
            System.out.println("Ops! Opção inválida, tente novamente.");
            return false;
        }
    }

    public static boolean novoDespertador() {
        System.out.println("Deseja configurar um novo despertador? Digite o número da opção abaixo e tecle Enter:");
        System.out.println("[1] >> Sim");
        System.out.println("[2] >> Não");
        int respostaUsuario = scnInput.nextInt();
        if (respostaUsuario == 1) {
            configurarDespertador();
        } else {
            sairDoSistema();
        }
        return true;
    }

    public static boolean podeAdiar() {
        if (adiamentoAtual < 3) {
            return true;
        } else {
            return false;
        }
    }

    public static void exibirAlarmeAdiado(int adiamentoInteger) {
        System.out.println("Ok! Despertador adiado em: " + adiamentoInteger + " minutos.");
        System.out.println("Você poderá adiar mais " + (qtdAdiamento - adiamentoAtual) + " vezes.");
    }
}