package com.halim.repo.impl;

//
//@Repository
//public class OrderJdbcRepositoryImpl implements OrderJdbcRepository{
//
//
//	@Autowired
//	NamedParameterJdbcTemplate npJdbcTemplate;
//
//	@Override
//	public void createOrder(OrderRequest orderRequest) {
//
//
//		SqlParameterSource ps3 = new MapSqlParameterSource().addValue("userId", orderRequest.getUserId())
//				.addValue("orderId", orderRequest.getOrderId())
//				.addValue("addressId", orderRequest.getAddressId())
//		        .addValue("status", orderRequest.getStatus());
//
//		  		 String query3 = "insert into orders ( orderId, userId,addressId, status)\r\n"
//						+ "values (:orderId , :userId , :addressId, :status); ";
//			npJdbcTemplate.update(query3, ps3);
//
//
//			List<ProductIdCount> products = orderRequest.getProductsAndCount();
//
//			products.forEach( product ->{
//
//				SqlParameterSource ps2 = new MapSqlParameterSource()
//						.addValue("count", product.getCount())
//						.addValue("orderId", orderRequest.getOrderId())
//						.addValue("productId",product.getProductId());
//				String query2 = "insert into order_product_mapping(orderId , productId, count )\r\n"
//						+ "values (:orderId ,:productId , :count)";
//
//			npJdbcTemplate.update(query2, ps2);
//			});
//
//
//			List<Integer> productIds = new ArrayList();
//			orderRequest.getProductsAndCount().stream().forEach( productAndCount -> productIds.add(productAndCount.getProductId()));
//
//			String query1 = "UPDATE product\r\n"
//					+ "			SET order_count=order_count+1 where id in(:productIds);";
//			SqlParameterSource ps1 = new MapSqlParameterSource()
//					.addValue("productIds", productIds);
//
//			npJdbcTemplate.update(query1, ps1);
//
//	}
//
//	@Override
//	public void updateOrderStatus(OrderStatusRequest orderstatusRequest) {
//		SqlParameterSource ps = new MapSqlParameterSource()
//				.addValue("orderId", orderstatusRequest.getId())
//				.addValue("status", orderstatusRequest.getStatus());
//
//		String query = "UPDATE orders\r\n"
//				+ "SET status =:status\r\n"
//				+ "WHERE orderId = :orderId;\r\n";
//		npJdbcTemplate.update(query, ps);
//	}
//
//	@Override
//	public OrderAndSellerDetailResponse verifyOrder(String orderId) {
//
//
//		SqlParameterSource ps = new MapSqlParameterSource().addValue("orderId", orderId);
//
//		String query = "\r\n"
//				+ "select\r\n"
//				+ "o.status as status,\r\n"
//				+ "o.userId as userId,\r\n"
//				+ "u.email as email,\r\n"
//				+ "u.name as name,\r\n"
//				+ "u.is_seller as is_seller,\r\n"
//				+ "u.number as number \r\n"
//				+ "from orders as o, user as u where o.orderId =:orderId and u.id = o.userId;";
//		OrderAndSellerDetailResponse odrDtl = npJdbcTemplate.queryForObject(query, ps, new BeanPropertyRowMapper<OrderAndSellerDetailResponse>(OrderAndSellerDetailResponse.class));
//
//		String query2 = "select \r\n"
//				+ "om.count as count,\r\n"
//				+ "pr.image as image,\r\n"
//				+ "pr.description as description,\r\n"
//				+ "pr.name as name,\r\n"
//				+ "pr.id as id\r\n"
//				+ "from order_product_mapping as om, product as pr where om.orderId=:orderId and om.productId=pr.id;";
//
//		List<ProductAndCount> productAndCount = npJdbcTemplate.query(query2, ps, new BeanPropertyRowMapper<ProductAndCount>(ProductAndCount.class));
//		odrDtl.setProducts(productAndCount);
//		return odrDtl;
//	}
//}

//@Override
//public void saveProduct(ProductRequest product) {
//	logger.debug("{}", "ProductJdbcRepositoryImpl::saveProduct started");
//
//	SqlParameterSource ps1 = new MapSqlParameterSource();
//	String query1 = "select count(id) from product;";
//	int id = 100 + npJdbcTemplate.queryForObject(query1, ps1, Integer.class);
//
//	SqlParameterSource ps3 = new MapSqlParameterSource().addValue("id", id).addValue("brand", product.getBrand())
//			.addValue("description", product.getDescription()).addValue("image", product.getImage())
//			.addValue("name", product.getName()).addValue("price", product.getPrice())
//			.addValue("quantity", product.getQuantity()).addValue("categoryId", product.getCategoryId());
//
//	String query3 = "insert into product(id, brand, description, image, name, price, quantity, discount_id)\r\n"
//			+ "value( :id, :brand, :description, :image, :name, :price, :quantity,null );";
//
//	npJdbcTemplate.update(query3, ps3);
//
//	SqlParameterSource ps2 = new MapSqlParameterSource().addValue("id", id).addValue("brand", product.getBrand())
//			.addValue("description", product.getDescription()).addValue("image", product.getImage())
//			.addValue("name", product.getName()).addValue("price", product.getPrice())
//			.addValue("quantity", product.getQuantity()).addValue("categoryId", product.getCategoryId());
//
//	String query2 = "insert into product_category(product_id, category_id)\r\n" + "value(:id, :categoryId);";
//	npJdbcTemplate.update(query2, ps2);
//	logger.debug("{}", "ProductJdbcRepositoryImpl::saveProduct ended");
//
//}