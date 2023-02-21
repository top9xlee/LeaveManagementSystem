package com.example.leavemanagementsystem.service.userService;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.mapper.*;
import com.example.leavemanagementsystem.model.*;
import com.example.leavemanagementsystem.repository.*;
import com.example.leavemanagementsystem.repository.absenceRepository.*;
import com.example.leavemanagementsystem.repository.user.*;
import com.example.leavemanagementsystem.utils.*;
import lombok.extern.log4j.*;
import net.bytebuddy.utility.*;
import org.springframework.beans.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Log4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    AbsenceRepository absenceRepository;

    @Autowired
    AbsenceRepositoryCustom absenceRepositoryCustom;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    AbsenceMapper absenceMapper;


    @Override
    public void createAppUser(UserEntity user) {

        user.setEncrytedPassword(bCryptPasswordEncoder.encode(user.getEncrytedPassword()));
        user.setStatus(1);
        userRepository.save(user);

    }

    @Override
    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElse(new UserEntity());

    }

    @Override
    public boolean updatePw(AppUserDto appUserDto) {
        UserEntity user = userRepository.findById(appUserDto.getUserId()).get();
        //      user.setUserName(appUserDto.getUserName());
//        user.setDepartment(appUserDto.getDepartmentId());
        //       user.setFullName(appUserDto.getFullName());
        //       user.setEmail(appUserDto.getEmail());
        //       System.out.println(appUserDto.getNewPassword());
        if (bCryptPasswordEncoder.matches(appUserDto.getChangePw(), user.getEncrytedPassword())) {
            user.setEncrytedPassword(bCryptPasswordEncoder.encode(appUserDto.getNewPassword()));
            userRepository.save(user);
            return true;

        }
        return false;
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Boolean resetPassword(UserEntity user, String newPass) {
        user.setEncrytedPassword(bCryptPasswordEncoder.encode(newPass));
        userRepository.save(user);
        return true;
    }

    @Override
    public UserEntity findByVerificationCode(String code) {
        return userRepository.findByVerificationCode(code);
    }

    @Override
    public Boolean verify(String code) {
        UserEntity user = findByVerificationCode(code);
        if (user != null) {
//            user.setIsVerifyEmail(Constants.STATUS_USE.ACTIVATE);
            userRepository.save(user);
            return true;
        } else
            return false;
    }

    @Override
    public void setVerify(Long id) {
        UserEntity userEntity = userRepository.findByUserId(id);
        String randomCode = RandomString.make(64);
        userEntity.setVerificationCode(randomCode);
        userRepository.save(userEntity);
    }

    @Override
    public PageResponse getAllUserEntityEnable(int limitPageUser, int page, Sort sort) {

        List<UserEntityDto> userEntityDto = new ArrayList<>();

        try {
            org.springframework.data.domain.Pageable pageable = PageRequest.of(page - 1, limitPageUser, sort);
            Page<UserEntity> userLst = userRepository.findAllByStatus(1, pageable);
            if (userLst.isEmpty()) return Utils.createPageResponse(0, null);
            for (UserEntity item : userLst) {
                UserEntityDto userEntityDto1 = new UserEntityDto();
                BeanUtils.copyProperties(item, userEntityDto1);
                userEntityDto.add(userEntityDto1);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return Utils.createPageResponse(totalItem(), userEntityDto);
    }

    @Override
    public PageResponse getAllByUserId(int limitPageUser, int page, UserEntity user) {
        long count = 0;
        List<AbsenceResponse> list = new ArrayList<>();

        try {
//            count = absenceRepositoryCustom.countAllByUserId(user.getUserId());
            count = absenceRepository.countAllByUserId(user.getUserId());
            list = absenceRepositoryCustom.findAllByUserId(user.getUserId(), limitPageUser, (page - 1) * limitPageUser)
                    .stream().map(absenceMapper::maptoDto2).collect(Collectors.toList());


        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return Utils.createPageResponse(count, list);
    }

    @Override
    public int totalItem() {
        return userRepository.getAllUserEntityEnable();
    }

    @Override
    public Response deleteUserEntity(Long id) {
        UserEntity userEntity = userRepository.findByUserId(id);
        userEntity.setStatus(0);
        userRepository.save(userEntity);
        return Utils.createResponse("OK", 1, null);
    }

    @Override
    public UserEntity getInfoVerify(String code) {
        UserEntity user = findByVerificationCode(code);

        return user;
    }

    @Override
    public List<UserEntity> getListUserByDepartment(Long id) {
        return userRepository.findByDepartment_DepartmentId(id);
    }

    @Override
    public Response updateUserEntity(UserEntityDto userEntityDto, Long id) {
        try {
            UserEntity user = userRepository.findByUserId(id);
            AppRole appRole = appRoleRepository.findByRoleId(userEntityDto.getAppRole().getRoleId());
            AppRole appRole1 = appRoleRepository.findByRoleId(user.getRole());

            user.setRole(userEntityDto.getAppRole().getRoleId());
            user.setDepartment(userEntityDto.getDepartment());
            user.setJobName(userEntityDto.getJobName());
            user = userRepository.save(user);
            UserRole userRole = userRoleRepository.getByUserAndRole(user.getUserId(), appRole1.getRoleId());
            userRoleRepository.delete(userRole);
            UserRole userRole1 = new UserRole();
            userRole1.setUser(user);
            userRole1.setAppRole(appRole);
            userRole1 = userRoleRepository.save(userRole1);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }

        return Utils.createResponse("OK", 1, null);

    }

    @Override
    public boolean checkUserName(AppUserDto appUserDto) {

        try {
            List<UserEntity> userEntities = userRepository.findAll();
            for (int i = 0; i < userEntities.size(); i++) {
                if (userEntities.get(i).getUserName() == null) {
                    continue;
                }
                if (userEntities.get(i).getUserName().equalsIgnoreCase(appUserDto.getUserName())) {
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.getMessage();
        }

        return false;
    }

    @Override
    public boolean checkEmail(AppUserDto appUserDto) {

        try {
            List<UserEntity> userEntities = userRepository.findAll();
            for (int i = 0; i < userEntities.size(); i++) {
                if (userEntities.get(i).getEmail() == null) {
                    continue;
                }
                if (userEntities.get(i).getEmail().equalsIgnoreCase(appUserDto.getEmail())) {
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.getMessage();
        }

        return false;

    }

    @Override
    public boolean checkPhoneNumber(AppUserDto appUserDto) {

        try {
            List<UserEntity> userEntities = userRepository.findAll();
            for (int i = 0; i < userEntities.size(); i++) {
                if (userEntities.get(i).getPhoneNumber() == null) {
                    continue;
                }
                if (userEntities.get(i).getPhoneNumber().equalsIgnoreCase(appUserDto.getPhoneNumber())) {
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.getMessage();
        }

        return false;

    }

}