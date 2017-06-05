package comh.example.simon.bbm.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comh.example.simon.bbm.R;

import static comh.example.simon.bbm.R.id.parent;

/**
 * Created by 96495 on 2017/5/29.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    private int resourceId;

    public BookAdapter(Context context, int textViewResourceId, List<Book> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Book book = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView textView5 = (TextView) view.findViewById(R.id.query_id);
        TextView textView = (TextView) view.findViewById(R.id.query_name);
        TextView textView1 = (TextView) view.findViewById(R.id.query_type);
        TextView textView2 = (TextView) view.findViewById(R.id.query_author);
        TextView textView3 = (TextView) view.findViewById(R.id.query_pub);
        TextView textView4 = (TextView) view.findViewById(R.id.query_price);
        textView.setText(book.getName());
        textView1.setText(book.getType());
        textView2.setText(book.getAuthor());
        textView3.setText(book.getPublish());
        textView4.setText(book.getPrice());
        textView5.setText(book.getId());
        return view;
    }
}
