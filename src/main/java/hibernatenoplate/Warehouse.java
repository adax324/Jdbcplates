package hibernatenoplate;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "Warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private  int id;
    private String nazwa;
    private String kod;
    private String adres;
    @OneToMany(mappedBy = "warehouse")
    private List<Product> products=new ArrayList<>();

    public Warehouse(String nazwa, String kod, String adres) {
        this.nazwa = nazwa;
        this.kod = kod;
        this.adres = adres;
    }
    public void addProduct(Product product){
        products.add(product);
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", kod='" + kod + '\'' +
                ", adres='" + adres + '\'' +
                '}';
    }
}
