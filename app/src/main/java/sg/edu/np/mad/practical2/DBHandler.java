package sg.edu.np.mad.practical2;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    public DBHandler(Context c) {
        super(c, "users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USERS_TABLE = "CREATE TABLE user (name TEXT, description TEXT, id INTEGER, followed INTEGER)";
        sqLiteDatabase.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
        onCreate(sqLiteDatabase);
    }

    public void insertUser(User u) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select * from user", null);


        int i = 0;
        if (u.Followed == false) {
            i = 0;
        } else {
            i = 1;
        }
        db.execSQL("Insert into user VALUES(\"" + u.Name + "\", \"" + u.Description + "\", \"" + u.Id + "\", \"" + i + "\")");
        Log.d("tab", u.Name);
        db.close();


        //Insert into User VALUES("username", "description", "id", "followed")
    }
    public ArrayList<User> getUsers(){
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<User> userList = new ArrayList<>();

        Cursor c = db.rawQuery("Select * from user", null);
        while(c.moveToNext()){
            String name = c.getString(0);
            String description = c.getString(1);
            Integer id = c.getInt(2);
            Integer temp = c.getInt(3);
            Boolean followed;
            if (temp == 1){
                followed = true;
            }
            else{
                followed = false;
            }
            User u = new User(name, description, id, followed);
            userList.add(u);
        }

        return userList;
    }
    public void updateUser(User u){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer i = 0;
        if(u.Followed == false){
            i = 0;
        }
        else{
            i = 1;
        }
        db.execSQL("UPDATE User SET Followed = " + i + " WHERE Id = " + u.Id);
    }
}

