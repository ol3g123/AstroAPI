package cz.kibo.api.astrology.builder;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import cz.kibo.api.astrology.domain.Coordinates;
import cz.kibo.api.astrology.domain.Planet;

public class PlanetBuilderTest {
	
	final double LONGITUDE = 16.0542676;
	final double LATITUDE = 48.8559107;
	final double GEOALT = 286;

	@Test
	public void allPlanetTest() {	
		System.out.println("PlanetBuilderTest:allPlanetTest");
		LocalDateTime event = LocalDateTime.of( 2018, 3, 20, 16, 20);
		Planet ephemeris = new PlanetBuilder( event )
							.planets()
							.build();		
		assertEquals(13, ephemeris.getPlanets().size());		
	}
	
	@Test
	public void planetTest() {		
		System.out.println("PlanetBuilderTest:planetTest");
		LocalDateTime event = LocalDateTime.of( 2018, 3, 20, 16, 20);
		Planet ephemeris = new PlanetBuilder( event )
							.planet("Sun, Moon")
							.build();		
		assertEquals(2, ephemeris.getPlanets().size());		
	}
	
	@Test
	public void topoTest() {
		System.out.println("PlanetBuilderTest:topoTest");
		LocalDateTime event = LocalDateTime.of( 2018, 3, 20, 16, 20);
				
		Planet ephemeris = new PlanetBuilder( event )
							.planet("Sun, Jupiter, Chiron")
							.topo(LONGITUDE, LATITUDE, GEOALT)
							.build();		
		assertEquals(3, ephemeris.getPlanets().size());				
		assertEquals(0, ephemeris.getPlanets().get("Sun").get(0).intValue()); 
	}
	
	@Test
	public void zidiacTest() {		
			System.out.println("PlanetBuilderTest:zidiacTest");
		LocalDateTime event = LocalDateTime.of( 2018, 4, 18, 4, 00);
				
		Planet ephemeris = new PlanetBuilder( event )
							.planet("Sun, Jupiter, Chiron, NNode, Lilith")
							.zodiac("Sassanian")
							.build();		
			
		assertEquals(5, ephemeris.getPlanets().size());
		assertEquals(7, ephemeris.getPlanets().get("Sun").get(0).intValue());
		assertEquals(210, ephemeris.getPlanets().get("Jupiter").get(0).intValue());
	}
}
