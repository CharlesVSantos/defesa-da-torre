package br.com.senac.defesa.model;

public class Laser extends Torre {

    public Laser(int id, Ponto posicao) {
        super(id, posicao, 5);
    }

    @Override
    public void disparar(Inimigo alvo) {
       alvo.receberDano(2);
    }

    @Override
    public int tempoDeRecarga() {
        return 0;
    }

    @Override
    public String getTipo() {
        return "LASER";
    }
}