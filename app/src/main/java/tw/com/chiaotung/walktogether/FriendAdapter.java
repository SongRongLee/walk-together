package tw.com.chiaotung.walktogether;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by User on 2016/3/16.
 */
public class FriendAdapter extends BaseAdapter {
    String [] result;
    Context context;
    int [] imageId;
    int [] steps;
    private static LayoutInflater inflater=null;
    public FriendAdapter(Activity a, String[] userNameList, int[] userImages, int[] userStepList) {
        // TODO Auto-generated constructor stub
        result=userNameList;
        context=a;
        imageId=userImages;
        steps = userStepList;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView t_name;
        TextView t_steps;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.friend_item, null);
        holder.t_name=(TextView) rowView.findViewById(R.id.text_friend_name);
        holder.t_steps=(TextView) rowView.findViewById(R.id.text_friend_steps);
        holder.img=(ImageView) rowView.findViewById(R.id.image_friend);

        holder.t_name.setText(result[position]);
        String S = String.valueOf(steps[position]) + " steps";
        holder.t_steps.setText(S);
        holder.img.setImageResource(imageId[position]);
/*
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
            }
        });
        */
        return rowView;
    }



}