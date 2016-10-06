package gov.weather;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.*;

/**
 * Created by toddkinsman on 10/6/16.
 */
public class NdfdXMLBindingStubTest {

    @Test
    public void latLonListZipCode() throws Exception {
        // Get response from NOAA/NDFD API

        // This creates the binding and locates the the port the api is located on so we can access the correct endpoint
        NdfdXMLBindingStub binding = (NdfdXMLBindingStub) new NdfdXMLLocator().getndfdXMLPort();
        String zipResp = binding.latLonListZipCode("53718");

        // create JAXB context and initializing Marshaller
        JAXBContext jaxbContext = JAXBContext.newInstance(DwmlType.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        DwmlType dwml = (DwmlType) jaxbUnmarshaller.unmarshal(new StringReader(zipResp));

        assertEquals("Incorrect latlong were returned", "43.0798,-89.3875", dwml.getLatLonList());

    }

}