package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {
    private String name;
    private Integer id;

    public Criteria(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
    public static class Builder <SELF extends Builder<SELF>>{
        String name;
        Integer id;
        public SELF setName(String name) {
            this.name = name;
            return (SELF) this;
        }
        public SELF setId(Integer id){
            this.id = id;
            return (SELF)this;
        }
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public static Builder builder(){
        return new Builder();
    }
}