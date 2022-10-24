package com.bpop.kaisen.AOP;

import com.bpop.kaisen.models.dto.DataInfoRes;
import com.bpop.kaisen.models.dto.ProductInfoDTO;
import com.bpop.kaisen.models.entities.Audit;
import com.bpop.kaisen.models.entities.User;
import com.bpop.kaisen.repositories.AuditRepository;
import com.bpop.kaisen.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
@Log4j2
public class ValidateCreateDataAspect {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuditRepository auditRepository;

    @Around("execution(* createAndUpdate*(..))")
    public Object validateUserSession(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object obj : args) {
            if (obj instanceof ProductInfoDTO) {
                ProductInfoDTO productInfoDTO = (ProductInfoDTO) obj;
                User user = userRepository.findByIdentificationAndPass(productInfoDTO.getUserId(), productInfoDTO.getPsw()).orElseGet(()-> null);
                if (user == null) {
                    return DataInfoRes.builder().statusCode(403).responseStatus("Usted no tiene permiso para crear productos").build();
                }

            }
        }
        return joinPoint.proceed();
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
