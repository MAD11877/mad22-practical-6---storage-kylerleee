package sg.edu.np.mad.practical2;

import java.io.Serializable;

public class User implements Serializable {
    String Name;
    String Description;
    Integer Id;
    Boolean Followed;

    public User (String name, String description, Integer id, Boolean followed) {
        this.Name = name;
        this.Description = description;
        this.Id = id;
        this.Followed = followed;
    }

    public User() {
        
    }
}
