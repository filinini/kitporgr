package com.example.kitpo_rgr.List;

import com.example.kitpo_rgr.Builder.UserType;
import com.example.kitpo_rgr.Comparator.Comparator;
import java.util.ArrayList;

public class TList implements TListInterface
{
	private class Node
	{
		public Node   next;
		public Object data;

		public Node(Object data)
		{
			this.data = data;
			this.next = null;
		}
	}

	private Node       head;
	private Node       tail;
	private int        size;
	private UserType builder;

	public TList(){
		init();
	}

	public TList(UserType builder)
	{
		this.builder    = builder;
		this.head       = null;
		this.tail       = null;
		this.size       = 0;
	}

	public TList(UserType[] arr) {
		for (int i=0; i<arr.length; i++)
		{
			pushEnd(arr[i]);
		}
	}

	public void init()
	{
		this.builder    = null;
		this.head       = null;
		this.tail       = null;
		this.size       = 0;
	}

	public boolean clear()
	{
        if (head == null)
        {
            return false;
        }

        while (head != null)
        {
            delete(0);
        }

		return true;
	}

	public boolean pushFront(Object obj)
	{

		Node nNode = new Node(obj);

		if (head == null)
		{
			head = nNode;
			tail = nNode;
		}
		else
		{
			Node temp = head;
			head      = nNode;
			head.next = temp;
		}
		size++;
		return true;
	}

	public boolean pushEnd(Object data)
	{
		Node nNode = new Node(data);

		if (head == null)
		{
			head = nNode;
			tail = nNode;
		}
		else
		{
			tail.next = nNode;
			tail      = tail.next;
		}
		size++;
		return true;
	}

	public boolean add(Object data, int index)
	{
		Node nNode = new Node(data);

		if (head == null)
		{
			head = nNode;
			tail = nNode;
		}
		else
		{
			Node temp, current;
			temp    = head;
			current = null;

			for (int i = 0; i < index; i++)
			{
				current = temp;
				temp    = temp.next;
			}

			current.next = nNode;
			nNode.next   = temp;
		}
		size++;
		return true;
	}

	public boolean delete(int index)
	{
        if (size < 0 || index < 0)
        {
            return false;
        }

		Node toDel, toDelPrev = null;

		if (head == null)
		{
			System.out.println("List is empty");
			return false;
		}
		else
		{
			if (head != tail)
			{
                if (index > 0)
                {
                    toDelPrev = findNode(index - 1);
                    toDel     = toDelPrev.next;
                }
                else
                {
                    toDel = head;
                }

				if (toDelPrev != null)
				{
					toDelPrev.next = toDel.next;
					toDel          = null;
				}
				//Попали в голову
				else
				{
					head  = toDel.next;
					toDel = null;
				}
			}
			else
			{
				head = tail = null;
			}
		}
		size--;
		return true;
	}

	public Object find(int index)
	{
		Object dataNode;
		Node   current = head;

		if (index == 0)
		{
			dataNode = current.data;
			return dataNode;
		}

        for (int i = 0; i < index; i++)
        {
            current = current.next;
        }
		dataNode = current.data;
		return dataNode;
	}

	public int find(Object obj)
	{
		Node current = head;
		int  index   = 0;

        if (head == null)
        {
            return -1;
        }
        else
        {
            while (current != null)
            {
                if (current.data == obj)
                {
                    return index;
                }
                index++;
                current = current.next;
            }
        }
		return -1;
	}

	public void forEach(DoIt action) {
		ArrayList arr = new ArrayList();

		for (Node cur = head; cur != null; cur = cur.next)
			arr.add(cur.data);

		for (int i=0; i<arr.size();i++) {
			String str;
			if (arr.get(i) == null) str = "null ";
			else str = arr.get(i).toString() + " ";
			action.doIt(str);
		}
	}

	public void sort(Comparator comparator)
	{
		head = quicksort(head, comparator);
	}

	private Node quicksort(Node p, Comparator comparator)
	{
		Node m, q, p1, p2;
		if (p == null || p.next == null)
		{
			return p;
		}
		m = p;
		p = p.next;
		p1 = p2 = null;
		while (p != null)
		{
			q = p;
			p = p.next;
			int comp = comparator.compare(q.data, m.data);
			if (comp < 0 || comp == 0)
			{
				q.next = p1;
				p1 = q;
			}
			else
			{
				q.next = p2;
				p2 = q;
			}
		}

		p1 = quicksort(p1, comparator);
		p2 = quicksort(p2, comparator);

		m.next = p2;
		if (p1 == null)
		{
			return m;
		}
		for (q = p1; q.next != null; q = q.next);
		q.next = m;
		return p1;
	}

	private Node findNode(int id)
	{
		Node res = head;
        for (int i = 0; i < id; i++)
        {
            res = res.next;
        }
		return res;
	}

	public int getSize()
	{
		return this.size;
	}

	public UserType getBuilder()
	{
		return builder;
	}

	public String toString() {
		Node cur = head;
		String str ="";
		for (int i = 0; i < size; i++) {
			str += (cur.data.toString());
			str+="\n";
			cur = cur.next;
			}
		return str;
	}
}
