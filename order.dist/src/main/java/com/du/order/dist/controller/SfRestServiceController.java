package com.du.order.dist.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Utilities;

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
import com.du.order.dist.model.util.PairModel;
import com.du.order.dist.model.util.Response;
import com.du.order.dist.model.util.SystemError;
import com.du.order.dist.model.util.ValidationError;
import com.du.order.dist.model.util.transfer.AIn;
import com.du.order.dist.model.util.transfer.CreateGenelSiparisIn;
import com.du.order.dist.model.util.transfer.GenelSiparisIn;
import com.du.order.dist.model.util.transfer.UpdateGenelSiparisIn;



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
    
    
   	@ResponseBody
       @RequestMapping(
                 value = "/createSiparis"
               , consumes = "application/json"
               , produces = "application/json"
               , method = RequestMethod.POST)
       public ResponseEntity<Response> create(@RequestBody CreateGenelSiparisIn objectIn) {
    	
    	Response resp = new Response(true,HttpStatus.OK.value(), resourceMessage.getMessage("service.success"));
    	
        try {
        	checkAuthentication(objectIn);
        	checkValidityCreate(objectIn);
        	Order order = transformer.transform(objectIn);
        	orderService.create(order);
        }
        catch (AuthenticationError ex){
        	resp = new Response(false, HttpStatus.OK.value(),resourceMessage.getMessage("authentication.exception"));
            logger.error(ex.getMessage());
            return new ResponseEntity<>(resp,HttpStatus.OK);
        }
        catch (ValidationError ex){
        	resp = new Response(false, HttpStatus.OK.value(), Utility.validationErrorToString(ex));
            logger.error(ex.getMessage());
            return new ResponseEntity<>(resp,HttpStatus.OK);
        }
        catch (SystemError ex){
        	resp = new Response(false, HttpStatus.OK.value(), ex.getMessage() );
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
    
   	@ResponseBody
       @RequestMapping(
                 value = "/updateSiparis"
               , consumes = "application/json"
               , produces = "application/json"
               , method = RequestMethod.POST)
       public ResponseEntity<Response> update(@RequestBody UpdateGenelSiparisIn objectIn) {
       	
    	Response resp = new Response(true,HttpStatus.OK.value(), resourceMessage.getMessage("service.success"));
    	
        try {
        	checkAuthentication(objectIn);
        	checkValidityUpdate(objectIn);
        	Order order = transformer.transform(objectIn);
        	orderService.update(order);
        }
        catch (AuthenticationError ex){
        	resp = new Response(false, HttpStatus.OK.value(),"Kullanıcı adı yada parola yanlış");
            logger.error(ex.getMessage());
            return new ResponseEntity<>(resp,HttpStatus.OK);
        }
        catch (ValidationError ex){
        	resp = new Response(false, HttpStatus.OK.value(), Utility.validationErrorToString(ex) );
            logger.error(ex.getMessage());
            return new ResponseEntity<>(resp,HttpStatus.OK);
        }
        catch (SystemError ex){
        	resp = new Response(false, HttpStatus.OK.value(), ex.getMessage() );
            logger.error(ex.getMessage());
            return new ResponseEntity<>(resp,HttpStatus.OK);
        }
        catch (Exception ex){
        	resp = new Response(false, HttpStatus.OK.value(),"Sistem Hatası");
            logger.error(ex.getMessage());
            return new ResponseEntity<>(resp,HttpStatus.OK);
        }
        return new ResponseEntity<>(resp,HttpStatus.OK);
       }

   	
    private void checkAuthentication(AIn objectIn) throws AuthenticationError {
    	logger.info(String.format("Incoming sfUsername :%s and password: %s", objectIn.getUserName(), objectIn.getPassword()));
    	if(!(env.getRequiredProperty("sf.password").equals(objectIn.getPassword()) || env.getRequiredProperty("sf.userName").equals(objectIn.getUserName()))){
    		throw new AuthenticationError();
    	}
	}
    
    @Autowired
    IValidator validator;
    
    private void checkValidityUpdate(GenelSiparisIn objectIn) throws ValidationError {
    	validator.validate((UpdateGenelSiparisIn) objectIn);
	}
    
    private void checkValidityCreate(GenelSiparisIn objectIn) throws ValidationError {
    	validator.validate((CreateGenelSiparisIn) objectIn);
	}
}