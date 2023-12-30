/*Name: Group 15
  Nguyễn Khánh Hà - ITCSIU21004
  Phạm Anh Huy - ITCSIU21133
  Trần Quang Bảo Duy - ITCSIU21176
  Purpose: This class is to set the state of the game */
package Lines;

public class StateSetting {
	private State state;
	 	
	public void setState(State state) {
		this.state = state;
	}
	 
	public void applyState() {
		this.state.handleRequest();
	}

}
