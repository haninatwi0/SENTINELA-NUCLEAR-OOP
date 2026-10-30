package br.com.fiap.sentinela;

public class SensorRadiacao extends DispositivoMonitoramento {

    private double leituraAtual;
    private double limiteSeguranca;
    private ZonaRestrita zonaMonitorada;

    public SensorRadiacao(String codigoIdentificador,
            String localizacao, double limiteSeguranca,
            ZonaRestrita zonaMonitorada) {

        super(codigoIdentificador, localizacao);

        if (Double.isNaN(limiteSeguranca)
                || Double.isInfinite(limiteSeguranca)
                || limiteSeguranca <= 0
                || limiteSeguranca > 1_000_000) {
            throw new IllegalArgumentException(
                    "Limite de seguranca invalido.");
        }

        if (zonaMonitorada == null) {
            throw new IllegalArgumentException(
                    "A zona monitorada e obrigatoria.");
        }

        if (Double.compare(limiteSeguranca,
                zonaMonitorada.getLimiteRadiacao()) != 0) {
            throw new IllegalArgumentException(
                    "O limite do sensor deve ser igual ao da zona.");
        }

        this.limiteSeguranca = limiteSeguranca;
        this.zonaMonitorada = zonaMonitorada;
        this.leituraAtual = zonaMonitorada.getNivelRadiacaoAtual();
    }

    public void registrarLeitura(double leitura) {
        if (Double.isNaN(leitura) || Double.isInfinite(leitura)
                || leitura < 0 || leitura > 1_000_000) {
            System.out.println("FALHA: leitura invalida no sensor "
                    + getCodigoIdentificador()
                    + ". Valor anterior mantido.");
            return;
        }

        leituraAtual = leitura;
        zonaMonitorada.registrarLeituraRadiacao(leitura);

        System.out.printf(
                "Sensor %s: %.2f µSv/h | Zona: %s%n",
                getCodigoIdentificador(),
                leituraAtual,
                zonaMonitorada.getIdentificador());
    }

    @Override
    public String analisarSituacao() {
        if (!isEmOperacao()) {
            return "ATENCAO";
        }

        return zonaMonitorada.classificarRisco();
    }

    @Override
    public ZonaRestrita getZonaAssociada() {
        return zonaMonitorada;
    }

    @Override
    public String criarMensagemAlerta() {
        return "Leitura de radiacao: " + leituraAtual
                + " µSv/h. Classificacao: "
                + zonaMonitorada.classificarRisco() + ".";
    }

    public double getLeituraAtual() {
        return leituraAtual;
    }

    public double getLimiteSeguranca() {
        return limiteSeguranca;
    }

    public ZonaRestrita getZonaMonitorada() {
        return zonaMonitorada;
    }
}