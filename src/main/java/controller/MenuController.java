package controller;

import java.time.LocalDate;
import java.util.*;

import model.Produto;
import model.Venda;
import repository.ProdutoRepository;
import service.VendaService;

public class MenuController {

    private Scanner scanner = new Scanner(System.in);
    private ProdutoRepository produtoRepo = new ProdutoRepository();
    private VendaService vendaService = new VendaService();

    public void iniciar() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== SISTEMA DA LOJA ===");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Registrar Venda");
            System.out.println("4 - Listar Vendas");
            System.out.println("5 - Reabastecer Estoque");
            System.out.println("0 - Sair");

            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> listarProdutos();
                case 3 -> registrarVenda();
                case 4 -> listarVendas();
                case 5 -> reabastecerEstoque();
                case 0 -> System.out.println("Sistema encerrado.");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    //  Métodos:

    public void cadastrarProduto() {
        System.out.println("\n--- CADASTRAR PRODUTO ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        System.out.print("Estoque: ");
        int estoque = scanner.nextInt();
        scanner.nextLine();

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setEstoque(estoque);

        produtoRepo.salvarProduto(produto);

        System.out.println("Produto cadastrado com sucesso!");
    }

    public void listarProdutos() {
        List<Produto> produtos = produtoRepo.listarProdutos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("\n--- PRODUTOS CADASTRADOS ---");
        for (Produto p : produtos) {
            System.out.printf("ID: %d | Nome: %s | Preço: %.2f | Estoque: %d%n",
                    p.getId(), p.getNome(), p.getPreco(), p.getEstoque());
        }
    }
    public void registrarVenda() {

        int vendaId = vendaService.criarVenda();

        while (true) {

            System.out.print("ID do produto (0 para finalizar): ");
            int produtoId = scanner.nextInt();

            if (produtoId == 0) break;

            System.out.print("Quantidade: ");
            int quantidade = scanner.nextInt();

            var produto = produtoRepo.buscarProdutoPorId(produtoId);

            if (produto == null) {
                System.out.println("Produto não encontrado.");
                continue;
            }

            if (produto.getEstoque() == 0) {
                System.out.println("Produto sem estoque.");
                continue;
            }

            if (quantidade > produto.getEstoque()) {
                System.out.println("Estoque insuficiente.");
                continue;
            }

            vendaService.adicionarItem(
                    vendaId,
                    produtoId,
                    quantidade,
                    produto.getPreco()
            );

            System.out.println("Item adicionado à venda.");
        }

        double total = vendaService.calcularTotalVenda(vendaId);

        if (total == 0) {
            System.out.println("Venda cancelada.");
            return;
        }

        vendaService.finalizarVenda(vendaId);


        System.out.println("Venda finalizada.");
        System.out.println("Total: R$ " + total);
    }
    public void listarVendas() {

        var vendas = vendaService.listarVendas();

        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        Map<String, List<Venda>> vendasPorDia = new HashMap<>();

        for (var venda : vendas) {

            String data = venda.getData().substring(0, 10);

            vendasPorDia
                    .computeIfAbsent(data, k -> new ArrayList<>())
                    .add(venda);
        }

        System.out.println("\n--- HISTÓRICO DE VENDAS ---");

        for (var entry : vendasPorDia.entrySet()) {

            String data = entry.getKey();
            List<Venda> vendasDoDia = entry.getValue();

            System.out.println("\n=== VENDAS DO DIA " + data + " ===");

            double totalDia = 0;

            for (var venda : vendasDoDia) {
                System.out.println("Venda ID: " + venda.getId()
                        + " | Total: R$ " + venda.getValorTotal());

                totalDia += venda.getValorTotal();
            }

            System.out.println("Total do dia: R$ " + totalDia);
        }
    }
    public void reabastecerEstoque() {

        System.out.println("\n--- REABASTECER ESTOQUE ---");

        System.out.print("ID do produto: ");
        int id = scanner.nextInt();

        System.out.print("Quantidade a adicionar: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        var produto = produtoRepo.buscarProdutoPorId(id);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        produtoRepo.somarEstoque(id, quantidade);
    }

    }