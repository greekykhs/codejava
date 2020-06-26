package com.greekykhs.java8;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class Java8Test2 {
	public static List<Employee> getEmployees() throws Exception {
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateOfJoining1 = dateformat.parse("17/07/1989");
		Date dateOfJoining2 = dateformat.parse("15/10/2007");	
		Date dateOfJoining3 = dateformat.parse("01/10/2020");
		Date dateOfJoining4 = dateformat.parse("11/11/2020");
		
		List<Employee> employees=new ArrayList<>();		
		employees.add(new Employee(1, "Ronalda", 5000, dateOfJoining1, 28));
		employees.add(new Employee(2, "Matilda", 7000, dateOfJoining2, 32));
		employees.add(new Employee(3, "Roslie", 10000, dateOfJoining3, 50));
		employees.add(new Employee(4, "Martha", 55000, dateOfJoining4, 60));
		
		return employees;
	}
	public static void main(String[] args) throws Exception{
		List<Employee> employees=getEmployees();
		
		//count the number of employees
		long count=employees.stream().count();
		
		//Sort employees based on Date of Joining
		employees.stream()
		.sorted((e1, e2)->e1.getDateOfJoining().compareTo(e2.getDateOfJoining()))
		.collect(Collectors.toList());
		
		//Sort employees based on Date of Joining
		//using method reference
		employees.stream()
		.sorted(Comparator.comparing(Employee::getDateOfJoining))
		.collect(Collectors.toList());
		
		//Filter employees with salary less than 8000
		employees.stream()
		.filter(e->e.getSalary()<8000)
		.collect(Collectors.toList());
		
		//Sort employees by age using method reference
		employees.stream()
		.sorted(Comparator.comparing(Employee::getAge))
		.collect(Collectors.toList());
		
		//Sort employees by age in reverse order using method reference
		employees.stream()
		.sorted(Comparator.comparing(Employee::getAge).reversed())
		.collect(Collectors.toList());
		
		//Covert the list into map, where key:id, value:name
		Map<Integer, String> empMap=employees.stream()
				.collect(Collectors.toMap(e->e.getId(), e->e.getName()));
		
		//Covert the list into map using method ref, where key:id, value:name
		empMap=employees.stream()
				.collect(Collectors.toMap(Employee::getId, Employee::getName));
		
		//While converting to a map if the duplicate key is encountered 
		//it will throw 'java.lang.IllegalStateException: Duplicate key'. 
		//We can handle this by providing merging function.
		empMap=employees.stream()
		.collect(Collectors.toMap(e->e.getId(), e->e.getName(),
				(oldName, newName)->oldName+newName));
		
		//Print name of all the employees in captial letters in sorted order
		employees.stream()
		.map(e->e.getName())
		.map(String::toUpperCase)
		.sorted()
		.forEach(System.out::println);
		
		//Find average age of employee
		double averageAge=employees.stream()
				.mapToInt(e->e.getAge())
				.average().getAsDouble();
		//OR
		averageAge=employees.stream()
				.collect(Collectors.averagingDouble(e -> e.getAge()));
		
		//Print the max salary
		employees.stream()
		.mapToDouble(e->e.getSalary())
		.max()
		.ifPresent(System.out::println);
		
		//groups all Employees by age
		Map<Integer, List<Employee>> empAgeGroup=employees.stream()
				.collect(Collectors.groupingBy(e -> e.getAge()));
		
		//Convert employee names into a comma seperated string
		String commaSepratedNames = employees.stream()
				.map(Employee::getName)
				.collect(Collectors.joining(", ", "{", "}"));
		
		//Divide the employees into groups according to salary is greater than 5000
		Map<Boolean, List<Employee>> mappedEmployees=employees.stream()
				.collect(Collectors.partitioningBy
						(e->e.getSalary()>5000));
		
		//Increment the salary of each employee by 2000
		employees.stream()
		.peek(e -> e.setSalary(e.getSalary() + 2000))
		.peek(System.out::println)
		.collect(Collectors.toList());
		
		//Find the youngest and oldest employee
		Employee youngEmployee=employees.stream()
				.min((e1, e2)->e1.getAge() - e2.getAge())
				.orElseThrow(NoSuchElementException::new);
		Employee oldEmployee=employees.stream()
				.max(Comparator.comparing(Employee::getAge))
				.orElseThrow(NoSuchElementException::new);
		
		//Convert the list into Set of employee names
		Set<String> empNames = employees.stream()
	            .map(Employee::getName)
	            .collect(Collectors.toSet());
		
		//summaryStatistics()
		//statics.getMin(), statics.getMax(), statics.getAverage()
		//statics.getCount(), statics.getCount()
		DoubleSummaryStatistics statics = employees.stream()
			      .mapToDouble(Employee::getSalary)
			      .summaryStatistics();		
	}
}
class Employee {
	private int id;
	private String name;
	private double salary;	
	private Date dateOfJoining;
	private int age;
	public Employee(int id, String name, double salary, Date dateOfJoining, int age) {
		super();
		this.id=id;
		this.name = name;
		this.salary = salary;
		this.dateOfJoining = dateOfJoining;
		this.age=age;
	}
	//getter, setters, tostring
	@Override
	public String toString() {
		return "id="+id+", name=" + name + ", salary=" + salary + ", dateOfJoining=" + dateOfJoining + ", age="+age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
}
