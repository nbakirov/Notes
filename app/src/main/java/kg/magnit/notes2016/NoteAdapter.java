package kg.magnit.notes2016;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class NoteAdapter extends BaseAdapter implements View.OnClickListener {

    final String LOG_TAG = "nb_log";
    Activity activity;

    private ArrayList<Note> rData = new ArrayList<Note>();

    private static LayoutInflater inflater = null;
    private Context mContext;

    public NoteAdapter(Activity a, ArrayList<Note> adapN, Context context) {
        this.mContext = context;
        activity = a;
        rData = adapN;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.d(LOG_TAG, "я здесь - public NoteAdapter (Activity a, ArrayList<String> adapN, ArrayList<String> adapD, Context context)");

    }

    @Override
    public int getCount() {
        Log.d(LOG_TAG, "Вызов функции getCount");
        return rData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onClick(View v) {

    }

    public static class ViewHolder {

        public TextView text;
        public TextView text2;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {
            //****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.notes_list, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.text);
            holder.text2 = (TextView) vi.findViewById(R.id.textTime);


            /************ Set holder with LayoutInflater ************/

            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }
        Note item = rData.get(position);
        Log.d(LOG_TAG, rData + "Note item = rData.get(position);");
        Context context = parent.getContext();
        holder.text.setText(item.getNote_date());


        String bigtext = item.getNote_text();
        Log.d(LOG_TAG, "String bigtext получил значение = item.getNote_text");

        if (bigtext.length() > 40) {
            bigtext = bigtext.substring(0, 30) + "...";
            holder.text2.setText(bigtext);
        } else {


            Log.d(LOG_TAG, "Проверка на 200 символов");
            holder.text2.setText(bigtext);
        }

        /******** Set Item Click Listner for LayoutInflater for each row ***********/

        vi.setOnClickListener(new OnItemClickListener(position, item));

        return vi;
    }

    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;
        private Note n;

        OnItemClickListener(int position, Note note) {
            mPosition = position;
            n = note;
        }

        @Override
        public void onClick(View arg0) {
            Intent myIntent = new Intent(mContext, CreateNoteActivity.class);
            int flag = 1;
            myIntent.putExtra("flag_place", String.valueOf(flag));
            myIntent.putExtra("note_text", n.getNote_text());
            myIntent.putExtra("note_date", n.getNote_date());
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(myIntent);
        }

    }
}
