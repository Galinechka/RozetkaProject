package Stack;

import static org.junit.Assert.*;
import java.util.EmptyStackException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testMyStack {

	MyStack_witherrors myStack;
	
	@Before
	public void setUp() throws Exception {
		myStack= new MyStack_witherrors();
	}

	@After
	public void tearDown() throws Exception {
		myStack.setSize(0);
		for (int i=0; i<myStack.getSize(); i++){
				myStack.setArr(i, null);
		}
	}

	@Test
	public void checkEmptyForNewStack() {
		assertTrue(myStack.empty());
	}
	
	@Test
	public void checkPushMetod() {
		myStack.push(1);
		myStack.push(2);
		myStack.push(3);
		assertFalse(myStack.empty());
		assertSame(3, myStack.getArr(2));
	}
	@Test (expected=IndexOutOfBoundsException.class)
	public void checkPushException() {
		for (int i=0; i<12; i++){
		myStack.push(1);
		}
	}
	
	@Test(expected=EmptyStackException.class)
	public void checkPeekMetod() {
		myStack.peek();
		myStack.push(2);
		assertSame(2, myStack.peek());
		assertFalse(myStack.empty());
	}
	
	@Test(expected=NullPointerException.class)
	public void checkPopMetod() {
		myStack.push(1);
		myStack.push(2);
		myStack.push(3);
		int sizeBefore=myStack.getSize();
		assertSame(3, myStack.pop());
		assertSame(sizeBefore-1, myStack.getSize());
		myStack.getArr(sizeBefore-1);
	}
	@Test(expected=EmptyStackException.class)
	public void checkPopException() {
		myStack.pop();
	}
	
	@Test
	public void checkPushPeekPop() {
		myStack.push(1);
		myStack.push(2);
		myStack.push(3);
		assertSame(3, myStack.pop());
		myStack.push(4);
		assertSame(4, myStack.peek());
		assertSame(3, myStack.getSize());
		assertSame(4, myStack.getArr(myStack.getSize()-1));
	}
	
}
