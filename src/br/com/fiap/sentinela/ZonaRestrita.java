package br.com.fiap.sentinela;

public class ZonaRestrita {

    private String identificador;
    private String descricao;
    private double nivelRadiacaoAtual;
    private double limiteRadiacao;

    public ZonaRestrita(String identificador, String descricao, double limiteRadiacao) {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("O identificador nao pode ser vazio.");
        }

        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descricao nao pode ser vazia.");
        }

        validarRadiacao(limiteRadiacao);
        if (limiteRadiacao == 0) {
            throw new IllegalArgumentException("O limite de radiacao deve ser maior que zero.");
        }
        this.identificador = identificador;
        this.descricao = descricao;
        this.limiteRadiacao = limiteRadiacao;
        this.nivelRadiacaoAtual = 0.2;
    }

    private void validarRadiacao(double valor) {
        if (Double.isNaN(valor) || Double.isInfinite(valor) || valor < 0 || valor > 1_000_000) {
            throw new IllegalArgumentException("Valor de radiacao invalido.");
        }
    }

    public void registrarLeituraRadiacao(double leitura) {
        if (Double.isNaN(leitura) || Double.isInfinite(leitura) || leitura < 0 || leitura > 1_000_000) {
            System.out.println("FALHA: leitura invalida. Valor anterior mantido.");
            return;
        }
        nivelRadiacaoAtual = leitura;
    }

    public String classificarRisco() {
        if (nivelRadiacaoAtual > limiteRadiacao) {
            return "CRITICO";
        }
        if (nivelRadiacaoAtual >= limiteRadiacao * 0.8) {
            return "ATENCAO";
        }
        return "NORMAL";
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getNivelRadiacaoAtual() {
        return nivelRadiacaoAtual;
    }

    public double getLimiteRadiacao() {
        return limiteRadiacao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            System.out.println("Erro: descricao invalida.");
            return;
        }
        this.descricao = descricao;
    }

    public void setLimiteRadiacao(double limiteRadiacao) {
        if (Double.isNaN(limiteRadiacao) || Double.isInfinite(limiteRadiacao) || limiteRadiacao <= 0 || limiteRadiacao > 1_000_000) {
            System.out.println("Erro: limite de radiacao invalido.");
            return;
        }
        this.limiteRadiacao = limiteRadiacao;
    }

    public void exibirInformacoes() {
        System.out.println("Zona: " + identificador);
        System.out.println("Descricao: " + descricao);
        System.out.printf("Radiacao atual: %.2f µSv/h%n",nivelRadiacaoAtual);
        System.out.printf("Limite de seguranca: %.2f µSv/h%n",limiteRadiacao);
        System.out.println("Classificacao: " + classificarRisco());
    }
}