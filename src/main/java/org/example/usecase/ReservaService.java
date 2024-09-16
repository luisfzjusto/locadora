package org.example.usecase;

import org.example.dao.ClienteDAO;
import org.example.dao.ReservaDAO;
import org.example.dao.VeiculoDAO;
import org.example.dto.ClienteDTO;
import org.example.dto.VeiculoDTO;
import org.example.model.Cliente;
import org.example.model.ContaLocadora;
import org.example.model.Reserva;
import org.example.model.Veiculo;
import org.example.strategy.AnualStrategy;
import org.example.strategy.DiariaStrategy;
import org.example.strategy.MensalStrategy;
import org.example.strategy.PricingStrategy;

import java.util.Scanner;

// Classe responsável por gerenciar a lógica de negócio relacionada às reservas  (reservar e devolver)

public class ReservaService {
    // Declaração das variáveis privadas que serão usadas para acessar DAO e gerenciar dados de reserva, cliente e veiculo
    private ReservaDAO reservaDAO;
    private ContaLocadora contaLocadora;
    private VeiculoDAO veiculoDAO;
    private ClienteDAO clienteDAO;

    // Construtor que inicializa os objetos DAO, bem como ContaLocadora
    public ReservaService() {
        this.reservaDAO = new ReservaDAO(); // cria uma instância de ReservaDAO para gerenciar reservas
        this.clienteDAO = new ClienteDAO(); // cria uma instância de ClienteDAO para gerenciar clientes
        this.contaLocadora = new ContaLocadora(); // cria uma instância de ContaLocadora para gerenciar o saldo da locadora
        this.veiculoDAO = new VeiculoDAO(); // // cria uma instância de VeiculoDAO para gerenciar veiculos
    }

    // Método que realiza a reserva de um veículo. Se inicia com a validação de cliente e veiculo e, posteriormente,
    // a seleção de uma estratégia de precificação e o cálculo do valor total da reserva
    public void realizarReserva(ClienteDTO clienteDTO, VeiculoDTO veiculoDTO, String tipoReserva, int quantidade) {

        Cliente cliente = clienteDAO.buscarClientePorCPF(clienteDTO.getCpf()); // busca o cliente no BD a partir do CPF

        if(cliente == null){ // se o cliente não for encontrado, exibe uma mensagem e retorna
            System.out.println("Cliente não encontrado!");
            return;
        }

        Veiculo veiculo = veiculoDAO.buscarVeiculoPorPlaca(veiculoDTO.getPlaca()); // busca o veiculo no BD a partir da placa

        if(veiculo == null){ // se a o veiculo não for encontrado, exibe uma mensagem e retorna
            System.out.println("Veículo não encontrado!");
            return;
        }

        if ("alugado".equalsIgnoreCase(veiculo.getStatus())) { // se o veiculo já estiver alugado, exibe uma mensagem e retorna
            System.out.println("Veículo indisponível.");
            return;
        }

        PricingStrategy pricingStrategy; // Definição da estratégia de precificação com base no tipo de reserva
        switch (tipoReserva.toLowerCase()) {
            case "mensal":
                pricingStrategy = new MensalStrategy(); // para reservas do tipo mensal
                break;
            case "anual":
                pricingStrategy = new AnualStrategy(); // para reservas do tipo anual
                break;
            case "diaria":
            default:
                pricingStrategy = new DiariaStrategy(); // por padrão, pois tratamos diaria como um atributo, usa DiariaStrategy
                break;
        }

        // Cria um novo objeto do tipo Reserva, associando a ele a estratégia de precificação
        Reserva reserva = new Reserva(pricingStrategy);
        reserva.setCliente(cliente); // associa cliente à reserva
        reserva.setVeiculo(veiculo); // associa veiculo à reserva
        reserva.setTipoReserva(tipoReserva); // define o tipo de reserva
        reserva.setQuantidade(quantidade); // define a quantidade de dias, meses ou anos

        // calcula o valor total da reserva, com base na estratégia de precificação, e exibe no console
        double valorTotal = reserva.calcularValorTotal();
        System.out.println("Valor total da reserva: " + valorTotal);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Deseja confirmar a reserva? (s/n)"); // Solicita ao usuario a confirmação da reserva
        String confirmacao = scanner.nextLine();

        if("s".equalsIgnoreCase(confirmacao)){ // se o usuário confirmar a reserva
            contaLocadora.adicionarSaldo(valorTotal); // adiciona o valor da reserva ao saldo de contaLocadora
            System.out.println("Pagamento realizado com sucesso!");

            veiculo.setStatus("alugado"); // altera o status do veículo para alugado
            veiculoDAO.atualizarVeiculo(veiculo); // atualiza o status no BD

            reservaDAO.reservar(reserva); // salva a reserva no BD
        } else{ // caso o usuário não confirme a reserva, exibe mensagem no console
            System.out.println("Reserva não realizada.");
        }
    }

    // Método para devolver um veículo alugado. Busca o veículo pela placa e, caso o status seja "alugado",
    // altera o status para "disponível" e salva a alteração no BD
    public void devolverVeiculo(String placa) {
        Veiculo veiculo = veiculoDAO.buscarVeiculoPorPlaca(placa); // busca o veiculo no BD pela placa

        if(veiculo != null) {
            if ("alugado".equalsIgnoreCase(veiculo.getStatus())) { // verifica se o status do veiculo é "alugado"
                veiculo.setStatus("disponível"); // caso positivo, define o status como "disponível"
                veiculoDAO.atualizarVeiculo(veiculo); // atualiza o status do veiculo no BD
                System.out.println("Veículo devolvido com sucesso!");
            } else { // caso o veiculo já esteja disponível, exibe mensagem
                System.out.println("O veículo já está disponível.");
            }
        } else { // caso não localize o veículo pela placa, exibe mensagem
            System.out.println("Veículo não encontrado.");
        }
    }

    // Método que busca um cliente no BD pelo CPF
    private Cliente buscarClientePorCpf(String cpf) {
        return clienteDAO.buscarClientePorCPF(cpf); // Chama o método em ClienteDAO para buscar o cliente no BD
    }

    // Método que retorna o saldo em contaLocadora
    public double consultarSaldo(){
        return contaLocadora.getSaldo(); // retorna o saldo
    }



}
