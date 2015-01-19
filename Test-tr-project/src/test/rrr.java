package test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import controller.ApplicationAction;

public class rrr {

	public static void main(String[] args) {
		AbstractApplicationContext ctx=
				new FileSystemXmlApplicationContext("beans.xml");
		ApplicationAction db= (ApplicationAction) ctx.getBean("ssss");
		db.AddFromFile("rrrr");
		ctx.close();
	}

}
