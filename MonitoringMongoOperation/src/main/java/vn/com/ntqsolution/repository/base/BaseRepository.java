/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.com.ntqsolution.repository.base;

import java.util.List;

/**
 *
 * @author tuantv
 */
public interface BaseRepository {
    
    List<Object> getSlowCurrentDBOperation(long secs);
    
}
