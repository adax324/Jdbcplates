package jdbcplate;

public class Warehouse {
    private final int id;
    private String nazwa;
    private String kod;
    private String adres;

    public Warehouse(int id, String nazwa, String kod, String adres) {
        this.id = id;
        this.nazwa = nazwa;
        this.kod = kod;
        this.adres = adres;
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
