package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    private List<Note> mNotes;
    private OnNoteDeletedListener mOnNoteDeletedListener;

    public void addNewNoteToList(Note note) {
        mNotes.add(note);
        notifyItemInserted(mNotes.size() - 1);
    }

    public interface OnNoteDeletedListener {
        void onNoteDeleted(int position);
    }

    public NotesAdapter(List<Note> notes) {
        mNotes = notes;
    }

    public void setOnNoteDeletedListener(OnNoteDeletedListener listener) {
        mOnNoteDeletedListener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        final Note note = mNotes.get(position);
        holder.bind(note);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mOnNoteDeletedListener != null) {
                    mOnNoteDeletedListener.onNoteDeleted(holder.getAdapterPosition());
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        private TextView mDateTextView;

        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.note_edittext);
            mDateTextView = itemView.findViewById(R.id.date_picker);
        }

        void bind(Note note) {
            mTitleTextView.setText(note.getTitle());
            mDateTextView.setText(note.getDate());
        }
    }
}
