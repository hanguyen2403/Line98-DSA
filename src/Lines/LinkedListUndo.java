package Lines;

class Link {
	public int[][] ball = new int[Constant.Row][Constant.Column];
	public int[] color = new int[3];
	public long score;
	public Link next;
	public Link previous;

	public Link(int[][] b, int[] c, long s) {
        for (int i=0;i<Constant.Row;i++)
            for (int j=0;j<Constant.Column;j++)
            	ball[i][j]=b[i][j];

        for (int k=0; k < 3; k++)
            color[k] = c[k];

        score = s;
	}
	
}

public class LinkedListUndo {
	private Link first;
	private Link last;
	   
	public LinkedListUndo() {
		first = null;
		last = null;
	}
	   
	public boolean isEmpty() {
		return first==null; 
	}
	
	public void insertLast(int[][] b, int[] c, long s) {
		Link newLink = new Link(b, c, s);
		
		if(isEmpty())
			first = newLink;
		else {
			last.next = newLink;
			newLink.previous = last;
		}
		last = newLink;
	}
	
	public Link deleteFirst() {
		Link temp = first;
		if(first.next == null) {
			last = null;
			first = null;
		}
		else {
			first.next.previous = null;
			first = first.next;
		}
		return temp;
	}
	
	public Link deleteLast() {
		Link temp = last;
		if(last.previous == null) {
			first = null;
			last = null;
		}
		else {
			last.previous.next = null;
			last = last.previous;
		}
		return temp;
	}
}
