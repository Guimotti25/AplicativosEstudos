package com.example.motti.appbanco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnInsert;
    Button btnDelete;
    TextView listaNomes;
    DataHelper dB;
    EditText fieldName;
    Button btnUptade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);



            // Novas instâncias dos elementos da View
              dB = new DataHelper(this);
            btnInsert = (Button) findViewById(R.id.insert);
              btnDelete = findViewById(R.id.btnDelete);
              btnUptade = findViewById(R.id.btnUptade);
            listaNomes = (TextView) this.findViewById(R.id.listaNomes);
            fieldName = (EditText) findViewById(R.id.nome);
// Ação do clique do botão INSERT
            btnInsert.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
// Inserção de registro na tabela
                    dB.Insert(fieldName.getText().toString());
// Lista de todos os nomes cadastrados na tabela
                    List<String> names = dB.SelectAll();
// Impressão dos itens cadastrados
                    StringBuilder sb = new StringBuilder();
                    sb.append("Nomes Cadastrados:\n");
                    for (String name : names) {
                        sb.append(name + "\n");
                    }
                    listaNomes.setText(sb.toString());
                }
            });


            btnDelete.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
// esta ok, falta so apagar a lista
                    try {
                    dB.DeleteAll(fieldName.getText());
                    List<String> names = dB.SelectAll();


                    names.clear();
                   // listaNomes.clearComposingText();
                  //  listaNomes.setText("vazio");



                }catch(Exception e){
                       // e.printstacktrace();
                        Log.e("Opa",e.getMessage());
                    }

            }

            });
            btnUptade.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                    List<String> names = dB.SelectAll();
                        StringBuilder sb = new StringBuilder();
                        sb.append("Nomes Cadastrados:\n");
                        for (String name : names) {
                            sb.append(name + "\n");
                        }
                        listaNomes.setText(sb.toString());
                    }catch(Exception e){
                        // e.printstacktrace();
                        Log.e("Opa",e.getMessage());
                    }

                }
            });
    }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
//noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }


