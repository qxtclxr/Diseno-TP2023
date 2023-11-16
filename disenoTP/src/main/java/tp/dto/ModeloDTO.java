package tp.dto;

import tp.entidad.Modelo;
import java.util.List;

public class ModeloDTO extends NoModificableDTO<Modelo> {
	private String nombre;
	private MarcaDTO marca;
	private List<Integer> años;
	
	@Override
	public String getText() {
		return this.nombre;
	}
	
	public MarcaDTO getMarca() {
		return this.marca;
	}
	
	public void setMarca(MarcaDTO marca) {
		this.marca = marca;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Integer> getAños() {
		return años;
	}

	public void setAños(List<Integer> años) {
		this.años = años;
	}
	
}
