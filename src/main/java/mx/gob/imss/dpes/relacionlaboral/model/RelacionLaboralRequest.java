/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.relacionlaboral.model;

import lombok.Data;
import mx.gob.imss.dpes.common.model.BaseModel;
import mx.gob.imss.dpes.interfaces.relacionlaboral.model.RelacionLaboralIn;
import mx.gob.imss.dpes.interfaces.relacionlaboral.model.RelacionLaboralOut;


/**
 *
 * @author eduardo.loyo
 */
@Data
public class RelacionLaboralRequest extends BaseModel {
    private RelacionLaboralIn request = new RelacionLaboralIn();
    private RelacionLaboralOut response = new RelacionLaboralOut();
}
