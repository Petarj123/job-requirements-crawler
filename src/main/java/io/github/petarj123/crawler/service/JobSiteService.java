package io.github.petarj123.crawler.service;

import io.github.petarj123.common.dto.JobSiteDTO;
import io.github.petarj123.common.exception.ValidationException;
import io.github.petarj123.domain.EntityMapper;
import io.github.petarj123.domain.entity.JobSite;
import io.github.petarj123.domain.repository.JobSiteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class JobSiteService {

    @Inject
    JobSiteRepository jobSiteRepository;

    @Inject
    EntityMapper entityMapper;

    @Inject
    Validator validator;

    public List<JobSite> getAll() {
        return jobSiteRepository.getAll();
    }

    public JobSite getById(int id) {
        return jobSiteRepository.getById(id).orElseThrow(() -> new NotFoundException("Job site not found with id: " + id));
    }

    @Transactional
    public JobSite createJobSite(JobSiteDTO jobSiteDTO) {
        JobSite jobSite = new JobSite(jobSiteDTO.name(), jobSiteDTO.baseUrl(), jobSiteDTO.lastCrawled(), jobSiteDTO.isActive(), LocalDateTime.now(), LocalDateTime.now());
        Set<ConstraintViolation<JobSite>> violations = validator.validate(jobSite);

        if (!violations.isEmpty()) {
            throw new ValidationException(violations);
        }

        jobSiteRepository.persist(jobSite);
        return jobSite;
    }

    @Transactional
    public JobSite updateJobSite(int id, JobSiteDTO jobSiteDTO) throws NoSuchFieldException, IllegalAccessException {
        JobSite jobsite = getById(id);
        entityMapper.updateEntity(jobsite, jobSiteDTO);
        jobsite.setUpdatedAt(LocalDateTime.now());

        jobSiteRepository.persist(jobsite);
        return jobsite;
    }


}
