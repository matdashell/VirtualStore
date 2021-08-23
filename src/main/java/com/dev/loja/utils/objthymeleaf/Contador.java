package com.dev.loja.utils.objthymeleaf;

import java.util.ArrayList;
import java.util.List;

public class Contador {

    int cont = 0;

    List<Integer> list = new ArrayList<>();

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public void addCont(int num){
        this.cont += num;
    }

    public void setListSize(int size) {
        for(int i = 0; i < size; i++){
            this.list.add(i);
        }
    }

    public List<Integer> getListCont(){
        return this.list;
    }
}
