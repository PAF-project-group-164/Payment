package com;

import model.Payment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class PaymentService {
	Payment PaymentsObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return PaymentsObj.readPayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(
	@FormParam("Payment_customer_id") String Payment_customer_id,
	@FormParam("Payment_customer_name") String Payment_customer_name,
	@FormParam("Payment_date") String Payment_date,
	@FormParam("Payment_amount") String Payment_amount,
	@FormParam("Payment_description") String Payment_description) {
		String output = PaymentsObj.insertPayment(Payment_customer_id, Payment_customer_name, Payment_date,  Payment_amount, Payment_description);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updatePayment(String paymentData) { 
		// Convert the input string to a JSON object
		JsonObject Pay_Object = new JsonParser().parse(paymentData).getAsJsonObject();

		// Read the values from the JSON object
		String Payment_id = Pay_Object.get("Payment_id").getAsString();
		String Payment_customer_id = Pay_Object.get("Payment_customer_id").getAsString();
		String Payment_customer_name = Pay_Object.get("Payment_customer_name").getAsString();
		String Payment_date = Pay_Object.get("Payment_date").getAsString();
		String Payment_amount = Pay_Object.get("Payment_amount").getAsString();
		String Payment_description = Pay_Object.get("Payment_description").getAsString();
		
		String output = PaymentsObj.updatePayment(Payment_id, Payment_customer_id, Payment_customer_name, Payment_date, Payment_amount, Payment_description);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		// Read the value from the element
		String Payment_id = doc.select("Payment_id").text();
		String output = PaymentsObj.deletePayment(Payment_id);
		return output;
	}
}
