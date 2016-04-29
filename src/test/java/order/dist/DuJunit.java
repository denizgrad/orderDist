package order.dist;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import com.du.order.dist.config.WebMvcConfig;
import com.du.order.dist.interfaces.ISalesForceClient;
import com.du.order.dist.model.entity.Order;
import com.du.order.dist.repository.OrderRepository;
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebMvcConfig.class)
public class DuJunit {
	@Resource
	OrderRepository orderRepository;
	@Test
	public void dbTest(){
		Order o = new Order();
		populateOrder(o);
		orderRepository.save(o);
		Assert.notNull(o.getOid());
	}
	@Autowired
	ISalesForceClient sf;
	
	@Test
	public void testSfProdQuery(){
		Assert.isTrue(StringUtils.isNotBlank(sf.testSfClient()));
	}
	
	@Test
	public void testSfContact(){
//		String accID  = sf.returnAccountId("hizli@ab.com.tr", "hizli1234");
//		System.out.println(accID);
		Order order = new Order();
		order.setRemoteId("a0i11000005duljAAA");
		order.setSiparisDurum("Görüldü");
		order.setSiparisTeslimTarihi(new Date());
		sf.updateStatus(order);
		
		
	}
	public void populateOrder (Order order){
		order.setBarcodeNumber("TEST_BARCODE_NUMBER_!'^_123_ĞÜŞİÖÇ_ığüşöç");
	}
}
