package com.example.leavemanagementsystem.repository.absenceRepository;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.model.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    Absence findByAbsenceId(Long id);

    Page<Absence> findAllByStatus(Pageable pageable, int status);

    List<Absence> findAllByDepaId(Long id);

    List<Absence> findAllByUserId(Long id);

    List<Absence> findAllByStatus(int status);

    List<Absence> findAllByStatusAndDepaIdOrderByEndDateDesc(int status, Long id);

    @Query("select m from Absence m where m.startDate between ?1 and ?2 and m.status = 1 order by m.absenceId")
    List<Absence> getListAbsenceByMonth(Date date1, Date date2);

    @Query("select m from Absence m where (m.startDate <= ?1 and m.endDate >= ?2) or (m.startDate >= ?1 and m.endDate >= ?2 and m.startDate <= ?2) or (m.startDate <= ?1 and m.endDate <= ?2 and m.endDate >= ?1) or (m.startDate >= ?1 and m.endDate <= ?2) and m.type = 1 and m.userId=?3 and m.status=1")
    List<Absence> getListAbsenceFurloughByMonth(Date fromDate, Date toDate, Long userId);

    @Query("select m from Absence m where (m.startDate <= ?1 and m.endDate >= ?2) or (m.startDate >= ?1 and m.endDate >= ?2 and m.startDate <= ?2) or (m.startDate <= ?1 and m.endDate <= ?2 and m.endDate >= ?1) or (m.startDate >= ?1 and m.endDate <= ?2) and m.type = 2 and m.userId=?3 and m.status=1")
    List<Absence> getListAbsenceUnpaidByMonth(Date fromDate, Date toDate, Long userId);

    @Query("select m from Absence m where m.startDate between ?1 and ?2 and m.status = 1")
    List<Absence> getListPostByMonth(Date date1, Date date2);

    @Query("select m from Absence m where m.startDate between ?1 and ?2 and m.status = 1 and m.userId = ?3")
    List<Absence> getListPostByMonthAndUserId(Date date1, Date date2, Long userId);

    @Query("select m from Absence m where m.startDate < ?2 and m.endDate > ?1 and m.status = 2")
    List<Absence> getListPostByDate(Date date1, Date date2);

    @Query("select m,u.fullName as fullNameOfDepartment from Absence m " +
            "inner join UserEntity u on u.userId = m.absenceBy where m.userId = ?1")
    List<AbsenceResponse> getListPostByUser1(Long userID);

    int countByStatusAndDepaId(int status, Long departmentId);

    //    @Query(value = "select a.absence_id, a.start_date , a.end_date , a.date_off ,a.title, a.description,a.status,a.type, a.absence_response , a.enable_date , a.create_date , u.full_name,d.department_name,u.full_name ,u.job_name ,a.type,a.absence_by,a.person_in_charge from absence a inner join user u on u.user_id= a.user_id inner join department d on d.department_id = a.department_id where a.user_id = ?1 and not a.status = 3 ", nativeQuery = true)
//    Page<AbsenceResponse> getAllByUserId(Long id, Pageable pageable);
    @Query(value = " SELECT count(*) FROM absence a INNER JOIN user u ON u.user_id = a.user_id WHERE a.user_id = ?1 AND NOT a.status = 3",
            nativeQuery = true)
    long countAllByUserId(Long id);

    @Query(value = "select count(*) FROM absence a INNER JOIN department d on d.department_id = a.department_id WHERE a.department_id=?1 AND NOT a.status = 3",
            nativeQuery = true)
    long findAllAbsenceByDepaId(Long id);

    @Query(value = " select count(*) FROM absence a INNER JOIN department d on d.department_id = a.department_id INNER JOIN user u on u.user_id = a.user_id WHERE a.status =?1 and a.department_id=?2 ",
            nativeQuery = true)
    long findAllAbsenceByDepaIdAndStatus(Long status, Long id);
}
