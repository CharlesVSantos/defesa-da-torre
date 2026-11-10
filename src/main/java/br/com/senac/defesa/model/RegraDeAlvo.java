package br.com.senac.defesa.model;

import java.util.List;

public abstract class RegraDeAlvo {

    public abstract Inimigo escolherAlvo(
            List<Inimigo> candidatos,
            Ponto posicaoTorre
    );

    public abstract String getNome();
}

