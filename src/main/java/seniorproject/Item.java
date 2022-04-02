package seniorproject;

import java.util.*;
import java.lang.*;
public class Item {
int id;
String brand;
int modelNum;
double salePrice;
double purchasePrice;
int count;
int width;
int size;
int aspectRatio;
int diameter;

public Item(int id, String brand, int modelNum, double salePrice, double purchasePrice, int count, int width, int size,
		int aspectRatio, int diameter) {
	super();
	this.id = id;
	this.brand = brand;
	this.modelNum = modelNum;
	this.salePrice = salePrice;
	this.purchasePrice = purchasePrice;
	this.count = count;
	this.width = width;
	this.size = size;
	this.aspectRatio = aspectRatio;
	this.diameter = diameter;
}

public void print() {	//for testing contents of an item
	
}
}
