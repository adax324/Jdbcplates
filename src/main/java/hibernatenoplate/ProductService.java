package hibernatenoplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ProductService {
    private EntityManager entityManager =null;
    private EntityManagerFactory entityManagerFactory=null;


    public void addProduct(Product product){
        setConnection();
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        closeConnection();
    }

    public void updateCount(Product product,int count){
        setConnection();
        Query query=entityManager.createQuery("UPDATE Product p Set p.count=:count WHERE p.id=:id");
        query.setParameter("count",count);
        query.setParameter("id",product.getId());
        entityManager.getTransaction().begin();
        query.executeUpdate();
        entityManager.getTransaction().commit();
        closeConnection();
    }
    public Product getProductBy(int id){
        setConnection();
        Query query=entityManager.createQuery("FROM Product p Where p.id=:id");
        query.setParameter("id",id);
        return (Product) query.getSingleResult();
    }
    public List<Product> getAllProducts(){
        setConnection();
        Query query=entityManager.createQuery("FROM Product");
        List<Product> products=query.getResultList();
        closeConnection();
        return products;
    }
    public List<Product> getAllProductsFromWarehouse(Warehouse warehouse){
        setConnection();
        Query query=entityManager.createQuery("FROM Product p WHERE p.warehouse=:w");
        query.setParameter("w",warehouse);
        List<Product> products=query.getResultList();
        closeConnection();
        return products;
    }
    public void moveProductToWarehouse(Product product,Warehouse warehouse){
        setConnection();
        Query query=entityManager.createQuery("UPDATE Product p Set warehouse=:w WHERE p.id=:p_id" );
        query.setParameter("p_id",product.getId());
        query.setParameter("w",warehouse);
        entityManager.getTransaction().begin();
        query.executeUpdate();
        entityManager.getTransaction().commit();
        closeConnection();
    }
    public void deletProductFromWarehouse(Product product){
        setConnection();
        Query query =entityManager.createQuery("DELETE FROM Product p WHERE p.id=:id");
        query.setParameter("id",product.getId());
        entityManager.getTransaction().begin();
        query.executeUpdate();
        entityManager.getTransaction().commit();
        closeConnection();


    }
    public void deleteAllProductsFromWarehouse(Warehouse warehouse){
        setConnection();
        Query query=entityManager.createQuery("DELETE FROM Product p WHERE p.warehouse=:w");
        query.setParameter("w",warehouse);
        entityManager.getTransaction().begin();
        query.executeUpdate();
        entityManager.getTransaction().commit();
        closeConnection();

    }




    private  void setConnection(){
        entityManagerFactory=Persistence.createEntityManagerFactory("hibernateex_javapol83");
        entityManager= entityManagerFactory.createEntityManager();
    }
    public   void closeConnection(){
        entityManagerFactory.close();
    }


}
