package aop;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;

public class MaintenanceApplBench {
	
	public Object test(ProceedingJoinPoint point){	
		boolean actionAppl = false;
		Object[] args = point.getArgs();
		Object res = new Object();
		Object fl=false;
		Throwable dropping = new Throwable("User is not Autorized");
		try {
			
			 if(actionAppl){
				res = point.proceed(args);
				System.out.println("test appl");
			}else {
			
				System.out.println("test admin");
			}			
			if(res instanceof Boolean){
				fl = (boolean) res;
			}else if(res instanceof String){
				fl = (String) res;
			}else if(res instanceof List){
				fl = (List<String>) res;
			}	
		} catch (Throwable e) {				
			System.out.println("User is not Autorized");
		}
		return fl;
	}
}
