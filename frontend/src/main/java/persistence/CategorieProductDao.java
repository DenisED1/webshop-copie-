package persistence;

import java.util.ArrayList;

import domain.Product;

public interface CategorieProductDao {

	public ArrayList<Product> getCategorieProducten(int id);

}
