package com.optimagrowth.organization.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimagrowth.organization.model.Organization;
import com.optimagrowth.organization.repository.OrganizationRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);

    @CircuitBreaker(name = "organizationservice", fallbackMethod = "buildFallbackLicenseList")
    public Organization findById(String organizationId) throws TimeoutException {
        randomlyRunLong();
        Optional<Organization> opt = repository.findById(organizationId);
        return (opt.isPresent()) ? opt.get() : null;
    }

    public Organization create(Organization organization) {
        organization.setId(UUID.randomUUID().toString());
        organization = repository.save(organization);
        return organization;

    }

    public void update(Organization organization) {
        repository.save(organization);
    }

    public void delete(Organization organization) {
        repository.deleteById(organization.getId());
    }

    private void randomlyRunLong() throws TimeoutException {
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 1) + 1) + 1;
        if (randomNum == 3)
            sleep();
    }

    private void sleep() throws TimeoutException {
        try {
            System.out.println("Sleep");
            Thread.sleep(5000);
            throw new java.util.concurrent.TimeoutException();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }
}