package com.du.order.dist.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.codehaus.plexus.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.du.order.dist.Utility;
import com.du.order.dist.interfaces.IMessageSource;
import com.du.order.dist.interfaces.IOrderService;
import com.du.order.dist.interfaces.ITransformer;
import com.du.order.dist.interfaces.IValidator;
import com.du.order.dist.interfaces.NamedEnum;
import com.du.order.dist.model.entity.Order;
import com.du.order.dist.model.util.AuthenticationError;
import com.du.order.dist.model.util.LoginForm;
import com.du.order.dist.model.util.OrderError;
import com.du.order.dist.model.util.PairModel;
import com.du.order.dist.model.util.Response;
import com.du.order.dist.model.util.ValidationError;
import com.du.order.dist.model.util.transfer.AIn;
import com.du.order.dist.model.util.transfer.CreateGenelSiparisIn;
import com.du.order.dist.model.util.transfer.GenelSiparisIn;
import com.du.order.dist.model.util.transfer.UpdateGenelSiparisIn;
import com.du.order.dist.service.ServiceProvider;

@RestController
@RequestMapping("/v1/siparis/islem")
@Transactional
public class SfRestServiceController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	private static String comboPackagePrefix = "com.du.order.dist.model.util.combo.";

	@Autowired
	private Environment env;

	@Autowired
	private IMessageSource resourceMessage;

	@Autowired
	private ITransformer transformer;

	@Autowired
	private IOrderService orderService;
	
	 @Autowired 
	 private HttpSession httpSession;

	@ResponseBody
	@RequestMapping(value = "/createSiparis", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Response> create(@RequestBody GenelSiparisIn objectIn) {

		Response resp = new Response(true, HttpStatus.OK.value(), resourceMessage.getMessage("service.success"));

		try {
			checkAuthentication(objectIn);
			checkValidityCreate(objectIn);
			Order order = transformer.transformCreate(objectIn);
			orderService.create(order);
		} catch (AuthenticationError ex) {
			resp = new Response(false, HttpStatus.OK.value(), resourceMessage.getMessage("authentication.exception"));
			logger.error(ex.getMessage() + resourceMessage.getMessage("authentication.exception"));
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (ValidationError ex) {
			resp = new Response(false, HttpStatus.OK.value(), Utility.validationErrorToString(ex));
			logger.error(Utility.validationErrorToString(ex));
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (OrderError ex) {
			resp = new Response(false, HttpStatus.OK.value(), ex.getDescription());
			logger.error(ex.getDescription());
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception ex) {
			resp = new Response(false, HttpStatus.OK.value(), resourceMessage.getMessage("service.exception"));
			logger.error(ex.getMessage());
			logger.error(ex.toString());
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/updateSiparis", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Response> update(@RequestBody GenelSiparisIn objectIn) {

		Response resp = new Response(true, HttpStatus.OK.value(), resourceMessage.getMessage("service.success"));

		try {
			checkAuthentication(objectIn);
			checkValidityUpdate(objectIn);
			Order order = transformer.transformUpdate(objectIn);
			if(order == null){
				return create(objectIn);
			} else {
				orderService.update(order);
			}
		} catch (AuthenticationError ex) {
			resp = new Response(false, HttpStatus.OK.value(), resourceMessage.getMessage("authentication.exception"));
			logger.error(ex.getMessage() + resourceMessage.getMessage("authentication.exception"));
			logger.error(ex.toString());
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (ValidationError ex) {
			resp = new Response(false, HttpStatus.OK.value(), Utility.validationErrorToString(ex));
			logger.error(Utility.validationErrorToString(ex));
			logger.error(ex.toString());
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (OrderError ex) {
			resp = new Response(false, HttpStatus.OK.value(), ex.getDescription());
			logger.error(ex.getDescription());
			logger.error(ex.toString());
			return new ResponseEntity<>(resp, HttpStatus.OK);
		} catch (Exception ex) {
			resp = new Response(false, HttpStatus.OK.value(), resourceMessage.getMessage("service.exception"));
			logger.error(ex.getMessage());
			logger.error(ex.toString());
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	private void checkAuthentication(AIn objectIn) throws AuthenticationError {
		logger.info(String.format("Incoming sfUsername :%s and password: %s", objectIn.getUserName(),
				objectIn.getPassword()));
		if (!(env.getRequiredProperty("sf.password").equals(objectIn.getPassword())
				|| env.getRequiredProperty("sf.userName").equals(objectIn.getUserName()))) {
			throw new AuthenticationError();
		}
	}

	@Autowired
	IValidator validator;

	private void checkValidityUpdate(GenelSiparisIn objectIn) throws ValidationError {
		validator.validate(objectIn);
	}

	private void checkValidityCreate(GenelSiparisIn objectIn) throws ValidationError {
		validator.validate(objectIn);
	}

	// ----------------------------

	/**
	 * @endpoint /public/fillCombo/{comboName}
	 * @param umut
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/fillCombo/{comboName}", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<List<PairModel>> test(@PathVariable("comboName") String umut) {
		Class<? extends Enum<?>> comboEnum = null;
		List<PairModel> retList = new ArrayList<>();
		try {
			comboEnum = (Class<? extends Enum<?>>) Class.forName(comboPackagePrefix + umut);
		} catch (ClassNotFoundException e) {
			logger.error("Requested Combo class not found!");
		}
		if (NamedEnum.class.isAssignableFrom(comboEnum)) {
			for (Enum<?> e : comboEnum.getEnumConstants()) {
				retList.add(new PairModel(((NamedEnum) e).getKey(), String.valueOf(((NamedEnum) e).getKey())));
			}
		}
		return new ResponseEntity<>(retList, HttpStatus.OK);
	}

	// TODO handle

	@ResponseBody
	@RequestMapping(value = "/getOrderList", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<List<Order>> getOrderList() {
		List<Order> retList = new ArrayList<>();
		// TODO null check
		try {
			LoginForm lf = (LoginForm) httpSession.getAttribute("LOGGEDIN_USER");
			retList = orderService.getOrderList(lf.getAccId());
		} catch (Exception e) {
			logger.error("can not fetch list of order");
		}
		return new ResponseEntity<>(retList, HttpStatus.OK);
	}

	// {oid} @PathVariable("oid") String oid
	// TODO parameter pass
	@ResponseBody
	@RequestMapping(value = "/getOrderByOid/{siparisOid}", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Order> getOrderByOid(@PathVariable("siparisOid") String siparisOid) {
		Order dbOrder = new Order();
		try {
			// dbOrder = orderService.getOrderByOid(oid);
			dbOrder = orderService.getOrderByOid(siparisOid);
		} catch (Exception e) {
			logger.error("can not fetch order by id");
		}
		return new ResponseEntity<>(dbOrder, HttpStatus.OK);
	}

	// {oid} @PathVariable("oid") String oid
	// TODO parameter pass
	@ResponseBody
	@RequestMapping(value = "/getOrderByBarcode/{barcode}", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Order> getOrderByBarcode(@PathVariable("barcode") String barcode) {
		Order dbOrder = new Order();
		try {
			dbOrder = orderService.getOrderByBarcode(barcode);
		} catch (Exception e) {
			logger.error("can not fetch order by barcode");
		}
		return new ResponseEntity<>(dbOrder, HttpStatus.OK);
	}
	
	// {oid} @PathVariable("oid") String oid
	// TODO parameter pass
	@ResponseBody
	@RequestMapping(value = "/updateOrderStatus/{oid}-{status}", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<String> updateOrderStatus(@PathVariable("oid") String oid, @PathVariable("status") String status) {
		
		try {
			orderService.updateOrderStatus(oid, status);
			
		} catch (Exception e) {
			logger.error("can not update order status");
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	// {oid} @PathVariable("oid") String oid
	// TODO parameter pass
	@ResponseBody
	@RequestMapping(value = "/updateOrderBarcode/{oid}-{barcode}", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<String> updateOrderBarcode(@PathVariable("oid") String oid, @PathVariable("barcode") String barcode) {
		
		try {
			orderService.updateOrderBarcode(oid, barcode);
			
		} catch (Exception e) {
			logger.error("can not update order barcode");
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// {oid} @PathVariable("oid") String oid
	// TODO parameter pass
	@ResponseBody
	@RequestMapping(value = "/deliverOrder/{oid}", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<String> deliverOrder(@PathVariable("oid") String oid) {
		
		try {
			orderService.deliverOrder(oid);
			
		} catch (Exception e) {
			logger.error("can not update order barcode");
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
