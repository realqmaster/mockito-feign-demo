package it.my.test.mockito;

import java.util.Map;
import java.util.Random;

import uk.co.jemos.podam.api.AttributeMetadata;
import uk.co.jemos.podam.api.DataProviderStrategy;
import uk.co.jemos.podam.typeManufacturers.TypeManufacturer;

public class NumericStringManufacturer implements TypeManufacturer<String> {

	@SuppressWarnings("rawtypes")
	@Override
	public String getType(DataProviderStrategy strategy, AttributeMetadata attributeMetadata,
			Map genericTypesArgumentsMap) {
		Random r = new Random();
		return String.valueOf(r.nextInt(2));
	}

}
