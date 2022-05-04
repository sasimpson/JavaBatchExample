package com.ke5eo;

import com.ke5eo.Datastore.SQLite.Connect;
import com.ke5eo.models.User;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Connection conn = Connect.connect("jdbc:sqlite:test.db");
        long rustart = System.currentTimeMillis();
        ArrayList<User> users = RandomUser.MakeUserList(1000);
        long ruend = System.currentTimeMillis();
        System.out.printf("get random users: %dms", ruend-rustart);

       /*
        try {
            PreparedStatement stmt = conn.prepareStatement("CREATE TABLE IF NOT EXISTS users\n" +
                    "(\n" +
                    "    id   integer not null\n" +
                    "            primary key autoincrement,\n" +
                    "    name string,\n" +
                    "    age  integer default 0 not null\n" +
                    ")");
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        */

        long start1 = System.currentTimeMillis();
        loopInsert(conn, users);
        long end1 = System.currentTimeMillis();
        System.out.printf("loop insert time: %dms\n", end1-start1);

        long start2 = System.currentTimeMillis();
        batchInsert(conn, users);
        long end2 = System.currentTimeMillis();
        System.out.printf("batch insert time: %dms\n", end2-start2);
    }

    private static void loopInsert(Connection conn, ArrayList<User> users) {
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into users (name, age) values (?, ?)");

            for (User user : users) {
                stmt.setString(1, user.getName());
                stmt.setInt(2, user.getAge());
                stmt.execute();
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void batchInsert(Connection conn, ArrayList<User> users) {
        try {
            PreparedStatement stmt = conn.prepareStatement("insert into users (name, age) values (?, ?)");

            for (User user : users) {
                stmt.setString(1, user.getName());
                stmt.setInt(2, user.getAge());
                stmt.addBatch();
                stmt.clearParameters();
            }
            stmt.executeBatch();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}