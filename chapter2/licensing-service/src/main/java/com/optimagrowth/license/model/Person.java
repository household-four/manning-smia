package com.optimagrowth.license.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String major;
    private String department;
    private LocalDate dateOfBirth;
    private String phone;
    private String email;

    @ManyToMany
    @JoinTable(name = "person_organization", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "organization_id"))
    private Set<Organization> organizations = new HashSet<>();

    @OneToMany(mappedBy = "president")
    private Set<Organization> presidencies = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Set<Organization> organizations) {
        this.organizations = organizations;
    }

    public Set<Organization> getPresidencies() {
        return presidencies;
    }

    public void setPresidencies(Set<Organization> presidencies) {
        this.presidencies = presidencies;
    }
}
