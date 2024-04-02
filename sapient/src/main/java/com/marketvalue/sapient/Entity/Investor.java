package com.marketvalue.sapient.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL)
    private List<Fund> funds;

    public Investor() {
        this.funds = new ArrayList<>();
    }

    public Investor(String name) {
        this.name = name;
        this.funds = new ArrayList<>();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Fund> getFunds() {
		return funds;
	}

	public void setFunds(List<Fund> funds) {
		this.funds = funds;
	}

    public void addFund(Fund fund) {
        funds.add(fund);
    }
    
 
}
