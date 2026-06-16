package org.unischeduler.backend.infrastructure.out.repository.academic_catalog;



import org.unischeduler.backend.domain.exceptions.academic_catalog.PeriodActiveNotFoundException;
import org.unischeduler.backend.domain.model.academic_catalog.entity.AcademicPeriod;
import org.unischeduler.backend.infrastructure.out.entity.academic_catalog.AcademicPeriodEntity;
import org.unischeduler.backend.infrastructure.out.persistence.excel.repository.academic_catalog.ExcelAcademicPeriodRepository;
import org.unischeduler.backend.domain.port.out.academic_catalog.AcademicPeriodRepository;

import org.unischeduler.backend.infrastructure.out.mapper.academic_catalog.AcademicPeriodMapper;

import java.util.List;
import java.util.Optional;


public class AcademicPeriodRepositoryImpl implements AcademicPeriodRepository {

    private final ExcelAcademicPeriodRepository academicPeriodRepository;

    public AcademicPeriodRepositoryImpl(ExcelAcademicPeriodRepository academicPeriodRepository) {
        this.academicPeriodRepository = academicPeriodRepository;
    }

    @Override
    public Optional<AcademicPeriod> findById(String id) {
        return academicPeriodRepository.findById(id)
                .map(AcademicPeriodMapper::toDomain);
    }

    @Override
    public List<AcademicPeriod> findAll() {
        return academicPeriodRepository.findAll()
                .stream()
                .map(AcademicPeriodMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByCode(String code) {
        return academicPeriodRepository.existsByCode(code);
    }

    @Override
    public Optional<AcademicPeriod> findByCode(String code) {
        return academicPeriodRepository.findByCode(code)
                .map(AcademicPeriodMapper::toDomain);
    }

    @Override
    public AcademicPeriod save(AcademicPeriod academicPeriod) {

        AcademicPeriodEntity entity =
                AcademicPeriodMapper.toEntity(academicPeriod);

        AcademicPeriodEntity savedEntity =
                academicPeriodRepository.save(entity);

        return AcademicPeriodMapper.toDomain(savedEntity);
    }

    @Override
    public AcademicPeriod update(AcademicPeriod academicPeriod) {

        AcademicPeriodEntity entity =
                AcademicPeriodMapper.toEntity(academicPeriod);

        AcademicPeriodEntity updatedEntity =
                academicPeriodRepository.update(entity);

        return AcademicPeriodMapper.toDomain(updatedEntity);
    }

    @Override
    public boolean deleteById(String id) {
        return academicPeriodRepository.deleteById(id);
    }

    @Override
    public Optional<AcademicPeriod> findActive() {
        AcademicPeriodEntity entity = academicPeriodRepository.findActive()
                .orElseThrow(() -> new PeriodActiveNotFoundException("No se encontro ningun periodo academico activo"));

        return Optional.of(AcademicPeriodMapper.toDomain(entity));
    }
}