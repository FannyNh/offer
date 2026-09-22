package com.thirdmoira.offer_backend.domain;

import com.thirdmoira.offer_backend.data.ServiceCategoryRepository;
import com.thirdmoira.offer_backend.data.ServiceRepository;
import com.thirdmoira.offer_backend.data.entities.ServiceCategoryEntity;
import com.thirdmoira.offer_backend.data.entities.ServiceEntity;
import com.thirdmoira.offer_backend.data.exceptions.ServiceCategoryNotFoundException;
import com.thirdmoira.offer_backend.data.exceptions.ServiceNotFoundException;
import com.thirdmoira.offer_backend.domain.models.Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceServiceTest {

    @InjectMocks
    private ServiceService serviceService;

    @Mock
    private ServiceRepository serviceRepository;

    @Mock
    private ServiceCategoryRepository serviceCategoryRepository;

    @Test
    void should_create_service_with_category_for_user() {
        ServiceCategoryEntity categoryEntity = new ServiceCategoryEntity();
        categoryEntity.setId(2L);
        when(serviceCategoryRepository.findEntityByIdAndUserId(2L, 7L)).thenReturn(Optional.of(categoryEntity));

        Service created = new Service();
        created.setId(10L);
        when(serviceRepository.save(any(ServiceEntity.class))).thenReturn(created);

        serviceService.create(7L, 2L, "Design Sprint", "5-day workshop");

        ArgumentCaptor<ServiceEntity> captor = ArgumentCaptor.forClass(ServiceEntity.class);
        verify(serviceRepository).save(captor.capture());
        ServiceEntity saved = captor.getValue();
        assertEquals(7L, saved.getUser().getUserId());
        assertEquals(2L, saved.getCategory().getId());
        assertEquals("Design Sprint", saved.getTitle());
        assertEquals("5-day workshop", saved.getDescription());
        assertEquals(BigDecimal.ZERO, saved.getBasePrice());
    }

    @Test
    void should_create_service_without_category_when_category_is_null() {
        Service created = new Service();
        created.setId(10L);
        when(serviceRepository.save(any(ServiceEntity.class))).thenReturn(created);

        serviceService.create(7L, null, "Design Sprint", "5-day workshop");

        ArgumentCaptor<ServiceEntity> captor = ArgumentCaptor.forClass(ServiceEntity.class);
        verify(serviceRepository).save(captor.capture());
        ServiceEntity saved = captor.getValue();
        assertNull(saved.getCategory());
    }

    @Test
    void should_throw_not_found_when_updating_unknown_service() {
        when(serviceRepository.findEntityByIdAndUserId(99L, 7L)).thenReturn(Optional.empty());

        ServiceNotFoundException ex = assertThrows(
                ServiceNotFoundException.class,
                () -> serviceService.update(7L, 99L, null, "Title", "Desc")
        );

        assertEquals("Service not found", ex.getMessage());
    }

    @Test
    void should_throw_not_found_when_deleting_unknown_service() {
        when(serviceRepository.findEntityByIdAndUserId(99L, 7L)).thenReturn(Optional.empty());

        ServiceNotFoundException ex = assertThrows(
                ServiceNotFoundException.class,
                () -> serviceService.delete(7L, 99L)
        );

        assertEquals("Service not found", ex.getMessage());
    }

    @Test
    void should_delete_service_owned_by_user() {
        ServiceEntity entity = new ServiceEntity();
        entity.setId(11L);
        when(serviceRepository.findEntityByIdAndUserId(11L, 7L)).thenReturn(Optional.of(entity));

        serviceService.delete(7L, 11L);

        verify(serviceRepository).delete(entity);
    }

    @Test
    void should_throw_when_category_does_not_belong_to_user() {
        when(serviceCategoryRepository.findEntityByIdAndUserId(4L, 7L)).thenReturn(Optional.empty());

        ServiceCategoryNotFoundException ex = assertThrows(
                ServiceCategoryNotFoundException.class,
                () -> serviceService.create(7L, 4L, "Title", "Desc")
        );

        assertEquals("Service category not found", ex.getMessage());
    }
}

