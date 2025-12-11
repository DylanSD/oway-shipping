package com.oway.shipment.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtils {

    public static Pageable getPageable(HttpServletRequest request, String pageKey, String sizeKey) {
        String pageParam = request.getParameter(pageKey);
        String sizeParam = request.getParameter(sizeKey);
        return getPageable(pageParam, sizeParam);
    }

    public static Pageable getPageable(String page, String size) {
        try {
            return getPageable(Integer.parseInt(page), Integer.parseInt(size));
        } catch (Exception ep) {
            //ep.printStackTrace();
            //TODO use logger.
        }
        return getPageable(0, 10);
    }

    public static Pageable getPageable(Integer page, Integer size) {
        Pageable pageable;
        if (page != null && size != null) {
            pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        } else {
            pageable = Pageable.unpaged(); // Return all if no pagination is requested
        }
        return pageable;
    }
}
