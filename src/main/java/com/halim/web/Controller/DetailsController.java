package com.halim.web.Controller;

//@RestController
//public class DetailsController {
//	@Autowired
//	private ProductService productService;
//
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private CategoryService categoryService;
//
//	@Autowired
//	private CartService cartService;
//
//	@Autowired
//	private AddressService addressService;
//
//	@Autowired
//	private TwilioOTPService twilioOTPService;
//
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	@Autowired
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	private UserDetailsService customUserDetailsService;
//
//	@Autowired
//	private JwtUtil jwtUtil;
//
//	@Autowired
//	private OrderService orderService;
//
//	private static final Logger logger = LoggerFactory.getLogger(DetailsController.class);
//
//
//
//
//
//
//	@RequestMapping(value = "/token", method = RequestMethod.POST)
//	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
//
//		try {
//
//			if(jwtRequest.getUsername().toString().contains("@")) {
//		   	  this.authenticationManager.authenticate(
//				   	new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
//			}
//		} catch (UsernameNotFoundException e) {
//			e.printStackTrace();
//			throw new Exception("Bad Credentials");
//		} catch (BadCredentialsException e) {
//			e.printStackTrace();
//			throw new Exception("Bad Credentials");
//		}
//
//		// fine area..
//		UserDetails userDetails = null;
//
//		User user = null;
//		if(jwtRequest.getUsername().toString().contains("@") == false) {
////			user = userRepository.getuserByNumber(jwtRequest.getUsername());
//			String requestUsername = jwtRequest.getUsername();
//			 user = userService.findByUserName(requestUsername);
//			userDetails = this.customUserDetailsService.loadUserByUsername(user.getUsername());
//
//		}
//		else{
////			user = userRepository.getUserByUserName(jwtRequest.getUsername());
//			String requestUsername = jwtRequest.getUsername();
//			user = userService.findByUserName(requestUsername);
//			userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
//		}
//
//		String token = this.jwtUtil.generateToken(userDetails);
//		System.out.println("JWT " + token);
//
//		// {"token":"value"}
//
//		return ResponseEntity.ok(new JwtResponse(token, user.getId(),false));
//
//	}
//
//
//	@CrossOrigin
//	@GetMapping("/product/{id}")
//	@ResponseBody
//	public Product showIndex(@PathVariable("id") String id) {
//		logger.debug("{}", "DetailsController::showIndex started");
//
////		Product product = productService.getProductById(id);
//		logger.debug("{}", "DetailsController::showIndex ended");
//
//		return null;
//	}
//
//	@CrossOrigin
//	@RequestMapping(value = "/search", method = RequestMethod.GET)
//	@ResponseBody
//	public List<Product> search(@RequestParam(name = "value", required = false) String value,
//			@RequestParam(name = "topseller", required = false) boolean topseller,
//			@RequestParam(name = "minprice", required = false) String minprice,
//			@RequestParam(name = "maxprice", required = false) String maxprice) {
//		logger.debug("{}", "DetailsController::search started");
//
//		List<Product> products = productService.searchProduct(value, minprice, maxprice, topseller);
//		logger.debug("{}", "DetailsController::search ended");
//
//		return products;
//	}
//	@CrossOrigin
//	@RequestMapping(value = "product/filter", method = RequestMethod.GET)
//	@ResponseBody
//	public List<Product> searchProductsBetweenPrice(@RequestParam("minprice") String minprice,@RequestParam("maxprice") String maxprice){
//		List<Product> products = productService.searchProductsBetweenPrice(minprice,maxprice);
//		return products;
//	}

