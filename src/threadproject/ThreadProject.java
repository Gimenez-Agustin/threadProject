package threadproject;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean notNumber = true;
        boolean incorrect = false;
        int numOfThreads = 0;
        int numOfPools = 0;
        while (notNumber) {
            try {
                if(incorrect){
                    System.err.println("check the input. It should be a number and more than 0");
                    incorrect = false;
                }
                Scanner scan = new Scanner(System.in);
                System.out.println("Please enter the number of applications: ");
                String threads = scan.nextLine();
                numOfThreads = Integer.valueOf(threads);
                System.out.println("Please enter the number of deployment per phase: ");
                String pools = scan.nextLine();                
                numOfPools = Integer.valueOf(pools);
                if(numOfThreads<0 || numOfPools<0){
                    incorrect = true;
                }else{
                    notNumber = false;
                }                
            }catch(Exception ex){
                incorrect = true;
            }
        }

        ExecutorService executorExample = Executors.newFixedThreadPool(numOfPools);
        
        for (int i= 1; i <= numOfThreads; i++) {
            System.out.println("Runnable " + i);
            executorExample.execute(new Runnable() {

                public void run() {
                    try {
                        Random r = new Random();
                        int j = r.nextInt(1000000000);
                        Thread.currentThread().sleep(500);
                        System.out.println("start deploy application with ID: " + j);
                        Thread.currentThread().sleep(1000);
                        System.out.println("deploying " + j);
                        Thread.currentThread().sleep(10000);
                        System.out.println("Deploy Done! " + j);
                    } catch (InterruptedException e) {
                        System.out.println("ERROR" + e.getMessage());
                    }
                }                
            });
        }
        executorExample.shutdown();
    }
    }