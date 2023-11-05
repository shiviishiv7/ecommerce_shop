package com.halim.repo.impl;

//
//@Repository
//public class CategoryRepositoryImpl implements CategoryRepository {
//
//	 private static final Logger logger = LoggerFactory.getLogger(CategoryRepositoryImpl.class);
//
//	@Autowired
//	NamedParameterJdbcTemplate npJdbcTemplate;
//
//	@Override
//	public List<Category> getAllCategories() {
//		logger.debug("{}", "CategoryRepositoryImpl::getAllCategories started");
//
//		SqlParameterSource ps = new MapSqlParameterSource();
//		String query = "select * from category;";
//		List<Category> categories = npJdbcTemplate.query(query, ps, new BeanPropertyRowMapper<Category>(Category.class));
//		logger.debug("{}", "CategoryRepositoryImpl::getAllCategories ended");
//
//		return categories;
//	}
//
//	@Override
//	public Category getCategoryById(Long id) {
//		logger.debug("{}", "CategoryRepositoryImpl::getCategoryById started");
//
//		SqlParameterSource ps = new MapSqlParameterSource().addValue("id", id);
//		String query = "select * from category where id =:id";
//		Category category = npJdbcTemplate.queryForObject(query, ps, new BeanPropertyRowMapper<Category>(Category.class));
//		logger.debug("{}", "CategoryRepositoryImpl::getCategoryById ended");
//
//		return category;
//	}
//
//	@Override
//	public void saveCategory(Category category) {
//		logger.debug("{}", "CategoryRepositoryImpl::saveCategory started");
//
//		SqlParameterSource ps1 = new MapSqlParameterSource();
//
//		String query1 = "select count(id) from category;";
//		int id  = 100 +  npJdbcTemplate.queryForObject(query1, ps1, Integer.class);
//
//
//		SqlParameterSource ps = new MapSqlParameterSource()
//				.addValue("id", id)
//				.addValue("name", category.getName());
//
//		String query = "INSERT INTO Category(id , name)\r\n"
//				+ "VALUES (:id,:name);";
//		npJdbcTemplate.update(query, ps);
//		logger.debug("{}", "CategoryRepositoryImpl::saveCategory ended");
//
//	}
//
//	@Override
//	public void updateCategoryById(Category category) {
//		logger.debug("{}", "CategoryRepositoryImpl::updateCategoryById started");
//
//		SqlParameterSource ps = new MapSqlParameterSource()
//				.addValue("id", category.getId())
//				.addValue("name", category.getName());
//		String query = "UPDATE category\r\n"
//				+ "SET name =:name\r\n"
//				+ "WHERE id = :id;\r\n";
//		npJdbcTemplate.update(query, ps);
//		logger.debug("{}", "CategoryRepositoryImpl::updateCategoryById ended");
//
//	}
//	@Override
//	public void deleteCategory(Long id) {
//		logger.debug("{}", "CategoryRepositoryImpl::deleteCategory started");
//		SqlParameterSource ps = new MapSqlParameterSource().addValue("id", id);
//		String query = "delete from category where id=:id";
//		npJdbcTemplate.update(query, ps);
//		logger.debug("{}", "CategoryRepositoryImpl::deleteCategory ended");
//			}
//
//}
