package br.com.senac.defesa.model;

import java.util.List;

public abstract class Torre {

    public abstract void disparar(Inimigo alvo);

    public abstract int tempoDeRecarga();

    public abstract String getTipo();

    private final int id;
    private final Ponto posicao;
    private final double alcance;

    private String prioridade = "PROXIMO";
    private int recarga;
    private double angulo;
    private Ponto tiroAtual;

    private List<Inimigo> inimigosAtuais;

    public Torre(int id, Ponto posicao, double alcance) {
        this.id = id;
        this.posicao = posicao;
        this.alcance = alcance;
    }

    protected List<Inimigo> getInimigosAtuais() {
        return inimigosAtuais;
    }


    public final void agir(List<Inimigo> inimigos) {

        tiroAtual = null;

        if (recarga > 0) {
            recarga--;
            return;
        }

        Inimigo alvo = escolherAlvo(inimigos);

        if (alvo == null) {
            return;
        }

        angulo = Math.atan2(
                alvo.getPosicao().z() - posicao.z(),
                alvo.getPosicao().x() - posicao.x()
        );

        this.inimigosAtuais = inimigos;
        disparar(alvo);

        tiroAtual = alvo.getPosicao();

        recarga = tempoDeRecarga();
    }


    private Inimigo escolherAlvo(List<Inimigo> inimigos) {
        Inimigo escolhido = null;

        if (prioridade.equals("PROXIMO")) {
            double menorDistancia = Double.MAX_VALUE;
            for (Inimigo inimigo : inimigos) {
                double distancia = posicao.distanciaAte(inimigo.getPosicao());
                if (inimigo.estaVivo() && distancia <= alcance && distancia < menorDistancia) {
                    menorDistancia = distancia;
                    escolhido = inimigo;
                }
            }
        } else if (prioridade.equals("FORTE")) {
            int maiorVida = -1;
            for (Inimigo inimigo : inimigos) {
                double distancia = posicao.distanciaAte(inimigo.getPosicao());
                if (inimigo.estaVivo() && distancia <= alcance && inimigo.getVida() > maiorVida) {
                    maiorVida = inimigo.getVida();
                    escolhido = inimigo;
                }
            }
        } else if (prioridade.equals("AVANCADO")) {
            double maisAvancado = -1;
            for (Inimigo inimigo : inimigos) {
                double distancia = posicao.distanciaAte(inimigo.getPosicao());
                if (inimigo.estaVivo() && distancia <= alcance && inimigo.getPercorrido() > maisAvancado) {
                    maisAvancado = inimigo.getPercorrido();
                    escolhido = inimigo;
                }
            }
        }

        return escolhido;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public int getId() {
        return id;
    }

    public Ponto getPosicao() {
        return posicao;
    }

    public double getAlcance() {
        return alcance;
    }

    public double getAngulo() {
        return angulo;
    }

    public Ponto getTiroAtual() {
        return tiroAtual;
    }
}
