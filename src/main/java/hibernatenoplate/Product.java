package hibernatenoplate;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
    private String name;
    private String producent;
    private int count;

    public Product(Warehouse warehouse, String name, String producent, int count) {
        this.warehouse=warehouse;
        this.name = name;
        this.producent = producent;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", warehouse=" + warehouse +
                ", name='" + name + '\'' +
                ", producent='" + producent + '\'' +
                ", count=" + count +
                '}';
    }
}
