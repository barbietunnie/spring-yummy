package com.example.packtpub.yummy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YummyApplicationTests {
	@Autowired
	DatePrinter datePrinter;
	@MockBean
	TimeFactory timeFactory;

	@Test
	public void contextLoads() {
	}

	@Test
	public void datePrinterWorks() {
		given(timeFactory.now()).willReturn(LocalDateTime.of(2017, 8,15, 9, 50, 27));
		assertEquals("Now, it is 2017-08-15T09:50:27", datePrinter.printDate());
	}
}
