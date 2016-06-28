package working.model;

import static javax.persistence.GenerationType.*;

import javax.persistence.*;

@Entity
public class Phone {
	
	@Id @GeneratedValue(strategy=AUTO) private Long id;
	private String number;
	@ManyToOne
	private Employee employee;

	public Long getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
