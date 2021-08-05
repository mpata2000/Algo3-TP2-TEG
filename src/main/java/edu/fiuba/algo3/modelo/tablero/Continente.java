package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.List;

public class Continente {
    private final String nombreContinente;
    private final int fichas;
    private final List<Pais> paises;

    public Continente(String nombreContinente, int fichas, List<Pais> paises) {
        this.nombreContinente = nombreContinente;
        this.fichas = fichas;
        this.paises = paises;
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public int getFichas() {
        return fichas;
    }

    public String getNombre() {
        return nombreContinente;
    }

    public boolean esDeJugador(Jugador unJugador){
        return this.paises.stream().allMatch( pais -> pais.esDeJugador(unJugador));
    }

    public int cantidadPaisesDe(Jugador unJugador){
        return (int)this.paises.stream().filter(pais -> pais.esDeJugador(unJugador)).count();
    }

    public void agregarFichasExtraA(Jugador unJugador) {
        if(this.esDeJugador(unJugador)){
            unJugador.agregarFichas(this.fichas);
        }
    }

    public String paisesDeJugador(Jugador jugador){
        return (this.getNombre()+": "+this.cantidadPaisesDe(jugador)+"/"+this.paises.size());
    }
}
