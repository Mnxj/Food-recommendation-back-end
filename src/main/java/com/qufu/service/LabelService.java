package com.qufu.service;


import java.util.List;

public interface LabelService {
    int insertL(String latext);

    /**
     * 删除标签
     *
     * @param latext
     * @return
     */
    int delL(String latext);

    List<String> SelecetAll();
}
