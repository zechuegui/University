import java.util.*;

import java.lang.*;

public class LSystems implements LSystem {

    Map<Character, String> rules;//dicionario criado para adicionar e conter regras

    protected String starter;//variavel cirada para armazenar o valor inicial

    protected String ret;//para ter uma string que vai sofrer alteraçoes e ser retornada

    public LSystems () { //construtor
        this.rules = new HashMap<Character, String>();//utilizaçao do dicionario para criar as regras de modo geral
    }

    public void setStart(String start) {
        this.starter = start;//para guardar o valor inicial
    }

    public void addRule(Character symbol, String word) {
        rules.put(symbol, word);//para aplicar as regras de modo geral
    }

    protected String iter1 (String ret) {//recebe a string iterada 1 vez

        String arg = "";

        for (int i = 0; i < ret.length(); i++) {
            char c = ret.charAt(i);
            String val = rules.get(c);//verificar a string para procurar por chars que tenham uma regra aplicavel

            if (val != null){
                arg += val;//para adicionar a regra
            } else {
                arg += c;//copiar o simbolo se nao houver regra
            }
        }

        return arg;

    }

    public String iter(int n) {

        ret = starter;

        for (int i = 0; i < n; i++) {
            rules.get(ret);//para obter a letra inicial e fazer as n iterações
            ret = iter1(ret);
        }

        return ret;

    }

    public String getRet(){
        return ret;
    }

}