package sg.edu.np.mad.practical2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{

    ArrayList<User> data;
    public UserAdapter(ArrayList<User> data){
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
      String users = data.get(position).Name;
      String lastNum = users.substring(users.length() - 1);
      if(lastNum.equals("7")){
          return 1;
      }
      return 0;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;
        if(viewType == 1) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.seventh_layout, parent, false);
        }
        else {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.default_layout, null, false);
        }
        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = data.get(position);
        holder.name.setText(user.Name);
        holder.description.setText(user.Description);

        ImageView img2 = holder.itemView.findViewById(R.id.userImg);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(img2.getContext());
                alert.setTitle("Profile");
                alert.setMessage(user.Name);
                alert.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent to_main = new Intent(img2.getContext(), MainActivity.class);
                        to_main.putExtra("usr", user);
                        img2.getContext().startActivity(to_main);
                    }
                });
                alert.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
