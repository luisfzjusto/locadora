package org.example;

import org.example.dto.ClienteDTO;
import org.example.dto.EnderecoDTO;
import org.example.dto.VeiculoDTO;
import org.example.model.Reserva;
import org.example.usecase.ClienteService;
import org.example.usecase.ReservaService;
import org.example.usecase.VeiculoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

                    System.out.println("Nome:");
                    clienteDTO.setNome(scanner.nextLine()); // Lê o nome do cliente e o armazena no DTO

                    System.out.println("CPF:");
                    clienteDTO.setCpf(scanner.nextLine()); // Lê o cpf do cliente e o armazena no DTO

                    System.out.println("Data de Nascimento (dd/mm/aaaa):");
                    try { // Lê e formata a data de nascimento, armazenando-a no DTO
                        String dataNascimento = scanner.nextLine();
                        clienteDTO.setDataNascimento(LocalDate.parse(dataNascimento, formatter));
                    } catch (DateTimeParseException e) { // Caso a data seja inválida, exibe uma mensagem de erro e sai deste case
                        System.out.println("Data de nascimento inválida! Use o formato DD/MM/AAAA.");
                        break;
                    }

                    System.out.println("Telefone:");
                    clienteDTO.setTelefone(scanner.nextLine()); // Lê o telefone do cliente e o armazena no DTO

                    EnderecoDTO enderecoDTO = new EnderecoDTO(); // Cria um novo objeto EnderecoDTO para armazenar os dados do endereço

                    System.out.println("Logradouro:");
                    enderecoDTO.setLogradouro(scanner.nextLine()); // Lê o logradouro do endereço e armazena no DTO

                    System.out.println("Número:");
                    enderecoDTO.setNumero(scanner.nextLine()); // Lê o número do endereço e armazena no DTO

                    System.out.println("Bairro:");
                    enderecoDTO.setBairro(scanner.nextLine()); // Lê o bairro do endereço e armazena no DTO

                    System.out.println("Cidade:");
                    enderecoDTO.setCidade(scanner.nextLine()); // Lê a cidade do endereço e armazena no DTO

                    System.out.println("UF:");
                    enderecoDTO.setUf(scanner.nextLine()); // Lê a UF do endereço e armazena no DTO

                    System.out.println("CEP:");
                    enderecoDTO.setCep(scanner.nextLine()); // Lê o CEP do endereço e armazena no DTO

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
                    System.out.println("Informe o CPF:");
                    String cpf = scanner.nextLine(); // Lê o CPF do cliente a ser consultado

                    ClienteDTO clienteLocalizado = clienteService.buscarClientePorCPF(cpf); // Busca o cliente no sistema pelo CPF

                    if(clienteLocalizado != null){ // Se o cliente for encontrado, exibe seus dados
                        System.out.println("Nome: " + clienteLocalizado.getNome() + "\n" + "CPF: " + clienteLocalizado.getCpf() + "\n" + "Telefone: " + clienteLocalizado.getTelefone());
                    } else{ // Caso contrário, exibe uma mensagem informando que o cliente não foi localizado
                        System.out.println("CLIENTE NÃO LOCALIZADO!");
                    }
                    break;

                case 3:
                    System.out.println("CADASTRO DE VEÍCULOS");
                    VeiculoDTO veiculoDTO = new VeiculoDTO(); // Cria um novo objeto VeiculoDTO para armazenar os dados do veículo

                    System.out.println("Marca:");
                    veiculoDTO.setMarca(scanner.nextLine()); // Lê a marca do veículo e a armazena no DTO

                    System.out.println("Modelo:");
                    veiculoDTO.setModelo(scanner.nextLine()); // Lê o modelo do veículo e a armazena no DTO

                    System.out.println("Ano:");
                    veiculoDTO.setAno(scanner.nextInt()); // Lê o ano de fabricação do veículo e a armazena no DTO
                    scanner.nextLine(); // consome uma linha

                    System.out.println("Placa:");
                    veiculoDTO.setPlaca(scanner.nextLine()); // Lê a placa do veículo e a armazena no DTO

                    System.out.println("Preço diário:");
                    veiculoDTO.setDiaria(scanner.nextDouble()); // // Lê o valor da diária do veículo e a armazena no DTO
                    scanner.nextLine(); // consome uma linha

                    System.out.println("Categoria:"); // Lê a categoria do veículo e a armazena no DTO
                    veiculoDTO.setCategoria(scanner.nextLine());

                    veiculoService.cadastrarVeiculo(veiculoDTO); // Chama o serviço de veículo para cadastrar o veículo no sistema
                    System.out.println("VEÍCULO CADASTRADO COM SUCESSO!");
                    break;

                case 4:
                    System.out.println("RELAÇÃO DE VEÍCULOS DA LOJA");
                    System.out.println("Escolha a opção desejada:");
                    System.out.println("1 - Veículos disponíveis");
                    System.out.println("2 - Veículos alugados");

                    int opcaoEstoque = scanner.nextInt(); // Lê a opção de listagem de veículos (disponíveis ou alugados)
                    scanner.nextLine();

                    if(opcaoEstoque == 1){ // lista os veículos disponíveis
                        System.out.println("RELAÇÃO DE VEÍCULOS DISPONÍVEIS PARA LOCAÇÃO:");
                        // Cria uma lista para armazenar e chama o serviço de veículo para listar os veículos disponíveis
                        List<VeiculoDTO> veiculosDisponiveis = veiculoService.listarVeiculosDisponiveis();
                        for (VeiculoDTO veiculo : veiculosDisponiveis){ // Itera pela lista de veículos disponíveis e exibe suas informações
                            System.out.println("Marca: " + veiculo.getMarca() + "\n" + "Modelo: " + veiculo.getModelo() + "\n" + "Ano: " + veiculo.getAno() + "\n" + "Placa: " + veiculo.getPlaca() + "\n" + "Categoria: " + veiculo.getCategoria());
                        }
                    } else if (opcaoEstoque == 2){ // lista os veículos alugados
                        System.out.println("RELAÇÃO DE VEÍCULOS LOCADOS:");
                        // Cria uma lista para armazenar e chama o serviço de veículo para listar os veículos alugados
                        List<VeiculoDTO> veiculosAlugados = veiculoService.listarVeiculosAlugados();
                        for (VeiculoDTO veiculo : veiculosAlugados){ // Itera pela lista de veículos alugados e exibe suas informações
                            System.out.println("Marca: " + veiculo.getMarca() + "\n" + "Modelo: " + veiculo.getModelo() + "\n" + "Ano: " + veiculo.getAno() + "\n" + "Placa: " + veiculo.getPlaca() + "\n" + "Categoria: " + veiculo.getCategoria());
                        }
                    } else { // Se for selecionada uma opção inválida, exibe uma mensagem de erro
                        System.out.println("Opção inválida!");
                    }
                    break;

                case 5:
                    System.out.println("CONSULTA DE VEÍCULOS");
                    System.out.println("Informe a placa:");
                    String placa = scanner.nextLine(); // Lê a placa do veículo a ser consultado
                    VeiculoDTO veiculoLocalizado = veiculoService.buscarVeiculoPorPlaca(placa); // Chama o serviço de veículo para buscar o veículo pela placa.
                    if(veiculoLocalizado != null){ // Se o veículo for encontrado, exibe suas informações
                        System.out.println("Marca: " + veiculoLocalizado.getMarca() + "\n" + "Modelo: " + veiculoLocalizado.getModelo() + "\n" + "Ano: " + veiculoLocalizado.getAno() + "\n" + "Placa: " + veiculoLocalizado.getPlaca() + "\n" + "Categoria: " + veiculoLocalizado.getCategoria() + "\n" + "Status: " + veiculoLocalizado.getStatus());
                    } else { // Caso contrário, exibe uma mensagem informando que o veículo não foi localizado
                        System.out.println("VEÍCULO NÃO ENCONTRADO!");
                    }
                    break;

                case 6:
                    System.out.println("RESERVA DE VEÍCULOS");
                    System.out.println("Informe o cpf do cliente:");
                    String cpfCliente = scanner.nextLine(); // Lê o CPF do cliente que deseja fazer a reserva
                    ClienteDTO cliente = clienteService.buscarClientePorCPF(cpfCliente); // Chama o serviço que busca o cliente no sistema pelo CPF
                    if(cliente == null){ // Se o cliente não for localizado, exibe uma mensagem de erro e sai deste case
                        System.out.println("Cliente não localizado");
                        break;
                    }

                    System.out.println("Informe a placa do veículo:");
                    String placaVeiculo = scanner.nextLine(); // Lê a placa do veículo que o cliente deseja reservar
                    VeiculoDTO veiculo = veiculoService.buscarVeiculoPorPlaca(placaVeiculo); // Chama o serviço que busca o veículo no sistema pela placa
                    if(veiculo == null){ // Se o veículo não for localizado, exibe uma mensagem de erro e sai deste case
                        System.out.println("Veículo não localizado");
                        break;
                    }

                    System.out.println("Informe o tipo de reserva (diaria || mensal || anual):");
                    String tipoReserva = scanner.nextLine(); // Lê o tipo de reserva (diária, mensal ou anual)
                    System.out.println("Informe a quantidade (dias || meses || anos):");
                    int quantidade = scanner.nextInt(); // Lê a quantidade de dias, meses ou anos para a reserva
                    scanner. nextLine(); // Consome a linha

                    reservaService.realizarReserva(cliente, veiculo, tipoReserva, quantidade); // Chama o serviço de reserva para realizar a reserva
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