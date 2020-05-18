package com.example.securitynotesapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class NoteSelect extends AppCompatActivity {

    private List <NotesBuilder>
    notesList =new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_select);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Open("Note2.txt");
            }
        });

        RecyclerView notesRecycler = (RecyclerView) findViewById(R.id.notes);

        NotesAdapter nAdapter = new NotesAdapter(notesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        notesRecycler.setLayoutManager(mLayoutManager);
        notesRecycler.setItemAnimator(new DefaultItemAnimator());
        notesRecycler.setAdapter(nAdapter);

        prepareNotes();

    }

    private void prepareNotes() {
        File directory;
        directory = getFilesDir();
        File[] files = directory.listFiles();
        String theFile;
        assert files != null;
        for (int f = 1; f  <=files.length; f++){
            theFile = "Note" + f + ".txt";
            NotesBuilder note = new NotesBuilder(theFile, Open(theFile));
            notesList.add(note);
        }

    }

    public String Open(String fileName) {
        String content = "";
        try {
            InputStream in = openFileInput(fileName);
            if (in != null) {
                InputStreamReader tmp = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(tmp);
                String str;
                StringBuilder buf = new StringBuilder();
                while ((str = reader.readLine()) != null) {
                    buf.append(str + "\n");
                }
                in.close();

                content = buf.toString();
            }
        } catch (java.io.FileNotFoundException e) {
        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }

        return content;
    }
}


