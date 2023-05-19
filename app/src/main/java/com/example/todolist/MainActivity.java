package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private NotesAdapter mAdapter;

    // Список заметок
    private List<Note> mNoteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация RecyclerView и адаптера
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NotesAdapter(mNoteList);
        mRecyclerView.setAdapter(mAdapter);

        //Добавление заметки
        Button addNoteBtn = findViewById(R.id.add_note_btn);
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText noteEditText = findViewById(R.id.note_edittext);
                String noteTitle = noteEditText.getText().toString().trim();

                // Получение даты из DatePicker
                DatePicker datePicker = findViewById(R.id.date_picker);
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String date = day + "/" + month + "/" + year;

                // Создание новой заметки и добавление ее в список
                if (!TextUtils.isEmpty(noteTitle)) {
                    Note newNote = new Note(noteTitle, date);
                    mAdapter.addNewNoteToList(newNote);
                    noteEditText.getText().clear();
                }
            }
        });

        // Удаление заметки
        mAdapter.setOnNoteDeletedListener(new NotesAdapter.OnNoteDeletedListener() {
            @Override
            public void onNoteDeleted(int position) {
                mNoteList.remove(position);
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}