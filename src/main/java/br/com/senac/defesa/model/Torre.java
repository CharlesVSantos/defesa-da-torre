package br.com.senac.defesa.model;

import java.util.List;

public class Torre {

    private final int id;
    private final String tipo;
    private final Ponto posicao;
    private final double alcance;

    private String prioridade = "PROXIMO";
    private int recarga;
    private double angulo;
    private Ponto tiroAtual;

    public Torre(int id, String tipo, Ponto posicao) {
        this.id = id;
        this.tipo = tipo;
        this.posicao = posicao;
        switch (tipo) {
            case "CANHAO":
                alcance = 4.0;
                break;
            case "GELO":
                alcance = 3.5;
                break;
            case "LASER":
                alcance = 5.0;
                break;
            default:
                throw new IllegalArgumentException("Tipo de torre desconhecido: " + tipo);
        }
    }

    public void agir(List<Inimigo> inimigos) {
        tiroAtual = null;
        Inimigo alvo;

        switch (tipo) {
            case "CANHAO":
                if (recarga > 0) {
                    recarga--;
                    break;
                }
                alvo = escolherAlvo(inimigos);
                if (alvo == null) {
                    break;
                }
                angulo = Math.atan2(alvo.getPosicao().z() - posicao.z(), alvo.getPosicao().x() - posicao.x());
                // a bala explode e acerta também quem estiver perto do alvo
                alvo.receberDano(16);
                for (Inimigo outro : inimigos) {
                    if (outro != alvo && outro.estaVivo()
                            && outro.getPosicao().distanciaAte(alvo.getPosicao()) <= 1.5) {
                        outro.receberDano(5);
                    }
                }
                tiroAtual = alvo.getPosicao();
                recarga = 5;
                break;

            case "GELO":
                if (recarga > 0) {
                    recarga--;
                    break;
                }
                alvo = escolherAlvo(inimigos);
                if (alvo == null) {
                    break;
                }
                angulo = Math.atan2(alvo.getPosicao().z() - posicao.z(), alvo.getPosicao().x() - posicao.x());
                alvo.receberDano(3);
                alvo.desacelerar(12);
                tiroAtual = alvo.getPosicao();
                recarga = 2;
                break;

            case "LASER":
                if (recarga > 0) {
                    recarga--;
                    break;
                }
                alvo = escolherAlvo(inimigos);
                if (alvo == null) {
                    break;
                }
                angulo = Math.atan2(alvo.getPosicao().z() - posicao.z(), alvo.getPosicao().x() - posicao.x());
                alvo.receberDano(2);
                tiroAtual = alvo.getPosicao();
                recarga = 0;
                break;
        }
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

    public String getTipo() {
        return tipo;
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
