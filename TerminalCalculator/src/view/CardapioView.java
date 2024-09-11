package view;
import controller.*;
import java.util.*;

public class CardapioView {
    private static int pedidoCliente;
    public static void main(String[] args) {
        Scanner scnInput = new Scanner(System.in);
        System.out.println("Bem vindo ao Perfect Burguer!");
        System.out.println("Digite o número da opção desejada e tecle Enter");

        String[] produtos = CardapioController.verProdutos();
        for (int v = 0; v < produtos.length; v++) {
            System.out.println(String.format("[%d] >> %s", v + 1, produtos[v]));
        }

        pedidoCliente = scnInput.nextInt();

        System.out.println(CardapioController.respostaParaCliente(pedidoCliente));

        scnInput.close();
    }
}
