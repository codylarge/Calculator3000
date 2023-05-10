package utils;

import java.util.Vector;

public class MyStack
{
    private class StackNode
    {
        private Object data;
        private StackNode next;
        private StackNode(Object data, StackNode next)
        {
            this.data = data;
            this.next = next;
        }
        private StackNode(Object data)
        {
            this(data, null);
        }
    }
    private StackNode top;
    private int size;

    public MyStack()
    {
        this.top = null;
        this.size = 0;
    }

    public void push(Object data)
    {
        if(this.isEmpty()){}
        this.top = new StackNode(data, this.top);
        this.size++;
    }

    public Object pop()
    {
        if(this.isEmpty()) throw new IllegalArgumentException("Stack is empty");
        Object retData = this.top.data;
        this.top = this.top.next;
        size--;
        return retData;
    }

    public Object peek()
    {
        if(this.top == null) return null;
        return this.top.data;
    }

    public int size()
    {
        return this.size;
    }

    public boolean isEmpty()
    {
        return this.size == 0;
    }
}
