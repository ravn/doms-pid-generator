/*
 * $Id$
 * $Revision$
 * $Date$
 * $Author$
 *
 * The DOMS project.
 * Copyright (C) 2007-2010  The State and University Library
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package dk.statsbiblioteket.doms.pidgenerator;

import dk.statsbiblioteket.util.qa.QAInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.UUID;


/**
 * Provides Persistent IDentifier generation capabilities.
 * PIDs will be UUIDs prefixed by 'uuid:' and returned as strings.
 * For UUID specific information {@link java.util.UUID}.
 */
@QAInfo(author = "eab", reviewers = "kfc", level = QAInfo.Level.NORMAL,
        state = QAInfo.State.QA_OK)   
@WebService(endpointInterface = "dk.statsbiblioteket.doms.pidgenerator.PidGeneratorSoapWebservice")
public class PidGeneratorSoapWebserviceImpl implements
        PidGeneratorSoapWebservice {
    /**
     * The log for this class.
     */
    Log log = LogFactory.getLog(getClass());
    
    /**
     * Generates a Persistent IDentifier, this consists of a UUID prefixed
     * by the string 'uuid:'.
     *
     * This method provides the fault barrier. It provides the barrier for Pid
     * generation and pushes errors up to the webservice framework.
     * 
     * @return Persistent IDentifier of type UUID as string
     * @throws CommunicationException when pid generation fails.
     */
    @WebMethod
    public String generatePid() throws CommunicationException {
        log.trace("Enter generatePid()");
        try {
            return "uuid:" + UUID.randomUUID().toString();
        } catch (Exception e) {
            String message = "generatePid() failed with error " + e;
            log.error(message, e);
            throw new CommunicationException(message, message, e);
        }
    }

    /**
     * Generates a Persistent IDentifier, this consists of a UUID prefixed
     * by the string 'uuid:'.
     * Parameter input is ignored.
     *
     * @param infix string used to generate the Persistent IDentifier
     * @return Persistent IDentifier of type UUID as string
     * @throws CommunicationException when pid generation fails.
     */
    @WebMethod
    public String generatePidWithInfix(
            @WebParam(name = "pidGeneratorInfixRequest",
                    targetNamespace = "http://pidgenerator.doms.statsbiblioteket.dk/",
                    partName = "infix") String infix) throws CommunicationException {
        log.trace("Enter generatePid('" + infix + "')");
        return generatePid();
    }

}
