package br.com.leandroap.democontextactionbar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.leandroap.democontextactionbar.adapter.SelectionAdapter;

public class MainActivity extends ActionBarActivity {

    private ListView lvLista;
    private SelectionAdapter adapter;
    private String[] data = {"Fiesta", "Vectra", "Civic", "i30", "Gol",
            "Golf", "Camaro", "Stilo", "Corolla", "March", "Cruze",
            "Onix", "Fusion", "Jetta", "Porche"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvLista = (ListView) findViewById(R.id.lvLista);
        adapter = new SelectionAdapter(
                this,
                R.layout.row_list_item,
                R.id.tvText,
                data);

        lvLista.setAdapter(adapter);
        lvLista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvLista.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            private int nr = 0;
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean cheched) {

                if (cheched) {
                    nr++;
                    adapter.setNewSelection(position, cheched);
                } else {
                    nr--;
                    adapter.removeSelection(position);
                }

                mode.setTitle(nr + " selecionados");
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.contextual_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_delete:
                        adapter.clearSelection();
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                adapter.clearSelection();
                nr = 0;
            }
        });

        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                lvLista.setItemChecked(position, !adapter.isPositionChecked(position));
                return false;
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
