package es.upsa.papps.trabajoandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Notas extends AppCompatActivity {

    private static final String PREFS_NAME = "Preferencias";
    private static final String KEY_NOTE_COUNT = "Contador";
    private LinearLayout ContenedorNotas;
    private List<Notas2> ListaNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        ContenedorNotas = findViewById(R.id.ContenedorNotas);
        Button Guardar = findViewById(R.id.botonGuardar);

        ListaNotas = new ArrayList<>();

        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardarNota();
            }
        });

        loadNotesFromPreferences();
        displayNotes();
    }

    private void displayNotes() {
        for (Notas2 notas2 : ListaNotas) {
            crearNoteView(notas2);
        }
    }

    private void loadNotesFromPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int noteCount = sharedPreferences.getInt(KEY_NOTE_COUNT, 0);

        for (int i = 0; i < noteCount; i++) {
            String title = sharedPreferences.getString("note_title_" + i, "");
            String content = sharedPreferences.getString("note_content_" + i, "");

            Notas2 notas2 = new Notas2();
            notas2.setTitle(title);
            notas2.setContent(content);

            ListaNotas.add(notas2);
        }
    }
    private void GuardarNota() {
        EditText TextoEditable = findViewById(R.id.TextoEditable);
        EditText Contenido = findViewById(R.id.Contenido);

        String titulo = TextoEditable.getText().toString();
        String contenido = Contenido.getText().toString();

        if (!titulo.isEmpty() && !contenido.isEmpty()) {
            Notas2 notas2 = new Notas2();
            notas2.setTitle(titulo);
            notas2.setContent(contenido);

            ListaNotas.add(notas2);
            GuardarPreferencias();

            crearNoteView(notas2);
            LimpiarInputFields();
        }
    }

    private void LimpiarInputFields() {
        EditText titleEditText = findViewById(R.id.TextoEditable);
        EditText contentEditText = findViewById(R.id.Contenido);

        titleEditText.getText().clear();
        contentEditText.getText().clear();
    }

    private void crearNoteView(final Notas2 notas2) {
        View noteView = getLayoutInflater().inflate(R.layout.activity_notas2, null);
        TextView titleTextView = noteView.findViewById(R.id.tituloTextView);
        TextView contentTextView = noteView.findViewById(R.id.contenidoTextView);

        titleTextView.setText(notas2.getTitulo());
        contentTextView.setText(notas2.getContenido());

        noteView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogoEliminar(notas2);
                return true;
            }
        });

        ContenedorNotas.addView(noteView);
    }
    private void DialogoEliminar(final Notas2 notas2) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar esta nota.");
        builder.setMessage("Seguro que quieres eliminarla?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EliminarYRefrescar(notas2);
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void EliminarYRefrescar(Notas2 notas2) {
        ListaNotas.remove(notas2);
        GuardarPreferencias();
        refrescarNoteViews();
    }

    private void refrescarNoteViews() {
        ContenedorNotas.removeAllViews();
        displayNotes();
    }

    private void GuardarPreferencias() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(KEY_NOTE_COUNT, ListaNotas.size());
        for (int i = 0; i < ListaNotas.size(); i ++) {
            Notas2 notas2 = ListaNotas.get(i);
            editor.putString("Titulo_nota" + i, notas2.getTitulo());
            editor.putString("Contenido" + i, notas2.getContenido());
        }
        editor.apply();
    }
}