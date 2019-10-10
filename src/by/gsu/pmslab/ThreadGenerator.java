package by.gsu.pmslab;

import java.util.Arrays;
import java.util.Scanner;

public class ThreadGenerator {

    public static void main(String[] args) {
        System.out.println("Введите количество потоков");
        Scanner scanner=new Scanner(System.in);
        int col=scanner.nextInt();
        System.out.println(col);
        int[] flows=new int[col];
        int[] end=new int[col];
        double[] result=new double[col];
        for (int i=1;i<=col;i++) {
            System.out.println("Выберите операцию для " + i + " потока\n1.Сложение\n2.Вычитание\n3.Умножение");
            int operation = scanner.nextInt();
            flows[i - 1] = operation;
        }
        for (int i=1;i<=col;i++) {
            System.out.println("Введите число для "+i+" потока");
            end[i-1] = scanner.nextInt();
        }
        execute(col,end,result);
        getResult(result,col,flows);
        }
        private static double[] execute(int col, int[] end, double[] result){
            for (int i=0;i<col;i++){
                ClaculatorThread claculatorThread=new ClaculatorThread(end[i]);
                claculatorThread.start();
                result[i]=claculatorThread.getResult();
            }
            System.out.println(Arrays.toString(result));
            return result;
        }
        private static void getResult(double[] result,int col,int[] flows){
        int fullResult=0;
            for (int i=0;i<col;i++){
                switch (flows[i]){
                    case 1:fullResult+=result[i];
                    break;
                    case 2:fullResult-=result[i];
                    break;
                    case 3:fullResult*=result[i];
                }
            }
            System.out.println("Ответ: "+fullResult);
        }
    }

