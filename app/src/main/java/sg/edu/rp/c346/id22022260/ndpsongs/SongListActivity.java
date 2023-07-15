package sg.edu.rp.c346.id22022260.ndpsongs;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

public class SongListActivity extends AppCompatActivity {

    ListView lv;
    Button btnStarFilter;
    Spinner spinner;
    ArrayList<Song> songList = new ArrayList<Song>();
    ArrayList<Song> songs;
    ArrayList<String> spinnerList = new ArrayList<String>();
    ArrayAdapter<Song> aaSongs;
    ArrayAdapter<String> aaSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Song List");
        }

        lv = findViewById(R.id.lv);
        aaSongs = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, songList);
        lv.setAdapter(aaSongs);

        spinner = findViewById(R.id.spinner);
        aaSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerList);
        aaSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aaSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    loadSongs(false);
                } else {
                    loadSongs(Integer.parseInt(spinnerList.get(position)));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SongListActivity.this, EditSongActivity.class);
                intent.putExtra("data", songs.get(position));
                startActivity(intent);
            }
        });

        btnStarFilter = findViewById(R.id.btnStarFilter);
        btnStarFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSongs(true);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSongs(false);
        spinner.setSelection(0);
    }

    private void loadSongs(Boolean fiveStarsOnly) {
        DBHelper db = new DBHelper(SongListActivity.this);
        ArrayList<String> yearsList = db.getYears();
        spinnerList.clear();
        spinnerList.add("Show All");
        spinnerList.addAll(yearsList);
        aaSpinner.notifyDataSetChanged();

        if (fiveStarsOnly) {
            songs = db.getSongs(true);
        } else {
            songs = db.getSongs();
        }

        db.close();

        songList.clear();
        songList.addAll(songs);
        aaSongs.notifyDataSetChanged();
    }

    private void loadSongs(int year) {
        DBHelper db = new DBHelper(SongListActivity.this);
        songs = db.getSongs(year);
        db.close();

        songList.clear();
        songList.addAll(songs);
        aaSongs.notifyDataSetChanged();
    }
}