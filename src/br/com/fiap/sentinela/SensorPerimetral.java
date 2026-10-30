package br.com.fiap.sentinela;

public class SensorPerimetral extends DispositivoMonitoramento {

    private String setorVigiado;
    private boolean violacaoDetectada;
    private ZonaRestrita zonaMonitorada;

    public SensorPerimetral(String codigoIdentificador,
            String localizacao, String setorVigiado,
            ZonaRestrita zonaMonitorada) {

        super(codigoIdentificador, localizacao);

        if (setorVigiado == null || setorVigiado.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O setor vigiado e obrigatorio.");
        }

        if (zonaMonitorada == null) {
            throw new IllegalArgumentException(
                    "A zona monitorada e obrigatoria.");
        }

        this.setorVigiado = setorVigiado;
        this.zonaMonitorada = zonaMonitorada;
        this.violacaoDetectada = false;
    }

    public void registrarViolacao(boolean violacao) {
        violacaoDetectada = violacao;

        if (violacao) {
            System.out.println("VIOLACAO detectada no setor "
                    + setorVigiado + ".");
        } else {
            System.out.println("Perimetro integro no setor "
                    + setorVigiado + ".");
        }
    }

    @Override
    public String analisarSituacao() {
        if (!isEmOperacao()) {
            return "ATENCAO";
        }

        if (violacaoDetectada) {
            return "CRITICO";
        }

        return "NORMAL";
    }

    @Override
    public ZonaRestrita getZonaAssociada() {
        return zonaMonitorada;
    }

    @Override
    public String criarMensagemAlerta() {
        return "Violacao perimetral detectada no setor "
                + setorVigiado + ". Verificacao necessaria.";
    }

    public String getSetorVigiado() {
        return setorVigiado;
    }

    public boolean isViolacaoDetectada() {
        return violacaoDetectada;
    }

    public ZonaRestrita getZonaMonitorada() {
        return zonaMonitorada;
    }
}