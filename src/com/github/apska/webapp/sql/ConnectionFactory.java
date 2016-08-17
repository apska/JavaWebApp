package com.github.apska.webapp.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by APS2
 * on 17.08.2016
 */
public interface ConnectionFactory {
    Connection getConnection() throws SQLException;
}
