package br.com.fiap.sentinela;

public class AlertaSeguranca {

    private DispositivoMonitoramento sensorOrigem;
    private ZonaRestrita zonaAfetada;
    private String severidade;
    private String mensagem;

    public AlertaSeguranca(DispositivoMonitoramento sensorOrigem,
            ZonaRestrita zonaAfetada, String severidade,
            String mensagem) {

        if (sensorOrigem == null || zonaAfetada == null) {
            throw new IllegalArgumentException(
                    "O dispositivo e a zona sao obrigatorios.");
        }

        if (severidade == null || severidade.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "A severidade e obrigatoria.");
        }

        if (mensagem == null || mensagem.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "A mensagem e obrigatoria.");
        }

        this.sensorOrigem = sensorOrigem;
        this.zonaAfetada = zonaAfetada;
        this.severidade = severidade;
        this.mensagem = mensagem;
    }

    public DispositivoMonitoramento getSensorOrigem() {
        return sensorOrigem;
    }

    public ZonaRestrita getZonaAfetada() {
        return zonaAfetada;
    }

    public String getSeveridade() {
        return severidade;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void exibirAlerta() {
        System.out.println("\n------------------------------------");
        System.out.println("ALERTA DE SEGURANCA");
        System.out.println("Severidade: " + severidade);
        System.out.println("Dispositivo: "
                + sensorOrigem.getCodigoIdentificador());
        System.out.println("Localizacao: "
                + sensorOrigem.getLocalizacao());
        System.out.println("Zona afetada: "
                + zonaAfetada.getIdentificador()
                + " - " + zonaAfetada.getDescricao());
        System.out.println("Mensagem: " + mensagem);
        System.out.println("------------------------------------");
    }
}
