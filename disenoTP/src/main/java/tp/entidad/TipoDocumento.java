package tp.entidad;

public enum TipoDocumento {
	DNI("DNI (Documento Nacional de Identidad)"),
	LC("LC (Libreta Civica)"),
	LE("LE (Libreta de Enrolamiento)");
	
	private String string;
	
	private TipoDocumento(String string) {
		this.string = string;
	}
	
	// the toString just returns the given name
   @Override
   public String toString() {
       return string;
   }
}
