package br.com.senac.defesa.model;

import java.util.List;

public class MaisForte extends RegraDeAlvo {

    @Override
    public Inimigo escolherAlvo(
            List<Inimigo> candidatos,
            Ponto posicaoTorre) {
        Inimigo escolhido = null;
        int maiorVida = -1;
        for (Inimigo inimigo : candidatos) {
            int vidaAtual = inimigo.getVida();
            if (vidaAtual > maiorVida) {
                maiorVida = vidaAtual;
                escolhido = inimigo;


            }
        }
        return escolhido;
    }

    @Override
    public String getNome() {
        return "FORTE";
    }
}
