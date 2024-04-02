
package com.marketvalue.sapient.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Holding {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;
    
    @Column
    private double quantity;
    
    @ManyToOne
    @JoinColumn(name = "fund_id")
    private Fund fund;
    
    public Holding() {

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

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Fund getFund() {
		return fund;
	}

	public void setFund(Fund fund) {
		this.fund = fund;
	}
	
	

}
