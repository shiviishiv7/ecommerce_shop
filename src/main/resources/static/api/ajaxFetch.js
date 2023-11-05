/**
 * 
 */
function handleListProduct(event)
{
  event.preventDefault();
  fire_ajax1();
}
function  fire_ajax1()
{
	$.ajax({
        type: "GET",
        url: "/listProduct",
        processData: false,// prevent jQuery from automatically
        // transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
        	$("#app").empty();
        	$("#app").append(data);
        },
	  error: function (e) {

          console.log("ERROR : ", e);
         
      }
  });
}

function handleAddProduct(event)
{
  event.preventDefault();
  fire_ajax();
}
function  fire_ajax()
{
	$.ajax({
        type: "GET",
        url: "/addProduct",
        processData: false,// prevent jQuery from automatically
        // transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
        	$("#app").empty();
        	$("#app").append(data);
        },
	  error: function (e) {

          console.log("ERROR : ", e);
         
      }
  });
}

// Search 
function dropDown(event){
		    var val = $("#search").val();
		    if(val.length > 1){
		    	$.ajax({
		            type: "GET",
		            url: "/search?value="+val,
		            processData: false,// prevent jQuery from automatically
		            // transforming the data into a query string
		            contentType: false,
		            cache: false,
		            timeout: 6000,
		            success: function (data) {
		            	$('.dropdown-toggle').dropdown();
		            	$("#dropdown-container").empty();
		            	$("#dropdown-container").append(data);
		            	
		            },
		    	  error: function (e) {

		              console.log("ERROR : ", e);
		             
		          }
		      });
		    	
		    }
			
		}
		
		
		
		

const paymentStart = (x) => {
   
    console.log(x + "payment started........");
   var amount = x.toString().split('.')[0];
 

  // we will use ajax to send request to server to create order- jquery

  $.ajax({
    url: "/create_order",
    data: JSON.stringify({ amount: amount, info: "order_request" }),
    contentType: "application/json",
    type: "POST",
    dataType: "json",
    success: function (response) {
      //invoked when success
      console.log(response);
      if (response.status == "created") {
        //open payment form
        let options = {
          key: "rzp_test_rmROBgYfrGNWgC",
          amount: response.amount,
          currency: "INR",
          name: "Smart Contact Manager",
          description: "Donation",
          image:
            "https://yt3.ggpht.com/-4BGUu55s_ko/AAAAAAAAAAI/AAAAAAAAAAA/3Cfl_C4o8Uo/s108-c-k-c0x00ffffff-no-rj-mo/photo.jpg",
          order_id: response.id,
          handler: function (response) {
            console.log(response.razorpay_payment_id);
            console.log(response.razorpay_order_id);
            console.log(response.razorpay_signature);
            console.log("payment successful !!");
            // alert("congrates !! Payment successful !!");
            swal("Good job!", "congrates !! Payment successful !!", "success");
          },
          prefill: {
            name: "",
            email: "",
            contact: "",
          },

          notes: {
            address: "LearnCodeWith Durgesh ",
          },
          theme: {
            color: "#3399cc",
          },
        };

        let rzp = new Razorpay(options);

        rzp.on("payment.failed", function (response) {
          console.log(response.error.code);
          console.log(response.error.description);
          console.log(response.error.source);
          console.log(response.error.step);
          console.log(response.error.reason);
          console.log(response.error.metadata.order_id);
          console.log(response.error.metadata.payment_id);
          //alert("Oops payment failed !!");
          swal("Failed !!", "Oops payment failed !!", "error");
        });

        rzp.open();
      }
    },
    error: function (error) {
      //invoked when error
      console.log(error);
      alert("something went wrong !!");
    },
  });
};
				