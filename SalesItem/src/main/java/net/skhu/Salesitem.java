package net.skhu;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Salesitem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String itemName;

    @ManyToOne
    @JoinColumn(name = "typeId")
    Itemtype itemtype;

    int unitPrice;
    int count;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Itemtype getItemtype() {
		return itemtype;
	}
	public void setItemtype(Itemtype itemtype) {
		this.itemtype = itemtype;
	}
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
