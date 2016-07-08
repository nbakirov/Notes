package kg.magnit.notes2016;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NoteAdapter adapter;
    NotesDatabase db;


    /*AlertDialog.Builder ad;
    Context context;
    String title = "Форма подтверждения";
    String message = "Вы действительно хотите удалить все заметки";
    String button1String = "Нет";
    String button2String = "Да"; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         db = new NotesDatabase(MainActivity.this.getApplicationContext());



    }
    @Override
    protected void onStart() {
        super.onStart();

        adapter = new NoteAdapter(MainActivity.this, getData(), MainActivity.this.getApplicationContext());
        ListView dima = (ListView) findViewById(R.id.listView);
        dima.setAdapter(adapter);


        getSupportActionBar().setTitle("Супер заметки (" + String.valueOf(adapter.getCount()) + ")");
        getSupportActionBar().setSubtitle("Категория: основная");

    } //код в активити которая отображает наш списмок


   public ArrayList<Note> getData(){

       final ArrayList<Note> stringItems = new ArrayList<Note>();

       ArrayList<Note> pr = (ArrayList<Note>) db.getAllNotes();   // содержит  все даты
       for (Note p : pr) {
           stringItems.add(p);
       }
       return stringItems;
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
        if (id == R.id.developer) {
            Intent i = new Intent(MainActivity.this, DeveloperInfoActivity.class);
            startActivity(i);
        }



        if (id == R.id.add_note) {
            Intent w = new Intent(MainActivity.this, CreateNoteActivity.class);
            int flag = 0;
            w.putExtra("flag_place", String.valueOf(flag));
            startActivity(w);
        }
        if (id == R.id.show_map) {
            Intent smap = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(smap);
        }

        if (id == R.id.share) {
            Toast t = Toast.makeText(getApplicationContext(),"Скоро и эту тему пройдем",Toast.LENGTH_LONG);
            t.show();
                   }

        if (id == R.id.deleteall) {

            db.deleteAllNotes();

        }
          /*  ad = new AlertDialog.Builder(context);
            ad.setTitle(title);  // заголовок
            ad.setMessage(message); // сообщение
            ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                    Toast.makeText(context, "Вы сделали правильный выбор",
                            Toast.LENGTH_LONG).show();
                }
            });
            ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                    Toast.makeText(context, "Возможно вы правы", Toast.LENGTH_LONG)
                            .show();
                }
            });
            ad.setCancelable(true);
            ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    Toast.makeText(context, "Вы ничего не выбрали",
                            Toast.LENGTH_LONG).show();
                }
            });
*/



        return super.onOptionsItemSelected(item);
    }
}
