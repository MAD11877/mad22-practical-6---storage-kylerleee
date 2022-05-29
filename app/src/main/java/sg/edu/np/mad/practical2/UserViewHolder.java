package sg.edu.np.mad.practical2;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView description;
    public UserViewHolder(View item){
        super(item);
        name = item.findViewById(R.id.usrname);
        description = item.findViewById(R.id.desc);
    }
}
