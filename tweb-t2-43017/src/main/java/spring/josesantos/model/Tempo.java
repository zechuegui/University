package spring.josesantos.model;

public class Tempo {
    
    private int dorsal;
    private int id;
    private String ponto;
    private String time;
    
    public Tempo(int dorsal, int id, String ponto, String tempo) {
        this.dorsal = dorsal;
        this.id = id;
        this.ponto = ponto;
        this.time = tempo;
    }
    public int getDorsal() {
        return dorsal;
    }
    public String getTempo() {
        return time;
    }
    public void setTempo(String time) {
        this.time = time;
    }
    public String getPonto() {
        return ponto;
    }
    public void setPonto(String ponto) {
        this.ponto = ponto;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String toString(){
        return "[dorsal- "+this.getDorsal()+
                ";id- "+this.getId()+
                ";ponto- "+this.getPonto()+
                ";time- "+this.getTempo()+"]\n";
    }
}

