import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.jar.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.stream.Stream;

public class LocalizadorClasse {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java LocalizadorClasse <nome da classe> <diretório> [-p]");
            return;
        }

        String nomeClasse = args[0];
        String diretorioPath = args[1];
        boolean usarParallelStream = args.length > 2 && args[2].equals("-p");

        long tempoInicio = System.currentTimeMillis();

        try {
            List<String> resultados = localizarClasse(nomeClasse, diretorioPath, usarParallelStream);

            if (resultados.isEmpty()) {
                System.out.println("A classe não foi encontrada.");
            } else {
                System.out.println("Classe encontrada em:");
                resultados.forEach(System.out::println);
            }

            long tempoFim = System.currentTimeMillis();
            long tempoDecorrido = tempoFim - tempoInicio;
            System.out.println("Tempo gasto: " + tempoDecorrido + " ms");
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao pesquisar a classe: " + e.getMessage());
        }
    }

    private static List<String> localizarClasse(String nomeClasse, String diretorioPath, boolean usarParallelStream) throws IOException {
        List<String> resultados = new ArrayList<>();
        Stream<Path> fluxoDeArquivos = usarParallelStream ?
                Files.walk(Paths.get(diretorioPath)).parallel() :
                Files.walk(Paths.get(diretorioPath));

        fluxoDeArquivos.filter(caminho -> caminho.toString().endsWith(".class") || caminho.toString().endsWith(".jar") || caminho.toString().endsWith(".war"))
                .forEach(caminho -> {
                    if (caminho.toString().endsWith(".class")) {
                        if (verificarClasseEmArquivo(nomeClasse, caminho.toFile())) {
                            resultados.add(caminho.toString());
                        }
                    } else if (caminho.toString().endsWith(".jar") || caminho.toString().endsWith(".war")) {
                        verificarClasseEmArquivoCompactado(nomeClasse, caminho.toFile(), resultados);
                    }
                });

        return resultados;
    }

    private static boolean verificarClasseEmArquivo(String nomeClasse, File arquivo) {
        if (arquivo.isFile() && arquivo.getName().endsWith(".class")) {
            try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(arquivo))) {
                int magic = dataInputStream.readInt();
                if (magic == 0xCAFEBABE) {
                    int versaoMenor = dataInputStream.readUnsignedShort();
                    int versaoMaior = dataInputStream.readUnsignedShort();

                    int numeroConstantes = dataInputStream.readUnsignedShort();

                    for (int i = 1; i < numeroConstantes; i++) {
                        int tag = dataInputStream.readByte();
                        switch (tag) {
                            case 7:
                                int indiceClasse = dataInputStream.readUnsignedShort();
                                String constante = dataInputStream.readUTF();
                                if (constante.contains(nomeClasse.replace(".", "/"))) {
                                    return true;
                                }
                                break;
                            default:
                                int pularBytes = switch (tag) {
                                    case 1, 3, 4, 9, 10, 11 -> dataInputStream.readUnsignedShort();
                                    case 5, 6 -> 4;
                                    default -> 0;
                                };
                                dataInputStream.skipBytes(pularBytes);
                                break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static void verificarClasseEmArquivoCompactado(String nomeClasse, File arquivoCompactado, List<String> resultados) {
        try {
            if (arquivoCompactado.getName().endsWith(".jar")) {
                verificarClasseEmJar(nomeClasse, arquivoCompactado, resultados);
            } else if (arquivoCompactado.getName().endsWith(".war")) {
                verificarClasseEmWar(nomeClasse, arquivoCompactado, resultados);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void verificarClasseEmJar(String nomeClasse, File arquivoJar, List<String> resultados) throws IOException {
        try (JarFile jarFile = new JarFile(arquivoJar)) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    if (entry.getName().contains(nomeClasse.replace(".", "/"))) {
                        resultados.add(arquivoJar.getAbsolutePath() + " (no arquivo JAR)");
                        break;
                    }
                }
            }
        }
    }

    private static void verificarClasseEmWar(String nomeClasse, File arquivoWar, List<String> resultados) throws IOException {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(arquivoWar))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    if (entry.getName().contains(nomeClasse.replace(".", "/"))) {
                        resultados.add(arquivoWar.getAbsolutePath() + " (no arquivo WAR)");
                        break;
                    }
                }
            }
        }
    }
}
