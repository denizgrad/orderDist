package com.du.order.dist.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.du.order.dist.interfaces.IMessageSource;
import com.du.order.dist.interfaces.IOrderService;
import com.du.order.dist.interfaces.ITransformer;
import com.du.order.dist.interfaces.NamedEnum;
import com.du.order.dist.model.util.PairModel;



@RestController
@RequestMapping("/public")
@Transactional
public class PublicRestServiceController {

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

    /**
     * @endpoint /public/fillCombo/{comboName}
     * @param umut
     * @return
     */
    @SuppressWarnings("unchecked")
	@ResponseBody
    @RequestMapping(
              value = "/fillCombo/{comboName}"
            , produces = "application/json"
            , method = RequestMethod.POST)
    public ResponseEntity<List<PairModel>> test(@PathVariable("comboName") String umut) {
    	Class<? extends Enum<?>> comboEnum = null;
    	List<PairModel> retList = new ArrayList<>();
    	try {
			comboEnum = (Class<? extends Enum<?>>) Class.forName(comboPackagePrefix + umut);
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
}
