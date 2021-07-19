package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.excepciones.NoSePuedenCrearCeroDados;

import java.util.ArrayList;
import java.util.Random;
import static java.util.Comparator.reverseOrder;

public class Dados {
    private ArrayList<Integer> conjuntoDados = new ArrayList<>();

    Dados(int cantidadDados) throws NoSePuedenCrearCeroDados {
        if(cantidadDados<1){ throw new NoSePuedenCrearCeroDados();}

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
    public int obtenerDado(int indiceDelDado){
        return conjuntoDados.get(indiceDelDado);
    }

    /* Se comparan los dos conjuntos de dados ordenados de mayor a menor
    * Si el numero del dado, de los dados recividos es menor,
    * */
    public int[] comparadorDeDados(Dados unosDados){
        int cantidadDeDadosAComparar = Math.min(this.cantidadDados(),unosDados.cantidadDados());
        int[] fichasPerdidas = {0,0}; // Pos

        for (int i = 0; i < cantidadDeDadosAComparar; i++) {
            if(this.obtenerDado(i) > unosDados.obtenerDado(i)){
                fichasPerdidas[1]++;
            }else{
                fichasPerdidas[0]++;
            }
        }

        return fichasPerdidas;
    }

}
