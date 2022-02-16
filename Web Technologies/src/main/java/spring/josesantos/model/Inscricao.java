package spring.josesantos.model;

public class Inscricao{

    private String email;
    private String genero;
    private String escalao;
    private int id;
    private int pago;
    private int dorsal;

    public Inscricao(String email, String genero, String escalao,int id, int pago, int dorsal) {
        this.setEmail(email);
        this.setGenero(genero);
        this.setEscalao(escalao);
        this.setId(id);
        this.setPago(pago);
        this.setDorsal(dorsal);
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEscalao() {
        return escalao;
    }

    public void setEscalao(String escalao) {
        this.escalao = escalao;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String toString(){
        return "[email- "+this.getEmail()+
                ";genero- "+this.getGenero()+
                ";escalao- "+this.getEscalao()+
                ";id- "+this.getId()+
                ";pago- "+this.getPago()+
                ";dorsal- "+this.getDorsal()+"]\n";
    }

}