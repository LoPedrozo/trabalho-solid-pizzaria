package pizzaria.notificacao;

import pizzaria.contrato.CanalDeNotificacao;
import pizzaria.dominio.Cliente;

// LSP (Liskov Substitution Principle):
// Pode substituir qualquer CanalDeNotificacao sem quebrar o sistema,
// porque realmente envia a mensagem.
// SRP: esta classe só sabe enviar mensagem pelo WhatsApp.
public class NotificacaoWhatsApp implements CanalDeNotificacao {

    @Override
    public void enviar(Cliente cliente, String mensagem) {
        System.out.println("[WHATSAPP] Para " + cliente.getTelefone() + ": " + mensagem);
    }
}
