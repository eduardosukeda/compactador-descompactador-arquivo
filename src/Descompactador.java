import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Descompactador extends Object {

    public static void main(String[] args) {
        ListaEncadeada lst = new ListaEncadeada();
        String[] palavrasDaLinha;
        String textoDescompactado = null;

        String indiceDaPalavra;
        try {
            List<String> textoCompactado = Files.readAllLines(Paths.get("descompactar.txt"));
            textoDescompactado = String.join("\n", textoCompactado);

            BufferedWriter writer = new BufferedWriter(new FileWriter("saida.txt"));

            for (String linha : textoCompactado) {
                if (linha.equals("0")) {
                    break;
                }

                palavrasDaLinha = linha.split("\\W+");

                for (String palavra : palavrasDaLinha) {
                    indiceDaPalavra = lst.buscaElemento(palavra);
                    if (indiceDaPalavra == null) {
                        if (palavra.equals("")) {
                        } else {
                            lst.insereInicio(palavra);
                        }
                    } else {

                        int indiceDaPrimeiraOcorrencia = textoDescompactado.indexOf(indiceDaPalavra);
                        String inicioDoTexto = textoDescompactado.substring(0, indiceDaPrimeiraOcorrencia + indiceDaPalavra.length());
                        String finalDoTexto = textoDescompactado.substring(indiceDaPrimeiraOcorrencia + indiceDaPalavra.length());

                        textoDescompactado = inicioDoTexto + finalDoTexto.replaceFirst(palavra, indiceDaPalavra);
                        lst.remove(indiceDaPalavra);
                        lst.insereInicio(indiceDaPalavra);
                        indiceDaPalavra = "";
                    }
                }
            }

            writer.write(textoDescompactado);

            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
