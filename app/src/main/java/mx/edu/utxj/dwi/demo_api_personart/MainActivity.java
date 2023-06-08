package mx.edu.utxj.dwi.demo_api_personart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button btnSave,btnSearch,btnDelete,btnUpdate;
    private EditText idObra,nombreObra,tamanoObra,tecnicaObra,materialesObra,valorEconomicoObra,nombreAutor,telefonoAutor,correoAutor;
    private ListView lvArt;
    private RequestQueue requestQueue;
    private JsonArrayRequest jsonArrayRequest;
    private ArrayList<String> originData= new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private String url = "http://10.10.62.17:3300/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave=findViewById(R.id.btnSave);
        btnSearch=findViewById(R.id.btnSearch);
        btnDelete=findViewById(R.id.btnDelete);
        btnUpdate=findViewById(R.id.btnUpdate);
        idObra=findViewById(R.id.idObra);
        nombreObra=findViewById(R.id.nombreObra);
        tamanoObra=findViewById(R.id.tamanoObra);
        tecnicaObra=findViewById(R.id.tecnicaObra);
        materialesObra=findViewById(R.id.materialesObra);
        valorEconomicoObra=findViewById(R.id.valorEconomicoObra);
        nombreAutor=findViewById(R.id.nombreAutor);
        telefonoAutor=findViewById(R.id.telefonoAutor);
        correoAutor=findViewById(R.id.correoAutor);
        requestQueue= Volley.newRequestQueue(this);
        lvArt=findViewById(R.id.lvArt);

    }
}