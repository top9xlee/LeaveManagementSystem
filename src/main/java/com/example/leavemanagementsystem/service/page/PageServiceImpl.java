package com.example.leavemanagementsystem.service.page;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.utils.*;
import org.springframework.stereotype.*;

@Service
public class PageServiceImpl implements PageService {
    //    @Autowired
//    UserService userService;
    @Override
    public NewOutput page(int limitPageUser, int page, PageResponse response) {

        NewOutput newOutput = new NewOutput();
        newOutput.setPage(page);
        newOutput.setResponse(response);
        long total = response.getCount();
        if (response.getCount() % limitPageUser == 0) {
            newOutput.setTotalPage((int) total / limitPageUser);
        } else {
            newOutput.setTotalPage(((int) total / limitPageUser) + 1);
        }
        if (page == 1) {
            newOutput.setPreviousPage(1);
        } else if (page > 1) {
            newOutput.setPreviousPage(page - 1);
        }
        if (newOutput.getTotalPage() == 0) {
            newOutput.setTotalPage(1);
        }
        if (page == newOutput.getTotalPage()) {
            newOutput.setNextPage(newOutput.getTotalPage());
        } else if (page < newOutput.getTotalPage()) {
            newOutput.setNextPage(page + 1);
        }
        return newOutput;
    }
}
