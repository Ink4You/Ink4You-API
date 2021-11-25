package br.com.bandtec.ink4yousembanco.uteis;

public class FilaObj <T> {

    private T[] fila;
    private int tamanho;

    public FilaObj(int capacidade) {
        this.fila = (T[]) new Object[capacidade];
        this.tamanho = 0;
    }

    public boolean isEmpty(){
        return tamanho == 0;
    }

    public boolean isFull(){
        return tamanho == fila.length;
    }

    public void insert(T info){
        if (isFull()){
            System.out.println("A fila esta cheia!");
        }else {
            fila[tamanho++] = info;
        }
    }

    public T peek(){
        if (isEmpty()){
            System.out.println("A lista esta vazia!");
            return null;
        }
        return fila[0];
    }

    public T poll(){
        T primeiro = fila[0];
        if (isEmpty()){
            System.out.println("A fila está vazia, não existem itens para remover!");
            return null;
        } else {
            for (int i = 0; i < tamanho -1; i++){
                fila[i] = fila[i+1];
            }
            fila[tamanho -1] = null;
            --tamanho;
        }
        return primeiro;
    }

    public void exibir(){
        if (isEmpty()){
            System.out.println("A fila esta vazia!");
        }

        for (int i = 0; i < tamanho; i++){
            System.out.print(fila[i] + "\t");
        }
    }

    public int getTamanho() {
        return tamanho;
    }
}
