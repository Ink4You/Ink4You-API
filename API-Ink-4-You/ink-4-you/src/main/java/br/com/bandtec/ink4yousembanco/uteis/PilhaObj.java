package br.com.bandtec.ink4yousembanco.uteis;

public class PilhaObj <T> {

    private T[] pilha;
    private int topo;

    public PilhaObj(int capacidade) {
        this.pilha = (T[]) new Object[capacidade];
        this.topo = -1;
    }

    public boolean isEmpty(){
        return topo == -1;
    }

    public boolean isFull(){
        return topo == pilha.length -1;
    }

    public void push(T info){
        if (isFull()){
            System.out.println("Pilha cheia!");
        } else {
            pilha[++topo] = info;
        }
    }

    public T pop(){
        if (isEmpty()){
            return null;
        }
        return pilha[topo--];
    }


    public T peek(){
        if (isEmpty()){
            System.out.println("A pilha esta vazia!");
            return null;
        }

        return pilha[topo];
    }

    public void exibir(){
        if (isEmpty()){
            System.out.println("Pilha vazia!");
        }

        for (int i = topo; i >= 0; i--){
            System.out.println(pilha[i]);
        }
    }

    public T[] getPilha() {
        return pilha;
    }
}
