package tp.dto;

public abstract class NoModificableDTO<T> {
	
	private Long id;
	
	public abstract String getText();
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return this.getText().toUpperCase();
	}
}
