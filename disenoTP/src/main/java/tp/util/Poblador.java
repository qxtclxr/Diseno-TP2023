package tp.util;

import tp.entidad.*;
import tp.logica.*;
import tp.app.App;
import tp.dao.*;
import tp.exception.ObjetoNoEncontradoException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;


public class Poblador {
	
	public static void poblar() {
		try {
			poblarAjusteHijos();
			poblarPaises();
			poblarProvinciasArgentinas();
			poblarAutos();
			poblarKms();
			poblarPorcentajeKm();
			poblarSiniestros();
			poblarPorcentajeSiniestros();
			poblarCobertura();
			poblarPorcentajeCobertura();
			poblarDescuentoPorUnidad();
			poblarPorcentajeRobo();
			poblarMedidasDeSeguridad();
			poblarPorcentajesMedidas();
			poblarPorcentajeLocalidad();
			poblarDerechosDeEmision();
			poblarCliente();
			poblarSucursal();
		} catch (ObjetoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void poblarSucursal() {
		SucursalDAO daoSuc = new SucursalDAO();
		Sucursal suc = new Sucursal();
		suc.setCodigoSucursal(0);
		suc.setSecuenciaDePoliza(0);
		daoSuc.updateInstance(suc);
	}
	
	public static Usuario poblarUsuario(String username, String pass) {
		SucursalDAO daoSuc = new SucursalDAO();
		UsuarioDAO dao = new UsuarioDAO();
		Usuario user = new Usuario();
		user.setApellido("Perez");
		user.setContrasenia(pass);
		user.setNickname(username);
		user.setNombre("Juan");
		user.setTipoDocumento(TipoDocumento.DNI);
		user.setSucursalAsociada(daoSuc.getAll().get(0));
		dao.updateInstance(user);
		return user;
	}
	
	public static void poblarCliente() {
		ClienteDAO dao = new ClienteDAO();
		//DomicilioDAO daoDomi = new DomicilioDAO();
		LocalidadDAO daoLocal = new LocalidadDAO();
		
		Domicilio domicilio1 = new Domicilio();
		Cliente cliente1 = new Cliente();
		domicilio1.setCalle("Avenida Ramirez");
		domicilio1.setNumero("555");
		domicilio1.setLocalidad(daoLocal.getAll().get(0));
		domicilio1.setCodigoPostal("3100");
		//daoDomi.saveInstance(domicilio1);
		
		cliente1.setNroCliente("123456789");
		cliente1.setNombres("Jorge Amor");
		cliente1.setApellido("Ameal");
		cliente1.setTipoDocumento(TipoDocumento.DNI);
		cliente1.setNroDocumento("33333333");
		cliente1.setTipoCliente(TipoCliente.NORMAL);
		cliente1.setNroCuil("22-33333333-6");
		cliente1.setFechaNacimiento(LocalDate.of(1966, 5, 22));
		cliente1.setCondicionIVA(TipoIVA.MONOTRIBUTISTA);
		cliente1.setCorreoElectronico("amor@hotmail.com");
		cliente1.setEstadoCivil(EstadoCivil.VIUDO);
		cliente1.setProfesion("Dirigente");
		cliente1.setAnioRegistro(LocalDateTime.now().withDayOfYear(1));
		cliente1.setSexo(Sexo.MASCULINO);
		cliente1.setFechaModificacionEstado(LocalDateTime.now());
		cliente1.setCantSiniestrosCliente(1);
		cliente1.setDomicilio(domicilio1);
		dao.saveInstance(cliente1);
		
		Domicilio domicilio2 = new Domicilio();
        domicilio2.setCalle("Avenida General Paz");
        domicilio2.setNumero("123");
        domicilio2.setLocalidad(daoLocal.getAll().get(2));
        domicilio2.setCodigoPostal("3200");
        //daoDomi.saveInstance(domicilio2);

        // Crear un nuevo cliente
        Cliente cliente2 = new Cliente();
        cliente2.setNroCliente("123456790");
        cliente2.setNombres("Maria de las Mercedes");
        cliente2.setApellido("Gomez Gertz");
        cliente2.setTipoDocumento(TipoDocumento.LE);
        cliente2.setNroDocumento("44444444");
        cliente2.setTipoCliente(TipoCliente.ACTIVO);
        cliente2.setNroCuil("22-44444444-6");
        cliente2.setFechaNacimiento(LocalDate.of(1980, 8, 15));
        cliente2.setCondicionIVA(TipoIVA.RESPONSABLE_INSCRIPTO);
        cliente2.setCorreoElectronico("maria@gmail.com");
        cliente2.setEstadoCivil(EstadoCivil.CASADO);
        cliente2.setProfesion("Arquitecta");
        cliente2.setAnioRegistro(LocalDateTime.now().withDayOfYear(150));
        cliente2.setSexo(Sexo.FEMENINO);
        cliente2.setFechaModificacionEstado(LocalDateTime.now());
        cliente2.setCantSiniestrosCliente(2);
        cliente2.setDomicilio(domicilio2);
        dao.saveInstance(cliente2);
		
        // Crear un nuevo domicilio
        Domicilio domicilio3 = new Domicilio();
        domicilio3.setCalle("25 de Mayo");
        domicilio3.setNumero("789");
        domicilio3.setLocalidad(daoLocal.getAll().get(3));
        domicilio3.setCodigoPostal("3300");
        //daoDomi.saveInstance(domicilio3);

        // Crear un nuevo cliente
        Cliente cliente3 = new Cliente();
        cliente3.setNroCliente("123456791");
        cliente3.setNombres("Carlos Luciano Albertino");
        cliente3.setApellido("Rodriguez Paz");
        cliente3.setTipoDocumento(TipoDocumento.LC);
        cliente3.setNroDocumento("55555555");
        cliente3.setTipoCliente(TipoCliente.PLATA);
        cliente3.setNroCuil("22-55555555-6");
        cliente3.setFechaNacimiento(LocalDate.of(1975, 3, 10));
        cliente3.setCondicionIVA(TipoIVA.MONOTRIBUTISTA);
        cliente3.setCorreoElectronico("carlos@gmail.com");
        cliente3.setEstadoCivil(EstadoCivil.SOLTERO);
        cliente3.setProfesion("Ingeniero");
        cliente3.setAnioRegistro(LocalDateTime.now().withDayOfYear(200));
        cliente3.setSexo(Sexo.MASCULINO);
        cliente3.setFechaModificacionEstado(LocalDateTime.now());
        cliente3.setCantSiniestrosCliente(0);
        cliente3.setDomicilio(domicilio3);
		dao.saveInstance(cliente3);
	}
	
	public static void poblarDerechosDeEmision() {
		DerechosDeEmisionDAO dao = new DerechosDeEmisionDAO();
		DerechosDeEmision derechos = new DerechosDeEmision();
		dao.saveInstance(derechos);
		
		ValorDerechosDeEmisionDAO daoValor = new ValorDerechosDeEmisionDAO();
		ValorDerechosDeEmision valor = new ValorDerechosDeEmision();
		valor.setDerechosAsociados(derechos);
		valor.setFechaModificacion(LocalDateTime.now());
		valor.setValorNumerico(3000F);
		derechos.setValorActualDerechosDeEmision(valor);
		daoValor.updateInstance(valor);
	}
	
	public static void poblarPorcentajeLocalidad() {
		PorcentajeRiesgoLocalidadDAO dao = new PorcentajeRiesgoLocalidadDAO();
		LocalidadDAO daoLocal = new LocalidadDAO();
		List<Localidad> entidades = daoLocal.getAll();
		Random rand = new Random();
		float valor = rand.nextFloat(5);
		for(Localidad entidad : entidades) {
			PorcentajeRiesgoLocalidad porc = new PorcentajeRiesgoLocalidad();
			porc.setValorNumerico(valor++);
			porc.setLocalidadAsociada(entidad);
			porc.setFechaModificacion(LocalDateTime.now());
			entidad.setValorActualFactorRiesgo(porc);
			dao.updateInstance(porc);
		}
	}
	
	public static void poblarPorcentajesMedidas() {
		PorcentajeMedidaDeSeguridadDAO dao = new PorcentajeMedidaDeSeguridadDAO();
		List<MedidaDeSeguridad> entidades = GestorMedidaDeSeguridad.getAll(); 
		float valor = 1;
		for(MedidaDeSeguridad entidad : entidades) {
			PorcentajeMedidaDeSeguridad porc = new PorcentajeMedidaDeSeguridad();
			porc.setValorNumerico(valor++);
			porc.setMedidaAsociada(entidad);
			porc.setFechaModificacion(LocalDateTime.now());
			entidad.setValorActualPorcMedidaDeSeg(porc);
			dao.updateInstance(porc);
		}
	}
	
	public static void poblarMedidasDeSeguridad() {
		MedidaDeSeguridadDAO dao = new MedidaDeSeguridadDAO();
		
		MedidaDeSeguridad medida = new MedidaDeSeguridad();
		medida.setPregunta("Se guarda en Garage?");
		dao.updateInstance(medida);
		
		medida = new MedidaDeSeguridad();
		medida.setPregunta("Tiene alarma?");
		dao.updateInstance(medida);
		
		medida = new MedidaDeSeguridad();
		medida.setPregunta("Posee dispositivo de rastreo vehicular?");
		dao.updateInstance(medida);
		
		medida = new MedidaDeSeguridad();
		medida.setPregunta("Posee tuercas antirobo en las cuatro cuerdas?");
		dao.updateInstance(medida);
		
	}
	
	public static void poblarPorcentajeRobo() {
		PorcentajeEstadisticaRoboDAO dao = new PorcentajeEstadisticaRoboDAO();
		AnioModeloDAO daoAnio = new AnioModeloDAO();
		List<AnioModelo> entidades = daoAnio.getAll();
		Random rand = new Random();
		float valor = rand.nextFloat(5);
		for(AnioModelo entidad : entidades) {
			PorcentajeEstadisticaRobo porc = new PorcentajeEstadisticaRobo();
			porc.setValorNumerico(valor++);
			porc.setAnioModeloAsociado(entidad);
			porc.setFechaModificacion(LocalDateTime.now());
			entidad.setValorActualPorcentajeEstadisticaRobo(porc);
			dao.updateInstance(porc);
		}
	}
	
	public static void poblarPorcentajeKm() {
		PorcentajeKMRealizadosDAO dao = new PorcentajeKMRealizadosDAO();
		List<RangoKMRealizados> entidades = GestorRangoKMRealizados.getAll();
		float valor = 1;
		for(RangoKMRealizados entidad : entidades) {
			PorcentajeKMRealizados porc = new PorcentajeKMRealizados();
			porc.setValorNumerico(valor++);
			porc.setRangoAsociado(entidad);
			porc.setFechaModificacion(LocalDateTime.now());
			entidad.setValorActualPorcentajeKMRealizados(porc);
			dao.updateInstance(porc);
		}
	}
	
	public static void poblarPorcentajeCobertura() {
		PorcentajeCoberturaDAO dao = new PorcentajeCoberturaDAO();
		List<Cobertura> entidades = GestorCobertura.getAll();
		float valor = 1;
		for(Cobertura entidad : entidades) {
			PorcentajeCobertura porc = new PorcentajeCobertura();
			porc.setValorNumerico(valor++);
			porc.setCoberturaAsociada(entidad);
			porc.setFechaModificacion(LocalDateTime.now());
			entidad.setValorActualPorcentajeCobertura(porc);
			dao.updateInstance(porc);
		}
	}
	
	public static void poblarCobertura() {
		CoberturaDAO dao = new CoberturaDAO();
		
		Cobertura cobertura = new Cobertura();
		cobertura.setDescripcion("");
		cobertura.setTipoCobertura("Responsabilidad Civil");
		dao.updateInstance(cobertura);
		
		cobertura = new Cobertura();
		cobertura.setDescripcion("");
		cobertura.setTipoCobertura("Resp. Civil + Robo o Incendio Total");
		dao.updateInstance(cobertura);
		
		cobertura = new Cobertura();
		cobertura.setDescripcion("");
		cobertura.setTipoCobertura("Todo Total");
		dao.updateInstance(cobertura);
		
		cobertura = new Cobertura();
		cobertura.setDescripcion("");
		cobertura.setTipoCobertura("Terceros Completos");
		dao.updateInstance(cobertura);
		
		cobertura = new Cobertura();
		cobertura.setDescripcion("");
		cobertura.setTipoCobertura("Todo Riesgo con Franquicia");
		dao.updateInstance(cobertura);
		
	}
	
	public static void poblarPorcentajeSiniestros() {
		PorcentajeCantSiniestrosDAO dao = new PorcentajeCantSiniestrosDAO();
		List<RangoCantSiniestros> entidades = GestorRangoCantSiniestros.getAll();
		float valor = 1;
		for(RangoCantSiniestros entidad : entidades) {
			PorcentajeCantSiniestros porc = new PorcentajeCantSiniestros();
			porc.setValorNumerico(valor++);
			porc.setRangoAsociado(entidad);
			porc.setFechaModificacion(LocalDateTime.now());
			entidad.setValorActualPorcentajeCantSiniestros(porc);
			dao.updateInstance(porc);
		}
	}
	
	public static void poblarDescuentoPorUnidad() {
		PorcentajeDescPorUnidadDAO porcDao = new PorcentajeDescPorUnidadDAO();
		DescuentoPorUnidadDAO descDao = new DescuentoPorUnidadDAO();
		PorcentajeDescPorUnidad porc = new PorcentajeDescPorUnidad();
		DescuentoPorUnidad desc = new DescuentoPorUnidad();
		
		desc.setDesdeCantU(0);
		descDao.updateInstance(desc);
		
		porc.setValorNumerico(0F);
		porc.setFechaModificacion(LocalDateTime.now());
		desc.setValorActualDescPorUnidad(porc);
		porc.setDescAsociado(desc);
		porcDao.updateInstance(porc);
		
		desc = new DescuentoPorUnidad();
		desc.setDesdeCantU(1);
		descDao.updateInstance(desc);
		
		porc = new PorcentajeDescPorUnidad();
		porc.setValorNumerico(2.5F);
		porc.setFechaModificacion(LocalDateTime.now());
		desc.setValorActualDescPorUnidad(porc);
		porc.setDescAsociado(desc);
		porcDao.updateInstance(porc);
	}
	
	public static void poblarAjusteHijos() {
		PorcentajeAjusteHijosDAO porcDao = new PorcentajeAjusteHijosDAO();
		AjusteHijosDAO ajusteDao = new AjusteHijosDAO();
		PorcentajeAjusteHijos porc = new PorcentajeAjusteHijos();
		AjusteHijos ajuste = new AjusteHijos();
		
		ajusteDao.updateInstance(ajuste);
		
		porc.setValorNumerico(2.5F);
		porc.setFechaModificacion(LocalDateTime.now());
		ajuste.setValorActualPorcentajeCantHijos(porc);
		porc.setAjusteAsociado(ajuste);
		porcDao.updateInstance(porc);
		
		
	}
	
	public static void poblarPaises() {
		
		PaisDAO dao = new PaisDAO();
		
        Pais estadosUnidos = new Pais();
        estadosUnidos.setCodPais("01");
        estadosUnidos.setNombre("Estados Unidos");
        dao.updateInstance(estadosUnidos);

        Pais china = new Pais();
        china.setCodPais("02");
        china.setNombre("China");
        dao.updateInstance(china);

        Pais india = new Pais();
        india.setCodPais("03");
        india.setNombre("India");
        dao.updateInstance(india);

        Pais brasil = new Pais();
        brasil.setCodPais("04");
        brasil.setNombre("Brasil");
        dao.updateInstance(brasil);

        Pais rusia = new Pais();
        rusia.setCodPais("05");
        rusia.setNombre("Rusia");
        dao.updateInstance(rusia);

        Pais mexico = new Pais();
        mexico.setCodPais("06");
        mexico.setNombre("Mexico");
        dao.updateInstance(mexico);

        Pais indonesia = new Pais();
        indonesia.setCodPais("07");
        indonesia.setNombre("Indonesia");
        dao.updateInstance(indonesia);

        Pais pakistan = new Pais();
        pakistan.setCodPais("08");
        pakistan.setNombre("Pakistan");
        dao.updateInstance(pakistan);

        Pais bangladesh = new Pais();
        bangladesh.setCodPais("09");
        bangladesh.setNombre("Bangladesh");
        dao.updateInstance(bangladesh);

        Pais japon = new Pais();
        japon.setCodPais("10");
        japon.setNombre("Japon");
        dao.updateInstance(japon);

        Pais nigeria = new Pais();
        nigeria.setCodPais("11");
        nigeria.setNombre("Nigeria");
        dao.updateInstance(nigeria);

        Pais alemania = new Pais();
        alemania.setCodPais("12");
        alemania.setNombre("Alemania");
        dao.updateInstance(alemania);

        Pais francia = new Pais();
        francia.setCodPais("13");
        francia.setNombre("Francia");
        dao.updateInstance(francia);

        Pais reinoUnido = new Pais();
        reinoUnido.setCodPais("14");
        reinoUnido.setNombre("Reino Unido");
        dao.updateInstance(reinoUnido);

        Pais italia = new Pais();
        italia.setCodPais("15");
        italia.setNombre("Italia");
        dao.updateInstance(italia);

        Pais turquia = new Pais();
        turquia.setCodPais("16");
        turquia.setNombre("Turquia");
        dao.updateInstance(turquia);

        Pais sudafrica = new Pais();
        sudafrica.setCodPais("17");
        sudafrica.setNombre("Sudafrica");
        dao.updateInstance(sudafrica);

        Pais canada = new Pais();
        canada.setCodPais("18");
        canada.setNombre("Canada");
        dao.updateInstance(canada);

        Pais arabiaSaudita = new Pais();
        arabiaSaudita.setCodPais("19");
        arabiaSaudita.setNombre("Arabia Saudita");
        dao.updateInstance(arabiaSaudita);

        Pais coreaDelSur = new Pais();
        coreaDelSur.setCodPais("20");
        coreaDelSur.setNombre("Corea del Sur");
        dao.updateInstance(coreaDelSur);

        Pais argentina = new Pais();
        argentina.setCodPais("21");
        argentina.setNombre("Argentina");
        dao.updateInstance(argentina);

        Pais colombia = new Pais();
        colombia.setCodPais("22");
        colombia.setNombre("Colombia");
        dao.updateInstance(colombia);

        Pais espana = new Pais();
        espana.setCodPais("23");
        espana.setNombre("Espana");
        dao.updateInstance(espana);

        Pais australia = new Pais();
        australia.setCodPais("24");
        australia.setNombre("Australia");
        dao.updateInstance(australia);

        Pais egipto = new Pais();
        egipto.setCodPais("25");
        egipto.setNombre("Egipto");
        dao.updateInstance(egipto);
	}
	
	public static void poblarProvinciasArgentinas() throws ObjetoNoEncontradoException {
        ProvinciaDAO daoProv = new ProvinciaDAO();
        LocalidadDAO daoLocal = new LocalidadDAO();
        
        Pais argentina = GestorLocalizacion.getPaisByNombre("Argentina");
        
        Provincia buenosAires = new Provincia();
        buenosAires.setNombreProvincia("Buenos Aires");
        buenosAires.setPais(argentina);
        daoProv.updateInstance(buenosAires);
        Localidad caba = new Localidad();
        caba.setNombre("Ciudad Autonoma de Buenos Aires");
        caba.setProvincia(buenosAires);
        daoLocal.updateInstance(caba);

        Provincia catamarca = new Provincia();
        catamarca.setNombreProvincia("Catamarca");
        catamarca.setPais(argentina);
        daoProv.updateInstance(catamarca);
        Localidad sanFernando = new Localidad();
        sanFernando.setNombre("San Fernando del Valle de Catamarca");
        sanFernando.setProvincia(catamarca);
        daoLocal.updateInstance(sanFernando);

        Provincia chaco = new Provincia();
        chaco.setNombreProvincia("Chaco");
        chaco.setPais(argentina);
        daoProv.updateInstance(chaco);
        Localidad resistencia = new Localidad();
        resistencia.setNombre("Resistencia");
        resistencia.setProvincia(chaco);
        daoLocal.updateInstance(resistencia);

        Provincia chubut = new Provincia();
        chubut.setNombreProvincia("Chubut");
        chubut.setPais(argentina);
        daoProv.updateInstance(chubut);
        Localidad comodoro = new Localidad();
        comodoro.setNombre("Comodoro Rivadavia");
        comodoro.setProvincia(chubut);
        daoLocal.updateInstance(comodoro);

        Provincia cordoba = new Provincia();
        cordoba.setNombreProvincia("Cordoba");
        cordoba.setPais(argentina);
        daoProv.updateInstance(cordoba);
        Localidad cordobaCapital = new Localidad();
        cordobaCapital.setNombre("Corodoba");
        cordobaCapital.setProvincia(cordoba);
        daoLocal.updateInstance(cordobaCapital);

        Provincia corrientes = new Provincia();
        corrientes.setNombreProvincia("Corrientes");
        corrientes.setPais(argentina);
        daoProv.updateInstance(corrientes);
        Localidad pasoDeLosLibres = new Localidad();
        pasoDeLosLibres.setNombre("Paso De Los Libres");
        pasoDeLosLibres.setProvincia(corrientes);
        daoLocal.updateInstance(pasoDeLosLibres);

        Provincia entreRios = new Provincia();
        entreRios.setNombreProvincia("Entre Rios");
        entreRios.setPais(argentina);
        daoProv.updateInstance(entreRios);
        Localidad parana = new Localidad();
        parana.setNombre("Parana");
        parana.setProvincia(entreRios);
        daoLocal.updateInstance(parana);

        Provincia formosa = new Provincia();
        formosa.setNombreProvincia("Formosa");
        formosa.setPais(argentina);
        daoProv.updateInstance(formosa);
        Localidad formosaCapital = new Localidad();
        formosaCapital.setNombre("Formosa");
        formosaCapital.setProvincia(formosa);
        daoLocal.updateInstance(formosaCapital);

        Provincia jujuy = new Provincia();
        jujuy.setNombreProvincia("Jujuy");
        jujuy.setPais(argentina);
        daoProv.updateInstance(jujuy);
        Localidad ssDeJujuy = new Localidad();
        ssDeJujuy.setNombre("San Salvador de Jujuy");
        ssDeJujuy.setProvincia(jujuy);
        daoLocal.updateInstance(ssDeJujuy);

        Provincia laPampa = new Provincia();
        laPampa.setNombreProvincia("La Pampa");
        laPampa.setPais(argentina);
        daoProv.updateInstance(laPampa);
        Localidad santaRosa = new Localidad();
        santaRosa.setNombre("Santa Rosa de La Pampa");
        santaRosa.setProvincia(laPampa);
        daoLocal.updateInstance(santaRosa);

        Provincia laRioja = new Provincia();
        laRioja.setNombreProvincia("La Rioja");
        laRioja.setPais(argentina);
        daoProv.updateInstance(laRioja);
        Localidad laRiojaCapital = new Localidad();
        laRiojaCapital.setNombre("La Rioja");
        laRiojaCapital.setProvincia(laRioja);
        daoLocal.updateInstance(laRiojaCapital);

        Provincia mendoza = new Provincia();
        mendoza.setNombreProvincia("Mendoza");
        mendoza.setPais(argentina);
        daoProv.updateInstance(mendoza);
        Localidad sanRafael = new Localidad();
        sanRafael.setNombre("San Rafael");
        sanRafael.setProvincia(mendoza);
        daoLocal.updateInstance(sanRafael);

        Provincia misiones = new Provincia();
        misiones.setNombreProvincia("Misiones");
        misiones.setPais(argentina);
        daoProv.updateInstance(misiones);
        Localidad posadas = new Localidad();
        posadas.setNombre("Posadas");
        posadas.setProvincia(misiones);
        daoLocal.updateInstance(posadas);

        Provincia neuquen = new Provincia();
        neuquen.setNombreProvincia("Neuquen");
        neuquen.setPais(argentina);
        daoProv.updateInstance(neuquen);
        Localidad neuquenCapital = new Localidad();
        neuquenCapital.setNombre("Neuquen");
        neuquenCapital.setProvincia(neuquen);
        daoLocal.updateInstance(neuquenCapital);

        Provincia rioNegro = new Provincia();
        rioNegro.setNombreProvincia("Rio Negro");
        rioNegro.setPais(argentina);
        daoProv.updateInstance(rioNegro);
        Localidad bariloche = new Localidad();
        bariloche.setNombre("San Carlos de Bariloche");
        bariloche.setProvincia(rioNegro);
        daoLocal.updateInstance(bariloche);

        Provincia salta = new Provincia();
        salta.setNombreProvincia("Salta");
        salta.setPais(argentina);
        daoProv.updateInstance(salta);
        Localidad saltaCapital = new Localidad();
        saltaCapital.setNombre("Salta");
        saltaCapital.setProvincia(salta);
        daoLocal.updateInstance(saltaCapital);

        Provincia sanJuan = new Provincia();
        sanJuan.setNombreProvincia("San Juan");
        sanJuan.setPais(argentina);
        daoProv.updateInstance(sanJuan);
        Localidad sanJuanCapital = new Localidad();
        sanJuanCapital.setNombre("San Juan");
        sanJuanCapital.setProvincia(sanJuan);
        daoLocal.updateInstance(sanJuanCapital);

        Provincia sanLuis = new Provincia();
        sanLuis.setNombreProvincia("San Luis");
        sanLuis.setPais(argentina);
        daoProv.updateInstance(sanLuis);
        Localidad sanLuisCapital = new Localidad();
        sanLuisCapital.setNombre("San Luis");
        sanLuisCapital.setProvincia(sanLuis);
        daoLocal.updateInstance(sanLuisCapital);

        Provincia santaCruz = new Provincia();
        santaCruz.setNombreProvincia("Santa Cruz");
        santaCruz.setPais(argentina);
        daoProv.updateInstance(santaCruz);
        Localidad rioGallegos = new Localidad();
        rioGallegos.setNombre("Rio Gallegos");
        rioGallegos.setProvincia(santaCruz);
        daoLocal.updateInstance(rioGallegos);

        Provincia santaFe = new Provincia();
        santaFe.setNombreProvincia("Santa Fe");
        santaFe.setPais(argentina);
        daoProv.updateInstance(santaFe);
        Localidad santaFeCapital = new Localidad();
        santaFeCapital.setNombre("Santa Fe");
        santaFeCapital.setProvincia(santaFe);
        daoLocal.updateInstance(santaFeCapital);

        Provincia santiagoDelEstero = new Provincia();
        santiagoDelEstero.setNombreProvincia("Santiago del Estero");
        santiagoDelEstero.setPais(argentina);
        daoProv.updateInstance(santiagoDelEstero);
        Localidad sdeCaptial = new Localidad();
        sdeCaptial.setNombre("Santiago del Estero");
        sdeCaptial.setProvincia(santiagoDelEstero);
        daoLocal.updateInstance(sdeCaptial);

        Provincia tierraDelFuego = new Provincia();
        tierraDelFuego.setNombreProvincia("Tierra del Fuego");
        tierraDelFuego.setPais(argentina);
        daoProv.updateInstance(tierraDelFuego);
        Localidad usuahia = new Localidad();
        usuahia.setNombre("Usuahia");
        usuahia.setProvincia(tierraDelFuego);
        daoLocal.updateInstance(usuahia);

        Provincia tucuman = new Provincia();
        tucuman.setNombreProvincia("Tucuman");
        tucuman.setPais(argentina);
        daoProv.updateInstance(tucuman);
        Localidad tucumanCapital = new Localidad();
        tucumanCapital.setNombre("Tucuman");
        tucumanCapital.setProvincia(tucuman);
        daoLocal.updateInstance(tucumanCapital);
	}
	
	public static void poblarAutos() {
        MarcaDAO daoMarca = new MarcaDAO();
        ModeloDAO daoModelo = new ModeloDAO();
        AnioModeloDAO daoAnioModelo = new AnioModeloDAO();

        Marca toyota = new Marca();
        toyota.setNombre("Toyota");
        daoMarca.updateInstance(toyota);

        Marca ford = new Marca();
        ford.setNombre("Ford");
        daoMarca.updateInstance(ford);

        Marca chevrolet = new Marca();
        chevrolet.setNombre("Chevrolet");
        daoMarca.updateInstance(chevrolet);
        
     // Instanciar y guardar el primer modelo de auto para la marca Toyota
        Modelo corolla = new Modelo();
        corolla.setNombreModelo("Corolla");
        corolla.setDescripcion("Sedán compacto");
        corolla.setMarca(toyota);
        daoModelo.updateInstance(corolla);

        // Instanciar y guardar el segundo modelo de auto para la marca Toyota
        Modelo rav4 = new Modelo();
        rav4.setNombreModelo("RAV4");
        rav4.setDescripcion("SUV compacto");
        rav4.setMarca(toyota);
        daoModelo.updateInstance(rav4);
        
     // Instanciar y guardar el primer modelo de auto para la marca Ford
        Modelo focus = new Modelo();
        focus.setNombreModelo("Focus");
        focus.setDescripcion("Hatchback compacto");
        focus.setMarca(ford);
        daoModelo.updateInstance(focus);

        // Instanciar y guardar el segundo modelo de auto para la marca Ford
        Modelo mustang = new Modelo();
        mustang.setNombreModelo("Mustang");
        mustang.setDescripcion("Deportivo");
        mustang.setMarca(ford);
        daoModelo.updateInstance(mustang);
        
     // Instanciar y guardar el primer modelo de auto para la marca Chevrolet
        Modelo cruze = new Modelo();
        cruze.setNombreModelo("Cruze");
        cruze.setDescripcion("Sedán mediano");
        cruze.setMarca(chevrolet);
        daoModelo.updateInstance(cruze);

        // Instanciar y guardar el segundo modelo de auto para la marca Chevrolet
        Modelo traverse = new Modelo();
        traverse.setNombreModelo("Traverse");
        traverse.setDescripcion("SUV grande");
        traverse.setMarca(chevrolet);
        daoModelo.updateInstance(traverse);
        
        AnioModelo corolla2019 = new AnioModelo();
        corolla2019.setValorVehiculo(25000.0f);
        corolla2019.setAnio(2019);
        corolla2019.setTieneModelo(corolla);
        daoAnioModelo.updateInstance(corolla2019);

        AnioModelo corolla2020 = new AnioModelo();
        corolla2020.setValorVehiculo(26000.0f);
        corolla2020.setAnio(2020);
        corolla2020.setTieneModelo(corolla);
        daoAnioModelo.updateInstance(corolla2020);

        AnioModelo rav42019 = new AnioModelo();
        rav42019.setValorVehiculo(30000.0f);
        rav42019.setAnio(2019);
        rav42019.setTieneModelo(rav4);
        daoAnioModelo.updateInstance(rav42019);

        AnioModelo rav42020 = new AnioModelo();
        rav42020.setValorVehiculo(31000.0f);
        rav42020.setAnio(2020);
        rav42020.setTieneModelo(rav4);
        daoAnioModelo.updateInstance(rav42020);
        
        AnioModelo focus2019 = new AnioModelo();
        focus2019.setValorVehiculo(25000.0f);
        focus2019.setAnio(2019);
        focus2019.setTieneModelo(focus);
        daoAnioModelo.updateInstance(focus2019);

        AnioModelo focus2023 = new AnioModelo();
        focus2023.setValorVehiculo(26000.0f);
        focus2023.setAnio(2023);
        focus2023.setTieneModelo(focus);
        daoAnioModelo.updateInstance(focus2023);

        AnioModelo mustang2005 = new AnioModelo();
        mustang2005.setValorVehiculo(300000.0f);
        mustang2005.setAnio(2005);
        mustang2005.setTieneModelo(mustang);
        daoAnioModelo.updateInstance(mustang2005);

        AnioModelo mustang2010 = new AnioModelo();
        mustang2010.setValorVehiculo(310000.0f);
        mustang2010.setAnio(2010);
        mustang2010.setTieneModelo(mustang);
        daoAnioModelo.updateInstance(mustang2010);
        
        AnioModelo cruze2015 = new AnioModelo();
        cruze2015.setValorVehiculo(25000.0f);
        cruze2015.setAnio(2015);
        cruze2015.setTieneModelo(cruze);
        daoAnioModelo.updateInstance(cruze2015);

        AnioModelo cruze2019 = new AnioModelo();
        cruze2019.setValorVehiculo(26000.0f);
        cruze2019.setAnio(2019);
        cruze2019.setTieneModelo(cruze);
        daoAnioModelo.updateInstance(cruze2019);

        AnioModelo traverse2022 = new AnioModelo();
        traverse2022.setValorVehiculo(30000.0f);
        traverse2022.setAnio(2022);
        traverse2022.setTieneModelo(traverse);
        daoAnioModelo.updateInstance(traverse2022);

        AnioModelo traverse2017 = new AnioModelo();
        traverse2017.setValorVehiculo(34000.0f);
        traverse2017.setAnio(2017);
        traverse2017.setTieneModelo(traverse);
        daoAnioModelo.updateInstance(traverse2017);
        
	}
	
	public static void poblarKms() {
		RangoKMRealizadosDAO dao = new RangoKMRealizadosDAO();
		
		RangoKMRealizados rango = new RangoKMRealizados();
		rango.setConcepto("Hasta 10.000 Km");
		dao.updateInstance(rango);
		
		rango = new RangoKMRealizados();
		rango.setConcepto("Hasta 20.000 Km");
		dao.updateInstance(rango);
		
		rango = new RangoKMRealizados();
		rango.setConcepto("Hasta 30.000 Km");
		dao.updateInstance(rango);
		
		rango = new RangoKMRealizados();
		rango.setConcepto("Hasta 40.000 Km");
		dao.updateInstance(rango);
		
		rango = new RangoKMRealizados();
		rango.setConcepto("Mas de 40.000 Km");
		dao.updateInstance(rango);
	}
	
	public static void poblarSiniestros() {
		RangoCantSiniestrosDAO dao = new RangoCantSiniestrosDAO();
		
		RangoCantSiniestros rango = new RangoCantSiniestros();
		rango.setConcepto("Ninguno");
		rango.setDesdeCant(0);
		dao.updateInstance(rango);
		
		rango = new RangoCantSiniestros();
		rango.setConcepto("1");
		rango.setDesdeCant(1);
		dao.updateInstance(rango);
		
		rango = new RangoCantSiniestros();
		rango.setConcepto("2");
		rango.setDesdeCant(2);
		dao.updateInstance(rango);
		
		rango = new RangoCantSiniestros();
		rango.setConcepto("Más de 2");
		rango.setDesdeCant(3);
		dao.updateInstance(rango);
	}
}
