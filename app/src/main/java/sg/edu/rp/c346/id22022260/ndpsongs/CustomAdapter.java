package sg.edu.rp.c346.id22022260.ndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;

    public CustomAdapter(Context context, int resource, ArrayList<Song> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        songList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);
        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvSingers = rowView.findViewById(R.id.tvSinger);
        TextView tvStars = rowView.findViewById(R.id.tvStar);
        TextView tvYear = rowView.findViewById(R.id.tvYear);

        Song songObj = songList.get(position);

        tvTitle.setText(songObj.getTitle());
        tvSingers.setText(songObj.getSingers());
        tvStars.setText(songObj.getStarDisplay());
        tvYear.setText(String.valueOf(songObj.getYear()));

        return rowView;
    }
}
