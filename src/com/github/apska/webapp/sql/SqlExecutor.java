package com.github.apska.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by APS2
 * on 17.08.2016
 */
public interface SqlExecutor<T> {
    T execute(PreparedStatement st) throws SQLException;
}
