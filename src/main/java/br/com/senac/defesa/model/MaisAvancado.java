package br.com.senac.defesa.model;

import java.util.List;

public class MaisAvancado extends RegraDeAlvo {

    @Override
    public Inimigo escolherAlvo(
            List<Inimigo> candidatos,
            Ponto posicaoTorre) {
        Inimigo escolhido = null;
        double maiorPercorrido = -1;
        for (Inimigo inimigo : candidatos) {
            double percursoAtual = inimigo.getPercorrido();
            if (percursoAtual > maiorPercorrido) {
            maiorPercorrido = percursoAtual;
            escolhido = inimigo;}
        }
        return escolhido;
    }

    @Override
    public String getNome() {
        return "AVANCADO";
    }
}