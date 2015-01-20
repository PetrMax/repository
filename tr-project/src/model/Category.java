package model;
import javax.persistence.*;
import java.util.*;
@Entity
public class Category {
	@Id
	String catName;
	@OneToMany(mappedBy="publisher")
	List<Question> books;


	protected String getCatName() {
		return catName;
	}
	protected void setCatName(String catName) {
		this.catName = catName;
	}
	


}
