package com.lukire.activityapp.user;


import com.lukire.activityapp.database.Cache;
import com.lukire.activityapp.database.Database;
import com.lukire.activityapp.database.Query;
import org.apache.tomcat.jni.Time;

import java.util.HashMap;

public class User {

    private static Cache<String, User> userCache = new Cache<String, User>();

    private static final Query query = new Query("user");

    private String name;
    private int ID;
    private String password;

    public static User getUser(String name) {
        return userCache.containsKey(name)?userCache.get(name):new User(name);
    }

    private User(String name) {
        userCache.put(name, this, Time.now()+1200000);

        this.name=name;

        String[] args = {"ID", "name", "password"};

        HashMap<String, String> data = query.selectUniqueColumn(args, new Query.Condition(Query.ConditionType.EQUALS, "name", name));

        this.ID=Integer.parseInt(data.get("ID"));

    }

    public static boolean registerUser(String name, String password) {
        if (User.userExists(name)) {
           return false;
        }

        Database.getDatabase().insert("INSERT INTO user (name, password) VALUES ('"+name+"','"+password+"')");
        return true;
    }

    public static boolean userExists(String name) {
        return query.containsPrimaryKey("name", name);
    }

    public int getID() { return this.ID; }
    public String getName() {  return this.name; }
    public String getPassword() { return this.password; }
}
