package com.halim.repo.impl;

//
//@Repository
//public class UserRepositoryImpl implements UserRepository {
//
//	@Autowired
//	NamedParameterJdbcTemplate npJdbcTemplate;
//
//	@Override
//	public User getUserByUserName(String username) {
//		SqlParameterSource ps = new MapSqlParameterSource().addValue("email", username);
//		String query = "select * from user where email =:email;";
//		User user = npJdbcTemplate.queryForObject(query, ps, new BeanPropertyRowMapper<User>(User.class));
//		return user;
//	}
//
//	@Override
//	public void save(User user) {
//
//		SqlParameterSource ps1 = new MapSqlParameterSource();
//		String query1 = "select count(id) from user;";
//		int id  = 100 +  npJdbcTemplate.queryForObject(query1, ps1, Integer.class);
//		SqlParameterSource ps = new MapSqlParameterSource()
//				.addValue("id", id)
//				.addValue("name", user.getName())
//		        .addValue("email", user.getEmail())
//		    	.addValue("is_admin", user.isIs_admin())
//		        .addValue("password", user.getPassword())
//		        .addValue("number", user.getNumber())
//		        .addValue("role", user.getRole());
//		String query = "INSERT INTO user(id , name, email, password, role, number, is_admin)\r\n"
//				+ "VALUES (:id, :name, :email, :password, :role, :number, :is_admin);";
//		npJdbcTemplate.update(query, ps);
//    }
//
//	@Override
//	public String getEmailByNumber(String username) {
//        SqlParameterSource ps = new MapSqlParameterSource().addValue("number", username);
//		String query = "select * from user where number =:number;";
//		User user = npJdbcTemplate.queryForObject(query, ps, new BeanPropertyRowMapper<User>(User.class));
//		return user.getEmail();
//	}
//
//	@Override
//	public User getuserByNumber(String number) {
//	    SqlParameterSource ps = new MapSqlParameterSource().addValue("number", number);
//		String query = "select * from user where number =:number;";
//		User user = npJdbcTemplate.queryForObject(query, ps, new BeanPropertyRowMapper<User>(User.class));
//		return user;
//
//	}
//
//	@Override
//	public void makeSeller(MakeSellerRequest makeSellerRequest) {
//		SqlParameterSource ps = new MapSqlParameterSource()
//				.addValue("email", makeSellerRequest.getEmail())
//				.addValue("isSeller", makeSellerRequest.getIsSeller());
//		String query = "update user set is_seller=:isSeller where email=:email;";
//		npJdbcTemplate.update(query, ps);
//	}
//
//	@Override
//	public User getUser(String userId) {
//		SqlParameterSource ps = new MapSqlParameterSource().addValue("id", userId);
//		String query = "select * from user where id =:id;";
//		User user = npJdbcTemplate.queryForObject(query, ps, new BeanPropertyRowMapper<User>(User.class));
//		return user;
//	}
//
//
//
//}
