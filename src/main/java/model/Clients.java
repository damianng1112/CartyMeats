package model;

public class Clients {
	private String id;
	private String client_name;
	private String client_address;
	private String facing;
	
	public Clients() {
		
	}

	public Clients(String client_name, String client_address, String facing) {
		this.client_name = client_name;
		this.client_address = client_address;
		this.facing = facing;
	}

	public Clients(String id, String client_name, String client_address, String facing) {
		this.id = id;
		this.client_name = client_name;
		this.client_address = client_address;
		this.facing = facing;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getClient_address() {
		return client_address;
	}

	public void setClient_address(String client_address) {
		this.client_address = client_address;
	}

	public String getFacing() {
		return facing;
	}

	public void setFacing(String facing) {
		this.facing = facing;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
