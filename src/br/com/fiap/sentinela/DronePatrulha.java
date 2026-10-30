package br.com.fiap.sentinela;

public class DronePatrulha extends DispositivoMonitoramento {

    private String rotaPatrulha;
    private double bateriaPercentual;
    private boolean emPatrulha;
    private boolean veiculoNaoIdentificadoDetectado;
    private ZonaRestrita zonaMonitorada;

    public DronePatrulha(String codigoIdentificador,
            String localizacao, String rotaPatrulha,
            double bateriaPercentual, ZonaRestrita zonaMonitorada) {

        super(codigoIdentificador, localizacao);

        if (rotaPatrulha == null || rotaPatrulha.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "A rota de patrulha e obrigatoria.");
        }

        if (Double.isNaN(bateriaPercentual)
                || Double.isInfinite(bateriaPercentual)
                || bateriaPercentual < 0
                || bateriaPercentual > 100) {
            throw new IllegalArgumentException(
                    "A bateria deve estar entre 0 e 100.");
        }

        if (zonaMonitorada == null) {
            throw new IllegalArgumentException(
                    "A zona monitorada e obrigatoria.");
        }

        this.rotaPatrulha = rotaPatrulha;
        this.bateriaPercentual = bateriaPercentual;
        this.zonaMonitorada = zonaMonitorada;
        this.emPatrulha = false;
        this.veiculoNaoIdentificadoDetectado = false;
    }

    public void iniciarPatrulha() {
        if (!isEmOperacao() || bateriaPercentual <= 10) {
            System.out.println(
                    "Drone indisponivel para iniciar a patrulha.");
            return;
        }

        emPatrulha = true;

        System.out.println("Drone " + getCodigoIdentificador()
                + " iniciou a patrulha pela rota "
                + rotaPatrulha + ".");
    }

    public void enviarParaInspecao() {
        if (!isEmOperacao() || bateriaPercentual <= 10) {
            System.out.println(
                    "Drone indisponivel para realizar a inspecao.");
            return;
        }

        emPatrulha = false;

        System.out.println("Drone " + getCodigoIdentificador()
                + " enviado para inspecionar a area.");
    }

    public void simularAnaliseCamera(boolean veiculoDetectado) {
        veiculoNaoIdentificadoDetectado = veiculoDetectado;

        if (veiculoDetectado) {
            System.out.println(
                    "Camera: veiculo nao identificado confirmado.");
        } else {
            System.out.println(
                    "Camera: nenhum veiculo suspeito identificado.");
        }
    }

    @Override
    public String analisarSituacao() {
        if (!isEmOperacao() || bateriaPercentual <= 10) {
            return "ATENCAO";
        }

        if (veiculoNaoIdentificadoDetectado) {
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
        if (veiculoNaoIdentificadoDetectado) {
            return "Camera confirmou um veiculo nao identificado "
                    + "na zona " + zonaMonitorada.getIdentificador()
                    + ".";
        }

        return "Drone indisponivel ou com situacao anormal.";
    }

    public String getRotaPatrulha() {
        return rotaPatrulha;
    }

    public double getBateriaPercentual() {
        return bateriaPercentual;
    }

    public boolean isEmPatrulha() {
        return emPatrulha;
    }

    public boolean isVeiculoNaoIdentificadoDetectado() {
        return veiculoNaoIdentificadoDetectado;
    }

    public ZonaRestrita getZonaMonitorada() {
        return zonaMonitorada;
    }
}
