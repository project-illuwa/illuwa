package com.yunseong.location.service;

import com.yunseong.location.domain.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

}
