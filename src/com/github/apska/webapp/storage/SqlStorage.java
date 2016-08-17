package com.github.apska.webapp.storage;

import com.github.apska.webapp.model.Resume;
import com.github.apska.webapp.sql.ConnectionFactory;
import com.github.apska.webapp.sql.Sql;
import com.github.apska.webapp.sql.SqlExecutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by APS2
 * on 14.08.2016
 */
public class SqlStorage implements IStorage {
    public Sql sql;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sql = new Sql(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        /*sql.execute("DELETE FROM resume", new SqlExecutor<Void>()){
            @Override
            public Void execute(PreparedStatement ps) throws SQLException{
                ps.execute();
                return null;
            }
        }*/

        sql.execute("DELETE FROM resume");
    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume load(String uuid) {
        return sql.execute("SELECT r.uuid, r.full_name, r.location, r.home_page " +
                        "FROM resume r " +
                        "WHERE r.uuid=?",
                new SqlExecutor<Resume>() {
                    @Override
                    public Resume execute(PreparedStatement ps) throws SQLException {
                        return null;
                    }
                }
        );
    }

    @Override
    public Collection<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isSectionSupported() {
        return false;
    }
}
