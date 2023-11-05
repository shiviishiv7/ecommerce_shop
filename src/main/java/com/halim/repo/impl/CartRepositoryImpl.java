package com.halim.repo.impl;

//
//@Repository
//public class CartRepositoryImpl implements CartRepository {
//
//	@Autowired
//	NamedParameterJdbcTemplate npJdbcTemplate;
//
//	@Override
//	public void addToCart(CartRequest cartRequest) {
//
//
//		SqlParameterSource ps1 = new MapSqlParameterSource()
//				.addValue("uId", cartRequest.getUserId())
//				.addValue("pId", cartRequest.getProductId());
//
//		String query1 = "select  count(productId) from cart where userId=:uId and productId=:pId;";
//
//		int count = npJdbcTemplate.queryForObject(query1, ps1, Integer.class);
//
//	    if(count >=1) return;
//
//		SqlParameterSource ps = new MapSqlParameterSource().addValue("uId", cartRequest.getUserId())
//				.addValue("quantity", cartRequest.getQuatity()).addValue("pId", cartRequest.getProductId());
//
//		String query = "insert into cart(productId, quantity, userId)\r\n" + "value (:pId, 1, :uId);\r\n" + "";
//		npJdbcTemplate.update(query, ps);
//
//	}
//
//	@Override
//	public List<ProductAndQuantity> getCartItems(Long id) {
//		SqlParameterSource ps = new MapSqlParameterSource().addValue("id", id);
//		String query = "select\r\n"
//				+ "   p.brand as brand,\r\n"
//				+ "   p.price as price,\r\n"
//				+ "   p.id as id,\r\n"
//				+ "   p.image as image,\r\n"
//				+ "   p.name as name,\r\n"
//				+ "   c.quantity as quantity,\r\n"
//				+ "   p.description as description,\r\n"
//				+ "   c.quantity as count\r\n"
//				+ " from cart as c, product as p where p.id=c.productId and  c.userId=:id;";
//
//		 List<ProductAndQuantity> products = npJdbcTemplate.query(query, ps,
//				new BeanPropertyRowMapper<ProductAndQuantity>(ProductAndQuantity.class));
//
//		 products.forEach(product->{
//		   	   SqlParameterSource ps1 = new MapSqlParameterSource().addValue("productId", product.getId());
//				String query1 = "select * from product_image_map where product_id=:productId";
//				List<ProductAndImage> productAndImages = npJdbcTemplate.query(query1, ps1, new ProductImageMapper());
//
//				product.setImage(new ArrayList<>());
//				productAndImages.forEach(productAndImage ->{
//					product.getImage().add(productAndImage.getImage());
//				});
//		 });
//
//		 // logger.debug("{}", "ProductJdbcRepositoryImpl::searchByValue ended");
//		return products;
//	}
//
//	@Override
//	public void emptyCartById(Long userId) {
//		SqlParameterSource ps = new MapSqlParameterSource().addValue("id", userId);
//		String query = "delete from cart where userId=:id";
//		npJdbcTemplate.update(query, ps);
//	}
//
//	@Override
//	public void deleteCartByuserIdAndProductId(Long userId, Long productId) {
//		SqlParameterSource ps = new MapSqlParameterSource()
//				.addValue("userId", userId)
//				.addValue("productId", productId);
//		String query = "delete from cart where userId=:userId and productId=:productId";
//		npJdbcTemplate.update(query, ps);
//	}
//
//}
