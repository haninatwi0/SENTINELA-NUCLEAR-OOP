# SENTINELA NUCLEAR — Sprint 1

## Informações dos Integrantes

| Nome | RM |
|---|---|
| Hanin Atwi | 567626 |
| Gabriel Guimarães de Oliveira | 567835 |
| Pedro Paulo Ferreira Agnelo D’angelo | 567564 |
| Laura Stephanie Vasquez Oliveira | 567277 |
| Mark Lima Leal | 566760 |

- **Turma:** 2CCPB
- **Curso:** Ciência da Computação
- **Instituição:** FIAP

---

## 1. Descrição do Projeto

O **Sentinela Nuclear** é um protótipo desenvolvido em Java que simula o monitoramento e a segurança física de uma instalação nuclear fictícia.

O sistema utiliza dispositivos de monitoramento, sensores de radiação, sensores perimetrais e drones de patrulha para identificar situações anormais e gerar alertas de segurança.

O objetivo da Sprint 1 é modelar as zonas restritas, os dispositivos de monitoramento e a central responsável por analisar as leituras e emitir alertas classificados por severidade.

## 2. Tecnologias Utilizadas

- Java
- Programação Orientada a Objetos (POO)
- Encapsulamento
- Herança
- Polimorfismo
- Associação entre objetos
- Arrays

## 3. Estrutura das Classes

| Classe | Responsabilidade |
|---|---|
| `ZonaRestrita` | Representa uma zona da instalação e controla os níveis de radiação. |
| `DispositivoMonitoramento` | Superclasse dos dispositivos de monitoramento. |
| `SensorRadiacao` | Registra e monitora os níveis de radiação. |
| `SensorPerimetral` | Monitora o perímetro e identifica violações. |
| `DronePatrulha` | Simula o patrulhamento, a inspeção e a análise por câmera. |
| `AlertaSeguranca` | Armazena informações sobre os alertas gerados. |
| `CentralMonitoramento` | Gerencia os dispositivos, analisa as situações e gera alertas. |
| `SistemaPrincipal` | Instancia os objetos e executa os três cenários obrigatórios. |

## 4. Diagrama UML

O diagrama UML apresenta a estrutura do sistema Sentinela Nuclear, incluindo as classes, seus atributos, métodos e relacionamentos.

O diagrama representa a herança entre `DispositivoMonitoramento` e suas subclasses, além das associações entre os dispositivos, as zonas restritas e os alertas de segurança.

<img width="3444" height="1862" alt="Class Diagram0" src="https://github.com/user-attachments/assets/07a13502-386d-44af-820c-a4d2a6b52e03" />



## 5. Conceitos de Programação Orientada a Objetos

- **Classe e objeto:** utilização da classe `ZonaRestrita` para representar as zonas da instalação.
- **Encapsulamento:** atributos privados e métodos públicos para proteger os dados.
- **Construtores:** criação de objetos com informações obrigatórias e validações.
- **Associação (TEM-UM):** `AlertaSeguranca` possui referências ao dispositivo de origem e à zona afetada.
- **Herança (É-UM):** as classes `SensorRadiacao`, `SensorPerimetral` e `DronePatrulha` herdam de `DispositivoMonitoramento`.
- **Polimorfismo:** sobrescrita de métodos com `@Override` para implementar comportamentos específicos de cada dispositivo.
- **Arrays:** a central utiliza `DispositivoMonitoramento[]` para armazenar e analisar diferentes dispositivos.

## 6. Cenários Obrigatórios

### Cenário 1 — Rotina Normal

Os sensores registram níveis de radiação dentro dos limites de segurança. O perímetro permanece íntegro e o drone realiza sua patrulha sem identificar situações suspeitas.

**Resultado esperado:** a central não identifica situações anormais e não gera alertas.

### Cenário 2 — Pico de Radiação

Um sensor registra um nível de radiação acima do limite de segurança estabelecido para a zona monitorada.

