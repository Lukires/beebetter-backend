package com.lukire.activityapp.database;

import java.sql.ResultSet;
import java.util.HashMap;

public class Query {

    private static final Database database = Database.getDatabase();

    private String table;

    public Query(String table) {
        this.table=table;
    }

    public boolean containsPrimaryKey(String item, String entry) {
        ResultSet rs = database.select("SELECT "+item+" FROM "+table+" WHERE "+item+" EQUALS "+entry);

        try {
            return rs.next();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public HashMap<String, String> selectUniqueColumn(String args[], Condition con) {
        StringBuilder query = new StringBuilder();

        for (String arg : args) {
            query.append(arg+" ");
        }

        ResultSet rs = database.select("SELECT "+query.toString()+" FROM "+table+" WHERE "+con.toString());

        try{
            HashMap<String, String> data = new HashMap<String, String>();
            for (String arg : args) {
                data.put(arg, rs.getString(arg));
            }
            return data;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public void insert(HashMap<String, String> input) {
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        for(String key : input.keySet()) {
            columns.append(key+", ");
            values.append("'"+input.get(key)+"', ");
        }

        String columnString = columns.toString().substring(columns.length()-2);
        String valueString = values.toString().substring(values.length()-2);

        database.insert("INSERT INTO "+table+" ("+columnString+") VALUES ("+valueString+")");
    }

    public static class Condition {

        private ConditionType type;
        private String item;
        private String compare;
        public Condition(ConditionType type, String item, String compare) {
            this.type=type;
            this.item=item;
            this.compare=compare;
        }

        public String toString() {
            return "WHERE "+item + " " + type.getParameter() + " "+compare;
        }

    }
    public enum ConditionType {
        EQUALS("EQUALS");

        private String parameter;
        ConditionType(String parameter) {
            this.parameter=parameter;
        }

        public String getParameter() {
            return this.parameter;
        }
    }
}
