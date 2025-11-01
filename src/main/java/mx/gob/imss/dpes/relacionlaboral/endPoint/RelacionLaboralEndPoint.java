/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.relacionlaboral.endPoint;

import java.util.logging.Level;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.relacionlaboral.model.RelacionLaboralRequest;
import mx.gob.imss.dpes.relacionlaboral.service.RelacionLaboralService;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.interfaces.relacionlaboral.model.RelacionLaboralIn;
import mx.gob.imss.dpes.interfaces.relacionlaboral.model.RelacionLaboralOut;

import org.eclipse.microprofile.openapi.annotations.Operation;

/**
 *
 * @author eduardo.loyo
 */
@Path("/laboral")
@RequestScoped
public class RelacionLaboralEndPoint extends BaseGUIEndPoint<RelacionLaboralIn, RelacionLaboralIn, RelacionLaboralRequest> {
    @Inject
    private RelacionLaboralService service;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consumo de RelacionLaboral",
            description = "Info del RelacionLaboral")
    public Response load(RelacionLaboralIn request) throws BusinessException {
    	log.log(Level.INFO,"RelacionLaboral - request :{0}", request);
    	Message<RelacionLaboralOut> out = service.execute(new Message<RelacionLaboralIn>(request));
        log.log(Level.INFO,"RelacionLaboral - responde: {0}", out);
        return toResponse(out);
    }
}
