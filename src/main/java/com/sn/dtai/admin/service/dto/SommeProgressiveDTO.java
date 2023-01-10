package com.sn.dtai.admin.service.dto;

import java.io.Serializable;

public class SommeProgressiveDTO implements Serializable {

    private int sommeInferieure;

    private int sommeEgale;

    private int somme;
    
    public SommeProgressiveDTO(int sommeInferieure, int sommeEgale){
        this.sommeInferieure = sommeInferieure;
        this.sommeEgale = sommeEgale;
        this.somme = sommeInferieure + sommeEgale;
    };


    public int getSommeInferieure() {
        return sommeInferieure;
    }

    public void setSommeInferieure(int sommeInferieure) {
        this.sommeInferieure = sommeInferieure;
    }

    public int getSommeEgale() {
        return sommeEgale;
    }

    public void setSommeEgale(int sommeEgale) {
        this.sommeEgale = sommeEgale;
    }

    public int getSomme() {
        return somme;
    }

    public void setSomme() {
        this.somme = getSommeInferieure() + getSommeEgale();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SommeProgressiveDTO{" +
         
            ", sommeInferieure ='" + getSommeInferieure() + "'" +
            ", sommeEgale='" + getSommeEgale() + "'" +
            ", somme Total ='" + getSomme() + "'" +
            "}";
    }
}
