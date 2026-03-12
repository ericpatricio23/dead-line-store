package service;

import model.ItemVenda;
import model.Venda;
import repository.ItemVendaRepository;
import repository.ProdutoRepository;
import repository.VendaRepository;

import java.time.LocalDateTime;
import java.util.List;

public class VendaService {

    private VendaRepository vendaRepository = new VendaRepository();
    private ItemVendaRepository itemVendaRepository = new ItemVendaRepository();
    private ProdutoRepository produtoRepository = new ProdutoRepository();

    public int criarVenda() {
        Venda venda = new Venda();

        venda.setData(LocalDateTime.now().toString());
        venda.setValorTotal(0);
        return vendaRepository.criarVenda(venda);
    }

    public void adicionarItem(int vendaId, int produtoId, int quantidade, double precoUnitario) {
        ItemVenda item = new ItemVenda(vendaId, produtoId, quantidade, precoUnitario);
        itemVendaRepository.salvarItem(item);
        produtoRepository.atualizarEstoque(produtoId, quantidade);
    }

    public double calcularTotalVenda(int vendaId) {
        double total = 0;

        var itens = itemVendaRepository.listarItensPorVenda(vendaId);

        for (ItemVenda item : itens) {
            total += item.getSubtotal();
        }

        return total;
    }


    public List<Venda> listarVendas() {
        return vendaRepository.listarVendas();
    }

    public List<ItemVenda> listarItensDaVenda(int vendaId) {
        return itemVendaRepository.listarItensPorVenda(vendaId);
    }

    public void finalizarVenda(int vendaId) {

        double total = calcularTotalVenda(vendaId);

        vendaRepository.atualizarTotal(vendaId, total);
    }

}
