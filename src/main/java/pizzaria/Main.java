package pizzaria;

import java.util.ArrayList;

import pizzaria.contrato.RegraDePreco;
import pizzaria.dominio.Cliente;
import pizzaria.dominio.Endereco;
import pizzaria.dominio.ItemPedido;
import pizzaria.dominio.Pedido;
import pizzaria.dominio.Pizza;
import pizzaria.dominio.Tamanho;
import pizzaria.notificacao.NotificacaoEmail;
import pizzaria.notificacao.NotificacaoWhatsApp;
import pizzaria.pagamento.PagamentoCartao;
import pizzaria.pagamento.PagamentoDinheiro;
import pizzaria.pagamento.PagamentoPix;
import pizzaria.persistencia.RepositorioPedidoEmMemoria;
import pizzaria.regra.DescontoCupom;
import pizzaria.regra.PromocaoPizzaGrande;
import pizzaria.regra.TaxaDeEntrega;
import pizzaria.servico.CalculadoraDePreco;
import pizzaria.servico.ServicoDePedido;

// DIP (Dependency Inversion Principle) na prática:
// O Main é o único lugar do sistema onde as implementações das interfaces
// (regras, pagamentos, notificações, calculadora e repositório) são escolhidas e criadas.
// Aqui fazemos a montagem manual da injeção de dependências.
// É isso que permite trocar Pix por Cartão (ou WhatsApp por E-mail)
// sem alterar uma única linha do ServicoDePedido.
public class Main {

    public static void main(String[] args) {

        // O repositório é criado uma vez só e usado nos dois cenários.
        RepositorioPedidoEmMemoria repositorio = new RepositorioPedidoEmMemoria();

        // ==================== CENÁRIO 1 ====================
        System.out.println("--------------------------------------------------");
        System.out.println("CENARIO 1 - Pix + WhatsApp");
        System.out.println("--------------------------------------------------");

        // 1. Endereço, cliente e pedido
        Endereco endereco1 = new Endereco("Rua das Flores, 100", "Centro", 5.0);
        Cliente cliente1 = new Cliente("Maria Silva", "(51) 99999-1111", "maria@email.com", endereco1);
        Pedido pedido1 = new Pedido("PED-001", cliente1);

        // 2. Pizzas do pedido (uma GRANDE, então a promoção será aplicada)
        Pizza calabresa = new Pizza("Calabresa", Tamanho.GRANDE, 55.00);
        Pizza margherita = new Pizza("Margherita", Tamanho.MEDIA, 42.00);
        pedido1.adicionarItem(new ItemPedido(calabresa, 1));
        pedido1.adicionarItem(new ItemPedido(margherita, 1));

        // 3. Regras de preço deste cenário
        ArrayList<RegraDePreco> regras1 = new ArrayList<RegraDePreco>();
        regras1.add(new TaxaDeEntrega(2.50));
        regras1.add(new PromocaoPizzaGrande(5.00));
        CalculadoraDePreco calculadora1 = new CalculadoraDePreco(regras1);

        // 4. Implementações escolhidas: Pix e WhatsApp
        PagamentoPix pix = new PagamentoPix();
        NotificacaoWhatsApp whatsApp = new NotificacaoWhatsApp();

        // 5. Injeção de dependências pelo construtor
        ServicoDePedido servico1 = new ServicoDePedido(calculadora1, pix, whatsApp, repositorio);
        servico1.finalizarPedido(pedido1);

        // ==================== CENÁRIO 2 ====================
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("CENARIO 2 - Cartao + E-mail");
        System.out.println("--------------------------------------------------");

        // 1. Outro endereço, outro cliente e outro pedido
        Endereco endereco2 = new Endereco("Av. Brasil, 2500", "Jardim America", 8.0);
        Cliente cliente2 = new Cliente("Joao Souza", "(51) 98888-2222", "joao@email.com", endereco2);
        Pedido pedido2 = new Pedido("PED-002", cliente2);

        // 2. Nenhuma pizza GRANDE: a promoção será avaliada, mas não dará desconto
        Pizza portuguesa = new Pizza("Portuguesa", Tamanho.MEDIA, 45.00);
        Pizza chocolate = new Pizza("Chocolate", Tamanho.PEQUENA, 30.00);
        pedido2.adicionarItem(new ItemPedido(portuguesa, 1));
        pedido2.adicionarItem(new ItemPedido(chocolate, 1));

        // 3. Regras de preço diferentes (a promoção está na lista de propósito)
        ArrayList<RegraDePreco> regras2 = new ArrayList<RegraDePreco>();
        regras2.add(new TaxaDeEntrega(3.00));
        regras2.add(new PromocaoPizzaGrande(5.00));
        regras2.add(new DescontoCupom("ALUNO10", 10.0));
        CalculadoraDePreco calculadora2 = new CalculadoraDePreco(regras2);

        // 4. Implementações escolhidas: Cartão e E-mail
        PagamentoCartao cartao = new PagamentoCartao("Visa");
        NotificacaoEmail email = new NotificacaoEmail();

        // 5. Mesmo ServicoDePedido, outras peças encaixadas
        ServicoDePedido servico2 = new ServicoDePedido(calculadora2, cartao, email, repositorio);
        servico2.finalizarPedido(pedido2);

        // ==================== CENÁRIO 3 ====================
        // LSP: só o meio de pagamento muda. O resto é reaproveitado do cenário 1.
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("CENARIO 3 - Dinheiro + WhatsApp");
        System.out.println("--------------------------------------------------");

        Pedido pedido3 = new Pedido("PED-003", cliente1);
        pedido3.adicionarItem(new ItemPedido(margherita, 2));

        PagamentoDinheiro dinheiro = new PagamentoDinheiro();
        ServicoDePedido servico3 = new ServicoDePedido(calculadora1, dinheiro, whatsApp, repositorio);
        servico3.finalizarPedido(pedido3);

        // ==================== REPOSITÓRIO ====================
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("CONSULTA NO REPOSITORIO");
        System.out.println("--------------------------------------------------");

        Pedido encontrado = repositorio.buscarPorId("PED-001");
        // buscarPorId devolve null quando não acha o pedido, então testamos antes de usar.
        if (encontrado == null) {
            System.out.println("Pedido PED-001 nao encontrado.");
        } else {
            System.out.println("Pedido " + encontrado.getId() + " esta pago? " + encontrado.isPago());
        }
    }
}
