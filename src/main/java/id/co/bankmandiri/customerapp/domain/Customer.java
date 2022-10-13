package id.co.bankmandiri.customerapp.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Customer {
    private int CustomerId;
    private String firstName;
    private String LastName;
    private LocalDate dateOfBirth;

    public Customer(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        LastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Customer(int customerId, String firstName, String lastName, LocalDate dateOfBirth) {
        CustomerId = customerId;
        this.firstName = firstName;
        LastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "CustomerId=" + CustomerId +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return CustomerId == customer.CustomerId && Objects.equals(firstName, customer.firstName) && Objects.equals(LastName, customer.LastName) && Objects.equals(dateOfBirth, customer.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CustomerId, firstName, LastName, dateOfBirth);
    }
}
