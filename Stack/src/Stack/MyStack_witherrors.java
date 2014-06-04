/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.epam.kiev.pkg;
package Stack;

import java.util.EmptyStackException;

/**
 *
 * @author Andrii_Rodionov
 */
public class MyStack_witherrors {
    private Integer[] arr; 
    private int size;
    private final int MAX_STACK_SIZE;
    
    
    
    public MyStack_witherrors(int stack_size){
        MAX_STACK_SIZE = stack_size;
        arr = new Integer[MAX_STACK_SIZE];
        size=0;
    }
    
    public MyStack_witherrors(){
        this(10);
     }
    
    int getArr(int i){
    	return arr[i];
    }
    
    int getSize(){
    	return size;
    }
    void setArr(int i, Integer element){
    	arr[i]=element;
    }
    
   void setSize(int i){
    	size=i;
    }
 
    public void push(Integer item){
        if(size == MAX_STACK_SIZE){
            throw new IndexOutOfBoundsException();
        }
        arr[size] = item;
        size++;
    }  

    public Integer peek(){
        if(empty()){
            throw new EmptyStackException();
        }
            
        return arr[(size-1)];
    }
 
    public Integer pop(){
        if(empty()){
            throw new EmptyStackException();
        }
        int currentElement=arr[size-1];
        arr[(size-1)]=null;
        size--;
        return currentElement;
    }
 
    public boolean empty(){
        return (size == 0);
    }
}
