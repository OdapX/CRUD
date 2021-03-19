package application;

public class Data {
      private  Integer id;
      private String first_name;
      private String last_name;
      private String adress;
      
      public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Data(Integer id,String fname,String lname,String adress) {
    	  this.id=id;
    	  this.last_name=lname;
    	  this.first_name=fname;
    	  this.adress=adress;
      }
}
