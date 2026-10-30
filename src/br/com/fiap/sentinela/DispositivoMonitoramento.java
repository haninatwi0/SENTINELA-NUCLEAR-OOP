package br.com.fiap.sentinela;

public class DispositivoMonitoramento {

    private String codigoIdentificador;
    private String localizacao;
    private boolean emOperacao;

    public DispositivoMonitoramento(String codigoIdentificador,String localizacao) {
        if (codigoIdentificador == null
                || codigoIdentificador.trim().isEmpty()) {
            throw new IllegalArgumentException("O codigo identificador e obrigatorio.");
        }

        if (localizacao == null || localizacao.trim().isEmpty()) {
            throw new IllegalArgumentException("A localizacao e obrigatoria.");
        }

        this.codigoIdentificador = codigoIdentificador;
        this.localizacao = localizacao;
        this.emOperacao = true;
    }

    public String analisarSituacao() {
        if (!emOperacao) {
            return "ATENCAO";
        }

        return "NORMAL";
    }

    public ZonaRestrita getZonaAssociada() {
        return null;
    }

    public String criarMensagemAlerta() {
        return "Dispositivo indisponivel ou em situacao anormal.";
    }

    public String getCodigoIdentificador() {
        return codigoIdentificador;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public boolean isEmOperacao() {
        return emOperacao;
    }

    public void setLocalizacao(String localizacao) {
        if (localizacao == null || localizacao.trim().isEmpty()) {
            System.out.println("Erro: localizacao invalida.");
            return;
        }
        this.localizacao = localizacao;
    }

    public void setEmOperacao(boolean emOperacao) {
        this.emOperacao = emOperacao;
    }

    public void exibirInformacoes() {
        System.out.println("Codigo: " + codigoIdentificador);
        System.out.println("Localizacao: " + localizacao);
        System.out.println("Em operacao: "+ (emOperacao ? "SIM" : "NAO"));
    }
}