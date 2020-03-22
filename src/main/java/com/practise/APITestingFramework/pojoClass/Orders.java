package com.practise.APITestingFramework.pojoClass;

import java.util.ArrayList;

public class Orders {
	
	private String intent;
	private PurchaseList[] purchase_units;
	public PurchaseList[] getPurchase_units() {
		return purchase_units;
	}
	public void setPurchase_units(PurchaseList[] purchase_units) {
		this.purchase_units = purchase_units;
	}
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	
	

}
