package sg.edu.rp.c346.id22022260.ndpsongs;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class EditSongActivity extends AppCompatActivity {

    EditText etSongId, etSongTitle, etSingers, etYear;
    RadioGroup rgStars;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Edit Song");
        }

        etSongId = findViewById(R.id.etSongId);
        etSongTitle = findViewById(R.id.etSongTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        data = (Song) intent.getSerializableExtra("data");

        etSongId.setText(String.valueOf(data.get_id()));
        etSongTitle.setText(data.getTitle());
        etSingers.setText(data.getSingers());
        etYear.setText(String.valueOf(data.getYear()));

        if (data.getStars() == 1) {
            rgStars.check(R.id.rbOneStar);
        } else if (data.getStars() == 2) {
            rgStars.check(R.id.rbTwoStar);
        } else if (data.getStars() == 3) {
            rgStars.check(R.id.rbThreeStar);
        } else if (data.getStars() == 4) {
            rgStars.check(R.id.rbFourStar);
        } else if (data.getStars() == 5) {
            rgStars.check(R.id.rbFiveStar);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setTitle(etSongTitle.getText().toString());
                data.setSingers(etSingers.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));

                if (rgStars.getCheckedRadioButtonId() == R.id.rbOneStar) {
                    data.setStars(1);
                } else if (rgStars.getCheckedRadioButtonId() == R.id.rbTwoStar) {
                    data.setStars(2);
                } else if (rgStars.getCheckedRadioButtonId() == R.id.rbThreeStar) {
                    data.setStars(3);
                } else if (rgStars.getCheckedRadioButtonId() == R.id.rbFourStar) {
                    data.setStars(4);
                } else if (rgStars.getCheckedRadioButtonId() == R.id.rbFiveStar) {
                    data.setStars(5);
                }

                DBHelper dbh = new DBHelper(EditSongActivity.this);
                dbh.updateSong(data);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditSongActivity.this);
                dbh.deleteSong(data.get_id());
                dbh.close();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}