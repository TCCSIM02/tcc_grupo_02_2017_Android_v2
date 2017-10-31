package com.doctappo;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MeusAgendamentosActivity extends CommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_agendamentos);
        allowBack();
        setHeaderTitle("Meus agendamentos");

        ListView listview = (ListView) findViewById(R.id.listview);

        super.isLogged();

        String[] dados = new String[] { "Cupcake\nNilton\n2", "Donut", "Eclair", "Froyo", "Gingerbread",
                "Honeycomb", "Ice Cream Sandwich", "Jelly Bean",
                "KitKat", "Lollipop", "Marshmallow", "Nougat" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dados);

        listview.setAdapter(adapter);
    }
}
