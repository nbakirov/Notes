package kg.magnit.notes2016;

/**
 * Created by Администратор on 23.05.2016.
 */
public class Note {
    int id;
    String note_text;
    String note_date;
   Double longt; //долгота
   Double latit; //широта




    public Double getLatit() {
        return latit;
    }

    public void setLatit(Double latit) {
        this.latit = latit;
    }
    public Double getLongt() {
        return longt;
    }

    public void setLongt(Double longt) {
        this.longt = longt;
    }

    public int getId() {
        return id;
    }

    public String getNote_text() {
        return note_text;
    }

    public String getNote_date() {
        return note_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNote_text(String note_text) {
        this.note_text = note_text;
    }

    public void setNote_date(String note_date) {
        this.note_date = note_date;
    }

    public Note() {

    }

    public Note(String s, String d) { // конструктор перед занесением в базу данных
        this.note_text = s;
        this.note_date = d;

    }


    public Note(int i, String s, String d) {
        this.id = i;
        this.note_text = s;
        this.note_date = d;

    }

    public Note(int id, String note_text, String note_date, Double longt, Double latit) {
        this.id = id;
        this.note_text = note_text;
        this.note_date = note_date;
        this.longt = longt;
        this.latit = latit;
    }

    public Note(String note_text, String note_date, Double longt, Double latit) {
        this.note_text = note_text;
        this.note_date = note_date;
        this.longt = longt;
        this.latit = latit;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", note_text='" + note_text + '\'' +
                ", note_date='" + note_date + '\'' +
                ", longt=" + longt +
                ", latit=" + latit +
                '}';
    }
}
