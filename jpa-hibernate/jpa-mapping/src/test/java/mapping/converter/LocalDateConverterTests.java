package mapping.converter;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import mapping.converter.LocalDateConverter;

public class LocalDateConverterTests {

	private LocalDateConverter converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new LocalDateConverter();
	}

	@Test
	public void convertToDatabaseColumn() throws Exception {
		assertEquals(
			java.sql.Date.valueOf("2000-03-15"),
			converter.convertToDatabaseColumn(LocalDate.of(2000, 3, 15)));
	}
	
	@Test
	public void convertToEntityAttribute() throws Exception {
		assertEquals(
			LocalDate.of(2000, 3, 15),
			converter.convertToEntityAttribute(
					java.sql.Date.valueOf("2000-03-15")));
	}

}
