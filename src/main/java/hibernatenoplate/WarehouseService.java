package hibernatenoplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class WarehouseService {
    private EntityManagerFactory entityManagerFactory = null;
    private EntityManager entityManager = null;

    public void addWarehouse(Warehouse warehouse) {
        setConnection();
        entityManager.getTransaction().begin();
        entityManager.persist(warehouse);
        entityManager.getTransaction().commit();
        closeConnection();
    }
    public void addProductsToWarehouse(List<Product> product,Warehouse warehouse){
        setConnection();
        for (Product product1 : product) {
            entityManager.getTransaction().begin();
            entityManager.persist(new Product(warehouse,product1.getName(),product1.getProducent(),product1.getCount()));
            entityManager.getTransaction().commit();


        }


        closeConnection();
    }


    public List<Warehouse> getAllWarehouses() {
        setConnection();
        Query query = entityManager.createQuery("FROM Warehouse");
        return query.getResultList();
    }

    public Warehouse getWarehouseById(int id) {
        setConnection();
        Query query = entityManager.createQuery("FROM Warehouse w WHERE w.id=:warehouse_id");
        query.setParameter("warehouse_id", id);
        return (Warehouse) query.getSingleResult();
    }

    public void deleteWarehouse(Warehouse warehouse) {
        setConnection();
        Query query = entityManager.createQuery("DELETE FROM Warehouse w WHERE w=:w");
        query.setParameter("w", warehouse);
        entityManager.getTransaction().begin();
        query.executeUpdate();
        entityManager.getTransaction().commit();
        closeConnection();
    }

    private void setConnection() {
        entityManagerFactory = Persistence.createEntityManagerFactory("hibernatex");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void closeConnection() {
        entityManagerFactory.close();
    }



}
