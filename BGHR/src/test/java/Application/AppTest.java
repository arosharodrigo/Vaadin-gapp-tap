package com.mycompany.app;

import org.testng.annotations.Test;
import Application.frontServ;

public class AppTest
{

    frontServ g1=new frontServ();
	@Test
	public void test1(){
	assert"A1".equals(g1.method2());
	System.out.println("Test is OK 1");
	}


    
}
