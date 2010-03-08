package dk.statsbiblioteket.doms.pidgenerator;

import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.WebMethod;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: esben
 * Date: 08-Mar-2010
 * Time: 12:21:29
 * To change this template use File | Settings | File Templates.
 */

@WebService(endpointInterface = "dk.statsbiblioteket.doms.pidgenerator.PidGeneratorSoapWebservice")
public class PidGeneratorSoapWebserviceImpl implements PidGeneratorSoapWebservice {

    /**
     *
     * @return Persistent IDentifier of type UUID as string
     * @throws CommunicationException
     */
    @WebMethod
    public String generatePid() throws CommunicationException {
        return "uuid:"+UUID.randomUUID().toString();
    }

    /**
     *
     * @param infix string used to generate the Persistent IDentifier
     * @return Persistent IDentifier of type UUID as string
     * @throws CommunicationException
     */
    public String generatePidWithInfix(@WebParam(name = "pidGeneratorInfixRequest", targetNamespace = "http://pidgenerator.doms.statsbiblioteket.dk/", partName = "infix") String infix) throws CommunicationException {
        return generatePid();
    }

}
