package edu.val.demohack;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

//import com.google.gson.Gson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import edu.val.demohack.dto.Eventos;

public class BusquedaEventos extends AsyncTaskLoader<Eventos> {

    private String distrito;
    private final static String URL_API_EVENTOS_MADRID_POR_DISTRITO = "https://datos.madrid.es/egob/catalogo/206974-0-agenda-eventos-culturales-100.json?distrito_nombre=";

    public BusquedaEventos(@NonNull Context context, String distrito) {
        super(context);
        this.distrito = distrito;
        Log.d(MainActivity.ETIQUETA_LOG, "BusquedaEventos constructor");
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Nullable
    @Override
    public Eventos loadInBackground() {
        //Object resultadoCanciones = null;
        Eventos eventos =null;
        //String info_eventos = null;
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        InputStreamReader inputStreamReader = null;
       // Gson gson = null;

        try {

            distrito = URLEncoder.encode(distrito, "UTF-8");
            distrito = URL_API_EVENTOS_MADRID_POR_DISTRITO + distrito;
            Log.d(MainActivity.ETIQUETA_LOG, "Llamando a " + distrito);
            url = new URL(distrito);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                Log.d(MainActivity.ETIQUETA_LOG, "Respuesta OK ");
                inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                // gson = new Gson();
                //resultadoCanciones = gson.fromJson(inputStreamReader, ResultadoCanciones.class);


                StringBuilder stringBuilder = new StringBuilder();

                try (BufferedReader br = new BufferedReader(inputStreamReader))

                {

                    ObjectMapper mapper = new ObjectMapper();
                    eventos = mapper.readValue(inputStreamReader, Eventos.class);
                    Log.d(MainActivity.ETIQUETA_LOG, "RX n eventos = " + eventos.getLista_eventos().size());

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(MainActivity.ETIQUETA_LOG, "error ", e);
                }


            } else {
                Log.d(MainActivity.ETIQUETA_LOG, "Respuesta FALLO ");
            }


        } catch (Throwable fallo) {
            Log.e(MainActivity.ETIQUETA_LOG, "Se ha producido un fallo ", fallo);
        } finally {

            try {
                inputStreamReader.close();
                httpURLConnection.disconnect();
            } catch (IOException ioException) {
                Log.e(MainActivity.ETIQUETA_LOG, "Fallo al liberar recursos ", ioException);
            }


        }


        return eventos;
    }
}
