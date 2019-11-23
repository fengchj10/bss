package com.angevin.mapper;

import com.angevin.domain.ReconciliaVO;
import com.angevin.entity.Reconcilia;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @auther Angevin
 * @date 2019年11月1日
 */
@Mapper
public interface ReconciliaMapper {

    List<Reconcilia> findAll();

    Page<Reconcilia> findByPage(ReconciliaVO reconcilia);

    List<Reconcilia> findById(Long id);
    List<Reconcilia> findByValueBean(ReconciliaVO reconcilia);

    void create(Reconcilia reconcilia);

    void update(Reconcilia reconcilia);

    void delete(Long id);
}
