package sg.edu.np.mad.practical2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        User usr = new User("Kyler", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua \n test \n test", 1, false);
        */
        Button button1 = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        TextView username = findViewById(R.id.username);
        TextView description = findViewById(R.id.description);
        User usr = (User) getIntent().getSerializableExtra("usr");
        description.setText(usr.Description);
        username.setText(usr.Name);
        /*
        Intent receive = getIntent();
        int randint = receive.getIntExtra("random", -1);
        username.setText("MAD "+ randint);

         */

        button1.setText(usr.Followed ? "Unfollow" : "Follow");
        DBHandler db = new DBHandler(this);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V) {
                if (button1.getText() == "Follow") {
                    button1.setText("Unfollow");
                    Toast toast = Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    button1.setText("Follow");
                    Toast toast = Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT);
                    toast.show();
                }
                usr.Followed = !usr.Followed;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(i);
            }
        });

    }
}