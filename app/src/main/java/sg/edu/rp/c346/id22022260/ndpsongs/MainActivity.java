package sg.edu.rp.c346.id22022260.ndpsongs;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etSongTitle, etSingers, etYear;
    RadioGroup rgStars;
    Button btnInsert, btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("NDP Songs");
        }

        etSongTitle = findViewById(R.id.etSongTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String songTitle = etSongTitle.getText().toString();
                String singers = etSingers.getText().toString();
                String yearString = etYear.getText().toString();

                if (songTitle.isEmpty()
                        || singers.isEmpty()
                        || yearString.isEmpty()
                ) {
                    Toast.makeText(MainActivity.this, "Field(s) cannot be empty.", Toast.LENGTH_LONG).show();
                    return;
                }

                if (yearString.length() != 4) {
                    Toast.makeText(MainActivity.this, "Year has to have a length of 4.", Toast.LENGTH_LONG).show();
                    return;
                }

                int year = Integer.parseInt(yearString);
                int starsId = rgStars.getCheckedRadioButtonId();
                int stars = 0;

                if (starsId == R.id.rbOneStar) {
                    stars = 1;
                } else if (starsId == R.id.rbTwoStar) {
                    stars = 2;
                } else if (starsId == R.id.rbThreeStar) {
                    stars = 3;
                } else if (starsId == R.id.rbFourStar) {
                    stars = 4;
                } else if (starsId == R.id.rbFiveStar) {
                    stars = 5;
                }

                DBHelper db = new DBHelper(MainActivity.this);
                db.insertSong(songTitle, singers, year, stars);

                Toast.makeText(MainActivity.this, "Added song", Toast.LENGTH_SHORT).show();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SongListActivity.class);
                startActivity(intent);
            }
        });
    }
}