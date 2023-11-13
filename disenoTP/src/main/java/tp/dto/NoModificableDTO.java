package tp.dto;

public abstract class NoModificableDTO<T> {
	
	public Long id;
	public abstract String getText();
	
	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return this.getText().toUpperCase();
	}
}
