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
