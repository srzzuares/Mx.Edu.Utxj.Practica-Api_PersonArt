package mx.edu.utxj.dwi.demo_api_personart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button btnSave, btnSearch, btnDelete, btnUpdate;
    private EditText idObra, nombreObra, tamanoObra, tecnicaObra, materialesObra, valorEconomicoObra, nombreAutor, telefonoAutor, correoAutor;
    private ListView lvArt;
    private RequestQueue requestQueue;
    private JsonArrayRequest jsonArrayRequest;
    private ArrayList<String> originData = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private String host = "192.168.1.81";//10.10.62.17
    private String url = "http://"+host+":3030/";
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

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObjectRequest obtenerDatos = new JsonObjectRequest(
                        Request.Method.GET,
                        url + "obtAct/" + idObra.getText().toString(),
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if(response.has("status")){
                                    Toast.makeText(MainActivity.this, "Obra no encontrada", Toast.LENGTH_SHORT).show();
                                }else {
                                    try {
                                        nombreObra.setText(response.getString("nombreObra"));
                                        tamanoObra.setText(response.getString("tamanoObra"));
                                        tecnicaObra.setText(response.getString("tecnicaObra"));
                                        materialesObra.setText(response.getString("materialesObra"));
                                        valorEconomicoObra.setText(response.getString("valorEconomicoObra"));
                                        nombreAutor.setText(response.getString("nombreAutor"));
                                        telefonoAutor.setText(response.getString("telefonoAutor"));
                                        correoAutor.setText(response.getString("correoAutor"));
                                    } catch (JSONException error) {
                                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                );
                requestQueue.add(obtenerDatos);
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject guardaArte = new JSONObject();
                try {
                    guardaArte.put("idObra",idObra.getText().toString());
                    guardaArte.put("nombreObra",nombreObra.getText().toString());
                    guardaArte.put("tamanoObra",tamanoObra.getText().toString());
                    guardaArte.put("tecnicaObra",tecnicaObra.getText().toString());
                    guardaArte.put("materialesObra",materialesObra.getText().toString());
                    guardaArte.put("valorEconomicoObra",valorEconomicoObra.getText().toString());
                    guardaArte.put("nombreAutor",nombreAutor.getText().toString());
                    guardaArte.put("telefonoAutor",telefonoAutor.getText().toString());
                    guardaArte.put("correoAutor",correoAutor.getText().toString());
                }catch (JSONException error){
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
                JsonObjectRequest salvar = new JsonObjectRequest(
                        Request.Method.POST,
                        url+"crear",
                        guardaArte,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                                try {
                                    if (response.getString("status").equals("Obra Guardada"))
                                        Toast.makeText(MainActivity.this, "¡Obra Guardada con exito", Toast.LENGTH_SHORT).show();
                                    idObra.setText("");
                                    nombreObra.setText("");
                                    tamanoObra.setText("");
                                    tecnicaObra.setText("");
                                    materialesObra.setText("");
                                    valorEconomicoObra.setText("");
                                    nombreAutor.setText("");
                                    telefonoAutor.setText("");
                                    correoAutor.setText("");
                                    ListarProductos();
                                } catch (JSONException e){
                                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                );
                requestQueue.add(salvar);
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject actualizar = new JSONObject();
                try {
                    actualizar.put("idObra", idObra.getText().toString());
                    actualizar.put("nombreObra", nombreObra.getText().toString());
                    actualizar.put("tamanoObra", tamanoObra.getText().toString());
                    actualizar.put("tecnicaObra", tecnicaObra.getText().toString());
                    actualizar.put("materialesObra", materialesObra.getText().toString());
                    actualizar.put("valorEconomicoObra", valorEconomicoObra.getText().toString());
                    actualizar.put("nombreAutor", nombreAutor.getText().toString());
                    actualizar.put("telefonoAutor", telefonoAutor.getText().toString());
                    actualizar.put("correoAutor", correoAutor.getText().toString());
                }catch(JSONException error){
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
                JsonObjectRequest actualizado = new JsonObjectRequest(
                        Request.Method.PUT,
                        url + "actualizar/" + idObra.getText().toString(),
                        actualizar,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    if (response.getString("status").equals("Obra Actualizada"))
                                        Toast.makeText(MainActivity.this, "Obra Actualizada exitosamente", Toast.LENGTH_SHORT).show();
                                    idObra.setText("");
                                    nombreObra.setText("");
                                    tamanoObra.setText("");
                                    tecnicaObra.setText("");
                                    materialesObra.setText("");
                                    valorEconomicoObra.setText("");
                                    nombreAutor.setText("");
                                    telefonoAutor.setText("");
                                    correoAutor.setText("");

                                } catch (JSONException e) {
                                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                                ListarProductos();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                );
                requestQueue.add(actualizado);
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObjectRequest eliminado = new JsonObjectRequest(
                        Request.Method.DELETE,
                        url+"eliminar/"+idObra.getText().toString(),
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    if (response.getString("status").equals("Obra Eliminada")) {
                                        Toast.makeText(MainActivity.this, "Arte eliminado", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(MainActivity.this, "Error al eliminar", Toast.LENGTH_SHORT).show();

                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                                idObra.setText("");
                                ListarProductos();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                );
                requestQueue.add(eliminado);
            }
        });
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
                                String autor = response.getJSONObject(i).getString("nombreAutor");
                                originData.add(idObra +" <-> " +nombre+" <-> $"+costo+" <-> "+autor);
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