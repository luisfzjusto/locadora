package org.example;

import org.example.dto.ClienteDTO;
import org.example.dto.EnderecoDTO;
import org.example.dto.VeiculoDTO;
import org.example.model.Reserva;
import org.example.usecase.ClienteService;
import org.example.usecase.ReservaService;
import org.example.usecase.VeiculoService;
import org.example.validator.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// A classe Main implementa um sistema interativo para gerenciar clientes, veículos, reservas e devoluções.
// Utiliza um loop para exibir um menu e capturar as escolhas do usuário, delegando as operações
// para serviços correspondentes (ClienteService, VeiculoService, ReservaService).

public class Main {
    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Define o formato para datas no padrão "dia/mês/ano".

        Scanner scanner = new Scanner(System.in); // Cria um objeto Scanner para leitura de dados do console
        ClienteService clienteService = new ClienteService(); // Cria uma instância de ClienteService para gerenciar clientes
        VeiculoService veiculoService = new VeiculoService(); // Cria uma instância de VeiculoService para gerenciar veiculos
        ReservaService reservaService = new ReservaService(); // Cria uma instância de ReservaService para gerenciar reservas

        while(true){ // Inicia um loop infinito até que a opção de sair seja selecionada
            System.out.println("SISTEMA DE GESTÃO DE LOJA");
            System.out.println("======================================");
            System.out.println("Escolha a opção desejada:");
            System.out.println("1 - Cadastro de clientes");
            System.out.println("2 - Consulta de clientes cadastrados");
            System.out.println("3 - Cadastro de veículos");
            System.out.println("4 - Relação de veículos da loja");
            System.out.println("5 - Consulta de veículo");
            System.out.println("6 - Reserva de veículo");
            System.out.println("7 - Devolução de veículo");
            System.out.println("8 - Consulta caixa da loja");
            System.out.println("9 - Sair");

            int opcao = scanner.nextInt(); // Lê a opção selecionada pelo usuário
            scanner.nextLine(); // Consumir uma linha

            System.out.println();

            switch(opcao){ // Estrutura switch para executar diferentes ações com base na opção selecionada
                case 1:
                    ClienteDTO clienteDTO = new ClienteDTO(); // Cria um novo objeto ClienteDTO para armazenar os dados do cliente

                    System.out.println("CADASTRO DE CLIENTES");

                    while(true) {
                        System.out.println("Nome:");
                        clienteDTO.setNome(scanner.nextLine());// Lê o nome do cliente e o armazena no DTO
                        if(NomeValidator.validarNome(clienteDTO.getNome())){
                            break; // nome é válido, portanto sai do loop
                        } else{
                            System.out.println("Nome inválido. Deve conter, no mínimo, 10 caracteres (apenas letras)");
                        }
                    }

                    while (true) {
                        System.out.println("CPF:");
                        String cpf = scanner.nextLine();

                        // Validação de formato de CPF
                        if (CPFValidator.validarCPF(cpf)) {
                            // Verifica se o CPF já está cadastrado
                            if (clienteService.buscarClientePorCPF(cpf) == null) {
                                clienteDTO.setCpf(cpf);
                                break;
                            } else {
                                System.out.println("CPF já cadastrado!");
                            }
                        } else {
                            System.out.println("CPF inválido! Por favor, tente novamente.");
                        }
                    }

                    while(true) {
                        System.out.println("Data de Nascimento (dd/mm/aaaa):");
                        String dataNascimentoString = scanner.nextLine();

                        if(DataNascimentoValidator.validarFormatoData(dataNascimentoString)){
                            LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatter);

                            if(DataNascimentoValidator.validarMaioridade(dataNascimento)){
                                clienteDTO.setDataNascimento(dataNascimento);
                                break;
                            } else {
                                System.out.println("Cliente deve ser maior de idade");
                            }
                        } else {
                            System.out.println("Formato de data inválido (DD/MM/AAAA)");
                        }
                    }

                    while(true){
                        System.out.println("Telefone:");
                        clienteDTO.setTelefone(scanner.nextLine()); // Lê o telefone do cliente e o armazena no DTO
                        if(TelefoneValidator.validarTelefone(clienteDTO.getTelefone())){
                            break;
                        } else {
                            System.out.println("Formato de telefone inválido ((00) 00000-0000)");
                        }
                    }

                    EnderecoDTO enderecoDTO = new EnderecoDTO(); // Cria um novo objeto EnderecoDTO para armazenar os dados do endereço

                    while(true) {
                        System.out.println("Logradouro:");
                        enderecoDTO.setLogradouro(scanner.nextLine()); // Lê o logradouro do endereço e armazena no DTO
                        if(LogradouroValidator.validarLogradouro(enderecoDTO.getLogradouro())){
                            break;
                        } else {
                            System.out.println("Logradouro inválido. Deve possuir, no mínimo, 4 caracteres");
                        }
                    }

                    while(true) {
                        System.out.println("Número:");
                        enderecoDTO.setNumero(scanner.nextLine()); // Lê o número do endereço e armazena no DTO
                        if(NumeroValidator.validarNumero(enderecoDTO.getNumero())){
                            break;
                        } else {
                            System.out.println("Número inválido. Não aceita letras e caracteres especiais");
                        }
                    }

                    while(true) {
                        System.out.println("Bairro:");
                        enderecoDTO.setBairro(scanner.nextLine()); // Lê o bairro do endereço e armazena no DTO
                        if(BairroValidator.validarBairro(enderecoDTO.getBairro())){
                            break;
                        } else {
                            System.out.println("Bairro inválido. Deve possuir, no mínimo, 5 caracteres");
                        }
                    }

                    while(true) {
                        System.out.println("Cidade:");
                        enderecoDTO.setCidade(scanner.nextLine()); // Lê a cidade do endereço e armazena no DTO
                        if(CidadeValidator.validarCidade(enderecoDTO.getCidade())){
                            break;
                        } else {
                            System.out.println("Cidade inválida. Deve possuir, no mínimo, 3 caracteres (letras)");
                        }
                    }

                    while(true) {
                        System.out.println("UF:");
                        String uf = scanner.nextLine();
                        if(UFValidator.validarUF(uf)) {
                            enderecoDTO.setUf(UFValidator.formatarUF(uf)); // Lê a UF do endereço e armazena no DTO
                            break;
                        } else {
                            System.out.println("UF inválida. Deve conter apenas 2 letras");
                        }
                    }

                    while(true) {
                        System.out.println("CEP:");
                        enderecoDTO.setCep(scanner.nextLine()); // Lê o CEP do endereço e armazena no DTO
                        if(CEPValidator.validarCEP(enderecoDTO.getCep())){
                            break;
                        } else {
                            System.out.println("Formato de CEP inválido (00000-000)");
                        }
                    }

                    clienteDTO.setEnderecoDTO(enderecoDTO); // Associa o EnderecoDTO ao ClienteDTO

                    try { // Chama o serviço de cliente para cadastrar o cliente no sistema
                        clienteService.cadastrarCliente(clienteDTO);
                        System.out.println("CLIENTE CADASTRADO COM SUCESSO!");
                    } catch (IllegalArgumentException e) { // Caso ocorra algum erro no cadastro, exibe a mensagem de erro
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.println("CONSULTA DE CLIENTES");
                    while(true) {
                        System.out.println("Informe o CPF:");
                        String cpf = scanner.nextLine(); // Lê o CPF do cliente a ser consultado

                        ClienteDTO clienteLocalizado = clienteService.buscarClientePorCPF(cpf); // Busca o cliente no sistema pelo CPF

                        if (clienteLocalizado != null) { // Se o cliente for encontrado, exibe seus dados
                            System.out.println("Nome: " + clienteLocalizado.getNome() + "\n" + "CPF: " + clienteLocalizado.getCpf() + "\n" + "Telefone: " + clienteLocalizado.getTelefone());
                            break;
                        } else { // Caso contrário, exibe uma mensagem informando que o cliente não foi localizado
                            System.out.println("CLIENTE NÃO LOCALIZADO!");
                        }
                    }
                    break;

                case 3:
                    System.out.println("CADASTRO DE VEÍCULOS");
                    VeiculoDTO veiculoDTO = new VeiculoDTO(); // Cria um novo objeto VeiculoDTO para armazenar os dados do veículo

                    while(true) {
                        System.out.println("Marca:");
                        veiculoDTO.setMarca(scanner.nextLine()); // Lê a marca do veículo e a armazena no DTO
                        if(MarcaValidator.validarMarca(veiculoDTO.getMarca())){
                            break;
                        } else {
                            System.out.println("Marca inválida. Deve conter, no mínimo, 3 letras");
                        }
                    }

                    while(true) {
                        System.out.println("Modelo:");
                        veiculoDTO.setModelo(scanner.nextLine()); // Lê o modelo do veículo e a armazena no DTO
                        if(ModeloValidator.validarModelo(veiculoDTO.getModelo())){
                            break;
                        } else {
                            System.out.println("Modelo inválido. Deve conter, no mínimo, 3 caracteres");
                        }
                    }

                    while(true) {
                        try {
                            System.out.println("Ano:");
                            veiculoDTO.setAno(scanner.nextInt()); // Lê o ano de fabricação do veículo e a armazena no DTO
                            if (AnoValidator.validarAno(String.valueOf(veiculoDTO.getAno()))) {
                                break;
                            } else {
                                System.out.println("Ano inválido. Deve conter 4 dígitos numéricos");
                            }
                        } catch (InputMismatchException e){
                            System.out.println("Ano inválido. Deve conter 4 dígitos numéricos");
                        }
                    }

                    scanner.nextLine(); // consome uma linha

                    while (true) {
                        System.out.println("Placa:");
                        String placa = scanner.nextLine();
                        if (PlacaValidator.validarPlaca(placa)) {
                            // Verifica se a placa já está cadastrada
                            if (veiculoService.buscarVeiculoPorPlaca(placa) == null) {
                                veiculoDTO.setPlaca(placa);
                                break;
                            } else {
                                System.out.println("Placa já cadastrada! Informe uma placa diferente.");
                            }
                        } else {
                            System.out.println("Placa inválida. Formato: AAA-0000 ou AAA-0A00.");
                        }
                    }

                    while(true) {
                        try{
                            System.out.println("Preço diário:");
                            veiculoDTO.setDiaria(scanner.nextDouble()); // // Lê o valor da diária do veículo e a armazena no DTO
                            if(DiariaValidator.validarDiaria(veiculoDTO.getDiaria())){
                                break;
                            } else {
                                System.out.println("Diária inválida. Deve ser numérico e positivo");
                            }
                        } catch (InputMismatchException e){
                            System.out.println("Diária inválida. Deve ser numérico e positivo");
                            scanner.nextLine(); // consome uma linha
                        }
                    }
                    scanner.nextLine(); // consome uma linha

                    while(true) {
                        System.out.println("Categoria:");
                        veiculoDTO.setCategoria(scanner.nextLine()); // Lê a categoria do veículo e a armazena no DTO
                        if(CategoriaValidator.validarCategoria(veiculoDTO.getCategoria())){
                            break;
                        } else {
                            System.out.println("Categoria inválida. Opções: POPULAR, SUPERIOR, UTILITARIO, PREMIUM ");
                        }
                    }

                    veiculoService.cadastrarVeiculo(veiculoDTO); // Chama o serviço de veículo para cadastrar o veículo no sistema
                    System.out.println("VEÍCULO CADASTRADO COM SUCESSO!");
                    break;

                case 4:
                    System.out.println("RELAÇÃO DE VEÍCULOS DA LOJA");
                    while (true) {
                        System.out.println("Escolha a opção desejada:");
                        System.out.println("1 - Veículos disponíveis");
                        System.out.println("2 - Veículos alugados");

                        // Tenta capturar a entrada do usuário
                        try {
                            String input = scanner.nextLine().trim(); // Lê a entrada e remove espaços em branco
                            if (input.isEmpty()) { // Verifica se o usuário não forneceu entrada
                                System.out.println("Opção inválida!");
                                continue; // Volta ao início do loop
                            }

                            int opcaoEstoque = Integer.parseInt(input); // Converte a entrada para int

                            if (opcaoEstoque == 1) { // lista os veículos disponíveis
                                System.out.println("RELAÇÃO DE VEÍCULOS DISPONÍVEIS PARA LOCAÇÃO:");
                                // Cria uma lista para armazenar e chama o serviço de veículo para listar os veículos disponíveis
                                List<VeiculoDTO> veiculosDisponiveis = veiculoService.listarVeiculosDisponiveis();
                                if (veiculosDisponiveis.isEmpty()) {
                                    System.out.println("Não há veículos disponíveis para locação");
                                } else {
                                    for (VeiculoDTO veiculo : veiculosDisponiveis) { // Itera pela lista de veículos disponíveis e exibe suas informações
                                        System.out.println("Marca: " + veiculo.getMarca() + "\n"
                                                + "Modelo: " + veiculo.getModelo() + "\n"
                                                + "Ano: " + veiculo.getAno() + "\n"
                                                + "Placa: " + veiculo.getPlaca() + "\n"
                                                + "Categoria: " + veiculo.getCategoria());
                                    }
                                }
                                break; // volta ao menu principal
                            } else if (opcaoEstoque == 2) { // lista os veículos alugados
                                System.out.println("RELAÇÃO DE VEÍCULOS LOCADOS:");
                                // Cria uma lista para armazenar e chama o serviço de veículo para listar os veículos alugados
                                List<VeiculoDTO> veiculosAlugados = veiculoService.listarVeiculosAlugados();
                                if (veiculosAlugados.isEmpty()) {
                                    System.out.println("Não há veículos alugados no momento.");
                                } else {
                                    for (VeiculoDTO veiculo : veiculosAlugados) { // Itera pela lista de veículos alugados e exibe suas informações
                                        System.out.println("Marca: " + veiculo.getMarca() + "\n"
                                                + "Modelo: " + veiculo.getModelo() + "\n"
                                                + "Ano: " + veiculo.getAno() + "\n"
                                                + "Placa: " + veiculo.getPlaca() + "\n"
                                                + "Categoria: " + veiculo.getCategoria());
                                    }
                                }
                                break; // Após exibir os veículos, volta ao menu principal
                            } else { // Se for selecionada uma opção inválida
                                System.out.println("Opção inválida!");
                            }
                        } catch (NumberFormatException e) { // Captura erro se a entrada não for um número
                            System.out.println("Opção inválida!");
                        }
                    }
                    break; // volta ao menu principal



                case 5:
                    System.out.println("CONSULTA DE VEÍCULOS");
                    while(true) {
                        System.out.println("Informe a placa:");
                        String placa = scanner.nextLine(); // Lê a placa do veículo a ser consultado
                        VeiculoDTO veiculoLocalizado = veiculoService.buscarVeiculoPorPlaca(placa); // Chama o serviço de veículo para buscar o veículo pela placa.
                        if (veiculoLocalizado != null) { // Se o veículo for encontrado, exibe suas informações
                            System.out.println("Marca: " + veiculoLocalizado.getMarca() + "\n" + "Modelo: " + veiculoLocalizado.getModelo() + "\n" + "Ano: " + veiculoLocalizado.getAno() + "\n" + "Placa: " + veiculoLocalizado.getPlaca() + "\n" + "Categoria: " + veiculoLocalizado.getCategoria() + "\n" + "Status: " + veiculoLocalizado.getStatus());
                            break;
                        } else { // Caso contrário, exibe uma mensagem informando que o veículo não foi localizado
                            System.out.println("VEÍCULO NÃO ENCONTRADO!");
                        }
                    }
                    break;

                case 6:
                    System.out.println("RESERVA DE VEÍCULOS");

                    // Loop para obter o CPF do cliente
                    ClienteDTO cliente = null;
                    while (true) {
                        System.out.println("Informe o CPF do cliente:");
                        String cpfCliente = scanner.nextLine(); // Lê o CPF do cliente que deseja fazer a reserva
                        cliente = clienteService.buscarClientePorCPF(cpfCliente); // Chama o serviço que busca o cliente no sistema pelo CPF
                        if (cliente != null) { // Se o cliente for localizado
                            break; // Sai do loop
                        } else {
                            System.out.println("Cliente não localizado!");
                        }
                    }

                    // Loop para obter a placa do veículo
                    VeiculoDTO veiculo = null;
                    while (true) {
                        System.out.println("Informe a placa do veículo:");
                        String placaVeiculo = scanner.nextLine(); // Lê a placa do veículo
                        veiculo = veiculoService.buscarVeiculoPorPlaca(placaVeiculo); // Busca o veículo no sistema pelo serviço
                        if (veiculo != null) { // Se o veículo for localizado
                            break; // Sai do loop
                        } else {
                            System.out.println("Veículo não localizado!");
                        }
                    }

                    // Loop para obter o tipo de reserva
                    String tipoReserva = "";
                    while (true) {
                        System.out.println("Informe o tipo de reserva (diaria || mensal || anual):");
                        tipoReserva = scanner.nextLine().trim().toLowerCase(); // Lê o tipo de reserva

                        if (tipoReserva.equals("diaria") || tipoReserva.equals("mensal") || tipoReserva.equals("anual")) {
                            // Entrada válida, continua para a quantidade
                            break;
                        } else {
                            System.out.println("Tipo de reserva inválido! Opções: diaria, mensal ou anual.");
                        }
                    }

                    // Loop para obter a quantidade com tratamento de exceção
                    int quantidade = 0;
                    while (true) {
                        try {
                            System.out.println("Informe a quantidade (dias || meses || anos):");
                            String entradaQuantidade = scanner.nextLine(); // Lê a entrada como String
                            if (entradaQuantidade.isEmpty()) {
                                throw new InputMismatchException(); // Força a exceção se a entrada for vazia
                            }
                            quantidade = Integer.parseInt(entradaQuantidade); // Converte a entrada para int
                            if (quantidade > 0) {
                                break; // Sai do loop se a entrada for válida
                            } else {
                                System.out.println("Diária deve ser um valor numérico positivo!");
                            }
                        } catch (NumberFormatException | InputMismatchException e) {
                            System.out.println("Diária deve ser um valor numérico positivo!");
                        }
                    }

                    // Chama o serviço de reserva para realizar a reserva
                    reservaService.realizarReserva(cliente, veiculo, tipoReserva, quantidade);
                    System.out.println("RESERVA REALIZADA COM SUCESSO!");
                    break;


                case 7:
                    System.out.println("DEVOLUÇÃO DE VEÍCULOS");
                    System.out.println("Informe a placa do veículo");
                    String placaDevolucao = scanner.nextLine(); // Lê a placa do veículo a ser devolvido
                    reservaService.devolverVeiculo(placaDevolucao); // Chama o serviço de reserva para devolver o veículo
                    break;

                case 8:
                    double saldo = reservaService.consultarSaldo(); // Chama o serviço de reserva para consultar o saldo da loja
                    System.out.println("Saldo em caixa da loja: R$ " + saldo);
                    break;

                case 9:
                    System.out.println("------------------");
                    System.out.println("PROGRAMA ENCERRADO");
                    System.out.println("------------------");
                    return;

                default: // Exibe uma mensagem de erro se for selecionada uma opção inválida
                    System.out.println("OPÇÃO INVÁLIDA");
            }
        }
    }
}