package controller;

import java.util.List;
import java.util.Scanner;

import model.Produto;
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
            System.out.println("0 - Sair");

            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // consome quebra de linha

            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> listarProdutos();
                case 3 -> registrarVenda();
                case 4 -> listarVendas();
                case 0 -> System.out.println("Sistema encerrado.");
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    // ---------------------- Métodos ----------------------

    private void cadastrarProduto() {
        System.out.println("\n--- CADASTRAR PRODUTO ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        System.out.print("Estoque: ");
        int estoque = scanner.nextInt();
        scanner.nextLine(); // consome quebra de linha

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setEstoque(estoque);

        produtoRepo.salvarProduto(produto);

        System.out.println("Produto cadastrado com sucesso!");
    }

    private void listarProdutos() {
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
    private void registrarVenda() {

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

            vendaService.adicionarItem(
                    vendaId,
                    produtoId,
                    quantidade,
                    produto.getPreco()
            );
        }

        double total = vendaService.calcularTotalVenda(vendaId);

        System.out.println("Venda finalizada.");
        System.out.println("Total: R$ " + total);
    }
    private void listarVendas() {

        var vendas = vendaService.listarVendas();

        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        System.out.println("\n--- HISTÓRICO DE VENDAS ---");

        for (var venda : vendas) {
            System.out.println("Venda ID: " + venda.getId());
            System.out.println("Data: " + venda.getData());
            System.out.println("Total: R$ " + venda.getValorTotal());
            System.out.println("-------------------------");
        }
    }

    }