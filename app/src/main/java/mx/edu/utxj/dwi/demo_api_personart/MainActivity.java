package mx.edu.utxj.dwi.demo_api_personart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button btnSave, btnSearch, btnDelete, btnUpdate;
    private EditText idObra, nombreObra, tamanoObra, tecnicaObra, materialesObra, valorEconomicoObra, nombreAutor, telefonoAutor, correoAutor;
    private ListView lvArt;
    private RequestQueue requestQueue;
    private JsonArrayRequest jsonArrayRequest;
    private ArrayList<String> originData = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private String url = "http://10.10.62.14:3030/";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = findViewById(R.id.btnSave);
        btnSearch = findViewById(R.id.btnSearch);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        idObra = findViewById(R.id.idObra);
        nombreObra = findViewById(R.id.nombreObra);
        tamanoObra = findViewById(R.id.tamanoObra);
        tecnicaObra = findViewById(R.id.tecnicaObra);
        materialesObra = findViewById(R.id.materialesObra);
        valorEconomicoObra = findViewById(R.id.valorEconomicoObra);
        nombreAutor = findViewById(R.id.nombreAutor);
        telefonoAutor = findViewById(R.id.telefonoAutor);
        correoAutor = findViewById(R.id.correoAutor);
        requestQueue = Volley.newRequestQueue(this);
        lvArt = findViewById(R.id.lvArt);
        ListarProductos();
    }


    protected void ListarProductos() {
        jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                String idObra = response.getJSONObject(i).getString("idObra");
                                String nombre = response.getJSONObject(i).getString("nombreObra");
                                String costo = response.getJSONObject(i).getString("valorEconomicoObra");
                                originData.add(idObra +"::" +nombre+"::"+costo);
                                adapter = new ArrayAdapter<>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,originData);
                                lvArt.setAdapter(adapter);
                            }catch (JSONException error){
                                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }

        );
        requestQueue.add(jsonArrayRequest);
    }
}