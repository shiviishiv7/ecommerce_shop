package com.halim.repo.impl;

//@Repository
//public class ProductJdbcRepositoryImpl implements ProductJdbcRepository {
//
//	private static final Logger logger = LoggerFactory.getLogger(ProductJdbcRepositoryImpl.class);
//
//	@Autowired
//	NamedParameterJdbcTemplate npJdbcTemplate;
//
//	@Override
//	public List<Product> search(String value, String minprice, String maxprice, boolean topseller) {
//		SqlParameterSource ps = new MapSqlParameterSource().addValue("value", "%" + value + "%")
//				.addValue("minprice", minprice).addValue("maxprice", maxprice);
//		String query = "";
//
//		if(topseller) {
//			query = "select * from product order by order_count desc limit 10;";
//		}
//		else if(value == null && minprice == null && maxprice == null) {
//			return new ArrayList<Product>();
//		}
//		else if (value == null) {
//			query = " SELECT * FROM product\r\n" + "WHERE price BETWEEN :minprice AND :maxprice ";
//		}
//		else if (minprice == null && maxprice == null) {
//			query = "select * from product where name like :value";
//		} else {
//			query = " select * from product where name like :value\r\n" + " and  price BETWEEN :minprice AND :maxprice";
//		}
//		List<Product> products = npJdbcTemplate.query(query, ps, new BeanPropertyRowMapper<Product>(Product.class));
//
//
//		SqlParameterSource ps1 = new MapSqlParameterSource();
//		String query1 = "select * from product_image_map";
//
//		List<ProductAndImage> productAndImages = npJdbcTemplate.query(query1, ps1, new BeanPropertyRowMapper<ProductAndImage>(ProductAndImage.class));
//
//		Map<Long, List<String>> productImageMap = new HashMap<>();
//		productAndImages.stream().forEach( productAndImage -> {
//	         if(productImageMap.containsKey(productAndImage.getProductId()) == false)
//	        	 productImageMap.put(productAndImage.getProductId(), new ArrayList<>());
//
//	         productImageMap.get(productAndImage.getProductId()).add(productAndImage.getImage());
//		});
//
//		products.forEach(product -> {
//			if(productImageMap.containsKey(product.getId()))
//				product.setImage(productImageMap.get(product.getId()));
//		});
//
//		return products;
//	}
//
//
//
//	@Override
//	public Product getProductById(Long id) {
//		SqlParameterSource ps = new MapSqlParameterSource().addValue("id", id);
//		logger.debug("{}", "ProductJdbcRepositoryImpl::getProductById started");
//
//		String query = "select p.description as description,\r\n"
//				+ "       p.brand as brand,\r\n" + "       p.price as price,\r\n" + "       p.quantity as quantity,\r\n"
//				+ "       p.name as name,\r\n" + "	   p.id as id,\r\n" + "       c.name as category\r\n"
//				+ "       from product as p, product_category as pc, category as c where pc.product_id = p.id and pc.category_id = c.id and p.id=:id;";
//
//		Product product = npJdbcTemplate.queryForObject(query, ps, new BeanPropertyRowMapper<Product>(Product.class));
//
//		SqlParameterSource ps1 = new MapSqlParameterSource().addValue("productId", product.getId());
//		String query1 = "select * from product_image_map where product_id=:productId";
//		List<ProductAndImage> productAndImages = npJdbcTemplate.query(query1, ps1, new ProductImageMapper());
//
//		product.setImage(new ArrayList<>());
//		productAndImages.forEach(productAndImage ->{
//			product.getImage().add(productAndImage.getImage());
//		});
//		logger.debug("{}", "ProductJdbcRepositoryImpl::getProductById ended");
//		return product;
//	}
//
//	@Override
//	public void saveProduct(ProductRequest product) {
//		logger.debug("{}", "ProductJdbcRepositoryImpl::saveProduct started");
//
//		SqlParameterSource ps1 = new MapSqlParameterSource();
//		String query1 = "select count(id) from product;";
//		int id = 1000 + npJdbcTemplate.queryForObject(query1, ps1, Integer.class);
//
//		SqlParameterSource ps3 = new MapSqlParameterSource().addValue("id", id).addValue("brand", product.getBrand())
//				.addValue("description", product.getDescription())
//				.addValue("name", product.getName()).addValue("price", product.getPrice())
//				.addValue("discount", product.getDiscount())
//				.addValue("quantity", product.getQuantity()).addValue("categoryId", product.getCategoryId());
//
//		String query3 = "insert into product(id, brand, description, name, price, quantity, discount_id, discount)\r\n"
//				+ "value( :id, :brand, :description,  :name, :price, :quantity,null, discount);";
//
//		npJdbcTemplate.update(query3, ps3);
//
//		SqlParameterSource ps2 = new MapSqlParameterSource().addValue("id", id).addValue("brand", product.getBrand())
//				.addValue("description", product.getDescription()).addValue("image", product.getImage())
//				.addValue("name", product.getName()).addValue("price", product.getPrice())
//				.addValue("quantity", product.getQuantity()).addValue("categoryId", product.getCategoryId());
//
//		String query2 = "insert into product_category(product_id, category_id)\r\n" + "value(:id, :categoryId);";
//		npJdbcTemplate.update(query2, ps2);
//
//		List<String> images = product.getImage();
//
//		images.forEach( image ->{
//
//			SqlParameterSource ps4 = new MapSqlParameterSource()
//					.addValue("image", image).addValue("product_id", id);
//			String query4 = "insert into product_image_map(product_id , image)\r\n"
//					+ "values (:product_id,:image)";
//
//		     npJdbcTemplate.update(query4, ps4);
//		});
//		logger.debug("{}", "ProductJdbcRepositoryImpl::saveProduct ended");
//	}
//
//	@Override
//	public void updateProductById(Product product) {
//		logger.debug("{}", "ProductJdbcRepositoryImpl::updateProductById started");
//
//		SqlParameterSource ps = new MapSqlParameterSource().addValue("id", product.getId())
//				.addValue("name", product.getName()).addValue("description", product.getDescription())
//				.addValue("quantity", product.getQuantity()).addValue("price", product.getPrice())
//				.addValue("discount", product.getDiscount())
//				.addValue("category", product.getCategory()).addValue("brand", product.getBrand());
//
//		String query = "update product set brand =:brand, description=:description, \r\n"
//				+ "	    name=:name, price =:price, discount=:discount"
//				+ "     ,quantity =:quantity  WHERE id=:id;";
//		npJdbcTemplate.update(query, ps);
//
//
//		SqlParameterSource ps5 = new MapSqlParameterSource().addValue("productId", product.getId());
//		String query5 = "delete from product_image_map where product_id=:productId;";
//		npJdbcTemplate.update(query5, ps5);
//
//		List<String> images = product.getImage();
//		images.forEach( image ->{
//
//			SqlParameterSource ps4 = new MapSqlParameterSource()
//					.addValue("image", image).addValue("product_id", product.getId());
//			String query4 = "insert into product_image_map(product_id , image)\r\n"
//					+ "values (:product_id,:image)";
//
//		     npJdbcTemplate.update(query4, ps4);
//		});
//
//
//		logger.debug("{}", "ProductJdbcRepositoryImpl::updateProductById ended");
//	}
//
//
//
//	@Override
//	public void deleteProduct(Long id) {
//		logger.debug("{}", "ProductJdbcRepositoryImpl::deleteProduct started");
//
//		SqlParameterSource ps2 = new MapSqlParameterSource().addValue("id", id);
//		String query2 = "delete from product_category where product_id=:id;";
//		npJdbcTemplate.update(query2, ps2);
//
//
//		SqlParameterSource ps3 = new MapSqlParameterSource().addValue("id", id);
//		String query3 = "delete from product_image_map where product_id=:id;";
//		npJdbcTemplate.update(query3, ps3);
//
//
//		SqlParameterSource ps4 = new MapSqlParameterSource().addValue("id", id);
//		String query4 = "delete from cart where productId=:id;";
//		npJdbcTemplate.update(query4, ps4);
//
//
//		SqlParameterSource ps5 = new MapSqlParameterSource().addValue("id", id);
//		String query5 = "delete from order_product_mapping where productId=:id;";
//		npJdbcTemplate.update(query5, ps5);
//
//
//		SqlParameterSource ps6 = new MapSqlParameterSource().addValue("id", id);
//		String query6 = "delete from cartitem where product_id=:id;";
//		npJdbcTemplate.update(query6, ps6);
//
//
//		SqlParameterSource ps1 = new MapSqlParameterSource().addValue("id", id);
//		String query1 = "delete from product where id=:id;";
//		npJdbcTemplate.update(query1, ps1);
//
//		logger.debug("{}", "ProductJdbcRepositoryImpl::deleteProduct ended");
//
//	}
//
//
//}
