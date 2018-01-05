package com.jsoftgroup.inventory;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.mappers.CsvWithHeaderMapper;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.context.SpringBootTest;

import com.jsoftgroup.api.InventoryAPI;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnitParamsRunner.class)
@PrepareForTest({StringUtils.class})
@SpringBootTest
public class InventoryApplicationTests {

	@Test
	public void contextLoads() {
		
	}
	
	@Test
	@FileParameters(value ="src/test/resources/test.csv", mapper = CsvWithHeaderMapper.class)
	public void getServiceTest(String name,String expected) {
		String value="";
		InventoryAPI service=new InventoryAPI();
		
		if("power".equalsIgnoreCase(name)){
			PowerMockito.mockStatic(StringUtils.class);
			PowerMockito.when(StringUtils.isEmpty(name)).thenReturn(true);
		}
		
		value=service.getService(name);
		Assert.assertEquals(value, expected);
		
	}

}
