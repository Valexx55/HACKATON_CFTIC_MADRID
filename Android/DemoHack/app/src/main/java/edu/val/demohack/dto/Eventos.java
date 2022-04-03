package edu.val.demohack.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Eventos {

    public Eventos ()
    {
        this.lista_eventos = new ArrayList<Evento>();
    }

    @JsonProperty("@graph")
    private List<Evento> lista_eventos;

    public List<Evento> getLista_eventos() {
        return lista_eventos;
    }

    public void setLista_eventos(List<Evento> lista_eventos) {
        this.lista_eventos = lista_eventos;
    }
}