//	@CrossOrigin
//	@ResponseBody
//	@PutMapping("/product/{id}")
//	public void updateProductById(@RequestBody Product product) {
//		logger.debug("{}", "DetailsController::updateProductById started");
//
//		productService.updateProductById(product);
//		logger.debug("{}", "DetailsController::updateProductById ended");
//	}
//
//	@CrossOrigin
//	@GetMapping("/category")
//	@ResponseBody
//	public List<Category> getCategories() {
//		logger.debug("{}", "DetailsController::getCategories started");
//
//		List<Category> categories = categoryService.getAllCategories();
//		logger.debug("{}", "DetailsController::getCategories ended");
//
//		return categories;
//	}
//
//	@CrossOrigin
//	@GetMapping("/category/{id}")
//	@ResponseBody
//	public Category getCategoryById(@PathVariable("id") String id) {
//		logger.debug("{}", "DetailsController::getCategoryById started");
//
//		Category category = categoryService.getCategoryById(id);
//		logger.debug("{}", "DetailsController::getCategoryById ended");
//
//		return category;
//	}
//
//	@CrossOrigin
//	@ResponseBody
//	@PostMapping("/category")
//	public String addCategory(@RequestBody Category category) {
//		logger.debug("{}", "DetailsController::addCategory started");
//		categoryService.addCategory(category);
//		logger.debug("{}", "DetailsController::addCategory ended");
//		return "created";
//
//	}
//
//	@CrossOrigin
//	@ResponseBody
//	@PutMapping("/category/{id}")
//	public void updateCategoryById(@RequestBody Category category) {
//		logger.debug("{}", "DetailsController::updateCategoryById started");
//		categoryService.updateCategoryById(category);
//		logger.debug("{}", "DetailsController::updateCategoryById ended");
//
//	}
//
//	@CrossOrigin
//	@ResponseBody
//	@DeleteMapping("/category/{id}")
//	public void deleteCategory(@PathVariable("id") String id) {
//		logger.debug("{}", "DetailsController::deleteCategory started");
//		categoryService.deleteCategoryById(id);
//		logger.debug("{}", "DetailsController::deleteCategory ended");
//	}
//
//	@CrossOrigin
//	@ResponseBody
//	@PostMapping("/product")
//	public String saveProduct(@RequestBody ProductRequest product) {
//		logger.debug("{}", "DetailsController::saveProduct started");
//
//		productService.saveProductToDB(product);
//		logger.debug("{}", "DetailsController::saveProduct ended");
//
//		return "product created";
//	}
//
//	@CrossOrigin
//	@ResponseBody
//	@DeleteMapping("/product/{id}")
//	public String deleteProduct(@PathVariable("id") String id) {
//		logger.debug("{}", "DetailsController::deleteProduct started");
//		productService.deleteProduct(id);
//		logger.debug("{}", "DetailsController::deleteProduct ended");
//		return "product deleted";
//	}
//
//
//
//	@CrossOrigin
//	@ResponseBody
//	@PostMapping("/addtocart")
//	public String addToCart(@RequestBody CartRequest cartrequest) {
//		logger.debug("{}", "DetailsController::addToCart started");
//
//		cartService.addToCart(cartrequest);
//		logger.debug("{}", "DetailsController::addToCart ended");
//
//		return "item added";
//	}
//
//	@CrossOrigin
//	@ResponseBody
//	@GetMapping("/shoppingcart/{userId}")
//	public CartResponse addToCart(@PathVariable("userId") String id) {
//		CartResponse cartResponse = new CartResponse();
//		List<ProductAndQuantity> products = cartService.getCartItems(id);
//		cartResponse.setProducts(products);
//		return cartResponse;
//	}
//
//
//	@CrossOrigin
//	@ResponseBody
//	@DeleteMapping("/shoppingcart/user/{userId}/product/{productId}")
//	public String deleteFromCart(@PathVariable("userId") Long userId,@PathVariable("productId") Long productId) {
//	    cartService.getCartItems(userId, productId);
//		return "item deleted for user" + userId;
//	}


