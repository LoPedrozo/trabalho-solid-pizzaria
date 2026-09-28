package pizzaria.dominio;

// SRP (Single Responsibility Principle):
// Esta classe só guarda os dados do cliente.
// Ela não envia mensagens: quem faz isso são os canais de notificação.
public class Cliente {

    private String nome;
    private String telefone;
    private String email;
    private Endereco endereco;

    public Cliente(String nome, String telefone, String email, Endereco endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}
