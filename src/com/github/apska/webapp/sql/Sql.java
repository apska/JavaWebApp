package com.github.apska.webapp.sql;

import com.github.apska.webapp.WebAppException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by APS2
 * on 17.08.2016
 */
public class Sql {
    private final ConnectionFactory connectionFactory;

    public Sql(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sql){
        execute(sql, (PreparedStatement ps) -> {
            ps.execute();
            return null;
        });

        /*try(Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.execute();
        }catch(SQLException e){
            throw new WebAppException("SQL failed: " + sql, e);
        }*/
    }

    public <T> T execute(String sql, SqlExecutor<T> executor){
        try(Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            return executor.execute(ps);
        }catch(SQLException e){
            throw new WebAppException("SQL failed: " + sql, e);
        }
    }
}
