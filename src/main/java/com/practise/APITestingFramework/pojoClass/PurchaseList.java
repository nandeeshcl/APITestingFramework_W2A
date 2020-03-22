package com.practise.APITestingFramework.pojoClass;

public class PurchaseList {
	
	private String reference_id;
	private Amount amount;
	public Amount getAmount() {
		return amount;
	}
	public void setAmount(Amount amount) {
		this.amount = amount;
	}
	public String getReference_id() {
		return reference_id;
	}
	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}

}
