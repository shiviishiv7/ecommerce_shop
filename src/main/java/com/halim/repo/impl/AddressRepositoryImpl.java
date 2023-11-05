package com.halim.repo.impl;

//
//@Repository
//public class AddressRepositoryImpl implements AddressRepository {
//
//	@Autowired
//	NamedParameterJdbcTemplate npJdbcTemplate;
//
//    private static final Logger logger = LoggerFactory.getLogger(AddressRepositoryImpl.class);
//
//
//	@Override
//	public void addAddress(AddressRequest address) {
//		logger.debug("{}", "AddressRepositoryImpl::addAddress started");
//
//		SqlParameterSource ps1 = new MapSqlParameterSource();
//
//		String query1 = "select count(id) from Address;";
//		int id  = 100 +  npJdbcTemplate.queryForObject(query1, ps1, Integer.class);
//
//
//		SqlParameterSource ps = new MapSqlParameterSource()
//				.addValue("id", id)
//				.addValue("user_id", address.getUser_id())
//				.addValue("firstname", address.getFirstname())
//				.addValue("lastname", address.getLastname())
//				.addValue("streetaddress", address.getStreetaddress())
//				.addValue("state", address.getState())
//				.addValue("townorcity", address.getTownorcity())
//				.addValue("postcodezip", address.getPostcodezip())
//				.addValue("country", address.getCountry())
//				.addValue("email", address.getEmail())
//				.addValue("phoneno", address.getPhoneno())
//				.addValue("Landmark", address.getLandmark());
//
//
//		String query = "insert into Address(id, user_id, firstname, lastname,streetaddress, Landmark,state, townorcity, postcodezip, country, email, phoneno)\r\n"
//				+ "value(:id,:user_id, :firstname, :lastname, :streetaddress, :Landmark, :state, :townorcity, :postcodezip, :country, :email, :phoneno);";
//		npJdbcTemplate.update(query, ps);
//		logger.debug("{}", "AddressRepositoryImpl::addAddress ended");
//
//
//	}
//
//	@Override
//	public List<AddressRequest> getAddressByUser(Long userid) {
//		logger.debug("{}", "AddressRepositoryImpl::getAddressByUser started");
//		SqlParameterSource ps = new MapSqlParameterSource()
//				.addValue("user_id", userid);
//		String query = "select * from Address where user_id=:user_id;";
//		List<AddressRequest> addresses = npJdbcTemplate.query(query, ps, new BeanPropertyRowMapper<AddressRequest>(AddressRequest.class));
//		logger.debug("{}", "AddressRepositoryImpl::getAddressByUser ended");
//		return addresses;
//	}
//
//	@Override
//	public void updateAddressById(AddressRequest address) {
//		logger.debug("{}", "AddressRepositoryImpl::updateAddressById started");
//		SqlParameterSource ps = new MapSqlParameterSource()
//				                  .addValue("id", address.getId())
//				                  .addValue("user_id", address.getUser_id())
//				                  .addValue("firstname", address.getFirstname())
//				                  .addValue("lastname", address.getLastname())
//				                  .addValue("streetaddress", address.getStreetaddress())
//				                  .addValue("Landmark", address.getLandmark())
//				                  .addValue("state", address.getState())
//				                  .addValue("townorcity", address.getTownorcity())
//				                  .addValue("postcodezip", address.getPostcodezip())
//				                  .addValue("country", address.getCountry())
//				                  .addValue("email", address.getEmail())
//				                  .addValue("phoneno", address.getPhoneno());
//		String query = "update address set firstname =:firstname , lastname =:lastname , streetaddress = :streetaddress, user_id = :user_id,\r\n"
//				+ "				Landmark =:Landmark,state =:state, townorcity =:townorcity , postcodezip =:postcodezip , country =:country , email =:email, \r\n"
//				+ "				phoneno =:phoneno WHERE id =:id";
//		npJdbcTemplate.update(query, ps);
//		logger.debug("{}", "AddressRepositoryImpl::updateAddressById ended");
//	}
//
//	@Override
//	public void deleteAddressById(Long id) {
//		logger.debug("{}", "AddressRepositoryImpl::deleteAddressById started");
//		SqlParameterSource ps = new MapSqlParameterSource().addValue("id", id);
//		String query = "delete from address where id=:id";
//		npJdbcTemplate.update(query, ps);
//		logger.debug("{}", "AddressRepositoryImpl::deleteAddressById ended");
//	}
//
//
//}
