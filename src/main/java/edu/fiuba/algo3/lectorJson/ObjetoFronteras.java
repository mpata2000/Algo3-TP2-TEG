package edu.fiuba.algo3.lectorJson;

import java.util.Arrays;
import java.util.List;

public class ObjetoFronteras {
    private String Pais;
    private String Continente;
    private String[] PaisesLimitrofes;
    //private String PaisesLimitrofes;

    public String getPais(){return this.Pais;}
    public String getContinente(){return this.Continente;}

    public List<String> getPaisesLimitrofes(){
        //return Arrays.asList(this.PaisesLimitrofes.split(","));
        return Arrays.asList(this.PaisesLimitrofes);
    }
}
