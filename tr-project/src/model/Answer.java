package model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
public class Answer {
	// класс надо сделать для работы , в таком виде все работает , 
	// при изменении следите за изменениями в других классах иначе работать не будет и ошибку искать будем 3 дня
	@Id
	String name;
	@ManyToMany(mappedBy="answers")
	List<Question> books;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Question> getBooks() {
		return books;
	}
	public void setBooks(List<Question> books) {
		this.books = books;
	}


}
