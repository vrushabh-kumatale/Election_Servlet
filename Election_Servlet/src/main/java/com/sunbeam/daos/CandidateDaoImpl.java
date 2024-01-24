package com.sunbeam.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.sunbeam.pojos.Candidate;

public class CandidateDaoImpl extends Dao implements CandidateDao {

	public CandidateDaoImpl() throws Exception {
		
	}

	@Override
	public int save(Candidate c) throws Exception {
		String sql = "insert into Candidates values(default, ?, ?, ?)";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, c.getName());
			stmt.setString(2, c.getParty());
			stmt.setInt(3, c.getVotes());
			return stmt.executeUpdate();
		}
	
	}

	@Override
	public int deleteById(int id) throws Exception {
		String sql = "DELETE from Candidates WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			int cnt = stmt.executeUpdate();
			return cnt;
			
		}
	
	}

	@Override
	public List<Candidate> findAll() throws Exception {
		List<Candidate> list = new ArrayList();
		String sql = "SELECT * FROM candidates";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery(sql)) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String name  = rs.getString("name");
					String party = rs.getString("party");
					int votes = rs.getInt("votes");
					Candidate c = new Candidate(id, name, party, votes);
					list.add(c);
				}
			}
		}
		return list;
	}

	@Override
	public int incrementVote(int candidateId) throws Exception {
		String sql = "UPDATE candidates SET votes=votes+1 where id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, candidateId);
			int cnt = stmt.executeUpdate(); 
			return cnt;
		}
		
	}

	@Override
	public List<Candidate> findByParty(String givenParty) throws Exception {
		List<Candidate> list = new ArrayList<>();
		String sql = "SELECT * from candidates where party=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, "givenParty");
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String party = rs.getString("party");
					int votes = rs.getInt("votes");
					Candidate c = new Candidate(id, name, party, votes);
					list.add(c);
				}
			}
		}
		return list;
	}

	@Override
	public Candidate findById(int id) throws Exception {
		String sql = "select * from candidates where id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					id = rs.getInt("id");
					String name = rs.getString("name");
					String party = rs.getString("party");
					String votes  = rs.getString("votes");
					Candidate c = new Candidate(id, name, party, id);
					return c;
				}
			}
		}
		return null;
	}

}