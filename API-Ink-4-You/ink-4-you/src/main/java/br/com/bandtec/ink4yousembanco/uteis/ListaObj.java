package br.com.bandtec.ink4yousembanco.uteis;

public class ListaObj <T>{

    private T[] vetor;
    private int nroElem;

    public ListaObj(int tamanho) {
        vetor = (T[]) new Object[tamanho];
        nroElem = 0;
    }

    public boolean adicionar(T elemento) {
        if (nroElem >= vetor.length) {
            System.out.println("A lista está cheia");
            return false;
        }
        vetor[nroElem++] = elemento;
        return true;
    }

    //metodo que adiciona no inicio (Provavelmente com erro)
    public boolean adicionaNoInicio(T elemento){
        if (nroElem >= vetor.length) {
            System.out.println("A lista está cheia");
            return false;
        }

        T[] vetorNovo = (T[]) new Object[nroElem++];

        for (int i = 0; i < vetor.length; i++){
            vetorNovo[0] = elemento;
            vetorNovo[nroElem++] = vetor[i];
        }
        return true;

    }

    public void exibir() {
        if (nroElem == 0) {
            System.out.println("\nA lista está vazia.");
        }
        else {
            for (int i = 0; i < nroElem; i++) {
                System.out.print(vetor[i] + "\t");
                System.out.println("");
            }
            System.out.println();
        }
    }

    public int buscar(T elementoBuscado) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(elementoBuscado)) {
                return i;
            }
        }
        return -1;
    }


    public boolean removePeloIndice (int indice) {
        if (indice < 0 || indice >= nroElem) {
            System.out.println("\nÍndice inválido!");
            return false;
        }

        for (int i = indice; i < nroElem - 1; i++) {
            vetor[i] = vetor[i+1];
        }
        nroElem--;
        return true;
    }

    public boolean removeElemento(T elementoARemover) {
        return removePeloIndice(buscar(elementoARemover));
    }

    public int getTamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return null;
        }
        return vetor[indice];
    }

    public void limpa() {
        nroElem = 0;
    }
}
