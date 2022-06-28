package sg.edu.np.mad.practical2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://mad-week-6-3e98f-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference reference = db.getReference("Users");
        EditText Password = findViewById(R.id.EditPassword);
        EditText Name = findViewById(R.id.EditUsername);
        Button loginButton = findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Iname = Name.getText().toString();
                String Ipassword = Password.getText().toString();
                reference.child("mad").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String checkPassword = snapshot.child("password").getValue(String.class);
                        String checkName = snapshot.child("username").getValue(String.class);
                        if (checkPassword.equals(Ipassword) && checkName.equals(Iname)) {
                            Intent Login_to_List = new Intent(LoginPage.this, ListActivity.class);
                            startActivity(Login_to_List);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });
    }
}