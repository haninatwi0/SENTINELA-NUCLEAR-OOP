package br.com.fiap.sentinela;

public class CentralMonitoramento {

    private DispositivoMonitoramento[] dispositivos;
    private int quantidadeDispositivos;

    public CentralMonitoramento(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException(
                    "A capacidade deve ser maior que zero.");
        }

        dispositivos = new DispositivoMonitoramento[capacidade];
        quantidadeDispositivos = 0;
    }

    public void adicionarDispositivo(
            DispositivoMonitoramento dispositivo) {

        if (dispositivo == null) {
            System.out.println("Erro: dispositivo invalido.");
            return;
        }

        if (quantidadeDispositivos >= dispositivos.length) {
            System.out.println(
                    "Erro: capacidade de dispositivos atingida.");
            return;
        }

        dispositivos[quantidadeDispositivos] = dispositivo;
        quantidadeDispositivos++;

        System.out.println("Dispositivo "
                + dispositivo.getCodigoIdentificador()
                + " cadastrado na central.");
    }

    public void analisarLeituras() {
        boolean alertaGerado = false;

        System.out.println("\nAnalisando dispositivos...");

        for (int i = 0; i < quantidadeDispositivos; i++) {
            DispositivoMonitoramento dispositivo = dispositivos[i];

            String severidade = dispositivo.analisarSituacao();

            if (!severidade.equals("NORMAL")) {
                ZonaRestrita zonaAfetada =
                        dispositivo.getZonaAssociada();

                if (zonaAfetada != null) {
                    AlertaSeguranca alerta = new AlertaSeguranca(
                            dispositivo,
                            zonaAfetada,
                            severidade,
                            dispositivo.criarMensagemAlerta());

                    alerta.exibirAlerta();
                    alertaGerado = true;
                }
            }
        }

        if (!alertaGerado) {
            System.out.println(
                    "Nenhuma anomalia detectada. Sistema normal.");
        }
    }

    public int getQuantidadeDispositivos() {
        return quantidadeDispositivos;
    }
}