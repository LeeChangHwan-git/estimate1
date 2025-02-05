package com.storelabs.mygage.estimate.service;

import com.storelabs.mygage.estimate.dto.request.ProjectCreateRequest;
import com.storelabs.mygage.estimate.dto.response.ProjectResponse;
import com.storelabs.mygage.estimate.entity.Project;
import com.storelabs.mygage.estimate.entity.User;
import com.storelabs.mygage.estimate.enums.ProjectStatusDetail;
import com.storelabs.mygage.estimate.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectFacadeService {

    private final AuthenticationService authenticationService;
    private final ProjectRepository projectRepository;

    @Transactional
    public List<ProjectResponse> findProjectsByUserIdByJwt(String token) {
        String userIdFromToken = authenticationService.getUserIdFromToken(token);

        return projectRepository.findByUserIdWithEstimates(userIdFromToken).stream()
                .map(ProjectResponse::from)
                .collect(Collectors.toList());
    }

}
