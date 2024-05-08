package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;


public class MemberDao {
	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement psmt;
	public MemberDao() {
		try {
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/.h2/sqlprg", "sa", "1234");
			st=con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public List<MemberVO> getMembers() throws SQLException{
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		rs = st.executeQuery("select * from member");
		
		while(rs.next()) {
			MemberVO m = new MemberVO();
			m.setId(rs.getInt(1));
			m.setPass(rs.getString(2));
			m.setName(rs.getString(3));
			m.setRegidate(rs.getDate(4));
			memberList.add(m);
		}
		
		return memberList;
	}
	
	public MemberVO getMemberById(Integer id) throws SQLException {
		MemberVO m = new MemberVO();
		rs=st.executeQuery("select * from member where id="+id);
		if(rs.next()) {
			m.setId(rs.getInt(1));
			m.setPass(rs.getString(2));
			m.setName(rs.getString(3));
			m.setRegidate(rs.getDate(4));
		}
		return  m;
	}
	
	public MemberVO InsertMember(MemberVO m) throws SQLException {
		
		psmt = con.prepareStatement("insert into member (pass,name) values(?,?)");
		psmt.setString(1,m.getPass());
		psmt.setString(2, m.getName());
		psmt.executeUpdate();
		m.setRegidate(new Date());
		return m;
	}
	
	public int UpdateMember(MemberVO m) throws SQLException {
		if(getMemberById(m.getId())==null) {
			return 0;
		}
		String query;
		if(m.getName()!=null&&m.getPass()!=null)
			query = String.format("update member set name='%s', pass='%s' where id=%d",m.getName(),m.getPass(),m.getId());
		else if(m.getName()!=null)
			query = String.format("update member set name='%s' where id=%d",m.getName(),m.getId());
		else if(m.getPass()!=null)
			query = String.format("update member set pass='%s' where id=%d",m.getPass(),m.getId());
		else
			return 0;
		int result = st.executeUpdate(query);
		return result;
	}
	
	public int DeleteMember(Integer id) throws SQLException {
		if(getMemberById(id)==null)
			return 0;
		int result = st.executeUpdate("delete from member where id="+id);
		return result;
	}
}
