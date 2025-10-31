/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.relacionlaboral.exception;

import mx.gob.imss.dpes.common.exception.BusinessException;

/**
 *
 * @author eduardo.loyo
 */
public class RelacionLaboralException extends BusinessException{
    private final static String KEY = "msg002";
    public RelacionLaboralException(){
        super(KEY);
    }
}
