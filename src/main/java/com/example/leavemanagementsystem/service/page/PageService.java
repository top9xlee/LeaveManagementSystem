package com.example.leavemanagementsystem.service.page;

import com.example.leavemanagementsystem.dto.*;
import com.example.leavemanagementsystem.utils.*;

public interface PageService {
    NewOutput page(int limit, int page, PageResponse response);
}
