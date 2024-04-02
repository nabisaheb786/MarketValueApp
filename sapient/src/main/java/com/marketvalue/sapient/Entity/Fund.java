// entity/Fund.java
package com.marketvalue.sapient.Entity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Fund {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;
   
    
    @OneToMany(mappedBy = "fund", cascade = CascadeType.ALL)
    private List<Holding> holdings;
     
    
    public Fund() {
        this.holdings = new ArrayList<>();
    }

    public Fund(String name) {
        this.name = name;
        this.holdings = new ArrayList<>();
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

	public List<Holding> getHoldings() {
		return holdings;
	}

	public void setHoldings(List<Holding> holdings) {
		this.holdings = holdings;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}
	
}
