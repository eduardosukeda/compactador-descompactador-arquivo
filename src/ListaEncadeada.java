public class ListaEncadeada {

    private No ini;

    public ListaEncadeada() {
        this.ini = null;
    }

    public boolean vazia() {
        return ini == null;
    }

    public void insereInicio(String elemento) {
        No novo = new No(elemento, ini);
        ini = novo;
    }

    @Override
    public String toString() {
        String strLista = "";
        No temp = ini;

        while (temp != null) {
            strLista = strLista + temp.getElemento() + " ";
            temp = temp.getProx();
        }
        return strLista;
    }

    public void remove(String elemento) {
        if (vazia()) {
            System.out.println("Lista Vazia!");
        } else {
            No temp = ini;
            No anterior = null;

            while (temp != null && !temp.getElemento().equals(elemento)) {
                anterior = temp;
                temp = temp.getProx();
            }

            if (anterior == null) {
                ini = ini.getProx();
            } else {
                if (temp != null && temp.getElemento().equals(elemento)) {
                    anterior.setProx(temp.getProx());
                } else {
                    System.out.println(elemento + " NÃO está lista");
                }
            }
        }

    }

    public int buscaIndex(String x) {
        No temp = ini;
        int index = 1;

        while (temp != null) {
            if (temp.getElemento().equals(x)) {
                return index;
            } else {
                index++;
                temp = temp.getProx();
            }
        }
        return -1;
    }

    public String buscaElemento(String x) {
        No temp = ini;
        int index = 1;
        if (isNumeric(x)) {
            while (temp != null) {
                if (index == Integer.parseInt(x)) {
                    return temp.getElemento();
                } else {
                    index++;
                    temp = temp.getProx();
                }
            }

        }
        return null;
    }

    private static boolean isNumeric(String s) {
        return s != null && s.matches("\\d+");
    }
}
