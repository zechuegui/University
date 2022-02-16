package spring.josesantos.model;

public class Pagamento {
    private String email;
    private int id;
    private float mbAmount;
    private int mbEntity;
    private int mbReference;

    public Pagamento(String email, int id, float mbAmount, int mbEntity, int mbReference) {
        this.email = email;
        this.id = id;
        this.mbAmount = mbAmount;
        this.mbEntity = mbEntity;
        this.mbReference = mbReference;
    }
    
    public int getMbReference() {
        return mbReference;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public float getMbAmount() {
        return mbAmount;
    }
    public void setMbAmount(float mbAmount) {
        this.mbAmount = mbAmount;
    }
    public int getMbEntity() {
        return mbEntity;
    }
    public void setMbEntity(int mbEntity) {
        this.mbEntity = mbEntity;
    }
    public void setMbReference(int mbReference) {
        this.mbReference = mbReference;
    }

}
