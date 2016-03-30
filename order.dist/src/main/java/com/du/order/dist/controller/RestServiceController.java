package com.du.order.dist.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.du.order.dist.interfaces.IMessageSource;
import com.du.order.dist.interfaces.IOrderService;
import com.du.order.dist.interfaces.ITransformer;
import com.du.order.dist.interfaces.NamedEnum;
import com.du.order.dist.model.entity.Order;
import com.du.order.dist.model.util.AuthenticationError;
import com.du.order.dist.model.util.PairModel;
import com.du.order.dist.model.util.Response;
import com.du.order.dist.model.util.transfer.CreateSiparisIn;
import com.du.order.dist.model.util.transfer.UpdateSiparisIn;



@RestController
@RequestMapping("/v1/siparis/islem")
public class RestServiceController {

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

    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping(
              value = "/fillCombo/{comboName}"
//            , consumes = "application/json"
            , produces = "application/json"
            , method = RequestMethod.POST)
    public ResponseEntity<List<PairModel>> test(@PathVariable("comboName") String umut) {
    	Class<? extends Enum<?>> comboEnum = null;
    	List<PairModel> retList = new ArrayList<>();
    	try {
			comboEnum = (Class<? extends Enum<?>>) Class.forName(comboPackagePrefix+umut);
		} catch (ClassNotFoundException e) {
			logger.error("Requested Combo class not found!");
		}
		if(NamedEnum.class.isAssignableFrom(comboEnum)){
			for (Enum<?> e : comboEnum.getEnumConstants()) {
				retList.add(new PairModel(((NamedEnum)e).getName())); 
			}
		}
    	return new ResponseEntity<>(retList , HttpStatus.OK);
    }
    
    
    
    @SuppressWarnings("unchecked")
   	@ResponseBody
       @RequestMapping(
                 value = "/createSiparis"
               , consumes = "application/json"
               , produces = "application/json"
               , method = RequestMethod.POST)
       public ResponseEntity<Response> create(@RequestBody CreateSiparisIn objectIn) {
    	
    	Response resp = new Response(true,HttpStatus.OK.value(), resourceMessage.getMessage("service.success"));
    	
        try {
        	checkValidity(objectIn);
        	Order order = transformer.transform(objectIn);
        	orderService.create(order);
        }
        catch (AuthenticationError ex){
        	resp = new Response(false, HttpStatus.OK.value(),resourceMessage.getMessage("autentication.exception"));
            logger.error(ex.getMessage());
            return new ResponseEntity<>(resp,HttpStatus.OK);
        }
        catch (Exception ex){
        	resp = new Response(false, HttpStatus.OK.value(),resourceMessage.getMessage("service.exception"));
            logger.error(ex.getMessage());
            return new ResponseEntity<>(resp,HttpStatus.OK);
        }
        return new ResponseEntity<>(resp,HttpStatus.OK);
       }
    
    private void checkValidity(CreateSiparisIn objectIn) throws AuthenticationError {
    	logger.info(String.format("Incomig sfUsername :%s and password: %s", objectIn.getUserName(), objectIn.getPassword()));
    	String sfUserName = env.getRequiredProperty("sf.userName");
    	String sfpassword = env.getRequiredProperty("sf.password");
    	if((sfpassword.equals(objectIn.getPassword()) || sfUserName.equals(objectIn.getUserName()))){
    		throw new AuthenticationError();
    	}
    	
	}



	@SuppressWarnings("unchecked")
   	@ResponseBody
       @RequestMapping(
                 value = "/updateSiparis"
               , consumes = "application/json"
               , produces = "application/json"
               , method = RequestMethod.POST)
       public ResponseEntity<Response> update(@RequestBody UpdateSiparisIn objectIn) {
       	
    	return new ResponseEntity<>(null , HttpStatus.OK);
       }
//    public ResponseEntity<BaseVo> sendShipment(@RequestBody Shipment shipment,
//                                               @RequestHeader(value="Authorization") String authorization) {
//
//        BaseVo base = new BaseVo(true,HttpStatus.OK.value(),messageUtil.getMessage("service.success"));
//
//        try {
//            String userName = new String(Base64.decodeBase64(authorization.replaceAll("Basic ","").getBytes())).split(":")[0];
//            shipment.setCreateUser(userName);
//            shipment.setUpdateUser(userName);
//            shipmentService.newShipment(shipment);
//        }
//        catch (Exception ex){
//            base = new BaseVo(false, HttpStatus.OK.value(),messageUtil.getMessage("service.exception"));
//            logger.error(ex.getMessage());
//            return new ResponseEntity<>(base,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(base,HttpStatus.OK);
//    }
//
//
//    @ResponseBody
//    @RequestMapping(
//            value = "/update"
//            , consumes = "application/json"
//            , produces = "application/json"
//            , method = RequestMethod.POST)
//    public ResponseEntity<BaseVo> updateShipment(@RequestBody Shipment shipment,
//                                                 @RequestHeader(value="Authorization") String authorization) {
//
//        BaseVo base = new BaseVo(true,HttpStatus.OK.value(),messageUtil.getMessage("service.success"));
//
//        try {
//            logger.info(shipment.toString());
//            String userName = new String(Base64.decodeBase64(authorization.replaceAll("Basic ","").getBytes())).split(":")[0];
//            shipment.setUpdateUser(userName);
//            shipmentService.updateStatusShipment(shipment);
//        }
//        catch (Exception ex){
//            base = new BaseVo(false, HttpStatus.OK.value(),messageUtil.getMessage("service.exception"));
//            logger.error(ex.getMessage());
//            return new ResponseEntity<>(base,HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(base,HttpStatus.OK);
//    }
//
//    @ResponseBody
//    @RequestMapping(
//            value = "/cancel"
//            , consumes = "application/json"
//            , produces = "application/json"
//            , method = RequestMethod.POST)
//    public ResponseEntity<BaseVo> cancelShipment(@RequestBody Shipment shipment,
//                                                 @RequestHeader(value="Authorization") String authorization) {
//
//        BaseVo base = new BaseVo(true,HttpStatus.OK.value(),messageUtil.getMessage("service.success"));
//
//        try {
//            String userName = new String(Base64.decodeBase64(authorization.replaceAll("Basic ","").getBytes())).split(":")[0];
//            shipment.setUpdateUser(userName);
//            shipmentService.cancelShipment(shipment.getShipmentId());
//        }
//        catch (Exception ex){
//            base = new BaseVo(false, HttpStatus.OK.value(),messageUtil.getMessage("service.exception"));
//            logger.error(ex.getMessage());
//            return new ResponseEntity<>(base,HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(base,HttpStatus.OK);
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/uptime", method = RequestMethod.GET)
//    public String uptime() {
//        RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
//
//        long millis = rb.getUptime();
//        long second = (millis / 1000) % 60;
//        long minute = (millis / (1000 * 60)) % 60;
//        long hour = (millis / (1000 * 60 * 60)) % 24;
//
//        String time = String.format("%02d:%02d:%02d:%02d", hour, minute, second, millis);
//
//        return time;
//    }
}
