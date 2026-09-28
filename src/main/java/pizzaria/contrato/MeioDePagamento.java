package pizzaria.contrato;

// ISP (Interface Segregation Principle):
// Esta interface tem poucos métodos de propósito.
// Um meio de pagamento só precisa saber pagar e dizer o seu nome.
// Nenhuma classe é obrigada a implementar métodos que não usa.
public interface MeioDePagamento {

    void pagar(double valor);

    String getNome();
}
