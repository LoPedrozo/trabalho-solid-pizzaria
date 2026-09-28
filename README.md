# Trabalho SOLID - Sistema de Pedidos de Pizzaria

Sistema de pedidos de pizzaria em Java puro, feito para demonstrar os cinco princípios SOLID.

## Autores

- 
- 

## Domínio escolhido

Sistema de pedidos de pizzaria: o cliente faz um pedido com pizzas, o sistema calcula o preço
aplicando regras (taxa de entrega, cupom, promoção), recebe o pagamento, salva o pedido e
notifica o cliente.

## Como compilar e executar

Compilar:

```bash
javac -d out $(find src -name "*.java")
```

Executar:

```bash
java -cp out pizzaria.Main
```

## Princípios SOLID aplicados

### S — Single Responsibility Principle (Responsabilidade Única)

Cada classe tem um único motivo para mudar. `CalculadoraDePreco` só calcula o preço,
`RepositorioPedidoEmMemoria` só guarda pedidos e cada classe de `regra`, `pagamento` e
`notificacao` cuida de uma única coisa.

### O — Open/Closed Principle (Aberto/Fechado)

`CalculadoraDePreco` percorre uma lista de `RegraDePreco` sem nenhum `if`, `switch` ou `instanceof`.
Para criar uma regra nova (como `TaxaDeEntrega`, `DescontoCupom` ou `PromocaoPizzaGrande`) basta
criar uma classe nova e registrá-la no `Main`, sem alterar a calculadora.

### L — Liskov Substitution Principle (Substituição de Liskov)

`PagamentoPix`, `PagamentoCartao` e `PagamentoDinheiro` podem ser trocados entre si em qualquer lugar
que espera um `MeioDePagamento`. O mesmo vale para `NotificacaoWhatsApp` e `NotificacaoEmail`
com `CanalDeNotificacao`. Todas cumprem o contrato de verdade: nenhuma lança exceção ou fica vazia.

### I — Interface Segregation Principle (Segregação de Interfaces)

As interfaces do pacote `contrato` são pequenas e específicas: `RegraDePreco`, `MeioDePagamento`,
`CanalDeNotificacao` e `RepositorioDePedidos` têm apenas um ou dois métodos cada.
Nenhuma classe é obrigada a implementar métodos que não usa.

### D — Dependency Inversion Principle (Inversão de Dependência)

`ServicoDePedido` é a classe de alto nível e só conhece as interfaces, recebidas pelo construtor.
Não existe `new` dentro dela. Quem decide quais implementações usar é o `Main`, que monta
todos os objetos e injeta as dependências.

## Como estender o sistema

Exemplo: adicionar pagamento por vale-refeição.

1. Criar uma classe nova no pacote `pizzaria.pagamento` implementando `MeioDePagamento`:

```java
package pizzaria.pagamento;

import pizzaria.contrato.MeioDePagamento;

public class PagamentoValeRefeicao implements MeioDePagamento {

    @Override
    public void pagar(double valor) {
        String valorFormatado = String.format("%.2f", valor);
        System.out.println("[VALE] Pagamento de R$ " + valorFormatado + " aprovado no vale-refeicao.");
    }

    @Override
    public String getNome() {
        return "Vale-refeicao";
    }
}
```

2. Registrar no `Main`, passando a nova classe para o `ServicoDePedido`:

```java
PagamentoValeRefeicao vale = new PagamentoValeRefeicao();
ServicoDePedido servico = new ServicoDePedido(calculadora, vale, notificacao, repositorio);
```

Nenhuma linha do `ServicoDePedido`, da `CalculadoraDePreco` ou das outras formas de pagamento
precisa ser alterada.
