package db;

import java.io.IOError;
import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DB_Manager {
    private static EntityManagerFactory emf;
    private static DB_Manager db_manager;
	public DB_Manager() {
		emf = Persistence.createEntityManagerFactory("db");
	}
	
	public static DB_Manager getSingletonInstance() {
        if (db_manager == null){
        	db_manager = new DB_Manager();
        }
        return db_manager;
    }

	public static EntityManagerFactory getEmf() {
		return emf;
	}

	public static void setEmf(EntityManagerFactory emf) {
		DB_Manager.emf = emf;
	}
	
}
