package customerManager;

public class Record{
	
	private String table = null;
	private String idName = null;
	private Integer idValue = null;
	
	public Record(String table, String idName, Integer idValue){
		this.table = table;
		this.idName = idName;
		this.idValue = idValue;
	}

	public String getTable() {
		return table;
	}

	public String getIdName() {
		return idName;
	}

	public Integer getIdValue() {
		return idValue;
	}
}
