package starbucks.starbucksteam;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

/**
 * util class for filtering the response from Controller layer.
 */
public class FilterUtil {

    /**
     * /**
     * This function will filter out in-depth details for payments.
     *
     * @param payments The payments object to be filtered.
     * @return Filtered object.
     */
    public static Object applyPaymentFilters(Object payments) {
        SimpleBeanPropertyFilter paymentFilter = SimpleBeanPropertyFilter.serializeAllExcept("order");
        FilterProvider filters = new SimpleFilterProvider().addFilter("paymentFilter", paymentFilter);
        MappingJacksonValue mapping = new MappingJacksonValue(payments);
        mapping.setFilters(filters);
        return mapping;
    }
}
