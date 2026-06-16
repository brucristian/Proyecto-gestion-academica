package org.unischeduler.backend.domain.port.out.enrollment.repository;

import org.unischeduler.backend.domain.model.enrollment.entity.EnrollmentDetail;

import java.util.ArrayList;

public interface EnrollmentDetailRepository {
    EnrollmentDetail save(EnrollmentDetail enrollmentDetail, String enrollmentId);

    ArrayList<EnrollmentDetail> findByEnrollmentId(String enrollmentId);
}
