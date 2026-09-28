package pizzaria.contrato;

import pizzaria.dominio.Cliente;

// ISP (Interface Segregation Principle):
// Esta interface tem um único método de propósito.
// Um canal de notificação só precisa saber enviar uma mensagem ao cliente.
public interface CanalDeNotificacao {

    void enviar(Cliente cliente, String mensagem);
}
