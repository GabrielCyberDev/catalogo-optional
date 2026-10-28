import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    // Busca por nome retornando Optional (o produto pode não existir)
    public static Optional<Produto> buscarPorNome(List<Produto> produtos, String nome) {
        return produtos.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    // Exibe o resultado da busca com ifPresentOrElse
    public static void exibirBusca(List<Produto> produtos, String nome) {
        System.out.println("Buscando: " + nome);
        buscarPorNome(produtos, nome).ifPresentOrElse(
                p -> System.out.println("Encontrado: " + p.getNome() + " - R$ " + p.getPreco()),
                () -> System.out.println("Produto não encontrado.")
        );
    }

    public static void main(String[] args) {
        List<Produto> produtos = Arrays.asList(
                new Produto("Teclado", 150.00),
                new Produto("Mouse", 80.00),
                new Produto("Monitor", 900.00),
                new Produto("Cabo HDMI", 35.00),
                new Produto("Notebook", 3500.00)
        );

        // 1) Produtos com preço maior que 100
        List<Produto> caros = produtos.stream()
                .filter(p -> p.getPreco() > 100)
                .collect(Collectors.toList());
        System.out.println("=== Produtos com preço acima de R$ 100 ===");
        caros.forEach(System.out::println);

        // 2) Ordenados por nome (ordem alfabética)
        List<Produto> ordenados = produtos.stream()
                .sorted(Comparator.comparing(Produto::getNome))
                .collect(Collectors.toList());
        System.out.println();
        System.out.println("=== Produtos ordenados por nome ===");
        ordenados.forEach(System.out::println);

        // 3) Busca com Optional
        System.out.println();
        System.out.println("=== Busca por nome ===");
        exibirBusca(produtos, "Monitor");   // existe
        exibirBusca(produtos, "Impressora"); // não existe
    }
}