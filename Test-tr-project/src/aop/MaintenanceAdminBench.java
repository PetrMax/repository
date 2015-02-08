package aop;

import log.logger;

import org.aspectj.lang.ProceedingJoinPoint;

public class MaintenanceAdminBench {

	public Object test(ProceedingJoinPoint point){		
		boolean actionAdmin = true;
		Object[] args = point.getArgs();		
		Object fl=false;

		try {	
			if(actionAdmin){
				fl = point.proceed(args);
				System.out.println("test admin");
			}else{
				logger.log(fl);
				System.out.println("User is not Autorized syso");
				new Exception("User is not Autorized");
			}			
		} catch (Throwable e) {				
			System.out.println("User is not Autorized");
		}
		return fl;
	}
}
