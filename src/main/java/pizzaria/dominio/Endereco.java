package pizzaria.dominio;

// SRP (Single Responsibility Principle):
// Esta classe só guarda os dados do endereço de entrega.
// Ela não calcula taxa: quem faz isso é a regra TaxaDeEntrega.
public class Endereco {

    private String rua;
    private String bairro;
    private double distanciaKm;

    public Endereco(String rua, String bairro, double distanciaKm) {
        // Regra de integridade: distância não pode ser negativa.
        if (distanciaKm < 0) {
            throw new IllegalArgumentException("A distancia nao pode ser negativa.");
        }
        this.rua = rua;
        this.bairro = bairro;
        this.distanciaKm = distanciaKm;
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }
}
