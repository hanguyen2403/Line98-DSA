/*Name: Group 15
  Nguyễn Khánh Hà - ITCSIU21004
  Phạm Anh Huy - ITCSIU21133
  Trần Quang Bảo Duy - ITCSIU21176
  Purpose: This class is to apply undo feature of game*/
package Lines;

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
