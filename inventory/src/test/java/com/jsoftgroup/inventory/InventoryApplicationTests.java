package com.jsoftgroup.inventory;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import junitparams.mappers.CsvWithHeaderMapper;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.context.SpringBootTest;

import com.jsoftgroup.api.InventoryAPI;
import com.jsoftgroup.repo.InventoryJPARepo;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnitParamsRunner.class)
@PrepareForTest({StringUtils.class})
@SpringBootTest
public class InventoryApplicationTests {
	
	
	@InjectMocks
	InventoryAPI inventoryAPI;
	
	@Mock
	InventoryJPARepo inventoryJPARepo;
	
	@Test
	public void contextLoads() {
		
	}
	
	@Before
	public void init() {
	    MockitoAnnotations.initMocks(this);
	    inventoryJPARepo=Mockito.mock(InventoryJPARepo.class);
	}
	
	/*Junit 4.12
	powermock 1.6.2
	JUnitParams 1.1.0
	mockito 1.10.19*/
	
	
	@Test
	@FileParameters(value ="src/test/resources/test.csv", mapper = CsvWithHeaderMapper.class)
	public void getServiceTest(String name,String expected) {
		String value="";
		
		if("power".equalsIgnoreCase(name)){
			PowerMockito.mockStatic(StringUtils.class);
			PowerMockito.when(StringUtils.isEmpty(name)).thenReturn(true);
	
			Mockito.when(inventoryJPARepo.deleteByProductId(1l)).thenReturn(1);
			
		}
		
		value=inventoryAPI.getService(name);
		Assert.assertEquals(value, expected);
		
	}

}
