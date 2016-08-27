package com.github.apska.webapp.storage;

import com.github.apska.webapp.WebAppException;
import com.github.apska.webapp.model.Resume;
import com.github.apska.webapp.sql.ConnectionFactory;
import com.github.apska.webapp.sql.Sql;
import com.github.apska.webapp.sql.SqlExecutor;

import java.sql.*;
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
        sql.execute("INSERT INTO resume (uuid, full_name, location, home_page) VALUES (?, ?, ?, ?)",
                new SqlExecutor<Object>() {
                    @Override
                    public Object execute(PreparedStatement st) throws SQLException {
                        st.setString(1, r.getUuid());
                        st.setString(2, r.getFullName());
                        st.setString(3, r.getLocation());
                        st.setString(4, r.getHomePage());
                        st.execute();
                        return null;
                    }
                });
    }

    @Override
    public void update(Resume r) {
        sql.execute("UPDATE resume SET full_name=?, location=?, home_page=? WHERE uuid=?",
                new SqlExecutor<Object>() {
                    @Override
                    public Object execute(PreparedStatement st) throws SQLException {
                        st.setString(1, r.getFullName());
                        st.setString(2, r.getLocation());
                        st.setString(3, r.getHomePage());
                        st.setString(4, r.getUuid());
                        if(st.executeUpdate() == 0){
                            throw new WebAppException("Resume with uuid \"" + r.getUuid() + "\" not exist.", r.getUuid());
                        }
                        return null;
                    }
                });
    }

    @Override
    public void delete(String uuid) {
        sql.execute("DELETE FROM resume WHERE uuid=?",
                new SqlExecutor<Object>() {
                    @Override
                    public Object execute(PreparedStatement ps) throws SQLException {
                        ps.setString(1, uuid);
                        if (ps.executeUpdate() == 0){
                            throw new WebAppException("Resume with uuid \"" + uuid + "\" not exist.", uuid);
                        }
                        return null;
                    }
                }
        );
    }

    @Override
    public Resume load(final String uuid) {
        return sql.execute("SELECT r.uuid, r.full_name, r.location, r.home_page " +
                        "FROM resume r " +
                        "WHERE r.uuid=?",
                new SqlExecutor<Resume>() {
                    @Override
                    public Resume execute(PreparedStatement ps) throws SQLException {
                        ps.setString(1, uuid);
                        ResultSet rs = ps.executeQuery();
                        if (!rs.next()){
                            throw new WebAppException("Resume with uuid \"" + uuid + "\" not exist.", uuid);
                        }

                        Resume r = new Resume(uuid, rs.getString("full_name"), rs.getString("location"));

                        return r;
                    }
                }
        );
    }

    @Override
    public Collection<Resume> getAllSorted() {
        /*return sql.execute("SELECT uuid, full_name, location, home_page " +
                        "FROM resume " +
                        "ORDER BY full_name, uuid",
                new SqlExecutor<Resume>() {
                    @Override
                    public Resume execute(PreparedStatement ps) throws SQLException {
                        ps.setString(1, uuid);
                        ResultSet rs = ps.executeQuery();
                        if (!rs.next()){
                            throw new WebAppException("Resume with uuid \"" + uuid + "\" not exist.", uuid);
                        }

                        Resume r = new Resume(uuid, rs.getString("full_name"), rs.getString("location"));

                        return r;
                    }
                }
        );*/

        return null;
    }

    @Override
    public int size() {
        return sql.execute("SELECT count(uuid) FROM resume",
                new SqlExecutor<Integer>() {
                    @Override
                    public Integer execute(PreparedStatement ps) throws SQLException {
                        ResultSet rs = ps.executeQuery();
                        rs.next();

                        return rs.getInt(1);
                    }
                }
        );
    }

    @Override
    public boolean isSectionSupported() {
        return false;
    }
}
