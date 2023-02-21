package com.example.leavemanagementsystem.repository.absenceRepository;

import com.example.leavemanagementsystem.dto.*;
import lombok.*;
import org.hibernate.transform.*;
import org.hibernate.type.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.time.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AbsenceRepositoryCustomImpl implements AbsenceRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;


//    @Override
//    public long countAllByUserId(Long id) {
//        StringBuilder sql = new StringBuilder();
//        sql.append("SELECT count(*)");
//        sql.append(" FROM absence a ");
//        sql.append(" INNER JOIN user u ON u.user_id = a.user_id ");
//        sql.append(" INNER JOIN department d on d.department_id = a.department_id ");
//        sql.append(" WHERE a.user_id = ").append(id);
//        sql.append(" AND NOT a.status = 3");
//        return  ((Number) entityManager.unwrap(org.hibernate.Session.class)
//                .createNativeQuery(sql.toString()).getFirstResult()).longValue();
//    }

    @Override
    public List<AbsenceResponse> findAllByUserId(Long id, Integer limit,
                                                 Integer offset) {//limit=perpage , offset= perpage*page
        List<AbsenceResponse> res = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a.absence_id as absenceId, a.start_date as startDate, a.end_date as endDate,");
        sql.append(" a.date_off as dayOff,a.title, a.description,a.status,a.type, a.absence_response as note,  ");
        sql.append(" a.enable_date as enableDate, a.create_date as createDate  , u.full_name as postUser, ");
        sql.append(
                " d.department_name as departmentName,u.full_name as userName,u.job_name as jobName,a.type as type,a.absence_by as absenceBy,a.person_in_charge as personInCharge ");
        sql.append(" FROM absence a ");
        sql.append(" INNER JOIN user u ON u.user_id = a.user_id ");
        sql.append(" INNER JOIN department d on d.department_id = a.department_id ");
        sql.append(" WHERE a.user_id = ").append(id);
        sql.append(" AND NOT a.status = 3");
        sql.append(" ORDER BY a.`status`, a.create_date desc ");

        if (limit != null && offset != null) {
            sql.append(" LIMIT ").append(limit).append(" OFFSET ").append(offset);
        }
        res = entityManager.unwrap(org.hibernate.Session.class).createNativeQuery(sql.toString())
                .addScalar("absenceId", LongType.INSTANCE)
                .addScalar("startDate", DateType.INSTANCE)
                .addScalar("endDate", DateType.INSTANCE)
                .addScalar("dayOff", FloatType.INSTANCE)
                .addScalar("title", StringType.INSTANCE)
                .addScalar("description", StringType.INSTANCE)
                .addScalar("status", IntegerType.INSTANCE)
                .addScalar("type", IntegerType.INSTANCE)
                .addScalar("note", StringType.INSTANCE)
                .addScalar("enableDate", DateType.INSTANCE)
                .addScalar("createDate", DateType.INSTANCE)
                .addScalar("postUser", StringType.INSTANCE)
                .addScalar("departmentName", StringType.INSTANCE)
                .addScalar("userName", StringType.INSTANCE)
                .addScalar("jobName", StringType.INSTANCE)
                .addScalar("type", IntegerType.INSTANCE)
                .addScalar("absenceBy", LongType.INSTANCE)
                .addScalar("personInCharge", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(AbsenceResponse.class))
                .list();

        entityManager.close();
        return res;
    }

    @Override
    public List<AbsenceResponse> findAllAbsenceByDepaIdAndStatus(Long status, Long id, Integer limit, Integer offset) {
        List<AbsenceResponse> res = null;
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT  a.absence_id as absenceId, a.start_date as startDate, a.end_date as endDate," +
                        "a.date_off as dayOff,a.title,a.description,a.status,a.type, a.absence_response as note ," +
                        "a.enable_date as enableDate, a.create_date as createDate  , u.full_name as postUser," +
                        "d.department_name as departmentName,u.full_name as userName,u.job_name as jobName,a.type as type,a.absence_by as absenceBy,a.person_in_charge as personInCharge ");
        sql.append(" FROM absence a ");
        sql.append(" INNER JOIN department d on d.department_id = a.department_id ");
        sql.append(" INNER JOIN user u on u.user_id = a.user_id");
        sql.append(" WHERE a.status =" + status + " and a.department_id=" + id);
        if (limit != null && offset != null) {
            sql.append(" LIMIT ").append(limit).append(" OFFSET ").append(offset);
        }

        res = entityManager.unwrap(org.hibernate.Session.class).createNativeQuery(sql.toString())
                .addScalar("absenceId", LongType.INSTANCE)
                .addScalar("startDate", DateType.INSTANCE)
                .addScalar("endDate", DateType.INSTANCE)
                .addScalar("dayOff", FloatType.INSTANCE)
                .addScalar("title", StringType.INSTANCE)
                .addScalar("description", StringType.INSTANCE)
                .addScalar("status", IntegerType.INSTANCE)
                .addScalar("type", IntegerType.INSTANCE)
                .addScalar("note", StringType.INSTANCE)
                .addScalar("enableDate", DateType.INSTANCE)
                .addScalar("createDate", DateType.INSTANCE)
                .addScalar("postUser", StringType.INSTANCE)
                .addScalar("departmentName", StringType.INSTANCE)
                .addScalar("userName", StringType.INSTANCE)
                .addScalar("jobName", StringType.INSTANCE)
                .addScalar("type", IntegerType.INSTANCE)
                .addScalar("absenceBy", LongType.INSTANCE)
                .addScalar("personInCharge", LongType.INSTANCE)

                .setResultTransformer(Transformers.aliasToBean(AbsenceResponse.class))
                .list();
        entityManager.close();
        return res;
    }

    @Override
    public List<AbsenceResponse> findAllAbsenceByDepaId(Long id, Integer limit, Integer offset) {
        List<AbsenceResponse> res = null;
        StringBuilder sql = new StringBuilder();
        int start_page = 0;
        int size = 10;
        sql.append(
                "SELECT  a.absence_id as absenceId, a.start_date as startDate, a.end_date as endDate,a.date_off as dayOff,a.title,a.description,a.status,a.type, a.absence_response as note ,a.enable_date as enableDate, a.create_date as createDate  , u.full_name as postUser,d.department_name as departmentName,u.full_name as userName,u.job_name as jobName,a.type as type,a.absence_response as note,a.absence_by as absenceBy,a.person_in_charge as personInCharge ");
        sql.append(" FROM absence a ");
        sql.append(" INNER JOIN department d on d.department_id = a.department_id ");
        sql.append(" INNER JOIN user u on u.user_id = a.user_id");
        sql.append(" WHERE a.department_id=" + id);
        sql.append(" AND NOT a.status = 3");
//        sql.append(" LIMIT "+start_page+" , "+(start_page+size));
        if (limit != null && offset != null) {
            sql.append(" LIMIT ").append(limit).append(" OFFSET ").append(offset);
        }

        res = entityManager.unwrap(org.hibernate.Session.class).createNativeQuery(sql.toString())
                .addScalar("absenceId", LongType.INSTANCE)
                .addScalar("startDate", DateType.INSTANCE)
                .addScalar("endDate", DateType.INSTANCE)
                .addScalar("dayOff", FloatType.INSTANCE)
                .addScalar("title", StringType.INSTANCE)
                .addScalar("description", StringType.INSTANCE)
                .addScalar("status", IntegerType.INSTANCE)
                .addScalar("type", IntegerType.INSTANCE)
                .addScalar("note", StringType.INSTANCE)
                .addScalar("enableDate", DateType.INSTANCE)
                .addScalar("createDate", DateType.INSTANCE)
                .addScalar("postUser", StringType.INSTANCE)
                .addScalar("departmentName", StringType.INSTANCE)
                .addScalar("userName", StringType.INSTANCE)
                .addScalar("jobName", StringType.INSTANCE)
                .addScalar("type", IntegerType.INSTANCE)
                .addScalar("note", StringType.INSTANCE)
                .addScalar("absenceBy", LongType.INSTANCE)
                .addScalar("personInCharge", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(AbsenceResponse.class))
                .list();
        entityManager.close();
        return res;
    }

    @Override
    public List<WordResponse> findByAbsenceId(Long id) {
        List<WordResponse> res = null;
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT a.absence_id as absenceId, a.start_date as startDate, a.end_date as endDate,a.department_id as departmentId,a.user_id as userId,");
        sql.append(" a.date_off as dayOff,a.title, a.description,a.status,a.type, a.absence_response as note,  ");
        sql.append(" a.enable_date as enableDate, a.create_date as createDate  , u.full_name as postUser, ");
        sql.append(
                " d.department_name as departmentName,u.full_name as userName,u.job_name as jobName,a.type as type,a.absence_by as absenceBy,u.phone_number as phoneNumber,a.person_in_charge as personInCharge,a.absence_response as absenceResponse  ");
        sql.append(" FROM absence a ");
        sql.append(" INNER JOIN user u ON u.user_id = a.user_id ");
        sql.append(" INNER JOIN department d on d.department_id = a.department_id ");
        sql.append(" WHERE a.absence_id = " + id);
        sql.append(" ORDER BY a.`status`, a.create_date desc ");
//        sql.append(" LIMIT "+start_page+" , "+(start_page+size));

        res = entityManager.unwrap(org.hibernate.Session.class).createNativeQuery(sql.toString())
                .addScalar("absenceId", LongType.INSTANCE)
                .addScalar("userId", LongType.INSTANCE)
                .addScalar("startDate", LocalDateTimeType.INSTANCE)
                .addScalar("endDate", LocalDateTimeType.INSTANCE)
                .addScalar("departmentId", LongType.INSTANCE)
                .addScalar("dayOff", FloatType.INSTANCE)
                .addScalar("title", StringType.INSTANCE)
                .addScalar("description", StringType.INSTANCE)
                .addScalar("status", IntegerType.INSTANCE)
                .addScalar("type", IntegerType.INSTANCE)
                .addScalar("note", StringType.INSTANCE)
                .addScalar("enableDate", DateType.INSTANCE)
                .addScalar("createDate", LocalDateTimeType.INSTANCE)
                .addScalar("postUser", StringType.INSTANCE)
                .addScalar("departmentName", StringType.INSTANCE)
                .addScalar("userName", StringType.INSTANCE)
                .addScalar("jobName", StringType.INSTANCE)
                .addScalar("type", IntegerType.INSTANCE)
                .addScalar("absenceBy", LongType.INSTANCE)
                .addScalar("phoneNumber", StringType.INSTANCE)
                .addScalar("personInCharge", LongType.INSTANCE)
                .addScalar("absenceResponse", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(WordResponse.class))
                .list();

        entityManager.close();
        return res;
    }

    @Override
    public List<WordResponse> getListWordResponseByMonth(LocalDate date1, LocalDate date2) {
        List<WordResponse> res = null;
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT a.absence_id as absenceId, a.start_date as startDate, a.end_date as endDate,a.department_id as departmentId,a.user_id as userId,");
        sql.append(" a.date_off as dayOff,a.title, a.description,a.status,a.type, a.absence_response as note,  ");
        sql.append(" a.enable_date as enableDate, a.create_date as createDate  , u.full_name as postUser, ");
        sql.append(
                " d.department_name as departmentName,u.full_name as userName,u.job_name as jobName,a.type as type,a.absence_by as absenceBy,u.phone_number as phoneNumber,a.person_in_charge as personInCharge,a.absence_response as absenceResponse   ");
        sql.append(" FROM absence a ");
        sql.append(" INNER JOIN user u ON u.user_id = a.user_id ");
        sql.append(" INNER JOIN department d on d.department_id = u.department_id ");
        sql.append(" WHERE a.start_date between '" + date1 + "' and '" + date2 + "' and a.status = 1");
        sql.append(" ORDER BY a.`status`, a.create_date desc ");
//        sql.append(" LIMIT "+start_page+" , "+(start_page+size));

        res = entityManager.unwrap(org.hibernate.Session.class).createNativeQuery(sql.toString())
                .addScalar("absenceId", LongType.INSTANCE)
                .addScalar("startDate", LocalDateTimeType.INSTANCE)
                .addScalar("userId", LongType.INSTANCE)
                .addScalar("endDate", LocalDateTimeType.INSTANCE)
                .addScalar("departmentId", LongType.INSTANCE)
                .addScalar("dayOff", FloatType.INSTANCE)
                .addScalar("title", StringType.INSTANCE)
                .addScalar("description", StringType.INSTANCE)
                .addScalar("status", IntegerType.INSTANCE)
                .addScalar("type", IntegerType.INSTANCE)
                .addScalar("note", StringType.INSTANCE)
                .addScalar("enableDate", DateType.INSTANCE)
                .addScalar("createDate", LocalDateTimeType.INSTANCE)
                .addScalar("postUser", StringType.INSTANCE)
                .addScalar("departmentName", StringType.INSTANCE)
                .addScalar("userName", StringType.INSTANCE)
                .addScalar("jobName", StringType.INSTANCE)
                .addScalar("type", IntegerType.INSTANCE)
                .addScalar("absenceBy", LongType.INSTANCE)
                .addScalar("phoneNumber", StringType.INSTANCE)
                .addScalar("personInCharge", LongType.INSTANCE)
                .addScalar("absenceResponse", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(WordResponse.class))
                .list();

        entityManager.close();
        return res;
    }

    @Override
    public List<AbsenceResponse> getListAbsenceResponseByMonth(LocalDate date1, LocalDate date2) {
        List<AbsenceResponse> res = null;
        StringBuilder sql = new StringBuilder();
        sql.append(
                "SELECT a.absence_id as absenceId, a.start_date as startDate, a.end_date as endDate,a.department_id as departmentId,a.user_id as userId,");
        sql.append(" a.date_off as dayOff,a.title, a.description,a.status,a.type, a.absence_response as note,  ");
        sql.append(" a.enable_date as enableDate, a.create_date as createDate  , u.full_name as postUser, ");
        sql.append(
                " d.department_name as departmentName,u.full_name as userName,u.job_name as jobName,a.type as type,a.absence_by as absenceBy,u.phone_number as phoneNumber,a.person_in_charge as personInCharge  ");
        sql.append(" FROM absence a ");
        sql.append(" INNER JOIN user u ON u.user_id = a.user_id ");
        sql.append(" INNER JOIN department d on d.department_id = a.department_id ");
        sql.append(" WHERE a.start_date between '" + date1 + "' and '" + date2 + "' and a.status = 1");
        sql.append(" ORDER BY a.absence_id ");
//        sql.append(" LIMIT "+start_page+" , "+(start_page+size));

        res = entityManager.unwrap(org.hibernate.Session.class).createNativeQuery(sql.toString())
                .addScalar("absenceId", LongType.INSTANCE)
                .addScalar("startDate", DateType.INSTANCE)
                .addScalar("userId", LongType.INSTANCE)
                .addScalar("endDate", DateType.INSTANCE)
                .addScalar("departmentId", LongType.INSTANCE)
                .addScalar("dayOff", FloatType.INSTANCE)
                .addScalar("title", StringType.INSTANCE)
                .addScalar("description", StringType.INSTANCE)
                .addScalar("status", IntegerType.INSTANCE)
                .addScalar("type", IntegerType.INSTANCE)
                .addScalar("note", StringType.INSTANCE)
                .addScalar("enableDate", DateType.INSTANCE)
                .addScalar("createDate", DateType.INSTANCE)
                .addScalar("postUser", StringType.INSTANCE)
                .addScalar("departmentName", StringType.INSTANCE)
                .addScalar("userName", StringType.INSTANCE)
                .addScalar("jobName", StringType.INSTANCE)
                .addScalar("type", IntegerType.INSTANCE)
                .addScalar("absenceBy", LongType.INSTANCE)
                .addScalar("phoneNumber", StringType.INSTANCE)
                .addScalar("personInCharge", LongType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(AbsenceResponse.class))
                .list();

        entityManager.close();
        return res;
    }
}
