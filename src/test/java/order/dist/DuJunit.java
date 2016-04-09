package order.dist;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import com.du.order.dist.config.WebMvcConfig;
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
	
	public void populateOrder (Order order){
		order.setBarcodeNumber("TEST_BARCODE_NUMBER_!'^_123_ĞÜŞİÖÇ_ığüşöç");
	}
}