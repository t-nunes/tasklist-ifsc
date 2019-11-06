/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transformers;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Task;

/**
 *
 * @author thiago
 */
public class TaskTransformer {
    static public Task transform(ResultSet resultSet) {
        try {
            return new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getInt("wasted_seconds"),
                        resultSet.getString("created_at"),
                        resultSet.getString("updated_at"),
                        resultSet.getString("started_play"),
                        resultSet.getBoolean("active")
                );
        } catch(SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}