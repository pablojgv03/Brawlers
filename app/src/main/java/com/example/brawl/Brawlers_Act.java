package com.example.brawl;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Brawlers_Act extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new RecyclerAdapter(this);

        new taskConnections().execute("GET", "/brawlers");
        setContentView(R.layout.activity_brawlers);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //Todo 3.2 Por último solo debemos añadir los elementos creados anteriormente a la vista
        // padre (RecyclerView) con sus respectivos métodos.
        recyclerView = (RecyclerView) findViewById(R.id.recView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }

    //Todo 2. (ver clase HttpConnectPokemon.java ) Al ser una tarea que implica una espera,
    // como es la respuesta del servidor, se tiene que llevar a cabo a través de un hilo
    // secundario.
    private class taskConnections extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
            switch (strings[0]){
                case "GET":
                    result = HttpConnectBrawl.getRequest(strings[1]);
                    break;
                case "POST":
                    result = Integer.toString(HttpConnectBrawl.postRequest(strings[1],strings[2]));
                    break;
            }


            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                if(s != null){
                    Log.d("D","DATOS: "+s);

                    //Todo 3. La respuesta que nos devuelve es un texto en formato JSON. Para ello,
                    // en este caso, haremos uso de las clases que nos proporciona Android. Antes
                    // que nada, se deberá consultar la documentación para conocer el formato de
                    // la respuesta del servidor, y así saber cómo deserializar el mensaje.
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    String id = "";
                    String name = "";
                    String efoto = "";
                    String descripcion = "";
                    for(int i=0; i<jsonArray.length(); i++){
                        id = jsonArray.getJSONObject(i).getString("id");
                        name = jsonArray.getJSONObject(i).getString("name");
                        efoto = jsonArray.getJSONObject(i).getString("imageUrl2");
                        descripcion = jsonArray.getJSONObject(i).getString("description");
                        Brawlers_Class brawler = new Brawlers_Class(id, name, efoto, descripcion);
                        adapter.add(brawler);
                    }
                    //Todo 3.1 Una vez tenemos los datos en nuestra colección debemos avisar al
                    // adaptador que la información ha cambiado.
                    adapter.notifyDataSetChanged();
                    Log.d("D","Array: "+adapter.listBrawlers.toString());
                }else{
                    Toast.makeText(Brawlers_Act.this, "Problema al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}