//	@CrossOrigin
//	@ResponseBody
//	@PostMapping("/register")
//	public String registerUser(@RequestBody User user, HttpSession session) {
//		user.setRole("ROLE_USER");
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		userRepository.save(user);
//		return "signup";
//	}
//
//	@GetMapping("/signin")
//	public String customLogin(Model model) {
//		model.addAttribute("title", "Login Page");
//		return "login";
//	}
//
//	@PostMapping("/address")
//	@CrossOrigin
//	@ResponseBody
//	public String addAddress(@RequestBody AddressRequest address) {
//		logger.debug("{}", "DetailsController::addAddress started");
//
//		addressService.addAddress(address);
//		logger.debug("{}", "DetailsController::addAddress ended");
//
//		return "address saved";
//	}
//
//	@GetMapping("/address/{userId}")
//	@CrossOrigin
//	@ResponseBody
//	public List<UserAddress> addAddress(@PathVariable("userId") String userid) {
//		logger.debug("{}", "DetailsController::addAddress started");
//
//		List<UserAddress> address = addressService.getAllAddressByUser(userid);
//		logger.debug("{}", "DetailsController::addAddress ended");
//
//		return address;
//	}
//
//	// creating order for payment
//
//	@CrossOrigin
//	@PostMapping("/create_order")
//	@ResponseBody
//	public String createOrder(@RequestBody OrderRequest orderRequest) throws Exception { // System.out.println("Hey
//																							// order function ex.");
//
//		double amt = orderRequest.getAmount();
//
//		RazorpayClient client = new RazorpayClient("rzp_test_rmROBgYfrGNWgC", "8YAbcfGSQZCh1sdjQkcW1zIM");
//
//		JSONObject ob = new JSONObject();
//		ob.put("amount", amt * 100);
//		ob.put("currency", "INR");
//		ob.put("receipt", "txn_235425");
//
//		Order order = client.Orders.create(ob);
////		orderRequest.setOrderId(order.get("id"));
//		orderRequest.setStatus(order.get("status"));
//
//		orderService.saveOrdersToDB(orderRequest);
//		return order.toString();
//	}
//
//	@CrossOrigin
//	@PostMapping("/order_status")
//	@ResponseBody
//	public String orderStatus(@RequestBody OrderStatusRequest orderstatusRequest) throws Exception {
//
//		orderService.updateOrderStatus(orderstatusRequest);
//
//		return "order status updated";
//	}
//
//
//	@CrossOrigin
//	@ResponseBody
//	@PutMapping("/address/{id}")
//	public void updateAddressById(@RequestBody UserAddress address) {
//		logger.debug("{}", "DetailsController::updateAddressById started");
//
//		addressService.updateAddressById(address);
//		logger.debug("{}", "DetailsController::updateAddressById ended");
//
//	}

//	@CrossOrigin
//	@ResponseBody
//	@DeleteMapping("/address/{id}")
//	public void deleteAddressById(@PathVariable("id") String id) {
//		logger.debug("{}", "DetailsController::deleteAddressById started");
//		addressService.deleteAddressById(id);
//		logger.debug("{}", "DetailsController::deleteAddressById ended");
//
//	}
//
//	@CrossOrigin
//	@ResponseBody
//	@DeleteMapping("/emptycart/{user-id}")
//	public void emptycart(@PathVariable("user-id") Long userId) {
//		cartService.emptyCartById(userId);
//	}
//
//
//	@CrossOrigin
//	@ResponseBody
//	@PostMapping("/router/sendOTP")
//	public PasswordResetResponseDto sendOtp(@RequestBody PasswordResetRequestDto passwordResetRequest) {
//		PasswordResetResponseDto response = twilioOTPService.sendOTPForPasswordReset(passwordResetRequest);
//		return response;
//	}
//
//	@CrossOrigin
//	@ResponseBody
//	@PostMapping("/router/validateOTP")
//	public String validateOtp(@RequestBody PasswordResetRequestDto passwordResetRequest) {
//		String response = twilioOTPService.validateOTP(passwordResetRequest.getOneTimePassword(), passwordResetRequest.getUserName());
//		return response;
//	}
//
//	@CrossOrigin
//	@ResponseBody
//	@PutMapping("/makeseller")
//	public String makeSeller(@RequestBody MakeSellerRequest makeSellerRequest) {
//        userService.makeSeller(makeSellerRequest);
//		return "user updated";
//	}
//
//	@CrossOrigin
//	@ResponseBody
//	@GetMapping("/verifyorder/{order-id}")
//	public OrderAndSellerDetailResponse verifyOrder(@PathVariable("order-id") String orderId) {
//		 OrderAndSellerDetailResponse odrDtls = orderService.verifyOrder(orderId);
//		return odrDtls;
//	}

//	@CrossOrigin
//	@ResponseBody
//	@GetMapping("/user/{user-id}")
//	public User getUser(@PathVariable("user-id") String userId) {
//		 User user = userService.findUserById(userId);
//		return user;
//	}


//}