**Resultado esperado:** a central identifica a situação e gera um alerta crítico.

### Cenário 3 — Acesso Indevido

O sensor perimetral detecta uma violação da cerca. O drone é enviado para realizar uma inspeção e sua câmera simula a identificação de um veículo não identificado.

**Resultado esperado:** a central identifica a ocorrência e gera os alertas correspondentes.

## 7. Perguntas de Reflexão

### 1. Por que `ZonaRestrita` é uma classe e "ZNR-01 - Casa do Reator" é um objeto?

**Resposta:**

`ZonaRestrita` é uma classe porque funciona como um modelo que define as características e os comportamentos de uma zona restrita. Já "ZNR-01 - Casa do Reator" é um objeto porque representa uma instância específica dessa classe, com seus próprios valores de identificação, descrição e nível de radiação.

### 2. Por que a regra que classifica o risco deve morar dentro de `ZonaRestrita`, e não espalhada em `ifs` do `main`?

**Resposta:**

Porque a classificação de risco é uma responsabilidade da própria zona. Colocar essa regra dentro da classe `ZonaRestrita` mantém o código organizado, facilita a manutenção e evita repetir as mesmas condições em diferentes partes do programa. O `main` deve apenas executar o cenário, e não concentrar as regras de negócio.

### 3. Se o `nivelRadiacaoAtual` fosse público, que tipo de fraude um programador descuidado (ou malicioso) poderia causar no relatório da central de monitoramento?

**Resposta:**

Um programador poderia alterar diretamente o nível de radiação sem passar pelas validações do sistema. Dessa forma, poderia registrar um valor menor que o real, esconder uma situação de risco ou modificar os dados dos relatórios. O encapsulamento protege essas informações e garante que as alterações sejam feitas por métodos controlados.

### 4. Por que o desaparecimento do construtor padrão, assim que você define um customizado, protege o cadastro de zonas da instalação?

**Resposta:**

Porque, ao criar um construtor personalizado com os parâmetros obrigatórios, o Java não disponibiliza automaticamente o construtor padrão sem argumentos. Assim, não é possível criar uma zona sem informar sua identificação, descrição e limite de radiação. Isso ajuda a garantir que os objetos sejam criados com os dados necessários.

### 5. Se o alerta guardasse apenas a String com o código do sensor, o que a central perderia na hora de escalonar o incidente e precisar consultar a localização e a zona afetada depois?

**Resposta:**

A central perderia o acesso direto às informações completas do sensor e da zona afetada. Armazenando referências aos objetos, o alerta consegue acessar informações como localização, identificação e zona relacionada ao incidente. Isso facilita a consulta e o gerenciamento dos alertas.

### 6. Se a mãe tem `codigoIdentificador` como `private`, por que a filha é obrigada a passar esse dado pelo `super()` em vez de simplesmente fazer `this.codigoIdentificador = "..."`? Que princípio das aulas passadas está sendo protegido?

**Resposta:**

Porque o atributo `codigoIdentificador` é privado e só pode ser acessado diretamente dentro da própria classe `DispositivoMonitoramento`. A classe filha não pode atribuir um valor diretamente a esse atributo usando `this`. Por isso, utiliza `super()` para chamar o construtor da classe mãe e inicializar o identificador corretamente. Esse mecanismo protege o princípio do encapsulamento.

## 8. Conclusão

O desenvolvimento do Sentinela Nuclear permitiu aplicar conceitos de Programação Orientada a Objetos em uma simulação de monitoramento de uma instalação nuclear.

Através da criação de classes, objetos, construtores, associações, herança e polimorfismo, foi possível organizar os dispositivos e centralizar a análise das situações detectadas.

O protótipo demonstra os três cenários obrigatórios da Sprint 1 e a geração de alertas de segurança conforme as situações identificadas.

---

**FIAP — Ciência da Computação**  
**Challenge Sprint 1 — 2CCP**
