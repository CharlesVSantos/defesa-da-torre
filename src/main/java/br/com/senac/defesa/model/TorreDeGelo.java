package br.com.senac.defesa.model;

public class TorreDeGelo extends Torre {

    public TorreDeGelo(int id, Ponto posicao) {
        super(id, posicao, 3.5);
    }

    @Override
    public void disparar(Inimigo alvo) {
        alvo.receberDano(3);
        alvo.desacelerar(12);
    }

    @Override
    public int tempoDeRecarga() {
        return 2;

    }

    @Override
    public String getTipo() {
        return "GELO";
    }
}
