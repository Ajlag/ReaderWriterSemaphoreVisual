/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforireaderwriter;

/**
 *
 * @author Ajla
 */
public class Main {
    public static final int NUM_OF_READERS = 3;
      public static final int NUM_OF_WRITERS =3;
      
    public static void main(String args[]) throws Throwable{
        
    ReadWrite rw=new ReadWrite();
    rw.setVisible(true);
  
       for (int i = 0; i < 3; i++) {
            rw.readerArray[i].start();
         }
      
         for (int i = 0; i < 3; i++) {
            rw.writerArray[i].start();
         }
    }
    
}
