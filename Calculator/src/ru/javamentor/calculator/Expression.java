package ru.javamentor.calculator;

import javax.naming.SizeLimitExceededException;

public class Expression {
    String exp;
    String expFin;
    String[] value;
    Operation operation;
    public Expression(String exp){
        this.exp=exp;
    }
    public void getResult() throws SizeLimitExceededException {
        if (exp.contains("+")){
            expFin=exp.replace("+"," ");
            value=expFin.split(" ");
            if (value.length<=2){
                operation=new Operation(value[0],"+",value[1]);
                operation.action();
            }
            else error();
        }
        if (exp.contains("-")){
            expFin=exp.replace("-"," ");
            value=expFin.split(" ");
            if (value.length<=2){
                operation=new Operation(value[0],"-",value[1]);
                operation.action();
            }
            else error();
        }
        if (exp.contains("*")){
            expFin=exp.replace("*"," ");
            value=expFin.split(" ");
            if (value.length<=2){
                operation=new Operation(value[0],"*",value[1]);
                operation.action();
            }
            else error();
        }
        if (exp.contains("/")){
            expFin=exp.replace("/"," ");
            value=expFin.split(" ");
            if (value.length<=2){
                operation=new Operation(value[0],"/",value[1]);
                operation.action();
            }
            else error();
        }
    }

    private void error() throws SizeLimitExceededException {
        throw new SizeLimitExceededException("Не болле одной арифметической оперцаии");
    }
}
