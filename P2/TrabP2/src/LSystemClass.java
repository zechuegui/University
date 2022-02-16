public class LSystemClass implements LSystem {

    public String start, texto;
    public String[] word= new String[20];
    public char[] symbol= new char[20];
    public  int cont=0;

    public void setStart(String start) {

        this.start = start;
    }
    public void addRule(Character symbol, String word) {

        this.symbol[cont]=symbol;
        this.word[cont]=word;
        cont++;
    }
    public String iter(int n) {

        texto = start;

        while(n>0){

            for (int i=0; i<symbol.length;i++){

                String help = String.valueOf(symbol[i]);
                texto = texto.replaceAll(help,word[i]);
            }
            texto = texto.toUpperCase();
            n--;
        }
        return texto;
    }
}
