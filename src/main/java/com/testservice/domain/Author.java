package com.testservice.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Author {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Author() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Author [id=");
        builder.append(id);
        builder.append(", firstName=");
        builder.append(firstName);
        builder.append(", lastName=");
        builder.append(lastName);
        builder.append(", age=");
        builder.append(age);
        builder.append(", salary=");
        builder.append(salary);
        builder.append("]");
        return builder.toString();
    }
}