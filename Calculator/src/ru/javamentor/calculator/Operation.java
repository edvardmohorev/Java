package ru.javamentor.calculator;

import java.util.Map;


public class Operation {
    private String a,b,operation;
    private Values values = new Values();
    private int x, y,result;
    private boolean isRime=false;

    public Operation(String a, String operation, String b) {
        this.a = a;
        this.b = b;
        this.operation = operation;
    }

    public void action() {
        switch (operation) {
            case "+":
                sum();
                break;
            case "-":
                minus();
                break;
            case "*":
                multiplications();
                break;
            case "/":
                division();
                break;
        }
    }

    private void sum() {
        getNum(a, b);
        check();
        result = x + y;
        answer();
    }

    private void minus() {
        getNum(a, b);
        check();
        result = x - y;
         answer();
    }

    private void multiplications() {
        getNum(a, b);
        check();
        result = x * y;
        answer();
    }

    private void division() {
        getNum(a, b);
        check();
        result = x / y;
        answer();
    }

    private void getNum(String a, String b) {
        if (values.getValue().get(a) != null && values.getValue().get(a) != null) {
            x = values.getValue().get(a);
            y = values.getValue().get(b);
            isRime=true;
        } else if (values.getValue().get(a) == null && values.getValue().get(a) == null) {
            x = Integer.parseInt(a);
            y = Integer.parseInt(b);
        }
    }

    private void check() {
        if (x < 1 || x > 10 || y < 1 || y > 10) {
            throw new NumberFormatException("Неверно ведены числа");
        }
    }

    private static Object getValByKey(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }
    private void answer(){
        if (isRime){
                System.out.println(a+" "+operation+" "+b+" = "+getValByKey(values.value,result));
        }
        else
            System.out.println(a+" "+operation+" "+b+" = "+result);
    }
}

