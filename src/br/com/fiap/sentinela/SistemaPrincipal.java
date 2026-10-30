package br.com.fiap.sentinela;

public class SistemaPrincipal {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("          SENTINELA NUCLEAR");
        System.out.println(" Sistema de Monitoramento e Seguranca");
        System.out.println("========================================");

        // Criacao das zonas
        ZonaRestrita casaReator = new ZonaRestrita("ZNR-01", "Casa do Reator", 3.0);

        ZonaRestrita patioCombustivel = new ZonaRestrita("ZNR-02","Patio de Armazenamento de Combustivel Irradiado", 5.0);

        // Criacao dos dispositivos
        SensorRadiacao sensorReator = new SensorRadiacao("RAD-01", "Casa do Reator", 3.0, casaReator);

        SensorRadiacao sensorPatio = new SensorRadiacao("RAD-02", "Patio de Combustivel", 5.0, patioCombustivel);

        SensorPerimetral sensorPerimetro = new SensorPerimetral("PER-01", "Cerca Norte", "Setor Norte", casaReator);

        DronePatrulha drone = new DronePatrulha("DRN-01", "Base de Drones", "Perimetro Norte e Leste", 95.0, casaReator);

        // Cadastro na central
        CentralMonitoramento central = new CentralMonitoramento(4);

        central.adicionarDispositivo(sensorReator);
        central.adicionarDispositivo(sensorPatio);
        central.adicionarDispositivo(sensorPerimetro);
        central.adicionarDispositivo(drone);

        // ========================================
        // CENARIO 1: ROTINA NORMAL
        // ========================================
        System.out.println("\n========================================");
        System.out.println("CENARIO 1 - ROTINA NORMAL");
        System.out.println("========================================");

        sensorReator.registrarLeitura(0.2);
        sensorPatio.registrarLeitura(0.3);
        sensorPerimetro.registrarViolacao(false);

        drone.iniciarPatrulha();
        drone.simularAnaliseCamera(false);

        casaReator.exibirInformacoes();
        patioCombustivel.exibirInformacoes();

        central.analisarLeituras();

        // ========================================
        // CENARIO 2: PICO DE RADIACAO
        // ========================================
        System.out.println("\n========================================");
        System.out.println("CENARIO 2 - PICO DE RADIACAO");
        System.out.println("========================================");

        sensorReator.registrarLeitura(4.7);

        casaReator.exibirInformacoes();

        central.analisarLeituras();

        // ========================================
        // CENARIO 3: ACESSO INDEVIDO
        // ========================================
        System.out.println("\n========================================");
        System.out.println("CENARIO 3 - ACESSO INDEVIDO");
        System.out.println("========================================");

        // Restauracao da leitura normal
        sensorReator.registrarLeitura(0.2);

        // Deteccao de violacao do perimetro
        sensorPerimetro.registrarViolacao(true);

        // Envio do drone para inspecao
        drone.enviarParaInspecao();

        // Confirmacao simulada pela camera
        drone.simularAnaliseCamera(true);

        central.analisarLeituras();

        System.out.println("\n========================================");
        System.out.println("Monitoramento finalizado.");
        System.out.println("========================================");
    }
}