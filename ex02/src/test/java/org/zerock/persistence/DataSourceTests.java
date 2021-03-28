package org.zerock.persistence;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
// Java 설정을 사용하는 경우
// @ContextConfiguration(classes= {RootConfig.class})
@Log4j
public class DataSourceTests {
    
    @Autowired
    private DataSource ds;
 
    @Test
    public void testConnection() {
    	
        try (Connection con = ds.getConnection()) {
 
        	log.info(con);
            System.out.println("JDBC연결 성공 !!");
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
        
	@Test
    public void testMyBatis() {
    	
        try (SqlSession session = sqlSessionFactory.openSession();
						Connection con = session.getConnection();) {
 
        	log.info(session);
					log.info(con);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}