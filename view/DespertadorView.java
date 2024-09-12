package view;
import java.util.*;
import controller.*;

public class DespertadorView {
    public static int horaDespertar;
    public static int minutoDespertar;
    public static int segundoDespertar;
    public static int adiar = 10; // minutos
    public static int[] adiamentos = {5, 10, 15};

    public static Date date = null;
    public static Calendar calendar = null;

    public static int horaAtual;
    public static int minutoAtual;
    public static int segundoAtual;

    public static void main(String[] args) {
        boolean sair = false;

        Scanner scnDespertador = new Scanner(System.in);
        int respostaUsuario;
        Scanner scnConfigurar = new Scanner(System.in);

        DespertadorController.getHMS();

        System.out.println(
            String.format(
                "Agora são: %d:%d:%d.", 
                horaAtual, 
                minutoAtual, 
                segundoAtual
            )
        );

        System.out.println("Digite abaixo a hora que você deseja configurar o despertador e tecle Enter:");
        horaDespertar = scnConfigurar.nextInt();

        System.out.println("Digite abaixo o minuto que você deseja configurar o despertador e tecle Enter:");
        minutoDespertar = scnConfigurar.nextInt();

        System.out.println("Digite abaixo o segundo que você deseja configurar o despertador e tecle Enter:");
        segundoDespertar = scnConfigurar.nextInt();

        while (sair == false) {
            DespertadorController.getHMS();

            System.out.println(
                String.format(
                    "Agora são: %d:%d:%d. O próximo alarme irá despertar às %d:%d:%d", 
                    horaAtual, 
                    minutoAtual, 
                    segundoAtual, 
                    horaDespertar, 
                    minutoDespertar, 
                    segundoDespertar
                )
            );

            if (
                horaAtual >= horaDespertar && 
                minutoAtual >= minutoDespertar && 
                segundoAtual >= segundoDespertar
            ) {
                System.out.println("Acorda, seu despertador está chamando.");
                System.out.println("Digite abaixo uma opção numérica:");
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

                respostaUsuario = scnDespertador.nextInt();
                System.out.println(DespertadorController.acaoDespertador(respostaUsuario));
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print("\033[H\033[2J");  
            System.out.flush();
        }

        scnDespertador.close();
        scnConfigurar.close();
    }
}