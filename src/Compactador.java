import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Compactador {

    public static void main(String[] args) {
        ListaEncadeada lst = new ListaEncadeada();
        String[] palavrasDaLinha;
        String textoCompactado = "";

        int indiceDaPalavra;
        try {
            List<String> textoCompleto = Files.readAllLines(Paths.get("compactar.txt"));
            textoCompactado = String.join("\n", textoCompleto);

            BufferedWriter writer = new BufferedWriter(new FileWriter("saida.txt"));

            for (String linha : textoCompleto) {
                if (linha.equals("0")) {
                    break;
                }

                palavrasDaLinha = linha.split("\\W+");

                for (String palavra : palavrasDaLinha) {
                    indiceDaPalavra = lst.buscaIndex(palavra);
                    if (indiceDaPalavra == -1) {
                        if (palavra.equals("")) {
                        } else {
                            lst.insereInicio(palavra);
                        }
                    } else {

                        int indiceDaPrimeiraOcorrencia = textoCompactado.indexOf(palavra);
                        String inicioDoTexto = textoCompactado.substring(0, indiceDaPrimeiraOcorrencia + palavra.length());
                        String finalDoTexto = textoCompactado.substring(indiceDaPrimeiraOcorrencia + palavra.length());

                        textoCompactado = inicioDoTexto + finalDoTexto.replaceFirst(palavra, Integer.toString(indiceDaPalavra));
                        lst.remove(palavra);
                        lst.insereInicio(palavra);
                        indiceDaPalavra = 0;
                    }
                }
            }

            writer.write(textoCompactado);

            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
