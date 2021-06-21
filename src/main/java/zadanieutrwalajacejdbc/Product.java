package zadanieutrwalajacejdbc;

import lombok.Getter;

@Getter
public class Product {
    private Integer id;
    private int warehouseId;
    private String name;
    private String producent;
    private int count;

    public Product(int id, int warehouseId, String name, String producent, int count) {
        this.id = id;
        this.warehouseId = warehouseId;
        this.name = name;
        this.producent = producent;
        this.count = count;
    }

    public Product(int warehouseId, String name, String producent, int count) {
        this.warehouseId = warehouseId;
        this.name = name;
        this.producent = producent;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", warehouseId=" + warehouseId +
                ", name='" + name + '\'' +
                ", producent='" + producent + '\'' +
                ", count=" + count +
                '}';
    }
}
