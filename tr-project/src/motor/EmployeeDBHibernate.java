/*package motor;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@SuppressWarnings({"rawtypes","unchecked"})
public class EmployeeDBHibernate implements EmployeeDB {
	@PersistenceContext (unitName="springHibernate")
	EntityManager em;

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRES_NEW)
	public boolean addEmployee(String employeeType, int basicSalary, int id,
			String name, String[] data) {
		boolean result=false;
		if(em.find(Employee.class, id)==null){
			try {
				Class cl=Class.forName("model."+employeeType);
				Employee empl=(Employee) cl.newInstance();
				empl.fillData(data);
				empl.setId(id);
				empl.setBasicSalary(basicSalary);
				empl.setName(name);
				empl.setType(employeeType);
				em.persist(empl);
				result=true;
			} catch (Exception e) {
				System.out.println("Type wrong "+employeeType);
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int getSalaryBudget() {
		Query query=em.createQuery("SELECT e FROM Employee e");
		List<Employee> employees=query.getResultList();
		int budget=0;
		for(Employee empl: employees)
			budget+=empl.computeSalary();
		return budget;
	}

	@Override
	public String[] getAnyMultipleQuery(String strQuery) {
		Query query=em.createQuery(strQuery);
		List<Object[]> result=query.getResultList();
		String []array=new String[result.size()];
		int ind=0;
		for(Object[] arObj:result){
			array[ind++]=Arrays.deepToString(arObj);
		}
		return array;
	}

	@Override
	public String[] getAnySingleQuery(String strQuery) {
		Query query=em.createQuery(strQuery);
		List<Object> result=query.getResultList();
		String []array=new String[result.size()];
		int ind=0;
		for(Object obj: result)
			array[ind++]=obj.toString();
		return array;
	}
}
*/