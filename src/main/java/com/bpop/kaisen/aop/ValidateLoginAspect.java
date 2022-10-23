package com.bpop.kaisen.aop;

import com.bpop.kaisen.models.dto.DataInfoRes;
import com.bpop.kaisen.models.dto.ProductInfoDTO;
import com.bpop.kaisen.models.entities.Audit;
import com.bpop.kaisen.models.entities.User;
import com.bpop.kaisen.repositories.AuditRepository;
import com.bpop.kaisen.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@Log4j2
public class ValidateLoginAspect {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuditRepository auditRepository;

    @Before("execution(* createAndUpdate*(..))")
    public DataInfoRes validateUserSession(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        DataInfoRes dataInfoRes = new DataInfoRes();
        for (Object obj : args) {
            if (obj instanceof ProductInfoDTO) {
                ProductInfoDTO productInfoDTO = (ProductInfoDTO) obj;
                User user = userRepository.findByIdentificationAndPass(productInfoDTO.getUserId(), productInfoDTO.getPsw()).orElseGet(()-> null);
                if (user == null) {
                    dataInfoRes =DataInfoRes.builder().statusCode(403).responseStatus("Usted no tiene permiso para crear productos").build();
                }
            }
        }
        return dataInfoRes;
    }

    @After("execution(* createAndUpdate*(..))")
    public void insertAuditInfo(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object obj: args) {
            if (obj instanceof ProductInfoDTO) {
                ProductInfoDTO productInfoDTO = (ProductInfoDTO) obj;
                Audit audit = Audit.builder().date(new Date()).productName(productInfoDTO.getProduct().getName()).userId(productInfoDTO.getUserId()).build();
                auditRepository.save(audit);
            }
        }
    }

}
