package kg.magnit.notes2016;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNoteActivity extends AppCompatActivity {

    EditText et;
    Button update;
    Button save;
    String note_text;
    String note_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et = (EditText) findViewById(R.id.editText);
        save = (Button) findViewById(R.id.button);
        update = (Button) findViewById(R.id.button_reload);

        int flag = Integer.valueOf(getIntent().getStringExtra("flag_place"));

        if (flag == 0) {
            update.setEnabled(false);

        } else if (flag == 1) {

            save.setEnabled(false);
            note_text = getIntent().getStringExtra("note_text");
            note_date = getIntent().getStringExtra("note_date");
            et = (EditText) findViewById(R.id.editText);
            getSupportActionBar().setTitle("Редактировать заметку (" + note_date + ")");
            et.setText(note_text);
        }

    }

    public void buttontosave(View view) {

        String noteText = et.getText().toString();

        if (noteText.equals("")) {
            Toast tn = Toast.makeText(this, "Заметка не может быть пустой", Toast.LENGTH_LONG);
            tn.show();
        } else {

            NotesDatabase db = new NotesDatabase(CreateNoteActivity.this);

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            String formatedDate = df.format(c.getTime());
            Log.d("formatedDate", formatedDate);


            double lat = 0.0;//широта
            double longT = 0.0;//долгота


            GPSTracker gp = new GPSTracker(CreateNoteActivity.this);

            if (gp.canGetLocation()) {
                lat = gp.getLatitude();
                longT = gp.getLongitude();


            } else {
                gp.showSettingsAlert();
            }

            if (lat == 0.0 || longT == 0.0) {
                Toast tg = Toast.makeText(this, "Координаты не определены /Попробуйте сохранить еще раз/", Toast.LENGTH_SHORT);
                tg.show();
            } else {
                Note note = new Note(noteText, formatedDate, longT, lat);
                Log.d("Note ", note.toString());
                db.addNote(note);
                Log.d("Широта", "Lat" + String.valueOf(lat));
                Log.d("Долгота", "Long" + String.valueOf(longT));
                Toast t = Toast.makeText(this, "Заметка создана", Toast.LENGTH_LONG);
                t.show();
                finish();
            }


        }

    }

    public void update(View view) {
        String refreshedNoteText = et.getText().toString();
        if (refreshedNoteText.equals("")) {
            Toast tm = Toast.makeText(this, "Заметка не может быть пустой", Toast.LENGTH_LONG);
            tm.show();
        } else {

            NotesDatabase db = new NotesDatabase(CreateNoteActivity.this);

            db.updateNoteByDate(note_date, refreshedNoteText);

            Toast t = Toast.makeText(this, "Заметка обновлена", Toast.LENGTH_LONG);
            t.show();
            finish();
        }
    }
}
