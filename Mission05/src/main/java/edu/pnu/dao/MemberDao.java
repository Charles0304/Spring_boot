package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.pnu.domain.MemberVO;

@Repository
public class MemberDao {
	Connection con;

	public MemberDao() {
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "1234");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public List<MemberVO> getMembers() throws SQLException{
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		 Statement st = con.createStatement();
		 ResultSet rs = st.executeQuery("select * from member");
		
		while(rs.next()) {
			MemberVO m = new MemberVO();
			m.setId(rs.getInt(1));
			m.setPass(rs.getString(2));
			m.setName(rs.getString(3));
			m.setRegidate(rs.getDate(4));
			memberList.add(m);
		}
		rs.close();
		st.close();
		return memberList;
	}
	
	public Map<String,Object> getMemberById(Integer id){
		MemberVO m = new MemberVO();
		Map<String,Object> log= new HashMap<String,Object>();
		String sql = "select * from member where id="+id;
		log.put("sql", sql);
		boolean flag = true;
		try {
		Statement st = con.createStatement();
		ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				m.setId(rs.getInt(1));
				m.setPass(rs.getString(2));
				m.setName(rs.getString(3));
				m.setRegidate(rs.getDate(4));
			}
		}catch(Exception e) {
			e.printStackTrace();
			flag = true;
		}
		log.put("isSuccess",flag);
		log.put("member",m);
		log.put("method","getMember");
		return log;
	}
	
	public Map<String,Object> InsertMember(MemberVO m){
		Map<String, Object> map = new HashMap<String,Object>();
		boolean flag = true;
		try {
			PreparedStatement psmt = con.prepareStatement("insert into member (pass,name) values(?,?)");
			psmt.setString(1,m.getPass());
			psmt.setString(2, m.getName());
			psmt.executeUpdate();
			psmt.close();
		}catch(Exception e) {
			e.printStackTrace();
			flag=false;
		}
		m.setRegidate(new Date());
		map.put("member", m);
		map.put("method", "insert");
		map.put("sql",String.format("insert into member (pass,name) values(%s,%s)", m.getPass(),m.getName()));
		map.put("isSuccess", flag);
		
		return map;
	}
	
	public Map<String,Object> UpdateMember(MemberVO m){
		Map<String, Object> map = new HashMap<String,Object>();
		
		boolean flag = true;
		
		if(getMemberById(m.getId())==null) {
			flag=false;
		}
		int result = 0;
		String query = null;
		if(m.getName()!=null&&m.getPass()!=null)
			query = String.format("update member set name='%s', pass='%s' where id=%d",m.getName(),m.getPass(),m.getId());
		else if(m.getName()!=null)
			query = String.format("update member set name='%s' where id=%d",m.getName(),m.getId());
		else if(m.getPass()!=null)
			query = String.format("update member set pass='%s' where id=%d",m.getPass(),m.getId());
		else
			flag=false;
		try {
			Statement st = con.createStatement();
			result = st.executeUpdate(query);
			st.close();
		}catch(Exception e) {
			e.printStackTrace();
			flag = false;
		}
		map.put("isSuccess", flag);
		map.put("sql", query);
		map.put("method", "update");
		map.put("result", result);
		return map;
	}
	
	public Map<String,Object> DeleteMember(Integer id){
		Map<String, Object> map = new HashMap<String,Object>();
		boolean flag = true;
		int result=0;
		String query="delete from member where id="+id;
		if(getMemberById(id)==null)
			flag = false;
		try {
			Statement st = con.createStatement();
			result = st.executeUpdate(query);
			st.close();
		}catch(Exception e) {
			e.printStackTrace();
			flag=false;
		}
		map.put("isSuccess", flag);
		map.put("sql", query);
		map.put("method", "delete");
		map.put("result", result);
		return map;
	}
	

}

