package edu.fiuba.algo3.modelo;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Dados {
    private ArrayList<Integer> dados = new ArrayList<Integer>();

    Dados(int cantidadDados) throws NoSePuedenCrearCeroDados{
        if(cantidadDados<1){
            throw new NoSePuedenCrearCeroDados();

        }
        Random random = new Random();

        for (int i = 0; i < cantidadDados; i++) {
            dados.add(random.nextInt(6)+1);
        }

        Collections.sort(dados, Collections.reverseOrder());
    }

    //Devuelve la cantidad de dados que hay
    public int cantidadDados(){
        return dados.size();
    }

    //Devuelve el numero del dado en el indice pedido
    public int obtenerDado(int indiceDelDado){
        return dados.get(indiceDelDado);
    }

    /* Se comparan los dos conjuntos de dados ordenados de mayor a menor
    * Si el numero del dado, de los dados recividos es menor,
    * */
    public int[] comparaDados(Dados unosDados){
        int cantidadDeDadosAComparar = Math.min(this.cantidadDados(),unosDados.cantidadDados());
        int[] fichasPerdidas = {0,0};

        for (int i = 0; i < cantidadDeDadosAComparar; i++) {
            if(this.obtenerDado(i) > unosDados.obtenerDado(i)){
                fichasPerdidas[0]++;
            }else{
                fichasPerdidas[1]++;
            }
        }

        return fichasPerdidas;
    }

}
