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
public interface RWLock {
     public abstract void acquireReadLock(int readerNum);
       public abstract void acquireWriteLock(int writerNum);
       public abstract void releaseReadLock(int readerNum);
       public abstract void releaseWriteLock(int writerNum);
}
