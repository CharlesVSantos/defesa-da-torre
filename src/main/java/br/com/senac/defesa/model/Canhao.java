package br.com.senac.defesa.model;

public class Canhao extends Torre {

    public Canhao(int id, Ponto posicao) {
        super(id, posicao, 4.0);
    }

    @Override
    public void disparar(Inimigo alvo) {

        alvo.receberDano(16);

        for (Inimigo outro : getInimigosAtuais()) {

            if (outro != alvo && outro.estaVivo()
                    && outro.getPosicao().distanciaAte(alvo.getPosicao()) <= 1.5) {

                outro.receberDano(5);
            }
        }
    }

    @Override
    public int tempoDeRecarga() {
        return 5;
    }

    @Override
    public String getTipo() {
        return "CANHAO";
    }
}