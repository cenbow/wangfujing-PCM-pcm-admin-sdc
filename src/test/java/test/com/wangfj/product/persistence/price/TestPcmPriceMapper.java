package test.com.wangfj.product.persistence.price;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wangfj.product.price.domain.vo.QueryPriceDto;
import com.wangfj.product.price.domain.vo.SelectPriceDto;
import com.wangfj.product.price.persistence.PcmPriceMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestPcmPriceMapper {
	@Autowired
	public PcmPriceMapper pcmPriceMapper;

	@Test
	public void test() {
		testQueryPriceInfoByShoppeProCode();
	}

	/**
	 * 变价信息查询
	 * 
	 * @Methods Name testQueryPriceInfoByShoppeProCode
	 * @Create In 2015年7月30日 By kongqf void
	 */
	@Test
	public void testQueryPriceInfoByShoppeProCode() {
		QueryPriceDto queryPriceDto = new QueryPriceDto();
		queryPriceDto.setShoppeProSid("1000000030");
		queryPriceDto.setChannelSid("0");
		SelectPriceDto selectPriceDto = new SelectPriceDto();
//		selectPriceDto = pcmPriceMapper.queryPriceInfoByPara(queryPriceDto);
		if (selectPriceDto != null)
			System.out.println(selectPriceDto.toString());
		else
			System.out.println("ShoppePro price info not exist");

	}
}
