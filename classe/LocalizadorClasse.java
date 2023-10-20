import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LocalizadorClasse {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java LocalizadorClasse <nome da classe> <diretório>");
            return;
        }

        String nomeClasse = args[0];
        String diretorioPath = args[1];

        try {
            List<String> resultados = localizarClasse(nomeClasse, diretorioPath);

            if (resultados.isEmpty()) {
                System.out.println("A classe não foi encontrada.");
            } else {
                System.out.println("Classe encontrada em:");
                resultados.forEach(System.out::println);
            }
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao pesquisar a classe: " + e.getMessage());
        }
    }

    private static List<String> localizarClasse(String nomeClasse, String diretorioPath) throws IOException {
        List<String> resultados = new ArrayList<>();
        Stream<Path> fluxoDeArquivos = Files.walk(Paths.get(diretorioPath));

        fluxoDeArquivos.filter(caminho -> caminho.toString().endsWith(".class") || caminho.toString().endsWith(".jar") || caminho.toString().endsWith(".war"))
                .forEach(caminho -> {
                    if (caminho.toString().endsWith(".class")) {
                        if (verificarClasseEmArquivo(nomeClasse, caminho.toFile())) {
                            resultados.add(caminho.toString());
                        }
                    } else if (caminho.toString().endsWith(".jar") || caminho.toString().endsWith(".war")) {
                        try {
                            verificarClasseEmArquivoCompactado(nomeClasse, caminho.toFile(), resultados);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

        return resultados;
    }

    private static boolean verificarClasseEmArquivo(String nomeClasse, File arquivo) {
        return false;
    }

    private static void verificarClasseEmArquivoCompactado(String nomeClasse, File arquivoCompactado, List<String> resultados) throws IOException {
    }
}
