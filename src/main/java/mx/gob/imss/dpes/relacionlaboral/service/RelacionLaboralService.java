/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.relacionlaboral.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;

import mx.gob.imss.dpes.common.service.ServiceDefinition;
import mx.gob.imss.dpes.interfaces.relacionlaboral.model.InfoCIRelacionLaboral;
import mx.gob.imss.dpes.interfaces.relacionlaboral.model.InfoCIRelacionLaboralData;
import mx.gob.imss.dpes.interfaces.relacionlaboral.model.RelacionLaboralIn;
import mx.gob.imss.dpes.interfaces.relacionlaboral.model.RelacionLaboralOut;
import mx.gob.imss.dpes.relacionlaboral.exception.RelacionLaboralException;
import mx.gob.imss.dpes.relacionlaboral.model.RelacionLaboralRequest;
import mx.gob.imss.dpes.relacionlaboral.ws.WSCIRelacionLaboralService;

import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author eduardo.loyo
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class RelacionLaboralService extends ServiceDefinition<RelacionLaboralIn, RelacionLaboralOut> {

    @Autowired
    private WSCIRelacionLaboralService service;
    
    

    @Override
    public Message<RelacionLaboralOut> execute(Message<RelacionLaboralIn> request) throws BusinessException {
        log.log(Level.INFO,"LLAMADA a cliente request: {0}", request.getPayload());
        mx.gob.imss.dpes.relacionlaboral.ws.ResponseCIRelacionesLaboralesResource response
                = service.getRelacionesLaboralesByNSS(request.getPayload().getNss());
        
        if(response.getCode().equals("000")){
            
            final List<InfoCIRelacionLaboral> listaRespuesta = new ArrayList<>();
            
            response.getListInfoRelacionesLaborales()
                    .getLstInfoRelacionesLaborales().forEach(new Consumer<mx.gob.imss.dpes.relacionlaboral.ws.InfoCIRelacionLaboral>(){
                        @Override
                        public void accept(mx.gob.imss.dpes.relacionlaboral.ws.InfoCIRelacionLaboral source){
                            InfoCIRelacionLaboralData relacion = new InfoCIRelacionLaboralData();
                            
                            relacion.setFecFinRelLab(source.getFecFinRelLab());
                            relacion.setFecIniRelLab(source.getFecIniRelLab());
                            relacion.setModalidad(source.getModalidad());
                            relacion.setRegPatron(source.getRegPatron()+source.getModalidad());
                            relacion.setTipoMovimiento(source.getTipoMovimiento());
                            InfoCIRelacionLaboral wrapper = new InfoCIRelacionLaboral();
                            wrapper.setLstInfoRelacionesLaborales(relacion);
                            listaRespuesta.add(wrapper);
                           
                        }
                    });
            RelacionLaboralOut respuesta = new RelacionLaboralOut();
            respuesta.setCode(response.getCode());
            respuesta.setMessage(response.getMessage());
            respuesta.setListInfoRelacionesLaborales(listaRespuesta);
            return new Message<>(respuesta);
        }else if(response.getCode().equals("002")){
            RelacionLaboralOut respuesta = new RelacionLaboralOut();
            respuesta.setCode(response.getCode());
            respuesta.setMessage(response.getMessage());
            return new Message<>(respuesta);
        }
        return response(null, ServiceStatusEnum.EXCEPCION, null, null);
        
    }

}
