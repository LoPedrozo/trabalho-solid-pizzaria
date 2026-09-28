package pizzaria.regra;

import pizzaria.contrato.RegraDePreco;
import pizzaria.dominio.Pedido;

// OCP (Open/Closed Principle):
// O desconto de cupom é uma regra nova adicionada com uma classe nova,
// sem mexer no código que já existia.
// SRP: esta classe só sabe aplicar o desconto do cupom.
public class DescontoCupom implements RegraDePreco {

    private String codigo;
    private double percentual;

    public DescontoCupom(String codigo, double percentual) {
        this.codigo = codigo;
        this.percentual = percentual;
    }

    @Override
    public double aplicar(Pedido pedido, double valorAtual) {
        double desconto = valorAtual * percentual / 100;
        return valorAtual - desconto;
    }

    @Override
    public String getDescricao() {
        return "Cupom " + codigo;
    }
}
