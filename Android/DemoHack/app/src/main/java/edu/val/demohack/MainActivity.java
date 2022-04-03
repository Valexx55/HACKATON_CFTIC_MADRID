package edu.val.demohack;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import edu.val.demohack.dto.Eventos;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Eventos>, AdapterView.OnItemSelectedListener {


    private String distrito;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private TextView nresultados;

    private Spinner spinner;
    private String[] opciones = {"ARGANZUELA", "BARAJAS", "CARABANCHEL", "CENTRO", "CHAMARTIN", "CHAMBERI",
            "CIUDAD LINEAL",
            "FUENCARRAL-EL PARDO",
            "HORTALEZA",
            "LATINA",
            "MONCLOA-ARAVACA",
            "MORATALAZ",
            "PUENTE DE VALLECAS",
            "RETIRO",
            "SALAMANCA",
            "SAN BLAS-CANILLEJAS",
            "USERA","VICALVARO",
            "VILLA DE VALLECAS","VILLAVERDE"};




    public final static String ETIQUETA_LOG = "AppEjemplos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.progressBar = findViewById(R.id.pbc);
        this.recyclerView = findViewById(R.id.rvc);
        this.nresultados = findViewById(R.id.nresultados);

        SpinnerAdapter spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        ((ArrayAdapter)spinnerAdapter).setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spinner = findViewById(R.id.spinnerv);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(this);

    }



    @NonNull
    @Override
    public Loader<Eventos> onCreateLoader(int id, @Nullable Bundle args) {

        BusquedaEventos busquedaEventos = null;

        busquedaEventos = new BusquedaEventos(this, distrito);


        return busquedaEventos;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onLoadFinished(@NonNull Loader<Eventos> loader, Eventos eventos) {

        Log.d(MainActivity.ETIQUETA_LOG, "onLoadFinished");
        //Log.d(MainActivity.ETIQUETA_LOG, "Listado recibido eventos= " + rc.toString());
        StringBuilder stringBuilder = new StringBuilder();

        eventos.getLista_eventos().forEach(e -> {
            Log.d(ETIQUETA_LOG, e.getTitle());
            stringBuilder.append(e.getTitle()+"\n");

        });

        this.progressBar.setVisibility(View.INVISIBLE);

        Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Eventos> loader) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Log.d(MainActivity.ETIQUETA_LOG, "SÍ HAY INTERNET");

        this.progressBar.setVisibility(View.VISIBLE);

        Log.d(MainActivity.ETIQUETA_LOG, "Opción nueva seleccionada en el spinner");
        TextView textView = (TextView)view;
        distrito = textView.getText().toString();
        Log.d(MainActivity.ETIQUETA_LOG, "Opción tocada " + textView.getText().toString());
        if (RedUtil.hayInternet(this)) {

            //CON AsyncTaskLoader
            LoaderManager lm = LoaderManager.getInstance(this);
            lm.initLoader(37, null, this);

        } else {
            Log.d(MainActivity.ETIQUETA_LOG, "NO HAY INTERNET");
            Toast.makeText(this, "SIN CONEXIÓN A INTERNET", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //SERÍA INVOCADA CUANDO CAMBIA EL ADAPTER Y UNA OPCIÓN SELECCIONADA DEJA DE ESTAR DISPONIBLE
    }
}