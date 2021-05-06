package com.reshome.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

// 특징? 스프링기반에서 본작업 들어가기 전에 jdbc oracle driver 를 이용한 연동테스트

@Log4j
public class JDBCTests {
	static {
		try {
			// DriverManager객체생성이 되어 메모리상에 로딩
			Class.forName("oracle.jdbc.OracleDriver");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String uid = "reshome";
		String pwd = "reshome";
		
		// jdk 1.8문법
		try(Connection con = DriverManager.getConnection(url, uid, pwd)){
			log.info(con);
		}catch(Exception ex) {
			fail(ex.getMessage());
		}
	}
}
