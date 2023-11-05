//package com.halim.web.Controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import com.halim.model.Product;
//import com.halim.web.service.ProductService;
//
//@Controller
//public class IndexController {
//	@Autowired
//	private ProductService productService;
//
//	@GetMapping("/")
//	public String showIndex(Model model) {
//		model.addAttribute("products", productService.getAllProduct());
//		return "index";
//	}
//
//
//	/*
//	 * @GetMapping(value = "/users")
//	 * 
//	 * @ResponseBody public List<User> getAllusers() { List<User> users =
//	 * userService.getAllUsers(); return users; }
//	 */
//
//	@PostMapping("/users")
//	@ResponseBody
//	@ResponseStatus(HttpStatus.CREATED)
//	public String adduser(HttpServletRequest req) {
//		
//		return "user created";
//	}
//
//	// creating order for payment
//	/*
//	 * @PostMapping("/create_order")
//	 * 
//	 * @ResponseBody public String createOrder(@RequestBody Map<String, Object>
//	 * data) throws Exception { // System.out.println("Hey order function ex.");
//	 * System.out.println(data);
//	 * 
//	 * int amt = Integer.parseInt(data.get("amount").toString());
//	 * 
//	 * var client = new RazorpayClient("rzp_test_rmROBgYfrGNWgC",
//	 * "8YAbcfGSQZCh1sdjQkcW1zIM");
//	 * 
//	 * JSONObject ob = new JSONObject(); ob.put("amount", amt * 100);
//	 * ob.put("currency", "INR"); ob.put("receipt", "txn_235425");
//	 * 
//	 * // creating new order
//	 * 
//	 * Order order = client.Orders.create(ob); System.out.println(order);
//	 * 
//	 * // if you want you can save this to your data.. return order.toString(); }
//	 */
//}
