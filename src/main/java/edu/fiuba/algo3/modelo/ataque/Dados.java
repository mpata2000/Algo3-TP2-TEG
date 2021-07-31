package edu.fiuba.algo3.modelo.ataque;

import java.util.ArrayList;
import java.util.Random;
import static java.util.Comparator.reverseOrder;

public class Dados {
    private final ArrayList<Integer> conjuntoDados = new ArrayList<>();

    public Dados(int cantidadDados) {
        if(cantidadDados<1){ throw new NoSePuedenCrearCeroDadosException();}

        Random rand = new Random();

        for (int i = 0; i < cantidadDados; i++) {
            conjuntoDados.add(rand.nextInt(6) + 1); // Esto deberia dar un numero random entre 1 y 6
        }

        conjuntoDados.sort(reverseOrder());
    }

    //Devuelve la cantidad de dados que hay
    public int cantidadDados(){
        return conjuntoDados.size();
    }

    //Devuelve el numero del dado en el indice pedido
    public int getDado(int indiceDelDado){
        return conjuntoDados.get(indiceDelDado);
    }

    /* Se comparan los dos conjuntos de dados ordenados de mayor a menor
    * Si el numero del dado, de los dados recividos es menor,
    * */
    public int[] compararDados(Dados unosDados){
        int cantidadDeDadosAComparar = Math.min(this.cantidadDados(),unosDados.cantidadDados());
        int[] fichasPerdidas = {0,0}; // Pos

        for (int i = 0; i < cantidadDeDadosAComparar; i++) {
            if(this.getDado(i) > unosDados.getDado(i)){
                fichasPerdidas[1]++;
            }else{
                fichasPerdidas[0]++;
            }
        }

        return fichasPerdidas;
    }

}
