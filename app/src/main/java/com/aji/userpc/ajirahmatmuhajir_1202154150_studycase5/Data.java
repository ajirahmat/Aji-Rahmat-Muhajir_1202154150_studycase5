package com.aji.userpc.ajirahmatmuhajir_1202154150_studycase5;

public class Data {

    String todoName, todoDesc, todoPriority;

    //inisiai variable
    //constuctor class data
    public Data(String todoName, String todoDesc, String todoPriority){
        this.todoName=todoName;
        this.todoDesc=todoDesc;
        this.todoPriority=todoPriority;
    }

    public String getTodoName() {

        return todoName;
    }

    public String getTodoDesc() {

        return todoDesc;
    }

    public String getTodoPriority() {
        return todoPriority;
    }

}
