/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaforireaderwriter;

import java.awt.Color;
import java.util.concurrent.Semaphore;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Ajla
 */
class Database implements RWLock{
      private int readerCount;  // the number of active readers
      private Semaphore mutex;  // controls access to readerCount
      private Semaphore db;     // controls access to the database
      public JButton[] buttons;
      public JLabel label2;

       public Database() {
         readerCount = 0;
         mutex = new Semaphore(1);
         db = new Semaphore(1);
      }
         public Database(JButton[] button, JLabel labels) {
         readerCount = 0;
         buttons=button;
         label2=labels;
         mutex = new Semaphore(1);
         db = new Semaphore(1);
      }
   
       public void acquireReadLock(int readerNum) {
         try{
         //mutual exclusion for readerCount 
            mutex.acquire();
               }
             catch (InterruptedException e) {}
      
        
         ++readerCount;
      
      // if I am the first reader tell all others
      // that the database is being read
         if (readerCount == 1){
            try{
               db.acquire();
            }
                catch (InterruptedException e) {}
         }
      
         System.out.println("Reader " + readerNum + " is reading. Reader count = " + readerCount);
          buttons[readerNum].setBackground(Color.red);
               buttons[readerNum].setOpaque(true);
               buttons[readerNum].setText("Čitač");
               label2.setIcon(new ImageIcon("C:/Users/Ajla/Desktop/SemaforiReaderWriter/src/semaforireaderwriter/Read-S.png"));
        
         //mutual exclusion for readerCount
         mutex.release();
      }
   
       public void releaseReadLock(int readerNum) {
         try{
         //mutual exclusion for readerCount
            mutex.acquire();
          
         }
             catch (InterruptedException e) {}
      
         --readerCount;
      
      // if I am the last reader tell all others
      // that the database is no longer being read
         if (readerCount == 0){
            db.release();
         }
      
         System.out.println("Reader " + readerNum + " is done reading. Reader count = " + readerCount);
       buttons[readerNum].setBackground(new JButton().getBackground());
        buttons[readerNum].setText("Završio čitač");
      
      //mutual exclusion for readerCount
         mutex.release();
      }
   
       public void acquireWriteLock(int writerNum) {
         try{
            db.acquire();
             buttons[writerNum].setBackground(Color.green);
      buttons[writerNum].setOpaque(true);
      buttons[writerNum].setText("Pisac");
      label2.setIcon(new ImageIcon("C:/Users/Ajla/Desktop/SemaforiReaderWriter/src/semaforireaderwriter/Write-S.png"));
             
         }
             catch (InterruptedException e) {}
         System.out.println("Writer " + writerNum + " is writing.");
      }
   
       public void releaseWriteLock(int writerNum) {
           
         System.out.println("Writer " + writerNum + " is done writing.");
         
         db.release();
        buttons[writerNum].setBackground(new JButton().getBackground());
        buttons[writerNum].setText("Završio pisac");
      }
   
   
   }