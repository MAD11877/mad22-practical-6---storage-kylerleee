package sg.edu.np.mad.practical2;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ArrayList<User> users = new ArrayList<>();
        init();
        DBHandler db = new DBHandler(this);
        users = db.getUsers();
        RecyclerView rv = findViewById(R.id.recyclerView);
        UserAdapter ua = new UserAdapter(users);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setAdapter(ua);
        rv.setLayoutManager(llm);

        /*
        ImageView iv = findViewById(R.id.alert_img);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(ListActivity.this);
                ad.setTitle("Profile");
                ad.setMessage("MADness");
                ad.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                ad.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Random r = new Random();
                        int randint = r.nextInt(1000000000);
                        Intent intent = new Intent(ListActivity.this, MainActivity.class);
                        intent.putExtra("random", randint);
                        startActivity(intent);
                    }
                });
                AlertDialog imgAlert = ad.create();
                imgAlert.show();
            }
        }); */
    }
    public void init(){
        DBHandler db = new DBHandler(this);
        for (int i = 0; i < 20; i++){
            User user = new User();
            int rand1 = ThreadLocalRandom.current().nextInt(-999999999, 999999999);
            user.Name = "Name" + rand1;
            int rand2 = ThreadLocalRandom.current().nextInt(-999999999, 999999999);
            user.Description = "Description" + rand2;
            user.Followed = ThreadLocalRandom.current().nextBoolean();
            user.Id = i;
            //users.add(user);
            Log.d("tab", user.Name);
            db.insertUser(user);
        }
    }
}