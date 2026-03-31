package service;

import model.Livre;

public interface Filter {
	boolean matches(Livre livre,String value);

}
