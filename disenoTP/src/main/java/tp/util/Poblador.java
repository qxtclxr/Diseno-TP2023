package tp.util;

import tp.entidad.*;
import tp.logica.*;
import tp.dao.*;
import tp.exception.ObjetoNoEncontradoException;
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
		} catch (ObjetoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			dao.saveInstance(porc);
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
			dao.saveInstance(porc);
		}
	}
	
	public static void poblarMedidasDeSeguridad() {
		MedidaDeSeguridadDAO dao = new MedidaDeSeguridadDAO();
		
		MedidaDeSeguridad medida = new MedidaDeSeguridad();
		medida.setPregunta("Se guarda en Garage?");
		dao.saveInstance(medida);
		
		medida = new MedidaDeSeguridad();
		medida.setPregunta("Tiene alarma?");
		dao.saveInstance(medida);
		
		medida = new MedidaDeSeguridad();
		medida.setPregunta("Posee dispositivo de rastreo vehicular?");
		dao.saveInstance(medida);
		
		medida = new MedidaDeSeguridad();
		medida.setPregunta("Posee tuercas antirobo en las cuatro cuerdas?");
		dao.saveInstance(medida);
		
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
			dao.saveInstance(porc);
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
			dao.saveInstance(porc);
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
			dao.saveInstance(porc);
		}
	}
	
	public static void poblarCobertura() {
		CoberturaDAO dao = new CoberturaDAO();
		
		Cobertura cobertura = new Cobertura();
		cobertura.setDescripcion("");
		cobertura.setTipoCobertura("Responsabilidad Civil");
		dao.saveInstance(cobertura);
		
		cobertura = new Cobertura();
		cobertura.setDescripcion("");
		cobertura.setTipoCobertura("Resp. Civil + Robo o Incendio Total");
		dao.saveInstance(cobertura);
		
		cobertura = new Cobertura();
		cobertura.setDescripcion("");
		cobertura.setTipoCobertura("Todo Total");
		dao.saveInstance(cobertura);
		
		cobertura = new Cobertura();
		cobertura.setDescripcion("");
		cobertura.setTipoCobertura("Terceros Completos");
		dao.saveInstance(cobertura);
		
		cobertura = new Cobertura();
		cobertura.setDescripcion("");
		cobertura.setTipoCobertura("Todo Riesgo con Franquicia");
		dao.saveInstance(cobertura);
		
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
			dao.saveInstance(porc);
		}
	}
	
	public static void poblarDescuentoPorUnidad() {
		PorcentajeDescPorUnidadDAO porcDao = new PorcentajeDescPorUnidadDAO();
		DescuentoPorUnidadDAO descDao = new DescuentoPorUnidadDAO();
		PorcentajeDescPorUnidad porc = new PorcentajeDescPorUnidad();
		DescuentoPorUnidad desc = new DescuentoPorUnidad();
		
		descDao.saveInstance(desc);
		
		porc.setValorNumerico(2.5F);
		porc.setFechaModificacion(LocalDateTime.now());
		desc.setValorActualDescPorUnidad(porc);
		porc.setDescAsociado(desc);
		porcDao.saveInstance(porc);
	}
	
	public static void poblarAjusteHijos() {
		PorcentajeAjusteHijosDAO porcDao = new PorcentajeAjusteHijosDAO();
		AjusteHijosDAO ajusteDao = new AjusteHijosDAO();
		PorcentajeAjusteHijos porc = new PorcentajeAjusteHijos();
		AjusteHijos ajuste = new AjusteHijos();
		
		ajusteDao.saveInstance(ajuste);
		
		porc.setValorNumerico(2.5F);
		porc.setFechaModificacion(LocalDateTime.now());
		ajuste.setValorActualPorcentajeCantHijos(porc);
		porc.setAjusteAsociado(ajuste);
		porcDao.saveInstance(porc);
		
		
	}
	
	public static void poblarPaises() {
		
		PaisDAO dao = new PaisDAO();
		
        Pais estadosUnidos = new Pais();
        estadosUnidos.setCodPais("01");
        estadosUnidos.setNombre("Estados Unidos");
        dao.saveInstance(estadosUnidos);

        Pais china = new Pais();
        china.setCodPais("02");
        china.setNombre("China");
        dao.saveInstance(china);

        Pais india = new Pais();
        india.setCodPais("03");
        india.setNombre("India");
        dao.saveInstance(india);

        Pais brasil = new Pais();
        brasil.setCodPais("04");
        brasil.setNombre("Brasil");
        dao.saveInstance(brasil);

        Pais rusia = new Pais();
        rusia.setCodPais("05");
        rusia.setNombre("Rusia");
        dao.saveInstance(rusia);

        Pais mexico = new Pais();
        mexico.setCodPais("06");
        mexico.setNombre("Mexico");
        dao.saveInstance(mexico);

        Pais indonesia = new Pais();
        indonesia.setCodPais("07");
        indonesia.setNombre("Indonesia");
        dao.saveInstance(indonesia);

        Pais pakistan = new Pais();
        pakistan.setCodPais("08");
        pakistan.setNombre("Pakistan");
        dao.saveInstance(pakistan);

        Pais bangladesh = new Pais();
        bangladesh.setCodPais("09");
        bangladesh.setNombre("Bangladesh");
        dao.saveInstance(bangladesh);

        Pais japon = new Pais();
        japon.setCodPais("10");
        japon.setNombre("Japon");
        dao.saveInstance(japon);

        Pais nigeria = new Pais();
        nigeria.setCodPais("11");
        nigeria.setNombre("Nigeria");
        dao.saveInstance(nigeria);

        Pais alemania = new Pais();
        alemania.setCodPais("12");
        alemania.setNombre("Alemania");
        dao.saveInstance(alemania);

        Pais francia = new Pais();
        francia.setCodPais("13");
        francia.setNombre("Francia");
        dao.saveInstance(francia);

        Pais reinoUnido = new Pais();
        reinoUnido.setCodPais("14");
        reinoUnido.setNombre("Reino Unido");
        dao.saveInstance(reinoUnido);

        Pais italia = new Pais();
        italia.setCodPais("15");
        italia.setNombre("Italia");
        dao.saveInstance(italia);

        Pais turquia = new Pais();
        turquia.setCodPais("16");
        turquia.setNombre("Turquia");
        dao.saveInstance(turquia);

        Pais sudafrica = new Pais();
        sudafrica.setCodPais("17");
        sudafrica.setNombre("Sudafrica");
        dao.saveInstance(sudafrica);

        Pais canada = new Pais();
        canada.setCodPais("18");
        canada.setNombre("Canada");
        dao.saveInstance(canada);

        Pais arabiaSaudita = new Pais();
        arabiaSaudita.setCodPais("19");
        arabiaSaudita.setNombre("Arabia Saudita");
        dao.saveInstance(arabiaSaudita);

        Pais coreaDelSur = new Pais();
        coreaDelSur.setCodPais("20");
        coreaDelSur.setNombre("Corea del Sur");
        dao.saveInstance(coreaDelSur);

        Pais argentina = new Pais();
        argentina.setCodPais("21");
        argentina.setNombre("Argentina");
        dao.saveInstance(argentina);

        Pais colombia = new Pais();
        colombia.setCodPais("22");
        colombia.setNombre("Colombia");
        dao.saveInstance(colombia);

        Pais espana = new Pais();
        espana.setCodPais("23");
        espana.setNombre("Espana");
        dao.saveInstance(espana);

        Pais australia = new Pais();
        australia.setCodPais("24");
        australia.setNombre("Australia");
        dao.saveInstance(australia);

        Pais egipto = new Pais();
        egipto.setCodPais("25");
        egipto.setNombre("Egipto");
        dao.saveInstance(egipto);
	}
	
	public static void poblarProvinciasArgentinas() throws ObjetoNoEncontradoException {
        ProvinciaDAO daoProv = new ProvinciaDAO();
        LocalidadDAO daoLocal = new LocalidadDAO();
        
        Pais argentina = GestorLocalizacion.getPaisByNombre("Argentina");
        
        Provincia buenosAires = new Provincia();
        buenosAires.setNombreProvincia("Buenos Aires");
        buenosAires.setPais(argentina);
        daoProv.saveInstance(buenosAires);
        Localidad caba = new Localidad();
        caba.setNombre("Ciudad Autonoma de Buenos Aires");
        caba.setProvincia(buenosAires);
        daoLocal.saveInstance(caba);

        Provincia catamarca = new Provincia();
        catamarca.setNombreProvincia("Catamarca");
        catamarca.setPais(argentina);
        daoProv.saveInstance(catamarca);
        Localidad sanFernando = new Localidad();
        sanFernando.setNombre("San Fernando del Valle de Catamarca");
        sanFernando.setProvincia(catamarca);
        daoLocal.saveInstance(sanFernando);

        Provincia chaco = new Provincia();
        chaco.setNombreProvincia("Chaco");
        chaco.setPais(argentina);
        daoProv.saveInstance(chaco);
        Localidad resistencia = new Localidad();
        resistencia.setNombre("Resistencia");
        resistencia.setProvincia(chaco);
        daoLocal.saveInstance(resistencia);

        Provincia chubut = new Provincia();
        chubut.setNombreProvincia("Chubut");
        chubut.setPais(argentina);
        daoProv.saveInstance(chubut);
        Localidad comodoro = new Localidad();
        comodoro.setNombre("Comodoro Rivadavia");
        comodoro.setProvincia(chubut);
        daoLocal.saveInstance(comodoro);

        Provincia cordoba = new Provincia();
        cordoba.setNombreProvincia("Cordoba");
        cordoba.setPais(argentina);
        daoProv.saveInstance(cordoba);
        Localidad cordobaCapital = new Localidad();
        cordobaCapital.setNombre("Corodoba");
        cordobaCapital.setProvincia(cordoba);
        daoLocal.saveInstance(cordobaCapital);

        Provincia corrientes = new Provincia();
        corrientes.setNombreProvincia("Corrientes");
        corrientes.setPais(argentina);
        daoProv.saveInstance(corrientes);
        Localidad pasoDeLosLibres = new Localidad();
        pasoDeLosLibres.setNombre("Paso De Los Libres");
        pasoDeLosLibres.setProvincia(corrientes);
        daoLocal.saveInstance(pasoDeLosLibres);

        Provincia entreRios = new Provincia();
        entreRios.setNombreProvincia("Entre Rios");
        entreRios.setPais(argentina);
        daoProv.saveInstance(entreRios);
        Localidad parana = new Localidad();
        parana.setNombre("Parana");
        parana.setProvincia(entreRios);
        daoLocal.saveInstance(parana);

        Provincia formosa = new Provincia();
        formosa.setNombreProvincia("Formosa");
        formosa.setPais(argentina);
        daoProv.saveInstance(formosa);
        Localidad formosaCapital = new Localidad();
        formosaCapital.setNombre("Formosa");
        formosaCapital.setProvincia(formosa);
        daoLocal.saveInstance(formosaCapital);

        Provincia jujuy = new Provincia();
        jujuy.setNombreProvincia("Jujuy");
        jujuy.setPais(argentina);
        daoProv.saveInstance(jujuy);
        Localidad ssDeJujuy = new Localidad();
        ssDeJujuy.setNombre("San Salvador de Jujuy");
        ssDeJujuy.setProvincia(jujuy);
        daoLocal.saveInstance(ssDeJujuy);

        Provincia laPampa = new Provincia();
        laPampa.setNombreProvincia("La Pampa");
        laPampa.setPais(argentina);
        daoProv.saveInstance(laPampa);
        Localidad santaRosa = new Localidad();
        santaRosa.setNombre("Santa Rosa de La Pampa");
        santaRosa.setProvincia(laPampa);
        daoLocal.saveInstance(santaRosa);

        Provincia laRioja = new Provincia();
        laRioja.setNombreProvincia("La Rioja");
        laRioja.setPais(argentina);
        daoProv.saveInstance(laRioja);
        Localidad laRiojaCapital = new Localidad();
        laRiojaCapital.setNombre("La Rioja");
        laRiojaCapital.setProvincia(laRioja);
        daoLocal.saveInstance(laRiojaCapital);

        Provincia mendoza = new Provincia();
        mendoza.setNombreProvincia("Mendoza");
        mendoza.setPais(argentina);
        daoProv.saveInstance(mendoza);
        Localidad sanRafael = new Localidad();
        sanRafael.setNombre("San Rafael");
        sanRafael.setProvincia(mendoza);
        daoLocal.saveInstance(sanRafael);

        Provincia misiones = new Provincia();
        misiones.setNombreProvincia("Misiones");
        misiones.setPais(argentina);
        daoProv.saveInstance(misiones);
        Localidad posadas = new Localidad();
        posadas.setNombre("Posadas");
        posadas.setProvincia(misiones);
        daoLocal.saveInstance(posadas);

        Provincia neuquen = new Provincia();
        neuquen.setNombreProvincia("Neuquen");
        neuquen.setPais(argentina);
        daoProv.saveInstance(neuquen);
        Localidad neuquenCapital = new Localidad();
        neuquenCapital.setNombre("Neuquen");
        neuquenCapital.setProvincia(neuquen);
        daoLocal.saveInstance(neuquenCapital);

        Provincia rioNegro = new Provincia();
        rioNegro.setNombreProvincia("Rio Negro");
        rioNegro.setPais(argentina);
        daoProv.saveInstance(rioNegro);
        Localidad bariloche = new Localidad();
        bariloche.setNombre("San Carlos de Bariloche");
        bariloche.setProvincia(rioNegro);
        daoLocal.saveInstance(bariloche);

        Provincia salta = new Provincia();
        salta.setNombreProvincia("Salta");
        salta.setPais(argentina);
        daoProv.saveInstance(salta);
        Localidad saltaCapital = new Localidad();
        saltaCapital.setNombre("Salta");
        saltaCapital.setProvincia(salta);
        daoLocal.saveInstance(saltaCapital);

        Provincia sanJuan = new Provincia();
        sanJuan.setNombreProvincia("San Juan");
        sanJuan.setPais(argentina);
        daoProv.saveInstance(sanJuan);
        Localidad sanJuanCapital = new Localidad();
        sanJuanCapital.setNombre("San Juan");
        sanJuanCapital.setProvincia(sanJuan);
        daoLocal.saveInstance(sanJuanCapital);

        Provincia sanLuis = new Provincia();
        sanLuis.setNombreProvincia("San Luis");
        sanLuis.setPais(argentina);
        daoProv.saveInstance(sanLuis);
        Localidad sanLuisCapital = new Localidad();
        sanLuisCapital.setNombre("San Luis");
        sanLuisCapital.setProvincia(sanLuis);
        daoLocal.saveInstance(sanLuisCapital);

        Provincia santaCruz = new Provincia();
        santaCruz.setNombreProvincia("Santa Cruz");
        santaCruz.setPais(argentina);
        daoProv.saveInstance(santaCruz);
        Localidad rioGallegos = new Localidad();
        rioGallegos.setNombre("Rio Gallegos");
        rioGallegos.setProvincia(santaCruz);
        daoLocal.saveInstance(rioGallegos);

        Provincia santaFe = new Provincia();
        santaFe.setNombreProvincia("Santa Fe");
        santaFe.setPais(argentina);
        daoProv.saveInstance(santaFe);
        Localidad santaFeCapital = new Localidad();
        santaFeCapital.setNombre("Santa Fe");
        santaFeCapital.setProvincia(santaFe);
        daoLocal.saveInstance(santaFeCapital);

        Provincia santiagoDelEstero = new Provincia();
        santiagoDelEstero.setNombreProvincia("Santiago del Estero");
        santiagoDelEstero.setPais(argentina);
        daoProv.saveInstance(santiagoDelEstero);
        Localidad sdeCaptial = new Localidad();
        sdeCaptial.setNombre("Santiago del Estero");
        sdeCaptial.setProvincia(santiagoDelEstero);
        daoLocal.saveInstance(sdeCaptial);

        Provincia tierraDelFuego = new Provincia();
        tierraDelFuego.setNombreProvincia("Tierra del Fuego");
        tierraDelFuego.setPais(argentina);
        daoProv.saveInstance(tierraDelFuego);
        Localidad usuahia = new Localidad();
        usuahia.setNombre("Usuahia");
        usuahia.setProvincia(tierraDelFuego);
        daoLocal.saveInstance(usuahia);

        Provincia tucuman = new Provincia();
        tucuman.setNombreProvincia("Tucuman");
        tucuman.setPais(argentina);
        daoProv.saveInstance(tucuman);
        Localidad tucumanCapital = new Localidad();
        tucumanCapital.setNombre("Tucuman");
        tucumanCapital.setProvincia(tucuman);
        daoLocal.saveInstance(tucumanCapital);
	}
	
	public static void poblarAutos() {
        MarcaDAO daoMarca = new MarcaDAO();
        ModeloDAO daoModelo = new ModeloDAO();
        AnioModeloDAO daoAnioModelo = new AnioModeloDAO();

        Marca toyota = new Marca();
        toyota.setNombre("Toyota");
        daoMarca.saveInstance(toyota);

        Marca ford = new Marca();
        ford.setNombre("Ford");
        daoMarca.saveInstance(ford);

        Marca chevrolet = new Marca();
        chevrolet.setNombre("Chevrolet");
        daoMarca.saveInstance(chevrolet);
        
     // Instanciar y guardar el primer modelo de auto para la marca Toyota
        Modelo corolla = new Modelo();
        corolla.setNombreModelo("Corolla");
        corolla.setDescripcion("Sedán compacto");
        corolla.setMarca(toyota);
        daoModelo.saveInstance(corolla);

        // Instanciar y guardar el segundo modelo de auto para la marca Toyota
        Modelo rav4 = new Modelo();
        rav4.setNombreModelo("RAV4");
        rav4.setDescripcion("SUV compacto");
        rav4.setMarca(toyota);
        daoModelo.saveInstance(rav4);
        
     // Instanciar y guardar el primer modelo de auto para la marca Ford
        Modelo focus = new Modelo();
        focus.setNombreModelo("Focus");
        focus.setDescripcion("Hatchback compacto");
        focus.setMarca(ford);
        daoModelo.saveInstance(focus);

        // Instanciar y guardar el segundo modelo de auto para la marca Ford
        Modelo mustang = new Modelo();
        mustang.setNombreModelo("Mustang");
        mustang.setDescripcion("Deportivo");
        mustang.setMarca(ford);
        daoModelo.saveInstance(mustang);
        
     // Instanciar y guardar el primer modelo de auto para la marca Chevrolet
        Modelo cruze = new Modelo();
        cruze.setNombreModelo("Cruze");
        cruze.setDescripcion("Sedán mediano");
        cruze.setMarca(chevrolet);
        daoModelo.saveInstance(cruze);

        // Instanciar y guardar el segundo modelo de auto para la marca Chevrolet
        Modelo traverse = new Modelo();
        traverse.setNombreModelo("Traverse");
        traverse.setDescripcion("SUV grande");
        traverse.setMarca(chevrolet);
        daoModelo.saveInstance(traverse);
        
        AnioModelo corolla2019 = new AnioModelo();
        corolla2019.setValorVehiculo(25000.0f);
        corolla2019.setAnio(2019);
        corolla2019.setTieneModelo(corolla);
        daoAnioModelo.saveInstance(corolla2019);

        AnioModelo corolla2020 = new AnioModelo();
        corolla2020.setValorVehiculo(26000.0f);
        corolla2020.setAnio(2020);
        corolla2020.setTieneModelo(corolla);
        daoAnioModelo.saveInstance(corolla2020);

        AnioModelo rav42019 = new AnioModelo();
        rav42019.setValorVehiculo(30000.0f);
        rav42019.setAnio(2019);
        rav42019.setTieneModelo(rav4);
        daoAnioModelo.saveInstance(rav42019);

        AnioModelo rav42020 = new AnioModelo();
        rav42020.setValorVehiculo(31000.0f);
        rav42020.setAnio(2020);
        rav42020.setTieneModelo(rav4);
        daoAnioModelo.saveInstance(rav42020);
        
        AnioModelo focus2019 = new AnioModelo();
        focus2019.setValorVehiculo(25000.0f);
        focus2019.setAnio(2019);
        focus2019.setTieneModelo(focus);
        daoAnioModelo.saveInstance(focus2019);

        AnioModelo focus2023 = new AnioModelo();
        focus2023.setValorVehiculo(26000.0f);
        focus2023.setAnio(2023);
        focus2023.setTieneModelo(focus);
        daoAnioModelo.saveInstance(focus2023);

        AnioModelo mustang2005 = new AnioModelo();
        mustang2005.setValorVehiculo(300000.0f);
        mustang2005.setAnio(2005);
        mustang2005.setTieneModelo(mustang);
        daoAnioModelo.saveInstance(mustang2005);

        AnioModelo mustang2010 = new AnioModelo();
        mustang2010.setValorVehiculo(310000.0f);
        mustang2010.setAnio(2010);
        mustang2010.setTieneModelo(mustang);
        daoAnioModelo.saveInstance(mustang2010);
        
        AnioModelo cruze2015 = new AnioModelo();
        cruze2015.setValorVehiculo(25000.0f);
        cruze2015.setAnio(2015);
        cruze2015.setTieneModelo(cruze);
        daoAnioModelo.saveInstance(cruze2015);

        AnioModelo cruze2019 = new AnioModelo();
        cruze2019.setValorVehiculo(26000.0f);
        cruze2019.setAnio(2019);
        cruze2019.setTieneModelo(cruze);
        daoAnioModelo.saveInstance(cruze2019);

        AnioModelo traverse2022 = new AnioModelo();
        traverse2022.setValorVehiculo(30000.0f);
        traverse2022.setAnio(2022);
        traverse2022.setTieneModelo(traverse);
        daoAnioModelo.saveInstance(traverse2022);

        AnioModelo traverse2017 = new AnioModelo();
        traverse2017.setValorVehiculo(34000.0f);
        traverse2017.setAnio(2017);
        traverse2017.setTieneModelo(traverse);
        daoAnioModelo.saveInstance(traverse2017);
        
	}
	
	public static void poblarKms() {
		RangoKMRealizadosDAO dao = new RangoKMRealizadosDAO();
		
		RangoKMRealizados rango = new RangoKMRealizados();
		rango.setConcepto("Hasta 10.000 Km");
		dao.saveInstance(rango);
		
		rango = new RangoKMRealizados();
		rango.setConcepto("Hasta 20.000 Km");
		dao.saveInstance(rango);
		
		rango = new RangoKMRealizados();
		rango.setConcepto("Hasta 30.000 Km");
		dao.saveInstance(rango);
		
		rango = new RangoKMRealizados();
		rango.setConcepto("Hasta 40.000 Km");
		dao.saveInstance(rango);
		
		rango = new RangoKMRealizados();
		rango.setConcepto("Mas de 40.000 Km");
		dao.saveInstance(rango);
	}
	
	public static void poblarSiniestros() {
		RangoCantSiniestrosDAO dao = new RangoCantSiniestrosDAO();
		
		RangoCantSiniestros rango = new RangoCantSiniestros();
		rango.setConcepto("Ninguno");
		rango.setDesdeCant(0);
		dao.saveInstance(rango);
		
		rango = new RangoCantSiniestros();
		rango.setConcepto("1");
		rango.setDesdeCant(1);
		dao.saveInstance(rango);
		
		rango = new RangoCantSiniestros();
		rango.setConcepto("2");
		rango.setDesdeCant(2);
		dao.saveInstance(rango);
		
		rango = new RangoCantSiniestros();
		rango.setConcepto("Más de 2");
		rango.setDesdeCant(3);
		dao.saveInstance(rango);
	}
}
