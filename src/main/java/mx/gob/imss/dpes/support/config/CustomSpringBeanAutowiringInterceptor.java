/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.support.config;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

/**
 *
 * @author antonio
 */
public class CustomSpringBeanAutowiringInterceptor extends SpringBeanAutowiringInterceptor {

    /**
     * Template method for configuring the
     * {@link AutowiredAnnotationBeanPostProcessor} used for autowiring.
     * @param processor the AutowiredAnnotationBeanPostProcessor to configure
     * @param target the target bean to autowire with this processor
     */
    @Override
    protected void configureBeanPostProcessor(AutowiredAnnotationBeanPostProcessor processor, Object target) {
        Set<Class<? extends Annotation>> annotationsToScan = new HashSet<>();
        annotationsToScan.add(Autowired.class);
        annotationsToScan.add(Value.class);
        processor.setAutowiredAnnotationTypes(annotationsToScan);

    }
}
