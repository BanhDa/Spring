/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.ntqsolution.repository.impl;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import vn.com.ntqsolution.repository.base.BaseRepository;

/**
 *
 * @author tuantv
 */
@Repository
@AllArgsConstructor
public class RepositoryImpl implements BaseRepository{

    private static final String CURRENT_OP_QUERY = "db.currentOp({\"secs_running\": {$gt:%d}})";
    
    private final MongoClient mongoClient;
    
    @Override
    public List<Object> getSlowCurrentDBOperation(long secs) {
        List<Object> result = new ArrayList<>();
        
        Collection<DB> dbs = mongoClient.getUsedDatabases();
        String query = String.format(CURRENT_OP_QUERY, secs);
        dbs.forEach(db -> {
            Object re = db.eval(query);
            if (re != null) {
                result.add(re);
            }
        });
        
        return result;
    }
}
